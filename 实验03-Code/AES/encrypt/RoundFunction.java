package AES.encrypt;

import AES.SBoxes;

public class RoundFunction {
    public static byte XOR(byte input1,byte input2){
        return (byte)(input1^input2);
    }
    
    public static byte[][] ByteSub(byte[][] input){
        for(int i=0;i<input[0].length;i++){
            for(int j=0;j<input[i].length;j++){
                int low=(input[i][j])&(0b00001111);
                int high=((input[i][j])&(0b11110000)>>>4);
                input[i][j]=SBoxes.SBox[high][low];
            }
        }
        return input;
    }

    public static byte[][] ShiftRow(byte[][] input){
        byte[][]temp=new byte[input.length][input[0].length];
        System.arraycopy(input, 0, temp, 0, 4);
        
        input[1][0]=temp[1][1];input[1][1]=temp[1][2];input[1][2]=temp[1][3];input[1][3]=temp[1][0];
        input[2][0]=temp[2][2];input[2][1]=temp[2][3];input[2][2]=temp[2][0];input[2][3]=temp[2][1];
        input[3][0]=temp[3][3];input[3][1]=temp[3][0];input[3][2]=temp[3][1];input[3][3]=temp[3][2];
        
        return input;
    }

    public static byte[][] MixColumn(byte[][] input){

        return input;
    }
}
