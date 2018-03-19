package jdk.study.base;

public class TestInteger {
  public static void main(String[] args) {
	Byte a=(byte) 127; int b=127;
	System.out.println("Byte and int:"+(a==b));
	
	Byte a1=(byte) 128; Byte b1=(byte) 128;
	System.out.println("Byte and Byte:"+(a1==b1));
	
	Byte a2=Byte.parseByte("127");
	Byte b2=Byte.valueOf("127");
	System.out.println("String parseToByte:"+(
			a2==b2));
	
	
}
}
