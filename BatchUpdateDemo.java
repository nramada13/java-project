import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.sql.*;

public class BatchUpdateDemo extends Application {

    private Connection connection;
    private Label lblResult;
    private static final int NUM_RECORDS = 1000;

    @Override
    public void start(Stage primaryStage) {
        // Buttons for connecting and for insert tests
        Button btnConnect = new Button("Connect to Database");
        Button btnInsertWithoutBatch = new Button("Insert Without Batch");
        Button btnInsertWithBatch = new Button("Insert With Batch");
        btnInsertWithoutBatch.setDisable(true);
        btnInsertWithBatch.setDisable(true);
        lblResult = new Label("Not connected");

        // Connect button: open a dialog with DBConnectionPanel
        btnConnect.setOnAction(e -> {
            DBConnectionPanel dbPanel = new DBConnectionPanel();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Database Connection");
            dialog.getDialogPane().setContent(dbPanel);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    return ButtonType.OK;
                }
                return null;
            });

            dialog.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {
                    connection = dbPanel.getConnection();
                    if (connection != null) {
                        lblResult.setText("Connected to database!");
                        btnInsertWithoutBatch.setDisable(false);
                        btnInsertWithBatch.setDisable(false);
                    } else {
                        lblResult.setText("Connection failed!");
                    }
                }
            });
        });

        // Insert without batch: each record is inserted individually
        btnInsertWithoutBatch.setOnAction(e -> {
            if (connection != null) {
                long timeTaken = insertWithoutBatch();
                lblResult.setText("Without Batch: " + timeTaken + " ms");
            }
        });

        // Insert with batch: records are added to a batch and executed together
        btnInsertWithBatch.setOnAction(e -> {
            if (connection != null) {
                long timeTaken = insertWithBatch();
                lblResult.setText("With Batch: " + timeTaken + " ms");
            }
        });

        VBox vbox = new VBox(10, btnConnect, btnInsertWithoutBatch, btnInsertWithBatch, lblResult);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 400, 200);
        primaryStage.setTitle("Batch Update Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to insert records without using batch updates
    private long insertWithoutBatch() {
        String sql = "INSERT INTO Temp(num1, num2, num3) VALUES (?, ?, ?)";
        long startTime = System.currentTimeMillis();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < NUM_RECORDS; i++) {
                ps.setDouble(1, Math.random());
                ps.setDouble(2, Math.random());
                ps.setDouble(3, Math.random());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    // Method to insert records using batch updates
    private long insertWithBatch() {
        String sql = "INSERT INTO Temp(num1, num2, num3) VALUES (?, ?, ?)";
        long startTime = System.currentTimeMillis();
        try {
            connection.setAutoCommit(false); // disable auto-commit for batch efficiency
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (int i = 0; i < NUM_RECORDS; i++) {
                    ps.setDouble(1, Math.random());
                    ps.setDouble(2, Math.random());
                    ps.setDouble(3, Math.random());
                    ps.addBatch();
                }
                ps.executeBatch();
                connection.commit();
            }
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Inner class: DBConnectionPanel to allow users to input JDBC connection details
    class DBConnectionPanel extends BorderPane {
        private TextField tfDriver = new TextField("com.mysql.cj.jdbc.Driver");
        private TextField tfURL = new TextField("jdbc:mysql://localhost:3306/yourdb");
        private TextField tfUsername = new TextField();
        private PasswordField pfPassword = new PasswordField();
        private Button btnConnect = new Button("Connect to DB");
        private Connection connection;

        public DBConnectionPanel() {
            GridPane grid = new GridPane();
            grid.setHgap(5);
            grid.setVgap(5);
            grid.setPadding(new Insets(10));

            grid.add(new Label("JDBC Driver:"), 0, 0);
            grid.add(tfDriver, 1, 0);
            grid.add(new Label("Database URL:"), 0, 1);
            grid.add(tfURL, 1, 1);
            grid.add(new Label("Username:"), 0, 2);
            grid.add(tfUsername, 1, 2);
            grid.add(new Label("Password:"), 0, 3);
            grid.add(pfPassword, 1, 3);

            setCenter(grid);
            setBottom(btnConnect);
            BorderPane.setAlignment(btnConnect, Pos.CENTER);
            BorderPane.setMargin(btnConnect, new Insets(10));

            btnConnect.setOnAction(e -> connectToDB());
        }

        // Establish a database connection using the provided credentials
        private void connectToDB() {
            try {
                Class.forName(tfDriver.getText().trim());
                connection = DriverManager.getConnection(
                    tfURL.getText().trim(),
                    tfUsername.getText().trim(),
                    pfPassword.getText()
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Connected to database!", ButtonType.OK);
                alert.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Connection failed: " + ex.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        }

        public Connection getConnection() {
            return connection;
        }
    }
}
