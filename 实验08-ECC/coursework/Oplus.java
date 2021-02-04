package coursework;


public class Oplus {
    public static int p=2017;
    public static int a=3;
    public static int b=2;
    public static int[] oplus(int[]P,int []Q){
        int []R=new int[2];
        int lambda=((Q[1]-P[1])*(GetInverseElement(Q[0]-P[0])))%p;
        if(lambda<0)
            lambda=lambda+p;
        R[0]=(lambda*lambda-P[0]-Q[0])%p;
        R[1]=(lambda*(P[0]-R[0])-P[1])%p;
        if(R[0]<0)
            R[0]+=p;
        if(R[1]<0)
            R[1]+=p;
        return R;
    }
    public static int[] doublePlus(int []P){
        int []R=new int[2];
        int lambda=(((3*P[0]*P[0]+a)%p)*(GetInverseElement(2*P[1])%p))%p;
        if(lambda<0)
            lambda+=p;
        R[0]=(lambda*lambda-2*P[0])%p;
        if(R[0]<0)
            R[0]+=p;
        R[1]=(lambda*(P[0]-R[0])-P[1])%p;
        if(R[1]<0)
            R[1]+=p;
        return R;
    }
    public static int GetInverseElement(int E){ 
        if(E%p<0){
            E=E%p+p;
        }
        if(E%p==0)
            return 0;
        for(int i=1;i<p;i++){
            if((i*E)%p==1)
                return i;
        }
        return 0;
    }
    
    public static void main(String[]args){
        int[]P={136,113};
        int[]Q={1042,435};
        int []R=oplus(P, Q);
        System.out.println("("+R[0]+","+R[1]+")");
        P[0]=1424;
        P[1]=660;
        R=Otimes.otimes(P,1614);
        System.out.println("("+R[0]+","+R[1]+")");
    }
    
}
