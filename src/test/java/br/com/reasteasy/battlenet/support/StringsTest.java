package br.com.reasteasy.battlenet.support;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import br.com.reasteasy.battlenet.support.Strings;

public class StringsTest {

	@Test
	public void deveRetornarNullQuandoCepInformadoForNull() throws Exception {
		//Dado
		String cep = null;

		//Quando
		String cepNormalizado = Strings.normalizarCep(cep);

		//Entao
		assertNull(cepNormalizado);
	}

	@Test
	public void deveRetornarNullQuandoCNpjInformadoForNull() throws Exception {
		//Dado
		String cnpj = null;

		//Quando
		String cnpjNormalizado = Strings.normalizarCNPJ(cnpj);

		//Entao
		assertNull(cnpjNormalizado);
	}
}