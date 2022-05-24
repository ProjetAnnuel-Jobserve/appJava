package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import modele.Task;
import service.Requests;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.event.ActionEvent;

public class Ajout extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField endDateField;
	private JTextArea description;
	private String state;
	private Requests service = new Requests();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ajout frame = new Ajout();
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ajout() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel labelTitle = new JLabel("AJOUTER UNE TACHE", SwingConstants.CENTER);
		JLabel labelName = new JLabel("Nom *");
		
		JLabel labelDescription = new JLabel("Description");
		JLabel labelEndDate = new JLabel("Date de fin *");
		JLabel labelState = new JLabel("Statut");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		endDateField = new JTextField();
		endDateField.setColumns(10);
		
		description = new JTextArea();
		description.setLineWrap(true);
		
		JComboBox stateBox = new JComboBox();
		stateBox.setModel(new DefaultComboBoxModel(new String[] {"A faire", "En cours", "Terminé"}));
		
		
		//En fonction du statut, ajouter à un tableau les valeurs des champs et ajouter le nom à la bonne liste d'accueil
		/*stateBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = stateBox.getSelectedItem().toString();
				//if(state == ...)
			}
		});*/
		
		
		JButton saveButton = new JButton("Sauvegarder");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = stateBox.getSelectedItem().toString();
				try {
					service.addTask(nameField.getText(), description.getText(), endDateField.getText(), state);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				cleanField(nameField, endDateField, description, stateBox);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(labelDescription)
							.addGap(138))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(description, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelName))))
							.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(labelEndDate, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(stateBox, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
							.addComponent(endDateField, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(8)
							.addComponent(labelState)))
					.addGap(13))
				.addComponent(labelTitle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(314, Short.MAX_VALUE)
					.addComponent(saveButton)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTitle)
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelEndDate)
						.addComponent(labelName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(endDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelState)
						.addComponent(labelDescription))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(17)
							.addComponent(stateBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
							.addComponent(saveButton))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(description, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(39))))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void cleanField(JTextField nameField, JTextField dateField, JTextArea descArea, JComboBox state) {
		nameField.setText("");
		dateField.setText("");
		descArea.setText("");
		state.setModel(new DefaultComboBoxModel(new String[] {"A faire", "En cours", "Terminé"}));
	}
}
