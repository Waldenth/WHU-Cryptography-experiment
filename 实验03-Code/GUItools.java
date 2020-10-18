import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Font;

public class GUItools {
    /** 窗体*/
    private JFrame jf;
    /** 面板*/
    private JPanel jp1,jp2,jp3;
    /** 单选框*/
    private JRadioButton jrb1,jrb2;
    private ButtonGroup EnDEchoose;
    /** 按钮*/
    private JButton chooseFile;
    private JButton start;
    /** 密码框*/
    private JPasswordField password;
    /** 标签*/
    private JLabel title;
    private JLabel passwordTips;
    private JLabel chooseEnDeTips;
    
    /**
     * 重写构造方法
    */
    public GUItools(){
        jf=new JFrame("AES-128");
        jf.setLayout(null);
        jp1=new JPanel();jp2=new JPanel();jp3=new JPanel();
        jrb1=new JRadioButton("加密");
        jrb2=new JRadioButton("解密");
        EnDEchoose=new ButtonGroup();
        chooseFile=new JButton("选择文件");
        chooseFile.setFocusPainted(false);
        start=new JButton("开始");
        start.setFocusPainted(false);
        password=new JPasswordField(18);
        title=new JLabel("AES-128加解密工具");
        passwordTips=new JLabel("输入密码(16位)");
        chooseEnDeTips=new JLabel("选择类型");
        initGUI();
    }

    /** 
     * 绘制界面
    */
    public void initGUI(){
        jf.setSize(500, 350);
        jf.add(jp1);
        jf.add(jp2);
        jf.add(jp3);
        jp1.setBounds(0,0,500,80);
        jp2.setBounds(0,80,500,180);
        jp3.setBounds(0,260,500,90);
       

        
        jp1.setLayout(null);
        jp1.add(title);
        title.setBounds(130,10,250,60);

        jp2.setLayout(null);
        jp2.add(passwordTips);
        jp2.add(password);
        jp2.add(chooseFile);
        jp2.add(jrb1);jp2.add(jrb2);
        jp2.add(chooseEnDeTips);
        passwordTips.setBounds(40,10,150,30);
        password.setBounds(40,50,150,20);
        chooseFile.setBounds(40,100,100,40);
        EnDEchoose.add(jrb1);
        EnDEchoose.add(jrb2);
        jp2.add(jrb1);
        jp2.add(jrb2);
        jrb1.setBounds(330,60,60,25);
        jrb2.setBounds(330,85,60,25);
        chooseEnDeTips.setBounds(330,10,60,30);

        jp3.setLayout(null);
        jp3.add(start);
        start.setBounds(150,0,200,30);
        
        
    }

    private void setFontAndColor(){
        Font TitleF=new Font("微软雅黑",Font.BOLD,25);
        title.setFont(TitleF);
        Font PasswordTipF=new Font("微软雅黑",Font.PLAIN,15);
        passwordTips.setFont(PasswordTipF);
        Font ChooseFileF=new Font("微软雅黑",Font.PLAIN,12);
        chooseFile.setFont(ChooseFileF);
        chooseEnDeTips.setFont(ChooseFileF);
    }



    /**
     * 启动
    */
    public void StartGUI() {
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFontAndColor();
    }

    public static void main(String[] args) {
        GUItools test = new GUItools();
        test.StartGUI();
    }
}
