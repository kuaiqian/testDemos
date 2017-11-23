package importnew.singleten;

public class SingleInstance {
    private SingleInstance() {
        super();
    }

    public SingleInstance getSingleInstance() {
        return SingleInstance.Single.instance;
    }

    private static final class Single {
        static final SingleInstance instance = new SingleInstance();
    }
}
