package model;

public enum RoleName {
    USER, MANAGER;

    public static RoleName getValue(String name) {
        return valueOf(name.toUpperCase());
    }
}
