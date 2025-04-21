import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TodoAppWithDate {
    private JFrame frame;
    private JPanel taskPanel;
    private JTextField inputField;
    private ArrayList<String> tasks;

    // 日付や時間の選択
    private JComboBox<String> yearComboBox, monthComboBox, dayComboBox;
    private JComboBox<String> hourComboBox, minuteComboBox;

    public TodoAppWithDate() {
        tasks = new ArrayList<>();
        frame = new JFrame("📝 タスク管理");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // タイトル
        JLabel title = new JLabel("📋 タスク一覧", SwingConstants.CENTER);
        title.setFont(new Font("Meiryo", Font.BOLD, 24));
        frame.add(title, BorderLayout.NORTH);

        // タスク表示エリア
        taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(scrollPane, BorderLayout.CENTER);

        // タスク追加フォーム
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        inputField = new JTextField(20);
        inputField.setFont(new Font("Meiryo", Font.PLAIN, 16));

        // 日付プルダウン設定
        yearComboBox = new JComboBox<>(new String[]{"2023", "2024", "2025"});
        monthComboBox = new JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
        dayComboBox = new JComboBox<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});

        // 時間プルダウン設定
        hourComboBox = new JComboBox<>(new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"});
        minuteComboBox = new JComboBox<>(new String[]{"00", "15", "30", "45"});

        // 追加ボタン
        JButton addButton = new JButton("➕ タスク追加");
        addButton.setFont(new Font("Meiryo", Font.BOLD, 16));
        addButton.addActionListener(e -> addTask());

        // パネルに追加
        inputPanel.add(inputField);
        inputPanel.add(new JLabel("年:"));
        inputPanel.add(yearComboBox);
        inputPanel.add(new JLabel("月:"));
        inputPanel.add(monthComboBox);
        inputPanel.add(new JLabel("日:"));
        inputPanel.add(dayComboBox);
        inputPanel.add(new JLabel("時間:"));
        inputPanel.add(hourComboBox);
        inputPanel.add(new JLabel("分:"));
        inputPanel.add(minuteComboBox);
        inputPanel.add(addButton);

        // タスク一覧の下に入力フォームを配置
        frame.add(inputPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void addTask() {
        String taskText = inputField.getText().trim();
        if (taskText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "⚠️ タスク内容を入力してください！");
            return;
        }

        String selectedYear = (String) yearComboBox.getSelectedItem();
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        String selectedDay = (String) dayComboBox.getSelectedItem();
        String selectedHour = (String) hourComboBox.getSelectedItem();
        String selectedMinute = (String) minuteComboBox.getSelectedItem();

        String deadline = selectedYear + "-" + selectedMonth + "-" + selectedDay + " " + selectedHour + ":" + selectedMinute;

        String fullTask = taskText + " (締切: " + deadline + ")";
        tasks.add(fullTask);
        inputField.setText("");
        updateTaskList();
    }

    private void updateTaskList() {
        taskPanel.removeAll();

        for (int i = 0; i < tasks.size(); i++) {
            String taskText = tasks.get(i);

            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JCheckBox checkBox = new JCheckBox(taskText);
            checkBox.setFont(new Font("Meiryo", Font.PLAIN, 16));

            JButton deleteButton = new JButton("❌");
            int finalI = i;
            deleteButton.addActionListener(e -> {
                tasks.remove(finalI);
                updateTaskList();
            });

            row.add(checkBox, BorderLayout.CENTER);
            row.add(deleteButton, BorderLayout.EAST);

            taskPanel.add(row);
        }

        taskPanel.revalidate();
        taskPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TodoAppWithDate::new);
    }
}
