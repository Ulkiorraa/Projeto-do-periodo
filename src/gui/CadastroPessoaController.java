package gui;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;

public class CadastroPessoaController {

    @FXML
    private TextField nome_txt;

    @FXML
    private TextField prof_txt;

    @FXML
    private RadioButton SexoO;

    @FXML
    private Button bot_cadas;

    @FXML
    private TextField cpf_txt;

    @FXML
    private ComboBox<String> estado_civil;

    @FXML
    private TextField nasc_txt;

    @FXML
    private RadioButton sexoF;

    @FXML
    private RadioButton sexoM;

    private ToggleGroup sexoToggleGroup;

    public void initialize() {
        // Define a máscara do CPF
        TextFormatter<String> cpfFormatter = new TextFormatter<>(change -> {
            if (change.isDeleted()) {
                return change;
            }
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0,3}\\.?\\d{0,3}\\.?\\d{0,3}-?\\d{0,2}")) {
                if (newText.length() == 4 || newText.length() == 8) {
                    change.setText(".");
                } else if (newText.length() == 12) {
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
                if (newText.length() == 3 || newText.length() == 6) {
                    change.setText("/");
                }
                return change;
            }
            return null;
        });
        nasc_txt.setTextFormatter(nascFormatter);

        // Define o TextFormatter para os campos nome
        TextFormatter<String> noNumbersFormatter = new TextFormatter<>(change -> {
            if (change.isDeleted()) {
                return change;
            }
            String newText = change.getControlNewText();
            if (newText.matches("\\D*")) {
                return change;
            }
            return null;
        });
        nome_txt.setTextFormatter(noNumbersFormatter);

        // Define o TextFormatter para os campos profissão
        TextFormatter<String> noNumbersFormatter2 = new TextFormatter<>(change -> {
            if (change.isDeleted()) {
                return change;
            }
            String newText = change.getControlNewText();
            if (newText.matches("\\D*")) {
                return change;
            }
            return null;
        });
        prof_txt.setTextFormatter(noNumbersFormatter2);

        // Define o ToggleGroup para os RadioButtons de sexo
        sexoToggleGroup = new ToggleGroup();
        sexoM.setToggleGroup(sexoToggleGroup);
        sexoF.setToggleGroup(sexoToggleGroup);
        SexoO.setToggleGroup(sexoToggleGroup);

        // Define as opções do ComboBox de estado civil
        List<String> opList = new ArrayList<>();
        opList.add("Solteiro(a)");
        opList.add("Casado(a)");
        opList.add("Divorciado(a)");
        opList.add("Viúvo(a)");
        for (String e : opList) {
            estado_civil.getItems().add(e);
        }

        // Adiciona um listener ao botão de cadastro
        bot_cadas.setOnAction(event -> cadastrarPessoa());
    }

    private void cadastrarPessoa() {
        Alerts alerts = new Alerts();
        String nome = nome_txt.getText().trim();
        String cpf = cpf_txt.getText().replaceAll("\\D", "");
        String dataNascimento = nasc_txt.getText().trim();
        String sexo;
        String profissao = prof_txt.getText().trim();

        // Obtenção do estado civil selecionado
        String estadoCivil = estado_civil.getSelectionModel().getSelectedItem();

        // Validação dos campos obrigatórios
        if (nome.isEmpty()) {
            alerts.mostrarMensagemDeErro("Nome é um campo obrigatório.");
            return;
        }
        if (cpf.isEmpty()) {
            alerts.mostrarMensagemDeErro("CPF é um campo obrigatório.");
            return;
        }
        if (dataNascimento.isEmpty()) {
            alerts.mostrarMensagemDeErro("Data de nascimento é um campo obrigatório.");
            return;
        }
        if (estado_civil.getSelectionModel().isEmpty()) {
            alerts.mostrarMensagemDeErro("Estado civil é um campo obrigatório.");
            return;
        }

        // Validação do sexo
        if (sexoF.isSelected()) {
            sexo = "Feminino";
        } else if (sexoM.isSelected()) {
            sexo = "Masculino";
        } else if (SexoO.isSelected()) {
            sexo = "Outro";
        } else {
            alerts.mostrarMensagemDeErro("Selecione o sexo.");
            return;
        }

        // Obtenção da data de nascimento
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNasc;
        try {
            dataNasc = LocalDate.parse(dataNascimento, fmt1);
        } catch (Exception e) {
            alerts.mostrarMensagemDeErro("Data de nascimento inválida.");
            return;
        }

        // Cálculo da idade
        int idade = Period.between(dataNasc, LocalDate.now()).getYears();

        // Validação da profissão
        if (profissao.isEmpty()) {
            profissao = "Desempregado(a)";
        } else if (profissao.equalsIgnoreCase("Engenheiro") || profissao.equalsIgnoreCase("Analista de sistemas")) {
            alerts.mostrarMensagem("Há vagas disponíveis para " + profissao + ".");
        }

        // Exibição dos dados da pessoa
        alerts.mostrarMensagem(
                "Nome: " + nome +
                "\nIdade: " + idade + " anos" +
                "\nSexo: " + sexo +
                "\nEstado civil: " + estadoCivil +
                "\nProfissão: " + profissao 
        );
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
