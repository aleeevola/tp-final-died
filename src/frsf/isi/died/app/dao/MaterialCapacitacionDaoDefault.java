package frsf.isi.died.app.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import frsf.isi.died.app.dao.util.CsvDatasource;
import frsf.isi.died.app.dao.util.CsvRecord;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.estructuras.Nodo;
import frsf.isi.died.tp.modelo.Biblioteca;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Tema;
import frsf.isi.died.tp.modelo.productos.Video;

public class MaterialCapacitacionDaoDefault implements MaterialCapacitacionDao {

	private static Grafo<MaterialCapacitacion> GRAFO_MATERIAL = new Grafo<MaterialCapacitacion>();
	private static Integer SECUENCIA_ID = 0;
	private static Biblioteca biblioteca = new BibliotecaABB();

	// pila deseos
	public static PriorityQueue<MaterialCapacitacion> deseos = new PriorityQueue<>(new DeseoComparator());

	// documentos
	public static List<Nodo> documentos = new ArrayList<>();

	private CsvDatasource dataSource;

	public void agregarDocumento(Nodo nodo) {
		documentos.add(nodo);
	}
	
	public boolean contieneDoc(Nodo nodo) {
		return documentos.contains(nodo);
	}

	public List<Nodo> listaDocumentos() {
		List<Nodo> doc = new ArrayList<>();
		for (Nodo n : documentos) {
			doc.add(n);
		}
		return doc;
	}

	public void agregarDeseo(MaterialCapacitacion mat) {
		// agrego a la pila
		deseos.add(mat);
	}

	public List<MaterialCapacitacion> listaDeseos() {
		// saco de la pila
		List<MaterialCapacitacion> ld = new ArrayList<>();
		while (!deseos.isEmpty()) {
			MaterialCapacitacion mat = deseos.poll();
			ld.add(mat);
		}
		// cargo de nuevo la pila
		for (MaterialCapacitacion c : ld) {
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

	public void editarMaterial(MaterialCapacitacion mat, String titulo, Double costo, Integer calificacion,
			Relevancia relv, Tema tem, Double precio, Integer paginas, Integer duracion) {
		int i = 0;
		for (MaterialCapacitacion e : GRAFO_MATERIAL.listaVertices()) {
			if (e.equals(mat)) {

				GRAFO_MATERIAL.getVertices().get(i).getValor().setTitulo(titulo);
				GRAFO_MATERIAL.getVertices().get(i).getValor().setCosto(costo);
				GRAFO_MATERIAL.getVertices().get(i).getValor().setCalificacion(calificacion);
				GRAFO_MATERIAL.getVertices().get(i).getValor().setRelevancia(relv);
				GRAFO_MATERIAL.getVertices().get(i).getValor().setTema(tem);
				if (mat.esLibro()) {
					((Libro) GRAFO_MATERIAL.getVertices().get(i).getValor()).setPrecioCompra(precio);
					((Libro) GRAFO_MATERIAL.getVertices().get(i).getValor()).setPaginas(paginas);
				} else {
					((Video) GRAFO_MATERIAL.getVertices().get(i).getValor()).setDuracionEnSegundos(duracion);
				}
				this.actualizarArchivos();
				break;
			}
			i++;
		}
	}

	public void eliminarMaterial(MaterialCapacitacion mat) {
		int i = 0;
		for (MaterialCapacitacion e : GRAFO_MATERIAL.listaVertices()) {
			if (e.equals(mat)) {

				GRAFO_MATERIAL.deletNodo(i);

				this.actualizarArchivos();
			}
			i++;
		}
	}

	public void actualizarArchivos() {
		try {
			List<CsvRecord> libros = new ArrayList<>();
			for (MaterialCapacitacion mat : this.listaLibros()) {
				libros.add((CsvRecord) mat);
			}
			System.out.println("CAAAAAAAAAAAAAA1");
			dataSource.guardarColeccion("libros.csv", libros);

			List<CsvRecord> videos = new ArrayList<>();
			for (MaterialCapacitacion mat : this.listaVideos()) {
				videos.add((CsvRecord) mat);
			}
			System.out.println("CAAAAAAAAAAAAAA2");
			dataSource.guardarColeccion("videos.csv", videos);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<MaterialCapacitacion> buscarMaterial(String titulo, Double calificacion, String tema,
			String fechaPublicacionDesde, String fechaPublicacionHasta, String orden) {
		List<MaterialCapacitacion> materiales = new ArrayList<MaterialCapacitacion>();
		for (MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if (titulo == null || mat.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
				if (calificacion == null || mat.getCalificacion().equals(calificacion.intValue())) {
					if (tema == null || mat.getTema().toString().equals(tema)) {
						if ((fechaPublicacionDesde == null && fechaPublicacionHasta == null)
								|| (mat.getFechaPublicacion().compareTo(fechaPublicacionDesde) >= 0
										&& mat.getFechaPublicacion().compareTo(fechaPublicacionHasta) <= 0)) {

							materiales.add(mat);

						}
					}
				}
			}
		}
		
		if (orden != null) {
			switch (orden) {
			case "Título":
				Comparator<MaterialCapacitacion> comparaTitulo = (mc1, mc2) -> mc1.getTitulo().toLowerCase()
				.compareTo(mc2.getTitulo().toLowerCase());
				Collections.sort(materiales, comparaTitulo);
				break;
			case "Calificación":				
				Comparator<MaterialCapacitacion> comparaCalificacion = (mc1, mc2) -> mc2.getCalificacion()
						- mc1.getCalificacion();
				Collections.sort(materiales, comparaCalificacion);
				break;
			case "Precio":
				Comparator<MaterialCapacitacion> comparaPrecio = (mc1, mc2) -> mc1.precio().intValue()
				- mc2.precio().intValue();
				Collections.sort(materiales, comparaPrecio);
				break;
			case "Fecha de publicación":
				Comparator<MaterialCapacitacion> comparaFecha = (mc1, mc2) -> mc1.getFechaPublicacion()
				.compareTo(mc2.getFechaPublicacion());
				Collections.sort(materiales, comparaFecha);
				break;
			case "Relevancia":
				Comparator<MaterialCapacitacion> comparaRelevancia = (mc1, mc2) -> mc1.getRelevancia()
				.compareTo(mc2.getRelevancia());
				Collections.sort(materiales, comparaRelevancia);
				break;
			case "Page Rank":
				//Comparator<MaterialCapacitacion> comparaPageRank = (mc1, mc2) -> this.PageRank(mc1).intValue()-this.PageRank(mc2).intValue();
				Comparator<MaterialCapacitacion> comparaPageRank = (mc1, mc2) -> mc1.getPageRank().intValue()-mc2.getPageRank().intValue();
				Collections.sort(materiales, comparaPageRank);
			}
		}

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

	public boolean esAdyacente(MaterialCapacitacion m1,MaterialCapacitacion m2) {
		List<MaterialCapacitacion> ady=new ArrayList<>();
		ady=GRAFO_MATERIAL.getAdyacentes(m1);
		for (MaterialCapacitacion unAdy: ady) {
	if (unAdy.equals(m2))	
	return true;	
			
		}
	return false;
	}
	
	
	public void pageRank(ArrayList<MaterialCapacitacion> materiales) {
		Double p, e=0.22;
		boolean bandera=true;
		while  (!bandera) {
		boolean seCumple=true;
			for (MaterialCapacitacion mat : materiales) {
		p=mat.getPageRank();
			mat.setPageRank(calcularPageRank(mat));
			if ((e>mat.getPageRank()-p) && seCumple)
		bandera=false;			
			else {
				seCumple=false;
				bandera=true;}
			}
		}

		}
	
	public Double calcularPageRank(MaterialCapacitacion mat) {
		double p = 0, d=0.85;
		for (MaterialCapacitacion material : GRAFO_MATERIAL.listaVertices()) {
			if (material.getTema() == mat.getTema() && this.esAdyacente(material, mat))
//				System.out.println(GRAFO_MATERIAL.gradoSalida(material));
//			System.out.println(this.calcularPageRank(material));
				p = p + this.calcularPageRank(material) / GRAFO_MATERIAL.gradoSalida(material);
		}
		if (p == 0)
			return 1-d;

		return (1-d) + d * p;

	}
	
/*
	public Double PageRank(MaterialCapacitacion mat) {
		return GRAFO_MATERIAL.calcularPageRank(mat);		
	}
*/
	// r(a)=(1-d)+d*sum(pr/c)
	// pr es los nodos que tienen un enlace hacia a, y c los enlaces salientes de
	// ese nodo.

	
}