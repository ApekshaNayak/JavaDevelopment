package com.project.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Account
 *
 */
@Entity
@Table(name = "account")
public class Account{
	@Id
	@Column(name = "Account_Number", nullable=false)
	private Integer accountNumber;
	@Column(name = "Owner" ,nullable=false)
	private String owner;
	@Column(name = "Account_Balance" ,nullable=true)
	private Double acctBalance;

	public Account() {

	}

	public Account(Integer accountNum, String owner, Double balance) {
		super();
	}

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Double getAcctBalance() {
		return acctBalance;
	}

	public void setAcctBalance(Double acctBalance) {
		this.acctBalance = acctBalance;
	}
}
