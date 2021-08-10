package tdd;

import java.sql.*;

public class ConnectAndGet {
    String url;
    String login;
    String password;
    Connection connection;

    public ConnectAndGet(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public String getQuery(String sql) {
        Connection connection = getConnection();
        String rsl = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                rsl = rsl + id + " " + name + "\n";
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        ConnectAndGet cg = new ConnectAndGet("jdbc:postgresql://127.0.0.1/items",
                "postgres",
                "password");
        System.out.println(cg.getQuery("SELECT * FROM category"));
    }
}
