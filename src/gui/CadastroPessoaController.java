package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import Formatter.*;

public class CadastroPessoaController {

    @FXML
    private RadioButton SexoO;

    @FXML
    private Button bot_cadas;

    @FXML
    private TextField cpf_txt;

    @FXML
    private ComboBox<?> estado_civil;

    @FXML
    private TextField nasc_txt;

    @FXML
    private TextField nome_txt;

    @FXML
    private TextField prof_txt;

    @FXML
    private RadioButton sexoF;

    @FXML
    private RadioButton sexoM;

    @FXML
    private void cpfKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(cpf_txt);
        tff.formatter();
    }

    @FXML
    private void nascKeyReleased() {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##/##/####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(nasc_txt);
        tff.formatter();
    }

}
