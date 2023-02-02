package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		//Condições inválidas
		if (array.length == 0 || rightIndex > array.length || rightIndex < leftIndex || rightIndex == leftIndex) {return;}
		//maior elemento
        int max = array[leftIndex];
		int min = array[leftIndex];
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (max < array[i]) {
                max = array[i];
			}
			if (min > array[i]) {
				min = array[i];
			}
		}
		int[] c = new int[max - min + 1];
		//frequencia
		for (int i = leftIndex; i <= rightIndex; i ++) {
			c[array[i] - min] += 1;
		}
		//cumulativa
		for (int i = 1; i < c.length; i++) {
			c[i] +=  c[i-1];
		}
		Integer[] b = array.clone();
		//ordenação
		for (int i = rightIndex; i >= leftIndex; i--) {
			array[c[b[i] - min] - 1] = b[i];
			c[b[i] - min] -= 1;
		}
	}
}
