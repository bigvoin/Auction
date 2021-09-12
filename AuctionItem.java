import java.io.*;
import java.rmi.*;
import java.util.Map.Entry;
import java.util.*;

public class AuctionItem implements Serializable{ // implements this interface to be able to be past as parameter in remote objects

    /** private Variables for the Auction Item */
	private int id;
	private String itemName;
	private String itemDescription;
	private int startPrice;
	private int reservePrice;
	private String status;
	private ClientInterface client;
	private ClientInterface lastBidder;
	private String lastName;
	private Hashtable<String, ClientInterface> map;

    /*
	* Constructor for Auction item
    */
	public AuctionItem(int auctionID, String name, String description, int startPrice, int reservePrice, ClientInterface client){
		id = auctionID;
		itemName = name;
		itemDescription = description;
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.client = client;
		status = "open";
		map = new Hashtable<>();
	}

	/*
	* Method that check for bids and send a message to the Client
	*/	
    public synchronized void checkBid(ClientInterface client, int value){
    	try{
    		
    		map.put(client.getEmail(), client);
    		lastBidder = client;
    		lastName = client.getName();
 			startPrice = value;
            

            for(Entry<String, ClientInterface> entry : map.entrySet()){
            	ClientInterface m = entry.getValue();
            	if(m == lastBidder){
            		lastBidder.getMessage("Bid placed for item: "+ itemName + " for " + startPrice +"\n");
            	}else{
            		m.getMessage("Bid for: "+ itemName + " was outbidded at price of  "+ startPrice + " by " + lastName +"\n");
            	}
            }
    	}catch(Exception e){
    		e.printStackTrace();
    	}

    }	
  	
  	/*
	* Method that norify the sellers and buyers that this item is closed for bidding
  	*/
    public synchronized void closeAuction(){
    	status = "closed";

    	try{
    		if(lastBidder == null){
    			client.getMessage("\n Auction Item: "+ id +", "+ itemName +" has closed with no bids \n");
    		}
    		else{
    			client.getMessage("\n Auction Item: "+ id +", "+ itemName +" has a winner --> "+ lastName + " Bid Price: "+ startPrice + " Email: "+ lastBidder.getEmail() +"\n");
    			for(Entry<String, ClientInterface> entry : map.entrySet()){
    				ClientInterface buyer = entry.getValue();
    				if(buyer == lastBidder){
    					lastBidder.getMessage("\n You have won the bid for --> "+ itemName+ "  at the price " +startPrice );
    				}else{
    					buyer.getMessage("\n An Item --> "+ itemName + " you bid on was outbidded and won by" + lastName +" for "+ startPrice +"\n");
    				}
    			}  
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    /*
	Method that displays the Item details 
    */
    public synchronized String getItemDetails(){
    	String string = " ";
    	string += "\nID: "+ id;
    	string += "\nName: "+ itemName;
    	string += "\nStarting Price: "+ startPrice;
    	string += "\nNumber Of bidders: " + map.size();
    	string += "\n status: " + status;

    	if(status == "open"){
    		if(lastBidder == null){
  				string += "\nLast Bidder: No bids yet";
    		}else{
    			string += "\n lastBidder: "+ lastName;
    		}
    	}else if(status == "closed"){
    		if(lastBidder == null){
  				string += "\nLast Bidder: Closed with No bids";
    		}else{
    			string += "\n Won by: "+ lastName +" at price of "+ startPrice;
    		}
    	}
        return string;
    }

    /*
	*Get Method for Name
    */
    public synchronized String getName(){
    	return itemName;
    }

    /*
	* Get Method for Price
    */
    public synchronized int getStartPrice(){
    	return startPrice;
    }

    /*
	* Get method for reserve Price
    */
    public synchronized int getReservePrice(){
    	return reservePrice;
    }

    /*
	* Get Method Description
    */   
    public synchronized String getDescription(){
    	return itemDescription;
    }

    /*
	* Get Method for status
    */
	public synchronized String getStatus(){
		return status;
	}
}