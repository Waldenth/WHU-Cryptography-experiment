package AES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import AES.decrypt.DECRYPT;
import AES.encrypt.ENCRYPT;


public class FileAPI {
    protected static int progressNow=0;
    protected static int fileLength=0;
    public static volatile boolean alreadyread=false;
    public static boolean EncryptFiles(String keydata,boolean isPath,String filePath,String...outputPaths){
        progressNow=0;
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
            System.out.println("file has already been read in memory");
            fileLength=data.length/16;
            alreadyread=true;
            for(progressNow=0;progressNow<data.length/16;progressNow++){
                tmp=ENCRYPT.encryptData(Arrays.copyOfRange(data,progressNow*16, progressNow*16+16), key);
                System.arraycopy(tmp, 0, data, progressNow*16, 16);
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
        alreadyread=false;
        return true;
    }

    public static boolean DecryptFiles(String keydata,boolean isPath,String filePath,String...outputPaths){
        progressNow=0;
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
            System.out.println("file has already been read in memory");
            fileLength=data.length/16;
            alreadyread=true;
            for(progressNow=0;progressNow<data.length/16;progressNow++){
                tmp=DECRYPT.decryptData(Arrays.copyOfRange(data,progressNow*16,progressNow*16+16 ), key);
                System.arraycopy(tmp, 0, data, progressNow*16, 16);
            }
            //progressNow=0;
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
        alreadyread=false;
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
    public static int GetprogressNow(){
        return progressNow;
    }
    public static int GetfileLength(){
        return fileLength;
    }
}
