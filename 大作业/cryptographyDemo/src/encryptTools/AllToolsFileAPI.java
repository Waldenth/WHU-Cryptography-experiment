package encryptTools;

public class AllToolsFileAPI {
	protected static int progressNow=0;
    protected static int fileLength=0;
    public static volatile boolean alreadyread=false;
    
    public static boolean EncryptFile(String key,String filePath,String outputPath,String type){
    	progressNow=0;
    	if(type.equals("DES")) {
    		
    		
    		
    		
    	}else if(type.equals("RC4")) {
    		
    		
    		
    		
    	}else if(type.equals("AES-256")) {
    		
    		
    		
    		
    	}else {//AES-128
    		
    		
    		
    	}
    	
    	
    	
    	return true;
    }
}
