package design.patterns.strategy;

public enum Sex {
    MAN {
        @Override
        public void say() {
            System.out.println("男");
        }
    },
    WOMEN {
        @Override
        public void say() {
            System.out.println("女");
        }
    };
    void say() {
    };
}
