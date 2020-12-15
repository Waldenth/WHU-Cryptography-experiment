package coursework;

public class Otimes {
    public static int[] otimes(int[]P,int times){
        int []ans=new int[2];
        ans[0]=P[0];
        ans[1]=P[1];
        if(times==0)
            return ans;
        for(int i=1;i<times;i++){
            if(ans[0]==P[0]&&ans[1]==P[1]){
                ans=Oplus.doublePlus(P);
            }else{
                ans=Oplus.oplus(P, ans);
            }
        }

        return ans;
    }
}
