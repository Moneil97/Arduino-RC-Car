import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import hypermedia.net.UDP;

import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.JScrollPane;

public class Wifi_Tester extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField inputTextbox;
	private String ip = "192.168.1.202";  // the remote IP address
	private int port  = 8888;    // the destination port
	private JPanel outputBox;
	private JButton sendButton;
	private JScrollPane scrollPane;

	public Wifi_Tester() {
		//Connect to Arduino Ethernet Board
		UDP udp = new UDP(this, 6000);
		udp.broadcast(true);
		udp.listen(true);
		
		//Setup JFrame
		setSize(322,269);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel lowerPanel = new JPanel();
		getContentPane().add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		inputTextbox = new JTextField();
		inputTextbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendButton.doClick();
			}
		});
		lowerPanel.add(inputTextbox, BorderLayout.CENTER);
		inputTextbox.setColumns(10);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Add Sent message to GUI
				JLabel temp = new JLabel(inputTextbox.getText());
				temp.setForeground(Color.black);
				outputBox.add(temp);
				outputBox.revalidate();
				
				//Send string to Arduino Ethernet Board
				udp.send(inputTextbox.getText(), ip, port);
				
				inputTextbox.setText("");
			}
		});
		lowerPanel.add(sendButton, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		outputBox = new JPanel();
		scrollPane.setViewportView(outputBox);
		outputBox.setLayout(new GridLayout(0,1));
		setVisible(true);
		
		//Tell UDP object the name of the method to call when it receives feedback from Arduino
		udp.setReceiveHandler("receive");
	}
	
	//Called when Arduino sends feedback through Ethernet
	public void receive(byte[] message) {
		
		String rec = "";
		for (byte b : message){
			rec += (char) b;
		}
		
		//Add message to GUI
		JLabel temp = new JLabel(rec);
		temp.setForeground(Color.blue);
		outputBox.add(temp);
		outputBox.revalidate();
	 }
	
	public static void say(Object s){
		System.out.println("System: " + s);
	}
	
	public static void main(String[] args) {
		new Wifi_Tester();
	}

}
