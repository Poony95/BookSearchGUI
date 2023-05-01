package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import db.ConnectionProvider;
import vo.CustomerOrderSearch;

public class OrdersDAO {
	//고객이름을 매개변수로 전달받아 해당 고객이 주문한 도서명,출판사,주문가격,구매일
	//목록을 반환하는 메소드를 정의
	public ArrayList<CustomerOrderSearch> findOrdersByName(String name){
		ArrayList<CustomerOrderSearch> list=
				new ArrayList<CustomerOrderSearch>();
		String sql = "select bookname,publisher,saleprice,orderdate "
				+ "from orders o, customer c, book b "
				+ "where o.bookid = b.bookid and "
				+ "c.custid = o.custid and "
				+ "name = ?";
		try {
			Connection conn
			= ConnectionProvider.getConnection();
			PreparedStatement pstmt 
			= conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String bookname = rs.getString(1);
				String publisher = rs.getString(2);
				int saleprice = rs.getInt(3);
				Date orderdate = rs.getDate(4);
				CustomerOrderSearch cos = 
					new CustomerOrderSearch(bookname, publisher, 
							saleprice, orderdate);
				list.add(cos);
			}
			ConnectionProvider.close(rs, pstmt, conn);
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}		
		return list;
	}
	
}






