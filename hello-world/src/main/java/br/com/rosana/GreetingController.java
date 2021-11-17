package br.com.rosana;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

		private static final String template = "Hello, %s!";
		private final AtomicLong counter = new AtomicLong();
		
		@RequestMapping("/greeting")
		public Greeting greeting(@RequestParam(value="name",defaultValue="World")String name) {
			return new Greeting(counter.incrementAndGet(), String.format(template, name));
			//note que o que eu passar como GET pelo navegador vai se tornar entrada desse método
			//exemplo: http://localhost:8080/greeting?name=rosana
			//note ainda que o counter vai se incrementando. se eu der F5 na página várias vezes, o valor
			//do counter vai ter subido, e o id de greeting vai ser maior.
			//se eu der http://localhost:8080/greeting no navegador, vai aparecer id algo e o content hello, World
			//assim, criamos nosso primeiro endpoint com "hello, world"
		}
}
