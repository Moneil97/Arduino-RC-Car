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
import javax.swing.JScrollBar;

import java.awt.GridLayout;

import javax.swing.JScrollPane;

public class Wifi_Module_Debugger_GUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private String ip = "192.168.1.202";  // the remote IP address
	private int port  = 8888;    // the destination port
	private JTextField inputTextbox;
	private JScrollPane scrollPane;
	private JButton sendButton;
	private JPanel outputBox;
	private JScrollBar scrollBar;
	
	public Wifi_Module_Debugger_GUI() {
		//Connect to Arduino Ethernet Board
		UDP udp = new UDP(this, 6000);
		udp.broadcast(true);
		udp.listen(true);
		
		//Setup JFrame
		setSize(304,388);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel lowerPanel = new JPanel();
		getContentPane().add(lowerPanel, BorderLayout.SOUTH);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		inputTextbox = new JTextField();
		inputTextbox.addActionListener(new ActionListener() {
			
			@Override //Called when Enter is pressed
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
				scrollBar.setValue(scrollBar.getMaximum());
				
				//Send string to Arduino Ethernet Board
				udp.send(inputTextbox.getText(), ip, port);
				
				inputTextbox.setText("");
			}
		});
		lowerPanel.add(sendButton, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		scrollBar = scrollPane.getVerticalScrollBar();
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
		
		System.out.println("\nReceived:");
		System.out.println("--------------------");
		
		for (byte b : message){
			System.out.print((char) b);
		}
		System.out.println("\n--------------------");
		
		
		String rec = "";
		for (byte b : message)
			rec += (char) b;
		
		//Add message to GUI
		JLabel temp = new JLabel(rec);
		temp.setForeground(Color.blue);
		outputBox.add(temp);
		outputBox.revalidate();
		scrollBar.setValue(scrollBar.getMaximum());
	 }
	
	public static void say(Object s){
		System.out.println("System: " + s);
	}
	
	public static void main(String[] args) {
		new Wifi_Module_Debugger_GUI();
	}

}
