package Parte2;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

public class Practica03_b extends JFrame implements ActionListener {

	    ListaInsumos listainsumo;
	    ListaCategorias listacategorias;
        private JTextField Tid,Tcategoria;
        private JButton Bagregar,Beliminar,Bsalir;
        private JTextArea Tareacategoria;
        
        private JPanel panelFormulario;
    
        public void inicializarcategorias() 
        {
          this.listacategorias = new ListaCategorias();
          Categoria nodo1 = new Categoria("01","Materiales");
          Categoria nodo2 = new Categoria("02","Mano de Obra");
          Categoria nodo3 = new Categoria("03","Maquinaria y Equipo");
          Categoria nodo4 = new Categoria("04","Servicios");
          this.listacategorias.agregarCategoria(nodo1);
          this.listacategorias.agregarCategoria(nodo2);
          this.listacategorias.agregarCategoria(nodo3);
          this.listacategorias.agregarCategoria(nodo4);
          
          
        }
        
	public Practica03_b() {
		super("Administracion de Productos");
		//inicializamos las listas para el manejo de datos
		this.inicializarcategorias();
		this.listainsumo = new ListaInsumos();
		
		setBounds(0, 0, 390, 370);
		panelFormulario = new JPanel();
		panelFormulario.setLayout(null);
        getContentPane().add(panelFormulario, BorderLayout.CENTER);
        
        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setBounds(10,34,71,20);
        panelFormulario.add(labelCategoria);
        
        JLabel labelId= new JLabel("ID:");
        labelId.setBounds(10,9,71,20);
        this.Tid = new JTextField(10);
        this.Tid.setEditable(false);
        this.Tid.setBounds(91,9,147,20);
        panelFormulario.add(labelId);
        panelFormulario.add(Tid);
        this.Tcategoria = new JTextField(20);
        this.Tcategoria.setEditable(false);
        this.Tcategoria.setBounds(91,35,147,20);
        panelFormulario.add(Tcategoria);
        
        this.Bagregar = new JButton("Agregar");
        this.Bagregar.setBounds(20,104,111,20);
        this.Bagregar.addActionListener(this);
        panelFormulario.setLayout(null);
        panelFormulario.add(Bagregar);
        
        this.Beliminar = new JButton("Eliminar");
        this.Beliminar.setBounds(153,104,111,20);
        this.Beliminar.addActionListener(this);
        panelFormulario.add(Beliminar);
        panelFormulario.setLayout(null);
        
        this.Bsalir = new JButton("Salir");
        this.Bsalir.setBounds(274,104,79,20);
        this.Bsalir.addActionListener(this);
        panelFormulario.add(Bsalir);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10,132,357,179);
        panelFormulario.add(scrollPane);
        this.Tareacategoria = new JTextArea(10,40);
        scrollPane.setViewportView(Tareacategoria);
        this.Tareacategoria.setEditable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
	}
	
	public void Volveralinicio() 
    {
    	this.Bagregar.setText("Agregar");
    	this.Bsalir.setText("Salir");
    	this.Beliminar.setEnabled(true);
    	this.Tid.setEditable(false);
    	this.Tcategoria.setEditable(false);
    	this.Tid.setText("");
    	this.Tcategoria.setText("");
    }
	
	public void Altas() 
	{ 
	  if(this.Bagregar.getText().compareTo("Agregar")==0) 
	  {
		    this.Bagregar.setText("Salvar");
	    	this.Bsalir.setText("Cancelar");
	    	this.Beliminar.setEnabled(false);
	    	this.Tid.setEditable(true);
	    	this.Tcategoria.setEditable(true);
	  }
	  else 
	  { System.out.println("aqui");
	   if (esdatoscompletos()) 
		   {  System.out.println("aqui");
		      String id,insumo,idcategoria;
		      id=this.Tid.getText().trim();
		      idcategoria=this.Tcategoria.getText().trim();
		      Insumo nodo= new Insumo(id," ",idcategoria);
		      if(!this.listainsumo.agregarInsumo(nodo))
		    	  JOptionPane.showMessageDialog(this, "lo siento el id "+id+ "ya existe lo tiene asignado "+this.listainsumo.buscarInsumo(id));
		      else 
		      {this.Tareacategoria.setText(this.listainsumo.toString());
		    	  
		      }
			   
		   }
			  this.Volveralinicio();
		  }
	}
	
	private boolean esdatoscompletos() {
		// TODO Auto-generated method stub
		return false;
	}

	public void Eliminar() {
		Object[] opciones = this.listainsumo.idinsumos();
		String id = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Eliminacion de insumos",
				JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		if ((id != null) || (!id.isEmpty())) {
			if (!this.listainsumo.eliminarInsumoPorId(id))
				JOptionPane.showMessageDialog(this, "No existe este id");
			else
				this.Tareacategoria.setText(this.listainsumo.toString());
		}

	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource()== this.Bagregar)
    	 this.Altas();
     else if(e.getSource()== this.Beliminar)
    	 this.Eliminar();
     else if(e.getSource()==Bsalir)
     {
    	 if(this.Bsalir.getText().compareTo("Cancelar")==0)
    		 this.Volveralinicio();
    	 else
    		 this.dispose();
     }
    }
    
    public static void main(String[] args) {
	new Practica03_b();
  }

}
