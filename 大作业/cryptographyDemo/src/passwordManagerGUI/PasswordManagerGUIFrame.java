package passwordManagerGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class PasswordManagerGUIFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordManagerGUIFrame frame = new PasswordManagerGUIFrame();
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
	public PasswordManagerGUIFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("密码管理");
		setSize(605, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("密码管理器");
		titleLabel.setBounds(227, 28, 127, 35);
		titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
		contentPane.add(titleLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(173, 94, 164, 28);
		contentPane.add(passwordField);
		
		JButton queryButton = new JButton("查询");
		queryButton.setBounds(449, 94, 97, 29);
		queryButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		contentPane.add(queryButton);
		
		JLabel passwordTipLabel = new JLabel("请输入主密钥");
		passwordTipLabel.setBounds(73, 96, 86, 19);
		passwordTipLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		contentPane.add(passwordTipLabel);
		
		
		JButton updateButton = new JButton("更新");
		updateButton.setBounds(247, 505, 97, 28);
		updateButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		contentPane.add(updateButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(92, 175, 433, 305);
		contentPane.add(scrollPane);
		
		Object [][]tableDataObjects= {{"null","null"}};
		String []tableTitle= {"密码描述","密码"};
		
		table = new JTable(tableDataObjects,tableTitle);
		scrollPane.setViewportView(table);
		
		
	}
}
