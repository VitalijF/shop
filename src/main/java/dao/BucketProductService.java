package dao;

import model.Product;

import java.util.List;

public interface BucketProductService {

    List<Product> getProductsByBucketId(int bucketId);

    void removeProductFromBucket(int bucketId, int productId);

    void addProductToBucket(int bucketId, int productId);
}
