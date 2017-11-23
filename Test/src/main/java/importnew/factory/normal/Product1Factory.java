package importnew.factory.normal;

import importnew.factory.model.Product;
import importnew.factory.model.Product1;

public interface Product1Factory {
    public Product1 create1();

    // 抽象工厂
    public Product create();
}
