/*    */ package org.biz.app.ui.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.security.GeneralSecurityException;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.SecretKey;
/*    */ import javax.crypto.SecretKeyFactory;
/*    */ import javax.crypto.spec.PBEKeySpec;
/*    */ import javax.crypto.spec.PBEParameterSpec;
/*    */ import sun.misc.BASE64Decoder;
/*    */ import sun.misc.BASE64Encoder;
/*    */ 
/*    */ public class DataSecUtil
/*    */ {
/* 16 */   private static final char[] PASSWORD = "enfldsgbnlsngdlksdsgm".toCharArray();
/* 17 */   private static final byte[] SALT = { -34, 51, 16, 18, -34, 51, 16, 18 };
/*    */ 
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 23 */     String originalPassword = "secret";
/* 24 */     System.out.println("Original password: " + originalPassword);
/* 25 */     String encryptedPassword = encrypt(originalPassword);
/* 26 */     System.out.println("Encrypted password: " + encryptedPassword);
/* 27 */     String decryptedPassword = decrypt(encryptedPassword);
/* 28 */     System.out.println("Decrypted password: " + decryptedPassword);
/*    */   }
/*    */ 
/*    */   public static String encrypt(String property) throws GeneralSecurityException {
/* 32 */     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
/* 33 */     SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
/* 34 */     Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
/* 35 */     pbeCipher.init(1, key, new PBEParameterSpec(SALT, 20));
/* 36 */     return base64Encode(pbeCipher.doFinal(property.getBytes()));
/*    */   }
/*    */ 
/*    */   public static String base64Encode(byte[] bytes)
/*    */   {
/* 41 */     return new BASE64Encoder().encode(bytes);
/*    */   }
/*    */ 
/*    */   public static String decrypt(String property) throws GeneralSecurityException, IOException {
/* 45 */     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
/*    */ 
/* 47 */     SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
/* 48 */     Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
/* 49 */     pbeCipher.init(2, key, new PBEParameterSpec(SALT, 20));
/* 50 */     return new String(pbeCipher.doFinal(base64Decode(property)));
/*    */   }
/*    */ 
/*    */   public static byte[] base64Decode(String property) throws IOException
/*    */   {
/* 55 */     return new BASE64Decoder().decodeBuffer(property);
/*    */   }
/*    */ }

/* Location:           D:\intel Biz\jxpower\JxPower.jar
 * Qualified Name:     util.ProtectedConfigFile
 * JD-Core Version:    0.5.4
 */