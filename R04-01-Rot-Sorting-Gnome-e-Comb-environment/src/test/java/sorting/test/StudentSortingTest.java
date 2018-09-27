package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.variationsOfBubblesort.CombSort;
import sorting.variationsOfBubblesort.GnomeSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;
	private Integer[] vetorValoresImpates;
	private Integer[] vetorValoresNegativos;
	private AbstractSorting<Integer> implementation;
	private Integer[] valoresSortidos;
	private Integer[] valoresSortidos2ponto0;
	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23, 31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49, 11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4});
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });
		populaVetorComValoresNegaticos(new Integer[] { 1, 2, 3, 4, -1 });
		populaVetorValoresNegativos(new Integer[] { -1,-4,-4,-3,-2,-7});
		populaVetorValoresSortidos(new Integer[] {-1,2,-3,4,-13,100,-203,4,5,6,7,8,54,-43,-75,7543,-1,2,8,8,8,8});
		populaVetorValoresSortigos2ponto0(new Integer[] {-1,2,-3,4,-13,1,2,-2,-5,-74,-88,-75,54,0,-1,2365,5235,4234,234,141,4234,2342,5245346,64,64,64,67,585,2,364,1,100,-203,4,5,6,7,8,54,-43,-75,7543,-1,2,8,8,8,8});
		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação do
	 * aluno
	 */
	private void getImplementation() {
		// TODO O aluno deve instanciar sua implementação abaixo ao invés de
		// null
		this.implementation = new GnomeSort<>();
		// Assert.fail("Implementation not provided");
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorComValoresNegaticos(Integer[] arrayPadrao) {
		this.vetorValoresImpates = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	public void populaVetorValoresNegativos(Integer[] arrayPadrao) {
		this.vetorValoresNegativos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	public void populaVetorValoresSortidos(Integer[] arrayPadrao) {
		this.valoresSortidos = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	public void populaVetorValoresSortigos2ponto0(Integer[] arrayPadrao) {
		this.valoresSortidos2ponto0 = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}
	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if (array.length > 0) {
			copy1 = Arrays.copyOf(array, array.length);
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}
	@Test
	public void testSort06() {
		genericTest(vetorValoresImpates);
	}
	
	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS UMA
	 * PARTE DO ARRAY.
	 */
	@Test
	public void testSort07() {
		genericTest(vetorValoresNegativos);
	}
	@Test
	public void testSort08() {
		genericTest(valoresSortidos);
	}
	@Test
	public void testSort09() {
		genericTest(valoresSortidos2ponto0);
	}
}