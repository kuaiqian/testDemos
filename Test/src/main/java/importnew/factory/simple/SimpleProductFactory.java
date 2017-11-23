package importnew.factory.simple;

import importnew.factory.model.Product;
import importnew.factory.model.ProductA;
import importnew.factory.model.ProductB;

public class SimpleProductFactory {
    public Product createProduct(String type) {
        if("A".equals(type)) {
            return new ProductA();
        }else if("B".equals(type)) {
            return new ProductB();
        }
        return null;
    }
}
