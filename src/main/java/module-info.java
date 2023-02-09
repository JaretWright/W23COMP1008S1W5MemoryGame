module com.example.w23comp1008s1w5memorygame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w23comp1008s1w5memorygame to javafx.fxml;
    exports com.example.w23comp1008s1w5memorygame;
}