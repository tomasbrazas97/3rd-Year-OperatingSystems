import java.io.*;
import java.net.Socket;

/**
 * 
 * @author Tomas Brazas
 * <p>G00349242</p>
 *
 */

public class MultiThreadedRunnable implements Runnable {
	
	Socket clientSocket = null;
	//constructors
	public MultiThreadedRunnable() {

	}
	public MultiThreadedRunnable(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	public void run(){
		//thread logic
		try {
			//creating buffered reader and print writer to communicate messages to client
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			//register or login menu
			String choice;
			do{
				choice = in.readLine();
				//register
				if(choice.equals("1")) {
					Person p = new Person();
					
					//creating new Person
					String fullName = in.readLine();
					p.setName(fullName);
					String name = fullName.replaceAll("\\s+","");
					p.setPassword(in.readLine());
					String fullAdd = in.readLine();
					p.setAddress(fullAdd);
					String address = fullAdd.replaceAll("\\s+","");
					p.setEmpNum(in.readLine());			
					String fullDep = in.readLine();
					p.setDepartment(fullDep);
					String department = fullDep.replaceAll("\\s+","");		
					
					try {
						//creating file and calling it users name
						String file = p.getName().toString();
						BufferedWriter bw = new BufferedWriter(new FileWriter(file + ".txt", true));
						//variable to write to file
						PrintWriter outfile = new PrintWriter(bw);
						//writing persons details to file to register
						outfile.println(name + " " + p.getPassword() + " " + address + " " + p.getEmpNum() + " " + p.getDepartment());
						out.println("User has been added to the database.");
						bw.close();
						outfile.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//login
				else if (choice.equals("2")) {
					String name = in.readLine();
					String password = in.readLine();
					//checking if users file exists
					boolean check = new File(name+".txt").exists();
					//if person exists
					if(check == true) {
						 System.out.println("Person exists");
						 String file = name+".txt";
						 //creating reader to read the file
						 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
						 String line = null;
						 while((line = br.readLine())!= null ){
							 	System.out.println("Line = " + line);
							 	 //Remove white spaces
								 String[] arrayLine = line.split("\\s+");
								 if(arrayLine.length <= 1) {
									 System.out.println("arrayLine length < 1");
									 break;
								 }
								 else {
									 //password validation
									 if(password.equals(arrayLine[1])){
										out.println("ok"); 
										//creating instance of person class with attribute values taken from file
										Person p = new Person(arrayLine[0], arrayLine[1], arrayLine[2], arrayLine[3], arrayLine[4]);
										//sending persons details to client
										out.println(p.getName());
										out.println(p.getAddress());
										out.println(p.getEmpNum());
										out.println(p.getDepartment());
								 }	
							}
						}
						br.close();
						//inner menu
						String menu2Choice;
						do {
							//creating writer to write records to file
							BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
							PrintWriter outfile = new PrintWriter(bw);
							menu2Choice = in.readLine();
							switch(menu2Choice) {
							   case "1" :
								  //write bug to file
								  String fBugNum = in.readLine();
								  String appName = in.readLine();
								  String pType = in.readLine();
								  String duration = in.readLine();
								  String probDesc = in.readLine();
								  String appStatus = in.readLine();
								  outfile.println(fBugNum + " " + appName + " "+ pType + " " + duration + " " + probDesc + " " + appStatus + "FT");
								  bw.close();
								  outfile.close();
							      break; 
							   
							   case "2" :
							      //Assign bug to user
								   String mBugNum = in.readLine();
								   String assignedBugUser = in.readLine();
								   outfile.println(mBugNum + " " + assignedBugUser + " MT ");
								   bw.close();
								   outfile.close();
							      break; 
							   case "3" :
								   //view unassigned bugs
								   BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
								   String line2 = null;
								   while((line2 = br2.readLine()) != null) {
									   System.out.println("Line2 = " + line2);
									   //splitting line into array to access individual values
									   String[] arrayLine2 = line2.split("\\s+");
									   //using FT and MT as indicators in file as to whether assigned or not assigned
									   //assigned
									   if(line2.contains("FT")==true) {
										 System.out.println("f");
										 out.println("f");
										 out.println(arrayLine2[0]);
										 out.println(arrayLine2[1]);
									   }
									   //not assigned
									   if(line2.contains("MT")==true) {
										 out.println("m");
										 out.println(arrayLine2[0]);
										 out.println(arrayLine2[1]);		 
									   } 
								   }
								   out.println("eof");
								   br2.close();
								  break; 
							   case "4" :
								      //View all bugs that are assigned
									   BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
									   String line3 = null;
									   while((line3 = br3.readLine()) != null) {
										   System.out.println("Line3 = " + line3);
										   if(line3.contains("FT")==true) {
											   out.println("f");
											   String[] arrayLine3 = line3.split("\\s+");
											   //values to client to print
											   out.println(arrayLine3[0]);
											   out.println(arrayLine3[1]);
											   out.println(arrayLine3[2]);
											   out.println(arrayLine3[3]);
											   out.println(arrayLine3[4]);
										   }
									   }
									   out.println("eof");
									   br3.close();
							      break; 
							   case "5" : 
								   		String bugId = in.readLine();
								   		
							            File inFile = new File(file);
							            if (!inFile.isFile()) {
							                System.out.println("Parameter is not an existing file");
							                return;
							            }
							            //Construct the new file that will be replacing old file once update happens
							            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
							            
							            try(PrintWriter pw = new PrintWriter(new FileWriter(tempFile)))
							               {
							                   try(BufferedReader br4 = new BufferedReader(new FileReader(file)))                
							                   {
									            String line4 = null;
									            //Read from the original file and rewrite to a new one
									            while ((line4 = br4.readLine()) != null) {
									            	System.out.println("line 4 =" + line4);
									                if (!line4.contains(bugId)) {
									                	System.out.println("No Bug under that ID has been found");
									                    pw.println(line4);
									                    pw.flush();
									                }
									            }
									            br4.close();
							                   } catch(IOException e) {
									            	System.out.println(e.getMessage());
									           }
							                   pw.close();
							                } catch(IOException e) {
								            	System.out.println(e.getMessage());
							                }
							            	//resources associated with file must be closed to delete file
								            bw.close();
											outfile.close();
							            	//Delete the original file
								            if (!inFile.delete()) {
								                System.out.println("Could not update file");
								            }
								            //Rename the new file to the filename the original file had.
								            if (!tempFile.renameTo(inFile)) {
								                System.out.println("Could not rename file");
								            }
								   break; 
							   default : 
							      System.out.println("Invalid input");
							}
							bw.close();
							outfile.close();
						}while(menu2Choice.compareTo("-1")!=0);
						
					} else {
						out.println("User not found. Please register. ");
					}
					
				} else {
					out.println("Error input");
				}
				
			}while(choice.compareTo("-1")!=0);
			in.close();
			out.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
