package transportFileGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class ServerGUIFrame extends JFrame {

	private JPanel contentPane;
	private JTextField portIDField;
	private JTextField filePathField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUIFrame frame = new ServerGUIFrame();
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
	public ServerGUIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Server");
		setSize(590, 430);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Server");
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
		titleLabel.setBounds(244, 27, 72, 42);
		contentPane.add(titleLabel);
		
		portIDField = new JTextField();
		portIDField.setBounds(50, 147, 119, 29);
		contentPane.add(portIDField);
		portIDField.setColumns(10);
		
		JLabel portIDTipLabel = new JLabel("监听端口号");
		portIDTipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		portIDTipLabel.setBounds(53, 112, 116, 25);
		contentPane.add(portIDTipLabel);
		
		JLabel filePathTipLabel = new JLabel("接收文件路径");
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
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				JOptionPane.showMessageDialog(null, "文件成功接收", "提示", JOptionPane.INFORMATION_MESSAGE); 
			}
		});
	}
}
