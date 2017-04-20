<?php

$hash = "error";
if(array_key_exists('hash',$_GET))
{ 
	$hash = escapeshellarg($_GET['hash']);
}
/*
else{
	if(array_key_exists('value',$_GET))
	{
		$hash = hash("sha512",escapeshellarg($_GET['value']));
	}
}
*/

header("Content-Type:text/xml");
system("java -classpath \"Signer.jar;scala-library.jar\" Signer $hash");
?>