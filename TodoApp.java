import java.io.*;
import java.util.*;

public class TodoApp {

    private static final String FILE_NAME = "todo_list.txt";
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- ToDoメニュー ---");
            System.out.println("1. タスクを見る");
            System.out.println("2. タスクを追加する");
            System.out.println("3. タスクを削除する");
            System.out.println("4. 終了する");
            System.out.print("番号を選んでください: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showTasks();
                    break;
                case "2":
                    addTask(scanner);
                    break;
                case "3":
                    deleteTask(scanner);
                    break;
                case "4":
                    saveTasks();
                    System.out.println("👋 アプリを終了します。おつかれさま！");
                    return;
                default:
                    System.out.println("⚠️ 無効な選択です。");
            }
        }
    }

    private static void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // 初回起動時はファイルがないためエラーを無視
        }
    }

    private static void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.println(task);
            }
        } catch (IOException e) {
            System.out.println("❌ タスクの保存中にエラーが発生しました。");
        }
    }

    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("📭 タスクはありません。");
            return;
        }
        System.out.println("📋 現在のタスク:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("📝 追加するタスクを入力: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("✅ タスクを追加しました！");
    }

    private static void deleteTask(Scanner scanner) {
        showTasks();
        System.out.print("❌ 削除するタスク番号を入力: ");
        String input = scanner.nextLine();
        try {
            int num = Integer.parseInt(input);
            if (num >= 1 && num <= tasks.size()) {
                String removed = tasks.remove(num - 1);
                System.out.println("🗑️ '" + removed + "' を削除しました！");
            } else {
                System.out.println("⚠️ 無効な番号です。");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ 数字を入力してください。");
        }
    }
}
