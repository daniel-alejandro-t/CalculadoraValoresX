package graficador.expresion_algebraica;

import java.math.BigDecimal;

// Clase para representar a un elemento de una expresi�n algebraica(operador u operandos)
public class Elemento {
	
	// campos de clase
	private Tipo tipo;
	private String elemento;
	
	// constructor
	public Elemento(String elemento) throws IllegalArgumentException {
		setElemento(elemento);
	}
	
	public Elemento() {
		this("x");
	}
	
	// m�todos getter
	public Tipo getTipo() {return tipo;}
	public String getElemento() {return elemento;}
	
	// m�todo para establecer el elemento
	public void setElemento(String elemento) throws IllegalArgumentException {
		this.elemento = elemento;
		tipo = Elemento.getTipo(elemento);
	}
	
	// m�todo para reconocer el tipo de un elemento
	public static Tipo getTipo(String elemento) throws IllegalArgumentException {
		try {
			new BigDecimal(elemento);// trata de convertir el string a un valor num�rico
			return Tipo.CONSTANTE;
		}catch(NumberFormatException e) {
			if(elemento.length() == 1) {
				char c = elemento.charAt(0);
				switch(c) {
				case '+':
				case '-':
				case '*':
				case '/':
				case '^':
				case '(':
				case ')':
					return Tipo.OPERADOR;
				case 'x':
				case 'X':
					return Tipo.VARIABLE;
				default:
					throw new IllegalArgumentException("Elemento inv�lido en la expresi�n");
				}
			}else throw new IllegalArgumentException("Elemento inv�lido en la expresi�n");
		}// termina bloque try-catch
	}

}
