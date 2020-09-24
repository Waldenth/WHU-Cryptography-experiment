import java.util.Scanner;

class Problem2_1{   //Caesar cipher
    StringBuffer inputText=new StringBuffer();
    Scanner sc=new Scanner(System.in);
    private int key;
    /**
     * 静态方法中，不能直接访问非静态成员(包括方法和变量)
     * 因为,非静态的变量是依赖于对象存在的，对象必须实例化之后，它的变量才会在内存中存在
    */
    public void Encrypt(){
        //重置
        inputText=new StringBuffer();
        sc=new Scanner(System.in);
        
        System.out.println("Please input Message that you want to encrypt");
        if(sc.hasNextLine()){
            inputText.append(sc.nextLine());
        }
        System.out.println("Please input the Message key");
        key=sc.nextInt();
        
        StringBuffer cipherText=new StringBuffer();

        for(int i=0;i<inputText.length();i++){
            if(inputText.charAt(i)==' '){
                cipherText.append(inputText.charAt(i));
            }else if(inputText.charAt(i)<='z'&&inputText.charAt(i)>='a'){
                
                cipherText.append((char)('a'+(inputText.charAt(i)-'a'+key)%26));
            
            }else if(inputText.charAt(i)<='Z'&&inputText.charAt(i)>='A'){

                cipherText.append((char)('A'+(inputText.charAt(i)-'A'+key)%26));

            }else{//输入了其他字符
                System.out.println("Illegal character!");
                System.exit(-1);//异常终止
            }
        }

        System.out.println("The cipher text is");
        System.out.println("\t"+cipherText);
    }

    public void Decrypt(){
        
        inputText=new StringBuffer();
        sc=new Scanner(System.in);

        System.out.println("Please input the cipher text that you want to encrypt");
        if(sc.hasNextLine()){
            inputText.append(sc.nextLine());
        }
        System.out.println("Please input the Message key");
        key=sc.nextInt();

        StringBuffer messageText=new StringBuffer();

        for(int i=0;i<inputText.length();i++){
            char tmp=inputText.charAt(i);
            if(tmp==' ')
                messageText.append(tmp);
            else if(tmp>='a'&&tmp<='z')
                messageText.append((char)('a'+(tmp-'a'-key+26)%26));
            else
                messageText.append((char)('A'+(tmp-'A'-key+26)%26));
        }

        System.out.println("The message is");
        System.out.println("\t"+messageText);
    }
}

public class Problem2 {
    public static void main(String[]args){
        Problem2_1 solution1=new Problem2_1();
        solution1.Encrypt();
        solution1.Decrypt();
    }
}

