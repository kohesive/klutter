package uy.klutter.core.jdk

import java.io.InputStream
import java.net.URL
import java.net.URLClassLoader
import java.util.*

// borrowed from KTOR
//   https://github.com/Kotlin/ktor/blob/fd99512bf8e207fef7af5ae6521f871ea9d2fa7b/ktor-core/src/org/jetbrains/ktor/host/OverridingClassLoader.kt
// probably originally from:
//   https://dzone.com/articles/java-classloader-handling
// then updated to fix resource loading

/**
 * A parent-last classloader that will try the child classloader first and then the parent.
 */
class ChildFirstClassloader(classpath: List<URL>, parentClassLoader: ClassLoader?) : ClassLoader(parentClassLoader) {
    private val childClassLoader = ChildURLClassLoader(classpath.toTypedArray(), parent)

    @Synchronized
    override fun loadClass(name: String, resolve: Boolean): Class<*> {
        try {
            // first we try to find a class inside the child classloader
            return childClassLoader.findClass(name)
        } catch (e: ClassNotFoundException) {
            // didn't find it, try the parent
            return super.loadClass(name, resolve)
        }
    }

    override fun loadClass(name: String): Class<*> {
        return loadClass(name, false)
    }

    override fun findClass(name: String): Class<*> {
        try {
            return childClassLoader.findClass(name)
        } catch (e: ClassNotFoundException) {
            /* nop */
        }
        return super.findClass(name)
    }


    /**
     * This class delegates (child then parent) for the findClass method for a URLClassLoader.
     * We need this because findClass is protected in URLClassLoader
     */
    private class ChildURLClassLoader(urls: Array<URL>, private val realParent: ClassLoader) : URLClassLoader(urls, null) {
        public override fun findClass(name: String): Class<*> {
            val loaded = super.findLoadedClass(name)
            if (loaded != null)
                return loaded

            try {
                // first try to use the URLClassLoader findClass
                return super.findClass(name)
            } catch (e: ClassNotFoundException) {
                // if that fails, we ask our real parent classloader to load the class (we give up)
                return realParent.loadClass(name)
            }
        }
    }

    override fun findResource(name: String): URL? {
        return childClassLoader.findResource(name) ?: super.findResource(name)
    }

    override fun getResource(name: String): URL? {
        return childClassLoader.getResource(name) ?: super.getResource(name)
    }

    class UrlEnumeration(val iter: Iterator<URL>): Enumeration<URL> {
        override fun nextElement(): URL {
            return iter.next()
        }

        override fun hasMoreElements(): Boolean {
            return iter.hasNext()
        }

    }

    override fun findResources(name: String): Enumeration<URL> {
        val combined = childClassLoader.findResources(name).asSequence() + super.findResources(name).asSequence()
        return UrlEnumeration(combined.iterator())
    }

    override fun getResources(name: String): Enumeration<URL> {
        val combined = childClassLoader.getResources(name).asSequence() + super.getResources(name).asSequence()
        return UrlEnumeration(combined.iterator())
    }

    override fun getResourceAsStream(name: String): InputStream? {
        return childClassLoader.getResourceAsStream(name) ?: super.getResourceAsStream(name)
    }

    override fun setClassAssertionStatus(className: String?, enabled: Boolean) {
        childClassLoader.setClassAssertionStatus(className, enabled)
        super.setClassAssertionStatus(className, enabled)
    }

    override fun clearAssertionStatus() {
        childClassLoader.clearAssertionStatus()
        super.clearAssertionStatus()
    }

    override fun setDefaultAssertionStatus(enabled: Boolean) {
        childClassLoader.setDefaultAssertionStatus(enabled)
        super.setDefaultAssertionStatus(enabled)
    }

    override fun setPackageAssertionStatus(packageName: String, enabled: Boolean) {
        childClassLoader.setPackageAssertionStatus(packageName, enabled)
        super.setPackageAssertionStatus(packageName, enabled)
    }


    // TODO: should these be overriden?
    //  override fun getPackages(): Array<out Package>? { }
    //  override fun getPackage(name: String?): Package? { }
    //  override fun definePackage(name: String?, specTitle: String?, specVersion: String?, specVendor: String?, implTitle: String?, implVersion: String?, implVendor: String?, sealBase: URL?): Package? { }
    //  override fun addClass(c: Class<*>?) { }
    //  override fun getClassLoadingLock(className: String?): Any? { }
}