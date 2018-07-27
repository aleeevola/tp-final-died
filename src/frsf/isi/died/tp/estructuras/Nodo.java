package frsf.isi.died.tp.estructuras;

import java.util.*;

public class Nodo {

	private String valor;
	private TipoDeDato tipoNodo;
	private ArrayList<Nodo> hijos;

	public Nodo(TipoDeDato t) {
		valor = "";
		tipoNodo = t;

	}

	public Nodo(String val, TipoDeDato t) {
		valor = val;
		tipoNodo = t;
		hijos = new ArrayList<Nodo>();
	}

	public String getValor() {
		return this.valor;
	}

	public TipoDeDato getTipoNodo() {
		return tipoNodo;
	}

	/**
	 * agrega como hijo del nodo actual
	 * 
	 * @param unNodo
	 *            si cumple con alguna condicion
	 */

	public void agregarHijo(Nodo unNodo) {
		if (unNodo.tipoNodo == TipoDeDato.TITULO) {
			System.out.print("No se puede agregar como hijo");
		}
		if (this.tipoNodo == TipoDeDato.TITULO && (unNodo.tipoNodo == TipoDeDato.METADATO
				|| unNodo.tipoNodo == TipoDeDato.RESUMEN || unNodo.tipoNodo == TipoDeDato.CAPITULO)) {
			this.hijos.add(unNodo);
		} else {
			if (this.tipoNodo == TipoDeDato.METADATO && (unNodo.tipoNodo == TipoDeDato.AUTOR
					|| unNodo.tipoNodo == TipoDeDato.EDITORIAL || unNodo.tipoNodo == TipoDeDato.PALABRA_CLAVE
					|| unNodo.tipoNodo == TipoDeDato.FECHA_PUBLICACION)) {
				this.hijos.add(unNodo);
			}
			if (this.tipoNodo == TipoDeDato.RESUMEN && unNodo.tipoNodo == TipoDeDato.PARRAFO) {
				this.hijos.add(unNodo);
			}
			if (this.tipoNodo == TipoDeDato.CAPITULO && unNodo.tipoNodo == TipoDeDato.SECCION) {
				this.hijos.add(unNodo);
			}
			if (this.tipoNodo == TipoDeDato.SECCION && unNodo.tipoNodo == TipoDeDato.PARRAFO) {
				this.hijos.add(unNodo);
			}
		}

	}

	/**
	 * retorna los hijos del nodo actual
	 */

	public ArrayList<Nodo> getHijos() {
		return this.hijos;
	}

	public ArrayList<Nodo> arbolEnPreorden() {
		ArrayList<Nodo> arbol = new ArrayList<Nodo>();
		if (this == null) {
			return new ArrayList<Nodo>();
		}
		arbol.add(this);
		for (int i = 0; i < this.hijos.size(); i++) {
			arbol.addAll(this.hijos.get(i).arbolEnPreorden());
		}

		return arbol;
	}
}