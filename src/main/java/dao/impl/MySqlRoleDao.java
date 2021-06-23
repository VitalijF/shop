package dao.impl;

import dao.RoleDao;
import lombok.SneakyThrows;
import model.Role;
import model.RoleName;
import util.QueryConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static util.MySQLConnector.CONNECTION;

public class MySqlRoleDao implements RoleDao {

    @Override
    @SneakyThrows
    public Role getById(int id) {
        try (PreparedStatement statement = CONNECTION.prepareStatement(QueryConstants.GET_ROLE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next()
                        ? getRoleFromResultSet(resultSet)
                        : null;
            }
        }
    }

    @SneakyThrows
    private Role getRoleFromResultSet(ResultSet resultSet) {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        role.setName(RoleName.getValue(resultSet.getString("id")));
        return role;
    }
}
