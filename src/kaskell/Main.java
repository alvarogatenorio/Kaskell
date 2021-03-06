package kaskell;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java_cup.runtime.Symbol;

public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream("input.txt"));
		/* Discover what this does more or less */
		Lexer lexer = new Lexer(input);

		@SuppressWarnings("deprecation")
		parser p = new parser(lexer);

		Symbol root = p.parse();
		Program program = (Program) root.value;
		if (program.checkIdentifiers()) {
			if (program.checkType()) {
				program.generateCode();
				System.out.println("I am the yeast of thoughts and mind!");
			}
		}

		/*
		 * Por hacer calculo de diversas constantes para procedimientos, jugar con la
		 * tabla de símbolos
		 * 
		 * actualmente si estamos en un procedimiento, todo lo hacemos igual que en un
		 * bloque normal, motivo por el cual se generarán errores al compilar cosas que
		 * contengan parametros por referencia
		 * 
		 * las calls pueden ser expresiones solitarias y conviene que su codigo se
		 * genere
		 */
	}
}
