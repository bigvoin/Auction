import java.rmi.*;
import java.util.*;

public interface AuctionInterface extends Remote{
	/**Method for creating an Auction Item */
	public int createItem(String name, String describption, int startingPrice, int reservePrice, ClientInterface client) throws RemoteException;

	/**Method for closing an Auction Item */
	public void closeItem(int id) throws RemoteException;

	/**Method for listing all existing Items */
	public void listItems() throws RemoteException;

	/** Method for seting bids on Item */
	public void bid(ClientInterface buyer, int auctionID, int value) throws RemoteException;
}