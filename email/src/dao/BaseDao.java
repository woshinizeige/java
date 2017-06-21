package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstm=null;
	ResultSet rs=null;
	
	/**
	 * 连接数据库
	 * @return con 数据库的连接
	 */
	public Connection con(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "zei", "admin");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 关闭连接
	 */
	public void close(Connection con,Statement stmt,PreparedStatement pstm,ResultSet rs){
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstm!=null){
					pstm.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * 增、删、改
	 */
	public int operate(String sql,Object[] o){
		con=con();
		int result=0;
		try {
			pstm=con.prepareStatement(sql);
			if(o!=null){
				for(int i=0;i<o.length;i++){
					pstm.setObject(i+1, o[i]);
				}
			result=pstm.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
