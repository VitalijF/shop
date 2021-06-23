package dao;

import model.Bucket;

import java.sql.SQLException;

public interface BucketDao {

    Bucket getById(int bucketId);

    void create(Bucket bucket);
}
