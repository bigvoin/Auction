import java.rmi.*;
import java.io.*;
import java.util.*;
import java.net.MalformedURLException;

/**
 * Server for the Auction
 */
public class Server{

	public Server(){
		try{
			AuctionInterface auction = new AuctionImpl(); // remote object
			Naming.rebind("rmi://localhost/Auction",auction); // binding the localhost to the remote object and the RMI registry
			System.out.println("The Server is Online");
		}catch(MalformedURLException m){
			System.out.println(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
		new Server();
	}
}