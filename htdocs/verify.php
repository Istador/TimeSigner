<?php
$xml = escapeshellarg("<error/>");
if(array_key_exists('xml',$_POST))
{ 
	$xml = escapeshellarg($_POST['xml']);
}

header("Content-Type:text/xml");
system("java -classpath \"Signer.jar;scala-library.jar\" Verifier $xml");
?>