package concurrency.synchronization;

public class Worker implements Runnable {

    private BankAccount account;
    private char type;
    private boolean synchronize;
    private int amount;

    public Worker(BankAccount account, char type, boolean synchronize, int amount) {
        this.account = account;
        this.type = type;
        this.synchronize = synchronize;
        this.amount = amount;
    }

    public void run() {
        if (synchronize)
            synchroProcess(type);
        else
            process(type);
    }

    public void synchroProcess(char type) {
//        for (int i = 0; i < 10; i++) {
        if (type == 'd') {
            account.synchroDeposit(amount);
        } else {
            account.synchroWithdrawal(amount);
        }
//        }
    }

    public void process(char type) {
//        for (int i = 0; i < 10; i++) {
        if (type == 'd') {
            account.deposit(amount);
        } else {
            account.withdrawal(amount);
        }
//        }
    }
}
