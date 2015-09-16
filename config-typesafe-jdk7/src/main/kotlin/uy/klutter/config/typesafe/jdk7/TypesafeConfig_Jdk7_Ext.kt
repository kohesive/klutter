package uy.klutter.config.typesafe.jdk7

import uy.klutter.config.typesafe.ConfiguredValue
import java.nio.file.Path
import java.nio.file.Paths

public fun ConfiguredValue.asPath(): Path = Paths.get(cfg.getString(key).trim()).toAbsolutePath()
public fun ConfiguredValue.asPathOrNull(): Path? = if (exists()) asPath() else null

public fun ConfiguredValue.asPathRelative(relativeTo: Path): Path = relativeTo.resolve(cfg.getString(key).trim()).toAbsolutePath()
public fun ConfiguredValue.asPathRelativeOrNull(relativeTo: Path): Path? = if (exists()) asPathRelative(relativeTo) else null

public fun ConfiguredValue.asPathSibling(relativeTo: Path): Path = relativeTo.resolveSibling(cfg.getString(key).trim()).toAbsolutePath()
public fun ConfiguredValue.asPathSiblingOrNull(relativeTo: Path): Path? = if (exists()) asPathSibling(relativeTo) else null

public fun ConfiguredValue.asPathList(): List<Path> =  cfg.getStringList(key).map { Paths.get(it).toAbsolutePath() }
public fun ConfiguredValue.asPathList(defaultValue: List<Path>): List<Path> = if (exists()) asPathList() else defaultValue
public fun ConfiguredValue.asPathListOrNull(): List<Path>? = if (exists()) asPathList() else null
public fun ConfiguredValue.asPathListOrEmpty(): List<Path> = asPathList(emptyList())