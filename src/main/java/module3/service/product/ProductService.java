package module3.service.product;

import module3.config.ConnectionJDBC;
import module3.model.Category;
import module3.model.Product;
import module3.service.category.CategoryService;
import module3.service.category.ICategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    public static final String SELECT_ALL_PRODUCT = "select * from product;";
    private static final String II_NEW_PRODUCT = "insert into product (name ,price,quantity,color,description) values (?,?,?,?,?);";
    private static final String INSERT_P_C = "insert into product_category (id_product, id_category)value (?,?);";
    private static final String INSERT_PRODUCT = "insert into product (name ,price,quantity,color,description) values (?,?,?,?,?);";
    private static final String DELETE_PRODUCT = "delete from product where id =?;";
    private static final String UPDATE_PRODUCT = "update product set name =?, price =?, quantity=?, color =?, description =? where id =?;";


    Connection connection = ConnectionJDBC.getConnect();
    ICategoryService categoryService = new CategoryService();


    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                List<Category> categories = categoryService.findAllByProductId(id);
                Product product = new Product(id, name, price, quantity, color, description, categories);
                products.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public void save(Product p) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT);
            statement.setString(1, p.getName());
            statement.setInt(2,p.getPrice());
            statement.setInt(3,p.getQuantity());
            statement.setString(4,p.getColor());
            statement.setString(5, p.getDescription());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(int id, Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setInt(2,product.getPrice());
            statement.setInt(3,product.getQuantity());
            statement.setString(4,product.getColor());
            statement.setString(5, product.getDescription());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void save(Product p, int[] categories) {
        int product_id = 0;
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement1 = connection.prepareStatement(II_NEW_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            statement1.setString(1, p.getName());
            statement1.setInt(2, p.getPrice());
            statement1.setInt(3, p.getQuantity());
            statement1.setString(4, p.getColor());
            statement1.setString(5, p.getDescription());
            int a1 = statement1.executeUpdate();
            ResultSet resultSet = statement1.getGeneratedKeys();
            while (resultSet.next()) {
                product_id = resultSet.getInt(1);
            }
            PreparedStatement statement = connection.prepareStatement(INSERT_P_C);
            for (int id_category : categories) {
                statement.setInt(1, id_category);
                statement.setInt(2, product_id);
            }
            connection.commit();
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }
}
