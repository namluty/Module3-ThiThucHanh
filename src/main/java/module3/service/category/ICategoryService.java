package module3.service.category;

import module3.model.Category;
import module3.service.IService;

import java.util.List;

public interface ICategoryService extends IService<Category> {
    List<Category> findAllByProductId(int id_product);
}
