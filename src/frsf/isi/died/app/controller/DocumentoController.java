package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.DocumentoPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.tp.modelo.productos.Libro;

public class DocumentoController {

	private DocumentoPanel panelDocumento;
	private MaterialCapacitacionDao materialDAO;
	
	public DocumentoController(DocumentoPanel panel) {
		this.panelDocumento = panel;
		this.panelDocumento.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarDocumento(String titulo,Double costo,Double precio,Integer paginas) {	
		//Libro l = new Libro(0,titulo, costo, precio, paginas) ;
		//materialDAO .agregarLibro(l);
		//this.panelDocumento.setListaLibros(materialDAO.listaLibros(),true);
	}
	
	public void crearPanel() {		
		this.panelDocumento.setListaDocumento(materialDAO.listaLibros(),false);
		this.panelDocumento.construir();
	}

	public DocumentoPanel getPanelLibro() {
		return panelDocumento;
	}

	public void setPanelDocumento(DocumentoPanel panelDocumento) {
		this.panelDocumento = panelDocumento;
	}
	
	
}