package hibernate;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.test.context.TestContextManager;

public class SpringJUnit45ClassRunner extends BlockJUnit4ClassRunner {

    private final TestContextManager testContextManager;

    public SpringJUnit45ClassRunner(Class<?> clazz)
            throws org.junit.runners.model.InitializationError {
        super(clazz);
        testContextManager = createTestContextManager(clazz);
    }

    @Override
    protected Object createTest() throws Exception {
        Object testInstance = super.createTest();
        getTestContextManager().prepareTestInstance(testInstance);
        return testInstance;
    }

    protected TestContextManager createTestContextManager(Class<?> clazz) {
        return new TestContextManager(clazz);
    }

    protected final TestContextManager getTestContextManager() {
        return testContextManager;
    }
}
