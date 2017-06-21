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
		String type=req.getParameter("type");          //�õ�Ҫ���еĲ���
		String toname=req.getParameter("toUser");      //�õ�������
		String fromname=req.getParameter("fromname");  //�õ��ʼ�������
		String title=req.getParameter("title");        //�õ��ʼ�����
		String content=req.getParameter("content");    //�õ��ʼ�����
		PrintWriter pw=resp.getWriter();
		String error2=null;
		UserDao ud=new UserDao();
		int result=0;
		String str=null;
		if(type.equals("nam")){
			if(""==toname||null==toname){
				error2="�ռ��˲���Ϊ�գ�";
			}else{
				error2="�ռ��˺Ϸ�";
			}
		}else if(type.equals("tit")){
			if(""==title||null==title){
				error2="���ⲻ��Ϊ�գ�";
			}else{
				error2="�������";
			}
		}else if(type.equals("con")){
			if(""==content||null==content){
				error2="���ݲ���Ϊ�գ�";
			}else{
				error2="���ݺϷ�";
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
