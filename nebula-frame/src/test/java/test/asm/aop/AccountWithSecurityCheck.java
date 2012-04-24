package test.asm.aop;

public class AccountWithSecurityCheck extends Account {
	private Account account;

	public AccountWithSecurityCheck(Account account) {
		this.account = account;
	}

	public void operation() {
		SecurityChecker.checkSecurity();
		account.operation();
	}
}