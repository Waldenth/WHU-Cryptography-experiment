public class Ex05 {
    static int []S ={1,1,0,0};
    public static void main(String[]args){
        for(int k=0;k<8;k++){
            switch(k){
                case 0: S[0]=0;S[1]=0;S[2]=0;break;
                case 1: S[0]=0;S[1]=0;S[2]=1;break;
                case 2: S[0]=0;S[1]=1;S[2]=0;break;
                case 3: S[0]=0;S[1]=1;S[2]=1;break;
                case 4: S[0]=1;S[1]=0;S[2]=0;break;
                case 5: S[0]=1;S[1]=0;S[2]=1;break;
                case 6: S[0]=1;S[1]=1;S[2]=0;break;
                case 7: S[0]=1;S[1]=1;S[2]=1;break;
            }


            S[3]=1^S[0]^S[1]^S[2]^(S[0]&S[1])^(S[1]&S[2])^(S[2]&S[0]);
            System.out.println("The initial state is:");    
            PrintData(S);
            for(int i=0;i<16;i++){
                GetNext(S);
                PrintData(S);
            }
            System.out.println("+-----------------------+");
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
        S[S.length-1]=1^S[0]^S[1]^S[2]^(S[0]&S[1])^(S[1]&S[2])^(S[2]&S[0]);
    }
}
