package com.zei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zei.dao.ManagerDao;
import com.zei.entity.Exam;
import com.zei.entity.Manager;
import com.zei.entity.Student;
import com.zei.entity.Test;
import com.zei.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerDao md;

	@Override
	public Manager Findmanager(Manager m) {
		return md.Findmanager(m);
	}

	@Override
	public void add(Test t) {
		md.addti(t);
	}

	@Override
	public List<Test> getall(Test p) {
		return md.getall(p);
	}

	@Override
	public int getsize() {
		return md.getsize();
	}

	@Override
	public Test findid(int id) {
		return md.findid(id);
	}
	
	@Override
	public void TestUpdate(Test t) {
		md.TestUpdate(t);
	}

	@Override
	public void TestDelete(int id) {
		md.TestDelete(id);
		
	}

	@Override
	public void addstu(Student stu) {
		md.addstu(stu);		
	}

	@Override
	public List<Student> findname(String name) {
		List<Student> list=md.findname(name);
		return list;
	}

	@Override
	public List<Exam> getid(int id) {		
		return md.getid(id);
	}

	@Override
	public List<Test> findTest(String name) {
		return md.findTest(name);
	}

	public List<Test> findanswer(String answer) {
		return md.findanswer(answer);
	}
}
