import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class UDP_RC_Contoller_Optimized extends JFrame{

	private DatagramSocket sock;
	boolean left, right, up, down;
	
	enum Controls{
		left('A'), right('D'), forward('W'), reverse('S');
		
		private final int id;
		Controls(int id) { this.id = id; }
	    public int getValue() { return id; }
	}

	public UDP_RC_Contoller_Optimized() throws IOException {
		
		sock = new DatagramSocket();
		sock.connect(InetAddress.getByName(/*"192.168.1.171"*/"oneil.asuscomm.com"), 1336);
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
						Thread.sleep(1000/*/5*//3);
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
			
			String message = "";
			
			if ((left && right) || (!left && !right)) //straight
				message+=0;
			else if (left && !right) //left
				message+=1;
			else if (!left && right) //right
				message+=2;
			if ((up && down) || (!up && !down)) //stop
				message +=0;
			else if (up && !down) //forward
				message+=1;
			else if (!up && down) //reverse
				message+=2;
			
			message += "$";
			try {
				//1st digit is turning
				//2nd digit is moving
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
		new UDP_RC_Contoller_Optimized();
	}
	
//	public void send(String s) throws IOException{
//		sock.send(new DatagramPacket(("/S" + s + "/E").getBytes(), s.length()+4));
//	}

}
