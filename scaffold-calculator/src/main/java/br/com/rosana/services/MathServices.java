package br.com.rosana.services;

import org.springframework.stereotype.Service;

@Service
public class MathServices {

	
	public Double convertToDouble(String strNumber) {
		if (strNumber==null) return 0D; //0D é 0 double
		String number = strNumber.replaceAll(",",".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}

	public boolean isNumeric(String strNumber) {
		if (strNumber==null) return false;
		String number = strNumber.replaceAll(",",".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+"); //usa uma regex (regular expression) para saber se é um número
	}
	
	public Double soma(Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}
	
	public Double subtracao(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}
	
	public Double multiplicacao(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}
	
	public Double divisao(Double numberOne, Double numberTwo) {
		return numberOne/numberTwo;
	}
	
	public Double raizQuadrada(Double number) {
		return (Double) Math.sqrt(number);
		
	}
	
}
