package myone;

import java.awt.EventQueue;
import javax.swing.JFrame;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Home() {
		setTitle("Not for all");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 960, 579);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnEnc = new JButton("Encryption");
		btnEnc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Encryption en = new Encryption();
				dispose();
				en.setVisible(true);
			}
		});
		btnEnc.setBackground(new Color(255, 140, 0));
		btnEnc.setFont(new Font("Monotype Corsiva", Font.BOLD, 60));
		btnEnc.setBounds(143, 95, 343, 85);
		contentPane.add(btnEnc);

		JButton btnDec = new JButton("Decryption");
		btnDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decryption de = new Decryption();
				dispose();
				de.setVisible(true);
			}
		});
		btnDec.setFont(new Font("Monotype Corsiva", Font.BOLD, 60));
		btnDec.setBackground(new Color(255, 0, 0));
		btnDec.setBounds(143, 279, 343, 85);
		contentPane.add(btnDec);

		JButton btnReadMeFirst = new JButton("Read me first");
		btnReadMeFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Home.this, " Don't you know the rules?\nThan don't use it..","                              Haha",JOptionPane.WARNING_MESSAGE);
				return;
			}
		});
		btnReadMeFirst.setToolTipText("Click here");
		btnReadMeFirst.setFont(new Font("Forte", Font.PLAIN, 36));
		btnReadMeFirst.setBackground(new Color(0, 255, 0));
		btnReadMeFirst.setBounds(643, 30, 276, 54);
		contentPane.add(btnReadMeFirst);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setToolTipText("");
		btnClose.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 36));
		btnClose.setBackground(new Color(154, 205, 50));
		btnClose.setBounds(721, 448, 150, 45);
		contentPane.add(btnClose);	
	}
}