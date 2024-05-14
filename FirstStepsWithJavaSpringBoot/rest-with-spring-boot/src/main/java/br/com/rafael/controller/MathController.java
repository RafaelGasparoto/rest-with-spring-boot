package br.com.rafael.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MathController {
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable(value="numberOne") String numberOne, @PathVariable(value="numberTwo") String numberTwo) throws Exception {
		if(!isNumeric(numberOne) || !isNumeric(numberTwo)) {
			throw new Exception();
		}
		
		return convertToDouble(numberOne) + convertToDouble(numberTwo);
	}
	
	private Double convertToDouble(String strNumber) {
		if(strNumber == null) return 0D;
		
		String value = strNumber.replaceAll(",", ".");
		if(isNumeric(value)) {
			return Double.parseDouble(value);
		}
		
		return 0D;
	}

	private boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		String value = strNumber.replaceAll(",", ".");
		return value.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
