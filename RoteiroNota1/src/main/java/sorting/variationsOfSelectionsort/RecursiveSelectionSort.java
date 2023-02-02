package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length <= 1 || leftIndex == rightIndex) {
			return;
		}

		int menor = leftIndex;
		for (int j = leftIndex; j < rightIndex; j++) {
			if (array[menor].compareTo(array[j + 1]) > 0) {
				menor = j + 1;
			}
		}
		Util.swap(array, menor, leftIndex);
		leftIndex += 1;
		sort(array, leftIndex, rightIndex);
	}
}
