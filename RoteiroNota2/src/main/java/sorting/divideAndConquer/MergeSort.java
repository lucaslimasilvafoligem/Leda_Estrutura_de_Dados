package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
        if (rightIndex <= leftIndex)
            return;
        else {
            int meio = (leftIndex + rightIndex) / 2;
            sort(array, leftIndex, meio);
            sort(array, meio + 1, rightIndex);
            merge(array, leftIndex, meio, rightIndex);
        }
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
}
