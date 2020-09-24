import java.util.Scanner;

class Problem1_1{
    StringBuffer inputText=new StringBuffer("This is a example");
    StringBuffer cipherText=new StringBuffer();
    Scanner sc=new Scanner(System.in);
    public void Decrypt(){
        System.out.println("Please input Message:");
        if(sc.hasNextLine()){
            inputText.replace(0,inputText.length(),sc.nextLine());
        }
        inputText.reverse();
       
        System.out.println(inputText);

        System.out.println("Please input the stable length of ciphertext");
        int stableLength=sc.nextInt();

        char currentChar;
        int position=0;
        int effectiveLength=0;
        while(position<inputText.length()){
            currentChar=inputText.charAt(position);
            if(currentChar != ' '){
                cipherText.append(currentChar);
                effectiveLength++;
            }
            if(effectiveLength!=0&&effectiveLength%stableLength==0)
                cipherText.insert(cipherText.length(),' ');
            position++;
        }
        System.out.println("The ciphertext is:");
        System.out.println("\t"+cipherText);
    }
}

public class Problem1 {
    public static void main(String[]args){
        Problem1_1 solution1=new Problem1_1();
        solution1.Decrypt();
    }
}

