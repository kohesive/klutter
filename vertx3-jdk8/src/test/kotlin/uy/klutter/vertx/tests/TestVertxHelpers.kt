package uy.klutter.vertx.tests

import io.vertx.core.AbstractVerticle
import org.junit.Test
import uy.klutter.vertx.promiseClose
import uy.klutter.vertx.promiseDeployVerticle
import uy.klutter.vertx.promiseUndeploy
import uy.klutter.vertx.vertx


public class TestVertxHelpers {
    @Test public fun testStartupAndDeployPromises() {
        val _vertx = vertx().get()
        val _deployment = _vertx.promiseDeployVerticle(TestVertxVerticle::class.java).get()
        _vertx.promiseUndeploy(_deployment).get()
        _vertx.promiseClose().get()

    }
}

public class TestVertxVerticle : AbstractVerticle() {
    override fun start() {
        println("Started")
    }

}