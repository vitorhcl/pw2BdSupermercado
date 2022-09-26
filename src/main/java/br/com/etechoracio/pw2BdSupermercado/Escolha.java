package br.com.etechoracio.pw2BdSupermercado;
import java.util.Scanner;

public class Escolha<T> {
	public <T> T next(Class<T> tipo, String msg) {
		Scanner ent = new Scanner(System.in);
		boolean existe = Supermercado.listar(tipo);
		if (!existe)
			return null;
		int num = ent.nextInt();
		if (!(0 < num && num <= Supermercado.getLista(tipo).size())) {
			System.out.println(msg);
			return null;
		}
		T objeto = (T) Supermercado.getLista(tipo).get(num - 1);
		return (T) objeto;
	}
}
