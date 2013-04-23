package test

import scala.reflect.io._
import dotty.tools.dotc.util._
import dotty.tools.dotc.parsing._
import Tokens._, Scanners._
import org.junit.Test

class ScannerTest extends DottyTest {

  def scan(name: String): Unit = scan(new PlainFile(name))

  def scan(file: PlainFile): Unit = {
    println("***** scanning " + file)
    val source = new SourceFile(file)
    val scanner = new Scanner(source)
    var i = 0
    while (scanner.token != EOF) {
//    print("["+scanner.token.show+"]")
      scanner.nextToken
//      i += 1
//      if (i % 10 == 0) println()
    }
  }

  def scanDir(path: String): Unit = scanDir(Directory(path))

  def scanDir(dir: Directory): Unit = {
    for (f <- dir.files)
      if (f.name.endsWith(".scala")) scan(new PlainFile(f))
    for (d <- dir.dirs)
      scanDir(d.path)
  }

  @Test
  def scanList() = {
    println(System.getProperty("user.dir"))
    scan("src/dotty/tools/dotc/core/Symbols.scala")
    scan("src/dotty/tools/dotc/core/Symbols.scala")
  }

  @Test
  def scanDotty() = {
    scanDir("src")
  }

  @Test
  def scanScala() = {
    scanDir("/Users/odersky/workspace/scala/src")
  }
}