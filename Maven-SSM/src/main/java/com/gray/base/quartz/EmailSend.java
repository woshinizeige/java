package com.gray.base.quartz;

import java.util.Date;


public class EmailSend{

	public void execute(){
		// TODO �����ʼ���һЩ����
		System.out.println("Timed task execution...Current time is: "+new Date(System.currentTimeMillis()));
	}

}
