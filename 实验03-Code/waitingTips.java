import javax.swing.JFrame;
import javax.swing.JLabel;

public class waitingTips{
    JFrame jf=new JFrame();
    JLabel tip=new JLabel("请稍后...");
    public  void InitEncryptShow(){
        jf.setTitle("正在加密中");
        jf.setSize(250,100);
        jf.setLocationRelativeTo(null);
        jf.add(tip);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void show(){
        jf.setVisible(true);
    }

    public  void InitDecryptShow(){
        jf.setTitle("正在解密中");
        jf.setSize(250,100);
        jf.setLocationRelativeTo(null);
        jf.add(tip);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public  void turnOff(){
        jf.dispose();
    }
}