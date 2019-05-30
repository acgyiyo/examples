package concurrency.synchronization;

public class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized int getSynchroBalance() {
        return balance;
    }

    public synchronized void synchroDeposit(int amount) {
        balance += amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdrawal(int amount) {
        balance -= amount;
    }

    public synchronized void synchroWithdrawal(int amount) {
        balance -= amount;
    }
}
