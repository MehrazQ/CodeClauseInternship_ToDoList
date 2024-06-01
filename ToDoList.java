import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ToDoList extends JFrame {
    private List<Task> tasks;
    private JList<Task> taskList;
    private DefaultListModel<Task> listModel;

    public ToDoList() {
        setTitle("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        tasks = new ArrayList<>();
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Task");
        JButton editButton = new JButton("Edit Task");
        JButton removeButton = new JButton("Remove Task");

        addButton.addActionListener(e -> addTask());
        editButton.addActionListener(e -> editTask());
        removeButton.addActionListener(e -> removeTask());

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void addTask() {
        String name = JOptionPane.showInputDialog(this, "Enter task name:");
        if (name != null && !name.isEmpty()) {
            Task task = new Task(name);
            tasks.add(task);
            listModel.addElement(task);
        }
    }

    private void editTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            Task selectedTask = tasks.get(selectedIndex);
            String newName = JOptionPane.showInputDialog(this, "Enter new task name:", selectedTask.getName());
            if (newName != null && !newName.isEmpty()) {
                selectedTask.setName(newName);
                listModel.set(selectedIndex, selectedTask);
            }
        }
    }

    private void removeTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the selected task?");
            if (confirm == JOptionPane.YES_OPTION) {
                Task removedTask = tasks.remove(selectedIndex);
                listModel.remove(selectedIndex);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoList toDoList = new ToDoList();
            toDoList.setVisible(true);
        });
    }
}

class Task {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}   