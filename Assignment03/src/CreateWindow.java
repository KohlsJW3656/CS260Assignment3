import javax.swing.*;
import javax.swing.border.Border;
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
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Please enter the information");
        JButton submit = new JButton("Submit");

        if(resource.equals("Water")) {
            if(operation.equals("Insert")){
                JPanel input = new JPanel();
                JTextField bottles = new JTextField("Number of 10oz bottles");
                JTextField halfLiterBottles = new JTextField("Number of half-liter bottles");
                JTextField jugs = new JTextField("Number of 5gal jugs");

                input.add(bottles);
                input.add(halfLiterBottles);
                input.add(jugs);

                panel.add(input, BorderLayout.CENTER);

                // Water water = new Water();
                //water.insert();
            }
            else if(operation.equals("Update")){
                Water water = new Water();
                water.update();
            }
            else {
                Water water = new Water();
                water.delete();
            }
        }

        else if(resource.equals("Food")) {
            if(operation.equals("Insert")){
                JPanel input = new JPanel();
                JTextField type = new JTextField("Food type");
                JTextField meals = new JTextField("Number of meals available");
                JTextField desc = new JTextField("Description");

                input.add(type);
                input.add(meals);
                input.add(desc);

                panel.add(input, BorderLayout.CENTER);


                //Food food = new Food();
                //food.insert();
            }
            else if(operation.equals("Update")){
                Food food = new Food();
                food.update();
            }
            else {
                Food food = new Food();
                food.delete();
            }
        }

        else {
            if(operation.equals("Insert")){
                JPanel input = new JPanel();
                JTextField beds = new JTextField("Number of beds");
                JTextField roomCap = new JTextField("Emergency room capacity");
                JTextField doctors = new JTextField("Number of doctors");
                JTextField nurses = new JTextField("Number of nurses");

                input.add(beds);
                input.add(roomCap);
                input.add(doctors);
                input.add(nurses);

                panel.add(input, BorderLayout.CENTER);

                //MedicalCenter medicalCenter = new MedicalCenter();
                //medicalCenter.insert();
            }
            else if(operation.equals("Update")){
                MedicalCenter medicalCenter = new MedicalCenter();
                medicalCenter.update();
            }
            else {
                MedicalCenter medicalCenter = new MedicalCenter();
                medicalCenter.delete();
            }
        }

        panel.add(label, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);
        newFrame.getContentPane().add(panel);
        newFrame.setVisible(true);
    }
}
