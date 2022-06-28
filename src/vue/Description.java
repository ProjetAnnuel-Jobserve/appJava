package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import modele.Task;
import service.Requests;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class Description extends JFrame {

	private JPanel contentPane;
	private Requests service = new Requests();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Description frame = new Description(new Task("","","","","",""));
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(true);
					//frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Description(Task task) {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton editButton = new JButton("Modifier");
		
		JTextArea descArea = new JTextArea();
		descArea.setText(task.getDescription());
		descArea.setEditable(false);
		descArea.setOpaque(false);
		
		
		JTextArea endDateArea = new JTextArea();
		endDateArea.setText(task.getDate());
		endDateArea.setEditable(false);
		endDateArea.setOpaque(false);
		
		JTextArea nameArea = new JTextArea();
		nameArea.setText(task.getName());
		nameArea.setEditable(false);
		nameArea.setOpaque(false);
		
		JComboBox<String> stateBox = new JComboBox();
		stateBox.setModel(new DefaultComboBoxModel(new String[] {task.getState()}));
		
		JComboBox contributorBox = new JComboBox();
		contributorBox.setModel(new DefaultComboBoxModel(new String[] {task.getPerson()}));
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(editButton.getText() == "Modifier") {
					edit(task, nameArea, endDateArea, descArea, stateBox, contributorBox,editButton);
				}
				else if(editButton.getText() == "Sauvegarder") {
					//envoi à l'api
					try {
						save(task, nameArea, endDateArea, descArea, stateBox, contributorBox, editButton);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		
		//clique sur le bouton sauvegarde, récupérer les champs et les attribuer à la tache et envoyer la tache à l'api
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(descArea, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(nameArea, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
									.addComponent(editButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(endDateArea, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(contributorBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(stateBox, 0, 119, Short.MAX_VALUE))))
							.addGap(29))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameArea, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(editButton))
					.addGap(26)
					.addComponent(descArea, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(contributorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(endDateArea, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(stateBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
		
	}
	
	public void edit(Task task, JTextArea nameArea, JTextArea dateArea, JTextArea descArea, JComboBox stateBox, JComboBox contributorBox, JButton editButton) {
		descArea.setEditable(true);
		dateArea.setEditable(true);
		nameArea.setEditable(true);
		descArea.setBorder(BorderFactory.createLineBorder(Color.black));
		dateArea.setBorder(BorderFactory.createLineBorder(Color.black));
		nameArea.setBorder(BorderFactory.createLineBorder(Color.black));
		nameArea.setText(task.getName());
		dateArea.setText(task.getDate());
		descArea.setText(task.getDescription());
		stateBox.setModel(new DefaultComboBoxModel(new String[] {"A faire", "En cours", "Terminé"}));
		contributorBox.setModel(new DefaultComboBoxModel(new String[] {"Zinedine", "Thomas", "Yvan"}));
		editButton.setText("Sauvegarder");
	}
	
	public void save(Task task, JTextArea nameArea, JTextArea dateArea, JTextArea descArea, JComboBox stateBox, JComboBox contributorBox, JButton editButton) throws IOException, InterruptedException {
		descArea.setEditable(false);
		dateArea.setEditable(false);
		nameArea.setEditable(false);
		descArea.setBorder(BorderFactory.createLineBorder(getForeground(), 0));
		dateArea.setBorder(BorderFactory.createLineBorder(getForeground(), 0));
		nameArea.setBorder(BorderFactory.createLineBorder(getForeground(), 0));
		nameArea.setText(nameArea.getText());
		dateArea.setText(dateArea.getText());
		descArea.setText(descArea.getText());
		stateBox.setModel(new DefaultComboBoxModel(new String[] {(String) stateBox.getSelectedItem()}));
		contributorBox.setModel(new DefaultComboBoxModel(new String[] {(String) contributorBox.getSelectedItem()}));
		editButton.setText("Modifier");
		//envoie a l'api PUT
		service.updateTask(nameArea.getText(), descArea.getText(), dateArea.getText(), stateBox.getSelectedItem().toString(), contributorBox.getSelectedItem().toString(), "628e06dc9485b8001681cbb1");
	}
}