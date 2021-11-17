package br.com.rosana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.rosana.exception.UnsuportedMathOperationException;
import br.com.rosana.services.MathServices;

@RestController
public class MathController {
	
	@Autowired 
	private MathServices services;

	//	private static final String template = "Hello, %s!";
	//	private final AtomicLong counter = new AtomicLong();
		
		@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
		//POR PADRÃO, SE NÃO COLOCAR O REQUEST METHOD, SERÁ GET
		public Double sum(@PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception {
			if (!services.isNumeric(numberOne) || !services.isNumeric(numberTwo)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double sum = services.soma(services.convertToDouble(numberOne),services.convertToDouble(numberTwo));
			return sum;
		
		}
		
		@RequestMapping(value = "/subtracao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
		//POR PADRÃO, SE NÃO COLOCAR O REQUEST METHOD, SERÁ GET
		public Double subtracao(@PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception {
			if (!services.isNumeric(numberOne) || !services.isNumeric(numberTwo)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double subtracao = services.subtracao(services.convertToDouble(numberOne),services.convertToDouble(numberTwo));
			return subtracao;
		}
		
		@RequestMapping(value = "/multiplicacao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
		public Double multiplicacao(@PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception {
			if (!services.isNumeric(numberOne) || !services.isNumeric(numberTwo)){
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double produto = services.multiplicacao(services.convertToDouble(numberOne),services.convertToDouble(numberTwo));
			return produto;
		}
		
		@RequestMapping(value = "/divisao/{numberOne}/{numberTwo}", method = RequestMethod.GET)
		public Double divisao(@PathVariable("numberOne")String numberOne, @PathVariable("numberTwo")String numberTwo) throws Exception {
			if (!services.isNumeric(numberOne) || !services.isNumeric(numberTwo)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double div = services.divisao(services.convertToDouble(numberOne),services.convertToDouble(numberTwo));
			return div;
		}
		
		//o @RequestMapping vai mapear o caminho que corresponder a esse value e vai fazer o 
		//@PathVariable vai fazer o binding do number digitado no value com a String number que é o parâmetro de entrada
		//do método.
		@RequestMapping(value = "/raizquadrada/{number}", method = RequestMethod.GET)
		public Double raiz(@PathVariable("number")String number) throws Exception {
			if (!services.isNumeric(number)) {
				throw new UnsuportedMathOperationException("Digite um valor numérico");
			}
			Double raiz = services.raizQuadrada(services.convertToDouble(number));
			return raiz;
		}


}
