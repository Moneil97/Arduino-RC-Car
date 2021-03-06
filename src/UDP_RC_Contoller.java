import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class UDP_RC_Contoller extends JFrame{

	private DatagramSocket sock;
	boolean left, right, up, down;
	
	enum Controls{
		left('A'), right('D'), forward('W'), reverse('S');
		
		private final int id;
		Controls(int id) { this.id = id; }
	    public int getValue() { return id; }
	}

	public UDP_RC_Contoller() throws IOException {
		
		sock = new DatagramSocket();
		sock.connect(InetAddress.getByName("192.168.1.171"), 1336);
		//sock = new Socket("192.168.1.171", 1336);
		System.out.println(sock);
		
		setSize(200,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keycode = e.getKeyCode();
				boolean state = false;
				
				if (keycode == Controls.left.getValue()){
					left = state;
				}
				else if (keycode == Controls.right.getValue()){
					right = state;
				}
				else if (keycode == Controls.forward.getValue()){
					up = state;
				}
				else if (keycode == Controls.reverse.getValue()){
					down = state;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				int keycode = e.getKeyCode();
				boolean state = true;
				
				if (keycode == Controls.left.getValue()){
					left = state;
				}
				else if (keycode == Controls.right.getValue()){
					right = state;
				}
				else if (keycode == Controls.forward.getValue()){
					up = state;
				}
				else if (keycode == Controls.reverse.getValue()){
					down = state;
				}
			}
		});
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Packet pack = new Packet();
				
				while (true){
					
					pack.sendUpdate();
					
					try {
						Thread.sleep(1000/5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}
		}).start();
		
		//sock.close();
	}
	
	class Packet{
		
		public void sendUpdate() {
			String message = "/S";
			message += (left? 1:0);
			message += (right? 1:0);
			message += (up? 1:0);
			message += (down? 1:0);
			message += "/E";
			try {
				sock.send(new DatagramPacket(message.getBytes(), message.length()));
				say(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void say(Object s) {
		System.out.println(s);
	}

	public static void main(String[] args) throws Exception{
		new UDP_RC_Contoller();
	}
	
//	public void send(String s) throws IOException{
//		sock.send(new DatagramPacket(("/S" + s + "/E").getBytes(), s.length()+4));
//	}

}
