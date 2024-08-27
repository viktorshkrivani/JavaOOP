module com.example.fmov {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fmov to javafx.fxml;
    exports com.example.fmov;
}