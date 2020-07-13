package ar.edu.davinci.dvds20201cg1.controlador.view;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.davinci.dvds20201cg1.controlador.MyApp;
import ar.edu.davinci.dvds20201cg1.modelo.Cliente;
import ar.edu.davinci.dvds20201cg1.servicio.ClienteServicio;

@Controller
public class ClienteThymeleafControlador extends MyApp{

	private final Logger LOGGER = LoggerFactory.getLogger(ClienteThymeleafControlador.class);


	private final ClienteServicio clienteServicio;


	@Autowired
	public ClienteThymeleafControlador(final ClienteServicio clienteServicio) {
		super();
		this.clienteServicio = clienteServicio;
	}


	@RequestMapping(path = "/cliente/listar", method = RequestMethod.GET)
	public ModelAndView clientes(){
		LOGGER.info("GET - clientes: /cliente/listar");
		ModelAndView modelAndView = new ModelAndView();
		List<Cliente> clientes = clienteServicio.listarClientes();
        LOGGER.info("clientes.size: " + clientes.size());

		modelAndView.addObject("selections", clientes);
		modelAndView.setViewName("clientes/clientes");
		return modelAndView;
	}


	// Buscar Clientes
    @RequestMapping(value = "/cliente/buscar", method = RequestMethod.GET)
    public String initFindForm(Model model) {
		LOGGER.info("GET - initFindForm: /cliente/buscar");

    	model.addAttribute("cliente", new Cliente());
        return "clientes/buscarClientes";
    }


    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public String processFindForm(Cliente cliente, BindingResult result, Model model) {
		LOGGER.info("GET - processFindForm: /clientes");
		LOGGER.info("cliente: " + cliente.toString());

		Collection<Cliente> results = null;
		// allow parameterless GET request for /clientes to return all records
        if (StringUtils.isEmpty(cliente.getLastName())) {
            //cliente.setLastName(""); // empty string signifies broadest possible search
            results = clienteServicio.listarClientes();
            LOGGER.info(" por lastName null results.size: " + results.size());

        } else {
        	// find clientes by last name
        	results = clienteServicio.buscarClienterPorApellido(cliente.getLastName());
            LOGGER.info(" por lastName not null results.size: " + results.size());
        }


        LOGGER.info("results.size: " + results.size());

        if (results.size() < 1) {
            // no clientes found
            result.rejectValue("lastName", "notFound", "not found");
            return "clientes/buscarClientes";
        }
        if (results.size() > 1) {
            // multiple clientes found
            model.addAttribute("selections", results);
            return "clientes/listadoClientes";
        } else {
            // 1 cliente found
            cliente = results.iterator().next();
            return "redirect:/myapp/cliente/" + cliente.getId();
        }
    }

    /**
     * Custom handler for displaying an cliente.
     *
     * @param clienteId the ID of the cliente to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "/cliente/{clienteId}", method = RequestMethod.GET)
    public ModelAndView showCliente(@PathVariable("clienteId") Long clienteId) {
		LOGGER.info("GET - showCliente: /cliente/{clienteId}  - clienteId: " + clienteId);

    	ModelAndView mav = new ModelAndView("clientes/detalleClientes");
        Cliente cliente = clienteServicio.buscarClientePorId(clienteId);
        LOGGER.info("cliente: " + cliente.toString());

        mav.addObject(cliente);
        return mav;
    }


    // Crear Clientes
    @RequestMapping(value = "/cliente/nuevo", method = RequestMethod.GET)
    public String initCreationForm(Model model) {
    	LOGGER.info("GET - initCreationForm: /cliente/nuevo");
    	Cliente cliente = new Cliente();
    	LOGGER.info("cliente: "+ cliente.toString());

        model.addAttribute(cliente);
        return "clientes/crearOModificarClienteFormulario.html";
    }

    @RequestMapping(value = "/cliente/nuevo", method = RequestMethod.POST)
    public String processCreationForm(@Valid Cliente cliente, BindingResult result, SessionStatus status) {
    	LOGGER.info("POST - processCreationForm: /cliente/nuevo");
    	LOGGER.info("cliente: "+ cliente.toString());

    	LOGGER.info("result.hasErrors(): "+ result.hasErrors());

    	if (result.hasErrors()) {
            return "clientes/crearOModificarClienteFormulario";
        } else {
            clienteServicio.grabarCliente(cliente);
            status.setComplete();
            return "redirect:/myapp/cliente/" + cliente.getId();
        }
    }

    //Modificar Cliente
    @RequestMapping(value = "/cliente/{clienteId}/editar", method = RequestMethod.GET)
    public String initUpdateClienteForm(@PathVariable("clienteId") Long clienteId, Model model) {
    	LOGGER.info("GET - initCreationForm: /cliente/{clienteId}/editar");
    	LOGGER.info("clienteId: "+ clienteId);

    	Cliente cliente =  clienteServicio.buscarClientePorId(clienteId);
        model.addAttribute(cliente);
        return "clientes/crearOModificarClienteFormulario";
    }

    @RequestMapping(value = "/cliente/{clienteId}/editar", method = RequestMethod.POST)
    public String processUpdateClienteForm(@Valid Cliente cliente, BindingResult result, SessionStatus status) {
    	LOGGER.info("POST - initCreationForm: /cliente/{clienteId}/editar");
    	LOGGER.info("cliente: "+ cliente.toString());

    	LOGGER.info("result.hasErrors(): "+ result.hasErrors());

    	if (result.hasErrors()) {
            return "clientes/crearOModificarClienteFormulario";
        } else {
            this.clienteServicio.grabarCliente(cliente);
            status.setComplete();
            return "redirect:/myapp/cliente/" + cliente.getId();
        }
    }

}
