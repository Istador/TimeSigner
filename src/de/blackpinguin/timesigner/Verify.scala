package de.blackpinguin.timesigner

import java.security._
import javax.security.cert._

object Verify {
  def apply(xml: scala.xml.Node): scala.xml.Node = scala.xml.Utility.trim(xml) match {
    case <timesign><datetime>{ dt }</datetime><hash>{ h }</hash><signature>{ s }</signature><certificate>{ c }</certificate></timesign> => {

      //println("<!--\n"+c.text+"\n-->\n")
      
      //read cert
      val x509 = X509Certificate.getInstance(b64.dec(c.text)) //base64 to byte[] to cert

      //init verifier with public key and algorithm
      val v = Signature.getInstance(x509.getSigAlgName)
      v.initVerify(x509.getPublicKey)

      //what is signed
      v.update(dt.text.getBytes) //String to byte[]
      v.update(unhex(h.text))    //HexString to byte[]

      //verify signature
      <timesign><result value={v.verify(b64.dec(s.text)).toString}/></timesign>  //returns boolean
    }
  }
}