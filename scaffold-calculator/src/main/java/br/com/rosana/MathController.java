package br.com.rosana;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rosana.exception.UnsuportedMathOperationException;

@RestController
public class MathController {

	//	private static final String template = "Hello, %s!";
	//	private final AtomicLong counter = new AtomicLong();
		
		@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
		//POR PADRÃO, SE NÃO COLOCAR O REQUEST METHOD, SERÁ GET
		public Double sum(@PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception {
			if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double sum = convertToDouble(numberOne)+convertToDouble(numberTwo);
			return sum;
		
		}
		
		@RequestMapping(value = "/subtracao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
		//POR PADRÃO, SE NÃO COLOCAR O REQUEST METHOD, SERÁ GET
		public Double subtracao(@PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception {
			if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double subtracao = convertToDouble(numberOne)-convertToDouble(numberTwo);
			return subtracao;
		}
		
		@RequestMapping(value = "/multiplicacao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
		public Double multiplicacao(@PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception {
			if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double multiplicacao = convertToDouble(numberOne)*convertToDouble(numberTwo);
			return multiplicacao;
		}
		
		@RequestMapping(value = "/divisao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
		public Double divisao(@PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception {
			if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double divisao = convertToDouble(numberOne)/convertToDouble(numberTwo);
			return divisao;
		}
		
		//o @RequestMapping vai mapear o caminho que corresponder a esse value e vai fazer o 
		//@PathVariable vai fazer o binding do number digitado no value com a String number que é o parâmetro de entrada
		//do método.
		@RequestMapping(value = "/raizquadrada/{number}", method = RequestMethod.GET)
		public Double raiz(@PathVariable("number")String number) throws Exception {
			if (!isNumeric(number)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double raiz = (Double) Math.sqrt(convertToDouble(number));
			return raiz;
		}

		private Double convertToDouble(String strNumber) {
			if (strNumber==null) return 0D; //0D é 0 double
			String number = strNumber.replaceAll(",",".");
			if (isNumeric(number)) return Double.parseDouble(number);
			return 0D;
		}

		private boolean isNumeric(String strNumber) {
			if (strNumber==null) return false;
			String number = strNumber.replaceAll(",",".");
			return number.matches("[-+]?[0-9]*\\.?[0-9]+"); //usa uma regex (regular expression) para saber se é um número
		}
}
