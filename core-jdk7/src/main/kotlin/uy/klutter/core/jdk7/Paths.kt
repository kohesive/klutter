package uy.klutter.core.jdk7


import java.nio.file.Files
import java.nio.file.Path

public fun Path.exists(): Boolean = Files.exists(this)
public fun Path.notExists(): Boolean = !this.exists()

public fun Path.deleteRecursive(): Boolean = this.toFile().deleteRecursively()