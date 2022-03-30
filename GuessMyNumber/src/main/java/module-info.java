module com.guessgame.guessmynumber {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.guessgame.guessmynumber to javafx.fxml;
    exports com.guessgame.guessmynumber;
}