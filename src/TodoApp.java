import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoApp {
    private List<Todo> todos;

    public TodoApp() {
        todos = readData(); // Initialize todos by reading from file
    }

    public void displayTodo() {
        int count = 0;
        if (todos.isEmpty()) {
            System.out.println("You have no more TODOs left!!!");
            return;
        }
        for (Todo todo : todos) {
            if (!todo.getTitle().contains("(Done)")) {
                count++;
            }
        }
        System.out.println(String.format("You have %d TODOs left.", count));
        for (int i = 0; i < todos.size(); i++) {
            System.out.println(String.format("%d. %s", (i + 1), todos.get(i).getTitle()));
        }
    }

    public void createTodo() throws IOException {
        userInput();
        writeData(todos);
    }

    public void editTodo(int index) {
        if (index < 1 || index > todos.size()) {
            System.out.println("Invalid TODO number.");
            return;
        }
        Todo todo = todos.get(index - 1);

        try {
            System.out.print("Enter new title: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String newTitle = reader.readLine();
            System.out.print("Enter new deadline (YYYY-MM-DD): ");
            String newDeadline = reader.readLine();
            LocalDate newDate = LocalDate.parse(newDeadline);

            todo.setTitle(newTitle);
            todo.setDeadline(newDate);

            writeData(todos);
            System.out.println("Edit Saved!!");
        } catch (IOException e) {
            System.out.println("Error editing TODO: " + e.getMessage());
        }
    }

    public void finishTodo() {
        System.out.print("Enter the number of the TODO to finish: ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int index = Integer.parseInt(reader.readLine());
            if (index < 1 || index > todos.size()) {
                System.out.println("Invalid TODO number.");
                return;
            }
            Todo todo = todos.get(index - 1);
            todo.setTitle(todo.getTitle() + " (Done)");
            writeData(todos);
            System.out.println("TODO marked as finished.");
        } catch (IOException e) {
            System.out.println("Error finishing TODO: " + e.getMessage());
        }
    }

    public void deleteTodo() {
        System.out.print("Enter the number of the TODO to delete: ");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int index = Integer.parseInt(reader.readLine());
            if (index < 1 || index > todos.size()) {
                System.out.println("Invalid TODO number.");
                return;
            }
            todos.remove(index - 1);
            writeData(todos);
            System.out.println("TODO deleted.");
        } catch (IOException e) {
            System.out.println("Error deleting TODO: " + e.getMessage());
        }
    }

    public void userInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("title: ");
                String title = reader.readLine();
                System.out.print("Until (YYYY-MM-DD): ");
                String deadline = reader.readLine();
                LocalDate date = LocalDate.parse(deadline);
                todos.add(new Todo(
                        title,
                        date
                ));
                System.out.println("Saved!!!");
                break;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    public void writeData(List<Todo> todoList) {
        try (FileWriter fileWriter = new FileWriter("todo.csv");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Todo todo : todoList) {
                StringBuilder todos = new StringBuilder();
                todos.append(todo.getTitle()).append(',');
                todos.append(todo.getDeadline());
                String line = todos.toString();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Todo> readData() {
        List<Todo> todoList = new ArrayList<>();
        File file = new File("todo.csv");


        if (!file.exists()) {
            return todoList;
        }

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] value = line.split(",");
                if (value.length != 2) {

                    continue;
                }
                LocalDate date = LocalDate.parse(value[1]);
                todoList.add(new Todo(
                        value[0],
                        date
                ));
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return todoList;
    }
}
