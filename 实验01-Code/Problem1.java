import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/** 简单置换密码 */
class Problem1_1{
    StringBuffer inputText=new StringBuffer("This is a example");
    StringBuffer cipherText=new StringBuffer();
    Scanner sc=new Scanner(System.in);
    public void Decrypt(){  //加密与解密可共用此段代码
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

/**  矩阵置换密码 */ 
class Problem1_2{
    StringBuffer inputText;
    Scanner sc;
    /** 加密算法 */
    public void Encrypt(){
        inputText=new StringBuffer();
        sc=new Scanner(System.in);
        String key="";
        StringBuffer cipherText=new StringBuffer();
        int i=0,j=0;
        System.out.println("Please input Messgae:");
        if(sc.hasNextLine()){
            inputText.append(sc.nextLine());
        }
        System.out.println("Please input the matrix column:");
        if(sc.hasNextLine()){
            j=Integer.parseInt(sc.nextLine());
        }

        int index=0;//剔除空格
        while(true){
            int spaceLocation = inputText.indexOf(" ",index);
            if(spaceLocation == -1)
				break;
			inputText.replace(spaceLocation, spaceLocation+1, "");
			index = spaceLocation+1;
        }
        //System.out.println(inputText);

        i=(inputText.length())/j;
        if(inputText.length()%j!=0)
            i=i+1;
        //System.out.println("i="+i);
        String[] cipherTextMatrix=new String[i];
        for(int p=0;p<i-1;p++){
            cipherTextMatrix[p]=inputText.substring(p*j, p*j+j);
        }
        //System.out.println((i-1)*j+"length="+inputText.length());
        cipherTextMatrix[i-1]=inputText.substring((i-1)*j);
        if(cipherTextMatrix[i-1].length()!=j){
            int tmplength=j-cipherTextMatrix[i-1].length();
            for(int p=0;p<tmplength;p++){
                cipherTextMatrix[i-1]=cipherTextMatrix[i-1].concat("?");
            }
        }
        /* 获取密钥串 */
        System.out.println("Please input key:");
        if(sc.hasNextLine()){
            key=sc.nextLine();
        }
        key=key.replaceAll(" ", "");
        key=DuplicateRemoval(key);
        //System.out.println(key);


        /* 用于比对密钥获取密文顺序 */
        char[] keySort=key.toCharArray();
        Arrays.sort(keySort);
        for(int p=0;p<keySort.length;p++){
            int k=key.indexOf(keySort[p]);
            //System.out.println(k);
            for(int q=0;q<i;q++){
                cipherText.append(cipherTextMatrix[q].charAt(k));
            }
            cipherText.append(" ");
        }
        
        System.out.println("The cipher data is:");
        System.out.println("\t"+cipherText);
    }

    /** 解密算法 */
    public void Decrypt(){
        inputText=new StringBuffer();
        sc=new Scanner(System.in);
        System.out.println("Please input cipherText:");
        if(sc.hasNextLine()){
            inputText.append(sc.nextLine());
        }
        String[]cipherText=inputText.toString().split(" ");
        String key="";
        System.out.println("Please input key:");
        if(sc.hasNextLine()){
            key=sc.nextLine();
        }
        key=key.replaceAll(" ", "");
        key=DuplicateRemoval(key);
        //System.out.println(key);

        /* 用于比对密钥获取密文顺序 */
        char[] keySort=key.toCharArray();
        Arrays.sort(keySort);
        /* 明文 */
        StringBuffer message=new StringBuffer();
        for(int i=0;i<cipherText[0].length();i++){
            for(int j=0;j<key.length();j++){
                int k=Arrays.binarySearch(keySort, key.charAt(j));
                //System.out.println("k="+k);
                message.append(cipherText[k].charAt(i));
            }
            message.append(" ");
        }
        System.out.println("The Message is:");
        System.out.println("\t"+message);

    }

    /** 密钥去重 */
    public String DuplicateRemoval(String input){
        HashSet<Character> newhash = new HashSet<>();
        StringBuffer res=new StringBuffer();
        int i = 0;
        while(i<input.length()) {
            char c = input.charAt(i);
            if(newhash.add(c)){
                res.append(c);
            }
            i++;
        }
        return res.toString();
    }
}

public class Problem1 {
    public static void main(String[]args){
        //Problem1_1 solution1=new Problem1_1();
        //solution1.Decrypt();
        Problem1_2 solution2=new Problem1_2();
        solution2.Decrypt();
    }
}

