package hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtil {
	public static String getDigest(String filePath,String algorithm){
		File file=new File(filePath);
		try (
                 InputStream fis =new FileInputStream(file) ;
        	){
           
            byte[]buffer=fis.readAllBytes();
        	MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(buffer);
            return bytesToHexString(messageDigest.digest());
        } catch(Exception e){
        	e.printStackTrace();
        }
        return null;
    }
    /**
     * 将字节数组转换成16进制字符串
     * @param bytes 即将转换的数据
     * @return 16进制字符串
     */
    private static String bytesToHexString(byte[] bytes){
        StringBuffer sb = new StringBuffer(bytes.length);
        String temp = null;
        for (int i = 0;i< bytes.length;i++){
            temp = Integer.toHexString(0xFF & bytes[i]);
            if (temp.length() <2){
                sb.append(0);
            }
            sb.append(temp);
        }
        return sb.toString();
    }
    
    public static void main(String[]args) {
    	String hashString1=MessageDigestUtil.getDigest("C:/Users/Aaron/Videos/video.mp4", "sha-256");
    	String hashString2=MessageDigestUtil.getDigest("C:/Users/Aaron/Videos/Encrypted_video.mp4", "sha-256");
    	System.out.println(hashString1);
    	System.out.println(hashString2);
    }
}
