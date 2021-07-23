package ru.job4j.grabber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
            cnn = DriverManager.getConnection(
                    cfg.getProperty("url"),
                    cfg.getProperty("username"),
                    cfg.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(Post post) {
        String sql = "INSERT INTO grabber (name, text, link, created) values (?, ?, ?, ?);";
        try {
            PreparedStatement statement = cnn.prepareStatement(sql);
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getDescription());
            statement.setString(3, post.getLink());
            statement.setTimestamp(4, Timestamp.valueOf(post.getCreated()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        String sql = "SELECT * FROM grabber";
        try {
            PreparedStatement statement = cnn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Post post = new Post(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("link"),
                        rs.getString("text"),
                        rs.getTimestamp("created").toLocalDateTime());
                rsl.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rsl;
    }

    @Override
    public Post findById(int id) {
        Post post = new Post();
        String sql = "SELECT * FROM grabber where id = ?;";
        try {
            PreparedStatement statement = cnn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            post.setId(id);
            post.setTitle(resultSet.getString("name"));
            post.setDescription(resultSet.getString("text"));
            post.setLink(resultSet.getString("link"));
            post.setCreated(resultSet.getTimestamp("created").toLocalDateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}