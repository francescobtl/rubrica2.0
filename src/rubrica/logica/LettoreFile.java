package rubrica.logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LettoreFile {


	public ArrayList<Persona> leggiFile (){
		File file = new File("informazioni.txt");
		ArrayList<Persona> voci =new ArrayList<>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if (line.matches("^*.*;.*;.*;.*;.*$")) voci.add(leggiRiga(line));

			}
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		}
		finally {
			if (scanner != null)scanner.close();
		}

		return voci;

	}

	public Persona leggiRiga (String riga){
		String [] campi = riga.split(";");
		Persona p = new Persona(campi[0], campi[1], campi[2], campi[3], Integer.parseInt(campi[4]));
		return p;
	}




	public void addLinea (Persona p, int size){

		PrintStream scrivi = null;
		try {

			scrivi = new PrintStream(new FileOutputStream("informazioni.txt", true));
			if (size == 0) {
				scrivi.append(p.getNome() + ";" + p.getCognome() + ";" + p.getIndirizzo() + ";" + p.getTelefono() + ";" + p.getEta());
			}else{
				scrivi.append(System.lineSeparator());
				scrivi.append(p.getNome() + ";" + p.getCognome() + ";" + p.getIndirizzo() + ";" + p.getTelefono() + ";" + p.getEta());
			}
			scrivi.close();


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		finally {
			if (scrivi != null) scrivi.close();
		}

	}
	
	public void rewriteFile (ArrayList<Persona> voci){
		PrintStream scrivi = null;
		try
		{
			FileOutputStream f = new FileOutputStream("informazioni.txt");
			scrivi = new PrintStream(f);
			for (Persona p : voci){
				scrivi.println(p.getNome() + ";" + p.getCognome() + ";" + p.getIndirizzo() + ";" + p.getTelefono() + ";" + p.getEta());

			}
			
		}
		catch (IOException e)
		{
			System.out.println("Errore: " + e);
			
		}
		finally {
			if (scrivi != null) scrivi.close();
		}

	}




}
