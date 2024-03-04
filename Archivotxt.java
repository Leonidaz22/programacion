package Libreria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Parte2.ListaCategorias;
import Parte2.Categoria;
	
	public class Archivotxt extends ListaCategorias{
		private String nombreArchivo;
	
		public Archivotxt(String nombreArchivo) {
			this.nombreArchivo = nombreArchivo;
		}
	
	public void guardar (String texto) {
	try (BufferedWriter writer = new BufferedWriter (new FileWriter (nombreArchivo))) {
	writer.write (texto);
	} catch (IOException e) {
	System.err.println("Error al guardar el archivo: " + e.getMessage());
	}
	}
	
	public ArrayList<String> cargar () {
	String[] elementos = null;
	ArrayList<String> lineas = new ArrayList<>();
	try (BufferedReader reader = new BufferedReader (new FileReader (nombreArchivo))) {
	String linea;
	while ((linea = reader.readLine()) != null) {
		elementos = linea.split(",");
	}
	for (String elemento : elementos) {
	lineas.add(elemento.trim());
	}
	} catch (IOException e) {
	System.err.println("Error al cargar el archivo: " + e.getMessage());
	}
	return lineas;
	}
	
	public void cargarCategorias (ArrayList<String[]> categoriasString) {
		for (String[] categoriaString: categoriasString)
		{ String idCategoria = categoriaString[0];
		String nombreCategoria = categoriaString[1];
		Categoria categoria = new Categoria (idCategoria, nombreCategoria);
		this.agregarCategoria (categoria); 
		}
	}
	
}