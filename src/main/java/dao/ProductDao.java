package dao;

import model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getAll();

    Product getById(int productId);

    void create(Product product);

}
