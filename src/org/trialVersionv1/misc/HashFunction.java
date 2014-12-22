package org.trialVersionv1.misc;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class HashFunction
{
  String name;
  String key;
  
  public String generateSerialKey(String name)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    String enc = SHA2(name, "SHA-256");
    String enc2 = SHA2(name, "SHA-384");
    String enc3 = SHA2(name, "SHA-512");
    String enc4 = enc + enc2 + enc3;
    


    this.key = String.valueOf(
      enc.charAt(13) + enc.charAt(21) + enc.charAt(17) + enc.charAt(8) + "-" + 
      enc2.charAt(3) + enc2.charAt(8) + enc2.charAt(15) + enc2.charAt(11) + enc2.charAt(18) + "-" + 
      enc3.charAt(22) + enc3.charAt(32) + enc3.charAt(17) + enc3.charAt(73) + "-" + 
      enc4.charAt(36) + enc4.charAt(54) + enc4.charAt(77) + enc4.charAt(12) + enc4.charAt(63));
    



    System.out.println("Key: " + this.key);
    
    return this.key;
  }
  
  public String encryptedDate(Calendar calendarExpDate)
  {
    String encNum = calendarExpDate.toString();
    
    return encNum;
  }
  
  private String calculateSecurityHash(String stringInput, String algorithmName)
    throws NoSuchAlgorithmException
  {
    String hexMessageEncode = "";
    byte[] buffer = stringInput.getBytes();
    MessageDigest messageDigest = MessageDigest.getInstance(algorithmName);
    messageDigest.update(buffer);
    byte[] messageDigestBytes = messageDigest.digest();
    for (int index = 0; index < messageDigestBytes.length; index++)
    {
      int countEncode = messageDigestBytes[index] & 0xFF;
      if (Integer.toHexString(countEncode).length() == 1) {
        hexMessageEncode = hexMessageEncode + "0";
      }
      hexMessageEncode = hexMessageEncode + Integer.toHexString(countEncode);
    }
    return hexMessageEncode;
  }
  
  public String SHA2(String text, String SHA_Type)
    throws NoSuchAlgorithmException, UnsupportedEncodingException
  {
    String hexMessageEncode = "";
    MessageDigest mesd = MessageDigest.getInstance(SHA_Type);
    byte[] bytes = text.getBytes("iso-8859-1");
    mesd.update(bytes, 0, bytes.length);
    byte[] sha2hash = mesd.digest();
    for (int index = 0; index < sha2hash.length; index++)
    {
      int countEncode = sha2hash[index] & 0xFF;
      if (Integer.toHexString(countEncode).length() == 1) {
        hexMessageEncode = hexMessageEncode + "0";
      }
      hexMessageEncode = hexMessageEncode + Integer.toHexString(countEncode);
    }
    return hexMessageEncode;
  }
}
