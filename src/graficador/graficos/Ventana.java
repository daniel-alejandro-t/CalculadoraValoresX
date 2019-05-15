package graficador.graficos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Math.Calculator;

import graficador.expresion_algebraica.Elemento;

// Creamos una clase ventana
public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;		// TODO: Esto yo no lo veo necesario...
	private JPanel ventanaUnica;
	private JTextField campoInsertaFuncion;
	private Grafica grafica;
	
	// Constructor
	public Ventana() {
		super("Graficador");									// TODO: Todo esto se va a una unica clase
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// Si cierra la ventana finaliza el programa
		Dimension size = new Dimension(800,550);		// Tamano inicial de la ventana
		setSize(size);												// Establece el tamano
		setMinimumSize(size);										// Le da un tamano minimo
		setLocationRelativeTo(null);								// Al pasarle como @param null establece la ventana en el centro

		ventanaUnica = new JPanel();									// TODO: Se esta instanciado el obj. Frame en la misma clase de la ventana - MOVER
		ventanaUnica.setBorder(new EmptyBorder(5, 5, 5, 5));

		ventanaUnica.setBackground(Color.WHITE);

		setContentPane(ventanaUnica);
		ventanaUnica.setLayout(new BorderLayout(0, 0));
		
		grafica = new Grafica();
		ventanaUnica.add(grafica, BorderLayout.CENTER);
		
		JPanel panelParCartesiano = new JPanel();									// Ventana contenedora del
		ventanaUnica.add(panelParCartesiano, BorderLayout.WEST);					// Agregamos el panel a la ventana
		GridBagLayout gbl_panel = new GridBagLayout();								//
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0};
		panelParCartesiano.setLayout(gbl_panel);
		
		JLabel lblFuncion = new JLabel("f(x)");
		lblFuncion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFuncion = new GridBagConstraints();				// A cada componente del GUI Se le da un layout
		gbc_lblFuncion.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFuncion.insets = new Insets(5, 5, 5, 5);
		gbc_lblFuncion.gridx = 0;													// Especifica la columna donde iniciara el componente
		gbc_lblFuncion.gridy = 0;													// Especifica la fila donde iniciara el componente
		panelParCartesiano.add(lblFuncion, gbc_lblFuncion);							// Agrega el componente a la lamina, pasandole como @param sus restricciones
		
		campoInsertaFuncion = new JTextField();										// Campo InsertaFuncion
		GridBagConstraints gbc_fieldFuncion = new GridBagConstraints();				// Declara las restricciones del componente
		gbc_fieldFuncion.fill = GridBagConstraints.BOTH;							//
		gbc_fieldFuncion.insets = new Insets(5, 5, 5, 5);
		gbc_fieldFuncion.gridx = 1;													// Columna desde la cual empezara el componente
		gbc_fieldFuncion.gridy = 0;													// Fila desde la cual empezara el componente
		panelParCartesiano.add(campoInsertaFuncion, gbc_fieldFuncion);				// Agrega el componente a la lamina, pasandole como @param sus restricciones
		campoInsertaFuncion.setColumns(12);
		
		JButton btnGraficar = new JButton("Graficar");
		GridBagConstraints gbc_btnGraficar = new GridBagConstraints();				// Restricciones del componente
		gbc_btnGraficar.gridwidth = 2;												// Ancho que ocupara el boton (extendiendose hasta ala casila x = 1 y = 1
		gbc_btnGraficar.fill = GridBagConstraints.HORIZONTAL;						// Indica que el componente tiene que ocupar espacio de forma horizontal
		gbc_btnGraficar.insets = new Insets(5, 5, 5, 5);	// Especifica el borde del componente
		gbc_btnGraficar.gridx = 0;													// Especifica la columna desde la cual empezara el componente
		gbc_btnGraficar.gridy = 1;													// Especifica la fila desde la cual empezara el componente
		panelParCartesiano.add(btnGraficar, gbc_btnGraficar);						// Agrega el componente a la lamina, pasandole como @param sus restricciones

		JTable tablaValores = new JTable(9, 2);				// Tamano de 9 filas por 6 columnas
		GridBagConstraints gbc_tablaValores = new GridBagConstraints();				// Restricciones del componente
		gbc_tablaValores.gridx = 0;													// Especifica la columna desde la cual empezara el componente
		gbc_tablaValores.gridy = 2;													// Especifica la fila desde la cual empezara el componente
		gbc_tablaValores.gridwidth = 2;												// Ancho que ocupara el boton (extendiendose hasta la casila x = 1 y = 2
		gbc_tablaValores.gridheight = 2;											// Alto que ocupara el boton (extendiendose hasta la casila x = 0 y = 3
		gbc_tablaValores.fill = GridBagConstraints.HORIZONTAL;						// Indica que el componente tiene que ocupar espacio de forma horizontal
		gbc_tablaValores.fill = GridBagConstraints.VERTICAL;						// Indica que el componente tiene que ocupar espacio de forma vertical
		gbc_tablaValores.insets = new Insets(5, 5, 5, 5);	// Especifica el borde del componente
		panelParCartesiano.add(tablaValores, gbc_tablaValores);						// Agrega el componente a la lamina, pasandole como @param sus restricciones
		// TODO: Y ahora como la relleno???
	//	campoInsertaFuncion.setForeground(Grafica.FONDO);							// TODO Presindible: Establece el color del primer plano (?)
	//	btnGraficar.setBackground(Color.WHITE);										// TODO: Para que le pone un fondo blanco al boton?
	//	btnGraficar.setFocusable(false);
		/*	TODO: Comentado, el programa funciona igual
		campoInsertaFuncion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent k) {
				try {
					char key = k.getKeyChar();
					if(key != ' ')
						Elemento.getTipo(""+key);
				}catch(IllegalArgumentException e) {
					k.consume();
				}
			}
		});
		*/

		// TODO: Se debe crear un nuevo evento para que se rellene la tabla al hacer click en el boton
		btnGraficar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				double [][] tablaValoresTemp = new double [2][4];

				// TODO 1: Pasarle al Calculator lo introducido por el user
				tablaValoresTemp = Calculator.evaluateAndPrintResult(campoInsertaFuncion.getText(),-4, 4);

				DefaultTableModel modelo = new DefaultTableModel();
				// TODO: Habra que ir rellenandolo posicion por posicion

				// TODO: Ajustamos el tamano del modelo
				modelo.addColumn("x");		// No hace nada

				// TODO: Hagamos una prueba con un valor
				//modelo.setValueAt("X", 0, 0);

				// TODO: Agregamos el modeloa JFrame
				tablaValores.setModel(modelo);		//  Ahora desaparecio		// TODO SI NO FUNCIONA PROBAMOS CON JTextArea

				// TODO 2: Recibir el array bidimensional y pasarselo a la tabla
				// TODO ERROR Fuera de rango, no se le dio tamano al array
				tablaValores.updateUI();

				// TODO 2: Actualizar la tabla para que se muestren los datos

				System.out.println("Ejecuto el evento");

				//	tablaValores.setModel(TableModel modeloTable = new );
			}
		});

		Action eventoGraficar = new Graficar();												// Se crea un evento del tipo Action del tipo graficar
		InputMap inputMap = ventanaUnica.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);	// Establece el mapa de entrada
		ActionMap actionMap = ventanaUnica.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "graficar");	// TODO: El puto boton de enter no funciona
		actionMap.put("graficar", eventoGraficar);
		btnGraficar.setAction(eventoGraficar);
		
	}
	
	private class Graficar extends AbstractAction{
		
		private static final long serialVersionUID = 1L;	// TODO Presindible

		// constructor
		public Graficar() {
			putValue(Action.NAME, "Graficar");	// Establece un nombre a la accion por defecto al crear un obj. Graficar
		}
		
		public void actionPerformed(ActionEvent e) {
			try {
				grafica.setFuncion(campoInsertaFuncion.getText());			// TODO: Llama a una fun. que todavia no se que hace, pasando entrada del user.

			}catch(IllegalArgumentException ex) {												// Si la entrada no es correcta...
				JOptionPane.showMessageDialog(Ventana.this, ex.getMessage());	// Muestra un mensaje de error
			//	campoInsertaFuncion.selectAll();												// TODO Probar si es presindible
			//	campoInsertaFuncion.requestFocus();												// TODO MEtodo no recomendado
			}
		}
		
	}

}

