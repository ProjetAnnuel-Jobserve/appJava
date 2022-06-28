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
	private String state, contributor;
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
		setBounds(100, 100, 464, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel labelTitle = new JLabel("AJOUTER UNE TACHE", SwingConstants.CENTER);
		JLabel labelName = new JLabel("Nom *");
		
		JLabel labelDescription = new JLabel("Description");
		JLabel labelEndDate = new JLabel("Date de fin *");
		JLabel labelState = new JLabel("Statut");
		JLabel personLabel = new JLabel("Contributeurs");
		
		nameField = new JTextField();
		nameField.setColumns(10); 
		
		endDateField = new JTextField();
		endDateField.setColumns(10);
		
		description = new JTextArea();
		description.setLineWrap(true);
		
		JComboBox stateBox = new JComboBox();
		stateBox.setModel(new DefaultComboBoxModel(new String[] {"A faire", "En cours", "Terminé"}));
		
		JComboBox contributorBox = new JComboBox();
		contributorBox.setModel(new DefaultComboBoxModel(new String[] {"Zinedine", "Thomas", "Yvan"}));
		
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
				contributor = contributorBox.getSelectedItem().toString()
;				try {
					service.addTask(nameField.getText(), description.getText(), endDateField.getText(), state, contributor);
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
				
				cleanField(nameField, endDateField, description, stateBox, contributorBox);
			}
		});
		
		
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(labelTitle, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
									.addComponent(labelName)
									.addComponent(personLabel))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(labelDescription)
									.addComponent(description, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(contributorBox, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(27)
								.addComponent(labelState))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(labelEndDate, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addComponent(endDateField, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)))
							.addComponent(stateBox, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE))
						.addComponent(saveButton))
					.addGap(18))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(labelTitle)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelEndDate)
						.addComponent(labelName))
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(endDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelState)
						.addComponent(personLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(stateBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(contributorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(labelDescription)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(description, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(saveButton))
					.addGap(36))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public void cleanField(JTextField nameField, JTextField dateField, JTextArea descArea, JComboBox state, JComboBox contributor) {
		nameField.setText("");
		dateField.setText("");
		descArea.setText("");
		state.setModel(new DefaultComboBoxModel(new String[] {"A faire", "En cours", "Terminé"}));
		contributor.setModel(new DefaultComboBoxModel(new String[] {"Zinedine", "Thomas", "Yvan"}));
	}
}
