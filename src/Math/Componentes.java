package Math;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Componentes extends JPanel {

    Componentes(){
        String valoresFuncion = "";

        JLabel      etiquetaTexto = new JLabel("Inserte la funcion lineal:");
        JTextField  entradaFuncion = new JTextField(10);
        JButton     botonCalcular =  new JButton("Calcular valores");
        JTextArea   campoTextoSalida = new JTextArea(valoresFuncion);

        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String salida = "";

                // array donde guardaremos el resultado
                double [][] tablaValoresTemp = new double [9][9];

                // Llamamos al metodo y guardamos el resultado
                tablaValoresTemp = Calculator.evaluateAndPrintResult(entradaFuncion.getText(),-4, 4);


                for (int i = 0; i<tablaValoresTemp.length; i++){
                    salida += ("x: " + tablaValoresTemp[i][0] + "\tf(x): " + tablaValoresTemp[i][1] + "\n");
                }

                campoTextoSalida.append(salida);   // TODO: Le pasaremos una var. String que contendra la salida

            }
        });

        add(etiquetaTexto);
        add(entradaFuncion);
        add(botonCalcular);
        add(campoTextoSalida);
    }
}
