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
	private Nodo nodoinicial;
	
	public DocumentoController(DocumentoPanel panel) {
		this.panelDocumento = panel;
		this.panelDocumento.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarNodo(Nodo nodoPadre, Nodo nodoHijo) {
		
		nodoPadre.agregarHijo(nodoHijo);
		
		this.panelDocumento.setListaDocumento(nodoinicial.arbolEnPreorden(),true);
	}
	
	public void crearPanel(MaterialCapacitacion mat) {	
		Nodo nodo = new Nodo(mat.getTitulo(),TipoDeDato.TITULO);
		nodoinicial=nodo;
		materialDAO.agregarDocumento(nodo);
		this.panelDocumento.setListaDocumento(nodo.arbolEnPreorden(),false);
		this.panelDocumento.construir();
		this.panelDocumento.setVisible(true);
	}

	public DocumentoPanel getPanelLibro() {
		return panelDocumento;
	}

	public void setPanelDocumento(DocumentoPanel panelDocumento) {
		this.panelDocumento = panelDocumento;
	}
	
	
}