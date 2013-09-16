import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

/**
 * Socket Writer
 *  A thread that writes data out to the socket
 */
public class SocketWriter implements Runnable {
	private SocketManager socketManager;
	
	public SocketWriter(SocketManager SocketManager) {
		this.socketManager = SocketManager;
	}
	
	/**
	 * 1. get the output stream to the socket.
	 * 2. while the socket is active, write strings from stdin towards the socket.
	 */
	public void run() {
		try {
			DataOutputStream out;

			out = socketManager.getOutputStream();
			BufferedReader stdReader = 
				new BufferedReader(new InputStreamReader(System.in));
			
			while (socketManager.isActive()) {
				String line = stdReader.readLine();
				out.writeBytes(line + "\n");
				out.flush();					
				if ("bye".equals(line))
					socketManager.stop();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
