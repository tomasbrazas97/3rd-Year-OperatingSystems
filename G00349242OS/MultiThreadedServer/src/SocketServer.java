import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Tomas Brazas
 * <p>G00349242</p>
 *
 */

public class SocketServer {
	int portNum = 2006;
	ServerSocket serverSocket = null;
	
	public void runServer() {
		try {
			serverSocket = new ServerSocket(portNum);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
		while(true) {
			try {
				//connection to client made
				Socket clientSocket = serverSocket.accept();
				//creating new thread and starting it
				MultiThreadedRunnable m = new MultiThreadedRunnable(clientSocket);
				new Thread(m).start();
			} catch(IOException e) {
				System.out.println(e.getMessage());
			}
		}

		
	}

}
