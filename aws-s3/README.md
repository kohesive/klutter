## klutter/aws-s3

Helper functions for Amazon AWS S3 objects.  See [extension functions in the source code](../aws-s3-jdk6/src/main/kotlin/uy/klutter/aws/s3/AmazonS3Client_Ext.kt).

Module is available in artifacts:

* uy.klutter:klutter-aws-s3 (latest JDK, currently JDK 6)
* [uy.klutter:klutter-aws-s3-jdk6](../aws-s3-jdk6)

## Injekt

An Injekt module is included to provide a singleton AmazonS3Client that uses the `defaultSafeCredentialsProviderChain()` from `klutter-aws-core`

Include this module into your InjektMain using something like:

```
class MyApp {
    companion object : InjektMain() {
        // my app starts here with a static main()
        platformStatic public fun main(args: Array<String>) {
            MyApp().run()
        }

        // the Injekt system will call me back here on a method I override.  And all my functions for registration are
        // easy to find on the receiver class
        override fun InjektRegistrar.registerInjectables() {
            // add my singletons, factories, keyed factories, per-thread factories, ...
            ...

            // import prebuilt Injekt modules
            importModule(AmazonS3Injektables)  
        }
    }

    ...
    // later, use them in properties in any other class
    val s3: AmazonS3Client by Delegates.injectLazy()
    // or use them anywhere in code 
    val s3: AmazonS3Client = Inject.get()
    // or another form of the same 
    val s3 = Inject<AmazonS3Client>.get()
    // or in constructors or method definitions
    public fun doSomethingWithS3(s3: AmazonS3Client = Inject.get()) { ... }
}
```
