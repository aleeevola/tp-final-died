package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.DocumentoController;
import frsf.isi.died.app.controller.verDocController;
import frsf.isi.died.tp.estructuras.Nodo;



public class verDocPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;

	private JLabel lblTitulo;
//	private JLabel lblMetadato;
	private JLabel lblAutor;
	private JLabel lblSeccion;
//	private JLabel lblParrafo;
	private JLabel lblCapitulo;
	private JLabel lblEditorial;
//	private JLabel lblResumen;
	private JLabel lblPalabra;
	private JLabel lblFecha;
	
	private JTextField txtTitulo;
//	private JTextField txtMetadato;
	private JTextField txtAutor;
	private JTextField txtSeccion;
//	private JTextField txtParrafo;
	private JTextField txtCapitulo;
	private JTextField txtEditorial;
//	private JTextField txtResumen;
	private JTextField txtPalabra;
	private JTextField txtFecha;
	
	private JButton btnFiltrar;
	

	private verDocTableModel tableModel;

	private verDocController controller;

	public verDocPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new verDocTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		lblAutor = new JLabel("Autor: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		this.add(lblAutor, gridConst);
		
		lblSeccion = new JLabel("Seccion: ");
		gridConst.gridx=0;
		gridConst.gridy=2;
		this.add(lblSeccion, gridConst);

		lblCapitulo = new JLabel("Capitulo: ");
		gridConst.gridx=0;
		gridConst.gridy=3;
		this.add(lblCapitulo, gridConst);
		
		lblEditorial = new JLabel("Editorial: ");
		gridConst.gridx=0;
		gridConst.gridy=4;
		this.add(lblEditorial, gridConst);

		lblPalabra = new JLabel("Palabra clave: ");
		gridConst.gridx=0;
		gridConst.gridy=5;
		this.add(lblPalabra, gridConst);
		
		lblFecha = new JLabel("Fecha publicacion: ");
		gridConst.gridx=0;
		gridConst.gridy=6;
		this.add(lblFecha, gridConst);
		
		///contenedores
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridy=0;
		this.add(txtTitulo, gridConst);
		
		txtAutor = new JTextField();
		txtAutor.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridy=1;
		this.add(txtAutor, gridConst);

		txtSeccion = new JTextField();
		txtSeccion.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridy=2;
		this.add(txtSeccion, gridConst);
		
		txtCapitulo = new JTextField();
		txtCapitulo.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridy=3;
		this.add(txtCapitulo, gridConst);
		
		txtEditorial = new JTextField();
		txtEditorial.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridy=4;
		this.add(txtEditorial, gridConst);
		
		txtPalabra = new JTextField();
		txtPalabra.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridy=5;
		this.add(txtPalabra, gridConst);
		
		txtFecha = new JTextField();
		txtFecha.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridy=6;
		this.add(txtFecha, gridConst);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener( e ->{
			try {
				iniciarFiltro();
			}catch(Exception ex) {
//			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridy=8;
		gridConst.gridx=2;
		this.add(btnFiltrar, gridConst);
		
				
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=4;
		gridConst.gridy=0;
		gridConst.gridwidth=4;
		gridConst.gridheight=9;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
		
	}

	public verDocController getController() {
		return controller;
	}

	public void setController(verDocController verDocController) {
		this.controller = verDocController;
	}
	
	public void setListaDocumento(List<Nodo> deseosLista,boolean actualizar) {
		this.tableModel.setDeseos(deseosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

	public void iniciarFiltro(){
		String titulo=null;
		String autor=null;
		String seccion=null;
		String capitulo=null;
		String editorial=null;
		String palabra=null;
		String fecha=null;
		
		if(!txtTitulo.getText().isEmpty()) titulo=txtTitulo.getText();
		if(!txtAutor.getText().isEmpty()) autor=txtAutor.getText();
		if(!txtSeccion.getText().isEmpty()) seccion=txtSeccion.getText();
		if(!txtCapitulo.getText().isEmpty()) capitulo=txtCapitulo.getText();
		if(!txtEditorial.getText().isEmpty()) editorial=txtEditorial.getText();
		if(!txtPalabra.getText().isEmpty()) palabra=txtPalabra.getText();
		if(!txtFecha.getText().isEmpty()) fecha=txtFecha.getText();
		
		controller.buscar(titulo, autor, seccion, capitulo, editorial, palabra, fecha);
	}
	
}