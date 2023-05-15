package net.codejava;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tempo extends JFrame {
	 
	 public Tempo() {
		 
	 }
	 
	 public void vittoriaTempo() {
		 JDialog dialog = new JDialog();

		    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    dialog.setSize(200, 100);
		    dialog.setLocationRelativeTo(null);
		    JLabel label = new JLabel("La squadra in casa ha vinto", SwingConstants.CENTER);
		    dialog.add(label);
		    dialog.setVisible(true);

		    // Chiudi la finestra di dialogo dopo 2 secondi
		    int tempoInMillisecondi = 2000; // 2 secondi
		    Timer timer = new Timer(tempoInMillisecondi, (ActionListener) new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            dialog.dispose();
		        }
		    });
		    
		    timer.setRepeats(false); // Esegui solo una volta
		    timer.start();
	 }
	 
	 public void pareggioTempo() {
		 JDialog dialog = new JDialog();

		    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    dialog.setSize(200, 100);
		    dialog.setLocationRelativeTo(null);
		    JLabel label = new JLabel("Le squadre hanno pareggiato", SwingConstants.CENTER);
		    dialog.add(label);
		    dialog.setVisible(true);

		    // Chiudi la finestra di dialogo dopo 2 secondi
		    int tempoInMillisecondi = 2000; // 2 secondi
		    Timer timer = new Timer(tempoInMillisecondi, (ActionListener) new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            dialog.dispose();
		        }
		    });
		    
		    timer.setRepeats(false); // Esegui solo una volta
		    timer.start();
	 }
	 
	 public void sconfittaTempo() {
		 JDialog dialog = new JDialog();

		    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    dialog.setSize(200, 100);
		    dialog.setLocationRelativeTo(null);
		    JLabel label = new JLabel("La squadra in trasferta ha vinto", SwingConstants.CENTER);
		    dialog.add(label);
		    dialog.setVisible(true);

		    // Chiudi la finestra di dialogo dopo 2 secondi
		    
		    int tempoInMillisecondi = 2000; // 2 secondi
		    Timer timer = new Timer(tempoInMillisecondi, (ActionListener) new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            dialog.dispose();
		        }
		    });
		    
		    timer.setRepeats(false); // Esegui solo una volta
		    timer.start();
	 }
}
