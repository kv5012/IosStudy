import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Socket Manager
 * A wrapper class that manages a socket
 */
class SocketManager {
	private Socket socket;
	private Boolean active;
	
	public SocketManager(Socket socket) {
		this.socket = socket;
		active = true;
	}
	
	public boolean isActive() {
		return active;
	} 
	
	public void stop() {
		active = false;
	}
	
	public void close() {
		try {
			socket.close();
			System.out.println("--connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 1. run socketWriter as a thread.
	 * 2. run socketReader as a thread.
	 */
	public void run() {
		try {
			// run socket writer as a thread 
			Thread socketWriter = new Thread(new SocketWriter(this));
			socketWriter.start();
			
			// run socket reader as a thread
			Thread socketReader = new Thread(new SocketReader(this));
			socketReader.start();
			
			// wait the threads end
			socketWriter.join();
			socketReader.join();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public DataOutputStream getOutputStream() throws Exception {
		return new DataOutputStream(socket.getOutputStream());
	}
	
	public BufferedReader getInputStream() throws Exception {
		return new BufferedReader(new InputStreamReader(
				new DataInputStream(socket.getInputStream())));
	}
}
