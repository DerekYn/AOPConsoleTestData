package com.test.pointcut;

public class Core implements ICore {
	
	public int num = 100;
	private String name = "홍길동";
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override // 주업무
	public void m1() {
		System.out.println("주업무");
	}	
	
	@Override // 주업무
	public void m2() {
		System.out.println("주업무2");
	}

	@Override
	public void m3(String color, String subject) {
		
		System.out.println("주업무3");
		System.out.print("색상 : " + color);
		System.out.println(" / 과목 : " + subject);
	}

	
	private int c = -5;
	
	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	@Override
	public void m4(int a, int b) {
		
		// a 또는 b 가 음수가 들어오면 0으로 변경되야 한다.
		// a 또는 b 가 10 보다 크면 10으로 변경되야 한다.
		
		int sum = a + b + c;
		// c는 -5에서 5으로 바뀌어야 한다.
		
		System.out.println("sum(a + b + c) => " + sum);
		// sum( a + b + c ) => 15 가 아니라 175 로 나온다.
		// 왜냐하면 a, b 는 지역변수이므로 다른곳에서 접근이 불가능하므로 변경이 될 수 없다.
		
	}

}
