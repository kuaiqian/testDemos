package jdk.study;

public class InitClassTest {

	public static void main(String[] args) {
		BadClass.doSomeThing();
		BadClass.doSomeThing();
	}
     
	
	
	private static class BadClass{
		static {
			System.out.println("before init error");
			try {
				int a=1/0;
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("after init error");
		}
		
		public static void doSomeThing() {
			System.out.println("something");
		}
	}
}
