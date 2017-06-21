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
		String name=(String)req.getSession().getAttribute("name");       // ��ǰ�û��������ͷ�
		if(""==name||null==name){
			name=req.getParameter("name");
		} 
		String pageindex = req.getParameter("pageindex"); // ��ǰҳ��
		/* ��ǰҳ��Ϊ�գ���ֵ1 */
		if (null == pageindex) {
			pageindex = "1";
		}
		int pageindex1 = Integer.parseInt(pageindex); // תΪint����
		SPage sp = new SPage(); // ���ͷ�ҳʵ����
		UserDao ud = new UserDao();
		sp.setPageIndex(pageindex1); // ���õ�ǰҳ
		sp.setPageCount(5); // ����ÿҳ��ʾ����
		int count = ud.selectCountS(name); // ͨ���û���ѯ�����ʼ�������
		sp.setPageCountA(count); // ���÷����ʼ�������
		List<Send> list = ud.emailSend(name, sp.getPageIndex(), sp.getPageCount()); // �õ���ǰҳҪ��ʾ���ʼ��б�
		sp.setList(list);         // ���õ�ǰҳҪ��ʾ���ʼ��б�
		req.getSession().setAttribute("sp", sp);
		resp.sendRedirect("yifaMsg.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
