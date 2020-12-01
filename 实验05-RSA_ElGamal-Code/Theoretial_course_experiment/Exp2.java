public class Exp2 {
    private static int Get_varphiN(int p,int q){
        return (p-1)*(q-1);
    }
    private static int Get_GCD(int a,int b){
        if(b==0)
            return a;
        int tmp=a%b;
        return Get_GCD(b, tmp);
    }
    private static boolean isPrimeNumber(int a){
        for(int i=2;i<a;i++){
            if(a%i==0)
                return false;
        }
        return true;
    }
    private static int[] IntegerFactorization(int a){
        int ans[]={-1,-1};
        for(int i=2;i<a;i++){
            if(isPrimeNumber(i)){
                if(a%i==0){
                    int j=a/i;
                    if(isPrimeNumber(j)){
                        ans[0]=i;
                        ans[1]=j;
                        return ans;
                    }
                }
            }
        }
        return ans;
    }
    
    private static int Get_d(int e,int varphiN){
        long tmp=0;
        for(int i=1;i<varphiN;i++){
            tmp=(long)((long)(i)*e);
            if(tmp%varphiN==1){
                return i;
            }
        }
        return -1;
    }

    private static int GetData(int num,int index,int module){
        long tmp=1;
        if(index==0){
            return 1;
        }else{
            for(int i=0;i<index;i++){
                tmp=(long)(tmp*num);
                tmp=(long)(tmp%module);
            }
        }
        return (int)tmp;
    }


    public static void main(String[]args){
        int N=2529629;
        int e=10861;
        int C=2346717;
        //System.out.printf("N=%d.%n",N);
        //long tmp=344041*11833;
        //System.out.printf("%d.%n",tmp);
        //int*int越界直接用(long)(int*int)强转可能出错,需要将一个至少乘数(强制)转换为long了再计算

        int ans[]=IntegerFactorization(N);
        int varphiN=Get_varphiN(ans[0], ans[1]);
        int d=Get_d(e, varphiN);
        int M=GetData(C, d, N);
        System.out.printf("p=%d,q=%d for N=%d. %n",ans[0],ans[1],N);
        System.out.printf("d=%d. %n",d);
        System.out.printf("M=%d. %n",M);

        System.out.printf("The PublicKey is <n=%d,e=%d>. %n",N,e);
        System.out.printf("The PrivateKey is <p=%d,q=%d,d=%d,vatphi(n)=%d>. %n",ans[0],ans[1],d,varphiN);
        System.out.printf("The Message is %d. %n",M);
        System.out.printf("The cipherText is %d. %n",C);

    }

}
