package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Account;
import tests.factory.AccountFactory;

public class AccountTests {
	
	@Test
	public void depositShouldIncreaseBalanceAndDiscountFeeWhenPositiveAmount() {
		double amount =  200.00;
		double expectedValue = 196.00;
		Account acc = AccountFactory.createEmptyAccount();
		
		acc.deposit(amount);
		
		Assertions.assertEquals(expectedValue, acc.getBalance());
	}
	
	@Test
	public void depositShouldDoNothingWhenNegativeAmount() {
		double expectedValue = 100.0;
		Account acc = AccountFactory.createAccount(expectedValue);
		double amount = -200.00;
		
		acc.deposit(amount);
		Assertions.assertEquals(expectedValue, acc.getBalance());
	}
	
	@Test
	public void fullWithDrawShouldClearBalanceAndReturnFullBalance() {
		double expectedValue = 0.0;
		double initialBalance = 800.0;
		Account acc = AccountFactory.createAccount(initialBalance);
		
		double result = acc.fullWithDraw();
	
		Assertions.assertTrue(expectedValue == acc.getBalance());
		Assertions.assertTrue(result == initialBalance);
	}
	
	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {
		Account acc = AccountFactory.createAccount(800.0);
		
		acc.withDraw(500.0);
		
		Assertions.assertEquals(300.0, acc.getBalance());
	}
	
	@Test
	public void withdrawShouldThrowExceptionWhenInsufficientBalance() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Account acc = AccountFactory.createAccount(800.0);
			acc.withDraw(801.0);
		});
	}
}
