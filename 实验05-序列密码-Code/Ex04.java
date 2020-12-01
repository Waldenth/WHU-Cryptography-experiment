public class Ex04{
    static int []S ={1,0,0,0};
    public static void main(String[]args){
        System.out.println("The initial state is:");    
        PrintData(S);
        for(int i=0;i<16;i++){
            GetNext(S);
            PrintData(S);
        }
    }
    private static void PrintData(int[]S){
        for(int i=0;i<S.length-1;i++){
            System.out.print(S[i]+" ");
        }
        System.out.println("\t  "+S[S.length-1]);
    }
    private static void GetNext(int[]S){
        for(int i=0;i<S.length-1;i++){
            S[i]=S[i+1];
        }
        S[S.length-1]=S[0]^S[2]^1^S[1]&S[2];
    }
}