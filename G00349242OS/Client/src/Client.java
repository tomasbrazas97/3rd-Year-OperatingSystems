import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 * @author Tomas Brazas
 * <p>G00349242</p>
 *
 */

public class Client {

	public static void main(String[] args) {
		//localhost
		String hostName = "127.0.0.1";
		int portNum = 2006;
		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;
		BufferedReader stdIn;
		String menuChoice;
		
		try {
			clientSocket = new Socket(hostName, portNum);
			
			//create I/O streams
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			//Welcome menu
			System.out.println("Bug Tracker");			
			do {
				System.out.println("Enter 1) to Register "
						+ "\nEnter 2) to Log In "
						+ "\nEnter -1) to Exit ");
				menuChoice = stdIn.readLine();
				out.println(menuChoice);
				if(menuChoice.equals("1")) {
					//register
					System.out.println("Enter your Name: ");
					out.println(stdIn.readLine());
					System.out.println("Enter your Password: ");
					out.println(stdIn.readLine());
					System.out.println("Enter your Email address: ");
					out.println(stdIn.readLine());
					System.out.println("Enter your Employee ID: ");
					out.println(stdIn.readLine());
					System.out.println("Enter your Department:");
					out.println(stdIn.readLine());
					System.out.println(in.readLine());
				}
				else if (menuChoice.equals("2")) {
					System.out.println("Enter your Name: ");
					out.println(stdIn.readLine());
					System.out.println("Enter your Password: ");
					out.println(stdIn.readLine());
					String okay = in.readLine();
					if(okay.equals("ok")) {
						System.out.println("Name: " + in.readLine());
						System.out.println("Address: " + in.readLine());
						System.out.println("Employee ID: " + in.readLine());
						System.out.println("Department: " + in.readLine());
						
						String menu2Choice;
						do{
							System.out.println("---Bug Tracker Menu---");
							System.out.println("Enter Number:");
							System.out.println("1) to Add a bug");
							System.out.println("2) to Assign bug to a user");
							System.out.println("3) to View not assigned bugs");
							System.out.println("4) to View all bugs in system");
							System.out.println("5) to Update bug using bug ID");
							System.out.println("-1) to Exit");
							
							menu2Choice= stdIn.readLine();
							out.println(menu2Choice);
							switch(menu2Choice) {
							 case "1" :
								//add bug record code
								 System.out.println("Enter Unique Bug ID:");
								 String fBugNum = stdIn.readLine();
								 out.println(fBugNum);
								 System.out.println("Application Name:");
								 String appName = stdIn.readLine();
								 out.println(appName);
								 System.out.println("Enter Platform Type(Window, Unix or Mac):");
								 String pType = stdIn.readLine();
								 out.println(pType);
								 System.out.println("Enter Time Stamp(minutes):");
								 String duration = stdIn.readLine();
								 out.println(duration);
								 System.out.println("Problem Description:");
								 String fullProbDesc = stdIn.readLine();
								 String probDesc = fullProbDesc.replaceAll("\\s+", "");
								 out.println(probDesc);
								 System.out.println("Enter Status(Open, Assigned or Closed):");
								 String appStatus = stdIn.readLine();
								 out.println(appStatus);
							     break; 
							   
							   case "2" :
							      //Assign bug record
								   System.out.println("Enter Bug ID:");
								   String mBugNum = stdIn.readLine();
								   out.println(mBugNum);
								   System.out.println("Enter User's Employee ID to assign bug:");
								   String assignedBugUser = stdIn.readLine();
								   out.println(assignedBugUser);
								   System.out.println("User " + assignedBugUser + " has succesfully been assigned bug: MT " + mBugNum);
							      break; 
							   case "3" :	
								    //View Not assigned bugs
								   	//Bugged
								   String mOrF = "";
								   while(mOrF.compareTo("eof")!=0) {
									   	   mOrF = in.readLine();
									   	   if(mOrF.equals("f")==true) {
									   		   System.out.println("Bug ID : " + in.readLine());
									   		   System.out.println("User: " + in.readLine());
									   	   }
									   	   else if(mOrF.equals("m")==true){
									   		   	System.out.println("Bug ID : " + in.readLine());
										   		System.out.println("User: " + in.readLine());
									   	   }	   
								   }
								   break;
							   case "4" :
								   //View all bugs in the system
								   String isEnd = "";
								   while(isEnd.compareTo("eof")!=0) {
									   	   isEnd = in.readLine();
									   	   if(isEnd.equals("f")==true) {
									   		   System.out.println("Bug Id: " + in.readLine());
									   		   System.out.println("Application Name: " + in.readLine());
											   System.out.println("Platform: " + in.readLine());
											   System.out.println("Time Stamp: " + in.readLine());
											   System.out.println("Description: " + in.readLine());
											   System.out.println("Status: " + in.readLine());
									   	   }
										   
								   }
								      break; 
							   case "5" :
								      //Update Bug using bug id
									   System.out.println("Enter Bug ID to Update: ");
									   String bugId = stdIn.readLine();
									   out.println(bugId);
								      break;
							   default : 
							      // Statements
							}
							
						}while(menu2Choice.compareTo("-1")!=0);
					} else {
						continue;
					}
				}
			}while(menuChoice.compareTo("-1")!=0);
		}
		//System.out.println("Server msg says: " + in.readLine());
		catch(UnknownHostException e) {
			System.exit(1);
		} catch(IOException e) {
			System.exit(1);
		}
			
	}

}
