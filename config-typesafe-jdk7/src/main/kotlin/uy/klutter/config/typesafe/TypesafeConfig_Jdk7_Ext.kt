package uy.klutter.config.typesafe

import java.nio.file.Path
import java.nio.file.Paths

fun ConfiguredValue.asPath(): Path = Paths.get(cfg.getString(key).trim()).toAbsolutePath()
fun ConfiguredValue.asPathOrNull(): Path? = if (exists()) asPath() else null

fun ConfiguredValue.asPathRelative(relativeTo: Path): Path = relativeTo.resolve(cfg.getString(key).trim()).toAbsolutePath()
fun ConfiguredValue.asPathRelativeOrNull(relativeTo: Path): Path? = if (exists()) asPathRelative(relativeTo) else null

fun ConfiguredValue.asPathSibling(relativeTo: Path): Path = relativeTo.resolveSibling(cfg.getString(key).trim()).toAbsolutePath()
fun ConfiguredValue.asPathSiblingOrNull(relativeTo: Path): Path? = if (exists()) asPathSibling(relativeTo) else null

fun ConfiguredValue.asPathList(): List<Path> =  cfg.getStringList(key).map { Paths.get(it).toAbsolutePath() }
fun ConfiguredValue.asPathList(defaultValue: List<Path>): List<Path> = if (exists()) asPathList() else defaultValue
fun ConfiguredValue.asPathListOrNull(): List<Path>? = if (exists()) asPathList() else null
fun ConfiguredValue.asPathListOrEmpty(): List<Path> = asPathList(emptyList())