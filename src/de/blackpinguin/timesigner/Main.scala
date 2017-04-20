package de.blackpinguin.timesigner

object Main {
  def sign(arg:String): Unit = {
    try {
      require(isSHA512(arg))
      val s = Sign(unhex(arg))
      println(s)
    } catch {
      case e: Throwable => 
        println(<timesign><exception class={e.getClass.toString}><message>{e.getMessage}</message><stacktrace>{e.getStackTraceString.toString}</stacktrace></exception></timesign>)
    }
  }
  
  def verify(arg:String): Unit = {
    try {
      val v = Verify(scala.xml.XML.loadString(arg))
      println(v)
    } catch {
      case e: Throwable => 
        println(<timesign><exception class={e.getClass.toString}><message>{e.getMessage}</message><stacktrace>{e.getStackTraceString.toString}</stacktrace></exception></timesign>)
    }
  }
  

}