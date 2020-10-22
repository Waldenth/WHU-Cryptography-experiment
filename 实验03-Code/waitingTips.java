import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;

public class waitingTips{
    JFrame jf=new JFrame("请稍后");
    JLabel tip=new JLabel(" ",JLabel.CENTER);
    public  void InitEncryptShow(){
        jf.setSize(250,100);
        jf.setLayout(new BorderLayout());
        jf.setLocationRelativeTo(null);
        tip.setText("文件加密中....");
        tip.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jf.add(tip,BorderLayout.CENTER);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void show(){
        jf.setVisible(true);
    }

    public  void InitDecryptShow(){
        jf.setSize(250,100);
        jf.setLayout(new BorderLayout());
        jf.setLocationRelativeTo(null);
        tip.setText("文件解密中....");
        tip.setFont(new Font("微软雅黑",Font.PLAIN,15));
        jf.add(tip,BorderLayout.CENTER);
        tip.setBounds(50, 10, 150, 80);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public  void turnOff(){
        jf.dispose();
    }
}