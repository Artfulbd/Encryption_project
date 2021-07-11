import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
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
	private String code, msg;
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
				encryption();	
			}
		});
		btnEnc.setBackground(new Color(255, 140, 0));
		btnEnc.setFont(new Font("Brush Script MT", Font.BOLD, 60));
		btnEnc.setBounds(143, 95, 338, 91);
		contentPane.add(btnEnc);

		JButton btnDec = new JButton("Decryption");
		btnDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Decryption();	
			}
		});
		btnDec.setFont(new Font("Brush Script MT", Font.BOLD, 60));
		btnDec.setBackground(new Color(255, 0, 0));
		btnDec.setBounds(143, 279, 338, 91);
		contentPane.add(btnDec);

		JButton btnReadMeFirst = new JButton("Read me first");
		btnReadMeFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Home.this, " Don't you know the rules?\nThen don't use it..","                              Haha",JOptionPane.WARNING_MESSAGE);
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

	void encryption() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 80, 960, 579);;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 165, 0));
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
		btnClose.setBounds(762, 486, 150, 45);
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
		btnBack.setBounds(26, 486, 150, 45);
		contentPane.add(btnBack);

		JLabel lblEnterYourMassege = new JLabel("Enter your message:  ");
		lblEnterYourMassege.setBackground(new Color(95, 158, 160));
		lblEnterYourMassege.setFont(new Font("Poor Richard", Font.PLAIN, 35));
		lblEnterYourMassege.setBounds(43, 39, 267, 45);
		contentPane.add(lblEnterYourMassege);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(321, 10, 613, 182);
		contentPane.add(scrollPane);

		JTextArea txtMsg = new JTextArea();
		scrollPane.setViewportView(txtMsg);
		txtMsg.setFont(new Font("Segoe Print", Font.PLAIN, 21));
		txtMsg.setToolTipText("What's wrong, just write down.");

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 324, 731, 145);
		contentPane.add(scrollPane_1);

		JTextArea txtCode = new JTextArea();
		scrollPane_1.setViewportView(txtCode);
		txtCode.setEditable(false);
		txtCode.setFont(new Font("Rockwell", Font.PLAIN, 30));

		JLabel lblYourCode = new JLabel(": Your code");
		lblYourCode.setFont(new Font("Poor Richard", Font.PLAIN, 35));
		lblYourCode.setBackground(new Color(95, 158, 160));
		lblYourCode.setBounds(751, 375, 150, 45);
		contentPane.add(lblYourCode);

		JButton btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCode.getText().equals("")) {
					JOptionPane.showMessageDialog(Home.this, "Sorry!!. Nothing to copy ...","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
					StringSelection data = new StringSelection(code);
					clip.setContents(data, data);
				}		
			}
		});
		btnCopy.setToolTipText("Copy the code");
		btnCopy.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 36));
		btnCopy.setBackground(new Color(0, 128, 128));
		btnCopy.setBounds(10, 269, 150, 45);
		contentPane.add(btnCopy);

		JButton btnPaste = new JButton("Paste"); 										
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();				
				Transferable cdata = clip.getContents(clip);
				try {
					if(cdata.isDataFlavorSupported(DataFlavor.stringFlavor)) {
						String s = (String)(cdata.getTransferData(DataFlavor.stringFlavor));
						txtMsg.setText(s);
					}
				}catch(Exception E) {
					JOptionPane.showMessageDialog(Home.this, " No text found on your clipboard...!","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}


			}
		});
		btnPaste.setToolTipText("Paste your massege here");
		btnPaste.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 36));
		btnPaste.setBackground(new Color(0, 128, 128));
		btnPaste.setBounds(784, 202, 150, 45);
		contentPane.add(btnPaste);	

		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtMsg.getText().equals("")) {
					JOptionPane.showMessageDialog(Home.this, " Nothing on your message box..","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					String main = "",store ="",t;
					t = txtMsg.getText();
					String[] s = t.split(" ");
					int r, h, l = t.length();
					for(String st: s) {
						l = st.length();
						for(int i = 0; i<l ;i++) {   
							r =95+(int)(Math.random()*500)%29;
							h = st.charAt(i);
							h = (h + r) % 127;
							store+=Integer.toString(r)+" "+Integer.toString(h)+" ";
						}
						main += store+"| ";
						store = "";
					}
					code = main;
					store = "";
					for(int i = 0; i<main.length(); i++) {
						store += main.charAt(i);
						if((i+1) % 50 == 0)store +="\n";
					}
					txtCode.setText(store);
				}
			}
		});
		btnEncrypt.setFont(new Font("Brush Script MT", Font.BOLD, 60));
		btnEncrypt.setBackground(new Color(50, 205, 50));
		btnEncrypt.setBounds(363, 213, 308, 83);
		contentPane.add(btnEncrypt);



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
		btnClear.setBounds(160, 144, 134, 37);
		contentPane.add(btnClear);		
	}
	
     void Decryption() {
 		
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
 		lblYourCode.setFont(new Font("Poor Richard", Font.PLAIN, 35));
 		lblYourCode.setBackground(new Color(95, 158, 160));
 		lblYourCode.setBounds(28, 29, 210, 45);
 		contentPane.add(lblYourCode);
 		
 		JLabel lblYourMessage = new JLabel(": Your message");
 		lblYourMessage.setFont(new Font("Poor Richard", Font.PLAIN, 35));
 		lblYourMessage.setBackground(new Color(95, 158, 160));
 		lblYourMessage.setBounds(730, 346, 204, 45);
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
 					JOptionPane.showMessageDialog(Home.this, "Sorry!!. Nothing to copy ...","Error",JOptionPane.ERROR_MESSAGE);
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
 		btnCopy.setBounds(556, 432, 150, 45);
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
 					JOptionPane.showMessageDialog(Home.this, " No text found on your clipboard...!","Error",JOptionPane.ERROR_MESSAGE);
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
 					JOptionPane.showMessageDialog(Home.this, " Nothing on your code box..","Error",JOptionPane.ERROR_MESSAGE);
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
 						JOptionPane.showMessageDialog(Home.this, " Wrong code ..!","Error",JOptionPane.ERROR_MESSAGE);
 						return;
 					}
 				}
 			}
 		});
 		btnDecrypt.setFont(new Font("Brush Script MT", Font.BOLD, 60));
 		btnDecrypt.setBackground(new Color(50, 205, 50));
 		btnDecrypt.setBounds(427, 91, 210, 52);
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