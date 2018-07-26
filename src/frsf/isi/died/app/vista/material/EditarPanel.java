package frsf.isi.died.app.vista.material;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.app.controller.EditarController;
import frsf.isi.died.app.controller.LibroController;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Tema;
import frsf.isi.died.tp.modelo.productos.Video;

public class EditarPanel extends JDialog{
	private JButton btnAgregar;
	private JButton btnCancelar;
	
	
	private JLabel lblTitulo; private JTextField txtTitulo;
	private JLabel lblCosto; private JTextField txtCosto;
	private JLabel lblPrecioCompra; private JTextField txtPrecioCompra;//libro
	private JLabel lblPaginas; private JTextField txtPaginas;//libro
	private JLabel lblDuracion; private JTextField txtDuracion;// video
	
	private JLabel lblCalificacion; private JTextField txtCalificacion;
	private JLabel lblRelevancia; private JComboBox cbRelevancia;
	private JLabel lblTema; private JComboBox cbTema;
	


	private EditarController controller;
	
	public EditarPanel(Frame parent, boolean modal) {
		super(parent, modal);
		this.setLayout(new GridBagLayout());
		this.setSize(600, 400);
	}
	
	public void construir(MaterialCapacitacion mat) {
		GridBagConstraints gridConst= new GridBagConstraints();
		
		lblTitulo = new JLabel("Titulo: ");
		gridConst.gridx=0;
		gridConst.gridy=0;
		gridConst.gridwidth=1;
		gridConst.weighty = 1.0; //----------------->>>>
		gridConst.weightx = 1.0;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblTitulo, gridConst);
		
		txtTitulo = new JTextField();
		txtTitulo.setColumns(30);
		
		gridConst.gridx=1;
		gridConst.gridwidth=4;
		gridConst.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtTitulo, gridConst);
		
		lblCosto = new JLabel("Costo: ");
		gridConst.gridx=0;
		gridConst.gridy=1;
		gridConst.gridwidth=1;
		gridConst.fill = GridBagConstraints.NONE;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblCosto, gridConst);
		
		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridwidth=1;
		gridConst.fill = GridBagConstraints.HORIZONTAL;
		this.add(txtCosto, gridConst);
		
		if(mat.esLibro()) {
			lblPrecioCompra = new JLabel("Precio Compra: ");
			gridConst.gridx=0;
			gridConst.gridy=3;
			gridConst.gridwidth=1;
			gridConst.fill = GridBagConstraints.NONE;
			gridConst.anchor = GridBagConstraints.CENTER;
			this.add(lblPrecioCompra, gridConst);
			
			txtPrecioCompra = new JTextField();
			txtPrecioCompra.setColumns(10);
			txtPrecioCompra.setText(((Libro) mat).getPrecioCompra().toString());
			gridConst.gridx=1;
			gridConst.gridwidth=1;
			gridConst.fill = GridBagConstraints.HORIZONTAL;
			this.add(txtPrecioCompra, gridConst);
		
			lblPaginas = new JLabel("Paginas: ");
			gridConst.gridx=3;
			gridConst.gridy=3;
			gridConst.gridwidth=1;
			gridConst.fill = GridBagConstraints.NONE;
			gridConst.anchor = GridBagConstraints.CENTER;
			this.add(lblPaginas, gridConst);
			
			txtPaginas = new JTextField();
			txtPaginas.setColumns(10);
			txtPaginas.setText(((Libro) mat).getPaginas().toString());
			gridConst.gridx=4;
			gridConst.gridwidth=1;
			gridConst.fill = GridBagConstraints.HORIZONTAL;
			this.add(txtPaginas, gridConst);
		}
		
		else {
			lblDuracion = new JLabel("Duracion: ");
			gridConst.gridx=0;
			gridConst.gridy=3;
			gridConst.gridwidth=1;
			gridConst.fill = GridBagConstraints.NONE;
			gridConst.anchor = GridBagConstraints.CENTER;
			this.add(lblDuracion, gridConst);
			
			txtDuracion = new JTextField();
			txtDuracion.setColumns(10);
			txtDuracion.setText(((Video) mat).getDuracionEnSegundos().toString());
			gridConst.gridx=1;
			gridConst.gridwidth=1;
			gridConst.fill = GridBagConstraints.HORIZONTAL;
			this.add(txtDuracion, gridConst);
		}
		
		lblCalificacion = new JLabel("Calificacion: ");
		gridConst.gridx=0;
		gridConst.gridy=4;
		gridConst.gridwidth=1;
		gridConst.fill = GridBagConstraints.NONE;
		gridConst.anchor = GridBagConstraints.CENTER;
		this.add(lblCalificacion, gridConst);
		
		txtCalificacion = new JTextField();
		txtCalificacion.setColumns(10);
		gridConst.gridx=1;
		gridConst.gridwidth=1;
		this.add(txtCalificacion, gridConst);
		
		lblRelevancia = new JLabel("Relevancia: ");
		gridConst.gridx=0;
		gridConst.gridy=5;
		gridConst.gridwidth=1;
		this.add(lblRelevancia, gridConst);
		
		cbRelevancia = new JComboBox(Relevancia.values());
		gridConst.gridx=1;
		gridConst.gridwidth=1;
		this.add(cbRelevancia, gridConst);
		
		lblTema = new JLabel("Tema: ");
		gridConst.gridx=0;
		gridConst.gridy=6;
		gridConst.gridwidth=1;
		this.add(lblTema, gridConst);
		
		cbTema = new JComboBox(Tema.values());
		gridConst.gridx=1;
		gridConst.gridwidth=1;
		this.add(cbTema, gridConst);
		
		
		
		//Seteo variables iniciales
		txtTitulo.setText(mat.getTitulo());
		txtCosto.setText(mat.getCosto().toString());
		txtCalificacion.setText(mat.getCalificacion().toString());
		cbRelevancia.setSelectedItem(mat.getRelevancia());
		cbTema.setSelectedItem(mat.getTema());
	
		
		
		
		btnAgregar = new JButton("Editar");
		btnAgregar.addActionListener( e ->{
			try {
				String titulo = txtTitulo.getText();
				Double costo = Double.valueOf(txtCosto.getText());
				
				Integer calificacion = Integer.valueOf(txtCalificacion.getText());
				Relevancia relv = (Relevancia) cbRelevancia.getSelectedItem();
				Tema tem = (Tema) cbTema.getSelectedItem();

				
				Double precio;
				Integer paginas;
				Integer duracion;
				
				
				if(mat.esLibro()) {
					precio = Double.valueOf(txtPrecioCompra.getText());
					paginas = Integer.valueOf(txtPaginas.getText());
					duracion = 0;
				}
				else {
					duracion = Integer.valueOf(txtDuracion.getText());
					precio=(double) 0;
					paginas=0;
				}
				controller.editarMaterial(mat, titulo, costo, calificacion, relv, tem, precio, paginas, duracion);
				
			}catch(Exception ex) {
			    JOptionPane.showMessageDialog(this, ex.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
			}
		});
		gridConst.gridwidth=2;
		gridConst.gridx=0;
		gridConst.gridy=7;
		gridConst.fill = GridBagConstraints.HORIZONTAL;
		this.add(btnAgregar, gridConst);

		btnCancelar= new JButton("Cancelar");
		btnCancelar.addActionListener( e ->{
			this.setVisible(false);
		});
		gridConst.gridwidth=2;
		gridConst.gridx=3;
		this.add(btnCancelar, gridConst);
		
	}

	public EditarController getController() {
		return controller;
	}


	public void setController(EditarController controller2) {
		// TODO Auto-generated method stub
		this.controller = controller2;
	}


}