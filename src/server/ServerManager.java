package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ServerManager implements Server_stub {

	private static final Double STANDARD_CHARGE = 2.55;
	private HashMap<String, Integer> accounts;
	private HashMap<String, Double> cash;

	protected ServerManager() throws RemoteException {
		super();
		accounts = new HashMap<String, Integer>(50);
		cash = new HashMap<String, Double>(50);
	}

	private boolean authenticateUser(String acc, Integer pin) {
		boolean is_valid = false;
		if (!accounts.isEmpty() && accounts.containsKey(acc)) {
			if (accounts.containsValue(pin)) {
				// user has been verified
				is_valid = true;
			}
		}
		return is_valid;
	}

	private boolean canWithdraw(String acc, double amount) {
		boolean can_withdraw = false;
		double val = 0.0;
		if (cash.containsKey(acc))
			val = cash.get(acc);
		if (val > amount)
			can_withdraw = true;
		else
			can_withdraw = false;
		return can_withdraw;
	}

	public String makeWithdraw(String acc, Integer pin, double amount)
			throws RemoteException {
		double amount1 = 0.0;
		String cashed = null;
		if (!acc.equals(null) && !pin.equals(null)) {
			if (authenticateUser(acc, pin)) {
				if (canWithdraw(acc, amount1) == true) {
					amount1 = (double) cash.get(acc);
					amount1 = amount1 - amount;
					cash.put(acc, amount1);
					cashed = "Deducted: " + amount + " Your balance is :"
							+ amount1;
				}
			} else {
				cashed = "You are not registered in the system";
			}
		}
		return cashed;
	}

	public boolean depositCash(String acc, Double amount)
			throws RemoteException {
		if (!acc.equals(null) && !amount.equals(null)) {
			double init = cash.get(acc);
			amount = (amount - STANDARD_CHARGE) + init;
			cash.put(acc, amount);
			return true;
		}
		return false;
	}

	public String checkBalance(String account, Integer pin)
			throws RemoteException {
		String balance = null;
		if (!account.equals(null) && !pin.equals(null)) {
			if (authenticateUser(account, pin)) {
				balance = "Balance:" + cash.get(account);
			} else {
				balance = "Your not registered:"+0;
			}
		}
		return balance;
	}

	public String createAccount(String pin, String acc, String iniDeposit)
			throws RemoteException {
		String created = null;
		/*
		 * [0]-->account [1]-->pin
		 */
		if (!acc.isEmpty()) {
			if (accounts.containsKey(acc)) {
				created = "An account already exists with that number";
			} else {
				accounts.put(acc, Integer.parseInt(pin));
				cash.put(acc, (double) Integer.parseInt(iniDeposit));
				created = "Account created with AccNo.:" + acc + " Pin:" + pin;
			}
		}
		return created;
	}

	private boolean updateAccount(String acc, Double amount) {
		if (cash.put(acc, amount) != null)
			return true;
		else
			return false;
	}

	public boolean transferCash(String fromAcc, String toAcc, Integer pin,
			Double amount) throws RemoteException {
		boolean transferd = false;
		if (!fromAcc.equals(null) && !toAcc.equals(null)) {
			if (authenticateUser(fromAcc, pin)
					&& canWithdraw(fromAcc, (amount + STANDARD_CHARGE))) {
				double accAmount = (double) cash.get(fromAcc);
				accAmount = (double) (cash.get(fromAcc) - (amount + STANDARD_CHARGE));
				updateAccount(fromAcc, accAmount);
				updateAccount(toAcc, amount);
				transferd = true;
			}
		}
		return transferd;
	}

	public static void main(String[] args) {
		/*
		 * if (System.getSecurityManager() == null) {
		 * System.setSecurityManager(new SecurityManager()); }
		 */
		try {
			Registry registry = LocateRegistry.getRegistry();
			System.out.println("Reg: " + registry.toString());
			String name = "ATMManager";
			ServerManager server = new ServerManager();
			Server_stub stub = (Server_stub) UnicastRemoteObject.exportObject(
					server, 0);
			registry.rebind(name, stub);
			System.out.println("All is well :-)\n");
		} catch (RemoteException re) {
			System.err.println("Remote Exception in DisplayGetEngine.main()\n");
			re.printStackTrace();
		}
	}

}
