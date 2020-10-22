import AES.FileAPI;
import AES.TestAES;

public class test {
    public static void main(String[] args) {
        waitingTips tip=new waitingTips(); 
        tip.InitEncryptShow();
        tip.show();
        FileAPI.EncryptFiles("key.txt", true, "Histoie.jpg", "CipherHistoie.jpg");
        FileAPI.DecryptFiles("key.txt", true, "CipherHistoie.jpg", "DecryptedHistoie.jpg");
        tip.turnOff();
    }
}
