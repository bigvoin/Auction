import java.rmi.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map.Entry;
import java.util.*;

/** Class that implements the methods of the AuctionInterface */
public class AuctionImpl extends java.rmi.server.UnicastRemoteObject implements AuctionInterface{

    
	private ConcurrentHashMap<Integer, AuctionItem> auctionMap;// creating a hashmap for id and Auction item

	public AuctionImpl() throws RemoteException{
		auctionMap = new ConcurrentHashMap<>(); //Creates a new, empty map 
	} 

	/*
	* Method that creates an Item into the Auction and returns the id of theis item in Integer value
	*/
	public int createItem(String name, String description, int startPrice, int reservePrice, ClientInterface client) throws RemoteException{
        Random rand = new Random();
		int id = rand.nextInt(99) + 1; // id should be between 1 and 99
		AuctionItem a = new AuctionItem(id, name, description, startPrice, reservePrice, client);
		auctionMap.put(id, a);
		return id;
	}

	/*
	* Method that closes an Item from the auction
	*/
	public void closeItem(int id) throws RemoteException{
		if(auctionMap.isEmpty()){
			System.out.println("There are currently no aution item in system to close");
		}else{
			// Checks the id in the map and remove the AuctionItem if found
			for(Entry<Integer, AuctionItem> entry : auctionMap.entrySet()){
				if(id == entry.getKey()){
					entry.getValue().closeAuction();
					auctionMap.remove(id, entry.getValue());
					System.out.println("Auction with AuctionID: "+ id +" Closed.");
				}else{
					System.out.println("Please Enter a valid ID.");
				}
			}

		}
	}

	/*
	* Method that is listing all Items from the auction
	*/
	public void listItems() throws RemoteException{
		String result = " ";
		if(auctionMap.isEmpty()){
			System.out.println(" There are currently no auction items in the system. ");
		}else{
			for(Entry<Integer, AuctionItem> entry : auctionMap.entrySet()){
				result = result + entry.getValue().getItemDetails();
			}
		}
		System.out.println(result);
	}

	/*
	* Method that shows the bids for given
	*/
	public void bid(ClientInterface buyer, int auctionID, int value) throws RemoteException{
        AuctionItem a = auctionMap.get(auctionID);
        if(a == null){
        	System.out.println("Please enter a valid ID");
        }else if(value <= a.getStartPrice()){
        	System.out.println("Please place a higher bid");
        }else if(a.getStatus() == "closed"){
        	System.out.println("Sorry, this auction is closed ");
        }else{
        	a.checkBid(buyer, value);
        	System.out.println("Your bid has been placed !!! ------- Auction ID: " + auctionID);
        }
	}
    
    
}