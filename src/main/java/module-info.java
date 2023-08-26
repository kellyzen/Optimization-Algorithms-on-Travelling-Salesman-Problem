module com.example.comp2024cwgroup6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.comp2024cwgroup6 to javafx.fxml;
    exports com.example.comp2024cwgroup6;
    exports com.example.comp2024cwgroup6.GA;
    opens com.example.comp2024cwgroup6.GA to javafx.fxml;
    exports com.example.comp2024cwgroup6.SA;
    opens com.example.comp2024cwgroup6.SA to javafx.fxml;
}