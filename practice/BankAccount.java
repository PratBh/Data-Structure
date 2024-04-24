package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	private volatile double balance;
	ReentrantLock lock = new ReentrantLock();
	
	public BankAccount(int b) {
		this.balance=b;
	}
	
	public synchronized void deposit(double amt) {
		lock.lock();
		try {
		balance+=amt;
		}finally {
		lock.unlock();
		}
	}
	
	public synchronized void withdraw(double amt) {
		lock.lock();
		try {
		if(balance>=amt) {
			balance-=amt;
		}else {
			throw new InsufficientFundsExceptio();
		}
		} catch (InsufficientFundsExceptio e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
			}
	}
	
	public synchronized double getBalance() {
		lock.lock();
		try {
		return balance;
		}finally {
			lock.unlock();
			}
	}
}

class Bank{
	
	List<BankAccount> list = new ArrayList<BankAccount>();
	public void removeAcc(BankAccount acc) {
		list.remove(acc);
	}
	
}

class InsufficientFundsExceptio extends Exception{
	public InsufficientFundsExceptio() {
		super("Insufficient balance");
	}
}

class Reservation {
	private String customerName;
	private int candidtaes;
	
	public boolean equals(Object o) {
		if(o instanceof Reservation) {
			Reservation res = (Reservation) o;
			if(customerName.equals(res.customerName))
				return true;
			else
				return false;
		}
		return false;
	}
}

class TestRes{
	private Map<Reservation,Integer> mp = new HashMap<Reservation, Integer>();
	
	public void addReservation(Reservation res,Integer table) {
		mp.put(res, table);
	}
	
	public Integer getTable(Reservation res) {
		return mp.get(res);
	}
}
