package gui;

import javax.swing.JFrame;
import javax.swing.JSlider;

import org.zu.ardulink.Link;
import org.zu.ardulink.protocol.IProtocol;
import org.zu.ardulink.protocol.MessageInfo;
import org.zu.ardulink.protocol.ReplyMessageCallback;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GUIController extends JFrame{

	public GUIController() {
		Link link = Link.getDefaultInstance();
		link.connect("COM3");
		
		ReplyMessageCallback callBack = new ReplyMessageCallback() {
			
			@Override
			public void replyInfo(MessageInfo arg0) {
				//say ("Call Back: " + arg0.getMessageReceived());
			}
		};

		
		setSize(551,592);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(14,0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnOn = new JButton("ON");
		btnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				link.sendPowerPinSwitch(0, IProtocol.POWER_HIGH, callBack);
			}
		});
		panel_1.add(btnOn);
		
		JButton btnOff = new JButton("OFF");
		btnOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(0, IProtocol.POWER_LOW, callBack);
			}
		});
		panel_1.add(btnOff);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button = new JButton("ON");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(1, IProtocol.POWER_HIGH, callBack);
			}
		});
		panel_5.add(button);
		
		JButton button_1 = new JButton("OFF");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(1, IProtocol.POWER_LOW, callBack);
			}
		});
		panel_5.add(button_1);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_2 = new JButton("ON");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(2, IProtocol.POWER_HIGH, callBack);
			}
		});
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("OFF");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(2, IProtocol.POWER_LOW, callBack);
			}
		});
		panel_2.add(button_3);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSlider slider3 = new JSlider();
		slider3.setValue(0);
		slider3.setMaximum(255);
		slider3.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				link.sendPowerPinIntensity(3, slider3.getValue(), callBack);
			}
		});
		panel_3.add(slider3);
		
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_4 = new JButton("ON");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(4, IProtocol.POWER_HIGH, callBack);
			}
		});
		panel_6.add(button_4);
		
		JButton button_5 = new JButton("OFF");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(4, IProtocol.POWER_LOW, callBack);
			}
		});
		panel_6.add(button_5);
		
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSlider slider = new JSlider();
		slider.setValue(0);
		slider.setMaximum(255);
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				link.sendPowerPinIntensity(5, slider.getValue(), callBack);
			}
		});
		panel_7.add(slider);
		
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSlider slider_1 = new JSlider();
		slider_1.setValue(0);
		slider_1.setMaximum(255);
		slider_1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				link.sendPowerPinIntensity(6, slider_1.getValue(), callBack);
			}
		});
		panel_8.add(slider_1);
		
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_6 = new JButton("ON");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(7, IProtocol.POWER_HIGH, callBack);
			}
		});
		panel_9.add(button_6);
		
		JButton button_7 = new JButton("OFF");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(7, IProtocol.POWER_LOW, callBack);
			}
		});
		panel_9.add(button_7);
		
		JPanel panel_10 = new JPanel();
		panel.add(panel_10);
		panel_10.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_8 = new JButton("ON");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(8, IProtocol.POWER_HIGH, callBack);
			}
		});
		panel_10.add(button_8);
		
		JButton button_9 = new JButton("OFF");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(8, IProtocol.POWER_LOW, callBack);
			}
		});
		panel_10.add(button_9);
		
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSlider slider_2 = new JSlider();
		slider_2.setValue(0);
		slider_2.setMaximum(255);
		slider_2.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				link.sendPowerPinIntensity(9, slider_2.getValue(), callBack);
			}
		});
		panel_11.add(slider_2);
		
		JPanel panel_12 = new JPanel();
		panel.add(panel_12);
		panel_12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSlider slider_3 = new JSlider();
		slider_3.setValue(0);
		slider_3.setMaximum(255);
		slider_3.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				link.sendPowerPinIntensity(10, slider_3.getValue(), callBack);
			}
		});
		panel_12.add(slider_3);
		
		JPanel panel_13 = new JPanel();
		panel.add(panel_13);
		panel_13.setLayout(new GridLayout(0, 1, 0, 0));
		
		JSlider slider_4 = new JSlider();
		slider_4.setValue(0);
		slider_4.setMaximum(255);
		slider_4.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				link.sendPowerPinIntensity(11, slider_4.getValue(), callBack);
			}
		});
		panel_13.add(slider_4);
		
		JPanel panel_14 = new JPanel();
		panel.add(panel_14);
		panel_14.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_10 = new JButton("ON");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(12, IProtocol.POWER_HIGH, callBack);
			}
		});
		panel_14.add(button_10);
		
		JButton button_11 = new JButton("OFF");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(12, IProtocol.POWER_LOW, callBack);
			}
		});
		panel_14.add(button_11);
		
		JPanel panel_15 = new JPanel();
		panel.add(panel_15);
		panel_15.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton button_12 = new JButton("ON");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(13, IProtocol.POWER_HIGH, callBack);
			}
		});
		panel_15.add(button_12);
		
		JButton button_13 = new JButton("OFF");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link.sendPowerPinSwitch(13, IProtocol.POWER_LOW, callBack);
			}
		});
		panel_15.add(button_13);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EmptyBorder(0, 10, 0, 10));
		getContentPane().add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(14,0));
		
		JLabel lblPin = new JLabel("Pin 0:");
		panel_4.add(lblPin);
		
		JLabel lblPin_1 = new JLabel("Pin 1:");
		panel_4.add(lblPin_1);
		
		JLabel lblPin_2 = new JLabel("Pin 2:");
		panel_4.add(lblPin_2);
		
		JLabel lblPin_3 = new JLabel("Pin 3:");
		panel_4.add(lblPin_3);
		
		JLabel lblPin_4 = new JLabel("Pin 4:");
		panel_4.add(lblPin_4);
		
		JLabel lblPin_5 = new JLabel("Pin 5:");
		panel_4.add(lblPin_5);
		
		JLabel lblPin_6 = new JLabel("Pin 6:");
		panel_4.add(lblPin_6);
		
		JLabel lblPin_7 = new JLabel("Pin 7:");
		panel_4.add(lblPin_7);
		
		JLabel lblPin_8 = new JLabel("Pin 8:");
		panel_4.add(lblPin_8);
		
		JLabel lblPin_9 = new JLabel("Pin 9:");
		panel_4.add(lblPin_9);
		
		JLabel lblPin_10 = new JLabel("Pin 10:");
		panel_4.add(lblPin_10);
		
		JLabel lblPin_11 = new JLabel("Pin 11:");
		panel_4.add(lblPin_11);
		
		JLabel lblPin_12 = new JLabel("Pin 12:");
		panel_4.add(lblPin_12);
		
		JLabel lblPin_13 = new JLabel("Pin 13:");
		panel_4.add(lblPin_13);
		
		JPanel panel_16 = new JPanel();
		getContentPane().add(panel_16, BorderLayout.SOUTH);
		panel_16.setLayout(new GridLayout(3,0));
		
		JLabel lblMasterSwitches = new JLabel("Master Switches:");
		lblMasterSwitches.setHorizontalAlignment(SwingConstants.CENTER);
		lblMasterSwitches.setFont(new Font("Tahoma", Font.PLAIN, 30));
		panel_16.add(lblMasterSwitches);
		
		JPanel panel_17 = new JPanel();
		panel_16.add(panel_17);
		panel_17.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnOn_1 = new JButton("ON");
		btnOn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOn.doClick();
				button.doClick();
				button_2.doClick();
				button_4.doClick();
				button_6.doClick();
				button_8.doClick();
				button_10.doClick();
				button_12.doClick();
			}
		});
		panel_17.add(btnOn_1);
		
		JButton btnOff_1 = new JButton("OFF");
		btnOff_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOff.doClick();
				button_1.doClick();
				button_3.doClick();
				button_5.doClick();
				button_7.doClick();
				button_9.doClick();
				button_11.doClick();
				button_13.doClick();
			}
		});
		panel_17.add(btnOff_1);
		
		JSlider slider_5 = new JSlider();
		slider_5.setValue(0);
		slider_5.setMaximum(255);
		slider_5.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				int val = slider_5.getValue();
				slider3.setValue(val);
				slider.setValue(val);
				slider_1.setValue(val);
				slider_2.setValue(val);
				slider_3.setValue(val);
				slider_4.setValue(val);
			}
		});
		panel_16.add(slider_5);
		
		JPanel panel_18 = new JPanel();
		getContentPane().add(panel_18, BorderLayout.NORTH);
		panel_18.setLayout(new BorderLayout(0, 0));
		
		JLabel lblArduinoControlSystem = new JLabel("Arduino Control System");
		lblArduinoControlSystem.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblArduinoControlSystem.setHorizontalAlignment(SwingConstants.CENTER);
		panel_18.add(lblArduinoControlSystem);
		
		JLabel lblByCameronOneil = new JLabel("by Cameron O'Neil");
		lblByCameronOneil.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblByCameronOneil.setHorizontalAlignment(SwingConstants.CENTER);
		panel_18.add(lblByCameronOneil, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public static void say(Object s){
		System.out.println(s);
	}

	public static void main(String[] args) {
		new GUIController();
	}

}
