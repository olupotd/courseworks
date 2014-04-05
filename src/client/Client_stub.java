/**
 * 
 */
package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author douglas
 *
 */
public interface Client_stub extends Remote {

	public void anouncement(String message) throws RemoteException;
}
