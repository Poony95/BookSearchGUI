package exam01;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.OrdersDAO;
import db.ConnectionProvider;
import vo.CustomerOrderSearch;

public class Test02 extends JFrame {
	JTable table;
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JTextField jtf_name;
//	도서명, 출판사명, 구매가격, 구매일
	public Test02() {
		jtf_name = new JTextField(10);
		colNames = new Vector<String>();
		colNames.add("도서명");
		colNames.add("출판사명");
		colNames.add("구매가격");
		colNames.add("구매일");
		rowData = new Vector<Vector<String>>();
		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);
		
		JPanel p = new JPanel();
		p.add(new JLabel("고객이름"));
		p.add(jtf_name);
		JButton btn_search = new JButton("조회");
		p.add(btn_search);
		
		add(p, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		setSize(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				String name = jtf_name.getText();
				OrdersDAO dao = new OrdersDAO();
				ArrayList<CustomerOrderSearch> list= 
					dao.findOrdersByName(name);
				for(CustomerOrderSearch cos:list) {
					Vector<String> v = new Vector<String>();
					v.add(cos.getBookname());
					v.add(cos.getPublisher());
					v.add(cos.getSaleprice()+"");
					v.add(cos.getOrderdate()+"");
					rowData.add(v);
				}
				table.updateUI();
			}
		});
	}
	
	public static void main(String[] args) {
		new Test02();
	}

}




