import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateWindow {
    public static void main(String args[]){
        homeWindow();
    }

    private static void homeWindow(){
        JFrame frame = new JFrame("Humanitarian Resource Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        String[] operationStrings = {"Insert", "Update", "Delete"};
        String[] resourcesStrings = {"Medical Center", "Water", "Food"};

        JPanel panel = new JPanel();

        JLabel label = new JLabel("Select an operation and resource type");
        JButton button = new JButton("Continue");

        JComboBox operations = new JComboBox(operationStrings);
        JComboBox resources = new JComboBox(resourcesStrings);
        panel.add(operations);
        panel.add(resources);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                newWindow(operations.getSelectedItem().toString(), resources.getSelectedItem().toString());

            }
        });

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, label);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        frame.setVisible(true);
    }

    private static void newWindow(String operation, String resource){
        JFrame newFrame = new JFrame("New Window");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(300,300);

        JLabel label = new JLabel(operation);
        JLabel label2 = new JLabel(resource);
        newFrame.getContentPane().add(BorderLayout.NORTH, label);
        newFrame.getContentPane().add(BorderLayout.SOUTH, label2);

        newFrame.setVisible(true);
    }
}
