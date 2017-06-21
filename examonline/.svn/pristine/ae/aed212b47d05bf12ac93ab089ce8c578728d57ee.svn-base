package com.zei.service;

import java.util.List;

import com.zei.entity.Exam;
import com.zei.entity.Student;
import com.zei.entity.Test;

public interface StudentService {
	public Student stuLogin(Student stu);   //学生登录验证
	public List<Test> getTest();            //随机获得题目
	public String getAnswer(int id);        //通过题目ID去查询答案
	public void addexam(Exam e);            //增加考试记录
	public List<Exam> lookexam(int stuid);    //学生查找自己的考试记录
	public void updatepsw(Student stu);       //修改密码
}
