package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entity.Receive;
import entity.SPage;
import entity.Send;

public class SendListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String name=(String)req.getSession().getAttribute("name");       // 当前用户，即发送方
		if(""==name||null==name){
			name=req.getParameter("name");
		} 
		String pageindex = req.getParameter("pageindex"); // 当前页数
		/* 当前页数为空，赋值1 */
		if (null == pageindex) {
			pageindex = "1";
		}
		int pageindex1 = Integer.parseInt(pageindex); // 转为int类型
		SPage sp = new SPage(); // 发送分页实体类
		UserDao ud = new UserDao();
		sp.setPageIndex(pageindex1); // 设置当前页
		sp.setPageCount(5); // 设置每页显示条数
		int count = ud.selectCountS(name); // 通过用户查询发送邮件的总数
		sp.setPageCountA(count); // 设置发送邮件的总数
		List<Send> list = ud.emailSend(name, sp.getPageIndex(), sp.getPageCount()); // 得到当前页要显示的邮件列表
		sp.setList(list);         // 设置当前页要显示的邮件列表
		req.getSession().setAttribute("sp", sp);
		resp.sendRedirect("yifaMsg.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
