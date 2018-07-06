package com.test.pointcut;

import java.util.HashMap;
import java.util.Set;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Cross {
	
	@Pointcut("execution(public void com.test.pointcut.Core.m1())")
	public void pc1() {}
	
	@Before("pc1()")
	public void before(JoinPoint joinPoint) {
		
		System.out.println("보조 업무");
		
		// 주업무 객체를 반환한다.
		System.out.println(joinPoint.getTarget());
		// getTarget()은 주업무 객체를 반환하기 때문에 주업무의 여러가지 정보를 얻어올수 있다.
		
		// Pointcut AspectJ 형식을 반환한다.
		System.out.println(joinPoint.toLongString());
		System.out.println(joinPoint.toShortString());
		System.out.println(joinPoint.toString());
		
		// Pointcut 메소드 형식을 반환한다.
		System.out.println(joinPoint.getSignature());
		
		// 인자값 리스트를 반환한다.
		System.out.println(joinPoint.getArgs());
		// getArgs()은 Pointcut의 파라미터값을 반환한다.
		// 주업무 객체가 실행되기 위한 인자값에 대한 정보를 보조 업무 개체가 접근 가능하다는 뜻이다.
	}
	
	@Pointcut("execution(public void com.test.pointcut.Core.m2())")
	public void pc2() {}
	
	@Before("pc2()")
	public void before2(JoinPoint joinPoint) {

		Core core = (Core)joinPoint .getTarget();
		
		System.out.println(core.num);
		System.out.println(core.getName());
		
	}
	
	@Pointcut("execution(public void com.test.pointcut.Core.m3(String, String))")
	public void pc3() {}
	
	HashMap<String, String> mapColor = new HashMap<String, String>();
	HashMap<String, String> mapSubject = new HashMap<String, String>();
	
	@After("pc3()")
	public void after(JoinPoint joinPoint) {
		
		// Pointcut("m3()")의 인자갑을 가져온다.
		// 즉, MainTest에서 m3()를 호출할때 넣어준 색상명을 가져온다.
		
		String color = (String)joinPoint.getArgs()[0];
		
		if(color != null && !color.trim().isEmpty()) {
			
			if(color.equalsIgnoreCase("red")) 
				mapColor.put(color, "빨강");
			else if(color.equalsIgnoreCase("blue")) 
				mapColor.put(color, "파랑");
			else if(color.equalsIgnoreCase("yellow")) 
				mapColor.put(color, "노랑");
			else if(color.equalsIgnoreCase("green")) 
				mapColor.put(color, "초록");
			else
				mapColor.put(color, "기타 색상");
		}
		else {
			color = "none";
			mapColor.put(color, "색상 없음");
		}
		
		Set<String> keyset = mapColor.keySet();
		
		for(String key : keyset) {
			System.out.println(key + " - " + mapColor.get(key));
		}
				
		String subject = (String)joinPoint.getArgs()[1];
		
		if(subject != null && !subject.trim().isEmpty()) {
			
			if(subject.equalsIgnoreCase("kor")) 
				mapSubject.put(subject, "국어");
			else if(subject.equalsIgnoreCase("eng")) 
				mapSubject.put(subject, "영어");
			else if(subject.equalsIgnoreCase("math")) 
				mapSubject.put(subject, "수학");
			else if(subject.equalsIgnoreCase("science")) 
				mapSubject.put(subject, "과학");
			else if(subject.equalsIgnoreCase("society")) 
				mapSubject.put(subject, "사회");
			else
				mapSubject.put(subject, "기타 과목");
		}
		else {
			subject = "none";
			mapSubject.put(subject, "과목 없음");
		}
		
		keyset = mapSubject.keySet();
		
		for(String key : keyset) {
			System.out.println(key + " - " + mapSubject.get(key));
		}
		System.out.println("--------------------------------------------------------");
	}
	
	
	@Pointcut("execution(public void com.test.pointcut.Core.m4(int, int))")
	public void pc4() {}
	
	@Before("pc4()")
	public void beforeQuiz(JoinPoint joinPoint) {

		int a = (Integer)joinPoint.getArgs()[0];
		int b = (Integer)joinPoint.getArgs()[1];
		System.out.println("a => " + a + ", b => " + b);
		
		Core core = (Core)joinPoint .getTarget();
		
		int c = core.getC();
		
		if(a < 0) a = 0;
		else if(a > 10) a = 10;
		
		if(b < 0) b = 0;
		else if(b > 10) b = 10;
		
		if(c < 0)
			core.setC(-c);
		
		joinPoint.getArgs()[0] = a;	// 파라미터값은 읽기만 되고 변경은 불가능
		joinPoint.getArgs()[1] = b; // 파라미터값은 읽기만 되고 변경은 불가능
		
	}
	
	////// == !!! 정리 !!! == //////
	/*
	 	주업무 객체에서 보조업무 객체로 무언가를 전달하려면...
	 	1. 주업무 객체의 멤버값 (public 으로 전달)
	 		--> JoinPoint 의 getTarget() 사용
	 		
	 	2. 주업무 객체의 Pointcut의 인자값
	 		--> JoinPoint 의 getArgs() 사용
	 		
	 	3. 주업무 객체의 Pointcut의 결과값
	 		--> After returning Advice 사용
	 */

}
