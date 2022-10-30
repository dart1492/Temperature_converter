module com.example.demo_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo_project to javafx.fxml;
    exports com.example.demo_project;
}