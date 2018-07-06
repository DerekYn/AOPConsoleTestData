package com.test.pointcut;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		
		// IoC(Inversion of Control)컨테이너(Spring컨테이너)로 사용되는 
		// ApplicationContext 객체 생성하기 
		// 이것은 XML 설정을 로드함으로써 생성할 수 있다. 
		ApplicationContext context = new GenericXmlApplicationContext("classpath:main.xml"); 
	
		// 빈(bean) 객체 생성하기
		ICore core = context.getBean("core", ICore.class); 
		// core.xml 파일에서 id 가 "core"로 되어진 객체를 얻어옴.	
		
		core.m1();
		
		System.out.println("================================================");
		
		core.m2();
		
		System.out.println("------------------------------------------------");
		
		core.m3("red", "kor");
		core.m3("blue", "eng");
		core.m3("red", "math");
		core.m3(" ", "science");
		core.m3("yellow", "society");
		
		System.out.println("=================== 퀴 즈 ===================");
		
		core.m4(-30, 200);
		
	}

}
