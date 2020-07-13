package ar.edu.davinci.dvds20201cg1.servicio;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.edu.davinci.dvds20201cg1.modelo.Cliente;
import ar.edu.davinci.dvds20201cg1.repositorio.ClienteRepositorio;

@Service
public class ClienteServicio {
	
	@Autowired
	ClienteRepositorio clienteRepositorio;
	
	public Page<Cliente> listar() {
		Pageable pageable = PageRequest.of(0, 2);
		return clienteRepositorio.findAll(pageable);
	}
	
	
	
	//PageRequest pageRequest = PageRequest.of(page, size, Sort.by(direction ,orderBy));
    
    //Page<Cliente> pageClientes = clienteService.findAll(pageRequest);
    
    //PageableResponse<Cliente> response = new PageableResponse<>(pageClientes);

	public List<Cliente> buscarClienterPorApellido(String apellido) {
		List<Cliente> clientes = clienteRepositorio.searchByLastName(apellido);
		if (CollectionUtils.isNotEmpty(clientes)) {
			return clientes;
		} else {
			return (List<Cliente>) CollectionUtils.EMPTY_COLLECTION;
		}
	}


}

