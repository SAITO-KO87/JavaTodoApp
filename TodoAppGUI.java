import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoAppGUI {
    private JFrame frame;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField inputField;
    private ArrayList<String> tasks = new ArrayList<>();

    public TodoAppGUI() {
        frame = new JFrame("ToDoアプリ");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // タスクリスト
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // 入力欄と追加ボタン
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(20);
        JButton addButton = new JButton("追加");
        addButton.addActionListener(e -> addTask());
        inputPanel.add(inputField);
        inputPanel.add(addButton);

        // 削除ボタン
        JButton deleteButton = new JButton("選択したタスクを削除");
        deleteButton.addActionListener(e -> deleteTask());

        // パネルに追加
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(deleteButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addTask() {
        String task = inputField.getText().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            taskListModel.addElement(task);
            inputField.setText("");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            tasks.remove(selectedIndex);
            taskListModel.remove(selectedIndex);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoAppGUI::new);
    }
}
