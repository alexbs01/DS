package e3;

public interface MfaStrategy {
    /**
     * Genera un código numérico o alfabético en función de en que clase se implemente
     * @return Un String con 2 o 6 números o 4 letras
     */
    String generateCode();
}
