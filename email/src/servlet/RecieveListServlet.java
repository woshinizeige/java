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
		String name=(String)req.getSession().getAttribute("name");               //��ǰ�û��������շ�
		if(""==name||null==name){
			name=req.getParameter("name");
		}
		String pageindex=req.getParameter("pageindex");     //��ǰҳ��
		/*��ǰҳ��Ϊ�գ���ֵ1*/
		if(null==pageindex){
			pageindex="1";
		}
		int pageindex1=Integer.parseInt(pageindex);        //תΪint����
		RPage rp=new RPage();                              //���շ�ҳʵ����
		UserDao ud=new UserDao();  
		rp.setPageIndex(pageindex1);                       //���õ�ǰҳ
		rp.setPageCount(5);                               //����ÿҳ��ʾ����
		int count=ud.selectCountR(name);                   //ͨ���û���ѯ�����ʼ�������
		rp.setPageCountA(count);                           //���ý����ʼ�������
		List<Receive> list=ud.emailList(name, rp.getPageIndex(), rp.getPageCount());   //�õ���ǰҳҪ��ʾ���ʼ��б�
		rp.setList(list);                                 //���õ�ǰҳҪ��ʾ���ʼ��б�
		req.getSession().setAttribute("mtoname", name);   //�����ռ��˵���������������Ҫʹ��
		req.getSession().setAttribute("rp", rp);
		resp.sendRedirect("main.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
