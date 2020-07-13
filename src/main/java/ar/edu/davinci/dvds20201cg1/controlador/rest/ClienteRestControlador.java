package ar.edu.davinci.dvds20201cg1.controlador.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.davinci.dvds20201cg1.controlador.MyRestApp;
import ar.edu.davinci.dvds20201cg1.modelo.Cliente;
import ar.edu.davinci.dvds20201cg1.servicio.ClienteServicio;

@RestController
public class ClienteRestControlador extends MyRestApp {
	
	@Autowired
	private ClienteServicio clienteServicio;
	
	@GetMapping(path="/clientes/listar")
	public @ResponseBody Iterable<Cliente> getClientes() {
		return clienteServicio.listar();
	}
	

	@GetMapping(path="/clientes/buscar")
	public @ResponseBody Iterable<Cliente> getClientes(@RequestParam String apellido) {
		return clienteServicio.buscarClienterPorApellido(apellido);
	}
	
}

