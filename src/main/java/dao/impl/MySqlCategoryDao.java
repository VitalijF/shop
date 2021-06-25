package dao.impl;

import dao.CategoryDao;
import lombok.SneakyThrows;
import model.Category;
import model.Role;
import model.RoleName;
import util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static util.MySQLConnector.CONNECTION;

public class MySqlCategoryDao implements CategoryDao {

    @Override
    @SneakyThrows
    public Category getById(int id) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.GET_CATEGORY_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next()
                        ? getCategoryFromResultSet(resultSet)
                        : null;
            }
        }
    }

    @SneakyThrows
    private Category getCategoryFromResultSet(ResultSet resultSet) {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName("name");
        return category;
    }
}
