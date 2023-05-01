package exam01;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import db.ConnectionProvider;

public class Test01 extends JFrame {
	JTable table;
	Vector<String> colNames;
	Vector<Vector<String>> rowData;
	JTextField jtf_name;
//	도서명, 출판사명, 구매가격, 구매일
	public Test01() {
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
				try {
					String name = jtf_name.getText();
					
					String sql = "select bookname,publisher,saleprice,orderdate "
							+ "from orders o, customer c, book b "
							+ "where o.bookid = b.bookid and "
							+ "c.custid = o.custid and "
							+ "name = ?";
					
					Connection conn = ConnectionProvider.getConnection();
					PreparedStatement pstmt = 
					conn.prepareStatement(sql);
					pstmt.setString(1, name);
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						String bookname = rs.getString(1);
						String publisher =rs.getString(2);
						int saleprice = rs.getInt(3);
						Date orderdate = rs.getDate(4);
						Vector<String> v = new Vector<String>();
						v.add(bookname);
						v.add(publisher);
						v.add(saleprice+"");
						v.add(orderdate+"");
						rowData.add(v);
					}
					table.updateUI();
					ConnectionProvider.close(rs, pstmt, conn);
				}catch (Exception ex) {
					System.out.println("예외발생:"+ex.getMessage());
				}
			}
		});
	}
	
	public static void main(String[] args) {
		new Test01();
	}

}




