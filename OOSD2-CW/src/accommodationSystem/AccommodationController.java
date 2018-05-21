/*
 * Licensed to JGC
 */
package accommodationSystem;

/**
 *
 * @author Jacob
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class AccommodationController extends Application
{

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Hall> halls = new ArrayList<>();
    private ObservableList<RowView> rows = FXCollections.observableArrayList();
    private User currentUser;

    /**
     * Main Class
     *
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    private void populateStudents() //Adds some students
    {
        students.add(new Student("Thomas", "Maul", "83747403", new ContactDetails("07700900411", "Thomas@mail.com", new Address("SW14 8BE"))));
        students.get(0).getContactDetails().getAddress().addLine("31 Clare Lawn Ave");
        students.get(0).getContactDetails().getAddress().addLine("London");

        students.add(new Student("Chris", "Roadknight", "99412663", new ContactDetails("07700900417", "chris@mail.com", new Address("CA3 0EH"))));
        students.get(1).getContactDetails().getAddress().addLine("1 Brunthill Rd");
        students.get(1).getContactDetails().getAddress().addLine("Carlisle");

        students.add(new Student("Nadeem", "Khan", "44766157", new ContactDetails("07700900407", "Nadeem@mail.com", new Address("SY24 5BU"))));
        students.get(2).getContactDetails().getAddress().addLine("B4353 Bow Street");

        students.add(new Student("Max", "Miller", "84829624", new ContactDetails("07700900182", "Max@mail.com", new Address("OX16 9RU"))));
        students.get(3).getContactDetails().getAddress().addLine("36 W Bar St");
        students.get(3).getContactDetails().getAddress().addLine("Banbury");
    }

    private void populateUsers() //Adds some users
    {
        users.add(new Warden("Warden1", "", new ContactDetails("07700900247", "bob@mail.com"), "Bob", "Smith"));
        users.add(new Warden("Warden2", "", new ContactDetails("07700900557", "jeff@mail.com"), "Jeff", "Jones"));
        users.add(new Manager("Manager", "", new ContactDetails("07700900837", "manager@email.com"), "Dave", "Roberts"));
        users.add(new Admin("Admin", "", new ContactDetails("07700900627", "admin@mail.com"), "Steve", "Davies"));
    }

    private void populateHalls() //Hard code the halls with the students and wardens
    {
        halls.add(new Hall("UWEH1", "10", new Address("BS28 2RS"), "01172561224", new Image("http://www1.uwe.ac.uk/images/student-village-frenchay-exterior-4.jpg")));
        halls.get(0).getAddress().addLine("1 Bear Lane");
        halls.get(0).getAddress().addLine("Bristol");
        halls.get(0).setWarden((Warden) users.get(0));
        halls.get(0).addRoom(new Room("101", 125.24, new Lease(students.get(0), "2017.244.10.101", 12, new Date(2017 - 1900, 8, 1), true), true, "Clean"));
        halls.get(0).addRoom(new Room("102", 128.76, new Lease(students.get(1), "2017.277.10.102", 12, new Date(2017 - 1900, 9, 4), true), true, "Dirty"));
        halls.get(0).addRoom(new Room("103", 123.22, "Clean"));

        halls.add(new Hall("UWEH2", "12", new Address("BS28 4BG"), "01176676366"));
        halls.get(1).getAddress().addLine("2 Whale Meadow");
        halls.get(1).getAddress().addLine("Bristol");
        halls.get(1).setWarden((Warden) users.get(1));
        halls.get(1).addRoom(new Room("101", 101.57, new Lease(students.get(2), "2017.242.12.101", 12, new Date(2017 - 1900, 7, 30), true), true, "Clean"));
        halls.get(1).addRoom(new Room("102", 121.39, new Lease(students.get(3), "2017.286.12.102", 12, new Date(2017 - 1900, 9, 13), true), true, "Dirty"));
        halls.get(1).addRoom(new Room("103", 143.11, "Off-line"));

//        //Additional rooms for testing
//        halls.add(new Hall("UWEH3", "13", new Address("BS39 4HT"), "01176676366"));
//        halls.get(2).getAddress().addLine("1 Paradise Row");
//        halls.get(2).getAddress().addLine("Pensford");
//        halls.get(2).getAddress().addLine("Bristol");
//        halls.get(2).setWarden((Warden) users.get(1));
//        halls.get(2).addRoom(new Room("001", 122.21, "Clean"));
//        halls.get(2).addRoom(new Room("002", 122.21, "Dirty"));
//        halls.get(2).addRoom(new Room("012", 122.21, "Clean"));
//        halls.get(2).addRoom(new Room("102", 122.21, "Clean"));
//        halls.get(2).addRoom(new Room("023", 122.21, "Clean"));
//        halls.get(2).addRoom(new Room("220", 122.21, "Clean"));
    }

    /**
     * Gives detail on a hall in the form of a pane
     *
     * @param hall
     * @return pane
     */
    private Pane displayHall(Hall hall)
    {
        //Setting up the pane
        Pane pane = new Pane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        //Image of the hall
        ImageView imgView = ImageViewBuilder.create()
                .image(hall.getImage())
                .build();
        imgView.setFitHeight(120);
        imgView.setFitWidth(120);
        imgView.setPreserveRatio(true);
        grid.add(imgView, 0, 1);

        //Hall name
        Label lbHallName = new Label(hall.getName());
        lbHallName.setFont(Font.font("Ariel", FontWeight.BOLD, 12));
        grid.add(lbHallName, 0, 0);

        //Hall address
        grid.add(new Label("Address:"), 1, 0);
        TextArea address = new TextArea(hall.getAddress().toString());
        address.setPrefSize(120, 70);
        address.setEditable(false);
        grid.add(address, 1, 1);

        //Hall number
        grid.add(new Label("Hall Number: "), 0, 2);
        grid.add(new Label(hall.getNumber()), 1, 2);

        //Hall phone number
        grid.add(new Label("Hall Phone Number: "), 0, 3);
        TextField phone = new TextField(hall.getPhoneNum());
        phone.setEditable(false);
        grid.add(phone, 1, 3);
        grid.add(new Label(""), 0, 4);

        //Hall Warden
        Label lbWarden = new Label("Hall Warden");
        grid.add(lbWarden, 0, 4);
        lbWarden.setStyle("-fx-font-weight: bold;");
        TextField warden = new TextField(hall.getWarden().toString());
        warden.setEditable(false);
        grid.add(warden, 1, 5);

        //Hall Warden phone number
        grid.add(new Label("Phone Number:"), 0, 6);
        TextField wardenPhone = new TextField(hall.getWarden().getContactDetails().getPhoneNumber());
        wardenPhone.setEditable(false);
        grid.add(wardenPhone, 1, 6);

        //Hall Warden email
        grid.add(new Label("Email:"), 0, 7);
        TextField wardenEmail = new TextField(hall.getWarden().getContactDetails().getEmail());
        wardenEmail.setEditable(false);
        grid.add(wardenEmail, 1, 7);

        //pane options
        grid.setMinSize(350, 250);
        pane.getChildren().add(grid);
        return pane;
    }

    private TableView previousLeases(List<Lease> leases)
    {
        ObservableList<PastLeaseRow> previous = FXCollections.observableArrayList();
        for (Lease lease : leases)
        { //Converting past leases into PastLeaseRows
            previous.add(new PastLeaseRow(lease));
        }

        TableView previousLeaseTable = new TableView();

        //Lease Number
        TableColumn<PastLeaseRow, String> leaseNumberCol = new TableColumn<>("Lease Number");
        leaseNumberCol.setCellValueFactory(new PropertyValueFactory<>("leaseNumber"));
        leaseNumberCol.setMinWidth(100);

        //Student name column
        TableColumn<PastLeaseRow, String> studentNameCol = new TableColumn<>("Student Name");
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentNameCol.setMinWidth(100);

        //Student number column
        TableColumn<PastLeaseRow, String> studentNumCol = new TableColumn<>("Student Number");
        studentNumCol.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        studentNumCol.setMinWidth(100);

        //Start date column
        TableColumn<PastLeaseRow, String> startDateCol = new TableColumn<>("Start Date");
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        startDateCol.setMinWidth(100);

        //End date column
        TableColumn<PastLeaseRow, String> endDateCol = new TableColumn<>("End Date");
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        endDateCol.setMinWidth(100);

        //Lease duration coloumn
        TableColumn<PastLeaseRow, String> durationCol = new TableColumn<>("(Duration)");
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        durationCol.setMinWidth(80);

        //Add the columns to the table
        previousLeaseTable.getColumns().addAll(leaseNumberCol, studentNameCol, studentNumCol, startDateCol, endDateCol, durationCol);

        //Table settings
        previousLeaseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        previousLeaseTable.setItems(previous); //Add data to the table

        previousLeaseTable.setFixedCellSize(25);
        previousLeaseTable.prefHeightProperty().bind(previousLeaseTable.fixedCellSizeProperty().multiply(Bindings.size(previousLeaseTable.getItems()).add(1.01)));
        previousLeaseTable.minHeightProperty().bind(previousLeaseTable.prefHeightProperty());
        previousLeaseTable.maxHeightProperty().bind(previousLeaseTable.prefHeightProperty());

        return previousLeaseTable;
    }

    /**
     * Provides a separate pane for a room with details about the room
     *
     * @param hall
     * @param room
     * @return pane
     */
    private Pane displayRoom(Hall hall, Room room)
    {
        //Setting up the pane
        Pane pane = new Pane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        //Room number
        grid.add(new Label("Room Number: "), 0, 0);
        Label roomNumber = new Label(room.getNumber());
        roomNumber.setFont(Font.font("Ariel", FontWeight.BOLD, 12));
        grid.add(roomNumber, 1, 0);

        //Hall name
        grid.add(new Label("Hall Name: "), 0, 1);
        grid.add(new Label(hall.getName()), 1, 1);

        //Hall number
        grid.add(new Label("Hall Number: "), 0, 2);
        grid.add(new Label(hall.getNumber()), 1, 2);

        //Room rent
        grid.add(new Label("Monthly Rent:"), 0, 3);
        TextField rent = new TextField(Double.toString(room.getMonthlyRent()));
        rent.setEditable(false);
        grid.add(rent, 1, 3);

        //Room Lease number
        grid.add(new Label("Lease Number:"), 0, 4);
        TextField lease = new TextField();
        lease.setEditable(false);
        grid.add(lease, 1, 4);

        //Student in Room (Lease - Student)
        grid.add(new Label("Student Name:"), 0, 6);
        TextField student = new TextField();
        student.setEditable(false);
        grid.add(student, 1, 6);

        //Room occupancy
        grid.add(new Label("Occupancy Status: "), 0, 5);
        TextField occ = new TextField();
        occ.setEditable(false);
        if (room.isOccupied())
        { //The lease number and student name exist if the room is occupied
            occ.setText("Occupied");
            lease.setText(room.getCurrentLease().getLeaseNumber());
            student.setText(room.getCurrentLease().getStudent().getFullName());
        } else
        { //The room is unoccupied - no student or lease
            occ.setText("Unoccupied");
            student.setText("");
            lease.setText("");
        }
        grid.add(occ, 1, 5);

        //Room cleaning status
        grid.add(new Label("Cleaning Status:"), 0, 7);
        TextField clean = new TextField(room.getCleaningStatus());
        clean.setEditable(false);
        grid.add(clean, 1, 7);

        //Past leases
        grid.add(new Label("Previous leases:"), 0, 8);
        //Manager scroll pane
        ScrollPane spPrevious = new ScrollPane();
        spPrevious.setContent(previousLeases(room.getLeases()));
        spPrevious.setHbarPolicy(ScrollBarPolicy.NEVER);
        spPrevious.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        spPrevious.setPrefViewportHeight(75);
        spPrevious.setPrefViewportWidth(580);
        grid.add(spPrevious, 0, 9, 3, 1);

        //pane options
        grid.setMinSize(350, 250);
        pane.getChildren().add(grid);
        return pane;
    }

    /**
     * Provides more details on a student in a separate pane
     *
     * @param hall
     * @param room
     * @return pane
     */
    private Pane displayStudent(Hall hall, Room room)
    {
        //Setting up the pane
        Student student = room.getCurrentLease().getStudent();
        Pane pane = new Pane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));

        //Student first name
        Label lFirstName = new Label("First Name:");
        TextField tfFirstName = new TextField(student.getFirstName());
        tfFirstName.setEditable(false);
        grid.add(lFirstName, 0, 1);
        grid.add(tfFirstName, 1, 1);

        //Student last name
        Label lLastName = new Label("Last Name:");
        TextField tfLastName = new TextField(student.getLastName());
        tfLastName.setEditable(false);
        grid.add(lLastName, 0, 2);
        grid.add(tfLastName, 1, 2);

        //Student number
        Label lStudentNumber = new Label("Student Number: ");
        TextField tfStudentNumber = new TextField(student.getStudentNumber());
        tfStudentNumber.setEditable(false);
        grid.add(lStudentNumber, 0, 3);
        grid.add(tfStudentNumber, 1, 3);

        //Student's current address - generated from room and hall
        Label lCurrentAddress = new Label("Current Addresss: "); //Constructs the students address based on the room and hall
        TextArea taCurrentAddress = new TextArea(student.getFullName() + "\n" + "Room " + room.getNumber() + "\n" + hall.getAddress().toString());
        taCurrentAddress.setPrefSize(120, 70);
        taCurrentAddress.setEditable(false);
        taCurrentAddress.setEditable(false);
        grid.add(lCurrentAddress, 0, 4);
        grid.add(taCurrentAddress, 1, 4);

        //Student ContactDetails
        Label contactDetails = new Label("Contact Details: ");
        contactDetails.setFont(Font.font("Ariel", FontWeight.BOLD, 14));
        grid.add(contactDetails, 0, 5);

        ContactDetails cd = student.getContactDetails();

        //Student phone number (ContactDetails)
        Label lPhoneNumber = new Label("Phone Number: ");
        TextField tfPhoneNumber = new TextField(cd.getPhoneNumber());
        tfPhoneNumber.setEditable(false);
        grid.add(lPhoneNumber, 0, 6);
        grid.add(tfPhoneNumber, 1, 6);

        //Student email (ContactDetails)
        Label lEmail = new Label("Email: ");
        TextField tfEmail = new TextField(cd.getEmail());
        tfEmail.setEditable(false);
        grid.add(lEmail, 0, 7);
        grid.add(tfEmail, 1, 7);

        //Student Address (ContactDetails) - out of term only for Managers or Admins
        if (this.currentUser.getUserType() == 2 || this.currentUser.getUserType() == 3)
        {
            Label loutAddress = new Label("Out of Term Address: ");
            TextArea taOutAddress = new TextArea(cd.getAddress().toString());
            taOutAddress.setPrefSize(120, 70);
            taOutAddress.setEditable(false);
            taOutAddress.setEditable(false);
            grid.add(loutAddress, 0, 8);
            grid.add(taOutAddress, 1, 8);
        }

        //pane options
        grid.setMinSize(350, 400);
        pane.getChildren().add(grid);
        return pane;
    }

    /**
     * Displays the options on a room lease for a manager view given in a pane
     *
     * @param hall
     * @param room
     * @param table
     * @return pane
     */
    private Pane leaseOptions(Hall hall, Room room, TableView table)
    {
        //Setting up the pane
        Pane pane = new Pane();
        Label label = new Label("Lease Info:");
        label.setFont(Font.font("Ariel", FontWeight.BOLD, 14));
        GridPane grid = new GridPane();
        grid.setMinHeight(100);
        grid.add(label, 0, 0);

        //Hall name
        grid.add(new Label("Hall Name: "), 0, 2);
        TextField hallName = new TextField();
        hallName.setEditable(false);
        hallName.setText(hall.getName());
        grid.add(hallName, 1, 2);

        //Hall Number
        grid.add(new Label("Hall Number: "), 0, 3);
        TextField hallNum = new TextField();
        hallNum.setEditable(false);
        hallNum.setText(hall.getNumber());
        grid.add(hallNum, 1, 3);

        //Room number
        grid.add(new Label("Room Number: "), 0, 4);
        TextField roomNum = new TextField();
        roomNum.setEditable(false);
        roomNum.setText(room.getNumber());
        grid.add(roomNum, 1, 4);

        //Lease number
        grid.add(new Label("Lease Number: "), 5, 2);
        TextField leaseNum = new TextField();
        leaseNum.setEditable(false);

        //Lease start date
        DatePicker startDate = new DatePicker();
        startDate.setShowWeekNumbers(false);

        //Lease end date
        grid.add(new Label("End Date:"), 7, 3);
        TextField endDate = new TextField();
        endDate.setEditable(false);
        grid.add(endDate, 8, 3);

        //Lease duration (months)
        Label lbDuration = new Label("Duration: ");
        grid.add(lbDuration, 7, 4);
        lbDuration.setStyle("-fx-font-weight: bold;");
        TextField duration = new TextField();
        duration.setPromptText("Default is 12 months");
        grid.add(duration, 8, 4);

        //Student name (Lease) - searchable
        ComboBox cmbStudent = new ComboBox<>();
        Label name = new Label("Student Name: ");
        name.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        grid.add(name, 5, 3);
        cmbStudent.getItems().addAll(students);
        new ComboBoxAutoComplete<>(cmbStudent); //The searchable combobox
        grid.add(cmbStudent, 6, 3);

        if (room.getCurrentLease() == null)
        { //If the room doesn't have a lease (unoccupied)
            leaseNum.setText("This is generated");
            leaseNum.setStyle("-fx-text-fill: #778c94;");

            cmbStudent.setPromptText("Find Student");

            startDate.setPromptText("Select Start Date");

            endDate.setText("Depends on duration");
            endDate.setStyle("-fx-text-fill: #778c94;");
        } else
        { //If there is a lease
            leaseNum.setText(room.getCurrentLease().getLeaseNumber()); //Show current Lease number

            cmbStudent.setValue(room.getCurrentLease().getStudent()); //Show current Student in Lease

            startDate.setValue(new java.sql.Date(room.getCurrentLease().getStartLease().getTime()).toLocalDate()); //Show current start date

            //Generate the end date of the lease (number of months from lease duration)
            Calendar c = Calendar.getInstance();
            c.setTime(room.getCurrentLease().getStartLease());
            c.add(Calendar.MONTH, room.getCurrentLease().getDuration());
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            endDate.setText(format1.format(c.getTime()));

            duration.setText(Integer.toString(room.getCurrentLease().getDuration()));

            //Calculate the number of days remaining before the lease ends
            Date currentDate = new Date();
            Date end = new Date(c.getTimeInMillis());
            long difference = end.getTime() - currentDate.getTime();
            float daysBetween = (difference / (1000 * 60 * 60 * 24)) + 1;
            grid.add(new Label("(" + daysBetween + " days remaining)"), 9, 3, 2, 1);
        }
        grid.add(leaseNum, 6, 2);

        //Room occupancy
        grid.add(new Label("Occupancy: "), 5, 4);
        TextField occ = new TextField();
        occ.setEditable(false);

        // Lease start date (label)
        Label start = new Label("Start Date: ");
        start.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        grid.add(start, 7, 2);
        grid.add(startDate, 8, 2);

        Button btCreate = new Button("Create");
        btCreate.setOnAction(e
                ->
                {
                    if (!room.getCleaningStatus().equals("Off-line"))
                    { //Check not Off-line
                        if (cmbStudent.getValue() != null && startDate.getValue() != null)
                        { //Check student and start date are filled in
                            if (duration.getText().equals("") || (duration.getText().matches("[0-9]*") && Integer.parseInt(duration.getText()) > 0))
                            { //Check duration is filled in correctly
                                int leaseDuration = 12; //Default lease length
                                if (!duration.getText().equals(""))
                                { //Set different length if user has changed it
                                    leaseDuration = Integer.parseInt(duration.getText());
                                }
                                String autoLeaseNumber = startDate.getValue().getYear() +"."+ startDate.getValue().getDayOfYear() + "." + hall.getNumber() + "." + room.getNumber();
                                java.util.Date date = java.sql.Date.valueOf(startDate.getValue());
                                if (room.isOccupied() == true)
                                { //Update operation being carried out
                                    Alert alert = new Alert(AlertType.CONFIRMATION);
                                    alert.setTitle("Confirm Edit of Lease");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Are you sure you want to update this lease?\nFor Hall "
                                            + hall.getName() + " room " + room.getNumber() + "\nThis cannot be undone.");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.isPresent() && action.get() == ButtonType.OK)
                                    {
                                        room.updateLease(new Lease((Student) cmbStudent.getValue(), autoLeaseNumber, leaseDuration, date));
                                        table.getSelectionModel().clearSelection();
                                    }
                                } else
                                { //Create operation
                                    Alert alert = new Alert(AlertType.CONFIRMATION);
                                    alert.setTitle("Confirm Creation of Lease");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Are you sure you want to create this lease?"
                                            + "\nHall: " + hall.getName() + "\nRoom: " + room.getNumber() + "\nStudent: " + cmbStudent.getValue());
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.isPresent() && action.get() == ButtonType.OK)
                                    {
                                        room.setLease(new Lease((Student) cmbStudent.getValue(), autoLeaseNumber, leaseDuration, date));
                                        table.getSelectionModel().clearSelection();
                                    }
                                }
                            } else //Duration is not valid
                            {
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setTitle("Duration not valid");
                                alert.setHeaderText(null);
                                alert.setContentText("Duration is not valid.\nLeave blank for 12 months.\nEnter a whole number greater than zero otherwise.");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK)
                                {
                                }
                            }

                        } else
                        { //The Manager hasn't filled in the required fields
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("Required fields missing");
                            alert.setHeaderText(null);
                            alert.setContentText("You cannot create a lease without the required fields.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK)
                            {
                            }
                        }
                    } else
                    { //The room is Off-line - error message to the manager
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Room is Off-line");
                        alert.setHeaderText(null);
                        alert.setContentText("This room is marked as Off-line.\nPlease contact "
                                + hall.getWarden().toString() + " (Warden for " + hall.getName() + ")\n"
                                + "either by email: " + hall.getWarden().getContactDetails().getEmail() + "\n"
                                + "or by phone: " + hall.getWarden().getContactDetails().getPhoneNumber() + "\n"
                                + "for more details.");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK)
                        {
                        }
                    }

                    table.refresh();
        });

        Button btRemove = new Button("Remove");
        btRemove.setOnAction(e
                ->
                { //Check the manager wants to remove the lease
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirm Removal of Lease");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to remove this lease?\nThis cannot be undone.");
                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.isPresent() && action.get() == ButtonType.OK)
                    {
                        Alert inform = new Alert(AlertType.INFORMATION);
                        inform.setTitle("Confirm Removal of Lease");
                        inform.setHeaderText(null);
                        inform.setContentText("The lease will be removed and the room will be set to unoccupied.");
                        Optional<ButtonType> action2 = inform.showAndWait();
                        if (action2.isPresent() && action2.get() == ButtonType.OK)
                        {
                            room.removeLease();
                        }
                    }
                    table.getSelectionModel().clearSelection();
                    table.refresh();
        });
        if (room.isOccupied())
        {
            occ.setText("Occupied");
            grid.add(btRemove, 9, 4);
            btCreate.setText("Update");
            grid.add(btCreate, 10, 4);
        } else
        {
            occ.setText("Unoccupied");
            grid.add(btCreate, 9, 4);
        }

        //pane options
        grid.add(occ, 6, 4);

        if (currentUser.getUserType() == 3)
        { //--- Admin ---\\
            Pane adminPane = new Pane();
            adminPane.setMinSize(180, 70);
            adminPane.setStyle("-fx-background-color: #d3d3d3");
            GridPane adminGrid = new GridPane();
            //Room cleaning status
            ComboBox cleaning = new ComboBox();
            cleaning.setEditable(false);
            ObservableList<String> options = FXCollections.observableArrayList();
            options.add("Clean");
            options.add("Dirty");
            if (!room.isOccupied())
            { //Off-line only an option if room is unoccupied
                options.add("Off-line");
            }
            cleaning.getItems().addAll(options);
            if (room.getCleaningStatus().equals("Clean"))
            {
                cleaning.setValue(cleaning.getItems().get(0));
            } else if (room.getCleaningStatus().equals("Dirty"))
            {
                cleaning.setValue(cleaning.getItems().get(1));
            } else
            {
                cleaning.setValue(cleaning.getItems().get(2));
            }
            //Formatting of labels and textfields
            Label lbCleanInfo = new Label("Cleaning Info:");
            lbCleanInfo.setFont(Font.font("Ariel", FontWeight.BOLD, 14));
            Label lbCleanSt = new Label("Cleaning Status: ");
            lbCleanSt.setStyle("-fx-font-weight: bold; -fx-underline: true;");
            adminGrid.add(lbCleanInfo, 0, 0);
            adminGrid.add(lbCleanSt, 0, 1);
            adminGrid.add(cleaning, 1, 1);
            //Update button
            Button btUpdateAdmin = new Button("Update");
            btUpdateAdmin.setOnAction(e
                    ->
                    { //Update button - no error checking needed as they can only select a value
                        if (this.currentUser.getUserType() == 3)
                        {
                            room.setCleaningStatus(cleaning.getValue().toString());
                            table.refresh();
                            table.getSelectionModel().clearSelection();
                        } else
                        {
                            Alert alert = new Alert(AlertType.WARNING);
                            alert.setTitle("User Level Incorrect");
                            alert.setHeaderText(null);
                            alert.setContentText("User is not the correct privileges");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK)
                            {
                            }
                        }
            });
            adminGrid.add(new Label(" "), 1, 2);
            adminGrid.add(btUpdateAdmin, 1, 3);
            adminPane.getChildren().add(adminGrid);
            grid.add(adminPane, 11, 0, 1, 5);
        }

        pane.getChildren().add(grid);
        return pane;
    }

    /**
     * Displays the options on a room (cleaning) for a warden given in a pane
     *
     * @param hall
     * @param room
     * @param table
     * @return pane
     */
    private Pane cleaningOptions(Hall hall, Room room, TableView table)
    {
        //Setting up the pane
        Pane pane = new Pane();
        Label label = new Label("Cleaning Info:");
        label.setFont(Font.font("Ariel", FontWeight.BOLD, 14));
        GridPane grid = new GridPane();
        grid.setMinHeight(100);
        grid.add(label, 0, 0);

        //Hall name
        grid.add(new Label("Hall Name: "), 0, 2);
        TextField hallName = new TextField();
        hallName.setEditable(false);
        hallName.setText(hall.getName());
        grid.add(hallName, 1, 2);

        //Hall number
        grid.add(new Label("Hall Number: "), 0, 3);
        TextField hallNum = new TextField();
        hallNum.setEditable(false);
        hallNum.setText(hall.getNumber());
        grid.add(hallNum, 1, 3);

        //Room number
        grid.add(new Label("Room Number: "), 0, 4);
        TextField roomNum = new TextField();
        roomNum.setEditable(false);
        roomNum.setText(room.getNumber());
        grid.add(roomNum, 1, 4);

        //Student name
        grid.add(new Label("Student Name: "), 5, 2);
        TextField studentName = new TextField();
        studentName.setEditable(false);
        if (room.getCurrentLease() == null)
        {
            studentName.setText("");
        } else
        {
            studentName.setText(room.getCurrentLease().getStudent().getFullName());
        }
        grid.add(studentName, 6, 2);

        //Room cleaning status (label)
        Label clean = new Label("Cleaning Status: ");
        clean.setStyle("-fx-font-weight: bold; -fx-underline: true;");
        grid.add(clean, 5, 3);

        //Room occupancy
        TextField occ = new TextField();
        occ.setText("Occupied");

        //Room cleaning status
        ComboBox cleaning = new ComboBox();
        cleaning.setEditable(false);
        ObservableList<String> options = FXCollections.observableArrayList();
        options.add("Clean");
        options.add("Dirty");
        if (!room.isOccupied())
        { //TODO: Check this is what they want //Only allow Off-line as an option if the room is unoccupied
            options.add("Off-line");
            occ.setText("Unoccupied");
        }

        cleaning.getItems().addAll(options);
        if (room.getCleaningStatus().equals("Clean"))
        {
            cleaning.setValue(cleaning.getItems().get(0));
        } else if (room.getCleaningStatus().equals("Dirty"))
        {
            cleaning.setValue(cleaning.getItems().get(1));
        } else
        {
            cleaning.setValue(cleaning.getItems().get(2));
        }
        grid.add(cleaning, 6, 3);

        //Room occupancy
        grid.add(new Label("Occupancy: "), 5, 4);
        occ.setEditable(false);
        grid.add(occ, 6, 4);

        //Update button
        Button btUpdate = new Button("Update");
        btUpdate.setOnAction(e
                ->
                { //Update button - no error checking needed as they can only select a value
                    if (hall.getWarden() == currentUser)
                    {
                        room.setCleaningStatus(cleaning.getValue().toString());
                        table.refresh();
                        table.getSelectionModel().clearSelection();
                    } else
                    {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Not correct warden");
                        alert.setHeaderText(null);
                        alert.setContentText("User is not the warden for this hall");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK)
                        {
                        }
                    }
        });
        grid.add(btUpdate, 8, 4);

        //pane options
        pane.getChildren().add(grid);
        return pane;
    }

    /**
     * This is the banner/navigation menu that the top of the screen
     *
     * @param stage
     * @param scLogin -
     * @param scHome
     * @param user
     * @return
     */
    private Pane banner(Stage stage, Scene scLogin, Scene scHome, String user)
    {
        Pane pane = new Pane();

        //Logo
        ImageView logo = ImageViewBuilder.create()
                .image(new Image("http://style.uwe.ac.uk/branding/couplets/engine/images/logo.png"))
                .build();
        logo.setFitHeight(120);
        logo.setFitWidth(120);
        logo.setPreserveRatio(true);

        //Home button - link to the main table
        Label home = new Label("Home");
        home.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        home.setOnMouseEntered(e
                ->
                {
                    home.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color:#1D6269;");
        });

        home.setOnMouseExited(e
                ->
                {
                    home.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color:transparent;");
        });
        home.setOnMouseClicked(e
                ->
                {
                    stage.setScene(scHome);
                    stage.centerOnScreen();
        });

        //User info - name and level
        Label userInfo = new Label(user);
        userInfo.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");

        // Logout button - not acutally a button
        Label logout = new Label("Logout");
        logout.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        logout.setOnMouseEntered(e
                ->
                {
                    logout.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color:#1D6269;");
        });

        logout.setOnMouseExited(e
                ->
                {
                    logout.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color:transparent;");
        });
        logout.setOnMouseClicked(e
                ->
                {
                    stage.setScene(scLogin);
                    stage.centerOnScreen();
                    this.currentUser = null;
        });

        HBox hbox = new HBox(8); // spacing = 8
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.getChildren().addAll(logo, home, userInfo, logout);

        pane.getChildren().add(hbox);
        pane.setStyle("-fx-background-color: #277F8A;");
        return pane;
    }

    @Override
    public void start(Stage stage)
    {
        // Sets up all of the classes
        populateStudents();
        populateUsers();
        populateHalls();

        //Populate the observable list (rows) with rowView object
        for (Hall hall : halls)
        {
            for (Room room : hall.getRooms())
            {
                rows.add(new RowView(hall, room));
            }
        }

        //Setting the scenes
        Scene userSelection, managerMainView, wardenMainView;
        managerMainView = new Scene(new Group());
        wardenMainView = new Scene(new Group());
        userSelection = new Scene(new Group());

        //Creating the tables
        TableView managerMainTable = new TableView();
        TableView wardenMainTable = new TableView();

        //--- User Selection ---\\
        stage.setTitle("UWE Accommodation Login");

        GridPane userLogin = new GridPane();
        userLogin.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns
        userLogin.setVgap(7);
        userLogin.setHgap(7);

        //Setting the Grid alignment
        userLogin.setAlignment(Pos.CENTER);
        userLogin.setMinSize(350, 250);
        ((Group) userSelection.getRoot()).getChildren().addAll(userLogin);

        //Logo
        ImageView logo = ImageViewBuilder.create()
                .image(new Image("http://style.uwe.ac.uk/branding/couplets/engine/images/logo.png"))
                .build();
        logo.setFitWidth(200);
        logo.setFitHeight(200);
        logo.setPreserveRatio(true);
        VBox logovb = new VBox();
        logovb.setAlignment(Pos.CENTER);
        logovb.setPadding(new Insets(5, 5, 5, 5));
        logovb.setStyle("-fx-background-color: #277F8A;");

        //Login welcome text
        Label text = new Label("Accommodation System");
        text.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #ffffff;");
        logovb.getChildren().addAll(logo, text);
        userLogin.add(logovb, 0, 0, 2, 2);

        //Username (label)
        Label userName = new Label("Username: ");
        userName.setStyle("-fx-font-weight: bold;");
        userLogin.add(userName, 0, 2);

        //Username (field)
        TextField userTextField = new TextField();
        userLogin.add(userTextField, 1, 2);

        //Password (label)
        Label pw = new Label("Password:");
        pw.setStyle("-fx-font-weight: bold;");
        userLogin.add(pw, 0, 3);

        //Password (field)
        PasswordField pwBox = new PasswordField();
        userLogin.add(pwBox, 1, 3);

        //The rows for a warden (only for their hall)
        ObservableList<RowView> wardenRows = FXCollections.observableArrayList();

        Label userLabel = new Label(); //Label for title

        //Login button
        Button btLogin = new Button("Login");
        userLogin.add(btLogin, 1, 4);
        btLogin.setDefaultButton(true);
        btLogin.setStyle("-fx-font-weight: bold; -fx-base: #E11823;");
        btLogin.setOnAction(e
                ->
                {
                    boolean correct = false;
                    for (int i = 0; i < users.size(); i++)
                    { //Looks for the user (sequential search)
                        if (userTextField.getText().equals(users.get(i).getUsername()) && pwBox.getText().equals(users.get(i).getPass()))
                        { //Finds a valid username and password
                            correct = true;
                            currentUser = users.get(i); //Sets current user
                            String view = "";
                            String wardenHall = "";
                            view = this.currentUser.getUserLevel();;
                            if (currentUser.getUserType() == 1) //User is Warden
                            {
                                wardenRows.clear();
                                //Sets the rooms which the warden can see
                                for (RowView row : rows)
                                {
                                    if (row.getHall().getWarden().getUsername().equals(this.currentUser.getUsername()))
                                    {
                                        wardenRows.add(row);
                                    }
                                }
                                wardenMainTable.getSelectionModel().clearSelection();
                                stage.setScene(wardenMainView); //Sets the correct stage
                                wardenHall = " for (" + wardenRows.get(0).getHallName() + ")";
                            } else if (currentUser.getUserType() == 2 || currentUser.getUserType() == 3)
                            {
                                managerMainTable.getSelectionModel().clearSelection();
                                stage.setScene(managerMainView); //Sets the correct stage
                            }
                            userTextField.clear();
                            pwBox.clear();
                            userLabel.setText(view + " - " + currentUser.toString() + wardenHall);
                            stage.setTitle("UWE Bristol Accommodation System - " + view + " View");
                            stage.centerOnScreen();
                            break;
                        }

                    }
                    if (correct == false)
                    { //The username and/or the pw the user has entered is incorrect
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Incorrect Login details");
                        alert.setHeaderText(null);
                        alert.setContentText("Username and/or Password is incorrect.\nPlease try again");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK)
                        {
                        }
                    }

        });

        //---Detailed View---\\
        //Return button
        //--- Manager ---\\
        /**
         * Create the columns for the table. Uses reflection on rowView object.
         *
         */
        //Lease number column
        TableColumn<RowView, String> leaseNumCol = new TableColumn<>("Lease Number");
        leaseNumCol.setCellValueFactory(new PropertyValueFactory<>("leaseNumber"));
        leaseNumCol.setMinWidth(150);

        //Hall name column
        TableColumn<RowView, String> hallNameCol = new TableColumn<>("Hall Name");
        hallNameCol.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        hallNameCol.setMinWidth(150);

        hallNameCol.setCellFactory(cf
                ->
                {
                    TableCell<RowView, String> cell = new TableCell<RowView, String>()
                    {
                        @Override //Needed but don't change anything
                        protected void updateItem(String item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            setText(empty ? null : item);
                        }
                    };
                    cell.setOnMouseClicked(e
                            ->
                            { //Double click on hall name column will display info page on hall
                                if (e.getClickCount() == 2 && (!cell.isEmpty()))
                                {
                                    TableRow<RowView> row;
                                    row = cell.getTableRow();
                                    GridPane tempHall = new GridPane();

                                    tempHall.add(banner(stage, userSelection, managerMainView, userLabel.getText()), 0, 0);
                                    tempHall.add(displayHall(row.getItem().getHall()), 0, 1);
                                    stage.setScene(new Scene(new Group(tempHall)));
                                    stage.centerOnScreen();
                                }
                    });
                    return cell;
        });

        //Hall number column
        TableColumn<RowView, String> hallNumCol = new TableColumn<>("Hall Number");
        hallNumCol.setCellValueFactory(new PropertyValueFactory<>("hallNumber"));
        hallNumCol.setMinWidth(150);

        //Room number column
        TableColumn<RowView, String> roomNumCol = new TableColumn<>("Room Number");
        roomNumCol.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomNumCol.setMinWidth(150);

        roomNumCol.setCellFactory(cf
                ->
                {
                    TableCell<RowView, String> cell = new TableCell<RowView, String>()
                    {
                        @Override //Needed but don't change anything
                        protected void updateItem(String item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            setText(empty ? null : item);
                        }
                    };
                    cell.setOnMouseClicked(e
                            ->
                            {
                                if (e.getClickCount() == 2 && (!cell.isEmpty()) && (this.currentUser.getUserType() == 2 || this.currentUser.getUserType() == 3))
                                { //Double click on room column to output page on the room
                                    TableRow<RowView> row;
                                    row = cell.getTableRow();
                                    GridPane tempRoom = new GridPane();

                                    tempRoom.add(banner(stage, userSelection, managerMainView, userLabel.getText()), 0, 0);
                                    tempRoom.add(displayRoom(row.getItem().getHall(), row.getItem().getRoom()), 0, 1);
                                    stage.setScene(new Scene(new Group(tempRoom)));
                                    stage.centerOnScreen();
                                }
                    });
                    return cell;
        });

        //Student name column
        TableColumn<RowView, String> studentNameCol = new TableColumn<>("Student Name");
        studentNameCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentNameCol.setMinWidth(150);

        studentNameCol.setCellFactory(cf
                ->
                {
                    TableCell<RowView, String> cell = new TableCell<RowView, String>()
                    {
                        @Override //Needed but don't change anything
                        protected void updateItem(String item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            setText(empty ? null : item);
                        }
                    };
                    cell.setOnMouseClicked(e
                            ->
                            {
                                if (e.getClickCount() == 2 && (!cell.isEmpty()) && (!cell.getItem().equals("")))
                                { //Double click Student Name column is display more detail on student (if one)
                                    TableRow<RowView> row;
                                    row = cell.getTableRow();
                                    GridPane tempStudent = new GridPane();
                                    tempStudent.add(banner(stage, userSelection, managerMainView, userLabel.getText()), 0, 0);

                                    tempStudent.add(displayStudent(row.getItem().getHall(), row.getItem().getRoom()), 0, 1);
                                    stage.setScene(new Scene(new Group(tempStudent)));
                                    stage.centerOnScreen();
                                }
                    });
                    return cell;
        });

        //Room occupancy status column
        TableColumn<RowView, String> occupancyCol = new TableColumn<>("Occupancy Status");
        occupancyCol.setCellValueFactory(new PropertyValueFactory<>("occupancy"));
        occupancyCol.setMinWidth(150);

        //Room cleaning status columns
        TableColumn<RowView, String> cleaningCol = new TableColumn<>("Cleaning Status");
        cleaningCol.setCellValueFactory(new PropertyValueFactory<>("cleaningStatus"));
        cleaningCol.setMinWidth(150);

        //Add the columns to the manager table
        managerMainTable.getColumns().addAll(leaseNumCol, hallNameCol, hallNumCol, roomNumCol, studentNameCol, occupancyCol, cleaningCol);
        Pane managerPane = new Pane();
        managerPane.setMinHeight(100);

        //Table settings
        managerMainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        managerMainTable.setItems(rows); //Add data to the table

        //Limit the number of rows in the table
        managerMainTable.setFixedCellSize(25);
        managerMainTable.prefHeightProperty().bind(managerMainTable.fixedCellSizeProperty().multiply(Bindings.size(managerMainTable.getItems()).add(1.01)));
        managerMainTable.minHeightProperty().bind(managerMainTable.prefHeightProperty());
        managerMainTable.maxHeightProperty().bind(managerMainTable.prefHeightProperty());

        //Manager scroll pane
        ScrollPane spManager = new ScrollPane();
        spManager.setContent(managerMainTable);
        spManager.setHbarPolicy(ScrollBarPolicy.NEVER);
        spManager.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        spManager.setPrefViewportHeight(180);
        spManager.setPrefViewportWidth(1050);

        //Manager view organisation
        VBox vbxTableManager = new VBox();
        vbxTableManager.getChildren().addAll(banner(stage, userSelection, managerMainView, userLabel.getText()), spManager, managerPane);
        ((Group) managerMainView.getRoot()).getChildren().addAll(vbxTableManager);

        //Manager table single click
        managerMainTable.setRowFactory(tv
                ->
                {
                    TableRow<RowView> row = new TableRow<>();
                    row.setOnMouseClicked(event
                            ->
                            {
                                if (event.getClickCount() == 1 && (!row.isEmpty()))
                                { //Single click on a row displays the lease options for the room
                                    managerPane.getChildren().clear();
                                    managerPane.getChildren().add(leaseOptions(row.getItem().getHall(), row.getItem().getRoom(), managerMainTable));
                                }
                    });
                    return row;
        });

        //--- Warden --\\
        TableColumn<RowView, String> hallNameColW = new TableColumn<>("Hall Name");
        hallNameColW.setCellValueFactory(new PropertyValueFactory<>("hallName"));
        hallNameColW.setMinWidth(150);

        TableColumn<RowView, String> roomNumColW = new TableColumn<>("Room Number");
        roomNumColW.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomNumColW.setMinWidth(150);

        TableColumn<RowView, String> studentNameColW = new TableColumn<>("Student Name");
        studentNameColW.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentNameColW.setMinWidth(150);

        studentNameColW.setCellFactory(cf
                ->
                {
                    TableCell<RowView, String> cell = new TableCell<RowView, String>()
                    {
                        @Override //Needed but don't change anything
                        protected void updateItem(String item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            setText(empty ? null : item);
                        }
                    };
                    cell.setOnMouseClicked(e
                            ->
                            {
                                if (e.getClickCount() == 2 && (!cell.isEmpty()) && (!cell.getItem().equals("")))
                                { //Double click Student Name column provides more detail
                                    TableRow<RowView> row;
                                    row = cell.getTableRow();
                                    GridPane tempStudent = new GridPane();

                                    tempStudent.add(banner(stage, userSelection, wardenMainView, userLabel.getText()), 0, 0);
                                    tempStudent.add(displayStudent(row.getItem().getHall(), row.getItem().getRoom()), 0, 1);
                                    stage.setScene(new Scene(new Group(tempStudent)));
                                    stage.centerOnScreen();
                                }
                    });
                    return cell;
        });

        TableColumn<RowView, String> occupancyColW = new TableColumn<>("Occupancy Status");
        occupancyColW.setCellValueFactory(new PropertyValueFactory<>("occupancy"));
        occupancyColW.setMinWidth(150);

        TableColumn<RowView, String> cleaningColW = new TableColumn<>("Cleaning Status");
        cleaningColW.setCellValueFactory(new PropertyValueFactory<>("cleaningStatus"));
        cleaningColW.setMinWidth(150);
        //DUPLICATE - END

        //Adds columns to the table
        wardenMainTable.getColumns().setAll(hallNameColW, roomNumColW, studentNameColW, occupancyColW, cleaningColW);
        Pane wardenPane = new Pane();
        wardenPane.setMinHeight(100);

        //Warden table settings
        wardenMainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        wardenMainTable.setItems(wardenRows); //Sets the data - note that wardenRows is generated during the login

        //Limit the number of rows in the table
        wardenMainTable.setFixedCellSize(25);
        wardenMainTable.prefHeightProperty().bind(wardenMainTable.fixedCellSizeProperty().multiply(Bindings.size(wardenMainTable.getItems()).add(1.01)));
        wardenMainTable.minHeightProperty().bind(wardenMainTable.prefHeightProperty());
        wardenMainTable.maxHeightProperty().bind(wardenMainTable.prefHeightProperty());

        //Warden scroll pane
        ScrollPane spWarden = new ScrollPane();
        spWarden.setContent(wardenMainTable);
        spWarden.setHbarPolicy(ScrollBarPolicy.NEVER);
        spWarden.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        spWarden.setPrefViewportHeight(125);
        spWarden.setPrefViewportWidth(750);

        //Warden view organisation
        final VBox vbxTableWarden = new VBox();
        vbxTableWarden.getChildren().addAll(banner(stage, userSelection, wardenMainView, userLabel.getText()), spWarden, wardenPane);
        ((Group) wardenMainView.getRoot()).getChildren().addAll(vbxTableWarden);

        //Warden table single click
        wardenMainTable.setRowFactory(tv
                ->
                {
                    TableRow<RowView> row = new TableRow<>();
                    row.setOnMouseClicked(event
                            ->
                            {
                                if (event.getClickCount() == 1 && (!row.isEmpty()))
                                { //Single click on a row displays the cleaning options for the room
                                    wardenPane.getChildren().clear();
                                    wardenPane.getChildren().add(cleaningOptions(row.getItem().getHall(), row.getItem().getRoom(), wardenMainTable));
                                }
                    });
                    return row;
        });

        //Stage setting
        stage.setScene(userSelection);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }
}
