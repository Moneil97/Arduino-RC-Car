import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import hypermedia.net.UDP;

//enum Controls{
//	left(37), right(39), straight(36), forward(38), reverse(40), still(41);
//	
//	private final int id;
//	Controls(int id) { this.id = id; }
//    public int getValue() { return id; }
//}

public class Wifi_Tester extends JFrame{

	private static final long serialVersionUID = 1L;

	public Wifi_Tester() {
		
		UDP udp = new UDP(this, 6000);
		udp.broadcast(true);
		udp.listen(true);
		
		setSize(200,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		addKeyListener(new KeyListener() {
			
			private String ip = "192.168.1.202";  // the remote IP address
			private int port  = 8888;    // the destination port
			private boolean leftHeld, rightHeld, forwardHeld, reverseHeld;
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
				int keycode = e.getKeyCode();
				
				if (keycode == Controls.left.getValue()){
					leftHeld = false;
					udp.send(String.valueOf(Controls.straight.getValue()), ip, port);
				}
				else if (keycode == Controls.right.getValue()){
					rightHeld = false;
					udp.send(String.valueOf(Controls.straight.getValue()), ip, port);
				}
				else if (keycode == Controls.forward.getValue()){
					forwardHeld = false;
					udp.send(String.valueOf(Controls.still.getValue()), ip, port);
				}
				else if (keycode == Controls.reverse.getValue()){
					reverseHeld = false;
					udp.send(String.valueOf(Controls.still.getValue()), ip, port);
				}
				else{
					System.err.println("Released Invalid Key: " + keycode);
					return;
				}
				
				System.out.println("Released: " + keycode);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				int keycode = e.getKeyCode();
				
				if (!leftHeld && keycode == Controls.left.getValue()){
					leftHeld = true;
					udp.send(String.valueOf(keycode), ip, port);
				}
				else if (!rightHeld  && keycode == Controls.right.getValue()){
					rightHeld = true;
					udp.send(String.valueOf(keycode), ip, port);
				}
				else if (!forwardHeld  && keycode == Controls.forward.getValue()){
					forwardHeld = true;
					udp.send(String.valueOf(keycode), ip, port);
				}
				else if (!reverseHeld  && keycode == Controls.reverse.getValue()){
					reverseHeld = true;
					udp.send(String.valueOf(keycode), ip, port);
				}
				else{
					//System.err.println("Pressed Invalid Key: " + keycode);
					return;
				}
				System.out.println("Pressed Key: " + keycode);
			}
		});
		
		udp.setReceiveHandler("receive");
	}
	
	public void receive(byte[] message) {
		for (byte b : message){
			System.out.print((char) b);
		}
		System.out.println();
	 }
	
	

	public static void main(String[] args) {
		new Wifi_Tester();
	}

}
