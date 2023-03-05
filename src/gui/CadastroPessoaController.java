package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

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

    public void initialize() {
        // Define a máscara do CPF
        TextFormatter<String> cpfFormatter = new TextFormatter<>(change -> {
            if (change.isDeleted()) {
                return change;
            }
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,3}\\.?\\d{0,3}\\.?\\d{0,3}-?\\d{0,2}")) {
                if (newText.length() == 4 || newText.length() == 8){
                    change.setText(".");
                }else if (newText.length() == 12) {
                    change.setText("-");
                }
                return change;
            }
            return null;
        });
        cpf_txt.setTextFormatter(cpfFormatter);

        // Define a máscara do de nascimento
        TextFormatter<String> nascFormatter = new TextFormatter<>(change -> {
            if (change.isDeleted()) {
                return change;
            }
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,2}\\/?\\d{0,2}\\/?\\d{0,4}")) {
                if (newText.length() == 3 || newText.length() == 6){
                    change.setText("/");
                }
                return change;
            }
            return null;
        });
        nasc_txt.setTextFormatter(nascFormatter);
    }
    /*
     * @FXML
     * private void cpfKeyReleased() {
     * TextFieldFormatter tff = new TextFieldFormatter();
     * tff.setMask("###.###.###-##");
     * tff.setCaracteresValidos("0123456789");
     * tff.setTf(cpf_txt);
     * tff.formatter();
     * }
     * 
     * @FXML
     * private void nascKeyReleased() {
     * TextFieldFormatter tff = new TextFieldFormatter();
     * tff.setMask("##/##/####");
     * tff.setCaracteresValidos("0123456789");
     * tff.setTf(nasc_txt);
     * tff.formatter();
     * }
     */

}
