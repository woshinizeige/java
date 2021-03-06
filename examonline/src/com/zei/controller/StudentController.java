package com.zei.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zei.entity.Exam;
import com.zei.entity.Student;
import com.zei.entity.Test;
import com.zei.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	StudentService ss;
	
	/*随机抽取题目考试*/
	@RequestMapping("/test")
	public String getTest(HttpServletRequest req){
		List<Test> list=ss.getTest();        //得到题目列表
		req.getSession().setAttribute("testlist", list);    //将题目集合存在Session中
		return "Student/exam.jsp";		
	}
	
	/*提交试卷后算总分*/
	@RequestMapping("/submitpage")
	public String getpoint(int[] testid, HttpServletRequest req){
		int total=0;
		for(int i=0;i<testid.length;i++){
			//得到每题的答案
			String answer=req.getParameter("answer"+i);
			//得到每题的正确答案
			String rightAnswer=ss.getAnswer(testid[i]);
			if(answer.equals(rightAnswer.trim())){
				total+=10;
			}
		}
		SimpleDateFormat spdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/*考试时间*/
		String testtime=spdf.format(new Date());
		req.getSession().setAttribute("testtime", testtime);
		req.getSession().setAttribute("total", total);
		//考试记录对象
		Exam e=new Exam();
		e.setStu((Student)req.getSession().getAttribute("stu"));
		e.setExamtime(testtime);
		e.setPoint(total);
		ss.addexam(e);
		return "Student/showpoint.jsp";
	}
	
	/*学生查看自己的考试记录*/
	@RequestMapping("/lookexam")
	public String lookexam(HttpServletRequest req){
		//获取当前登录的学生信息
		Student stu=(Student)req.getSession().getAttribute("stu");
		//通过学生的ID去查询当前学生的考试记录
		List<Exam> examlist=ss.lookexam(stu.getStudentid());
		req.getSession().setAttribute("examlist", examlist);
		return "Student/lookexam.jsp";
	}
	/*修改密码*/
	@RequestMapping("/updatepsw")
	public String updatepsw(String oldpassword,String newpassword1,String newpassword2,HttpServletRequest req,Model m){
		//返回页面的修改提示信息
		String error=null;
		//得到当前登录的学生对象
		Student stu=(Student)req.getSession().getAttribute("stu");
		if(null==oldpassword){
			error="原密码输入为空，修改失败！";
		}else if(!oldpassword.equals(stu.getPassword())){
			error="原密码输入有误，修改失败！";
		}else if(null==newpassword1||null==newpassword2){
			error="新密码输入为空，修改失败！";
		}else if(!newpassword1.equals(newpassword2)){
			error="新密码两次输入不一致，修改失败！";
		}else{
			/*输入无误后则调用方法修改密码*/
			stu.setPassword(newpassword1);
			ss.updatepsw(stu);
			error="修改成功";
			m.addAttribute("statu0", 1);
			m.addAttribute("error", error);
			return "Student/updatepassword.jsp";
		}
		m.addAttribute("statu0", 1);
		m.addAttribute("error", error);
		return "Student/updatepassword.jsp";
		
	}
	
	/*退出登录*/
	@RequestMapping("/stuexit")
	public String stuexit(HttpServletRequest req){
		req.getSession().invalidate();
		return "index.jsp";
	}
	
}
