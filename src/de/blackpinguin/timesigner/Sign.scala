package de.blackpinguin.timesigner

import java.io._
import java.util.Date
import java.text.{SimpleDateFormat=>SDF}
import java.security._
import java.security.spec._
import javax.security.cert._

object Sign {
  //openssl genrsa 4096 > c:\priv.key
  //openssl pkcs8 -topk8 -nocrypt -outform DER < c:\priv.key > c:\priv.pk8
  //openssl req -new -x509 -nodes -sha512 -days 365 -key c:\priv.key > c:\pub.cert
  
  def apply(hash:Array[Byte]):scala.xml.Node = {
    
    //make timestamp
    val dt = new SDF("yyyy-MM-dd HH:mm:ss Z").format(new Date)
    
    //load cert files I/O
    val cert = io.readFile("pub.cert")
    val pk8 = io.readFile("priv.pk8")
    
    //get private key
    val kspec = new PKCS8EncodedKeySpec(pk8);
    val privKey = KeyFactory.getInstance("RSA").generatePrivate(kspec);
    
    //init signer with cert-algorithm and private key
    val x509 = X509Certificate.getInstance(cert)
    val v = Signature.getInstance(x509.getSigAlgName)
    v.initSign(privKey, new SecureRandom)
    
    //what to sign
    v.update(dt.toString.getBytes)
    v.update(hash)
    
    //sign
    val s = v.sign
    
    //xml output
<timesign>
<datetime>{dt}</datetime>
<hash>{hex(hash)}</hash>
<signature>
{b64.enc(s)}
</signature>
<certificate>
{b64.enc(x509.getEncoded)}
</certificate>
</timesign>
  }
}