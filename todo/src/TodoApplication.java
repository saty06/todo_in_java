
import service.TodoService;
import util.InputUtil;

import java.sql.SQLException;

public class TodoApplication {
    public static void main(String[] args) {
        TodoService todoService = new TodoService();

        while (true) {
            System.out.println("\n=== To-Do Application ===");
            System.out.println("1. Add Todo");
            System.out.println("2. View Todos");
            System.out.println("3. Mark Todo as Completed");
            System.out.println("4. Delete Todo");
            System.out.println("5. Exit");

            int choice = InputUtil.readInt("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        String title = InputUtil.readString("Enter title: ");
                        String description = InputUtil.readString("Enter description: ");
                        todoService.addTodo(title, description);
                        break;
                    case 2:
                        todoService.getTodos().forEach(todo -> System.out.println(todo.getId() + ": " + todo.getTitle()
                                + " - " + (todo.isCompleted() ? "Completed" : "Pending")));
                        break;
                    case 3:
                        int idToComplete = InputUtil.readInt("Enter ID to mark as completed: ");
                        todoService.markTodoAsCompleted(idToComplete);
                        break;
                    case 4:
                        int idToDelete = InputUtil.readInt("Enter ID to delete: ");
                        todoService.deleteTodoById(idToDelete);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    }
}
