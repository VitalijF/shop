package dao.mysql;

import dao.ProductDao;
import lombok.SneakyThrows;
import model.Product;
import util.QueryConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static util.MySQLConnector.CONNECTION;

public class MySqlProductDao implements ProductDao {

    private static Connection connection;


    @Override
    @SneakyThrows
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.GET_ALL_PRODUCTS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
//                products.add(getProductFromResult(resultSet));
            }
        }
        return products;
    }

    @Override
    @SneakyThrows
    public Product getById(int productId) {
        Product product = null;
        try (PreparedStatement statement = connection.prepareStatement(QueryConstants.GET_PRODUCT_BY_ID)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
//                product = new Product(resultSet.getInt("id"), resultSet.getString("name"),
//                        resultSet.getString("description"), resultSet.getDouble("price"));
                }
            }

        }
        return product;
    }

    @Override
    @SneakyThrows
    public void create(Product product)  {
        try (PreparedStatement statement = connection.prepareStatement(QueryConstants.CREATE_PRODUCT)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.execute();
        }
    }

//    private static Product getProductFromResult(ResultSet resultSet) throws SQLException {
//        return new Product(resultSet.getInt("id"), resultSet.getString("name"),
//                resultSet.getString("description"), resultSet.getDouble("price"));
//    }
}
