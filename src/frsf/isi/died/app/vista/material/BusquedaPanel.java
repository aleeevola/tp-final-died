package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.BusquedaController;
import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Tema;

public class BusquedaPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblTitulo;
	private JLabel lblCalificacion;
	private JLabel lblTema;
	private JLabel lblFechaPublicacion;
	private JLabel lblOrdenamiento;
	private JTextField txtTitulo;
	private JTextField txtCalificacion;
	private JComboBox comboTema;
	private JTextField txtFechaPublicacion;
	private JComboBox txtOrdenamiento;
	private JButton btnBuscar;
	private JButton btnCancelar;

	private BusquedaTableModel tableModel;

	private BusquedaController controller;
	
	public BusquedaPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new BusquedaTableModel();
	}
	
	public void construir() {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(40);
		gridConst.gridx=1;
		gridConst.gridwidth=5;
		this.add(txtTitulo, gridConst);
		

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener( e ->{
			try {
				Double calificacion = Double.valueOf(txtCalificacion.getText());
				String tema = String.valueOf(comboTema.getSelectedItem());
				Integer fechaPublicacion = Integer.valueOf(txtFechaPublicacion.getText());
				controller.buscarMaterial(txtTitulo.getText(), calificacion, tema, fechaPublicacion);
				txtTitulo.setText("");
				txtCalificacion.setText("");
				comboTema.setSelectedIndex(0);
				txtFechaPublicacion.setText("");
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=6;
		this.add(btnBuscar, gridConst);
		
		
		lblCalificacion= new JLabel("Calificacion: ");		
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.weightx=0.0;
		this.add(lblCalificacion, gridConst);
		
		txtCalificacion = new JTextField();
		txtCalificacion.setColumns(5);
		gridConst.gridx=1;
		this.add(txtCalificacion, gridConst);
		
		lblTema= new JLabel("Tema: ");
		gridConst.gridx=2;
		this.add(lblTema, gridConst);
		
		comboTema = new JComboBox(Tema.values());
		//txtTema.setColumns(5);
		comboTema.setSelectedItem(null);
		gridConst.gridx=3;
		this.add(comboTema, gridConst);
		
		lblFechaPublicacion= new JLabel("Fecha desde: ");		
		gridConst.gridx=4;
		this.add(lblFechaPublicacion, gridConst);
		
		txtFechaPublicacion = new JTextField();
		txtFechaPublicacion.setColumns(7);
		gridConst.gridx=5;
		this.add(txtFechaPublicacion, gridConst);

		lblFechaPublicacion= new JLabel("hasta: ");		
		gridConst.gridx=6;
		this.add(lblFechaPublicacion, gridConst);
		
		txtFechaPublicacion = new JTextField();
		txtFechaPublicacion.setColumns(5);
		gridConst.gridx=7;
		this.add(txtFechaPublicacion, gridConst);
		
		btnCancelar= new JButton("Cancelar");
		gridConst.gridx=8;
		gridConst.weightx=1.0;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=7;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}

	public BusquedaController getController() {
		return controller;
	}

	public void setController(BusquedaController controller) {
		this.controller = controller;
	}
	
	public void setListaMateriales(List<MaterialCapacitacion> materialesLista,boolean actualizar) {
		this.tableModel.setMateriales(materialesLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

	
}