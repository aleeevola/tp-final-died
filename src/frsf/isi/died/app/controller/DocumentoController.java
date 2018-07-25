package frsf.isi.died.app.controller;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.DocumentoPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class DocumentoController {

	private DocumentoPanel panelDocumento;
	private MaterialCapacitacionDao materialDAO;
	
	public DocumentoController(DocumentoPanel panel) {
		this.panelDocumento= panel;
		this.panelDocumento.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}
	
	public void iniciarDoc(MaterialCapacitacion mat){
		
	}

	//creo que no se va a usar
	public void crearPanel() {		
		this.panelDocumento.setListaDocumento(materialDAO.listaMateriales(),false);
		this.panelDocumento.construir();
	}

	
	public DocumentoPanel getPanelDocumento() {
		return panelDocumento;
	}

	public void setPanelDocumento(DocumentoPanel panelDocumento) {
		this.panelDocumento = panelDocumento;
	}
	
	
}
