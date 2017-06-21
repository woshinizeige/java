package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.RPage;
import entity.Receive;

public class SelectBServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String name=req.getParameter("name");               //当前用户，即接收方
		String pageindex=req.getParameter("pageindex");     //当前页数
		String str=req.getParameter("str");                 //模糊查询的关键词
		/*当前页数为空，赋值1*/
		if(null==pageindex){
			pageindex="1";
		}
		int pageindex1=Integer.parseInt(pageindex);        //转为int类型
		RPage rp=new RPage();                              //接收分页实体类
		UserDao ud=new UserDao();  
		rp.setPageIndex(pageindex1);                       //设置当前页
		rp.setPageCount(5);                               //设置每页显示条数
		List<Receive> list=null;
		if(str!=""||str!=null){
			str=new String(str.getBytes("iso-8859-1"),"UTF-8");
			int count=ud.selectBCountR(name,str);                   //通过用户与相关条件查询接收邮件的总数
			rp.setPageCountA(count);                           		//设置相关邮件的总数
			list=ud.selectB(str, name, rp.getPageIndex(), rp.getPageCount()); //模糊查询得到的邮件列表
			req.getSession().setAttribute("ty", str);
		}else{
			int count=ud.selectCountR(name);                   //通过用户查询接收邮件的总数
			rp.setPageCountA(count);                           //设置接收邮件的总数
			list=ud.emailList(name, rp.getPageIndex(), rp.getPageCount());   //默认查询所有的邮件列表
		}
		rp.setList(list);                                 //设置当前页要显示的邮件列表
		req.getSession().setAttribute("mtoname", name);   //设置收件人的姓名，回信是需要使用
		req.getSession().setAttribute("rp", rp);
		resp.sendRedirect("main.jsp");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
