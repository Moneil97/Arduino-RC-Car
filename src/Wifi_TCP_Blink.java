import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class Wifi_TCP_Blink {

	private static Socket sock;

	public Wifi_TCP_Blink() throws IOException {
		
		sock = new Socket("192.168.1.171",1336);
		System.out.println(sock);
		
		for (int i=0; i < 5; i++){
			send("on");
			say("on");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			send ("off");
			say("off");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		sock.close();
	}
	
	public static void say(Object s) {
		System.out.println(s);
	}

	public static void main(String[] args) throws Exception{
		new Wifi_TCP_Blink();
	}
	
	public void send(String s) throws IOException{
		//Stay under 50 ish chars
		sock.getOutputStream().write(("/S" + s + "/E").getBytes());
		sock.getOutputStream().flush();
	}

}
