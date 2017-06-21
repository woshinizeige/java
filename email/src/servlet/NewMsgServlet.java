package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class NewMsgServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("Utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String type=req.getParameter("type");          //得到要进行的操作
		String toname=req.getParameter("toUser");      //得到接收人
		String fromname=req.getParameter("fromname");  //得到邮件发送人
		String title=req.getParameter("title");        //得到邮件标题
		String content=req.getParameter("content");    //得到邮件内容
		PrintWriter pw=resp.getWriter();
		String error2=null;
		UserDao ud=new UserDao();
		int result=0;
		String str=null;
		if(type.equals("nam")){
			if(""==toname||null==toname){
				error2="收件人不能为空！";
			}else{
				error2="收件人合法";
			}
		}else if(type.equals("tit")){
			if(""==title||null==title){
				error2="标题不能为空！";
			}else{
				error2="标题可用";
			}
		}else if(type.equals("con")){
			if(""==content||null==content){
				error2="内容不能为空！";
			}else{
				error2="内容合法";
			}
		}else{
			result=ud.newMsg(toname, fromname, title, content);
			if(result==2){
				str="cg";
				req.getSession().setAttribute("str", str);
				resp.sendRedirect("newMsg.jsp");
			}else{
				str="sb";
				req.getSession().setAttribute("str", str);
				resp.sendRedirect("newMsg.jsp");
			}
			
		}
		pw.print(error2);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
