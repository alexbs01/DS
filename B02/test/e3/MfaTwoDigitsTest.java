package e3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MfaTwoDigitsTest {
    MfaTwoDigits mfaTwoDigits = new MfaTwoDigits();

    @Test
    void generateCode() {
        assertLinesMatch("\\b[0-9]{2}\\b".lines(), mfaTwoDigits.generateCode().lines());
    }
}