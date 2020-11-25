package encryptTools.des.encrypt;

public class F {
	 public static byte[] function(byte[]input,byte[]subkey){
	        byte[] middleData=E.EWork(input, subkey);
	        middleData=S.SWork(middleData);
	        return P.PWork(middleData);
	 }
}
