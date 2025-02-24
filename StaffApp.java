import java.sql.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class StaffApp extends Application {
    // Text fields for each column in the Staff table
    private TextField tfId = new TextField();
    private TextField tfLastName = new TextField();
    private TextField tfFirstName = new TextField();
    private TextField tfMi = new TextField();
    private TextField tfAddress = new TextField();
    private TextField tfCity = new TextField();
    private TextField tfState = new TextField();
    private TextField tfTelephone = new TextField();
    private TextField tfEmail = new TextField();

    private Connection conn;

    @Override
    public void start(Stage primaryStage) {
        // Establish database connection (adjust driver, URL, username, and password as needed)
        connectToDatabase();

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));

        // Layout labels and text fields
        grid.add(new Label("ID:"), 0, 0);
        grid.add(tfId, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(tfLastName, 1, 1);
        grid.add(new Label("First Name:"), 0, 2);
        grid.add(tfFirstName, 1, 2);
        grid.add(new Label("MI:"), 0, 3);
        grid.add(tfMi, 1, 3);
        grid.add(new Label("Address:"), 0, 4);
        grid.add(tfAddress, 1, 4);
        grid.add(new Label("City:"), 0, 5);
        grid.add(tfCity, 1, 5);
        grid.add(new Label("State:"), 0, 6);
        grid.add(tfState, 1, 6);
        grid.add(new Label("Telephone:"), 0, 7);
        grid.add(tfTelephone, 1, 7);
        grid.add(new Label("Email:"), 0, 8);
        grid.add(tfEmail, 1, 8);

        // Buttons for operations
        Button btnView = new Button("View");
        Button btnInsert = new Button("Insert");
        Button btnUpdate = new Button("Update");
        HBox hBoxButtons = new HBox(10, btnView, btnInsert, btnUpdate);
        grid.add(hBoxButtons, 1, 9);

        // Set button actions
        btnView.setOnAction(e -> viewStaff());
        btnInsert.setOnAction(e -> insertStaff());
        btnUpdate.setOnAction(e -> updateStaff());

        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Staff Management");
        primaryStage.show();
    }

    // Establish connection to the database
    private void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Replace with your database URL, username, and password
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "username", "password");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // View a record based on the entered ID
    private void viewStaff() {
        try {
            String sql = "SELECT * FROM Staff WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfId.getText().trim());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tfLastName.setText(rs.getString("lastName"));
                tfFirstName.setText(rs.getString("firstName"));
                tfMi.setText(rs.getString("mi"));
                tfAddress.setText(rs.getString("address"));
                tfCity.setText(rs.getString("city"));
                tfState.setText(rs.getString("state"));
                tfTelephone.setText(rs.getString("telephone"));
                tfEmail.setText(rs.getString("email"));
            } else {
                showAlert("Record not found!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Insert a new staff record into the database
    private void insertStaff() {
        try {
            String sql = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfId.getText().trim());
            ps.setString(2, tfLastName.getText());
            ps.setString(3, tfFirstName.getText());
            ps.setString(4, tfMi.getText());
            ps.setString(5, tfAddress.getText());
            ps.setString(6, tfCity.getText());
            ps.setString(7, tfState.getText());
            ps.setString(8, tfTelephone.getText());
            ps.setString(9, tfEmail.getText());
            int r = ps.executeUpdate();
            if (r > 0)
                showAlert("Insert successful");
            else
                showAlert("Insert failed");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Update an existing staff record
    private void updateStaff() {
        try {
            String sql = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, " +
                         "state = ?, telephone = ?, email = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tfLastName.getText());
            ps.setString(2, tfFirstName.getText());
            ps.setString(3, tfMi.getText());
            ps.setString(4, tfAddress.getText());
            ps.setString(5, tfCity.getText());
            ps.setString(6, tfState.getText());
            ps.setString(7, tfTelephone.getText());
            ps.setString(8, tfEmail.getText());
            ps.setString(9, tfId.getText().trim());
            int r = ps.executeUpdate();
            if (r > 0)
                showAlert("Update successful");
            else
                showAlert("Update failed");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Utility method to display messages
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
