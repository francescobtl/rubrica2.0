package rubrica;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import rubrica.grafica.Grafica;

public class Main {

	public static void main(String[] args) {
//Path path = c:/Users/Francesco/workspace/rubrica2.0/informazioni.txt;
		//System.out.println(Files.exists(p1));
		    // ...
		//File file = new File("informazioni");
		
//		for (final File fileEntry : file.listFiles()) {
//	        
//	            System.out.println(fileEntry.getName());
//	        }
		//Path p1 = Paths.get("/informazioni/Persona"+progr+".txt");
		//Path p1 = Paths.get("informazioni/Persona0.txt");
		//System.out.println(Files.exists(p1));
		//try {
		//	Files.delete(p1);
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		new Grafica();
		//Path source = Paths.get("informazioni.txt");
		//Files.move(source, source.resolveSibling("renamed.txt"));
	}

}
