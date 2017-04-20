package de.blackpinguin.timesigner

object FileHash extends App {
  val f = io.readFile("C:/Dokumente und Einstellungen/RCL/Desktop/6400progs.txt")
  println(sha512hex(f))
  println(f.length)
}