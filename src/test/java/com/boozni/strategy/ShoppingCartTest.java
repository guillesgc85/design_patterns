package com.boozni.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShoppingCartTest {

    private ShoppingCart shoppingCart;
    private PaymentStrategy creditCardPayment;
    private PaymentStrategy payPalPayment;
    private PaymentStrategy bitcoinPayment;

    @BeforeEach
    public void setUp() {
        shoppingCart = new ShoppingCart();

        // Create mock strategies
        creditCardPayment = Mockito.mock(PaymentStrategy.class);
        payPalPayment = Mockito.mock(PaymentStrategy.class);
        bitcoinPayment = Mockito.mock(PaymentStrategy.class);
    }

    @Test
    public void shouldUseCreditCardPaymentStrategy() {
        shoppingCart.setPaymentStrategy(creditCardPayment);
        shoppingCart.checkout(100);

        // Verify that the credit card payment strategy was invoked with the correct amount
        verify(creditCardPayment, times(1)).pay(100);
    }

    @Test
    public void shouldUsePayPalPaymentStrategy() {
        shoppingCart.setPaymentStrategy(payPalPayment);
        shoppingCart.checkout(200);

        // Verify that the PayPal payment strategy was invoked with the correct amount
        verify(payPalPayment, times(1)).pay(200);
    }

    @Test
    public void shouldUseBitcoinPaymentStrategy() {
        shoppingCart.setPaymentStrategy(bitcoinPayment);
        shoppingCart.checkout(300);

        // Verify that the Bitcoin payment strategy was invoked with the correct amount
        verify(bitcoinPayment, times(1)).pay(300);
    }

    @Test
    public void shouldLogCorrectMessagesForDifferentPaymentStrategies() {

        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        // Pay using credit card
        shoppingCart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        shoppingCart.checkout(100);

        // Switch to PayPal payment
        shoppingCart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        shoppingCart.checkout(200);

        // Switch to Bitcoin payment
        shoppingCart.setPaymentStrategy(new BitcoinPayment("1ABC2DEF3GHI4JKL"));
        shoppingCart.checkout(300);

        // Capture the console output and assert the correct log messages
        String expectedOutput = "Paid 100 using Credit Card 1234-5678-9012-3456\n" +
                "Paid 200 using PayPal user@example.com\n" +
                "Paid 300 using Bitcoin wallet 1ABC2DEF3GHI4JKL\n";
        assertEquals(expectedOutput.trim(), outputStreamCaptor.toString().trim());
    }
}
