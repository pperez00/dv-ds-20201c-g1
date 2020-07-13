package ar.edu.davinci.dvds20201cg1.controlador.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.davinci.dvds20201cg1.controlador.MyApp;
import ar.edu.davinci.dvds20201cg1.modelo.Cliente;
import ar.edu.davinci.dvds20201cg1.servicio.ClienteServicio;

@Controller
public class ClienteSimpleControlador extends MyApp {


	private final Logger LOGGER = LoggerFactory.getLogger(ClienteSimpleControlador.class);


	private final ClienteServicio clienteServicio;


	@Autowired
	public ClienteSimpleControlador(final ClienteServicio clienteServicio) {
		super();
		this.clienteServicio = clienteServicio;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String viewHomePage(Model model) {
		LOGGER.info("GET - viewHomePage - /index");
		return "index";
	}

	@RequestMapping(value = "/cliente/list", method = RequestMethod.GET)
	public String showClientePage(Model model) {
		LOGGER.info("GET - showClientePage  - /cliente/list");
		List<Cliente> clientes = clienteServicio.listarClientes();
		model.addAttribute("listClientes", clientes);

		LOGGER.info("clientes.size: " + clientes.size());
		return "list_clientes";
	}

	@RequestMapping(value = "/cliente/new", method = RequestMethod.GET)
	public String showNewClientePage(Model model	) {
		LOGGER.info("GET - showNewClientePage - /cliente/new");
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);

		LOGGER.info("cliente: " + cliente.toString());

		return "new_cliente";
	}

	@RequestMapping(value = "/cliente/save", method = RequestMethod.POST)
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente) {
		LOGGER.info("POST - saveCliente - /cliente/save");
		LOGGER.info("cliente: " + cliente.toString());
		clienteServicio.grabarCliente(cliente);

		return "redirect:/myapp/cliente/list";
	}

	@RequestMapping(value = "/cliente/edit/{id}", method = RequestMethod.GET)
	public ModelAndView showEditClientePage(@PathVariable(name = "id") Long clienteId) {
		LOGGER.info("GET - showEditClientePage - /cliente/edit/{id}");
		LOGGER.info("cliente: " + clienteId);

		ModelAndView mav = new ModelAndView("edit_cliente");
		Cliente cliente = clienteServicio.buscarClientePorId(clienteId);
		mav.addObject("cliente", cliente);

		return mav;
	}

	@RequestMapping(value = "/cliente/delete/{id}", method = RequestMethod.GET)
	public String deleteCliente(@PathVariable(name = "id") Long clienteId) {
		LOGGER.info("GET - deleteCliente - cliente/delete/{id}");
		LOGGER.info("cliente: " + clienteId);
		clienteServicio.borrarCliente(clienteId);
		return "redirect:/myapp/cliente/list";
	}
}
