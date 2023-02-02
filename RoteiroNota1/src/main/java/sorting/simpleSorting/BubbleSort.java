package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length <= 1) {
			return;
		}
		for (int j = leftIndex; j < rightIndex; j++) {
			boolean controle = true;
			for (int i = leftIndex; i < rightIndex - j; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) {
					Util.swap(array, i, i + 1);
					controle = false;
				} 
			}
			if (controle) {
				break;
			}
		}
	}
}
