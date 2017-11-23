package importnew;

public class TestFinally {
    public static void main(String[] args) throws Exception {
        System.out.println(foo());
    }

    private static int foo() throws Exception {
        try {
            int a = 5 / 0;
            return 1;
        }catch (Exception e) {
            System.out.println("catch");
            return 2;
        }finally {
            // return 3;
            // throw new Exception("finally");
            System.err.println("finally");
        }
    }
}
