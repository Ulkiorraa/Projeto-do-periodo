import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

import db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();

        launch(args);

        DB.closeConnection();
    }

    @Override
    public void start(Stage stage) {
        try {
            ScrollPane scrollPane = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("gui/Cadastro_Layout.fxml")));
            scrollPane.setFitToHeight(true);
            scrollPane.setFitToWidth(true);
            Scene scene = new Scene(scrollPane);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Pessoas");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
