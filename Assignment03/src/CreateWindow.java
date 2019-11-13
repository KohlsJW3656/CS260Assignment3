import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateWindow {

    public void homeWindow(){
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
        newFrame.setSize(600,200);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please enter the information");
        JButton submit = new JButton("Submit");

        if(resource.equals("Water")) {
            if(operation.equals("Insert")){
                JPanel input = new JPanel(new GridLayout(3,2));
                JLabel label1 = new JLabel("Number of 10oz bottles:");
                JLabel label2 = new JLabel("Number of half-liter bottles:");
                JLabel label3 = new JLabel("Number of 5gal jugs:");
                JTextField bottles = new JTextField();
                JTextField halfLiterBottles = new JTextField();
                JTextField jugs = new JTextField();

                input.add(label1);
                input.add(bottles);

                input.add(label2);
                input.add(halfLiterBottles);

                input.add(label3);
                input.add(jugs);

                panel.add(input, BorderLayout.CENTER);

                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        int numOf10Bottles = Integer.parseInt(bottles.getText());
                        int numOfHalfLitter = Integer.parseInt(halfLiterBottles.getText());
                        int numOf5Gal = Integer.parseInt(jugs.getText());

                        Water water = new Water();
                        water.insert(numOf10Bottles, numOfHalfLitter, numOf5Gal);
                    }
                });

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
                JPanel input = new JPanel(new GridLayout(3,2));
                JLabel label1 = new JLabel("Food type:");
                JLabel label2 = new JLabel("Number of meals available:");
                JLabel label3 = new JLabel("Description");

                JTextField type = new JTextField();
                JTextField meals = new JTextField();
                JTextField desc = new JTextField();

                input.add(label1);
                input.add(type);

                input.add(label2);
                input.add(meals);

                input.add(label3);
                input.add(desc);

                panel.add(input, BorderLayout.CENTER);


                //Food food = new Food();
                //food.insert();
            }
            else if(operation.equals("Update")){
                //Food food = new Food();
                //food.update();
            }
            else {
                //Food food = new Food();
                //food.delete();
            }
        }

        else {
            if(operation.equals("Insert")){
                JPanel input = new JPanel(new GridLayout(4,2));
                JLabel label1 = new JLabel("Number of beds");
                JLabel label2 = new JLabel("Emergency room capacity");
                JLabel label3 = new JLabel("Number of doctors");
                JLabel label4 = new JLabel("Number of nurses");

                JTextField beds = new JTextField("", 5);
                JTextField roomCap = new JTextField();
                JTextField doctors = new JTextField();
                JTextField nurses = new JTextField();

                input.add(label1);
                input.add(beds);

                input.add(label2);
                input.add(roomCap);

                input.add(label3);
                input.add(doctors);

                input.add(label4);
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
