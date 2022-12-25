package e3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MfaFourLettersTest {
    MfaFourLetters mfaFourLetters = new MfaFourLetters();
    @Test
    void generateCode() {
        assertLinesMatch("\\b[A-Z]{4}\\b".lines(), mfaFourLetters.generateCode().lines());
    }
}