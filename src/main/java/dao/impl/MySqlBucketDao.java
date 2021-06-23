package dao.impl;

import dao.BucketDao;
import lombok.SneakyThrows;
import model.Bucket;
import util.MySQLConnector;
import util.QueryConstants;

import java.sql.*;

public class MySqlBucketDao implements BucketDao {

    private static Connection connection;

    @Override
    @SneakyThrows
    public Bucket getById(int bucketId) {
        Bucket bucket = null;
        try (PreparedStatement statement = connection.prepareStatement(QueryConstants.GET_BUCKET_BY_ID)) {
            statement.setInt(1, bucketId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    bucket = new Bucket(
                            resultSet.getInt("id"),
                            resultSet.getTimestamp("created_date").toLocalDateTime());
                }
            }

            return bucket;
        }
    }

    @Override
    @SneakyThrows
    public void create(Bucket bucket) {
        try (PreparedStatement statement = connection.prepareStatement(QueryConstants.CREATE_BUCKET)) {
            statement.setInt(1, bucket.getId());
            statement.setTimestamp(2, Timestamp.valueOf(bucket.getCreatedDate()));
            statement.execute();
        }
    }


}
