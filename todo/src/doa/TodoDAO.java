package doa;

import config.config;
import model.todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {
    public void addTodo(todo todo) throws SQLException {
        String query = "INSERT INTO todos (title, description, is_completed) VALUES (?, ?, ?)";
        try (Connection connection = config.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setBoolean(3, todo.isCompleted());
            preparedStatement.executeUpdate();
        }
    }

    public List<todo> getTodos() throws SQLException {
        List<todo> todos = new ArrayList<>();
        String query = "SELECT * FROM todos";
        try (Connection connection = config.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                todo todo = new todo(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("is_completed"));
                todos.add(todo);
            }
        }
        return todos;
    }

    public void updateTodoStatus(int id, boolean isCompleted) throws SQLException {
        String query = "UPDATE todos SET is_completed = ? WHERE id = ?";
        try (Connection connection = config.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, isCompleted);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteTodoById(int id) throws SQLException {
        String query = "DELETE FROM todos WHERE id = ?";
        try (Connection connection = config.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
