package myone;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Decryption extends JFrame {

	private JPanel contentPane;
	private String msg;

	public Decryption() {
		
		setBounds(250, 80, 960, 579);;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 69, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setToolTipText("");
		btnClose.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 36));
		btnClose.setBackground(new Color(154, 205, 50));
		btnClose.setBounds(764, 474, 150, 45);
		contentPane.add(btnClose);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				dispose();
				h.setVisible(true);
			}
		});
		btnBack.setToolTipText("Go back to home");
		btnBack.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 36));
		btnBack.setBackground(new Color(0, 128, 128));
		btnBack.setBounds(28, 474, 150, 45);
		contentPane.add(btnBack);
		
		JLabel lblYourCode = new JLabel("Enter your code:");
		lblYourCode.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblYourCode.setBackground(new Color(95, 158, 160));
		lblYourCode.setBounds(10, 41, 222, 39);
		contentPane.add(lblYourCode);
		
		JLabel lblYourMessage = new JLabel(": Your message");
		lblYourMessage.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblYourMessage.setBackground(new Color(95, 158, 160));
		lblYourMessage.setBounds(714, 352, 213, 39);
		contentPane.add(lblYourMessage);
		
		JTextField txtCode = new JTextField();
		txtCode.setBounds(246, 32, 688, 49);
		contentPane.add(txtCode);
		txtCode.setFont(new Font("Rockwell", Font.PLAIN, 40));
		txtCode.setColumns(22);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 151, 694, 271);
		contentPane.add(scrollPane);
		
		JTextArea txtMsg = new JTextArea();
		scrollPane.setViewportView(txtMsg);
		txtMsg.setEditable(false);
		txtMsg.setToolTipText("What's wrong, just write down.");
		txtMsg.setFont(new Font("Segoe Print", Font.PLAIN, 21));
		
		JButton btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCode.getText().equals("")) {
					JOptionPane.showMessageDialog(Decryption.this, "Sorry!!. Nothing to copy ...","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
					StringSelection data = new StringSelection(msg);
					clip.setContents(data, data);
				}
			}
		});
		btnCopy.setToolTipText("Copy your message");
		btnCopy.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 36));
		btnCopy.setBackground(new Color(0, 128, 128));
		btnCopy.setBounds(551, 459, 150, 45);
		contentPane.add(btnCopy);
		
		JButton btnPaste = new JButton("Paste");
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();				
				Transferable cdata = clip.getContents(clip);
				try {
					if(cdata.isDataFlavorSupported(DataFlavor.stringFlavor)) {
						String s = (String)(cdata.getTransferData(DataFlavor.stringFlavor));
						txtCode.setText(s);
					}
				}catch(Exception E) {
					JOptionPane.showMessageDialog(Decryption.this, " No text found on your clipboard...!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnPaste.setToolTipText("Paste your code here");
		btnPaste.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 36));
		btnPaste.setBackground(new Color(0, 128, 128));
		btnPaste.setBounds(764, 91, 150, 45);
		contentPane.add(btnPaste);
		
		JButton btnDecrypt = new JButton("Decrypt");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCode.getText().equals("")) {
					JOptionPane.showMessageDialog(Decryption.this, " Nothing on your code box..","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					String t;
					boolean b = true;
					String main = "",h1 = "", h2 = "";
					t = txtCode.getText();
					String[] s = t.split(" ");
					int i = 0, h = 0, ho;
					try {
						for(String st: s) {
							if(st.equals("|"))main+=" ";
							else {
								if(i % 2 == 0)h1 = st;
								else {
									h2 = st;
									h = (127 - Integer.parseInt(h1)) + Integer.parseInt(h2);
									//System.out.println("H1:"+h1+"  Ho:"+h2);
									ho = Integer.parseInt(h1);
									if(ho < 94  || ho > 123) {
										b = false;
										break;
									}
									if(h == 137 || h == 10)main +='\n';
									else main +=(char)h;
								}
								i++;
							}
						}
					}catch( NumberFormatException E) {
						b = false;
					}
					if(b && main != "") {
						msg = main;
						txtMsg.setText(main);
					}
					else {
						JOptionPane.showMessageDialog(Decryption.this, " Wrong code ..!","Error",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});
		btnDecrypt.setFont(new Font("Monotype Corsiva", Font.BOLD, 40));
		btnDecrypt.setBackground(new Color(50, 205, 50));
		btnDecrypt.setBounds(387, 89, 181, 52);
		contentPane.add(btnDecrypt);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtMsg.setText("");
				txtCode.setText("");
			}
		});
		btnClear.setToolTipText("Clear all");
		btnClear.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 34));
		btnClear.setBackground(new Color(0, 128, 128));
		btnClear.setBounds(753, 228, 150, 45);
		contentPane.add(btnClear);
	}
}
