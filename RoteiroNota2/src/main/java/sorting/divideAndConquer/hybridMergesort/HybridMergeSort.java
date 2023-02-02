package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		if (rightIndex <= SIZE_LIMIT) {
			Insertsort(array, leftIndex, rightIndex);
			return;
		} else {
			Mergesort(array, leftIndex, rightIndex);
			return;
		}
	}
	
	private void Mergesort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex <= leftIndex) {
            return;
	   	} 	else {
            	int meio = (leftIndex + rightIndex) / 2;
            	Mergesort(array, leftIndex, meio);
            	Mergesort(array, meio + 1, rightIndex);
            	merge(array, leftIndex, meio, rightIndex);
        }
		MERGESORT_APPLICATIONS += 1;
	}

	private T[] merge(T[] array, int inicio, int meio, int fim) {
		T[] aux = array.clone();
        int i = inicio;
        int j = meio + 1;
        int k = inicio;
        
        while (i <= meio && j <= fim) {
            if (aux[i].compareTo(aux[j]) < 0) {
                array[k] = aux[i];
                i += 1;
            } else {
                array[k] = aux[j];
                j += 1;
            }
            k += 1;
		}  
            
		while (i <= meio) {
   	        	array[k] = aux[i];
    	    	i += 1;
            	k += 1;
        	}
    	while (j <= fim) {
        	    array[k] = aux[j];
				j += 1;
    			k += 1;
        	}
		return array;
	}

	private void Insertsort(T[] array, int leftIndex, int rightIndex) {
		if (array.length <= 1) {
			return;
		}
		for (int i = leftIndex; i < rightIndex; i++) {
			int j = i + 1;
			while (j > 0 && array[j].compareTo(array[j - 1]) < 0) {
				Util.swap(array, j, j -1);
				j -= 1;
			}
		}
		INSERTIONSORT_APPLICATIONS += 1;
	}
}
