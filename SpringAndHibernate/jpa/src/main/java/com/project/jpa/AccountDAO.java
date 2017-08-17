package com.project.jpa;

import java.util.List;

import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.project.jpaEntity.Account;

@Transactional
public class AccountDAO {
	JpaTemplate template;

	/*
	 * public void createAccount(Integer acctnumber, String owner, Double
	 * balance) { Account account = new Account(acctnumber, owner, balance);
	 * template.persist(account); }
	 */
	public void createAccount(Account account) {
		template.persist(account);
	}

	public void updateBalance(Integer acctNum, Double newBalance) {
		Account acct = template.find(Account.class, acctNum);
		if (acct != null) {
			acct.setAcctBalance(newBalance);
		}
		template.merge(acct);
	}

	public void deleteAccount(int acctNum) {
		Account acct = template.find(Account.class, acctNum);
		if (acct != null) {
			template.remove(acct);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Account> getAllAcct() {
		List<Account> allAcctList = template
				.find("select acc from Account acc");
		return allAcctList;
	}

	public JpaTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JpaTemplate template) {
		this.template = template;
	}
}
