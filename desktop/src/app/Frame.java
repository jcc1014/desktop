package app;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
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
	public Frame() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setTitle("登录");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((ScreenSize.getScreenWidth()-450)/2, (ScreenSize.getScreenHeight()-300)/2, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_username = new JLabel("用户名");
		label_username.setFont(new Font("宋体", Font.PLAIN, 20));
		label_username.setBounds(87, 58, 75, 30);
		contentPane.add(label_username);
		
		JLabel label_password = new JLabel("密  码");
		label_password.setFont(new Font("宋体", Font.PLAIN, 20));
		label_password.setBounds(87, 119, 75, 30);
		contentPane.add(label_password);
		
		username = new JTextField();
		username.setFont(new Font("宋体", Font.PLAIN, 20));
		username.setBounds(172, 58, 161, 30);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton login = new JButton("登录");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = "http://localhost:8080/trace_system/baseInfo/syncLogin.do";
				String name =  username.getText();
				String pwd = new String(password.getPassword());
				List<NameValuePair> formparams = new ArrayList<NameValuePair>();  
			    formparams.add(new BasicNameValuePair("username", name));  
			    formparams.add(new BasicNameValuePair("password", pwd));
				String rs = HttpUtils.pushMessage(formparams, url);
				JSONObject js = JSONObject.parseObject(rs);
				if(null != js&&"success".equals(js.get("code").toString())){
					LoginState.username = name;
					LoginState.userid = js.getString("userid");
					LoginState.baseid = js.getString("baseid");
					LoginState.baseName = js.getString("baseName");
					LoginState.password = pwd;
					//跳转
					closeFrame();
				}else{
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					dialog.setSize(150, 150);
					dialog.setLocationRelativeTo(contentPane);
					JLabel label = new JLabel();
					label.setText(js.get("msg").toString());
					dialog.getContentPane().add(label);
					dialog.setVisible(true);
				}
			}
		});
		
		password = new JPasswordField();
		password.setBounds(172, 119, 161, 30);
		contentPane.add(password);
		login.setBackground(new Color(102, 204, 153));
		login.setFont(new Font("宋体", Font.PLAIN, 20));
		login.setBounds(82, 200, 93, 30);
		contentPane.add(login);
		
		JButton clear = new JButton("清空");
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
		clear.setFont(new Font("宋体", Font.PLAIN, 20));
		clear.setBackground(Color.LIGHT_GRAY);
		clear.setBounds(240, 200, 93, 30);
		contentPane.add(clear);
	}
	
	public void closeFrame(){
		this.dispose();
		JFrame main = new MainFrame();
		if(!main.isActive()){
			main.setVisible(true);
		}
	}
}
