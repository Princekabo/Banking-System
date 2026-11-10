package banking;

import banking.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleTest {

    @Test
    void savingsCannotWithdraw() {
        SavingsAccount s = new SavingsAccount("A1", 100, "Gabs", "C1");
        assertFalse(s.withdraw(50));
        assertEquals(100, s.getBalance());
    }

    @Test
    void investmentNeeds500() {
        assertThrows(IllegalArgumentException.class,
            () -> new InvestmentAccount("A2", 100, "Gabs", "C1"));
    }

    @Test
    void interestIsCorrect() {
        SavingsAccount s = new SavingsAccount("A1", 1000, "Gabs", "C1");
        assertEquals(0.5, s.calculateInterest(), 0.001);

        InvestmentAccount i = new InvestmentAccount("A2", 1000, "Gabs", "C1");
        assertEquals(50, i.calculateInterest(), 0.001);
    }
}