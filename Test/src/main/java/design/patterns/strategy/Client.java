package design.patterns.strategy;

public class Client {
    public static void main(String[] args) {
        System.out.println(Calculator.ADD.exec(1, 2));
        Sex.MAN.say();
    }
}
