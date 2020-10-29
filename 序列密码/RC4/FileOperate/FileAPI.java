package RC4.FileOperate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import RC4.Init;
import RC4.KeyStreamGeneration;

public class FileAPI {
    private static byte[] readFile(String filepath) throws IOException{
        File file=new File(filepath);
        FileInputStream input=new FileInputStream(file);
        byte[]getData=new byte[(int)file.length()];
        input.read(getData);
        input.close();
        return getData;
    }
    private static boolean writeFile(String filepath,byte[]outputData) throws IOException {
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
    public static boolean HandleFile(boolean isEn,String keydata,boolean isPath,String filepath,String...outputPaths){
        byte keyseed[]=new byte[16];//密钥字节数组
        byte data[];//待处理文件字符数组
        if(isPath){
            try{
                keyseed=readFile(keydata);
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            keyseed=keydata.getBytes();
        }

        try{
            data=readFile(filepath);
            System.out.println("file has already been read in memory");
            Init.InitStable(keyseed);
            byte [] key=KeyStreamGeneration.GenerationKey(data.length);
            for(int i=0;i<data.length;i++){
                data[i]=(byte)(data[i]^key[i]);
            }
            if(isEn){
                System.out.println("+-----------------------+");
                System.out.println("|    Encrypt success!   |");
                System.out.println("+-----------------------+");
            }else{
                System.out.println("+-----------------------+");
                System.out.println("|    Decrypt success!   |");
                System.out.println("+-----------------------+");
            }

            if(outputPaths.length!=0){
                writeFile(outputPaths[0], data);
            }else{
                if(isEn)
                    writeFile("cipherText.txt", data);
                else
                    writeFile("decryptedText.txt", data);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
}
