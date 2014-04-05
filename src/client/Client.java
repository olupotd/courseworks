package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import server.Server_stub;

public class Client implements Client_stub {

	private static Scanner scan;
	private static int pin, amount;
	private static String acc;
	private static String[] balance;

	protected Client() throws RemoteException {
		super();

	}

	public static void runATM(Server_stub client) {
		try {
			scan = new Scanner(System.in);
			do {
				printMenu();
				int read = scan.nextInt();
				switch (read) {
				case 1:
					// Make Withdraw
					System.out.println("Enter Account Number");
					acc = scan.next();
					System.out.println("Enter PIN");
					pin = scan.nextInt();
					System.out.println("Enter amount to Withdraw");
					amount = scan.nextInt();
					balance = client.checkBalance(acc, pin).split(":");
					System.out.println(Double.parseDouble(balance[1]));
					double bal = Double.parseDouble(balance[1]);
					if (bal > amount) {
						String result = client.makeWithdraw(acc, pin, amount);
						System.out.println(result);
					} else
						System.out.println("Your Balance is Low");

					break;
				case 2:
					System.out.println("Enter Account Number");
					acc = scan.next();
					System.out.println("Enter amount to Deposit");
					double deposit_amount = scan.nextDouble();
					if (client.depositCash(acc, deposit_amount)) {
						System.out.println("Deposit mae");
					} else
						System.out.println("Unable to deposit cash");
					break;
				case 3:
					System.out.println("Enter Account Number");
					acc = scan.next();
					System.out.println("Enter PIN");
					pin = scan.nextInt();
					System.out.println("Enter Account Number");
					String toAcc = scan.next();
					System.out.println("Enter amount to Deposit");
					double t_amount = scan.nextDouble();
					if (client.transferCash(acc, toAcc, pin, t_amount) == true)
						System.out.println("Transfer complete..");
					else
						System.out.println("Unable to tranfer money..");

					break;
				case 4:
					System.out.println("Enter Account Number");
					acc = scan.next();
					System.out.println("Enter PIN");
					pin = scan.nextInt();
					System.out.println(client.checkBalance(acc, pin));
					break;
				case 5:
					System.out.println("Enter Account Number");
					acc = scan.next();
					System.out.println("Enter PIN");
					String pin = scan.next();
					System.out.println("Enter initial deposit");
					String initDepo = scan.next();
					System.out
							.println(client.createAccount(pin, acc, initDepo));
					break;
				default:
					break;
				}
			} while (acc != null);
		} catch (Exception e) {
			System.out.println("Expected Integer values");
			e.printStackTrace();
		}
	}

	private static void printMenu() {
		System.out
				.println("\t1--Cash Withdraw\n\t2--Cash Deposit\n\t3--Cash Transfer\n\t4--Check Balance\n\t5--Create Account\n Enter a number to choose and option");
	}

	public void anouncement(String message) throws RemoteException {
		System.out.println("Notice from Bank:" + message);
	}

	public static void main(String[] args) {
		if (args.length >= 2) {
			try {
				Registry registry = LocateRegistry.getRegistry();
				System.out.println("Reg: " + registry.toString());
				String name = "ClientBoard";
				Client client = new Client();
				Client_stub stub = (Client_stub) UnicastRemoteObject
						.exportObject(client, 0);

				registry.rebind(name, stub);
				Server_stub server_object = (Server_stub) Naming
						.lookup("ATMManager");
				runATM(server_object);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
