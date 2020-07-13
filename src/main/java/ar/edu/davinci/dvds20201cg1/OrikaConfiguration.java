package ar.edu.davinci.dvds20201cg1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.davinci.dvds20201cg1.controlador.rest.response.ClienteResponse;
import ar.edu.davinci.dvds20201cg1.modelo.Cliente;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class OrikaConfiguration {

	private final ObjectMapper objectMapper;
	
	public OrikaConfiguration() {
		objectMapper = new ObjectMapper();
	}
	
	@Bean
	public MapperFacade mapper() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		mapperFactory.classMap(Cliente.class, ClienteResponse.class).byDefault().register();
		
		return mapperFactory.getMapperFacade();
	}
}

