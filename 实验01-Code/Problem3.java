import java.util.Scanner;

/** 代数密码-Vernam密码 */
class VermanCipher{
    StringBuffer inputText;
    private StringBuffer key;
    Scanner sc;
    public void Encrypt(){
        inputText=new StringBuffer();
        key=new StringBuffer();
        sc=new Scanner(System.in);
        System.out.println("Please input Message that you want to encrypt");
        if(sc.hasNextLine()){
            inputText.append(sc.nextLine());
        }
        System.out.println("Please input the Message key");
        if(sc.hasNextLine()){
            key.append(sc.nextLine());
        }
        if(key.length()!=inputText.length()){//密钥明文不等长,报错
            System.out.println("Error,String of Message and key are different");
            System.exit(-1);
        }
        StringBuffer cipherText=new StringBuffer();
        char curKey,curMes;
        for(int i=0;i<key.length();i++){
            curKey=key.charAt(i);
            curMes=inputText.charAt(i);

            cipherText.append((char)(curKey^curMes));
        }
        System.out.println("The cipher data is");
        for(int i=0;i<cipherText.length();i++){
            System.out.print((int)cipherText.charAt(i)+" ");
        }
        System.out.println("");
    }
    public void Decrypt(){
        inputText=new StringBuffer();
        key=new StringBuffer();
        sc=new Scanner(System.in);
        //int tmp;
        
        /**
         * 获取输入,按空格划分先转换成Int数字,再转换成char数字存储
        */
        System.out.println("Please input the cipher text that you want to encrypt");
        String str="";
        if(sc.hasNextLine()){
            str=sc.nextLine();
        }
        String[]tmpstr=str.split(" ");
        for(int i=0;i<tmpstr.length;i++){
            inputText.append( (char)Integer.parseInt(tmpstr[i]) );
        }
        /**
         * 获取密钥
        */
        System.out.println("Please input Message key");
        if(sc.hasNextLine()){
            key.append(sc.nextLine());
        }

        /**
         * 异或解密
        */
        if(key.length()!=inputText.length()){//长度不一致,报错            
            System.out.println("Error,String of Message and key are different");
            System.exit(-1);
        }
        StringBuffer message=new StringBuffer();
        char curKey,curCipher;
        for(int i=0;i<key.length();i++){
            curCipher=inputText.charAt(i);
            curKey=key.charAt(i);

            message.append((char)(curCipher^curKey));
        }

        System.out.println("The message text is");
        System.out.println("\t"+message);
    }
}



public class Problem3 {
    public static void main(String[]args){               

        VermanCipher solution=new VermanCipher();
        solution.Encrypt();
        solution.Decrypt();
    }
}
