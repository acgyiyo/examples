package concurrency.synchronization;

/**
 * con esta clase vemos un ejemplo donde tener los metodos en 'synchronized no es suficiente
 */
public class PromoWorker extends Worker {

    private BankAccount account;
    private int amount;
    private boolean synchronize;
    private char prType;
    private boolean block;

    public PromoWorker(BankAccount account, char type, boolean synchronize, int amount, boolean block) {
        super(account, type, synchronize, amount);
        this.account = account;
        this.amount = amount;
        this.synchronize = synchronize;
        this.prType = type;
        this.block = block;
    }

    public PromoWorker(BankAccount account, int amount, char prType) {
        this(account, prType, false, amount, false);
    }

    /*
    si nosotros tenemos los metodos sincronizados pero de igual manera estamos llamandolos individualemnte
    en dinstintas lineas de código puede dar la casulaidad que entre cada llamada un hilo pueda entrar y
    volver todo una locura
    */
    public void run() {
        if (synchronize) {
            synchroProcess();
        } else {
            if (block) {
                processSyncroBlock();
            } else {
                process();
            }
        }
    }

    private void process() {
        if (prType == 'w') {
            account.withdrawal(amount);
        } else if (prType == 'd') {
            account.deposit(amount);
            if (account.getBalance() >= 500) {
                int bonus = (int) ((account.getBalance() - 500) * 0.1);
                account.deposit(bonus);
            }
        }
    }

    /**
     * en un bloque sincronizado se evitan problemas al acceder a metodos que no estén sincronizados
     */
    private void processSyncroBlock() {
        synchronized (account) {
            if (prType == 'w') {
                account.withdrawal(amount);
            } else if (prType == 'd') {
                account.deposit(amount);
                if (account.getBalance() >= 500) {
                    int bonus = (int) ((account.getBalance() - 500) * 0.1);
                    account.deposit(bonus);
                }
            }
        }
    }


    private void synchroProcess() {
        if (prType == 'w') {
            account.synchroWithdrawal(amount);
        } else if (prType == 'd') {
            account.synchroDeposit(amount);
            if (account.getSynchroBalance() >= 500) {
                int bonus = (int) ((account.getSynchroBalance() - 500) * 0.1);
                account.synchroDeposit(bonus);
            }
        }
    }
}
