package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setAlwaysOnTop(true);
		setForeground(SystemColor.inactiveCaptionBorder);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 835, 546);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("操作");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("已上架");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sj();
			}
		});
		menu.add(menuItem);
		
		JMenuItem menuItem_2 = new JMenuItem("新货");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xh();
			}
		});
		menu.add(menuItem_2);
		
		JMenu menu_1 = new JMenu("系统");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("退出");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		menu_1.add(menuItem_1);
		mainPane = new JPanel();
		mainPane.setBackground(SystemColor.control);
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		/*JButton btn_prev = new JButton("上一页");
		btn_prev.setBackground(SystemColor.textHighlight);
		btn_prev.setBounds(15, 431, 123, 29);
		mainPane.add(btn_prev);
		
		JButton btn_next = new JButton("下一页");
		btn_next.setBackground(SystemColor.textHighlight);
		btn_next.setBounds(154, 431, 123, 29);
		mainPane.add(btn_next);*/
		
		JLabel label = new JLabel("日期");
		label.setBounds(15, 25, 44, 21);
		mainPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(57, 22, 123, 27);
		mainPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("名称");
		label_1.setBounds(202, 25, 44, 21);
		mainPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setText(LoginState.baseid);;
		textField_1.setColumns(10);
		textField_1.setBounds(250, 22, 123, 27);
		mainPane.add(textField_1);
		
		 Object[][] tableData =
			  {
			    new Object[]{"1111","10001" ,"苹果", 1.8,100,20 , "2018-02-01","下架"},
			  };
			  //定义一维数据作为列标题
		 Object[] columnTitle = {"识别码" , "货号" , "名称","单价（元/kg）","总量","余量","日期","操作"};
		table = new JTable(tableData , columnTitle);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(1300, 600));// 表格的显示尺寸
		table.setBackground(Color.WHITE);
		table.setRowHeight(25);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setEnabled(false);
		JScrollPane scrollPane=new JScrollPane(table); 
		scrollPane.setLocation(30, 70);
		scrollPane.setSize(1300, 600);
		scrollPane.setViewportBorder(null);
		mainPane.add(scrollPane);
		
		JButton button = new JButton("搜索");
		button.setBounds(416, 22, 93, 27);
		mainPane.add(button);
		
		JButton button_1 = new JButton("下架");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(541, 22, 93, 27);
		mainPane.add(button_1);
	}
	
	public void closeFrame(){
		this.dispose();
	}
	
	public void sj(){
		this.dispose();
		JFrame main = new MainFrame2();
		if(!main.isActive()){
			main.setVisible(true);
		}
	}
	public void xh(){
		this.dispose();
		JFrame main2 = new MainFrame2();
		if(!main2.isActive()){
			main2.setVisible(true);
		}
	}
}
