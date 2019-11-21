import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * This class is the class that handles the GUI
 */
public class CreateWindow {

    /*
      Creates the home window GUI
     */
    public static void homeWindow(){
        //frame that will be used for the home window
        JFrame frame = new JFrame("Humanitarian Resource Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setLocationRelativeTo(null);

        //array of operations that user can perform
        String[] operationStrings = {"Insert", "Update", "Delete"};
        //array of resources the user can operate on
        String[] resourcesStrings = {"Medical Center", "Water", "Food"};

        //panel that elements for the display will be added to
        JPanel panel = new JPanel();

        //The following are GUI elements that get added to the display
        JLabel label = new JLabel("Select an operation and resource type");
        JButton button = new JButton("Continue");
        JComboBox operations = new JComboBox(operationStrings);
        JComboBox resources = new JComboBox(resourcesStrings);

        panel.add(operations);
        panel.add(resources);

        //A method for handling when the user presses the continue button
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                if(operations.getSelectedItem().equals("Insert")) {
                    //user wants to insert
                    insertHumRes(resources.getSelectedItem().toString());
                }
                else {
                    //user wants to update/delete so we need to get the id
                    getIdFrame(operations.getSelectedItem().toString(), resources.getSelectedItem().toString());
                }
            }
        });

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.NORTH, label);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        frame.setVisible(true);
    }

    /**
     * This is the main window for the user. It displays the information for
     * updating or inserting an entry
     * @param hrId the HRID of the element the user is operating on
     * @param operation the operation the user is performing
     * @param resource the resource that the user is operating on
     */
    private static void updateOrInsert(int hrId, String operation, String resource){
        //the frame that will display the window
        JFrame newFrame = new JFrame("Operation");
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(600,200);
        newFrame.setLocationRelativeTo(null);

        //The following are elements of the display
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please enter the " + resource.toLowerCase() + " information");
        JButton submit = new JButton("Submit");

        if(resource.equals("Water")) {
            //user wants to insert water
            if(operation.equals("Insert")){
                //the following are elements of the display
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
                        newFrame.dispose();
                        //gets the number of 10oz bottles that the user input
                        int numOf10Bottles = Integer.parseInt(bottles.getText());
                        //gets the number of half litter bottles that the user input
                        int numOfHalfLitter = Integer.parseInt(halfLiterBottles.getText());
                        //gets the number of 5 gallon water bottles that the user input
                        int numOf5Gal = Integer.parseInt(jugs.getText());

                        //a water object that is used to insert a water
                        Water water = new Water();
                        water.insert(hrId, numOf10Bottles, numOfHalfLitter, numOf5Gal);
                        endFrame();
                    }
                });
            }
            //user wants to update water
            else if(operation.equals("Update")){
                //the following are elements that are added to the display
                JPanel input = new JPanel(new GridLayout(3,2));
                JLabel label1 = new JLabel("Number of 10oz bottles:");
                JLabel label2 = new JLabel("Number of half-liter bottles:");
                JLabel label3 = new JLabel("Number of 5gal jugs:");

                //a water object that is used to get the information about a water object
                Water water = new Water();
                //an array with the current information for a specific water object in the DB
                String[] results = water.displayHRID(hrId);

                //the following are elements of the display. results[x] is equal to what is
                //stored in the database for that element
                JTextField bottles = new JTextField(results[1]);
                JTextField halfLiterBottles = new JTextField(results[2]);
                JTextField jugs = new JTextField(results[3]);

                input.add(label1);
                input.add(bottles);

                input.add(label2);
                input.add(halfLiterBottles);

                input.add(label3);
                input.add(jugs);

                panel.add(input, BorderLayout.CENTER);

                //An action listener for when the user wants to update
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        newFrame.dispose();
                        //number of 10oz bottles the user wants to input
                        int numOf10Bottles = Integer.parseInt(bottles.getText());
                        //number of half litter bottles the user wants to input
                        int numOfHalfLitter = Integer.parseInt(halfLiterBottles.getText());
                        //number of 5 gallon bottles the user wants to input
                        int numOf5Gal = Integer.parseInt(jugs.getText());

                        //update the water with the values the user input
                        water.update(hrId, numOf10Bottles, numOfHalfLitter, numOf5Gal);
                        endFrame();
                    }
                });
            }
        }

        else if(resource.equals("Food")) {
            //user wants to insert a food
            if(operation.equals("Insert")){
                //the following are elements of the display
                JPanel input = new JPanel(new GridLayout(3,2));
                JLabel foodType = new JLabel("Food type:");
                JLabel numberOfMeals = new JLabel("Number of meals available:");
                JLabel description = new JLabel("Description");
                JTextField type = new JTextField();
                JTextField meals = new JTextField();
                JTextField desc = new JTextField();

                input.add(foodType);
                input.add(type);

                input.add(numberOfMeals);
                input.add(meals);

                input.add(description);
                input.add(desc);

                panel.add(input, BorderLayout.CENTER);

                //a method for when the user wants to insert their food information
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        newFrame.dispose();
                        //gets the number of meals that the user wants to input
                        int numOfMeals = Integer.parseInt(meals.getText());
                        //make an array with the type of food and food description
                        String[] values = new String[]{type.getText(), desc.getText()};
                        //check values for ' and update them with ''
                        values = checkForApostrophes(values);
                        //food object used to insert food
                        Food food = new Food();
                        //insert the food item
                        food.insert(hrId, values[0], numOfMeals, values[1]);
                        //display the ending frame
                        endFrame();
                    }
                });
            }
            else if(operation.equals("Update")){
                //user wants to update a food item
                //the following are elements of the display
                JPanel input = new JPanel(new GridLayout(3,2));
                JLabel foodType = new JLabel("Food type:");
                JLabel numberOfMeals = new JLabel("Number of meals available:");
                JLabel description = new JLabel("Description");

                //a food object used to get the current data for a food item in the DB
                Food food = new Food();
                //an array that holds the data that is in the DB for the food object
                //that the user wants to update
                String[] results = food.displayHRID(hrId);

                //the following are elements of the display. results[x] is what is currently
                //in the DB for that food object
                JTextField type = new JTextField(results[1]);
                JTextField meals = new JTextField(results[2]);
                JTextField desc = new JTextField(results[3]);

                input.add(foodType);
                input.add(type);

                input.add(numberOfMeals);
                input.add(meals);

                input.add(description);
                input.add(desc);

                panel.add(input, BorderLayout.CENTER);

                //a method for handling when a user wants to update a specific food item
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        newFrame.dispose();
                        //gets the number of meals that the user wants to input
                        int numOfMeals = Integer.parseInt(meals.getText());
                        //make an array with the type of food and food description
                        String[] values = new String[]{type.getText(), desc.getText()};
                        //check values for ' and update them with ''
                        values = checkForApostrophes(values);

                        //a food object is used to update food
                        Food food = new Food();
                        //update the food item
                        food.update(hrId, values[0], numOfMeals, values[1]);
                        endFrame();
                    }
                });
            }
        }
        else {
            //user wants to insert a medical center
            if(operation.equals("Insert")){
                //the following are elements of the display
                JPanel input = new JPanel(new GridLayout(4,2));
                JLabel numberOfBeds = new JLabel("Number of beds");
                JLabel erRoomCapacity = new JLabel("Emergency room capacity");
                JLabel numberOfDoctors = new JLabel("Number of doctors");
                JLabel numberOfNurses = new JLabel("Number of nurses");
                JTextField beds = new JTextField("", 5);
                JTextField roomCap = new JTextField();
                JTextField doctors = new JTextField();
                JTextField nurses = new JTextField();

                input.add(numberOfBeds);
                input.add(beds);

                input.add(erRoomCapacity);
                input.add(roomCap);

                input.add(numberOfDoctors);
                input.add(doctors);

                input.add(numberOfNurses);
                input.add(nurses);

                panel.add(input, BorderLayout.CENTER);

                //a method for when the user wants to insert a medical center
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        newFrame.dispose();
                        //gets the number of beds the user wants to insert
                        int numOfBeds = Integer.parseInt(beds.getText());
                        //gets the room capacity the user wants to insert
                        int roomCapacity = Integer.parseInt(roomCap.getText());
                        //gets the number of doctors the user wants to insert
                        int numOfdoctors = Integer.parseInt(doctors.getText());
                        //gets the number of nurses the user wants to insert
                        int numOfNurses = Integer.parseInt(nurses.getText());

                        //a medical center object that is used to insert a medical center
                        MedicalCenter medicalCenter = new MedicalCenter();
                        //insert the medical center
                        medicalCenter.insert(hrId, numOfBeds, roomCapacity, numOfdoctors, numOfNurses);
                        endFrame();
                    }
                });
            }
            //user wants to update a medical center
            else if(operation.equals("Update")){
                //the following are elements of the display
                JPanel input = new JPanel(new GridLayout(4,2));
                JLabel numberOfBeds = new JLabel("Number of beds");
                JLabel emergencyRoomCapacity = new JLabel("Emergency room capacity");
                JLabel numberOfDoctors = new JLabel("Number of doctors");
                JLabel numberOfNurses = new JLabel("Number of nurses");

                // a medical center object for getting the information that is currently
                // in the DB for a medical center object
                MedicalCenter medicalCenter = new MedicalCenter();
                //an array containing the information that is currently in the DB for
                //the medical center the user wants to update
                String[] results = medicalCenter.displayHRID(hrId);

                //elements that the user enters information into. results[x] is what is
                //currently stored in the DB
                JTextField beds = new JTextField(results[1]);
                JTextField roomCap = new JTextField(results[2]);
                JTextField doctors = new JTextField(results[3]);
                JTextField nurses = new JTextField(results[4]);

                input.add(numberOfBeds);
                input.add(beds);

                input.add(emergencyRoomCapacity);
                input.add(roomCap);

                input.add(numberOfDoctors);
                input.add(doctors);

                input.add(numberOfNurses);
                input.add(nurses);

                panel.add(input, BorderLayout.CENTER);

                //a method for when the user wants to update the medical center
                submit.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        newFrame.dispose();
                        //gets the number of beds the user wants to input
                        int numOfBeds = Integer.parseInt(beds.getText());
                        //gets the room capacity the user wants to input
                        int roomCapacity = Integer.parseInt(roomCap.getText());
                        //gets the number of doctors the user wants to input
                        int numOfdoctors = Integer.parseInt(doctors.getText());
                        //gets the number of nurses the user wants to input
                        int numOfNurses = Integer.parseInt(nurses.getText());

                        //a medical center object that is used to update
                        MedicalCenter medicalCenter = new MedicalCenter();
                        //update the medical center
                        medicalCenter.update(hrId, numOfBeds, roomCapacity, numOfdoctors, numOfNurses);
                        endFrame();
                    }
                });
            }
        }
        panel.add(label, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);
        newFrame.getContentPane().add(panel);
        newFrame.setVisible(true);
    }

    /**
     * Displays the screen to insert a humanitarian resource
     * @param resource the resource the user wants to insert
     */
    private static void insertHumRes(String resource){
        //the frame that will be displayed
        JFrame humResFrame = new JFrame("Humanitarian Resource");
        humResFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        humResFrame.setSize(600,400);
        humResFrame.setLocationRelativeTo(null);

        //the following are elements of the display
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

        //the following are text fields the user will enter info into
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

        //a method for when the user wants to submit their information
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String[] values = new String[] {hrName.getText(), hrAddress.getText(), hrPhone.getText(),
                        hrType.getText(), hrDesc.getText(), hrHours.getText()};
                //check if any of the values the user inserted have a '
                values = checkForApostrophes(values);
                //a humanitarian resource object used to insert one
                HumResource hr = new HumResource();
                //inserts a humanitarian resource. the value returned is the humanitarian resource's id
                int hrID = hr.insert(values[0], values[1], values[2], Double.parseDouble(hrLat.getText()),
                       Double.parseDouble(hrLon.getText()), values[3], values[4], values[5]);
                humResFrame.dispose();
                updateOrInsert(hrID, "Insert", resource);
            }
        });

        panel.add(label, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);
        humResFrame.getContentPane().add(panel);
        humResFrame.setVisible(true);
    }

    /**
     * A frame to get the id of the resource the user wants to operate on
     * @param operation the operation that the user wants to use
     * @param resource the type of resource the user wants to act on
     */
    private static void getIdFrame(String operation, String resource){
        //the frame that is displayed
        JFrame getIdFrame = new JFrame("Humanitarian Resource");
        getIdFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getIdFrame.setSize(300,200);
        getIdFrame.setLocationRelativeTo(null);

        //the following are elements of the display
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Please select the " + resource + " HRID");
        JButton submit = new JButton("Submit");

        //a call is made to getIDArray. this returns all the possible ids for a specific resource.
        //the ids are added to a dropdown menu for the user to select from.
        JComboBox idDisplay = new JComboBox(getIDArray(resource));

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(idDisplay, BorderLayout.CENTER);
        panel.add(submit, BorderLayout.SOUTH);

        //a method for when the user has selected the id
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                getIdFrame.dispose();
                //gets the id the user selected
                int hrID = Integer.parseInt(idDisplay.getSelectedItem().toString());
                if(operation.equals("Update")){
                    //go to the update frame
                    updateHRFrame(hrID, resource);
                }
                else {
                    //delete resource
                    deleteResource(resource, hrID);
                }
            }
        });
        getIdFrame.getContentPane().add(panel);
        getIdFrame.setVisible(true);
    }

    /**
     * The display for updating a humanitarian resource
     * @param hrID the hrid of the resource to be updated
     * @param resource the resource the user wants to update
     */
    private static void updateHRFrame(int hrID, String resource) {
        //the frame for the display
        JFrame updateHRFrame = new JFrame("Humanitarian Resource");
        updateHRFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateHRFrame.setSize(600, 400);
        updateHRFrame.setLocationRelativeTo(null);

        //the following are elements of the display
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Please update the Humanitarian Resource information");
        JButton submit = new JButton("Submit");

        //the following are elements of the display
        JPanel input = new JPanel(new GridLayout(8, 2));
        JLabel name = new JLabel("Humanitarian Resource Name");
        JLabel address = new JLabel("Address");
        JLabel phone = new JLabel("Phone Number");
        JLabel lat = new JLabel("Latitude");
        JLabel lon = new JLabel("Longitude");
        JLabel desc = new JLabel("Description");
        JLabel hours = new JLabel("Hours Open");

        //a humanitarian resource object used to get the data currently in the DB
        HumResource humResource = new HumResource();
        //an array of the current data in the DB for that HR
        String[] results = humResource.displayHRID("HumResource", hrID);

        //The following are elements of the display. results[x] is data that is
        //stored in the DB for that attribute
        JTextField hrName = new JTextField(results[1]);
        JTextField hrAddress = new JTextField(results[2]);
        JTextField hrPhone = new JTextField(results[3]);
        JTextField hrLat = new JTextField(results[4]);
        JTextField hrLon = new JTextField(results[5]);
        JTextField hrDesc = new JTextField(results[7]);
        JTextField hrHours = new JTextField(results[8]);

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

        input.add(desc);
        input.add(hrDesc);

        input.add(hours);
        input.add(hrHours);

        panel.add(input, BorderLayout.CENTER);

        //a method to handle when the user wants to update the HR
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //a humanitarian resource object used to update
                HumResource hr = new HumResource();

                //make an array with the values the user entered
                String[] values = new String[] {hrName.getText(), hrAddress.getText(), hrPhone.getText(),
                        results[6], hrDesc.getText(), hrHours.getText()};

                //check values for ' and update them with ''
                values = checkForApostrophes(values);
                //update humanitarian resource
                hr.update(hrID, values[0], values[1], values[2], Double.parseDouble(hrLat.getText()),
                        Double.parseDouble(hrLon.getText()), values[3], values[4], values[5]);
                updateHRFrame.dispose();
                updateOrInsert(hrID, "Update", resource);
            }
        });

        panel.add(label, BorderLayout.NORTH);
        panel.add(submit, BorderLayout.SOUTH);
        updateHRFrame.getContentPane().add(panel);
        updateHRFrame.setVisible(true);
    }

    /**
     * A method to delete a resource
     * @param resource the type of resource to be deleted
     * @param hrId the hrId of the resource to be deleted
     */
    private static void deleteResource(String resource, int hrId){
        if(resource.equals("Medical Center")) {
            //user wants to delete a medical center
            //a medical center object used to delete a medical center
            MedicalCenter medicalCenter = new MedicalCenter();
            //delete the medical center
            medicalCenter.delete(hrId);
            endFrame();
        }
        else if (resource.equals("Food")) {
            //user wants to delete a food item
            //a food object that is used to delete a food object
            Food food = new Food();
            //delete the food object
            food.delete(hrId);
            endFrame();
        }
        else {
            //user wants to delete water
            //a water object that is used to delete a water
            Water water = new Water();
            //delete the water
            water.delete(hrId);
            //display the ending frame
            endFrame();
        }
    }


    /**
     * Create the frame that the user sees when their operation has ended
     */
    private static void endFrame(){
        // Creating the frame the user sees
        JFrame endFrame = new JFrame("End Frame");
        endFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        endFrame.setSize(600,400);
        endFrame.setLocationRelativeTo(null);

        // The following are elements of the display
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Success! Your operation is finished!");
        JButton home = new JButton("Home");
        JButton quit = new JButton("Quit");

        panel.add(home);
        panel.add(quit);

        // Action Listener to to remove the current frame and go to home screen
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                endFrame.dispose();
                homeWindow();
            }
        });

        // Action Listener to remove the current frame and end the program
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                endFrame.dispose();
                return;
            }
        });

        endFrame.getContentPane().add(BorderLayout.NORTH, label);
        endFrame.getContentPane().add(BorderLayout.CENTER, panel);
        endFrame.setVisible(true);
    }

    /**
     * A helper method to change a ' to a '' so there isn't a DB error
     * @param values the values to be checked
     * @return an array that has strings that can be inserted into the DB
     */
    private static String[] checkForApostrophes(String[] values){
        //loop through array
        for(int i = 0; i < values.length; i++) {
            //replace ' with ''
            values[i] = values[i].replace("'", "''");
        }
        return values;
    }

    /**
     * A helper method to get an array of all the ids for a certain resource.
     * This is useful because it is used to allow users to only select and id
     * from the resource that they selected
     * @param resource the resource the user selected
     * @return an array of strings with the ids
     */
    public static String[] getIDArray(String resource) {
        if(resource.equals("Medical Center")){
            //medical center object used to get the ids
            MedicalCenter medicalCenter = new MedicalCenter();
            //returns all medical center ids in an array
            return medicalCenter.displayAllHRIDs();
        }
        else if(resource.equals("Water")){
            //water object used to get the ids
            Water water = new Water();
            //returns all water ids in an array
            return water.displayAllHRIDs();
        }
        else{
            //food object used to get the ids
            Food food = new Food();
            //returns all the food ids in an array
            return food.displayAllHRIDs();
        }
    }
}
