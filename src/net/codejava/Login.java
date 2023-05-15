package net.codejava;

import java.util.*;
import java.util.List;
import javax.swing.*;
import net.codejava.Menu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends JFrame {
	
	//Dichiarazione attributi
	
	private JLabel nomeUtenteLabel, passwordUtenteLabel;
	private JTextField nomeUtenteField, passwordUtenteField;
	private JButton logButton, registerButton;
	private static final String DB_URL = "jdbc:mysql://localhost/betting";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "BlaBlaBla24.";
	
	//Costruttore
	
	public Login() {
		ImageIcon imageIcon = new ImageIcon("nomeUtente.png");
		Image image = imageIcon.getImage().getScaledInstance(340, 100, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(image);
		
		ImageIcon imageIcon2 = new ImageIcon("Password.png");
		Image image2 = imageIcon2.getImage().getScaledInstance(340, 100, Image.SCALE_SMOOTH);
		imageIcon2 = new ImageIcon(image2);

		nomeUtenteLabel = new JLabel("Nome utente ", JLabel.CENTER);
		nomeUtenteLabel.setIcon(imageIcon);
		passwordUtenteLabel = new JLabel("Password ", JLabel.CENTER);
		passwordUtenteLabel.setIcon(imageIcon2);
		nomeUtenteField = new JTextField(50);
		passwordUtenteField = new JPasswordField(50);
		logButton = new JButton("Login");
		registerButton = new JButton("Register");
		
		//Grafica del panello 
		
		nomeUtenteLabel.setForeground(Color.white);
		passwordUtenteLabel.setForeground(Color.white);
		nomeUtenteLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		passwordUtenteLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		logButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		registerButton.setFont(new Font("Verdana", Font.PLAIN, 20));
		nomeUtenteField.setMaximumSize(nomeUtenteField.getMaximumSize());   
		nomeUtenteField.setPreferredSize(new Dimension(200, 100));
		Insets insets = new Insets(20, 20, 20, 20);
		nomeUtenteField.setMargin(insets);
		passwordUtenteField.setPreferredSize(new Dimension(200, 100));
		passwordUtenteField.setMargin(insets);

		JPanel panel = new JPanel(new GridLayout(4,1));
		
		panel.setBackground(Color.black);

		setTitle("Login");
		setSize(320, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel.add(nomeUtenteLabel);
		panel.add(nomeUtenteField, BorderLayout.CENTER);
		panel.add(passwordUtenteLabel, Color.white);
		panel.add(passwordUtenteField);
		
		ImageIcon icona = new ImageIcon("BetCampLogo.png");
		setIconImage(icona.getImage());
		//Azione onclick
		
		logButton.addActionListener(new ActionListener() {
			
			public int getId() {
				
				String nomeUtente = nomeUtenteField.getText();
				String passwordUtente = passwordUtenteField.getText();
				int id = 0;

				Connection con = null;
				PreparedStatement stmt = null;
				String sql = "SELECT * FROM accesso";
				
				//Connessione al database
				
				try {
					con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery("SELECT * FROM accesso");

					while(rs.next()) {
						if(true) {
							String nome = rs.getString("nome");
							String password = rs.getString("password");

							if(nome.equals(nomeUtente) && password.equals(passwordUtente)) {
								id = rs.getInt("id");
						    }														
					    }  
				    }
				}	
				catch(SQLException ex) {
					ex.printStackTrace();
				}
				finally {
					try {
						stmt.close();
						con.close();
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				return id;				
			}
			
			public void actionPerformed(ActionEvent e) {
					
					String nomeUtente = nomeUtenteField.getText();
					String passwordUtente = passwordUtenteField.getText();
					int id = getId();

					Connection con = null;
					PreparedStatement stmt = null;
					String sql = "SELECT accesso.id, accesso.nome, accesso.password, admin.id, admin.nome, admin.password FROM accesso JOIN admin";
					
					try {
						con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
						stmt = con.prepareStatement(sql);
						ResultSet rs = stmt.executeQuery("SELECT accesso.id, accesso.nome, accesso.password, admin.id, admin.nome, admin.password FROM accesso JOIN admin");
						boolean flag = false;
						
						while(rs.next() && !flag) {
							if(true) {
								String nome = rs.getString("accesso.nome");
								String password = rs.getString("accesso.password");
								String nomeAdmin = rs.getString("admin.nome");
								String passwordAdmin = rs.getString("admin.password");
								
								if(nome.equals(nomeUtente) && password.equals(passwordUtente)) {
									JOptionPane.showMessageDialog(null, "Logged in");
									new Menu(id);
									dispose();
									flag = true;
								}	
							
								else if(nomeAdmin.equals(nomeUtente) && passwordAdmin.equals(passwordUtente)) {
									JOptionPane.showMessageDialog(null, "Logged as Administrator");
									new Admin();
									dispose();
									flag = true;
								}
							}
						}
						if(!flag) {
							JOptionPane.showMessageDialog(null, "Nome utente o password errati.");
						}
					}
					catch(SQLException ex) {
						ex.printStackTrace();
					}
					finally {
						try {
							stmt.close();
							con.close();
						}catch(SQLException ex) {
							ex.printStackTrace();
						}
					}
			}
		});
		
		registerButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				new Registrazione();
				dispose();
			}
		});
		
		//Pannello per il pulsante invia
		
		JPanel buttonPanel = new JPanel(new GridLayout(1,2, 20, 25));

		logButton.setPreferredSize(new Dimension(150, 80));
		logButton.setForeground(Color.white);
		logButton.setBackground(Color.black);
		registerButton.setPreferredSize(new Dimension(150, 80));
		registerButton.setForeground(Color.white);
		registerButton.setBackground(Color.black);
		buttonPanel.setBackground(Color.black);

		buttonPanel.add(logButton);
		buttonPanel.add(registerButton);

		add(buttonPanel, BorderLayout.SOUTH);
		add(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Login();
	}
}

