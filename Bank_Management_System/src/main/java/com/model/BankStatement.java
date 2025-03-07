package com.model;
import java.time.LocalDate;
import java.time.LocalTime;
public class BankStatement {
	private int transactionId;
	private double transactionAmount;
	private double balanceAmount;
	private LocalDate dateOftransaction;
	private LocalTime timeOfTransaction;
	private String transactionType;
	private int userId;
	@Override
	public String toString() {
		return "BankStatement [transactionId=" + transactionId + ", transactionAmount=" + transactionAmount
				+ ", balanceAmount=" + balanceAmount + ", dateOftransaction=" + dateOftransaction
				+ ", timeOfTransaction=" + timeOfTransaction + ", transactionType=" + transactionType + ", userId="
				+ userId + "]";
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public LocalDate getDateOftransaction() {
		return dateOftransaction;
	}
	public void setDateOftransaction(LocalDate dateOftransaction) {
		this.dateOftransaction = dateOftransaction;
	}
	public LocalTime getTimeOfTransaction() {
		return timeOfTransaction;
	}
	public void setTimeOfTransaction(LocalTime timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public BankStatement() {
	}
	
}
