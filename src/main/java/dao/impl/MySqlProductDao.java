package dao.impl;

import dao.CategoryDao;
import dao.ProductDao;
import dao.RoleDao;
import lombok.SneakyThrows;
import model.Category;
import model.Product;
import model.Role;
import model.User;
import util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static util.MySQLConnector.CONNECTION;

public class MySqlProductDao implements ProductDao {


    private CategoryDao categoryDao;

    public MySqlProductDao() {
        categoryDao = new MySqlCategoryDao();
    }

    @Override
    @SneakyThrows
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.GET_ALL_PRODUCTS);
             ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Category category = categoryDao.getById(result.getInt("category_id"));
                products.add(getProductFromResult(result, category));
            }
        }
        return products;
    }

    @Override
    @SneakyThrows
    public Product getById(int productId) {
        Product product = null;
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.GET_PRODUCT_BY_ID)) {
            statement.setInt(1, productId);
            try(ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Category category = categoryDao.getById(result.getInt("category_id"));
                    product = getProductFromResult(result, category);
                }
            }
        }
        return product;
    }

    @Override
    @SneakyThrows
    public void create(Product product) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.CREATE_PRODUCT)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getQuantity());
            statement.setInt(6, product.getCategory().getId());
            statement.execute();
        }
    }

    @SneakyThrows
    private static Product getProductFromResult(ResultSet result, Category category) {
        Product product = new Product();
        product.setId(result.getInt("id"));
        product.setName(result.getString("name"));
        product.setDescription(result.getString("description"));
        product.setPrice(result.getDouble("price"));
        product.setQuantity(result.getInt("quantity"));
        product.setCategory(category);
        return product;
    }

}
