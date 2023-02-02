package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		//Condições inválidas
		if (array.length == 0 || rightIndex > array.length || rightIndex < leftIndex || rightIndex == leftIndex) {return;}
		//maior elemento
        int max = array[leftIndex];
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (max < array[i]) {
                max = array[i];
			}
		}
		int[] c = new int[max + 1];
		//frequencia
		for (int i = leftIndex; i <= rightIndex; i ++) {
			c[array[i]] += 1;
		}
		//cumulativa
		for (int i = 1; i < c.length; i++) {
			c[i] +=  c[i-1];
		}

		Integer[] b = array.clone();
		//ordenação
		for (int i = rightIndex; i >= leftIndex; i--) {
			array[c[b[i]] - 1] = b[i];
			c[b[i]] -= 1;
		}
	}
}
