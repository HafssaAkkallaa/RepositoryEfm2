package com.efm.crjj.ismo.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import org.hibernate.hql.internal.ast.tree.Statement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.efm.crjj.ismo.model.Employe;
import com.efm.crjj.ismo.metier.IMetier;
import com.efm.crjj.ismo.metier.MetierEmploye;
import com.efm.crjj.ismo.model.Employe;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class Principale extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldDepartement;
	private JTextField textFieldSalaire;
	private JTable table;
	private JLabel lblNewLabel_3;
	IMetier<Employe> metier = new MetierEmploye(); 
	ArrayList<Employe> employes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale frame = new Principale();
					frame.setVisible(true);
					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Create the frame.
	 */
	public Principale() {
		setTitle("Gestion des employes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1197, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Nouvel Employe", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 21, 242, 219);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom et prenom :");
		lblNewLabel.setBounds(10, 16, 222, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 41, 222, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Departement");
		lblNewLabel_1.setBounds(10, 72, 222, 14);
		panel.add(lblNewLabel_1);
		
		String[] departements = new String[]{"RH", "Finance", "IT", "Marketing"};
		JComboBox<String> comboBox = new JComboBox<String>(departements);
		comboBox.setBounds(10, 91, 222, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Salaire : ");
		lblNewLabel_2.setBounds(10, 129, 222, 14);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 154, 222, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		ImageIcon saveIcon = new ImageIcon("save.png");
		JButton btnEnregistrer = new JButton("Enregistrer",saveIcon);
		btnEnregistrer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nomPrenom = textField.getText();
				String departement = (String) comboBox.getSelectedItem();
				String salaireStr = textField_1.getText();
				double salaire = 0.0;
				if (!salaireStr.isEmpty()) {
					salaire = Double.parseDouble(salaireStr);
				}
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[]{nomPrenom, departement, salaire});
				lblNewLabel_3.setText("Nombre des employes "+employes.size());
			}
			public void actionPerformed1(ActionEvent e) {
				String nom = textFieldNom.getText();
		        String prenom = textFieldPrenom.getText();
		        String departement = textFieldDepartement.getText();
		        Double salaire = Double.parseDouble(textFieldSalaire.getText());

		        if(nom.equals("") || prenom.equals("") || departement.equals("")  || salaire.equals("")) {
		            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        employes.add(new Employe(nom,prenom,salaire));
		        AbstractTableModel tableModel;
				tableModel.fireTableDataChanged();

		        JOptionPane.showMessageDialog(null, "Employé ajouté avec succès", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		        
		        lblNewLabel_3.setText("Nombre des employes "+employes.size());
		        
		        textFieldNom.setText("");
		        textFieldPrenom.setText("");
		        textFieldDepartement.setText("");
		        textFieldSalaire.setText("");
			}
		});
		btnEnregistrer.setBounds(10, 185, 222, 23);
		panel.add(btnEnregistrer);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Liste des employes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 251, 616, 322);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 27, 576, 241);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Zaid alag", "RH", null},
				{"ZHRI Riyad", "Finance", "34000"},
			},
			new String[] {
				"Nom et Prenom", "Departement", "Salaire"
			}
		));
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Code Employe");
		model.addColumn("Nom");
		model.addColumn("Prénom");
		model.addColumn("Département");
		model.addColumn("Salaire");

		try {
		    Connection connection;
			Statement statement = connection.createStatement();
		    ResultSet resultSet = statement.executeQuery("SELECT * FROM T_Employe");

		    while (resultSet.next()) {
		        String codeEmploye = "E" + String.format("%03d", resultSet.getInt("id"));
		        String nom = resultSet.getString("nom");
		        String prenom = resultSet.getString("prenom");
		        String departement = resultSet.getString("departement");
		        double salaire = resultSet.getDouble("salaire");

		        model.addRow(new Object[] {codeEmploye, nom, prenom, departement, salaire});
		    }

		    resultSet.close();
		    statement.close();

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		JTable table = new JTable(model);
		
		ImageIcon deleteIcon = new ImageIcon("delete.png");
		JButton btnNewButton_1 = new JButton("Supprimer",deleteIcon);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            int response = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer l'employé sélectionné ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		            if (response == JOptionPane.YES_OPTION) {
		                DefaultTableModel model = (DefaultTableModel) table.getModel();
		                model.removeRow(selectedRow);
		                JOptionPane.showMessageDialog(null, "L'employé a été supprimé avec succès.", "Suppression réussie", JOptionPane.INFORMATION_MESSAGE);
		                lblNewLabel_3.setText("Nombre des employes "+employes.size());
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Veuillez sélectionner un employé à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnNewButton_1.setBounds(472, 286, 124, 23);
		panel_1.add(btnNewButton_1);
		
		ImageIcon imprimerIcon = new ImageIcon("imprimer.png");
		JButton btnNewButton_1_1 = new JButton("Imprimer",imprimerIcon);
		btnNewButton_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrinterJob job = PrinterJob.getPrinterJob();
		        job.setPrintable(new Printable() {
					@Override
					public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
						// TODO Auto-generated method stub
						if (pageIndex > 0) {
		                    return Printable.NO_SUCH_PAGE;
		                }
		                graphics.setFont(new Font("Arial", Font.BOLD, 14));
		                graphics.drawString("Liste des employés", 100, 50);
		                graphics.setFont(new Font("Arial", Font.PLAIN, 12));
		                int y = 100;
		                for (int i = 0; i < table.getRowCount(); i++) {
		                    String nom = table.getValueAt(i, 0).toString();
		                    String prenom = table.getValueAt(i, 1).toString();
		                    String sexe = table.getValueAt(i, 2).toString();
		                    String adresse = table.getValueAt(i, 3).toString();
		                    double salaire = (double) table.getValueAt(i, 4);
		                    String ligne = nom + " " + prenom + ", " + sexe + ", " + adresse + ", " + salaire;
		                    graphics.drawString(ligne, 100, y);
		                    y += 20;
		                }
		                return Printable.PAGE_EXISTS;
					}
		        });
		        boolean doPrint = job.printDialog();
		        if (doPrint) {
		            try {
		                job.print();
		            } catch (PrinterException ex) {
		                ex.printStackTrace();
		            }
		        }			
			}
			
		});
		btnNewButton_1_1.setBounds(327, 286, 135, 23);
		panel_1.add(btnNewButton_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_2.setBounds(262, 35, 364, 147);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		lblNewLabel_3.setText("Nombre des employes "+employes.size());
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel_3.setBounds(10, 25, 344, 44);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("0");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_3_1.setBounds(10, 80, 344, 44);
		panel_2.add(lblNewLabel_3_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nombre Employe par departement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(646, 124, 537, 387);
		contentPane.add(panel_3);
		
		
		Map<String, Integer> departementEmployeMap = new HashMap<>();
		for (Employe employe : employes) {
		    String departement = employe.getDepartement();
		    if (departementEmployeMap.containsKey(departement)) {
		        int nombreEmployes = departementEmployeMap.get(departement);
		        departementEmployeMap.put(departement, nombreEmployes + 1);
		    } else {
		        departementEmployeMap.put(departement, 1);
		    }
		}

		
		DefaultPieDataset dataset = new DefaultPieDataset();
		for (Map.Entry<String, Integer> entry : departementEmployeMap.entrySet()) {
		    String departement = entry.getKey();
		    int nombreEmployes = entry.getValue();
		    dataset.setValue(departement, nombreEmployes);
		}

		JFreeChart chart = ChartFactory.createPieChart(
		    "Nombre d'employés par département", 
		    dataset,  
		    true,  
		    true,  
		    false  
		);

		ChartPanel chartPanel = new ChartPanel(chart);

		// Add the chart panel to the panel_3
		panel_3.add(chartPanel);
	}	
}

