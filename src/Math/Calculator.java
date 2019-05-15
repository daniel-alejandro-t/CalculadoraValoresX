package Math;

import java.util.*;

public class Calculator {

    // TODO: Se podria hacer una funcion que trate los String para adecuarlos a lo que quiere la fun.
    // TODO: , que es mas facil que editar el algoritmo

    public static double[][] evaluateAndPrintResult(String inString, int valorMinimo, int valorMaximo) {
        String inStringOriginal = inString;     // Copia de la funcion que sera utilizada para cambiar los valores

        // Obtenemos la distancia entre los valores de x para crear el array con ese tamano
        int numPosiciones = getDistancia2Num(valorMinimo,valorMaximo);

        // Crear el array [][] con un tamano que sera la distancia entre 2 #s
        double [][] valoresFuncion = new double [numPosiciones][2];

        // TODO: Trata el string - Al terminar hacer una fun. para este asunto ( 5 'x' + 13 ) => ( 5 '* 4' + 13 )
        inString = inString.toLowerCase();      // Si el user ingreso la funcion en mayuscula, la pasa a minuscula...

        int valorContenido = valorMinimo;

        // Rellena la primera fila del array con los valores desde el minimo hasta el maximo
        for (int contadorarray = 0; contadorarray<numPosiciones; contadorarray++){
            valoresFuncion[contadorarray][0] = valorContenido;
            valorContenido++;
        }

        for (int i = 0; i<numPosiciones; i++){              // Recorre muchas veces
            inString = inStringOriginal;                    // Restablece la funcion a su estado inicial para poder ingresar el siguiente valor de x

            // Los siguientes condicionales convierten la entrada de ""

            if (inString.contains("+ x")){                  // Contempla la suma
                inString = inString.replaceAll("x", "+ " + Double.toString(valoresFuncion[i][0]));
            }else if (inString.contains("- x")){            // Contempla la resta
                inString = inString.replaceAll("x", "- " + Double.toString(valoresFuncion[i][0]));
            }else if (inString.contains("/ x")){            // Contempla la division
                inString = inString.replaceAll("/ x", "* " + Double.toString(valoresFuncion[i][0]));
            }else{                                          // Si no especifica el signo significa que es una multiplicacion ( 5 'x' + 13 ) => ( 5 '* 4' + 13 )
                inString = inString.replaceAll("x", "* " + Double.toString(valoresFuncion[i][0]));   // Remplaza x por el valor de x contenido el posicion [i][0] del array
            }

            String[] str = inString.split("\\s+");
            Queue<String> argsBox = new LinkedList<>();
            argsBox.addAll(Arrays.asList(str));
            Stack<String> ops = new Stack<>();
            Stack<Double> vals = new Stack<>();
            while (!argsBox.isEmpty()) { // Read token, push if operator.
                String token = argsBox.poll();
                switch (token) {
                    case "(":
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                    case "sin" :
                        ops.push(token);
                        break;
                    case "sqrt":
                        ops.push(token);
                        break;
                    case ")":
                        vals.push(evaluateOp(ops, vals));
                        break;
                    default:
                        // Token not operator or paren: push double value.
                        try {
                            vals.push(Double.parseDouble(token));
                            break;
                        }
                        catch (WrongInputFormat e){
                            System.out.println("wrong input");
                        }
                }
            }
            valoresFuncion[i][1] = evaluateOp(ops, vals); // devuelve f(x), y se guarda en la posicion correspondiente ejemplo: x = [-4] f(x) [resultado]
        }

        return valoresFuncion;  // Retorna la tabla de valores del array
    }

    // Obtiene la distancia entre 2 numeros, util para crear un array del tamano de la distancia entre elementos - Ejemplo: In: -4 4 Out: 9 elementos

    public static int getDistancia2Num(int valorMinimo, int valorMaximo){
        int contador = 0;

        for (int i = valorMinimo; i <= valorMaximo; i++){
            contador++;
        }
        return contador;
    }

    public static void evaluateAndPrintResult(String inString) {

        String[] str = inString.split("\\s+");
        Queue<String> argsBox = new LinkedList<>();
        argsBox.addAll(Arrays.asList(str));
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        while (!argsBox.isEmpty()) { // Read token, push if operator.
            String token = argsBox.poll();
            switch (token) {
                case "(":
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                case "sin" :
                    ops.push(token);
                    break;
                case "sqrt":
                    ops.push(token);
                    break;
                case ")":
                    vals.push(evaluateOp(ops, vals));
                    break;
                default:
                    // Token not operator or paren: push double value.
                    try {
                    vals.push(Double.parseDouble(token));
                    break;
                    }
                    catch (WrongInputFormat e){
                        System.out.println("wrong input");
                    }
            }
        }
        System.out.println(inString + " = " + evaluateOp(ops, vals));
    }

    private static Double evaluateOp(Stack<String> ops, Stack<Double> vals) {
        // Replace the top exp with its result.
        double valorDerecho = vals.pop();       // TODO Probar aver si puede calcular ( 2 + 3 - 5 )
        if (!ops.empty()) {
            String op = ops.pop();
            switch (op) {
                case "+":
                    valorDerecho = vals.pop() + valorDerecho;
                    break;
                case "-":
                    valorDerecho = vals.pop() - valorDerecho;
                    break;
                case "*":
                    valorDerecho = vals.pop() * valorDerecho;
                    break;
                case "/":
                    valorDerecho = vals.pop() / valorDerecho;
                    break;
                case "sqrt":
                    valorDerecho = Math.sqrt(valorDerecho);
                    break;
                case "sin" :
                    valorDerecho = Math.sin(valorDerecho);
                default:
                    break;
            }
        }
        return valorDerecho;
    }
    private static Queue<String> parseString (String inputString){
        String[] str = inputString.split("\\s+");
        Queue<String> argsBox = new LinkedList<>();
        argsBox.addAll(Arrays.asList(str));
        return argsBox;
    }


}