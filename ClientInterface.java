import java.rmi.*;

public interface ClientInterface extends Remote{

	// Get Method for client name
	public String getName() throws RemoteException;

	// Get Method for client email
	public String getEmail() throws RemoteException;

	// Set Method for client name
	public void setName(String name) throws RemoteException;

	// Set Method for client email
	public void setEmail(String email) throws RemoteException;

	// Get Method for displaying message to the user
	public void getMessage(String s) throws RemoteException;
  
}