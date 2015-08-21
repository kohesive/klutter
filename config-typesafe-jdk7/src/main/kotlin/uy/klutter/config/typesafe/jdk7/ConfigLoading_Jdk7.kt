package uy.klutter.config.typesafe.jdk7

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import com.typesafe.config.ConfigParseOptions
import java.io.File
import java.nio.file.Path

public class FileConfig(path: Path, failIfMissing: Boolean = true): uy.klutter.config.typesafe.FileConfig(path.toFile(), failIfMissing)

