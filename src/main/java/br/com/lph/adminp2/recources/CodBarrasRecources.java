package br.com.lph.adminp2.recources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/CodigosBarras")
public class CodBarrasRecources {
	
	@RequestMapping(method=RequestMethod.GET)
	public String listar() {
		return "REST está funcionando!";
	}
}
