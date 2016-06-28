package uy.klutter.core.common


import java.nio.file.Files
import java.nio.file.Path

fun Path.exists(): Boolean = Files.exists(this)
fun Path.notExists(): Boolean = !this.exists()

fun Path.deleteRecursively(): Boolean = this.toFile().deleteRecursively()