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

public class RecieveListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String name=(String)req.getSession().getAttribute("name");               //当前用户，即接收方
		if(""==name||null==name){
			name=req.getParameter("name");
		}
		String pageindex=req.getParameter("pageindex");     //当前页数
		/*当前页数为空，赋值1*/
		if(null==pageindex){
			pageindex="1";
		}
		int pageindex1=Integer.parseInt(pageindex);        //转为int类型
		RPage rp=new RPage();                              //接收分页实体类
		UserDao ud=new UserDao();  
		rp.setPageIndex(pageindex1);                       //设置当前页
		rp.setPageCount(5);                               //设置每页显示条数
		int count=ud.selectCountR(name);                   //通过用户查询接收邮件的总数
		rp.setPageCountA(count);                           //设置接收邮件的总数
		List<Receive> list=ud.emailList(name, rp.getPageIndex(), rp.getPageCount());   //得到当前页要显示的邮件列表
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
