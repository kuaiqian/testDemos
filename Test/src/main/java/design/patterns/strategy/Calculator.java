package design.patterns.strategy;

public enum Calculator {
    // 加法运算
    ADD("+") {
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },
    // 减法运算
    SUB("-") {
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    };
    private String value;

    private Calculator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public abstract int exec(int a, int b);
}
