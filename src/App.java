import java.io.IOException;
import java.sql.Connection;

import db.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {

        Connection conn = DB.getConnection();

        launch(args);

        DB.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent parent = FXMLLoader.load(this.getClass().getResource("gui/Cadastro_Layout.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Cadastro de Pessoas");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
