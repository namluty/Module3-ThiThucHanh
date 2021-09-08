package module3.service.category;

import module3.config.ConnectionJDBC;
import module3.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    private static final String SELECT_ALL_CATEGORY = "select * from category;";
    private static final String SELECT_CATEGORY_BY_B_ID = "select * from category join product_category pc on category.id_cate = pc.id_category and pc.id_product;";
    private static final String SELECT_CATEGORIES_BY_ID = "select * from category join product p on category.id_product = p.id where id_cate;";
    private static final String INSERT_CATEGORY = "insert into category (name,description)value (?,?);";
    private static final String UPDATE_CATEGORY = "update category set name =? , description = ? where id_cate=?;";
    private static final String DELETE_CATEGORY = "delete from category where id_cate = ?;";

    Connection connection = ConnectionJDBC.getConnect();

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Category category = new Category(id, name, description);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }


    @Override
    public Category findById(int id) {
        Category category = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORIES_BY_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id_cate = resultSet.getInt("id_cate");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                category = new Category(id_cate, name, description);
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }
        return category;
    }

    @Override
    public void save(Category p) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY);
            statement.setInt(1, p.getId());
            statement.setString(2, p.getName());
            statement.setString(3, p.getDescription());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(int id, Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY);
            statement.setInt(1, category.getId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Category> findAllByProductId(int id_product) {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY_BY_B_ID);
            statement.setInt(1,id_product);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Category category = new Category(id, name, description);
                categories.add(category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }
}
