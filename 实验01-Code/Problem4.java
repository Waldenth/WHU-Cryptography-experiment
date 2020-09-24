class Probelm4_1{
    StringBuffer cipherText=new StringBuffer("itrs zr trtzk");
    public void exhasutiveDecrypt(){
        int i,j;
        for(i=1;i<=26;i++){
            for(j=0;j<cipherText.length();j++){
                if(cipherText.charAt(j)==' ')
                    continue;
                else{
                    cipherText.setCharAt(j, (char)('a'+(cipherText.charAt(j)-'a'+25)%26)) ;
                }
            }
            System.out.println("the key is "+i+"\t"+cipherText);
        }
    }
}

public class Problem4{
    public static void main(String[]args){
        Probelm4_1 solution1=new Probelm4_1();
        solution1.exhasutiveDecrypt();
    }
}