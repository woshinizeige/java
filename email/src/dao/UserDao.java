package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Receive;
import entity.Send;
import entity.Users;

public class UserDao extends BaseDao {

	/**
	 * 登录邮箱
	 * 
	 * @param name
	 * @param password
	 * @return 是否从数据库查到用户名
	 */
	public boolean login(String name, String password) {
		con = con();
		String sql = "select * from users where username=? and password=?";
		boolean is = false;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			if (rs.next()) {
				is = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stmt, pstm, rs);
		}
		return is;
	}

	/**
	 * 注册查询用户名
	 * 
	 * @param name
	 * @return 是否存在用户名
	 */
	public boolean selectname(String name) {
		con = con();
		String sql = "select * from users where username=?";
		boolean is = false;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			if (rs.next()) {
				is = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stmt, pstm, rs);
		}
		return is;
	}

	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return result
	 */
	public int register(String username, String password, String email) {
		String sql = "insert into users values(?,?,?)";
		Object[] o = { username, password, email };
		int result = operate(sql, o);
		return result;
	}

	/**
	 * 查询接收邮件
	 * 
	 * @param name
	 * @return 接收邮件列表
	 */
	public List<Receive> emailList(String name, int pageIndex, int pageCount) {
		List<Receive> list = new ArrayList<Receive>();
		String sql = "select b.* from(select a.*,rownum r,to_char(e_createdate,'YYYY-MM-DD HH24:Mi:ss') time from (select e.* from receive e where e_toname=? order by e_createdate desc) a)b where r between ? and ?";
		con = con();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setInt(2, (pageIndex - 1) * pageCount + 1);
			pstm.setInt(3, pageIndex * pageCount);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Receive r = new Receive();
				r.setId(rs.getInt(1));
				r.setFromname(rs.getString(2));
				r.setTitle(rs.getString(3));
				r.setContent(rs.getString(4));
				r.setState(rs.getInt(5));
				r.setToname(rs.getString(6));
				r.setCreatedate(rs.getString("time"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stmt, pstm, rs);
		}
		return list;
	}

	/**
	 * 查询已发邮件
	 * 
	 * @param name
	 * @return 已发列表
	 */
	public List<Send> emailSend(String name, int pageIndex, int pageCount) {
		List<Send> list = new ArrayList<Send>();
		String sql = "select b.* from(select a.*,rownum r,to_char(e_createdate,'YYYY-MM-DD HH24:Mi:ss') time from (select s.* from send s where e_fromname=? order by e_createdate desc) a)b where r between ? and ?";
		con = con();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setInt(2, (pageIndex - 1) * pageCount + 1);
			pstm.setInt(3, pageIndex * pageCount);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Send s = new Send();
				s.setId(rs.getInt(1));
				s.setFromname(rs.getString(2));
				s.setTitle(rs.getString(3));
				s.setContent(rs.getString(4));
				s.setState(rs.getInt(5));
				s.setToname(rs.getString(6));
				s.setCreatedate(rs.getString("time"));
				list.add(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stmt, pstm, rs);
		}
		return list;
	}
	
	/**
	 * 查询接收邮件总条数
	 * @return 总条数
	 */
	public int selectCountR(String name){
		int countA=0;
		con=con();
		try {
			pstm=con.prepareStatement("select count(*) from receive where e_toname=?");
			pstm.setString(1, name);
			rs=pstm.executeQuery();
			if(rs.next()){
				countA=rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close(con, stmt, pstm, rs);
		}
		return countA;
	}
	
	/**
	 * 查询已发邮件总条数
	 * @return 总条数
	 */
	public int selectCountS(String name){
		int countA=0;
		con=con();
		try {
			pstm=con.prepareStatement("select count(*) from send where e_fromname=?");
			pstm.setString(1, name);
			rs=pstm.executeQuery();
			if(rs.next()){
				countA=rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close(con, stmt, pstm, rs);
		}
		return countA;
	}
	
	/**
	 * 按ID查询接收邮件
	 * @param i
	 * @return 接收的邮件
	 */
	public Receive emailR(int id){
		con=con();
		Receive r=new Receive();
		String sql="select r.*,to_char(e_createdate,'YYYY-MM-DD HH24:Mi:ss') time from receive r where e_id=?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs=pstm.executeQuery();
			if(rs.next()){
				r.setTitle(rs.getString("e_title"));
				r.setFromname(rs.getString("e_fromname"));
				r.setContent(rs.getString("e_content"));
				r.setCreatedate(rs.getString("time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, stmt, pstm, rs);
		}
		return r;
	}
	
	/**
	 * 按ID查询已发邮件
	 * @param i
	 * @return 已发的邮件
	 */
	public Send emailS(int id){
		con=con();
		Send s=new Send();
		String sql="select s.*,to_char(e_createdate,'YYYY-MM-DD HH24:Mi:ss') time from send s where e_id=?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, id);
			rs=pstm.executeQuery();
			if(rs.next()){
				s.setTitle(rs.getString("e_title"));
				s.setFromname(rs.getString("e_fromname"));
				s.setContent(rs.getString("e_content"));
				s.setCreatedate(rs.getString("time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, stmt, pstm, rs);
		}
		return s;
	}
	
	/**
	 * 查看邮件后改变邮件状态
	 * @param title
	 */
	public void update(int id){
		con=con();
		String sql="update receive set e_state = ? where e_id=?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, 1);
			pstm.setInt(2, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, stmt, pstm, rs);
		}
	}
	
	/**
	 * 通过ID删除接收的邮件
	 * @param title
	 */
	public void delete(int id){
		con=con();
		String sql="delete from receive where e_id = ?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, stmt, pstm, rs);
		}
	}
	
	/**
	 * 查询接收邮件的用户名
	 * @param toname
	 * @return 接收人列表
	 */
	public List<Users> toname(String toname){
		con=con();
		List<Users> list=new ArrayList<Users>();
		String sql="select * from users where username <> ?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, toname);
			rs=pstm.executeQuery();
			while(rs.next()){
				Users u=new Users();
				u.setUsername(rs.getString("username"));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, stmt, pstm, rs);
		}
		return list;
	}
	
	/**
	 * 发送邮件时在邮件接收表和发送表中添加邮件
	 * @param toname
	 * @param fromname
	 * @param title
	 * @param content
	 * @return 添加是否成功
	 */
	public int newMsg(String toname,String fromname,String title,String content){
		con=con();
		String sql="insert into send values(s_seq.nextval,?,?,?,?,?,sysdate)";
		String sql1="insert into receive values(r_seq.nextval,?,?,?,?,?,sysdate)";
		int result=0;
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, fromname);
			pstm.setString(2, title);
			pstm.setString(3, content);
			pstm.setInt(4, 1);
			pstm.setString(5, toname);
			result=pstm.executeUpdate();
			pstm=con.prepareStatement(sql1);
			pstm.setString(1, fromname);
			pstm.setString(2, title);
			pstm.setString(3, content);
			pstm.setInt(4, 0);
			pstm.setString(5, toname);
			result+=pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 通过ID删除发送的邮件
	 * @param title
	 */
	public void deleteS(int id){
		con=con();
		String sql="delete from send where e_id = ?";
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(con, stmt, pstm, rs);
		}
	}
	/**
	 * 模糊查询
	 * @param str
	 * @param name
	 * @param pageIndex
	 * @param pageCount
	 * @return 查询结果
	 */
	public List<Receive> selectB(String str,String name, int pageIndex, int pageCount) {
		List<Receive> list = new ArrayList<Receive>();
		String sql = "select b.* from(select a.*,rownum r,to_char(e_createdate,'YYYY-MM-DD HH24:Mi:ss') time from (select e.* from receive e where e_toname=? and (e_fromname like ? or e_title like ? or e_content like ?) order by e_createdate desc) a) b where r between ? and ?";
		con = con();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, "%"+str+"%");
			pstm.setString(3, "%"+str+"%");
			pstm.setString(4, "%"+str+"%");
			pstm.setInt(5, (pageIndex - 1) * pageCount + 1);
			pstm.setInt(6, pageIndex * pageCount);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Receive r = new Receive();
				r.setId(rs.getInt(1));
				r.setFromname(rs.getString(2));
				r.setTitle(rs.getString(3));
				r.setContent(rs.getString(4));
				r.setState(rs.getInt(5));
				r.setToname(rs.getString(6));
				r.setCreatedate(rs.getString("time"));
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, stmt, pstm, rs);
		}
		return list;
	}
	
	/**
	 * 模糊查询接收邮件总条数
	 * @return 总条数
	 */
	public int selectBCountR(String name,String str){
		int countA=0;
		con=con();
		try {
			pstm=con.prepareStatement("select count(*) from receive where e_toname=? and (e_fromname like ? or e_title like ? or e_content like ?)");
			pstm.setString(1, name);
			pstm.setString(2, "%"+str+"%");
			pstm.setString(3, "%"+str+"%");
			pstm.setString(4, "%"+str+"%");
			rs=pstm.executeQuery();
			if(rs.next()){
				countA=rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			close(con, stmt, pstm, rs);
		}
		return countA;
	}
}
