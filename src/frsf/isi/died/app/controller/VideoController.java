package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
//import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.app.vista.material.VideoPanel;
import frsf.isi.died.tp.modelo.productos.Tema;
import frsf.isi.died.tp.modelo.productos.Video;
//import frsf.isi.died.tp.modelo.productos.Libro;

public class VideoController {

	private VideoPanel panelVideo;
	private MaterialCapacitacionDao materialDAO;
	
	public VideoController(VideoPanel panel) {
		this.panelVideo = panel;
		this.panelVideo.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarVideo(String titulo,Double costo,Integer duracion, Tema tema) {
		Video l = new Video(0,titulo, costo, duracion,tema);
		materialDAO .agregarVideo(l);
		this.panelVideo.setListaVideos(materialDAO.listaVideos(),true);
	}
	
	public void crearPanel() {		
		this.panelVideo.setListaVideos(materialDAO.listaVideos(),false);
		this.panelVideo.construir();
	}

	public VideoPanel getPanelVideo() {
		return panelVideo;
	}

	public void setPanelVideo(VideoPanel panelVideo) {
		this.panelVideo = panelVideo;
	}
	
	
}
