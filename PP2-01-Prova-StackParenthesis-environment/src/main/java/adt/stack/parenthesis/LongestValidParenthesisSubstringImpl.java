package adt.stack.parenthesis;

import java.util.Stack;

/**
 * @author Cláudio Campelo Ver comentários na interface
 *         LongestValidParenthesisSubstring.
 *
 */
public class LongestValidParenthesisSubstringImpl implements LongestValidParenthesisSubstring {
	private Stack<String> pilha = new Stack<>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * adt.stack.parenthesis.LongestValidParenthesisSubstring#findLongest(java.lang.
	 * String)
	 */
	public String findLongest(String parenthesis) {
		String saida = "";
		if (searchForValidString(parenthesis)) {
			String[] arrayAux = parenthesis.split("");

			int contadorAux = 0;
			String auxString = "";
			for (String i : arrayAux) {
				if (i.equals("(")) {
					contadorAux++;
					pilha.push(i);
				} else if (i.equals(")") && contadorAux == 0) {
					auxString = "";
				} else if (i.equals(")") && contadorAux != 0) {
					contadorAux--;
					pilha.push(i);
					if (contadorAux == 0) {
						while (!pilha.isEmpty()) {
							auxString = pilha.pop() + auxString;
						}
						if (auxString.length() > saida.length()) {
							saida = auxString;
						}
					}
				}
			}
			saida = parenthesisMirror(saida);
		} else {
			saida = null;
		}
		return saida;
	}

	/*
	 * Este é um método utilitário que calcula o "espelho" de uma String. Ou seja, é
	 * uma espécie de flip horizontal. Veja os exemplos abaixo para entender como o
	 * método deve ser comportar.
	 * 
	 * A implementação deste método não é obrigatória, porém, é fortemente
	 * recomendada, visto que pode ser muito útil para a implementação do
	 * findLongest.
	 * 
	 * Neste método, implemente uma solução baseada em PILHA.
	 * 
	 * Exemplo 1: input = ((() expected_output = ()))
	 * 
	 * Exemplo 2: input = ()() expected_output = ()()
	 * 
	 * Exemplo 2: input = ((((( expected_output = )))))
	 */
	private String parenthesisMirror(String str) {
		Stack<String> stack = new Stack<String>();
		String mirrorStr = "";
		String[] arrayAux = str.split("");

		// Coloca dentro da pilha
		for (String i : arrayAux) {
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			if (stack.peek().equals("(")) {
				mirrorStr = mirrorStr + ")";
			} else if (stack.peek().equals(")")) {
				mirrorStr = mirrorStr + "(";
			}
			stack.pop();
		}
		return mirrorStr;

	}

	public boolean searchForValidString(String str) {
		boolean retorno = true;
		String[] newStr = str.split("");
		for (String i : newStr) {
			if (!i.equals("(") && !i.equals(")")) {
				retorno = false;
			}
		}
		return retorno;
	}
}
