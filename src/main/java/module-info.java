module com.example.electronicrx_program {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.electronicrx_program to javafx.fxml;
    exports com.example.electronicrx_program;
}