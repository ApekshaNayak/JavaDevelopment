package com.project.jpa;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.jpaEntity.Account;

/**
 * Unit test for simple App.
 */
public class AppTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"jpaApplicationContext.xml");
		AccountDAO accDao = context.getBean(AccountDAO.class);
		Account account = new Account();
		account.setAccountNumber(200910034);
		account.setOwner("Ketki Dave");
		account.setAcctBalance(350650D);
		
		accDao.createAccount(account);
		
		List<Account> list = accDao.getAllAcct();
		for(Account acct: list) {
			System.out.println(acct.getAccountNumber()+" "+acct.getOwner()+" "+acct.getAcctBalance());
		}
		/*accDao.createAccount(200910001, "Ketki Dave", 72000d);
		accDao.createAccount(200910002, "Suprita Ruikar", 90000d);*/
	}
}
