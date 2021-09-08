package module3.service.product;

import module3.model.Product;
import module3.service.IService;

public interface IProductService extends IService<Product> {
    public void save(Product p, int[] categories);
}
