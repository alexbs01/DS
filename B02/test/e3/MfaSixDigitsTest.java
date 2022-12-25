package e3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MfaSixDigitsTest {
    MfaSixDigits mfaSixDigits = new MfaSixDigits();

    @Test
    void generateCode() {
        assertLinesMatch("\\b[0-9]{6}\\b".lines(), mfaSixDigits.generateCode().lines());
    }
}