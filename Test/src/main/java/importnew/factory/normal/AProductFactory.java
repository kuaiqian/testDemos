package importnew.factory.normal;

import importnew.factory.model.Product;
import importnew.factory.model.ProductA;

public class AProductFactory implements ProductFactory {
    @Override
    public Product create() {
        return new ProductA();
    }

}
