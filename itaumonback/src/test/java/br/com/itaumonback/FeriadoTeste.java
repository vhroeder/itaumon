package br.com.itaumonback;

import java.util.Date;

import br.com.itaumonback.modelo.Feriado;

public class FeriadoTeste {

	public static void main(String[] args) {
		Feriado feriado = new Feriado();
		Date hoje = new Date();
		feriado.setDataInicio(hoje);
		System.out.println(feriado.getDataInicio());
	}

}
