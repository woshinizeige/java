package com.zei.dao;

import java.util.List;

import com.zei.entity.Exam;
import com.zei.entity.Manager;
import com.zei.entity.Student;
import com.zei.entity.Test;

public interface ManagerDao {
	public Manager Findmanager(Manager mg);

	public void addti(Test t);

	public List<Test> getall(Test p);
	
	public int getsize();
	
	public Test findid(int id);
	
	public void TestUpdate(Test t);
	
	public void TestDelete(int id);
	
	public void addstu(Student stu);
	
	public List<Student> findname(String name);
	
	public List<Exam> getid(int id);
	
	public List<Test> findTest(String name);
	
	public List<Test> findanswer(String answer);
}
