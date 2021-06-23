//package dao.impl;
//
//import dao.ProductService;
//import model.Product;
//import util.MySQLConnector;
//import util.QueryConstants;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProductServiceImpl implements ProductService {
//
//    private static Connection connection;
//
//    public ProductServiceImpl() throws SQLException, ClassNotFoundException {
//        connection = MySQLConnector.getConnection();
//    }
//
//    @Override
//    public List<Product> readAll() throws SQLException {
//        List<Product> products = new ArrayList<>();
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(QueryConstants.GET_ALL_PRODUCTS)) {
//            while (resultSet.next()) {
//                products.add(getProductFromResult(resultSet));
//            }
//        }
//        return products;
//    }
//
//    @Override
//    public Product read(int productId) throws SQLException {
//        ResultSet resultSet = null;
//        Product product = null;
//        try (PreparedStatement statement = connection.prepareStatement(QueryConstants.GET_PRODUCT_BY_ID)) {
//            statement.setInt(1, productId);
//            resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                product = new Product(resultSet.getInt("id"), resultSet.getString("name"),
//                        resultSet.getString("description"), resultSet.getDouble("price"));
//            }
//        } finally {
//            resultSet.close();
//        }
//        return product;
//    }
//
//    @Override
//    public void create(Product product) throws SQLException {
//        try (PreparedStatement statement = connection.prepareStatement(QueryConstants.CREATE_PRODUCT)) {
//            statement.setInt(1, product.getId());
//            statement.setString(2, product.getName());
//            statement.setString(3, product.getDescription());
//            statement.setDouble(4, product.getPrice());
//            statement.execute();
//        }
//    }
//
//    private static Product getProductFromResult(ResultSet resultSet) throws SQLException {
//        return new Product(resultSet.getInt("id"), resultSet.getString("name"),
//                resultSet.getString("description"), resultSet.getDouble("price"));
//    }
//}
