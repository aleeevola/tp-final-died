package frsf.isi.died.app.controller;

import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.app.vista.material.verDocPanel;
import frsf.isi.died.tp.estructuras.Nodo;
import frsf.isi.died.tp.estructuras.TipoDeDato;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class verDocController {

	private verDocPanel panelDeseos;
	private MaterialCapacitacionDao materialDAO;
	
	public verDocController(verDocPanel panel) {
		this.panelDeseos = panel;
		this.panelDeseos.setController(this);
		materialDAO = new MaterialCapacitacionDaoDefault();
	}

	
	public void agregarDeseos(MaterialCapacitacion mat) {	
		
	}
	
	public void crearPanel() {
		this.panelDeseos.setListaDocumento(materialDAO.listaDocumentos(),false);
		this.panelDeseos.construir();
	}

	public verDocPanel getPanelDeseos() {
		return panelDeseos;
	}

	public void setPanelDoc(verDocPanel panelDeseos) {
		this.panelDeseos = panelDeseos;
	}
	
	public void buscar(String titulo,String autor,String seccion,String capitulo,String editorial,String palabra,
	String fecha) {
		List<Nodo> resultados= new ArrayList<>();
		for(Nodo n : materialDAO.listaDocumentos()) {
			List<Nodo> lista=n.arbolEnPreorden();
			if(
			contiene(TipoDeDato.TITULO,titulo,lista) &&
			contiene(TipoDeDato.AUTOR,autor,lista) &&
			contiene(TipoDeDato.SECCION,seccion,lista) &&
			contiene(TipoDeDato.CAPITULO,capitulo,lista) &&
			contiene(TipoDeDato.EDITORIAL,editorial,lista) &&
			contiene(TipoDeDato.PALABRA_CLAVE,palabra,lista) &&
			contiene(TipoDeDato.FECHA_PUBLICACION,fecha,lista)
			) resultados.add(n);
		}
		panelDeseos.setListaDocumento(resultados, true);
	}
	
	public boolean contiene(TipoDeDato tipo,String txt, List<Nodo> lista) {
		if(txt==null) return true;
		for(Nodo n: lista) {
			if(n.getTipoNodo()==tipo && n.getValor().contains(txt)) return true;
		}
		return false;
	}
}
