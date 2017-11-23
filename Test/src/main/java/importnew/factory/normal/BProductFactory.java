package importnew.factory.normal;

import importnew.factory.model.Product;
import importnew.factory.model.ProductB;

public class BProductFactory implements ProductFactory {
    @Override
    public Product create() {
        return new ProductB();
    }

}
