package Math;

import javax.swing.*;

public class Ventana extends JFrame {

    Ventana(){
        setTitle("Calculadora f(x)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setResizable(true);
        setVisible(true);

        Componentes componentesGui = new Componentes();
        add(componentesGui);
    }
}
