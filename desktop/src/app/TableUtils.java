package app;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableUtils {

	public static void clearTable(JTable table){
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		tableModel.setRowCount(0);// 清除原有行
	}
	
	public static void deleteRow(JTable table,int rowIndex){
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.removeRow(rowIndex);// rowIndex是要删除的行序号
	}
	
	public static void addRow(JTable table,Object[] rowData){
		DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
		tableModel.addRow(rowData);
	}
	
	public static int[] getSelectedRows(JTable table){
		int[] selRowIndexs = table.getSelectedRows();// 用户所选行的序列
		return selRowIndexs;
	}
}
