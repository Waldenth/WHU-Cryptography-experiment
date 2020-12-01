public class Ex03{

    static int []S={0,0,1,0,1};
    
    public static void main(String[]args){
        System.out.println("The initial state is:");    
        PrintData(S);
        for(int i=0;i<16;i++){
            GetNext(S);
            PrintData(S);
        }
    }
    private static void PrintData(int[]S){
        for(int i=0;i<4;i++){
            System.out.print(S[i]+" ");
        }
        System.out.println("\t  "+S[4]);
    }
    private static void GetNext(int[]S){
        for(int i=0;i<4;i++){
            S[i]=S[i+1];
        }
        S[4]=((S[0]^S[1])^S[2])^S[3];
    }
}