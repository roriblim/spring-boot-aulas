package br.com.rosana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//enableautoconfiguration permite que o application context do spring seja automaticamente carregado baseado nos jars
//e nas configuracoes que a gente define. Sempre é feita depois que os beans já foram registrados no application context
//basicamente, ele vai facilitar e automatizar o trabalho de configuração, assumindo mais responsabilidade por ela

//ComponentScan diz ao spring boot que ele deve scanear os pacotes para encontrar arquivos de configuração,
//como o webconfig (arquivos que tenham  a annotation @Configuration)

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class Startup {
	public static void main(String[] args) {
		SpringApplication.run(Startup.class,args);
		
		//quando estivermos gravando um novo usuário, vamos ter de criar um endpoint que fazer isso,
		//pega a senha e encripta ela:
		//16 é o que o spring security usa como padrão
		/*BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		String result = bCryptPasswordEncoder.encode("admin123");
		System.out.println("My hash " + result);*/

	}
}
