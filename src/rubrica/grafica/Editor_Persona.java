package rubrica.grafica;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import rubrica.logica.ConvalidaDati;
import rubrica.logica.Persona;
import rubrica.logica.Rubrica;


@SuppressWarnings("serial")
public class Editor_Persona extends JFrame{

	private JPanel panel;
	private JTextField field1, field2, field3, field4, field5;
	private JButton b1, b2;
	static int posizione;
	static JLabel l;
	private Rubrica r;
	private JTable table;

	public Editor_Persona (Rubrica r, JTable table){
		this.r = r;
		this.table=table;
		this.setTitle("Editor Persona");
		field1 = new JTextField();
		field2 = new JTextField();
		field3 = new JTextField();
		field4 = new JTextField();
		field5 = new JTextField();
		this.panel= new JPanel(new GridLayout(6, 2));
		panel.add(new JLabel("Nome:"));
		panel.add(field1);
		panel.add(new JLabel("Cognome:"));
		panel.add(field2);
		panel.add(new JLabel("Indirizzo:"));
		panel.add(field3);
		panel.add(new JLabel("Telefono:"));
		panel.add(field4);
		panel.add(new JLabel("Età:"));
		panel.add(field5);
		b1 = new JButton("Salva");
		b1.setActionCommand("salva");
		b2 = new JButton("Annulla");
		b2.setActionCommand("annulla");
		b1.addActionListener(new Listener());
		b2.addActionListener(new Listener());
		panel.add(b1);
		panel.add(b2);
		add(panel, BorderLayout.SOUTH);
		setLayout(new FlowLayout());
		//pack();
		setSize(300, 220);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}


	public Editor_Persona (Rubrica r, JTable table, Persona p, int pos){
		this.r = r;
		this.table=table;
		this.setTitle("Editor Persona");
		posizione = pos;
		field1 = new JTextField(p.getNome());
		field2 = new JTextField(p.getCognome());
		field3 = new JTextField(p.getIndirizzo());
		field4 = new JTextField(p.getTelefono());
		field5 = new JTextField(""+p.getEta());
		this.panel= new JPanel(new GridLayout(6, 2));
		panel.add(new JLabel("Nome:"));
		panel.add(field1);
		panel.add(new JLabel("Cognome:"));
		panel.add(field2);
		panel.add(new JLabel("Indirizzo:"));
		panel.add(field3);
		panel.add(new JLabel("Telefono:"));
		panel.add(field4);
		panel.add(new JLabel("Età:"));
		panel.add(field5);
		b1 = new JButton("Salva");
		b1.setActionCommand("salva2");
		b2 = new JButton("Annulla");
		b2.setActionCommand("annulla");
		b1.addActionListener(new Listener());
		b2.addActionListener(new Listener());
		panel.add(b1);
		panel.add(b2);
		add(panel, BorderLayout.SOUTH);
		setLayout(new FlowLayout());
		setSize(300, 220);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}



	private class Listener implements ActionListener{    
		public void actionPerformed(ActionEvent e){  
			String command = e.getActionCommand();  
			if( command.equals( "salva" ))  {
				Persona p = new Persona();
				String nome = (field1.getText());
				String cognome = (field2.getText());
				String indirizzo = (field3.getText());
				String telefono = (field4.getText());
				String eta = (field5.getText());
				ConvalidaDati cd = new ConvalidaDati();
				boolean err = cd.convalida(nome, cognome, indirizzo, telefono, eta);
				if (!err){
				p.setNome(field1.getText());
				p.setCognome(field2.getText());
				p.setEta(Integer.parseInt(field5.getText()));
				p.setIndirizzo(field3.getText());
				p.setTelefono(field4.getText());
				r.addVoce(p);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[]{field1.getText(), field2.getText(), field4.getText()});
				dispose();
				}
				else JOptionPane.showMessageDialog(panel, "Dati non validi", "Errore", JOptionPane.ERROR_MESSAGE);
			}

			if( command.equals( "annulla" ))  {
				dispose();
			}

			if( command.equals( "salva2" ))  {
				Persona p = new Persona();
				p.setNome(field1.getText());
				p.setCognome(field2.getText());
				p.setEta(Integer.parseInt(field5.getText()));
				p.setIndirizzo(field3.getText());
				p.setTelefono(field4.getText());
				r.modificaVoce(posizione, p);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setValueAt(r.getVoci().get(posizione).getNome(), posizione, 0);
				model.setValueAt(r.getVoci().get(posizione).getCognome(), posizione, 1);
				model.setValueAt(r.getVoci().get(posizione).getTelefono(), posizione, 2);
				dispose();
			}


		}}  


}


