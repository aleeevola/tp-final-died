package frsf.isi.died.app.vista.material;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Tema;

public class LibroPanel extends JPanel{
	
	private JScrollPane scrollPane;
	private JTable tabla;
	private JLabel lblTitulo;
	private JLabel lblCosto;
	private JLabel lblPrecioCompra;
	private JLabel lblPaginas;
	private JLabel lblTema;
	private JTextField txtTitulo;
	private JTextField txtCosto;
	private JTextField txtPrecioCompra;
	private JTextField txtPaginas;
	private JComboBox<Tema> comboTema;
	private JButton btnAgregar;
	private JButton btnCancelar;

	private LibroTableModel tableModel;

	private LibroController controller;
	
	public LibroPanel() {
		this.setLayout(new GridBagLayout());
		tableModel = new LibroTableModel();
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
		gridConst.gridwidth=7;
		this.add(txtTitulo, gridConst);
		

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener( e ->{
			try {
				Double costo = Double.valueOf(txtCosto.getText());
				Double precio = Double.valueOf(txtPrecioCompra.getText());
				Integer paginas = Integer.valueOf(txtPaginas.getText());
				Tema tema = (Tema) comboTema.getSelectedItem();
				controller.agregarLibro(txtTitulo.getText(), costo, precio, paginas,tema);
				txtTitulo.setText("");
				txtCosto.setText("");
				txtPrecioCompra.setText("");
				txtPaginas.setText("");
				comboTema.setSelectedItem(null);
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridwidth=1;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.HORIZONTAL;
		gridConst.anchor = GridBagConstraints.LINE_START;
		gridConst.gridx=8;
		this.add(btnAgregar, gridConst);
		gridConst.fill=GridBagConstraints.NONE;
		
		lblCosto= new JLabel("Costo: ");		
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.weightx=0.0;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(5);
		gridConst.gridx=1;
		this.add(txtCosto, gridConst);
		
		lblPrecioCompra= new JLabel("Precio Compra: ");
		gridConst.gridx=2;
		this.add(lblPrecioCompra, gridConst);
		
		txtPrecioCompra = new JTextField();
		txtPrecioCompra.setColumns(5);
		gridConst.gridx=3;
		this.add(txtPrecioCompra, gridConst);
		
		lblPaginas= new JLabel("Paginas: ");		
		gridConst.gridx=4;
		this.add(lblPaginas, gridConst);
		
		txtPaginas = new JTextField();
		txtPaginas.setColumns(5);
		gridConst.gridx=5;
		this.add(txtPaginas, gridConst);
		
		lblTema = new JLabel("Tema: ");
		gridConst.gridx=6;
		this.add(lblTema, gridConst);
		
		comboTema = new JComboBox<Tema>(Tema.values());
		comboTema.setSelectedItem(null);		
		gridConst.gridx = 7;
		this.add(comboTema, gridConst);

		btnCancelar= new JButton("Cancelar");
		gridConst.gridx=8;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.HORIZONTAL;
		gridConst.anchor = GridBagConstraints.LINE_START;
		this.add(btnCancelar, gridConst);
		gridConst.fill=GridBagConstraints.NONE;
		
		tabla = new JTable(this.tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=8;	
		gridConst.gridy=2;
		gridConst.weighty=1.0;
		gridConst.weightx=1.0;
		gridConst.fill=GridBagConstraints.BOTH;
		gridConst.anchor=GridBagConstraints.PAGE_START;		
		this.add(scrollPane, gridConst);
	}

	public LibroController getController() {
		return controller;
	}

	public void setController(LibroController controller) {
		this.controller = controller;
	}
	
	public void setListaLibros(List<Libro> librosLista,boolean actualizar) {
		this.tableModel.setLibros(librosLista);
		if(actualizar) this.tableModel.fireTableDataChanged();
	}

}