package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array.length < 1) {return null;}
		array = quickSort(array, 0, array.length - 1);
		if (array[0] > x) {return null;}
		return buscaBinaria(array, x, -1, 0 , array.length -1);
	}

	public Integer[] quickSort(Integer[] array, int left, int rig) {
		if (array.length < 1 || rig > array.length -1 || left < 0 || left > rig) {return null;}
		if (left < rig) {
			int pivot = partition(array, left, rig);
			quickSort(array, left, pivot - 1);
			quickSort(array, pivot + 1, rig);
		}
		return array;
	}

	private int partition(Integer[] array, int left, int rig) {
		int pivot = array[left];
		int i = left;
		for (int j = left + 1; j <= rig; j++) {
			if (array[j] <= pivot) {
				i += 1;
				Util.swap(array, i, j);
			} 
		}
		Util.swap(array, left, i);
		return i;
	}

	public Integer buscaBinaria(Integer[] array, int x, int menor , int left, int rig) {
		if (array.length < 1 || rig > array.length -1 || left < 0) {return -1;}
		if (left > rig) {return menor;}
		Integer m = (left + rig) / 2;
		if (x == array[m]) {return array[m];}
		if (array[m] < x) {
			menor = array[m];
			return buscaBinaria(array, x, menor, m + 1, rig);
		}
		return buscaBinaria(array, x, menor, left, m - 1);
	}


}
