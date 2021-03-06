import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import AES.FileAPI;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

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
    /** 路径文本框*/
    public JTextField pathTips;

    public String filePath="null";
    public String fileName="null";
    public int ENorDEorNochoose=0;

    public  volatile boolean iscompleted=false;
    public  volatile boolean caninformUser=false;

    /**
     * 重写构造方法
    */
    public GUItools(){
        jf=new JFrame("AES-128");
        jf.setLayout(null);
        jp1=new JPanel();jp2=new JPanel();jp3=new JPanel();
        jrb1=new JRadioButton("加密");  //1
        jrb2=new JRadioButton("解密");  //2
        EnDEchoose=new ButtonGroup();
        chooseFile=new JButton("选择文件");
        chooseFile.setFocusPainted(false);
        start=new JButton("开始");
        start.setFocusPainted(false);
        password=new JPasswordField(18);
        title=new JLabel("AES-128加解密工具");
        passwordTips=new JLabel("输入密码(16位)");
        chooseEnDeTips=new JLabel("选择类型");
        pathTips=new JTextField();

        initGUI();
        work();
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
        jp2.add(pathTips);
        passwordTips.setBounds(40,10,150,30);
        password.setBounds(40,50,150,20);
        chooseFile.setBounds(40,100,100,40);
        pathTips.setBounds(160,110,140,20);
        pathTips.setVisible(true);
        pathTips.setText("null");
        pathTips.setEditable(true);

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
        start.setFont(ChooseFileF);
    }

    /**
     * 监听
    */
    public void work(){
        chooseFile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String str=event.getActionCommand();
                if(str.equals("选择文件")){
                    JFileChooser jfc=new JFileChooser();
                    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
                    jfc.showDialog(new JLabel(), "选择");
                    File file=jfc.getSelectedFile();
                    if(file.isFile()){
                        filePath=file.getAbsolutePath();
                        fileName=jfc.getSelectedFile().getName();
                    } 
                    filePath=filePath.replace("\\", "/");
                    //System.out.println(filePath); 
                    pathTips.setText(filePath);
                }
            }
        });
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                String str=event.getActionCommand();
                if(str.equals("开始")){
                    if(jrb1.isSelected())
                        ENorDEorNochoose=1;
                    else if(jrb2.isSelected())
                        ENorDEorNochoose=2;
                    boolean isNormal=true;
                    if(ENorDEorNochoose==0){
                        isNormal=false;
                        JOptionPane.showMessageDialog(null, "未选择处理类型", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                    if(isNormal){
                        String key=new String(password.getPassword());
                        //System.out.println(key);
                        if(key.length()!=16){
                            if(key.length()<5){
                                isNormal=false;
                                JOptionPane.showMessageDialog(null, "密码过短", "错误",JOptionPane.ERROR_MESSAGE);
                            }else if(key.length()>16){
                                isNormal=false;
                                JOptionPane.showMessageDialog(null, "密码过长", "错误",JOptionPane.ERROR_MESSAGE);
                            }else{
                                int needAddlength=16-key.length();
                                if(needAddlength<=key.length()){
                                    key=key.concat(key.substring(0,needAddlength));
                                }else{
                                    String tmpKey=new String(password.getPassword());
                                    int needAddStrNum=needAddlength/tmpKey.length();
                                    for(int i=0;i<needAddStrNum;i++){
                                        key=key.concat(tmpKey);
                                    }
                                    needAddlength=16-key.length();
                                    key=key.concat(key.substring( 0,needAddlength));
                                }
                            }
                            
                        }
                        if(isNormal){
                            if(filePath.equals("null")){
                                isNormal=false;
                                JOptionPane.showMessageDialog(null, "文件未指定", "错误",JOptionPane.ERROR_MESSAGE);
                            }
                            if(isNormal){
                                String outputPath="null";
                                if(ENorDEorNochoose==1)
                                    outputPath=filePath.substring(0,filePath.length()-fileName.length())+"Encrypted_"+fileName;
                                else if(ENorDEorNochoose==2)
                                    outputPath=filePath.substring(0,filePath.length()-fileName.length())+"Decrypted_"+fileName;
                                else{
                                    JOptionPane.showMessageDialog(null, "未选择处理类型", "错误", JOptionPane.ERROR_MESSAGE);
                                    isNormal=false;
                                }
                                if(isNormal){
                                    if(ENorDEorNochoose==1){
                                        TipEn waitTip=new TipEn();
                                        Encrypt en=new Encrypt(key, filePath, outputPath);
                                        en.start();
                                        waitTip.start();
                                    }else{
                                        TipDe waitTip=new TipDe();
                                        Decrypt de=new Decrypt(key, filePath, outputPath);                                        
                                        de.start();
                                        waitTip.start();
                                    } 

                                    Timer t=new Timer();
                                    t.schedule(new TimerTask(){
                                        public void run(){
                                            if(caninformUser){
                                                JOptionPane.showMessageDialog(null, "文件处理成功", "提示", JOptionPane.INFORMATION_MESSAGE); 
                                                t.cancel();
                                            }
                                        }
                                    },0,500);
                                    caninformUser=false;
                                    iscompleted=false;
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    class TipEn extends Thread{
        public void run(){
            waitingTips tip=new waitingTips();
            tip.InitEncryptShow();
            tip.show();
            while(true){
                if(iscompleted)
                    break;
            }
            tip.turnOff();  
            caninformUser=true;
            System.out.println("Now can inform user");
        }
    }

    class TipDe extends Thread{
        public void run(){
            waitingTips tip=new waitingTips();
            tip.InitDecryptShow();
            tip.show();
            while(true){
                if(iscompleted)
                    break;
            }
            tip.turnOff();
            caninformUser=true;
            System.out.println("Now can inform user");
        }
    }


    class Encrypt extends Thread{
        String key;String filePath;String outputPath;
        public Encrypt(String k,String f,String o){
            key=k;
            filePath=f;
            outputPath=o;
        }
        public void run(){
            FileAPI.EncryptFiles(key, false, filePath, outputPath);
            iscompleted=true;
            //System.out.println("-----------"+iscompleted+"-------------"); 
        }
    }

    class Decrypt extends Thread{
        String key;String filePath;String outputPath;
        public Decrypt(String k,String f,String o){
            key=k;
            filePath=f;
            outputPath=o;
        }
        public void run(){
            FileAPI.DecryptFiles(key, false, filePath, outputPath);
            iscompleted=true;
        }
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