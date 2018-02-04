package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Window.Type;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	private JTextField textField_1;
	private JTable table;
	private int rowI = -1;

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
		setType(Type.UTILITY);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("蔬菜管理系统");
		setForeground(SystemColor.inactiveCaptionBorder);
		setFont(new Font("Dialog", Font.PLAIN, 16));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 24));
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
		
		JLabel label = new JLabel("名称");
		label.setFont(new Font("宋体", Font.PLAIN, 24));
		label.setBounds(30, 25, 60, 35);
		mainPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 24));
		textField_1.setBounds(119, 25, 263, 35);
		mainPane.add(textField_1);
		
		 Object[][] tableData ={
			    /*new Object[]{"1111","10001" ,"苹果", 1.8,100,20 , "2018-02-01","下架"},*/
			  };
		 //定义一维数据作为列标题
		 Object[] columnTitle = {"编号" , "货号" , "名称","单价（元/kg）","总量","余量","日期","操作"};
		 table = new JTable(new DefaultTableModel(tableData , columnTitle));
		 OperationUtils.addTableData(table, "localhost", "已上架", "1", "");
		 table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowI = table.rowAtPoint(e.getPoint());
				table.addRowSelectionInterval(rowI,rowI);
				table.setSelectionBackground(Color.GRAY);
			}

		});
		table.setFont(new Font("宋体", Font.PLAIN, 24));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFillsViewportHeight(true);
		table.setPreferredScrollableViewportSize(new Dimension(1300, 600));// 表格的显示尺寸
		table.setBackground(Color.WHITE);
		table.setRowHeight(35);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setEnabled(false);
		//设置表头
		JTableHeader header=table.getTableHeader();
        header.setFont(new Font("宋体",Font.PLAIN,32));             //字体
        header.setPreferredSize(new Dimension(header.getWidth(),36));
        //滚动面板
		JScrollPane scrollPane=new JScrollPane(table); 
		scrollPane.setLocation(30, 70);
		//设置高度
		int w = ScreenSize.getScreenWidth()-60;
		int h = ScreenSize.getScreenHeight()-220;
		scrollPane.setSize(w, h);
		scrollPane.setViewportBorder(null);
		mainPane.add(scrollPane);
		
		JButton button = new JButton("搜索");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
				tableModel.setRowCount(0);
				OperationUtils.addTableData(table, "localhost", "已上架", "1", textField_1.getText());
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 24));
		button.setBounds(430, 24, 93, 37);
		mainPane.add(button);
		
		JButton button_1 = new JButton("清空");
		button_1.setFont(new Font("宋体", Font.PLAIN, 24));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setSelectionBackground(Color.WHITE);
				table.clearSelection();
			}
		});
		button_1.setBounds(570, 24, 93, 37);
		mainPane.add(button_1);
		
		JButton button_2 = new JButton("下架");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selRowIndexs = table.getSelectedRows();// 用户所选行的序列
				if(selRowIndexs.length>0){
					for (int i = 0; i < selRowIndexs.length; i++) {
						System.out.println(selRowIndexs[i]);
					}
				}else{
					JDialog dialog = new JDialog();
					dialog.setAlwaysOnTop(true);
					dialog.setSize(300, 200);
					dialog.setLocationRelativeTo(mainPane);
					JLabel label = new JLabel();
					label.setText("请至少选择一行再操作!");
					label.setFont(new Font("宋体",Font.PLAIN,24)); ;
					dialog.getContentPane().add(label);
					dialog.setVisible(true);
				}
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 24));
		button_2.setBounds(707, 24, 93, 37);
		mainPane.add(button_2);
		
		JButton button_3 = new JButton("刷新");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
				tableModel.setRowCount(0);
				OperationUtils.addTableData(table, "localhost", "已上架", "1", "");
			}
		});
		button_3.setFont(new Font("宋体", Font.PLAIN, 24));
		button_3.setBounds(840, 24, 93, 37);
		mainPane.add(button_3);
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
