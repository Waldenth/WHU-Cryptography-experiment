package encryptTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;



public class FileProcessingAPI {
	protected static volatile int progressNow=0;
    protected static int fileLength=0;
    public static volatile boolean alreadyread=false;
    
    public static boolean EncryptFile(String keyStr,String filePath,String outputPath,String type){
    	progressNow=0;
    	if(type.equals("DES")) {
    		progressNow=0;
    		byte[]key=keyStr.getBytes();
    		byte[]bitKey=new byte[64];
    		// GUI限制了最多8字节密码,不会溢出
    		//System.out.println(keyStr);
    		//System.out.println("key length = "+key.length);
    		for(int i=0;i<key.length;i++) {
    			String tmpString=Integer.toBinaryString(key[i]);
    			if(tmpString.length()<8){
                    int curLength=tmpString.length();
                    for(int j=0;j<8-curLength;j++)
                        tmpString="0"+tmpString;
                }else if(tmpString.length()>8){
                    tmpString=tmpString.substring(tmpString.length()-8);
                }
    			for(int k=0;k<tmpString.length();k++) {
    				bitKey[8*i+k]=(byte)(tmpString.charAt(k)-'0');
    			}
    		}
    		for(int curLength=8*key.length;curLength<64;curLength++) {
    			bitKey[curLength]=bitKey[(curLength)%(8*key.length)];
    		}//密码64比特转换结束
    		
    		byte[]data=readFile(filePath, false);
    		fileLength=data.length/64; //DES一个文件长度64bit
    		int fileByteLength=data.length/8;
    		int fileBitLength=data.length;
    		
    		System.out.println("BitLength="+fileBitLength);
    		
    		alreadyread=true;
    		
    		byte[]outputData=new byte[fileByteLength];
    		for(progressNow=0;progressNow<fileLength;progressNow++) {
    			byte[]tmpTextBit=encryptTools.des.encrypt.DECRYPT_des.decryptData(Arrays.copyOfRange(data,progressNow*64,progressNow*64+64), bitKey);
    			//System.arraycopy(tmpText,0,data, progressNow*64, 64);
    			byte[]tmpTextByte=ConvertBitToByte(tmpTextBit);
    			System.arraycopy(tmpTextByte, 0, outputData, progressNow*8, 8);
    		}
    		byte[]remain=new byte[fileBitLength-fileLength*64];
    		
    		System.arraycopy(data, fileLength*64, remain, 0, fileBitLength-fileLength*64);
    		
    		
    		byte[]remainBytes=ConvertBitToByte(remain);
    		
    		System.arraycopy(remainBytes, 0, outputData,fileLength*8 , fileByteLength-fileLength*8);
    		
    		
    		writeFile(outputPath, outputData, true);
    		alreadyread=false;
    		
    	}else if(type.equals("RC4")) {
    		progressNow=0;
    		byte[]keySeed=keyStr.getBytes();
    		
    		byte[]data=readFile(filePath, true);
    		fileLength=data.length;
    		alreadyread=true;
    		
    		encryptTools.rc4.Init.InitStable(keySeed);
    		byte[]key=encryptTools.rc4.KeyStreamGeneration.GenerationKey(data.length);
    		for(progressNow=0;progressNow<data.length;progressNow++) {
    			data[progressNow]=(byte)(data[progressNow]^key[progressNow]);
    		}
    		writeFile(outputPath, data, true);
    		alreadyread=false;
    		
    	}else if(type.equals("AES-256")) {
    		// NeedFix
    		// 暂未实现,现用AES-128代替
    		progressNow=0;
            byte[]key=keyStr.getBytes();
            
            byte[]data=readFile(filePath, true);
            fileLength=data.length/16;
            alreadyread=true;
            
            byte[]tmp=new byte[16];
            for(progressNow=0;progressNow<data.length/16;progressNow++){
                tmp=encryptTools.aes.encrypt.ENCRYPT.encryptData(Arrays.copyOfRange(data,progressNow*16, progressNow*16+16), key);
                System.arraycopy(tmp, 0, data, progressNow*16, 16);
            }
            writeFile(outputPath, data, true);
            alreadyread=false;

    	}else {//AES-128
    		progressNow=0;
            byte[]key=keyStr.getBytes();
            
            byte[]data=readFile(filePath, true);
            fileLength=data.length/16;
            alreadyread=true;
            
            byte[]tmp=new byte[16];
            for(progressNow=0;progressNow<data.length/16;progressNow++){
                tmp=encryptTools.aes.encrypt.ENCRYPT.encryptData(Arrays.copyOfRange(data,progressNow*16, progressNow*16+16), key);
                System.arraycopy(tmp, 0, data, progressNow*16, 16);
            }
            writeFile(outputPath, data, true);
            alreadyread=false;
    	}
    	return true;
    }
    public static boolean DecryptFile(String keyStr,String filePath,String outputPath,String type) {
    	progressNow=0;
    	if(type.equals("DES")) {
    		progressNow=0;
    		byte[]key=keyStr.getBytes();
    		byte[]bitKey=new byte[64];
    		// GUI限制了最多8字节密码,不会溢出
    		//System.out.println(keyStr);
    		//System.out.println("key length = "+key.length);
    		for(int i=0;i<key.length;i++) {
    			String tmpString=Integer.toBinaryString(key[i]);
    			if(tmpString.length()<8){
                    int curLength=tmpString.length();
                    for(int j=0;j<8-curLength;j++)
                        tmpString="0"+tmpString;
                }else if(tmpString.length()>8){
                    tmpString=tmpString.substring(tmpString.length()-8);
                }
    			for(int k=0;k<tmpString.length();k++) {
    				bitKey[8*i+k]=(byte)(tmpString.charAt(k)-'0');
    			}
    		}
    		for(int curLength=8*key.length;curLength<64;curLength++) {
    			bitKey[curLength]=bitKey[(curLength)%(8*key.length)];
    		}//密码64比特转换结束
    		
    		byte[]data=readFile(filePath, false);
    		fileLength=data.length/64; //DES一个文件长度64bit
    		int fileByteLength=data.length/8;
    		int fileBitLength=data.length;
    		
    		System.out.println("BitLength="+fileBitLength);
    		
    		alreadyread=true;
    		
    		byte[]outputData=new byte[fileByteLength];
    		for(progressNow=0;progressNow<fileLength;progressNow++) {
    			byte[]tmpTextBit=encryptTools.des.encrypt.DECRYPT_des.decryptData(Arrays.copyOfRange(data,progressNow*64,progressNow*64+64), bitKey);
    			//System.arraycopy(tmpText,0,data, progressNow*64, 64);
    			byte[]tmpTextByte=ConvertBitToByte(tmpTextBit);
    			System.arraycopy(tmpTextByte, 0, outputData, progressNow*8, 8);
    		}
    		byte[]remain=new byte[fileBitLength-fileLength*64];
    		
    		System.arraycopy(data, fileLength*64, remain, 0, fileBitLength-fileLength*64);
    		
    		
    		byte[]remainBytes=ConvertBitToByte(remain);
    		
    		System.arraycopy(remainBytes, 0, outputData,fileLength*8 , fileByteLength-fileLength*8);
    		
    		
    		writeFile(outputPath, outputData, true);
    		alreadyread=false;

    	}else if(type.equals("RC4")) {
    		progressNow=0;
    		byte[]keySeed=keyStr.getBytes();
    		
    		byte[]data=readFile(filePath, true);
    		fileLength=data.length;
    		alreadyread=true;
    		
    		encryptTools.rc4.Init.InitStable(keySeed);
    		byte[]key=encryptTools.rc4.KeyStreamGeneration.GenerationKey(data.length);
    		for(progressNow=0;progressNow<data.length;progressNow++) {
    			data[progressNow]=(byte)(data[progressNow]^key[progressNow]);
    		}
    		writeFile(outputPath, data, true);
    		alreadyread=false;
    	}else if(type.equals("AES-256")) {
    		// NeedFix
    		// 暂未实现,现用AES-128代替
    		progressNow=0;
            byte[]key=keyStr.getBytes();
            
            byte[]data=readFile(filePath, true);
            fileLength=data.length/16;
            alreadyread=true;
            
            byte[]tmp=new byte[16];
            for(progressNow=0;progressNow<data.length/16;progressNow++){
                tmp=encryptTools.aes.encrypt.ENCRYPT.encryptData(Arrays.copyOfRange(data,progressNow*16, progressNow*16+16), key);
                System.arraycopy(tmp, 0, data, progressNow*16, 16);
            }
            writeFile(outputPath, data, true);
            alreadyread=false;
    		    		
    	}else { //AES-128
    		progressNow=0;
            byte[]key=keyStr.getBytes();
            
            byte[]data=readFile(filePath, true);
            fileLength=data.length/16;
            alreadyread=true;
            
            byte[]tmp=new byte[16];
            
            
            for(progressNow=0;progressNow<data.length/16;progressNow++){
                tmp=encryptTools.aes.decrypt.DECRYPT.decryptData(Arrays.copyOfRange(data,progressNow*16, progressNow*16+16), key);
                System.arraycopy(tmp, 0, data, progressNow*16, 16);
            }
            writeFile(outputPath, data, true);
            alreadyread=false;
    	}
    	return true;
    }
    
    public static byte[] readFile(String filePath,boolean isNormal) {
    	File file=new File(filePath);
    	try( FileInputStream input=new FileInputStream(file)){
    		if(isNormal) { //正常字节读法
    			byte[] getData=new byte[(int)file.length()];
    			input.read(getData);
    			input.close();
    			System.out.println("file has already been read in memory");
    		    return getData;
    		}else { 	// DES奇葩bit读法
    			byte[]getData=new byte[(int)file.length()];
    			byte[]BitgetData=new byte[(int)(getData.length*8)];
    			input.read(getData);
    			input.close();
    			for(int i=0;i<getData.length;i++) {
    				String tmp=Integer.toBinaryString(getData[i]); // 生成的字符串二进制可能不是8位
    				if(tmp.length()<8){
    	                int curLength=tmp.length();
    	                for(int j=0;j<8-curLength;j++)
    	                    tmp="0"+tmp;
    	            }else if(tmp.length()>8){
    	                tmp=tmp.substring(tmp.length()-8);
    	            }
    				for(int j=0;j<8;j++) {
    					BitgetData[i+j]=(byte)(tmp.charAt(j)-'0');
    				}
    			}
    			System.out.println("file has already been read in memory");
    			return BitgetData;
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		System.out.println("error");
    		System.exit(-1);
		}
    	byte[] error=null;
    	return error;
    } 
    public static boolean writeFile(String filePath,byte[]outputData,boolean isNormal) {
    	File file=new File(filePath);
    	try(FileOutputStream output=new FileOutputStream(file)){
    		if(isNormal) {
    			output.write(outputData);
    		}else { //好像可以不用
    			byte[]outputBytes=ConvertBitToByte(outputData);
    			output.write(outputBytes);
    		}
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		return false;
    	}
    	return true;
    }
    private static byte[] ConvertBitToByte(byte[] input){
        byte[] bts=new byte[input.length/8];
        StringBuffer tmp;
        int k=0;
        for(int i=0;i<input.length;i+=8){
            tmp=new StringBuffer();
            for(int j=0;j<8;j++){
                char c=(char)('0'+(int)input[i+j]);
                tmp.append(c);
            }
            //System.out.println(tmp.toString());
            bts[k]=(byte)Integer.parseInt(tmp.toString(),2);
            k++;
        }
        return bts;
    }
    
    public static int GetprogressNow(){
        return progressNow;
    }
    public static int GetfileLength(){
        return fileLength;
    }
    
    
}
