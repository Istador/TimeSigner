package de.blackpinguin.timesigner

object io{
  def readFile(path:String):Array[Byte] = {
    val file = new java.io.File(path)
    val data = new Array[Byte](file.length.toInt)
    val fis = new java.io.FileInputStream(file)
    fis.read(data)
    fis.close
    data
  }
}

object isSHA512 {
  def apply(in:String):Boolean = {
    (in.length == 128) &&
    in.forall(c => ('0' <= c && c <= '9') || ('a' <= c && c <= 'f') || ('A' <= c && c <= 'F') )
  }
}

object sha512 {
  def apply(in:Array[Byte]):Array[Byte] = Crypt.sha512(in)
  
  def apply(in:String):Array[Byte] = apply(in.getBytes)
}

object sha512hex {
  def apply(in:Array[Byte]):String = hex(sha512(in))
  def apply(in:String):String = hex(sha512(in)) 
}


object hex{
  //http://stackoverflow.com/questions/2756166/what-is-are-the-scala-ways-to-implement-this-java-byte-to-hex-class
  //def apply(bytes:Array[Byte]):String = bytes.map{ b => String.format("%02X", new java.lang.Integer(b & 0xff)) }.mkString
  def apply(bytes:Array[Byte]):String = Crypt.byteToHexString(bytes)
}

object unhex{
  def apply(hex:String):Array[Byte] = Crypt.hexStringToByte(hex)
}

object b64{
  def dec(in:String):Array[Byte]=
    new sun.misc.BASE64Decoder().decodeBuffer(in)
  
  def enc(in:Array[Byte]):String = 
    new sun.misc.BASE64Encoder().encodeBuffer(in).replaceAll(System.getProperty("line.separator"), "")
}