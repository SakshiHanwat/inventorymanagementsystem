package com.inventory.config;

public class DBConfig {
    public static final String URL      = "jdbc:mysql://localhost:3306/inventory_db?useSSL=false&serverTimezone=UTC";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";   // ← Change this
    public static final String DRIVER   = "com.mysql.cj.jdbc.Driver";
}