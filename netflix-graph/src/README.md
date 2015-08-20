## klutter/netflix-graph

A wrapper library around the "compressed directed graph library" from [Netflix-Graph project](https://github.com/Netflix/netflix-graph) 
-- a Kotlin library to construct, serialize and compress, then load and use a directed graph data at in-memory speeds.  In addition to the features of Netflix-Graph, Klutter includes:

* Schema definition - define node types and relationships as Enums, and allowed linkages
* Ordinal tracking - easy ways to track the ordinals for every node, and use them both in construction and with serialization/deserialization of the graph
* Disambiguate relationships - when you have A -> "relate" -> B and A -> "relate" -> C with the same relationship name, Netflix-Graph cannot handle that natively.  Klutter allows this relationship both for construction and querying.
* and a much funner API

Sample Schema code:

```kotlin
val schema = defineGraphSchema<MyNodes, MyRelations>(RelationStructure.COMPACT) {
    from(Movie).connectEdges(Starring).to(Actor).autoMirrorEdges(StarredIn)
    from(Movie).connectOneEdge(DirectedBy).to(Director).autoMirrorEdges(Directed)

    // we support duplicate relations A->R->B and A->R->C by creating A->R.B->B and A->R.C->C under the covers
    // here, from the perspective of Award, it has three possible WonAward relations all with same name
    from(Movie).connectEdges(WonAward).to(Award).hashed().autoMirrorEdges(AwardWinner).compact()
    from(Actor).connectEdges(WonAward).to(Award).autoMirrorEdges(AwardWinner)
    from(Director).connectEdges(WonAward).to(Award).autoMirrorEdges(AwardWinner)

    modelScope() {
      // TODO: test relationships in model scope
    }
}
```

Note that mirrored edges mean that if you create one direction, the other direction is automatically added as well. The same for remove.  You can see that here when building the graph:

```kotlin
val builder = constructGraph(schema) {
    connect(Movie["Star Wars"]).edge(Starring).to(Actor["Harrison Ford"])
    connect(Movie["Star Wars"]).edge(Starring).to(Actor["Mark Hamill"])
    connect(Movie["Star Wars"]).edge(Starring).to(Actor["Carrie Fisher"])
    connect(Movie["Star Wars"]).edge(Starring).to(Actor["Peter Mayhew"])

    // less objects created version of syntax...
    connect(Movie["Star Wars"], DirectedBy, Director["George Lucas"])
    connect(Movie["Star Wars"], WonAward, Award["1979 Academy Award for Best Visual Effects"])
    connect(Movie["Star Wars"], WonAward, Award["1979 Academy Award for Best Original Music Score"])
    connect(Actor["Harrison Ford"], WonAward, Award["2000 People's Choice Award"])
    connect(Director["George Lucas"], WonAward, Award["2005 AFI Life Achievement Award"])
    connect(Award["1994 Academy Award for Best Picture"], AwardWinner, Director["Steven Spielberg"])
    connect(Movie["Indiana Jones"], Starring, Actor["Harrison Ford"])
    connect(Movie["Indiana Jones"], DirectedBy, Director["Steven Spielberg"])

    connect(Actor["Harrison Ford"], WonAward, Award["People's Choice Award"])
    connect(Director["Steven Spielberg"], WonAward, Award["People's Choice Award"])
    connect(Movie["Star Wars"], WonAward, Award["People's Choice Award"])
    connect(Movie["Indiana Jones"], WonAward, Award["People's Choice Award"])
}
```

Serializing the graph to disk:

```kotlin
 builder.serialize(outputStream)
```

Loading and using (usually in another process), this code is from a test case:

```kotlin
useGraph<MyNodes, MyRelations>(inputStream) {
    test.assertTrue(Actor["Harrison Ford"].getConnections(StarredIn).hasOnly(setOf(Movie("Star Wars"), Movie("Indiana Jones"))))
    assertEquals(Movie("Star Wars"), Actor["Mark Hamill"].getSingleConnection(StarredIn))
    assertEquals(Movie("Star Wars"), Actor["Carrie Fisher"].getSingleConnection(StarredIn))
    assertEquals(Movie("Star Wars"), Actor["Peter Mayhew"].getSingleConnection(StarredIn))
    test.assertTrue(Movie["Star Wars"].getConnections(Starring).hasOnly(setOf(Actor("Harrison Ford"), Actor("Mark Hamill"), Actor("Carrie Fisher"), Actor("Peter Mayhew"))))
    assertEquals(Director("George Lucas"), Movie["Star Wars"].getSingleConnection(DirectedBy))
    assertEquals(Movie("Star Wars"), Director["George Lucas"].getSingleConnection(Directed))

    assertEquals(Director("Steven Spielberg"), Award["1994 Academy Award for Best Picture"].getSingleConnection(AwardWinner))
    assertEquals(Award("2005 AFI Life Achievement Award"), Director["George Lucas"].getSingleConnection(WonAward))
    assertEquals(Movie("Star Wars"), Award["1979 Academy Award for Best Visual Effects"].getSingleConnection(AwardWinner))
    assertEquals(Movie("Star Wars"), Award["1979 Academy Award for Best Original Music Score"].getSingleConnection(AwardWinner))

    test.assertTrue(Award["People's Choice Award"].getConnections(AwardWinner).hasOnly(setOf(Movie("Star Wars"), Movie("Indiana Jones"), Director("Steven Spielberg"), Actor("Harrison Ford"))))
}
```        

See the full test cases in [TestNetflixGraph.kt](https://github.com/klutter/klutter/blob/master/netflix-graph/src/test/kotlin/uy/klutter/graph/netflix/TestNetflixGraph.kt) for a good example of using the library and the API.

The Netflix-Graph has been used to create graphs that take for example 12G of memory for construction, and use only 2G after derserialization.  Including 10's of millions of nodes+edges.  We have batch machings that generate
new graphs nightly that each processing node uses locally to do 10,000 traversals or more per second.

## Road Map: (random order)

* Delta building of graphs - allow adding additions incrementally with low cost at construction time by stacking graph "segments" and querying through the stack.  Same for ordinals.
* Alterative store for ordinals allowing access from disk, possible leveldb, mapdb, lucene, or other.
* Traversal engine - given a starting point, and traversal rules, collect things from the graph.  This isn't a query language it is something else, fun, and we use it to do really fast traversals that are easy to write.
* Take advantage of the recent performance improvements for building by keeping references to the builder objects.
* Track changes for 1.6-SNAPSHOT onwards
