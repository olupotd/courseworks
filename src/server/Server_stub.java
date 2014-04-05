package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server_stub extends Remote {

	public String makeWithdraw(String acc, Integer pin, double amount)
			throws RemoteException;

	public boolean depositCash(String account, Double amount)
			throws RemoteException;

	public String checkBalance(String account, Integer pin)
			throws RemoteException;

	public boolean transferCash(String fromAcc, String toAcc, Integer pin, Double amount)
			throws RemoteException;

	public String createAccount(String pin, String acc, String iniDeposit)
			throws RemoteException;
}
