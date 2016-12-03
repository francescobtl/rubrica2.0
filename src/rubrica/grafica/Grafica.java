package rubrica.grafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import rubrica.logica.Persona;
import rubrica.logica.Rubrica;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;




@SuppressWarnings("serial")
public class Grafica extends JFrame {

	private JFrame frame;
	private JButton bn, bm, be;
	private Rubrica r;
	private JTable table;

	public Grafica(){
		this.setTitle("Gestore Rubrica");
		this.r= new Rubrica();
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Object rowData[][] = new Object [r.getVoci().size()] [3];
		Object columnNames[] = { "Nome", "Cognome", "Telefono" };
		int i = 0;
		for (Persona p : this.r.getVoci()){
			rowData[i][0] = p.getNome();
			rowData[i][1] = p.getCognome();
			rowData[i][2] = p.getTelefono();
			i++;
		}
		table = new JTable(new DefaultTableModel(rowData, columnNames));
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(800, 400);
		bn = new JButton("Nuovo");
		bm = new JButton("Modifica");
		be = new JButton("Elimina");
		bn.setActionCommand("aggiungi");
		bm.setActionCommand("modifica");
		be.setActionCommand("elimina");
		JPanel pulsanti = new JPanel();
		pulsanti.setLayout(new GridLayout(1, 3));
		pulsanti.add(bn);
		pulsanti.add(bm);
		pulsanti.add(be);
		bn.addActionListener(new Listener());
		bm.addActionListener(new Listener());
		be.addActionListener(new Listener());
		frame.add(pulsanti, BorderLayout.SOUTH);
		frame.setVisible(true);
	}





	private class Listener implements ActionListener{    
		public void actionPerformed(ActionEvent e){  
			String command = e.getActionCommand();  
			if( command.equals( "elimina" )){
					removeSelectedRow(table);
			}

			if( command.equals( "aggiungi" ))  {
				new Editor_Persona(r, table);
			}

			if( command.equals( "modifica" ))  {
				int row = table.getSelectedRow();

				if (row == (-1)){
					JOptionPane.showMessageDialog(frame, "Nessuna voce selezionata", "Errore", JOptionPane.ERROR_MESSAGE);
				} else {
					Persona p = r.getVoci().get(row);
					new Editor_Persona(r, table, p, row);
				}

			}

		}  

		public void removeSelectedRow(JTable table){
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			int row = table.getSelectedRow();
			if (row==(-1)){
				JOptionPane.showMessageDialog(frame, "Nessuna voce selezionata", "Errore", JOptionPane.ERROR_MESSAGE);
			}else{
				int reply = JOptionPane.showConfirmDialog(null, "Eliminare la persona " + r.getVoci().get(row).getNome() + " " + r.getVoci().get(row).getCognome(), null, JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					r.eliminaVoci(row);
					model.removeRow(row);
				}
			}



		}


	}



}
