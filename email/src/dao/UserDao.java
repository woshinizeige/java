package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Receive;
import entity.Send;
import entity.Users;

public class UserDao extends BaseDao {

	/**
	 * ��¼����
	 * 
	 * @param name
	 * @param password
	 * @return �Ƿ�����ݿ�鵽�û���
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
	 * ע���ѯ�û���
	 * 
	 * @param name
	 * @return �Ƿ�����û���
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
	 * ע��
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
	 * ��ѯ�����ʼ�
	 * 
	 * @param name
	 * @return �����ʼ��б�
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
	 * ��ѯ�ѷ��ʼ�
	 * 
	 * @param name
	 * @return �ѷ��б�
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
	 * ��ѯ�����ʼ�������
	 * @return ������
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
	 * ��ѯ�ѷ��ʼ�������
	 * @return ������
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
	 * ��ID��ѯ�����ʼ�
	 * @param i
	 * @return ���յ��ʼ�
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
	 * ��ID��ѯ�ѷ��ʼ�
	 * @param i
	 * @return �ѷ����ʼ�
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
	 * �鿴�ʼ���ı��ʼ�״̬
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
	 * ͨ��IDɾ�����յ��ʼ�
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
	 * ��ѯ�����ʼ����û���
	 * @param toname
	 * @return �������б�
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
	 * �����ʼ�ʱ���ʼ����ձ�ͷ��ͱ�������ʼ�
	 * @param toname
	 * @param fromname
	 * @param title
	 * @param content
	 * @return ����Ƿ�ɹ�
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
	 * ͨ��IDɾ�����͵��ʼ�
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
	 * ģ����ѯ
	 * @param str
	 * @param name
	 * @param pageIndex
	 * @param pageCount
	 * @return ��ѯ���
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
	 * ģ����ѯ�����ʼ�������
	 * @return ������
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
