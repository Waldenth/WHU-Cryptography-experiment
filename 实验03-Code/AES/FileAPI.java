package AES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import AES.decrypt.DECRYPT;
import AES.encrypt.ENCRYPT;


public class FileAPI {
    public static boolean EncryptFiles(String keydata,boolean isPath,String filePath,String...outputPaths){
        byte[]key=new byte[16];
        byte[]data;
        byte[]tmp=new byte[16];
        if(isPath){
            try{
                key=readFile(keydata);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            key=keydata.getBytes();
        }
        
        try{
            data=readFile(filePath);
            for(int i=0;i<data.length/16;i++){
                tmp=ENCRYPT.encryptData(Arrays.copyOfRange(data,i*16, i*16+16), key);
                System.arraycopy(tmp, 0, data, i*16, 16);
            }
            System.out.println("+-----------------------+");
            System.out.println("|    Encrypt success!   |");
            System.out.println("+-----------------------+");
            if(outputPaths.length!=0){
                writeFile(outputPaths[0], data);
            }else{
                writeFile("cipherText.txt", data);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean DecryptFiles(String keydata,boolean isPath,String filePath,String...outputPaths){
        byte[]key=new byte[16];
        byte[]data;
        byte[]tmp=new byte[16];
        if(isPath){
            try{
                key=readFile(keydata);
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            key=keydata.getBytes();
        }

        try{
            data=readFile(filePath);
            for(int i=0;i<data.length/16;i++){
                tmp=DECRYPT.decryptData(Arrays.copyOfRange(data,i*16,i*16+16 ), key);
                System.arraycopy(tmp, 0, data, i*16, 16);
            }
            System.out.println("+-----------------------+");
            System.out.println("|    Decrypt success!   |");
            System.out.println("+-----------------------+");
            if(outputPaths.length!=0){
                writeFile(outputPaths[0], data);
            }else{
                writeFile("decryptedText.txt", data);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }

    private static byte[] readFile(String filepath) throws IOException{
        File file=new File(filepath);
        FileInputStream input=new FileInputStream(file);
        byte[] getData=new byte[(int)file.length()];
        for(int i=0;i<getData.length;i++){
            getData[i]=(byte)(input).read();
        }
        input.close();
        return getData;
    } 
    private static boolean writeFile(String filepath,byte[] outputData)throws IOException{
        File file=new File(filepath);
        FileOutputStream output=new FileOutputStream(file);
        output.write(outputData);
        System.out.println("+--------------------------------+");
        System.out.println("|       Export file success!     |");  
        System.out.println("+--------------------------------+");
        System.out.println("\tYour output file path is "+filepath);
        output.close();
        return true;
    }
}
