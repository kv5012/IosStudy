import java.net.*;

/**
 * Chatter Main
 */
public class Chatter {
	static private String mode;
	
	/**
	 * @param args [server-mode] args[0] = port
	 * 	           [client-mode] args[0] = host
	 *                           args[1] = port
	 */
	public static void main(String[] args) {
		
		// syntax check
		if (args.length == 1)
			mode = "server";
		else if (args.length == 2)
			mode = "client";
		else {
			System.err.println("syntax error. the following two are acceptable:");
			System.err.println("[server-mode] java Chatter port");
			System.err.println("[client-mode] java Chatter host port");
			return;
		}
		
		try {
			Socket socket = null;
			
			if ("server".equals(mode)) {
				// create a server socket
				ServerSocket svsock = new ServerSocket(Integer.parseInt(args[0]));

				// accept a connection from a client
				socket = svsock.accept();
				
			} else {
				// create a client socket
				socket = new Socket();
				
				// designate the server socket
				socket.connect(new InetSocketAddress(args[0], Integer.parseInt(args[1])));				
			}
			
			
			// assign the socket to a socket manager
			SocketManager socketManager = new SocketManager(socket);
			
			// start conversations
			socketManager.run();
			
			// close the connection
			socketManager.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
