package importnew.factory.normal;

import importnew.factory.model.Product;
import importnew.factory.model.Product1;
import importnew.factory.model.ProductA;
import importnew.factory.model.ProductC;

public class CProductFactory implements Product1Factory {
    @Override
    public Product create() {
        return new ProductA();
    }

    @Override
    public Product1 create1() {
        return new ProductC();
    }
}
