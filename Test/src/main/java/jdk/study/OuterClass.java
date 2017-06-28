package jdk.study;

public class OuterClass {
	private static InnerClass inner2=new OuterClass().new InnerClass(2);
	private InnerClass inner1=new InnerClass(1);
	private String name="_aaa_";
	
	public InnerClass get() {
		return new InnerClass();
	}

	public static InnerClass getStatic(int i) {
		OuterClass o=new OuterClass();
		return o.new InnerClass(i);
	}

	public static void main(String[] args) {
		System.out.println("create InnerClass");
		OuterClass.getStatic(3);
		new OuterClass().get();
	}

	class InnerClass {
		private String name="_bbb_";
		public InnerClass() {
			System.out.println("new InnerClass"+this.name);
		}
		
		public InnerClass(int i) {
			System.out.println("new InnerClass"+OuterClass.this.name+i);
		}
		
		public void sayHi(String name){
			System.out.println("hi,"+name);
		}
	}
}
