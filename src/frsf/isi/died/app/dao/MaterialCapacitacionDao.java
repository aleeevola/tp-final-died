package frsf.isi.died.app.dao;

import java.util.List;
import java.util.PriorityQueue;

import frsf.isi.died.tp.estructuras.Nodo;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Tema;
import frsf.isi.died.tp.modelo.productos.Video;

public interface MaterialCapacitacionDao {

	public void agregarLibro(Libro mat);
	public void agregarVideo(Video mat);
	public List<Libro> listaLibros();
	public List<Video> listaVideos();
	public List<MaterialCapacitacion> listaMateriales();
	public MaterialCapacitacion findById(Integer id);
	public void crearCamino(Integer idOrigen, Integer idDestino);
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos);
	List<MaterialCapacitacion> buscarMaterial(String titulo, Double calificacion, String tema, String fechaPublicacionDesde,
			String fechaPublicacionHasta, String orden);
	
	public void agregarDeseo(MaterialCapacitacion mat);
	public List<MaterialCapacitacion> listaDeseos();
	public void eliminarMaterial(MaterialCapacitacion mat);
	public void actualizarArchivos();
	public void editarMaterial(MaterialCapacitacion mat,String titulo,Double costo, 
			Integer calificacion,Relevancia relv,Tema tem,Double precio,Integer paginas, Integer duracion);
	public void agregarDocumento(Nodo nodo);
}
