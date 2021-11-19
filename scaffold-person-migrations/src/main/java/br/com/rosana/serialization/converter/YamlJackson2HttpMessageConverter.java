package br.com.rosana.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter{

	public YamlJackson2HttpMessageConverter() {
		super(new YAMLMapper()
				.setSerializationInclusion(JsonInclude.Include.NON_NULL), MediaType.parseMediaType("application/x-yaml"));
				//esse parametro no setSerializationInclusion é para garantir que, no formato YAML, não aparecerão 
		//atributos com valor null (isso corrige o fato de que, por causa da configuração do hateoas no personcontroller, estava aparecendo o link
		//mas também estavam aparecendo outros dados, null, que eu nao queria)
	}

}
