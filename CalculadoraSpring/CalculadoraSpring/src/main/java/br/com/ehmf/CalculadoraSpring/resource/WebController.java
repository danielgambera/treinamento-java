package br.com.ehmf.CalculadoraSpring.resource;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WebController {
	
	@GetMapping("/")
	public String showForm(Model model)
	{
		System.out.println("Cá passei!!!");
		model.addAttribute("modelOperacoes", List.of("Soma", "Subtração", "Multiplicação","Divisão"));
		return "index";
	}
	
	@PostMapping("/")
	public String handleFormSubmission(@RequestParam String modelOperacoes,
										@RequestParam String valor1,
										@RequestParam String valor2,
										Model model)
	{
		model.addAttribute("modelOperacoes", List.of("Soma", "Subtração", "Multiplicação","Divisão"));
		
		if (modelOperacoes.isEmpty() || valor1.isEmpty() || valor2.isEmpty())
		{
			model.addAttribute("response", "Informe os valores.");
			return null;
		}
		
		if (modelOperacoes.equals("Divisão") && Integer.parseInt(valor2) == 0)
		{
			model.addAttribute("response","Divisor não pode ser zero.");
			return("index");
		}

		
		String resposta = "";
		int res = 0;
		switch(modelOperacoes)
		{
		case "Soma":
			res = Integer.parseInt(valor1) + Integer.parseInt(valor2);
			break;
		case "Subtração":
			res = Integer.parseInt(valor1) - Integer.parseInt(valor2);
			break;
		case "Multiplicação":
			res = Integer.parseInt(valor1) * Integer.parseInt(valor2);
			break;
		case "Divisão":
			res = Integer.parseInt(valor1) / Integer.parseInt(valor2);
			break;
		}
		
		resposta = String.valueOf(res);
		model.addAttribute("response", resposta);
		
		return "index";
		
	}

	@RequestMapping("/limparResposta")
	public String limparResposta(Model model)
	{
		return "redirect:/";
	}
}
