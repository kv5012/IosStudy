import java.io.BufferedReader;

/**
 * Socket Reader
 *  A thread that read data from the socket
 */
class SocketReader implements Runnable {
	private SocketManager socketManager;
	
	public SocketReader(SocketManager socketManager) {
		this.socketManager = socketManager;
	}
	
	/**
	 * 1. get the input stream from the socket.
	 * 2. while the socket is active, read strings from socket and display them in stdout.
	 */
	public void run() {
		try {
			BufferedReader br =
				socketManager.getInputStream();
		
			while (socketManager.isActive()) {
				String line = br.readLine();
				System.out.println("> " + line);	    		
	    		if ("bye".equals(line))
					socketManager.stop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
