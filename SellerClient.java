import java.rmi.*;
import java.net.MalformedURLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

public class SellerClient extends BuyerClient{
    // final variables for host and server Name and a hashmap
    final static String host = "localhost";
	final static String serverName = "Auction";
	private ConcurrentHashMap<Integer, AuctionItem> sellerm; // creating empty map
	private int sellerID;

	public SellerClient(){
		super();
		sellerm = new ConcurrentHashMap<>(); 

		Random rand = new Random();
		sellerID = rand.nextInt(99) + 1; // the item id will vary from 1 to 99
		System.out.println("Id: " + sellerID);
	}

    /** Main method that creates the server client connection on Auction as server name and goes displays menu for the Sellers */
	public static void main(String[] args){
		
		try{	
			AuctionInterface server = (AuctionInterface) Naming.lookup("rmi://"+ host +"/"+ serverName);
            ClientInterface buyer = new ClientImpl();
            
            // loop for the user to choose options
            while(true){
                int option = 0;
                System.out.println(" 1 to view all Items \n");
                System.out.println(" 2 to Create an auction \n");
                System.out.println(" 3 to Close Auction \n");
                System.out.println(" 4 to Exit \n");
                System.out.println("--------------------------------------------------------------");
    
                Scanner scan = new Scanner(System.in);
                option =scan.nextInt();
    
                switch(option){
                    case 1:
                        listAll(server);
                        break;
    
                    case 2:
                        createAuction(server, buyer);
                        break;
    
                    case 3:
                        closeAuction(server); 
                        break;
                        
                    case 4:
                        System.exit(0);
                        break;
    
                    default:
                        System.out.println("Error");
                }
            }

		}catch(MalformedURLException m){
			System.out.println(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	* Method that gives the seller option to list an Item
	*/
	public static void createAuction(AuctionInterface a, ClientInterface c){
		try{
			System.out.println("Enter Item name: ");
			Scanner scan = new Scanner(System.in);
			String name =scan.nextLine();

			System.out.println("Enter Item Description: ");
			String des =scan.nextLine();

		    System.out.println("Enter Starting Price: ");
			int min = scan.nextInt();

			System.out.println("Enter Reserve Price: ");
			int res = scan.nextInt();

			while(min > res){
				System.out.println("Reserve Price lower than Starting Price ");
			    res = scan.nextInt();
			}
			System.out.println("Created item with id: " + a.createItem(name, des, min, res, c));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	* Method that closes Auction on chosen id
	*/
	public static void closeAuction(AuctionInterface a){

		try{
			System.out.println("Enter the AuctionItem ID you want to close: ");
			Scanner scan = new Scanner(System.in);
			int id = scan.nextInt();

			a.closeItem(id);

		}catch(Exception e){
			e.printStackTrace();
		}
	}


}