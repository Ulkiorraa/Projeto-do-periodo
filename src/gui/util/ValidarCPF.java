package gui.util;

public class ValidarCPF {
    
    public boolean validarCPF(String cpf) {
        // Remove pontos e traços do CPF
        cpf = cpf.replaceAll("[^\\d]", "");
        
        // Verifica se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int resto = soma % 11;
        int digito1 = (resto < 2) ? 0 : (11 - resto);
        
        // Verifica o primeiro dígito verificador
        if (digito1 != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }
        
        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        resto = soma % 11;
        int digito2 = (resto < 2) ? 0 : (11 - resto);
        
        // Verifica o segundo dígito verificador
        if (digito2 != Character.getNumericValue(cpf.charAt(10))) {
            return false;
        }
        
        return true;
    }
    
}
