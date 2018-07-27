package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.DocumentoPanel;
import frsf.isi.died.app.vista.material.LibroPanel;
import frsf.isi.died.tp.estructuras.Nodo;
import frsf.isi.died.tp.estructuras.TipoDeDato;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

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
	
	public void crearPanel(MaterialCapacitacion mat) {	
		Nodo nodo = new Nodo(mat.getTitulo(),TipoDeDato.TITULO);
		materialDAO.agregarDocumento(nodo);
		this.panelDocumento.setListaDocumento(nodo.arbolEnPreorden(),false);
		this.panelDocumento.construir(nodo);
		this.panelDocumento.setVisible(true);
	}

	public DocumentoPanel getPanelLibro() {
		return panelDocumento;
	}

	public void setPanelDocumento(DocumentoPanel panelDocumento) {
		this.panelDocumento = panelDocumento;
	}
	
	
}