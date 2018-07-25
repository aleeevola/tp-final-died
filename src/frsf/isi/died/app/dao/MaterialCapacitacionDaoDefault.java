package frsf.isi.died.app.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import frsf.isi.died.app.dao.util.CsvDatasource;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.modelo.Biblioteca;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class MaterialCapacitacionDaoDefault implements MaterialCapacitacionDao {

	private static Grafo<MaterialCapacitacion> GRAFO_MATERIAL = new Grafo<MaterialCapacitacion>();
	private static Integer SECUENCIA_ID = 0;
	private static Biblioteca biblioteca = new BibliotecaABB();

	//pila deseos
	public static PriorityQueue<MaterialCapacitacion> deseos = new PriorityQueue<>(new DeseoComparator());
	
	private CsvDatasource dataSource;
	

	public void agregarDeseo(MaterialCapacitacion mat){
		//agrego a la pila
		deseos.add(mat);
	}
	
	public List<MaterialCapacitacion> listaDeseos(){
		//saco de la pila
		List<MaterialCapacitacion> ld=new ArrayList<>();
		while (!deseos.isEmpty()) {
            MaterialCapacitacion mat = deseos.poll();
            ld.add(mat);
        }
		//cargo de nuevo la pila
		for(MaterialCapacitacion c :ld){
            deseos.add(c);
        }
		
		return ld;
	}




	public MaterialCapacitacionDaoDefault() {
		dataSource = new CsvDatasource();
		if (GRAFO_MATERIAL.esVacio()) {
			cargarGrafo();
		}
	}

	private void cargarGrafo() {
		List<List<String>> libros = dataSource.readFile("libros.csv");
		for (List<String> filaLibro : libros) {
			Libro aux = new Libro();
			aux.loadFromStringRow(filaLibro);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> videos = dataSource.readFile("videos.csv");
		for (List<String> filaVideo : videos) {
			Video aux = new Video();
			aux.loadFromStringRow(filaVideo);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> aristas = dataSource.readFile("aristas.csv");
		for (List<String> filaArista : aristas) {
			MaterialCapacitacion n1 = this.findById(Integer.valueOf(filaArista.get(0)));
			MaterialCapacitacion n2 = this.findById(Integer.valueOf(filaArista.get(2)));
			GRAFO_MATERIAL.conectar(n1, n2);
		}
	}

	@Override
	public void agregarLibro(Libro mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);
		biblioteca.agregar(mat);
		try {
			dataSource.agregarFilaAlFinal("libros.csv", mat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void agregarVideo(Video mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);
		biblioteca.agregar(mat);
		try {
			dataSource.agregarFilaAlFinal("videos.csv", mat);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<MaterialCapacitacion> buscarMaterial(String titulo, Double calificacion, String tema, String fechaPublicacionDesde,
			String fechaPublicacionHasta,String orden) {
		List<MaterialCapacitacion> materiales = new ArrayList<MaterialCapacitacion>();
		for (MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if (titulo == null || mat.getTitulo().contains(titulo)) {
				if (calificacion == null || mat.getCalificacion().equals(calificacion.intValue())) {
					if (tema == null || mat.getTema().toString().equals(tema)) {
						System.out.println(fechaPublicacionDesde+"1");
						System.out.println(fechaPublicacionHasta+"2");
						System.out.println(mat.getFechaPublicacion()+"3");
						System.out.println(mat.getFechaPublicacion().compareTo(fechaPublicacionDesde));
						System.out.println(mat.getFechaPublicacion().compareTo(fechaPublicacionHasta));
						if ((fechaPublicacionDesde == null && fechaPublicacionHasta == null) || (mat.getFechaPublicacion().compareTo(fechaPublicacionDesde)>0 && mat.getFechaPublicacion().compareTo(fechaPublicacionHasta)<0)) {
							System.out.println(fechaPublicacionDesde+"1 1");
							System.out.println(fechaPublicacionHasta+"2 2");
							System.out.println(mat.getFechaPublicacion()+"3");
							
							materiales.add(mat);
						
						}
					}
				}
			}
		}
		
		System.out.println(materiales.get(0));
		
		Comparator<MaterialCapacitacion> comparaTitulo= (mc1,mc2)-> mc1.getTitulo().compareTo(mc2.getTitulo());
		Comparator<MaterialCapacitacion> comparaPrecio= (mc1,mc2)-> mc1.precio().intValue()- mc2.precio().intValue();
		Comparator<MaterialCapacitacion> comparaCalificacion= (mc1,mc2)-> mc1.getCalificacion()- mc2.getCalificacion();
		
		
		return materiales;
		
	}

	@Override
	public List<Libro> listaLibros() {
		List<Libro> libros = new ArrayList<>();
		for (MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if (mat.esLibro())
				libros.add((Libro) mat);
		}
		return libros;
	}

	@Override
	public List<Video> listaVideos() {
		List<Video> vids = new ArrayList<>();
		for (MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if (mat.esVideo())
				vids.add((Video) mat);
		}
		return vids;
	}

	@Override
	public List<MaterialCapacitacion> listaMateriales() {
		// TODO Auto-generated method stub
		return GRAFO_MATERIAL.listaVertices();
	}

	@Override
	public MaterialCapacitacion findById(Integer id) {
		// TODO Auto-generated method stub
		for (MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if (mat.getId().equals(id))
				return mat;
		}
		return null;
	}

	@Override
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		return GRAFO_MATERIAL.buscarCaminoNSaltos(n1, n2, saltos);
	}

	@Override
	public void crearCamino(Integer idOrigen, Integer idDestino) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		GRAFO_MATERIAL.conectar(n1, n2);
		List<String> fila = new ArrayList<>();
		fila.add(n1.getId() + "");
		fila.add(n1.getTitulo());
		fila.add(n2.getId() + "");
		fila.add(n2.getTitulo());
		try {
			dataSource.agregarFilaAlFinal("aristas.csv", fila);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
