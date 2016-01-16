package com.baicai.util;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * <pre>
 * 基于密匙的xor加解密
 * 可以进行安全的二进制加密
 * </pre>
 */
public class XorEncrypt {
	/**
     * 私有密匙
     */
    private String privateKey = "EnCY9_hor_iNy4VA297768038ty8FG3lsA";
    /**
     * 密匙
     */
    private String key;
    /**
     * 协同密匙
     */
    private String companionKey;
    /**
    *
    * @param key 公有密匙
     * @throws NoSuchAlgorithmException 
    */
   public XorEncrypt(String key){
       this.key = CommonUtil.md5(privateKey + key);
       this.companionKey = this.genCompanion(key);
   }
   /**
    * 设置私有密匙
    * @param privateKey
    */
   public void setPrivateKey(String privateKey) {
       this.privateKey = privateKey;
   }
   /**
    * 制取协同密匙
    * @param <type> $key
    * @return <type>
    */
   private String genCompanion(String key) {
       StringBuilder strBuilder = new StringBuilder();
       byte[] keys = key.getBytes();
       strBuilder.append(privateKey);
       for (byte _key : keys) {
           strBuilder.append(_key);
       }

       return strBuilder.toString();
   }
   /**
    * 异或加解密
    * @param Mixed $content
    * @param String $pKey
    * @return String
    */
   private String getXorBilateral(String content, String pKey) {
       int len = pKey.length();
       int count = content.length();
       char current;
       StringBuilder strBuilder = new StringBuilder();
       int kIdx = 0;
       int i = 0;
       for (i = 0; i < count; i++) {
           if (kIdx == len) {
               kIdx = 0;
           }
           current = content.charAt(i);
           strBuilder.append((char) (current ^ pKey.charAt(kIdx)));
           kIdx++;
       }
       return strBuilder.toString();
   }

   /**
    * 加密,效率待优化
    * @param content
    * @return String
    * @throws IOException 
    */
   public String encrypt(String content){
       String randKey = RandomHelper.toString(16);
       content = getXorBilateral(content, this.key);
       content = getXorBilateral(content, randKey);
       content = randKey + content;
       content = getXorBilateral(content, this.companionKey);
       content =Base64.encodeToString(content);
       return content;
   }

   /**
    * 解密
    * @param content
    * @return String
    * @throws IOException
    */
   public String decrypt(String content){
       content = Base64.decodeToString(content);
       content = getXorBilateral(content, this.companionKey);
       String randKey = content.substring( 0, 16);
       content = content.substring(16, content.length());
       content = getXorBilateral(content, randKey);
       content = getXorBilateral(content, this.key);
       return content;
   }
   
}
