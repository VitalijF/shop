package dao.mysql;

import dao.BucketDao;
import lombok.SneakyThrows;
import model.Bucket;
import util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import static util.MySQLConnector.CONNECTION;

public class MySqlBucketDao implements BucketDao {

    @Override
    @SneakyThrows
    public Bucket getById(int bucketId) {
        Bucket bucket = null;
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.GET_BUCKET_BY_ID)) {
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
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.CREATE_BUCKET)) {
            statement.setInt(1, bucket.getId());
            statement.setTimestamp(2, Timestamp.valueOf(bucket.getCreatedDate()));
            statement.execute();
        }
    }


}
