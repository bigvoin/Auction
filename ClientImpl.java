import java.rmi.*;

/** Class that is implementing the methods for Client Interface */
public class ClientImpl extends java.rmi.server.UnicastRemoteObject implements ClientInterface{
	private String name;
	private String email;

	/*
	* Constructor which will set the names of the users to empty string
	*/
	protected ClientImpl() throws RemoteException{
		name = " ";
		email = " ";
	}

	/*
	* Set Method for Name
	*/
	public void setName( String n) throws RemoteException{
		name = n;
	}

	/*
	* Set Method for Email
	*/
	public void setEmail(String e) throws RemoteException{
		email = e;
	}

	/*
	* Get Method for Name
	*/
	public String getName() throws RemoteException{
		return name;
	}

	/*
	* Get Method for Email
	*/
	public String getEmail() throws RemoteException{
		return email;
	}	

	/*
	* Method that is showing given message to the user
	*/
	public void getMessage(String message) throws RemoteException{
		System.out.println(message);
	}
}