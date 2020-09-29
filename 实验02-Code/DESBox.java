import java.util.Arrays;

public class DESBox {
    int SBox_3[][]={
        {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},
        {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},
        {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},
        {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12},
    };
    String input1="101100";
    String input2="101101";
    
    int []numInput1=new int[input1.length()];
    int []numInput2=new int[input2.length()];

    public void GetAnswer(){
        for(int i=0;i<input1.length();i++){
            numInput1[i]=input1.charAt(i)-'0';
            numInput2[i]=input2.charAt(i)-'0';
        }
        int[]inputDifference=GetDifference(numInput1, numInput2);
        System.out.println("Input Difference is "+Arrays.toString(inputDifference));

        System.out.println("Ouput No.1 is " +GetOutput(numInput1));
        System.out.println("Ouput No.2 is " +GetOutput(numInput2));

        System.out.println("Output Difference is "+Integer.toBinaryString((Integer.parseInt("0"+GetOutput(numInput1),2))^(Integer.parseInt("0"+GetOutput(numInput2),2))));
    }
    public int[] GetDifference(int[]a,int []b){
        if(a.length!=b.length){
            System.out.println("Error-GetDifference(),length is different");
            System.exit(-1);
        }
        int []res=new int[a.length];
        for(int i=0;i<a.length;i++){
            res[i]=a[i]^b[i];
        }
        return res;
    }
    public String GetOutput(int []a){
        String res="";
        if(a.length!=6){
            System.out.println("Error-Input Length");
            System.exit(-1);
        }
        int row,column;
        row=Integer.parseInt("0"+a[0]+a[5], 2);
        column=Integer.parseInt("0"+a[1]+a[2]+a[3]+a[4], 2);
        res=Integer.toBinaryString(SBox_3[row][column]);        
        return res;
    }


    public static void main(String[]args){
        DESBox test=new DESBox();
        test.GetAnswer();
    }

}
