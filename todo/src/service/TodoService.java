package service;

import doa.TodoDAO;
import model.todo;

import java.sql.SQLException;
import java.util.List;

public class TodoService {
    private final TodoDAO todoDAO = new TodoDAO();

    public void addTodo(String title, String description) throws SQLException {
        todo todo = new todo(0, title, description, false);
        todoDAO.addTodo(todo);
    }

    public List<todo> getTodos() throws SQLException {
        return todoDAO.getTodos();
    }

    public void markTodoAsCompleted(int id) throws SQLException {
        todoDAO.updateTodoStatus(id, true);
    }

    public void deleteTodoById(int id) throws SQLException {
        todoDAO.deleteTodoById(id);
    }
}
