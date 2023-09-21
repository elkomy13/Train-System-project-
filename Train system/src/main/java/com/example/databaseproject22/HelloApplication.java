package com.example.databaseproject22;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import java.sql.*;
import java.util.Random;

public class HelloApplication extends Application {
    Admin admin = new Admin();
    Customer customer = new Customer();
    Route r = new Route();
    Train train = new Train();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Train Booking System");
        Scene scene = null;

        // Database connection details
        String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; " + "encrypt=true;trustServerCertificate=true;";

        Label label = new Label("Welcome to Our Train Booking System");
        Button adminButton = new Button("Admin");
        Button customerButton = new Button("Customer");


        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(label, adminButton, customerButton);

        adminButton.setOnAction(event -> {
            Stage adminStage = new Stage();
            adminStage.setTitle("Admin Login/Register");

            Button loginButton = new Button("Login");
            Button registerButton = new Button("Register");
            //

            loginButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");
            registerButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");
            label.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
            adminButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");
            customerButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");

            loginButton.setOnAction(loginEvent -> {
                Loginadmin();
            });

            registerButton.setOnAction(registerEvent -> {//
                RegisterAdmin();
            });
            GridPane adminGridPane = new GridPane();
            adminGridPane.setPadding(new Insets(20));
            adminGridPane.setVgap(100);
            adminGridPane.setHgap(100);
            adminGridPane.addRow(0, loginButton, registerButton);
            adminGridPane.setStyle("-fx-alignment: center");
            Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
            BackgroundImage background = new BackgroundImage(
                    wallpaperImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT
            );
            adminGridPane.setBackground(new Background(background));
            Scene adminScene = new Scene(adminGridPane, 800, 500);
            adminStage.setScene(adminScene);
            adminStage.show();

        });

        customerButton.setOnAction(event -> {
            Stage CustomerStage = new Stage();
            CustomerStage.setTitle("Customer Login/Register");

            Button loginButton = new Button("Login");
            loginButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");
            Button registerButton = new Button("Register");
            registerButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");

            loginButton.setOnAction(loginEvent -> {
                LOGINCUSTOMER();
            });

            registerButton.setOnAction(registerEvent -> {
                RegisterCustomer();
            });


            GridPane adminGridPane = new GridPane();

            adminGridPane.setPadding(new Insets(20));
            adminGridPane.setVgap(100);
            adminGridPane.setHgap(100);
            adminGridPane.addRow(0, loginButton, registerButton);
            adminGridPane.setStyle("-fx-alignment: center");
            Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
            BackgroundImage background = new BackgroundImage(
                    wallpaperImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT
            );
            adminGridPane.setBackground(new Background(background));

            Scene adminScene = new Scene(adminGridPane, 800, 500);
            // do a color red for the scene

            CustomerStage.setScene(adminScene);
            CustomerStage.show();

        });

        scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void RegisterAdmin(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Admin Registration");
        Label adminIdLabel = new Label("Admin ID:");
        adminIdLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        TextField adminIdTextField = new TextField();
        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: black;-fx-font-size: 20px; -fx-font-weight: bold;");
        TextField emailTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        PasswordField passwordField = new PasswordField();
        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");;
        TextField lastNameTextField = new TextField();
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center;");



        registerButton.setOnAction(event -> {
            int adminId = Integer.parseInt(adminIdTextField.getText());
            String email = emailTextField.getText();
            String password = passwordField.getText();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();

            Admin newAdmin = new Admin(adminId, firstName, lastName, email, password);

            if(newAdmin.addAdmin(newAdmin))
                ShowMessage("Added");
            else ShowMessage("Not Added");
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, adminIdLabel, adminIdTextField);
        gridPane.addRow(1, emailLabel, emailTextField);
        gridPane.addRow(2, passwordLabel, passwordField);
        gridPane.addRow(3, firstNameLabel, firstNameTextField);
        gridPane.addRow(4, lastNameLabel, lastNameTextField);
        gridPane.addRow(5, registerButton);

        registerButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px");

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void adminMenu(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Admin Menu");

        // Admin Menu Scene
        Button updateInfoButton = new Button("Update Admin Information");
        updateInfoButton.setStyle("-fx-background-radius: 20px; -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        VBox menuPane = new VBox(10);
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setPadding(new Insets(20));

        Label menuTitle = new Label("Admin Menu");
        menuTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: #000000;");

        updateInfoButton.setOnAction(event -> showUpdateAdminInfo());

        Button trainAndTripButton = new Button("Add or Update Train and Trip and Trip Cancellation");
        trainAndTripButton.setStyle("-fx-background-radius: 20px; -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");

        trainAndTripButton.setOnAction(event -> showTrainAndTripMenu());
        Button reportButton = new Button("Show The Report");
        reportButton.setStyle("-fx-background-radius: 20px; -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        reportButton.setOnAction(event -> getCounts());


        menuPane.getChildren().addAll(menuTitle, updateInfoButton, trainAndTripButton,reportButton);

        menuPane.setPrefSize(800, 500);

        Scene scene = new Scene(menuPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showTrainAndTripMenu() {
        Stage TTStage = new Stage();
        TTStage.setTitle("Train and Trip Menu");


        Button addtrainButton = new Button("Add Train");
        addtrainButton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");

        Button updatetrainButton = new Button("Update Train");
        updatetrainButton.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        Button addtripButton = new Button("Add Trip");
        addtripButton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        Button updatetripButton = new Button("Update Trip");
        updatetripButton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        Button DeleteTripButton = new Button("Delete Trip");
        DeleteTripButton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        Button ShowrouteButton = new Button("Show Route");
        ShowrouteButton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);


        Label menuTitle = new Label("Train and Trip");
        menuTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        addtrainButton.setOnAction(event -> AddTrain());
        updatetrainButton.setOnAction(event -> updatetrain());
        addtripButton.setOnAction(event -> AddTrip());
        updatetripButton.setOnAction(event -> updatetrip());
        DeleteTripButton.setOnAction(event -> tripCancelled());
        ShowrouteButton.setOnAction(event -> show_route());

        gridPane.addRow(0, addtrainButton, updatetrainButton);
        gridPane.addRow(1, addtripButton, updatetripButton);
        gridPane.addRow(2, DeleteTripButton);
        gridPane.addRow(3, ShowrouteButton);

        gridPane.setPrefSize(400, 300);

        VBox menuPane = new VBox(10);
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setPadding(new Insets(20));
        menuPane.getChildren().addAll(menuTitle, gridPane);


        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(menuPane);
        TTStage.setScene(scene);
        TTStage.show();
    }
    public void show_route()
    {
        Stage routeStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        try {
            Connection conn = null;
            String sql = "SELECT Route.Arrival_time, Route.Depature_time, Route.Trip_id, Route.Stop_no FROM Route INNER JOIN Trip ON Trip.Trip_id = Route.Trip_id";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            int rowIndex = 0;
            while (rs.next()) {
                int tripId = rs.getInt("Trip_id");
                int stopNo = rs.getInt("Stop_no");
                String arrivalTime = rs.getString("Arrival_time");
                String departureTime = rs.getString("Depature_time");

                Label tripIdLabel = new Label("Trip_id: " + tripId);
                tripIdLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
                Label stopNoLabel = new Label("Stop_no: " + stopNo);
                stopNoLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
                Label arrivalTimeLabel = new Label("Arrival_time: " + arrivalTime);
                arrivalTimeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: #000000;");
                Label departureTimeLabel = new Label("Departure_time: " + departureTime);
                departureTimeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #ffffff; -fx-text-fill: #000000;");

                // Add the labels to the grid pane at the specified row index
                gridPane.addRow(rowIndex++, tripIdLabel);
                gridPane.addRow(rowIndex++, stopNoLabel);
                gridPane.addRow(rowIndex++, arrivalTimeLabel);
                gridPane.addRow(rowIndex++, departureTimeLabel);
            }

            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Label menuTitle = new Label("Route");
        menuTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        VBox menuPane = new VBox(10);
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setPadding(new Insets(20));
        menuPane.getChildren().addAll(menuTitle, gridPane);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train4.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));
        gridPane.setPrefSize(800, 450);
        Scene scene = new Scene(menuPane);
        routeStage.setScene(scene);
        routeStage.show();
    }

    public void AddTrain() {
        Stage addStage = new Stage();
        addStage.setTitle("Add Train");

        Label trainIdLabel = new Label("Train ID:");
        trainIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");

        TextField trainIdTextField = new TextField();
        Label trainNameLabel = new Label("Train Name:");
        trainNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField trainNameTextField = new TextField();
        Label typeLabel = new Label("Train Type:");
        typeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField typeTextField = new TextField();
        Label availabilityLabel = new Label("Train Availability (true/false):");
        availabilityLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField availabilityTextField = new TextField();
        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");

        addButton.setOnAction(event -> {
            int trainId = Integer.parseInt(trainIdTextField.getText());
            String trainName = trainNameTextField.getText();
            String trainType = typeTextField.getText();
            boolean availability = Boolean.parseBoolean(availabilityTextField.getText());

            Train newTrain = new Train();
            newTrain.setTrain_id(trainId);
            newTrain.setTrain_name(trainName);
            newTrain.setTrain_type(trainType);
            newTrain.setTrain_Avaliable(availability);
            newTrain.setAdminID(admin.getAdmin_id());
            // Add the train to the database or perform any other desired actions
            if (newTrain.addTrain(admin.getAdmin_id(),newTrain)) {
                ShowMessage("Train Added");
            }
            else
            {
                ShowMessage("Train Not Added");
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, trainIdLabel, trainIdTextField);
        gridPane.addRow(1, trainNameLabel, trainNameTextField);
        gridPane.addRow(2, typeLabel, typeTextField);
        gridPane.addRow(3, availabilityLabel, availabilityTextField);
        gridPane.addRow(4, addButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train3.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        addStage.setScene(scene);
        addStage.show();
    }


    public void updatetrain(){
        Stage updatetrainstage=new Stage();
        updatetrainstage.setTitle("Update Train");

        Label trainIdLabel = new Label("Train ID:");
        trainIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField trainIdTextField = new TextField();
        Label trainNameLabel = new Label("Train Name:");
        trainNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField trainNameTextField = new TextField();
        Label typeLabel = new Label("Train Type:");
        typeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField typeTextField = new TextField();
        Label availabilityLabel = new Label("Train Availability (true/false):");
        availabilityLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField availabilityTextField = new TextField();
        Button updatetrainButton = new Button("Update");
        updatetrainButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        updatetrainButton.setOnAction(event -> {
            int trainId = Integer.parseInt(trainIdTextField.getText());
            String trainName = trainNameTextField.getText();
            String trainType = typeTextField.getText();
            boolean availability = Boolean.parseBoolean(availabilityTextField.getText());

            train.setTrain_id(trainId);
            train.setTrain_name(trainName);
            train.setTrain_type(trainType);
            train.setTrain_Avaliable(availability);
            train.setAdminID(admin.getAdmin_id());

            if (admin.getAdmin_id() == train.getAdminID() && trainId == train.getTrain_id())
            {
                if(train.UpdateTrain(trainId, admin.getAdmin_id(), train))
                    ShowMessage("Train Updated");

                else
                    ShowMessage("Train Not Updated");
            }
            else
                ShowMessage("Train Not Exist");

        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, trainIdLabel, trainIdTextField);
        gridPane.addRow(1, trainNameLabel, trainNameTextField);
        gridPane.addRow(2, typeLabel, typeTextField);
        gridPane.addRow(3, availabilityLabel, availabilityTextField);
        gridPane.addRow(4, updatetrainButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train3.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        updatetrainstage.setScene(scene);
        updatetrainstage.show();
    }



    public void AddTrip() {
        Stage addStage = new Stage();
        addStage.setTitle("Add Trip");

        // Create UI components for adding a trip
        Label trainID = new Label("Train ID:");
        trainID.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField trainIDTextField = new TextField();
        Label tripIdLabel = new Label("Trip ID:");
        tripIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripIdTextField = new TextField();
        Label tripDateLabel = new Label("Trip Date:");
        tripDateLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripDateTextField = new TextField();
        Label availableSeatsLabel = new Label("Available Seats:");
        availableSeatsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField availableSeatsTextField = new TextField();
        Label tripAvailabilityLabel = new Label("Trip Availability (true/false):");
        tripAvailabilityLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripAvailabilityTextField = new TextField();
        Label tripPriceLabel = new Label("Trip Price:");
        tripPriceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripPriceTextField = new TextField();
        Label destinationLabel = new Label("Destination:");
        destinationLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField destinationTextField = new TextField();
        Label arrivalTimeLabel = new Label("Arrival Time:");
        arrivalTimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField arrivalTimeTextField = new TextField();
        Label departureTimeLabel = new Label("Departure Time:");
        departureTimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField departureTimeTextField = new TextField();
        Label sourceLabel = new Label("Source:");
        sourceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField sourceTextField = new TextField();
        Button addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");

        addButton.setOnAction(event -> {
            int trainId = Integer.parseInt(trainIDTextField.getText());
            int tripId = Integer.parseInt(tripIdTextField.getText());
            String tripDate = tripDateTextField.getText();
            int availableSeats = Integer.parseInt(availableSeatsTextField.getText());
            boolean tripAvailability = Boolean.parseBoolean(tripAvailabilityTextField.getText());
            float tripPrice = Float.parseFloat(tripPriceTextField.getText());
            String destination = destinationTextField.getText();
            String arrivalTime = arrivalTimeTextField.getText();
            String departureTime = departureTimeTextField.getText();
            String source = sourceTextField.getText();

            Trip newTrip = new Trip();
            newTrip.setTrainID(trainId);
            newTrip.setTrip_ID(tripId);
            newTrip.setTrip_date(tripDate);
            newTrip.setAvailable_seats(availableSeats);
            newTrip.setTrain_Avaliable(tripAvailability);
            newTrip.setTrip_PRICE(tripPrice);
            newTrip.setDestination(destination);
            newTrip.setArrival_time(arrivalTime);
            newTrip.setDeparture_time(departureTime);
            newTrip.setSource(source);
            newTrip.setAdminID(admin.getAdmin_id());

            if(admin.getAdmin_id()==newTrip.getAdminID()&&trainId==newTrip.getTrainID())
            {
                if (newTrip.AddTrip(admin.getAdmin_id(), trainId, newTrip))
                    ShowMessage("Trip Added");
                else
                    ShowMessage("Trip Not Added");
            }
            else
                ShowMessage("Train ID Not Exist");
        });


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, trainID, trainIDTextField);
        gridPane.addRow(1, tripIdLabel, tripIdTextField);
        gridPane.addRow(2, tripDateLabel, tripDateTextField);
        gridPane.addRow(3, availableSeatsLabel, availableSeatsTextField);
        gridPane.addRow(4, tripAvailabilityLabel, tripAvailabilityTextField);
        gridPane.addRow(5, tripPriceLabel, tripPriceTextField);
        gridPane.addRow(6, destinationLabel, destinationTextField);
        gridPane.addRow(7, arrivalTimeLabel, arrivalTimeTextField);
        gridPane.addRow(8, departureTimeLabel, departureTimeTextField);
        gridPane.addRow(9, sourceLabel, sourceTextField);
        gridPane.addRow(10, addButton);


        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train3.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        addStage.setScene(scene);
        addStage.show();
    }
    public void tripCancelled(){
        Stage addStage = new Stage();
        addStage.setTitle("Cancel Trip");

        Label tripIdLabel = new Label("Trip ID:");
        tripIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripIdTextField = new TextField();
        Button cancelbutton = new Button("Delete");
        cancelbutton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");

        cancelbutton.setOnAction(event -> {
            int tripId = Integer.parseInt(tripIdTextField.getText());

            Trip newTrip = new Trip();
            newTrip.setTrip_ID(tripId);
            newTrip.setAdminID(admin.getAdmin_id());

            if(admin.getAdmin_id()==newTrip.getAdminID())
            {
                if (newTrip.DeleteTrip(admin.getAdmin_id(), tripId))
                    ShowMessage("Trip Deleted");
                else
                    ShowMessage("Trip Not Deleted");
            }
            else
                ShowMessage("Trip ID Not Exist");
        });
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train4.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.addRow(0, tripIdLabel, tripIdTextField);
        gridPane.addRow(1, cancelbutton);
        gridPane.setBackground(new Background(background));
        Scene scene = new Scene(gridPane, 800, 500);
        addStage.setScene(scene);
        addStage.show();

    }

    public void updatetrip(){
        Stage addStage = new Stage();
        addStage.setTitle("update Trip");

        Label tripIdLabel = new Label("Trip ID:");
        tripIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripIdTextField = new TextField();
        Label trainIDlabel = new Label("Train ID:");
        trainIDlabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField trainIDTextField = new TextField();
        Label tripDateLabel = new Label("Trip Date:");
        tripDateLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripDateTextField = new TextField();
        Label availableSeatsLabel = new Label("Available Seats:");
        availableSeatsLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField availableSeatsTextField = new TextField();
        Label tripAvailabilityLabel = new Label("Trip Availability (true/false):");
        tripAvailabilityLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripAvailabilityTextField = new TextField();
        Label tripPriceLabel = new Label("Trip Price:");
        tripPriceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripPriceTextField = new TextField();
        Label destinationLabel = new Label("Destination:");
        destinationLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField destinationTextField = new TextField();
        Label arrivalTimeLabel = new Label("Arrival Time:");
        arrivalTimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField arrivalTimeTextField = new TextField();
        Label departureTimeLabel = new Label("Departure Time:");
        departureTimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField departureTimeTextField = new TextField();
        Label sourceLabel = new Label("Source:");
        sourceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField sourceTextField = new TextField();
        Button updatetripButton = new Button("Update");
        updatetripButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff");
        updatetripButton.setOnAction(event->{

            int tripId = Integer.parseInt(tripIdTextField.getText());
            int trainId=Integer.parseInt(trainIDTextField.getText());
            String tripDate = tripDateTextField.getText();
            int availableSeats = Integer.parseInt(availableSeatsTextField.getText());
            boolean tripAvailability = Boolean.parseBoolean(tripAvailabilityTextField.getText());
            float tripPrice = Float.parseFloat(tripPriceTextField.getText());
            String destination = destinationTextField.getText();
            String arrivalTime = arrivalTimeTextField.getText();
            String departureTime = departureTimeTextField.getText();
            String source = sourceTextField.getText();

            Trip newTrip = new Trip();
            newTrip.setTrainID(trainId);
            newTrip.setTrip_ID(tripId);
            newTrip.setTrip_date(tripDate);
            newTrip.setAvailable_seats(availableSeats);
            newTrip.setTrain_Avaliable(tripAvailability);
            newTrip.setTrip_PRICE(tripPrice);
            newTrip.setDestination(destination);
            newTrip.setArrival_time(arrivalTime);
            newTrip.setDeparture_time(departureTime);
            newTrip.setSource(source);
            newTrip.setAdminID(admin.getAdmin_id());

            if (admin.getAdmin_id() == newTrip.getAdminID() && tripId == newTrip.getTrip_ID())
            {
                if(newTrip.UpdateTrip(admin.getAdmin_id(), trainId,newTrip))
                    ShowMessage("Trip Updated");

                else
                    ShowMessage("Trip Not Updated");
            }
            else
                ShowMessage("Trip Not Exist");

        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, tripIdLabel, tripIdTextField);
        gridPane.addRow(1, trainIDlabel, trainIDTextField);
        gridPane.addRow(2, tripDateLabel, tripDateTextField);
        gridPane.addRow(3, availableSeatsLabel, availableSeatsTextField);
        gridPane.addRow(4, tripAvailabilityLabel, tripAvailabilityTextField);
        gridPane.addRow(5, tripPriceLabel, tripPriceTextField);
        gridPane.addRow(6, destinationLabel, destinationTextField);
        gridPane.addRow(7, arrivalTimeLabel, arrivalTimeTextField);
        gridPane.addRow(8, departureTimeLabel, departureTimeTextField);
        gridPane.addRow(9, sourceLabel, sourceTextField);
        gridPane.addRow(10, updatetripButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train3.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        addStage.setScene(scene);
        addStage.show();
    }
    public void Showtriplist() {
        try {
            Connection conn = null;
            String sql = "SELECT Trip_id, Trip_date, Available_Seats , Trip_Available, Trip_Price,  Destination, Arrival_time, Departure_time, Source, TrainTrain_id FROM Trip";
            String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; " + "encrypt=true;trustServerCertificate=true;";
            conn = DriverManager.getConnection(jdbcUrl);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setPadding(new Insets(20));
            gridPane.setVgap(10);
            gridPane.setHgap(10);

            int rowIndex = 0;
            while (rs.next()) {
                int tripId = rs.getInt("Trip_id");
                String tripDate = rs.getString("Trip_date");
                int tripCapacity = rs.getInt("Available_Seats");
                boolean tripAvailable = rs.getBoolean("Trip_Available");
                float tripPrice = rs.getFloat("Trip_Price");
                String destination = rs.getString("Destination");
                String arrivalTime = rs.getString("Arrival_time");
                String departureTime = rs.getString("Departure_time");
                String source = rs.getString("Source");
                int trainId = rs.getInt("TrainTrain_id");

                Label tripIdLabel = new Label("Trip ID: " + tripId);
                tripIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");
                Label tripDateLabel = new Label("Trip Date: " + tripDate);
                tripDateLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");

                Label tripCapacityLabel = new Label("Trip Capacity: " + tripCapacity);
                tripCapacityLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");

                Label tripAvailableLabel = new Label("Trip Available: " + tripAvailable);
                tripAvailableLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");

                Label tripPriceLabel = new Label("Trip Price: " + tripPrice);
                tripPriceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");

                Label destinationLabel = new Label("Destination: " + destination);
                destinationLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");

                Label arrivalTimeLabel = new Label("Arrival Time: " + arrivalTime);
                arrivalTimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");

                Label departureTimeLabel = new Label("Departure Time: " + departureTime);
                departureTimeLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");

                Label sourceLabel = new Label("Source: " + source);
                sourceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");

                Label trainIdLabel = new Label("Train ID: " + trainId);
                trainIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 10px; -fx-text-fill: #000000; -fx-background-color: #febd;");


                gridPane.addRow(rowIndex++, tripIdLabel);
                gridPane.addRow(rowIndex++, tripDateLabel);
                gridPane.addRow(rowIndex++, tripCapacityLabel);
                gridPane.addRow(rowIndex++, tripAvailableLabel);
                gridPane.addRow(rowIndex++, tripPriceLabel);
                gridPane.addRow(rowIndex++, destinationLabel);
                gridPane.addRow(rowIndex++, arrivalTimeLabel);
                gridPane.addRow(rowIndex++, departureTimeLabel);
                gridPane.addRow(rowIndex++, sourceLabel);
                gridPane.addRow(rowIndex++, trainIdLabel);

                Separator separator = new Separator(Orientation.HORIZONTAL);
                GridPane.setColumnSpan(separator, 2);
                gridPane.addRow(rowIndex++, separator);
            }

            Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
            BackgroundImage background = new BackgroundImage(
                    wallpaperImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT
            );
            gridPane.setBackground(new Background(background));

            Scene scene = new Scene(gridPane, 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Seats List");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void booking() {
        Stage addStage = new Stage();
        addStage.setTitle("Make a Booking");

        Label tripIdLabel = new Label("Trip ID:");
        tripIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField tripIdTextField = new TextField();
        Label seatNoLabel = new Label("Seat Number:");
        seatNoLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField seatNoTextField = new TextField();
        Label customerIdLabel = new Label("Customer ID:");
        customerIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField customerIdTextField = new TextField();
        Button addButton = new Button("Book");


        addButton.setOnAction(event -> {
            int tripId = Integer.parseInt(tripIdTextField.getText());
            int seatNo = Integer.parseInt(seatNoTextField.getText());
            int customerId = Integer.parseInt(customerIdTextField.getText());

            Random random = new Random();
            int ticketNumber = random.nextInt(9000) + 1000;

            Booking book = new Booking();
            book.setTripID(tripId);
            book.setSeat_num(seatNo);
            book.setCustomerID(customerId);
            book.setTicket_num(ticketNumber);

            if (customer.getCust_id() == book.getCustomerID()) {
                if (book.bookTicket(book, tripId, seatNo, customerId))
                    ShowMessage("Trip Booked! Ticket Number: " + ticketNumber);
                else
                    ShowMessage("Trip Not Booked!");
            }
            else
                ShowMessage("Customer Not Exist");
        });

        addButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, tripIdLabel, tripIdTextField);
        gridPane.addRow(1, seatNoLabel, seatNoTextField);
        gridPane.addRow(2, customerIdLabel, customerIdTextField);
        gridPane.addRow(3, addButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train3.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 600);
        addStage.setScene(scene);
        addStage.show();
    }



    public void showUpdateAdminInfo(){
        Stage primaryStage7 = new Stage();

        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField lastNameTextField = new TextField();
        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField emailTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        PasswordField passwordField = new PasswordField();
        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        updateButton.setOnAction(event -> {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String email = emailTextField.getText();
            String password = passwordField.getText();

            Admin updatedAdmin = new Admin(admin.getAdmin_id(), firstName, lastName, email, password);

            if(admin.Update_adminData(admin.getAdmin_id(), updatedAdmin))
                ShowMessage("Admin data Updated");
            else
                ShowMessage("Admin data Not Updated");
        });

        updateButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, firstNameLabel, firstNameTextField);
        gridPane.addRow(1, lastNameLabel, lastNameTextField);
        gridPane.addRow(2, emailLabel, emailTextField);
        gridPane.addRow(3, passwordLabel, passwordField);
        gridPane.addRow(4, updateButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage7.setScene(scene);
        primaryStage7.show();

    }
    public void RegisterCustomer(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Customer Registration");
        Label customerIdLabel = new Label("Customer ID:");
        customerIdLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField customerIdTextField = new TextField();
        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField emailTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        PasswordField passwordField = new PasswordField();
        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField lastNameTextField = new TextField();
        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField phoneNumberTextField = new TextField();
        Button registerButton = new Button("Register");
        registerButton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");

        registerButton.setOnAction(event -> {
            int customerId = Integer.parseInt(customerIdTextField.getText());
            String email = emailTextField.getText();
            String password = passwordField.getText();
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();

            Customer newCustomer = new Customer();
            newCustomer.setCust_id(customerId);
            newCustomer.setEmail(email);
            newCustomer.setPassword(password);
            newCustomer.setFname(firstName);
            newCustomer.setLname(lastName);
            newCustomer.setPhone(phoneNumber);

            if(newCustomer.addCustomer(newCustomer))
                ShowMessage("Added");
            else ShowMessage("Not Added");
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, customerIdLabel, customerIdTextField);
        gridPane.addRow(1, emailLabel, emailTextField);
        gridPane.addRow(2, passwordLabel, passwordField);
        gridPane.addRow(3, firstNameLabel, firstNameTextField);
        gridPane.addRow(4, lastNameLabel, lastNameTextField);
        gridPane.addRow(5, phoneNumberLabel, phoneNumberTextField);
        gridPane.addRow(6, registerButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void Loginadmin(){
        Stage primaryStage1 = new Stage();
        primaryStage1.setTitle("Admin Login");

        Label adminIdLabel = new Label("Admin ID:");
        adminIdLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");
        TextField adminIdTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold;");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color:#5dd58b; -fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size: 20px;");

        loginButton.setOnAction(event -> {
            int adminId = Integer.parseInt(adminIdTextField.getText());
            String password = passwordField.getText();
            admin.setAdmin_id(adminId);
            admin.setPassword(password);

            if (admin.isAdminExists(adminId, password))
            {
                ShowMessage2("Admin login successful");
                adminMenu();
            } else
            {
                ShowMessage2("Admin login failed");
            }
        });


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, adminIdLabel, adminIdTextField);
        gridPane.addRow(1, passwordLabel, passwordField);
        gridPane.addRow(2, loginButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage1.setScene(scene);
        primaryStage1.show();

    }
    public void customerMenu() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Customer Menu");

        Button updateInfoButton = new Button("Update Customer Information");
        updateInfoButton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        Button bookingbutton = new Button("Book Ticket");
        bookingbutton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        Button Showingtrips = new Button("Show Seats");
        Showingtrips.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        Button cancelBookingButton = new Button("Cancel Booking");
        cancelBookingButton.setStyle(" -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");
        VBox menuPane = new VBox(10);
        menuPane.setAlignment(Pos.CENTER);
        menuPane.setPadding(new Insets(20));

        
        Label menuTitle = new Label("Customer Menu");
        menuTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: #000000;");

        updateInfoButton.setOnAction(event -> showUpdateCustomerInfo());
        Showingtrips.setOnAction(event -> Showtriplist());
        bookingbutton.setOnAction(event -> booking());
        cancelBookingButton.setOnAction(event -> cancelBooking());

        menuPane.getChildren().addAll(menuTitle, updateInfoButton, bookingbutton,Showingtrips ,cancelBookingButton);

        menuPane.setPrefSize(800, 500);

        Scene scene = new Scene(menuPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void cancelBooking() {
        Stage cancelStage = new Stage();
        cancelStage.setTitle("Cancel Booking");

        Label ticketNumberLabel = new Label("Ticket Number:");
        ticketNumberLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField ticketNumberTextField = new TextField();
        Label tripidLabel = new Label("Trip ID:");
        tripidLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField tripidTextField = new TextField();
        Label customerIdLabel = new Label("Customer ID:");
        customerIdLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField customerIdTextField = new TextField();
        Button cancelButton = new Button("Cancel Booking");

        cancelButton.setOnAction(event -> {
            int ticketNumber = Integer.parseInt(ticketNumberTextField.getText());
            int tripid = Integer.parseInt(tripidTextField.getText());
            int customerId = Integer.parseInt(customerIdTextField.getText());


            Booking booking = new Booking();
            booking.setTicket_num(ticketNumber);
            booking.setTripID(tripid);
            booking.setCustomerID(customerId);

            if(booking.CancelBooking(booking, tripid, ticketNumber, customerId))
                ShowMessage("Booking Cancelled");
            else
                ShowMessage("Booking Not Cancelled");
        });
        cancelButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, ticketNumberLabel, ticketNumberTextField);
        gridPane.addRow(1, tripidLabel, tripidTextField);
        gridPane.addRow(2, customerIdLabel, customerIdTextField);
        gridPane.addRow(3, cancelButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        cancelStage.setScene(scene);
        cancelStage.show();
    }

    public void LOGINCUSTOMER(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Customer Login");

        Label customerIdLabel = new Label("Customer ID:");
        customerIdLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-size: bold;");
        TextField customerIdTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 20px; -fx-font-size: bold;");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(event -> {
            int customerId = Integer.parseInt(customerIdTextField.getText());
            String password = passwordField.getText();
            customer.setCust_id(customerId);
            customer.setPassword(password);
            if (customer.isCustomerExists(customerId, password))
            {
                ShowMessage2("Customer login successful");
                customerMenu();
            }
            else
            {
                ShowMessage2("Customer login failed");
            }
        });

        loginButton.setStyle("-fx-background-color: #5dd58b; -fx-text-fill: #ffffff;");


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, customerIdLabel, customerIdTextField);
        gridPane.addRow(1, passwordLabel, passwordField);
        gridPane.addRow(2, loginButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train2.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public void showUpdateCustomerInfo() {
        Stage primaryStage = new Stage();

        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField firstNameTextField = new TextField();
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField lastNameTextField = new TextField();
        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField emailTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        PasswordField passwordField = new PasswordField();
        Label phoneNumberLabel = new Label("Phone Number:");
        phoneNumberLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #000000;");
        TextField phoneNumberTextField = new TextField();
        Button updateButton = new Button("Update");

        updateButton.setOnAction(event -> {
            String firstName = firstNameTextField.getText();
            String lastName = lastNameTextField.getText();
            String email = emailTextField.getText();
            String password = passwordField.getText();
            String phoneNumber = phoneNumberTextField.getText();

            Customer updatedCustomer = new Customer(customer.getCust_id(),firstName, lastName, email, password,phoneNumber);
            if (customer.Update_Customer(customer.getCust_id(), updatedCustomer))
                Updatedmessage("Customer data Updated");
            else
                Updatedmessage("Customer data Not Updated");
        });

        updateButton.setStyle("-fx-background-color:#5dd58b; -fx-text-fill: #ffffff;");


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.addRow(0, firstNameLabel, firstNameTextField);
        gridPane.addRow(1, lastNameLabel, lastNameTextField);
        gridPane.addRow(2, emailLabel, emailTextField);
        gridPane.addRow(3, passwordLabel, passwordField);
        gridPane.addRow(4, phoneNumberLabel, phoneNumberTextField);
        gridPane.addRow(5, updateButton);

        Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\train3.jpg");
        BackgroundImage background = new BackgroundImage(
                wallpaperImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        gridPane.setBackground(new Background(background));

        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void ShowMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(message);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void ShowMessage2(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(message);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void Updatedmessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(message);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void getCounts() {
        String jdbcUrl = "jdbc:sqlserver://LAPTOP-8G6P2TIL\\MSSQLSERVER:1433;database=TrainBooking;integratedSecurity=true; " + "encrypt=true;trustServerCertificate=true;";
        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             Statement stmt = conn.createStatement()) {
            Label customerCountLabel = new Label();
            Label bookCountLabel = new Label();
            Label tripCountLabel = new Label();
            Label trainCountLabel = new Label();
            Label RouteCountLabel = new Label();

            String customerQuery = "SELECT COUNT(*) FROM Customer";
            ResultSet customerResult = stmt.executeQuery(customerQuery);
            if (customerResult.next()) {
                int customerCount = customerResult.getInt(1);
                customerCountLabel.setStyle("-fx-text-fill: #000000;-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: #faebd7" );
                customerCountLabel.setText("Customer Count: " + customerCount);

            }

            String bookQuery = "SELECT COUNT(*) FROM Booking";
            ResultSet bookResult = stmt.executeQuery(bookQuery);
            if (bookResult.next()) {
                int bookCount = bookResult.getInt(1);
                bookCountLabel.setStyle("-fx-text-fill: #000000;-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: #faebd7");
                bookCountLabel.setText("Book Count: " + bookCount);
            }

            String tripQuery = "SELECT COUNT(*) FROM Trip";
            ResultSet tripResult = stmt.executeQuery(tripQuery);
            if (tripResult.next()) {
                int tripCount = tripResult.getInt(1);
                tripCountLabel.setStyle("-fx-text-fill: #000000;-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: #faebd7");
                tripCountLabel.setText("Trip Count: " + tripCount);
            }

            String trainQuery = "SELECT COUNT(*) FROM Train";
            ResultSet trainResult = stmt.executeQuery(trainQuery);
            if (trainResult.next()) {
                int trainCount = trainResult.getInt(1);
                trainCountLabel.setStyle("-fx-text-fill: #000000;-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: #faebd7");
                trainCountLabel.setText("Train Count: " + trainCount);
            }

            String RouteQuery = "SELECT COUNT(*) FROM Route";
            ResultSet RouteResult = stmt.executeQuery(RouteQuery);
            if (RouteResult.next()) {
                int RouteCount = RouteResult.getInt(1);
                RouteCountLabel.setStyle("-fx-text-fill: #000000;-fx-font-size: 20px; -fx-font-weight: bold; -fx-background-color: #faebd7");
                RouteCountLabel.setText("Route Count: " + RouteCount);
            }

            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setPadding(new Insets(20));
            gridPane.setVgap(10);
            gridPane.setHgap(10);
            gridPane.addRow(0, customerCountLabel);
            gridPane.addRow(1, bookCountLabel);
            gridPane.addRow(2, tripCountLabel);
            gridPane.addRow(3, trainCountLabel);
            gridPane.addRow(4, RouteCountLabel);

            Image wallpaperImage = new Image("C:\\Users\\ziade\\IdeaProjects\\Databaseproject22\\src\\main\\java\\com\\example\\databaseproject22\\Train.jpg");
            BackgroundImage background = new BackgroundImage(
                    wallpaperImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT
            );
            gridPane.setBackground(new Background(background));

            Scene scene = new Scene(gridPane, 800, 500);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}