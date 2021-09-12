import java.rmi.*;
import java.net.MalformedURLException;
import java.util.*;

public class BuyerClient{
    // final variables for host and server name
    final static String host = "localhost";
	final static String serverName = "Auction";

    /** Method for connecting to the server on host and server name and displaying menu for the buyer */
	public static void main(String[] args){
		
		try{
            AuctionInterface server = (AuctionInterface) Naming.lookup("rmi://"+ host +"/"+ serverName);
		    ClientInterface client = new ClientImpl();
            while(true){
                int option = 0;
                System.out.println(" 1 to view all Items \n");
                System.out.println(" 2 to bid for an item \n");
                System.out.println(" 3 to exit \n");
                System.out.println("................................");
    
                Scanner scan = new Scanner(System.in);
                option =scan.nextInt();
    
                switch(option){
                    case 1:
                        listAll(server);
                        break;
    
                    case 2:
                        bidItem(server, client);
                        break;
    
                    case 3:
                        System.exit(0);
                        break;
    
                    default:
                        System.out.println("Invalid Option");
                }
            }

		}catch(MalformedURLException m){
			System.out.println(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/*
	* Method, that lists all items on the auction 
	*/
	public static void listAll(AuctionInterface a){
		try{
			a.listItems();
	    }catch(Exception e){
			e.printStackTrace();
	    }
	}

	/*
	* Method that performce biding on item id, requires Buyers Name and Email
	*/
	public static void bidItem(AuctionInterface auction, ClientInterface client){
		try{
			System.out.println("Enter Id of Item: ");
			Scanner scan = new Scanner(System.in);
			int id = scan.nextInt();

			System.out.println("Enter name: ");
			Scanner scan2 = new Scanner(System.in); 
			String name = scan2.nextLine();
			client.setName(name);

			System.out.println("Enter email: ");
			String email = scan2.nextLine();
			client.setEmail(email);

			System.out.println("Enter to bid: ");
			Scanner bidScan = new Scanner(System.in);
			int value = scan.nextInt();

			auction.bid(client , id, value);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}