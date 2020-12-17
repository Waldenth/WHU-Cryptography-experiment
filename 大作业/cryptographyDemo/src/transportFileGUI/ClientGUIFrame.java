package transportFileGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ClientGUIFrame extends JFrame {

	private JPanel contentPane;
	private JTextField IPField;
	private JTextField filePathField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUIFrame frame = new ClientGUIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientGUIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(590, 430);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Client");
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		titleLabel.setBounds(244, 27, 72, 42);
		contentPane.add(titleLabel);
		
		IPField = new JTextField();
		IPField.setBounds(50, 147, 180, 29);
		contentPane.add(IPField);
		IPField.setColumns(10);
		
		JLabel IPTipLabel = new JLabel("目标IP:端口号");
		IPTipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		IPTipLabel.setBounds(53, 112, 116, 25);
		contentPane.add(IPTipLabel);
		
		JLabel filePathTipLabel = new JLabel("发送文件路径");
		filePathTipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		filePathTipLabel.setBounds(318, 112, 116, 25);
		contentPane.add(filePathTipLabel);
		
		filePathField = new JTextField();
		filePathField.setColumns(10);
		filePathField.setBounds(318, 147, 229, 29);
		contentPane.add(filePathField);
		
		JLabel passwordTipLabel = new JLabel("输入密码");
		passwordTipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		passwordTipLabel.setBounds(53, 208, 116, 25);
		contentPane.add(passwordTipLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(50, 243, 180, 21);
		contentPane.add(passwordField);
		
		JButton startButton = new JButton("start");
		startButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		startButton.setBounds(232, 320, 97, 34);
		contentPane.add(startButton);
		
		JButton chooseFileButton = new JButton("选择文件");
		chooseFileButton.setBounds(450, 186, 97, 23);
		contentPane.add(chooseFileButton);
	}
}
