package banking;

import java.util.LinkedHashMap;

/**
 * Private Variables:<br>
 * {@link #accounts}: List&lt;Long, Account&gt;
 */
public class Bank implements BankInterface {
	private LinkedHashMap<Long, Account> accounts = new LinkedHashMap<>();

	public Bank() {

	}

	private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
	}

	public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
		Long accountNumber = Account.getNewAccountNumber();
		CommercialAccount commercialAccount = new CommercialAccount(company, accountNumber, pin, startingDeposit);
		accounts.put(accountNumber, commercialAccount);
        return commercialAccount.getAccountNumber();
	}

	public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
		Long accountNumber = Account.getNewAccountNumber();
		ConsumerAccount consumerAccount = new ConsumerAccount(person, accountNumber, pin, startingDeposit);
		accounts.put(accountNumber, consumerAccount);
		return consumerAccount.getAccountNumber();
	}

	public boolean authenticateUser(Long accountNumber, int pin) {
		Account account = this.getAccount(accountNumber);
		return account.validatePin(pin);
	}

	public double getBalance(Long accountNumber) {
		Account account = this.getAccount(accountNumber);
		return account.getBalance();
	}

	public void credit(Long accountNumber, double amount) {
		Account account = this.getAccount(accountNumber);
		account.creditAccount(amount);
	}

	public boolean debit(Long accountNumber, double amount) {
		Account account = this.getAccount(accountNumber);
		if (account.getBalance() < amount) {
			System.out.println("Account " + account.getAccountNumber() + " should have insufficient funds.");
		}
		return account.debitAccount(amount);
	}
}
