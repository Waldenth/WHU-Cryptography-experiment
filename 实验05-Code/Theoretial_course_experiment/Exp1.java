import java.util.HashSet;

public class Exp1 {
    private static int Get_N(int p,int q){
        return p*q;
    }
    private static int Get_varphiN(int p,int q){
        return (p-1)*(q-1);
    }
    private static int Get_GCD(int a,int b){
        if(b==0)
            return a;
        int tmp=a%b;
        return Get_GCD(b, tmp);
    }
    private static int Get_e(int varphiN){
        HashSet<Integer> choosed=new HashSet<Integer>();
        int e=0;
        while(true){
            e=(int)(1+Math.random()*100000);
            if(choosed.contains(e))
                continue;
            else{
                choosed.add(e);
                if(Get_GCD(e, varphiN)==1){
                    break;
                }
            }
        }
        return e;
    }

    private static int Get_e(int varphiN,int d){
        for(int i=2;i<varphiN;i++){
            if(Get_GCD(i, varphiN)==1){
                long tmp=(long)((long)(i)*d);
                if(tmp%varphiN==1)
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
        int P=1831;
        int Q=1607;
        int d=1582283;
        int M=676155;

        int N=Get_N(P, Q);
        int varphiN=Get_varphiN(P, Q);
        int e=Get_e(varphiN,d);
        int C=GetData(M, e, N);

        // long 防止溢出
        //long MM=(long)M*M;
        //System.out.printf("%d%n",MM);

        System.out.printf("The PublicKey is <n=%d,e=%d>. %n",N,e);
        System.out.printf("The PrivateKey is <p=%d,q=%d,d=%d,vatphi(n)=%d>. %n",P,Q,d,varphiN);
        System.out.printf("The Message is %d. %n",M);
        System.out.printf("The cipherText is %d. %n",C);
    }
}
/**
 *  The PublicKey is <n=2942417,e=65869>.
    The PrivateKey is <p=1831,q=1607,d=1582283,vatphi(n)=2938980>.
    The cipherText is 1564938.
*/