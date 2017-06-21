package com.zei.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zei.entity.Manager;
import com.zei.entity.Student;
import com.zei.entity.Test;
import com.zei.service.ManagerService;
import com.zei.service.StudentService;

@Controller
public class EolController {

	@Autowired
	ManagerService ms;
	@Autowired
	StudentService ss;

	/*登录验证*/
	@RequestMapping("/login")
	public String login(String name, String password, int state,HttpServletRequest req,Model mo) {
		/*state == 1即为管理员登录*/
		if (state == 1) {
			Manager m = new Manager();
			m.setName(name);
			m.setPassword(password);
			Manager mm = ms.Findmanager(m);
			if (null != mm) {
				req.getSession().setAttribute("manname", name);
				return "Manager/login.jsp";				
			} else {
				mo.addAttribute("wrong","您的账号/密码错误");
				return "index.jsp";
			}
		} else {
			/*学生登录验证*/
			Student s=new Student();

			s.setName(name);
			s.setPassword(password);

			Student stu = ss.stuLogin(s);
			/*有这个学生对象则去抽取题目，开始考试*/
			if(null!=stu){
				req.getSession().setAttribute("stu", stu);
				return "Student/stumain.jsp";
			} else {
				mo.addAttribute("wrong","您的账号/密码错误");
				return "index.jsp";
			}
		}

	}

	@RequestMapping("/Addtitle")
	public String addt(Test t) {
		ms.add(t);
		return "Manager/login.jsp";
	}

	@RequestMapping("/editgo")
	public String Testupdate(Model m, Test p) {
			
		m.addAttribute("pagesize", p.getPagesize());

		m.addAttribute("num", ms.getsize()/5+1);

		m.addAttribute("list", ms.getall(p));

		return "Manager/show.jsp";
	}
	@RequestMapping("/findid")
	public String findid(Model m,Test p){
		m.addAttribute("Text", ms.findid(p.getTestid()));
		return "Manager/edit.jsp";
	}
	
	@RequestMapping("/update")
	public String showupdate(Test t){
		ms.TestUpdate(t);
		return "editgo";
	}
	
	@RequestMapping("/deleteid")
	public String delete(int testid){
		ms.TestDelete(testid);
		return "editgo";
		
	}
	
	
	@RequestMapping("/addstu")
	public String AddStudent(Student stu){
		ms.addstu(stu);
		return "Manager/Student.jsp";
	}
	
	@RequestMapping("/findname")
	public String find(Model m,String name){
		name="%"+name+"%";
		m.addAttribute("list", ms.findname(name));
		return "Manager/name.jsp";
	}
	
	@RequestMapping("/socer")
	public String getid (Model m,int stuid){
		m.addAttribute("li", ms.getid(stuid));
		return "Manager/socer.jsp";
	}
	
	@RequestMapping("/findtitle")
	public String findtl(Model m,String title){
		title="%"+title+"%";
		m.addAttribute("mt",ms.findTest(title));
		return "Manager/title.jsp";
	}
	@RequestMapping("/findanswer")
	public String findaw(Model m,String answer){
		System.out.println(ms.findanswer(answer));
		m.addAttribute("mt",ms.findanswer(answer));
		return "Manager/title.jsp";
	}
}
