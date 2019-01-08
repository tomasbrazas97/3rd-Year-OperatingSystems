/**
 * 
 * @author Tomas Brazas
 * <p>G00349242</p>
 *
 */

public class Server {
	//Run this class to launch the server
	public static void main(String[] args) {
		System.out.println("Server is now in operation");
		SocketServer s = new SocketServer();
		s.runServer();
	}

}
