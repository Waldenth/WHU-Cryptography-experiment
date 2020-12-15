package encryptFileGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import encryptTools.FileProcessingAPI;
import hash.MessageDigestUtil;
import jdbc.DatabaseManager;

//import encryptFileGUI.GUItools.Encrypt;
//import encryptTools.aes.FileAPI;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;

public class GUIFrame extends JFrame {

	public String filePath="null";
	public String fileName="null";
	public int ENorDEorNochoose=0; //加密1解密2, 0为未选择
    public  volatile boolean iscompleted=false;
    public  volatile boolean caninformUser=false;
    public String HandleType="null";
    
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField password;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField pathTips;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIFrame frame = new GUIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public GUIFrame() {
		setTitle("文件加密器");
		getContentPane().setLayout(null);
		setSize(780,530);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 780, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("文件加密器");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 30));
		
		JLabel choosePlanLabel = new JLabel("选择加密算法");
		choosePlanLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		JRadioButton AES128button = new JRadioButton("AES-128");
		buttonGroup.add(AES128button);
		AES128button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JRadioButton AES256button = new JRadioButton("AES-256");
		buttonGroup.add(AES256button);
		AES256button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JRadioButton DESbutton = new JRadioButton("DES");
		buttonGroup.add(DESbutton);
		DESbutton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JRadioButton RC4button = new JRadioButton("RC4");
		buttonGroup.add(RC4button);
		RC4button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JLabel inputPassTipLabel = new JLabel("输入密码");
		inputPassTipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		password = new JPasswordField();
		password.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		
		pathTips = new JTextField();
		pathTips.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		pathTips.setColumns(10);
		
		JButton chooseFileButton = new JButton("选择文件");
		chooseFileButton.setFocusPainted(false);
		chooseFileButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		chooseFileButton.addActionListener(new ActionListener() {
			@Override
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
		

		
		JLabel chooseENorDE_Label = new JLabel("选择加解密");
		chooseENorDE_Label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		JRadioButton ENFileButton = new JRadioButton("加密");
		buttonGroup_1.add(ENFileButton);
		ENFileButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JRadioButton DEFileButton = new JRadioButton("解密");
		buttonGroup_1.add(DEFileButton);
		DEFileButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton startButton = new JButton("开   始");
		startButton.setFocusPainted(false);
		startButton.setFont(new Font("微软雅黑", Font.BOLD, 16));
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				 String str=event.getActionCommand();
	                if(str.equals("开   始")){
	                    if(ENFileButton.isSelected())
	                        ENorDEorNochoose=1;
	                    else if(DEFileButton.isSelected())
	                        ENorDEorNochoose=2;
	                    if(AES128button.isSelected())
	                    	HandleType="AES-128";
	                    else if(AES256button.isSelected())
	                    	HandleType="AES-256";
	                    else if(RC4button.isSelected())
	                    	HandleType="RC4";
	                    else if(DESbutton.isSelected())
	                    	HandleType="DES";
	                    boolean isNormal=true;
	                    if(ENorDEorNochoose==0){
	                        isNormal=false;
	                        JOptionPane.showMessageDialog(null, "未选择处理类型", "错误", JOptionPane.ERROR_MESSAGE);
	                    }
	                    if(isNormal){
	                    	 if(HandleType.equals("null")) {
	 	                    	JOptionPane.showMessageDialog(null, "未选择加密类型\n将使用默认的AES-128处理","警告",JOptionPane.WARNING_MESSAGE);
	 	                    }
	                    	String key=new String(password.getPassword());
	                        //System.out.println(key);
	                        if(key.length()!=16){
	                        	if(HandleType.equals("DES")) {
	                        		if(key.getBytes().length>8) {
		                        		isNormal=false;
		                            	JOptionPane.showMessageDialog(null, "由于DES算法本身限制\n您不能使用超过8字节的密码", "错误",JOptionPane.ERROR_MESSAGE);
	                        		}
	                            }
	                        	else if(key.length()<5){
	                                isNormal=false;
	                                JOptionPane.showMessageDialog(null, "密码过短", "错误",JOptionPane.ERROR_MESSAGE);
	                            }
	                            else if(key.length()>16){
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
	                                	
	                                    if(HandleType.equals("DES")) {
	                                    	JOptionPane.showMessageDialog(null, "DES算法已经不够安全,且速度较慢\n只适合加密简单文件","警告",JOptionPane.WARNING_MESSAGE);
	                                    }
	                                    
	                                	if(ENorDEorNochoose==1){
	                                        TipEn waitTip=new TipEn();
	                                        Encrypt en=new Encrypt(key, filePath, outputPath,HandleType);
	                                        waitTip.setPriority(Thread.MAX_PRIORITY);
	                                        waitTip.start();
	                                        en.start();
	                                    }else{
	                                        TipDe waitTip=new TipDe();
	                                        Decrypt de=new Decrypt(key, filePath, outputPath,HandleType);                                        
	                                        waitTip.setPriority(Thread.MAX_PRIORITY);
	                                        waitTip.start();
	                                        de.start();
	                                    } 

	                                    Timer t=new Timer();
	                                    t.schedule(new TimerTask(){
	                                        public void run(){
	                                            if(caninformUser){
	                                            	if(ENorDEorNochoose==1) { //这是加密操作
	                                            		String originalFileHash=MessageDigestUtil.getDigest(filePath, "sha-256");
	                                            		String encryptFileHash=MessageDigestUtil.getDigest(filePath.substring(0,filePath.length()-fileName.length())+"Encrypted_"+fileName, "sha-256");
	                                            		String sqlString="insert into  hash_check(originalHash,encryptHash) "
	                                            				+ "values ('"+originalFileHash
	                                            				+"','"
	                                            				+encryptFileHash+"')"
	                                            				+ " on duplicate key update "
	                                            				+ " originalHash='"+originalFileHash
	                                            				+ "',encryptHash='"+encryptFileHash+"'";
	                                            		DatabaseManager.connect();
	                                            		DatabaseManager.execute(sqlString);
	                                            		DatabaseManager.close();
	                                            		JOptionPane.showMessageDialog(null, "文件加密成功\n数据库已入档!", "提示", JOptionPane.INFORMATION_MESSAGE); 
	                                            	}else if(ENorDEorNochoose==2) { //这是解密操作
	                                            		String encryptFileHash=MessageDigestUtil.getDigest(filePath, "sha-256");
	                                            		String curDecryptFileHash=MessageDigestUtil.getDigest(filePath.substring(0,filePath.length()-fileName.length())+"Decrypted_"+fileName, "sha-256");
	                                            		String sqlString="SELECT originalHash FROM hash_check WHERE encryptHash="+"'"+encryptFileHash +"'";   
	                                            		DatabaseManager.connect();
	                                            		ResultSet rs=DatabaseManager.query(sqlString);
	                                            		if(rs!=null) {
	                                            			String originalFileHash=null;
	                                            			try {
																while(rs.next()) {
																	originalFileHash=rs.getString("originalHash");
																}
															} catch (SQLException e) {
																// TODO 自动生成的 catch 块
																e.printStackTrace();
															}
	                                            			JOptionPane.showMessageDialog(null, "文件已解密!\n"+"原有文件Hash为 "+originalFileHash+"\n"
	                                            					+"解密文件Hash为 "+curDecryptFileHash, "提示", JOptionPane.INFORMATION_MESSAGE);  
	                                            		}else {
	                                            			JOptionPane.showMessageDialog(null, "查找原文件哈希失败\n无法保证解密文件准确性", "错误", JOptionPane.ERROR_MESSAGE);
	                                            		}
	                                            		DatabaseManager.close();
	                                            	}
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
		
		JLabel FilePathTip_Label = new JLabel("确认文件路径");
		FilePathTip_Label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(188)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(208, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(DESbutton, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(RC4button, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
								.addComponent(password, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
								.addComponent(inputPassTipLabel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(AES128button, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(AES256button, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
						.addComponent(choosePlanLabel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(chooseENorDE_Label, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
										.addGap(91))
									.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(ENFileButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addGap(49)
										.addComponent(DEFileButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addGap(61))))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(102)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addComponent(pathTips, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
										.addGap(50))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(FilePathTip_Label, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(187)
							.addComponent(chooseFileButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(278)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(295, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(choosePlanLabel)
						.addComponent(FilePathTip_Label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(AES128button)
						.addComponent(AES256button)
						.addComponent(pathTips, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(RC4button)
						.addComponent(DESbutton)
						.addComponent(chooseFileButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(inputPassTipLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(chooseENorDE_Label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(DEFileButton, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(ENFileButton))
					.addGap(51)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addGap(50))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	class TipEn extends Thread{
        public void run(){
            WaitingTips tip=new WaitingTips();
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
            WaitingTips tip=new WaitingTips();
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
        String key;String filePath;String outputPath;String type;
        public Encrypt(String k,String f,String o,String type){
            key=k;
            filePath=f;
            outputPath=o;
            this.type=type;
        }
        public void run(){
            //FileAPI.EncryptFiles(key, false, filePath, outputPath);
            FileProcessingAPI.EncryptFile(key, filePath, outputPath, type);
        	iscompleted=true;
            //System.out.println("-----------"+iscompleted+"-------------"); 
        }
    }

    class Decrypt extends Thread{
        String key;String filePath;String outputPath;String type;
        public Decrypt(String k,String f,String o,String type){
            key=k;
            filePath=f;
            outputPath=o;
            this.type=type;
        }
        public void run(){
            //FileAPI.DecryptFiles(key, false, filePath, outputPath);
            FileProcessingAPI.DecryptFile(key, filePath, outputPath, type);
        	iscompleted=true;
        }
    }
}
