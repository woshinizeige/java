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
		String name=req.getParameter("name");               //��ǰ�û��������շ�
		String pageindex=req.getParameter("pageindex");     //��ǰҳ��
		String str=req.getParameter("str");                 //ģ����ѯ�Ĺؼ���
		/*��ǰҳ��Ϊ�գ���ֵ1*/
		if(null==pageindex){
			pageindex="1";
		}
		int pageindex1=Integer.parseInt(pageindex);        //תΪint����
		RPage rp=new RPage();                              //���շ�ҳʵ����
		UserDao ud=new UserDao();  
		rp.setPageIndex(pageindex1);                       //���õ�ǰҳ
		rp.setPageCount(5);                               //����ÿҳ��ʾ����
		List<Receive> list=null;
		if(str!=""||str!=null){
			str=new String(str.getBytes("iso-8859-1"),"UTF-8");
			int count=ud.selectBCountR(name,str);                   //ͨ���û������������ѯ�����ʼ�������
			rp.setPageCountA(count);                           		//��������ʼ�������
			list=ud.selectB(str, name, rp.getPageIndex(), rp.getPageCount()); //ģ����ѯ�õ����ʼ��б�
			req.getSession().setAttribute("ty", str);
		}else{
			int count=ud.selectCountR(name);                   //ͨ���û���ѯ�����ʼ�������
			rp.setPageCountA(count);                           //���ý����ʼ�������
			list=ud.emailList(name, rp.getPageIndex(), rp.getPageCount());   //Ĭ�ϲ�ѯ���е��ʼ��б�
		}
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
