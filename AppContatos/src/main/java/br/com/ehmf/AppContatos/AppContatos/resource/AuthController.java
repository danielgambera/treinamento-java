package br.com.ehmf.AppContatos.AppContatos.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ehmf.AppContatos.AppContatos.configuration.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class AuthController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Operation(summary = "Obter um token. Informe uma senha igual ao usuario")
	@GetMapping("/token")
	public ResponseEntity<?> createToken(@RequestParam String usuario, @RequestParam String senha)
	{
		String token = jwtTokenUtil.createToken(usuario, senha);
		return ResponseEntity.ok(token);
	}
	
	
	
}