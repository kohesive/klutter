package uy.klutter.graph.netflix.tests

import org.junit.Test
import uy.klutter.graph.netflix.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import uy.klutter.graph.netflix.tests.MyNodes.*
import uy.klutter.graph.netflix.tests.MyRelations.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

enum class MyNodes {
    Movie,
    Actor,
    Director,
    Award
}

enum class MyRelations {
    StarredIn,
    Starring,
    DirectedBy,
    Directed,
    WonAward,
    AwardWinner
}

class TestNetflixGraph {
    private fun Set<NodeAndId<MyNodes>>.hasOnly(testFor: Set<NodeAndId<MyNodes>>): Boolean {
        return (this.size == testFor.size && this.containsAll(testFor))
    }

    @Test fun basicGraphBuilding() {
        val schema = defineGraphSchema<MyNodes, MyRelations>(RelationStructure.COMPACT) {
            // TODO: maybe syntax like
            //    Movie[Starring]..Actor[StarredIn]
            // need to be able to show many versus single in other syntax

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

        val builder = constructGraph(schema) {
            // TODO: maybe syntax options like
            //     Move["Star Wars"]..Starring..Actor["Harrison Ford"]
            //     Move["Star Wars"] - Starring - Actor["Harrison Ford"]
            //     Move["Star Wars"] + Starring + Actor["Harrison Ford"]

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

        // serialize so we test end to end
        val outputBuffer = ByteArrayOutputStream()
        builder.serialize(outputBuffer)

        // bring graph out of serializatino and use
        useGraph<MyNodes, MyRelations>(ByteArrayInputStream(outputBuffer.toByteArray())) {
            assertTrue(Actor["Harrison Ford"].getConnections(StarredIn).hasOnly(setOf(Movie("Star Wars"), Movie("Indiana Jones"))))
            assertEquals(Movie("Star Wars"), Actor["Mark Hamill"].getSingleConnection(StarredIn))
            assertEquals(Movie("Star Wars"), Actor["Carrie Fisher"].getSingleConnection(StarredIn))
            assertEquals(Movie("Star Wars"), Actor["Peter Mayhew"].getSingleConnection(StarredIn))
            assertTrue(Movie["Star Wars"].getConnections(Starring).hasOnly(setOf(Actor("Harrison Ford"), Actor("Mark Hamill"), Actor("Carrie Fisher"), Actor("Peter Mayhew"))))
            assertEquals(Director("George Lucas"), Movie["Star Wars"].getSingleConnection(DirectedBy))
            assertEquals(Movie("Star Wars"), Director["George Lucas"].getSingleConnection(Directed))

            assertEquals(Director("Steven Spielberg"), Award["1994 Academy Award for Best Picture"].getSingleConnection(AwardWinner))
            assertEquals(Award("2005 AFI Life Achievement Award"), Director["George Lucas"].getSingleConnection(WonAward))
            assertEquals(Movie("Star Wars"), Award["1979 Academy Award for Best Visual Effects"].getSingleConnection(AwardWinner))
            assertEquals(Movie("Star Wars"), Award["1979 Academy Award for Best Original Music Score"].getSingleConnection(AwardWinner))

            assertTrue(Award["People's Choice Award"].getConnections(AwardWinner).hasOnly(setOf(Movie("Star Wars"), Movie("Indiana Jones"), Director("Steven Spielberg"), Actor("Harrison Ford"))))
        }
    }
}

