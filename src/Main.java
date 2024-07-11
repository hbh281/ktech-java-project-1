import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        TodoApp app = new TodoApp();

        while (true) {
            app.displayTodo();
            System.out.println("\n1. Create TODO\n2. Edit TODO\n3. Finish TODO\n4. Delete TODO\n5. Exit\n");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    app.createTodo();
                    break;
                case 2:
                    System.out.print("Enter the number of the TODO to edit: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    app.editTodo(editIndex);
                    break;
                case 3:
                    app.finishTodo();
                    break;
                case 4:
                    app.deleteTodo();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Please input the right number");
            }
        }
    }
}
