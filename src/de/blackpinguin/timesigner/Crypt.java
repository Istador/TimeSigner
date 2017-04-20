package de.blackpinguin.timesigner;

import java.security.MessageDigest;

/**
 * 
 * Quellen:
 * http://blog.fethilale.com/sha512-hashing-on-java/
 *
 * @author Robin Christopher Ladiges
 */
public class Crypt {
	
	
	/**
	 * SHA512 einer Bytefolge
	 * @param in zu hashender Wert
	 * @return der gehashte Wert
	 * @author Robin Christopher Ladiges
	 */
	public static byte[] sha512(byte[] in){
		byte[] result = null;
		try{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(in);
			result = md.digest();
		}
		catch(Exception e){System.out.println(e.getMessage());}
		return result;
	}
	
	
	/**
	 * SHA512 einer Bytefolge eines Strings
	 * @param in zu hashende Zeichenkette
	 * @return der gehashte Wert
	 * @author Robin Christopher Ladiges
	 */
	public static byte[] sha512(String in){
		return sha512(in.getBytes());
	}
	
	
	/**
	 * Wandelt eine Bytefolge in die dazugehörige Hrxadezimalschreibweise als String um
	 * @param in Bytefolge
	 * @return Hexadezimale Repräsentation der Eingabe 
	 * @author Robin Christopher Ladiges
	 */
	public static String byteToHexString(byte[] in){
		String out = "";
		
		for(int i = 0; i < in.length; i++){
			String tmp = Integer.toHexString(new Byte(in[i]));
			
			while(tmp.length() < 2){
				tmp = "0"+tmp;
			}
			
			tmp = tmp.substring(tmp.length()-2);
			out += tmp;
		}
		
		return out;
	}

        
        /**
	 * Wandelt einen String in Hexadezimalschreibweise in eine Bytefolge um
	 * @param in Hexadezimale Repräsentation als String
	 * @return dazugehörige Bytefolge der Eingabe
	 * @author Robin Christopher Ladiges
	 */
        public static byte[] hexStringToByte(String in){
            int length = in.length() / 2 + in.length() % 2;
            byte[] result = new byte[length];
            
            String temp = in;
            byte c = 0;
            for(int i=0; i < length; i++){
                if(i==0 && in.length() % 2 != 0){
                    c = (byte) (int) Integer.parseInt(temp.substring(0,1), 16);
                    result[i] = c;
                    temp = temp.substring(1);
                }
                else{
                    c = (byte) (int) Integer.parseInt(temp.substring(0,2), 16);
                    result[i] = c;
                    temp = temp.substring(2);
                }
            }
            
            return result;
        }
        
        
	/**
	 * Gibt von einem Eingabestring den SHA512 Hash in Hexadezimaler Schreibweise als String aus
	 * @param in Eingabestring
	 * @return Hexadezimale Repräsentation des SHA512 Eingabestring 
	 * @author Robin Christopher Ladiges
	 */	
	public static String sha512hex(String in){
		return byteToHexString(sha512(in));
	}

}