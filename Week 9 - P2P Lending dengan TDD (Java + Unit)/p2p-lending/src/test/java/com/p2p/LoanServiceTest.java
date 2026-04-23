package com.p2p;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import com.p2p.domain.Borrower;
import com.p2p.domain.Loan;
import com.p2p.service.LoanService;

public class LoanServiceTest {
    private static final Logger logger = LogManager.getLogger(LoanServiceTest.class);

    @Test
    void shouldRejectLoanWhenBorrowerNotVerified() {

        Borrower borrower = new Borrower(false, 700);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

       assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, amount);
        });
        
        logger.info("TC-01: Success - borrower not verified");
    }

    @Test
    void shouldRejectLoanWhenAmountIsZeroOrNegative() {

        Borrower borrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();

        BigDecimal amount = BigDecimal.valueOf(0); 

        assertThrows(IllegalArgumentException.class, () -> {
            loanService.createLoan(borrower, amount);
        });

        logger.info("TC-02: Success - invalid amount rejected");
    }

    @Test
    void shouldApproveLoanWhenCreditScoreHigh() {

        Borrower borrower = new Borrower(true, 700);
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        Loan loan = loanService.createLoan(borrower, amount);

        assertEquals(Loan.Status.APPROVED, loan.getStatus());

        logger.info("TC-03: Success - loan approved");
    }

    @Test
    void shouldRejectLoanWhenCreditScoreLow() {

        Borrower borrower = new Borrower(true, 500); 
        LoanService loanService = new LoanService();
        BigDecimal amount = BigDecimal.valueOf(1000);

        Loan loan = loanService.createLoan(borrower, amount);

        assertEquals(Loan.Status.REJECTED, loan.getStatus());
        
        logger.info("TC-04: Success - loan rejected");
    }
}