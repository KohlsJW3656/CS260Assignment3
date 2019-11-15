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
        frame.setLocationRelativeTo(null);

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
                if(operations.getSelectedItem().equals("Insert")) {
                    insertHumRes(resources.getSelectedItem().toString());
                }
                else {
                    getIdFrame(operations.getSelectedItem().toString(), resources.getSelectedItem().toString());
                }
            }
        });

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, label);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        frame.setVisible(true);
    }

    private static void newWindow(int hrId, String operation, String resource){
        JFrame newFrame = new JFrame("New Window");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(600,200);
        newFrame.setLocationRelativeTo(null);

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
                        water.insert(-1,numOf10Bottles, numOfHalfLitter, numOf5Gal);
                    }
                });
            }
            else if(operation.equals("Update")){
                Water water = new Water();
                //water.update();
            }
            else {
                Water water = new Water();
                //water.delete();
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

                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String typeOfFood = type.getText();
                        int numOfMeals = Integer.parseInt(meals.getText());
                        String description = desc.getText();

                        Food food = new Food();
                        food.insert(-1, typeOfFood, numOfMeals, description);
                    }
                });


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

                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        int numOfBeds = Integer.parseInt(beds.getText());
                        int roomCapacity = Integer.parseInt(roomCap.getText());
                        int numOfdoctors = Integer.parseInt(doctors.getText());
                        int numOfNurses = Integer.parseInt(nurses.getText());

                        MedicalCenter medicalCenter = new MedicalCenter();
                        medicalCenter.insert(-1, numOfBeds, roomCapacity, numOfdoctors, numOfNurses);
                    }
                });
            }
            else if(operation.equals("Update")){
                MedicalCenter medicalCenter = new MedicalCenter();
                //medicalCenter.update();
            }
            else {
                MedicalCenter medicalCenter = new MedicalCenter();
                //medicalCenter.delete();
            }
        }

        panel.add(label, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);
        newFrame.getContentPane().add(panel);
        newFrame.setVisible(true);
    }

    private static void insertHumRes(String resource){
        JFrame humResFrame = new JFrame("Humanitarian Resource");
        humResFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        humResFrame.setSize(600,400);
        humResFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please enter the Humanitarian Resource information");
        JButton submit = new JButton("Submit");

        JPanel input = new JPanel(new GridLayout(8,2));
        JLabel name = new JLabel("Humanitarian Resource Name");
        JLabel address = new JLabel("Address");
        JLabel phone = new JLabel("Phone Number");
        JLabel lat = new JLabel("Latitude");
        JLabel lon = new JLabel("Longitude");
        JLabel type = new JLabel("Type");
        JLabel desc = new JLabel("Description");
        JLabel hours = new JLabel("Hours Open");

        JTextField hrName = new JTextField();
        JTextField hrAddress = new JTextField();
        JTextField hrPhone = new JTextField();
        JTextField hrLat = new JTextField();
        JTextField hrLon = new JTextField();
        JTextField hrType = new JTextField();
        JTextField hrDesc = new JTextField();
        JTextField hrHours = new JTextField();

        input.add(name);
        input.add(hrName);

        input.add(address);
        input.add(hrAddress);

        input.add(phone);
        input.add(hrPhone);

        input.add(lat);
        input.add(hrLat);

        input.add(lon);
        input.add(hrLon);

        input.add(type);
        input.add(hrType);

        input.add(desc);
        input.add(hrDesc);

        input.add(hours);
        input.add(hrHours);

        panel.add(input, BorderLayout.CENTER);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                HumResource hr = new HumResource();
                hr.insert(hrName.getText(), hrAddress.getText(), hrPhone.getText(), hrLat.getText(),
                       hrLon.getText(), hrType.getText(), hrDesc.getText(), hrHours.getText());
                humResFrame.dispose();
                newWindow(-1, "Insert", resource);
            }
        });

        panel.add(label, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);
        humResFrame.getContentPane().add(panel);
        humResFrame.setVisible(true);
    }

    private static void getIdFrame(String operation, String resource){
        JFrame getIdFrame = new JFrame("Humanitarian Resource");
        getIdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getIdFrame.setSize(600,400);
        getIdFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please enter the HRID");
        JTextField id = new JTextField();
        JButton submit = new JButton("Submit");

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(id, BorderLayout.CENTER);
        panel.add(submit, BorderLayout.SOUTH);

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                getIdFrame.dispose();
                int hrID = Integer.parseInt(id.getText());
                newWindow(hrID, operation, resource);
            }
        });

        getIdFrame.getContentPane().add(panel);
        getIdFrame.setVisible(true);

    }
}
