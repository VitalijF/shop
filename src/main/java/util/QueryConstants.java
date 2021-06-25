package util;

public final class QueryConstants {

    //USER
    public static final String GET_ALL_USERS = "SELECT * FROM user";
    public static final String GET_USER = "SELECT * FROM user WHERE id = ?";
    public static final String CREATE_USER = "INSERT INTO user (id, email, password, first_name, last_name, role_id) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER = "UPDATE user SET email = ?, password = ? , first_name = ?, last_name = ? WHERE id = ?";

    //PRODUCT
    public static final String GET_ALL_PRODUCTS = "SELECT * FROM product";
    public static final String GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
    public static final String CREATE_PRODUCT = "INSERT INTO product (id, name, description, price, quantity, category_id) VALUES (?, ?, ?, ?, ?, ?)";

    //BUCKET
    public static final String GET_BUCKET_BY_ID = "SELECT * FROM bucket WHERE id = ?";
    public static final String CREATE_BUCKET = "INSERT INTO bucket (id, created_date) VALUES (?, ?)";

    //ROLE
    public static final String GET_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?";

    //CATEGORY
    public static final String GET_CATEGORY_BY_ID = "SELECT * FROM category WHERE id = ?";

}
