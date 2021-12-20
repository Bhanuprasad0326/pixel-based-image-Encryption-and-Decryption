package com;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JScrollPane;
public class ViewDetection extends JFrame{
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jsp;
public ViewDetection(String title){
	super(title);
	dtm = new DefaultTableModel(){
		public boolean isCellEditable(int r,int c){
			return false;
		}
	};
	table = new JTable(dtm);
	table.setRowHeight(30);
	table.setFont(new Font("Courier New",Font.BOLD,13));
	jsp = new JScrollPane(table);
	dtm.addColumn("Test File Record");
	dtm.addColumn("Detection Result");
	table.getColumnModel().getColumn(0).setPreferredWidth(700);
	table.getColumnModel().getColumn(1).setPreferredWidth(100);
	getContentPane().add(jsp);
}
}
