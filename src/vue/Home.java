package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.parser.ParseException;

import modele.Task;
import service.Requests;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Home extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JList toDoList;
	private JList currentList;
	private JList doneList;
	private boolean isChanging = false;
	private Ajout ajout = new Ajout();
	private Task task = new Task();
	private Requests service = new Requests();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setResizable(false);
					frame.setVisible(true); 
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Home(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Initialisation des labels et boutons
		JLabel titleLabel = new JLabel("Gestion de Projet");
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
				
		JLabel toDoLabel = new JLabel("A faire");
		toDoLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				
		JLabel currentLabel = new JLabel("En cours");
		currentLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				
		JLabel doneLabel = new JLabel("Terminé");
		doneLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton addButton = new JButton("Ajouter");
		JButton descButton = new JButton("Afficher");
		JButton refreshButton = new JButton("Actualiser");
		JButton deleteButton = new JButton("Supprimer");
		
		
		//REQUETE BY STATE_ID POUR RECUPERER LES TACHES EN FONCTION DE LEUR STATUT POUR LES AJOUTER AUX LISTES 
		//Initialisation des listes
		DefaultListModel<String> model;
		try {
			model = service.getTaskByState(1);
			toDoList = new JList<>(model);
	        getContentPane().add(toDoList);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        DefaultListModel<String> model1;
		try {
			model1 = service.getTaskByState(2);
			currentList = new JList<>(model1);
			getContentPane().add(currentList);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		
		 DefaultListModel<String> model2;
		try {
			 model2 = service.getTaskByState(3);
			 doneList = new JList<>(model2);
			 getContentPane().add(doneList);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		//Actualiser les listes a partir de l'api
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home frm1 = new Home();
				Ajout frm2 = new Ajout();
				frm2.setVisible(true);
				frm1.setVisible(false);
			}
		});
		
		descButton.addActionListener(this);
		
		//Supprimer 
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//service.deleteTask(task.getId()); 
					service.deleteTask("628a29208cb58e00161e4197"); 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(task.getName());
			}
		});
		
		
		// Adding listeners to clear selected item on a list
		toDoList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			      if (!isChanging) {
			        isChanging = true;
			        currentList.clearSelection();
			        doneList.clearSelection();
			        isChanging = false;
			      }
			 }
        });

		currentList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			      if (!isChanging) {
			        isChanging = true;
			        toDoList.clearSelection();
			        doneList.clearSelection();
			        isChanging = false;
			      }
			 }
        });
		
		doneList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			      if (!isChanging) {
			        isChanging = true;
			        toDoList.clearSelection();
			        currentList.clearSelection();
			        isChanging = false;
			      }
			 }
        });
		
		
		
		//Initialisation du layout
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addComponent(toDoList, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(deleteButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(refreshButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(59)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(currentList, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
						.addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
					.addGap(54)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(doneList, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
							.addGap(17))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(addButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(descButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addContainerGap())))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(102)
					.addComponent(toDoLabel)
					.addGap(260)
					.addComponent(currentLabel)
					.addPreferredGap(ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
					.addComponent(doneLabel)
					.addGap(106))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(refreshButton)
								.addComponent(addButton))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(deleteButton)
								.addComponent(descButton)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(titleLabel)))
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(currentLabel)
						.addComponent(doneLabel)
						.addComponent(toDoLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(toDoList, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
						.addComponent(currentList, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
						.addComponent(doneList, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		contentPane.setLayout(gl_contentPane);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Afficher")) {
            int index = toDoList.getSelectedIndex();
            int index1 = currentList.getSelectedIndex();
            int index2 = doneList.getSelectedIndex();
            System.out.println("Index Selected: " + index);
            System.out.println("Index Selected current list: " + index1);
            System.out.println("Index Selected done list: " + index2);
            
            //Si index de la liste "en cours" est -1, l'utilisateur a cliqué sur la liste "a faire"
            if(index1 == -1 && index2 == -1) {
            	String name  = (String) toDoList.getSelectedValue();
            	
            	//Appelle API pour récupérer la tache en fonction de son nom
            	this.task = new Task(name, "desc", "12/05/2022", "A faire", "");
                System.out.println("Value Selected: " + name);
                Home frm1 = new Home();
    	    	Description frm2 = new Description(task);
    	    	frm2.setVisible(true);
    	    	frm1.setVisible(false);
            }
            else if(index == -1 && index2 == -1) {
            	String name  = (String) currentList.getSelectedValue();
            	this.task = new Task(name, "desc", "12/05/2022", "En cours", "");
                System.out.println("Value Selected: " + name);
                Home frm1 = new Home();
    	    	Description frm2 = new Description(task);
    	    	frm2.setVisible(true);
    	    	frm1.setVisible(false);
            }
            else if(index == -1 && index1 == -1) {
            	String name  = (String) doneList.getSelectedValue();
            	this.task = new Task(name, "desc", "12/05/2022", "Terminé", "");
                System.out.println("Value Selected: " + name);
                Home frm1 = new Home();
    	    	Description frm2 = new Description(task);
    	    	frm2.setVisible(true);
    	    	frm1.setVisible(false);
            }
            
        }
		
	}
}
