package ar.edu.davinci.dvds20201cg1.controlador.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteInsertRequest {
		
	private String name;
	
	private String lastName;
	
	private String email;
	
	private String password;
}
