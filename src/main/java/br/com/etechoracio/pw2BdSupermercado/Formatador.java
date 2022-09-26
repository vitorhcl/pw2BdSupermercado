package br.com.etechoracio.pw2BdSupermercado;
import java.time.LocalDate;
import java.time.format.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatador {
	public String moeda(double moeda) {
		return String.format("R$ %.2f", moeda);
	}

	public String cpf(String cpf) {
		return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9),
				cpf.substring(9, 11));
	}

	public String cnpj(String cnpj) {
		return String.format("%s.%s.%s/%s-%s", cnpj.substring(0, 2), cnpj.substring(2, 5), cnpj.substring(5, 8),
				cnpj.substring(8, 12), cnpj.substring(12, 14));
	}

	public String codBar(String codBar) {
		return String.format("%s %s %s", codBar.substring(0, 1), codBar.substring(1, 7), codBar.substring(7, 13));
	}

	public String data(LocalDate data) {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return f.format(data);
	}

	public LocalDate data(String data) {
		Pattern pattern = Pattern.compile("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]");
		Matcher matcher = pattern.matcher(data);
		if (!matcher.matches())
			throw new IllegalArgumentException();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(data, f);
	}
}
