public class Sorts {

    public static void swap(int[] array, int i, int j) {
		if (array == null)
			throw new IllegalArgumentException();

		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
    
    public int[] selectionSort(int[] array, int left, int rig) {
        for (int i = left; i < rig; i++) {
            int i_menor = i;
            for (int j = i + 1; j < rig; j++) {
               if (array[i_menor] >= array[j]) {i_menor = j;}
            }
            swap(array, i_menor, i);
        }
        return array;
    }

    public int[] bubbleSort(int[] array, int left, int rig) {
        for (int i = left; i < rig; i++) {
            boolean trocas = true;
            for (int j = left; j < rig - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                    trocas = false;
                }
            }
            if (trocas) {return array;}
        }
        return array;
    }

    public int[] insertionSort(int[] array, int left, int rig) {
        for (int i = left + 1; i < rig; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                swap(array, j, j - 1);
                j -= 1;
            }
        }
        return array;
    }

    public int[] bidirectionalBubbleSort(int[] array, int left, int rig) {
        for (int i = left; i < rig; i++) {
            boolean trocas = true;
            for (int j = left; j < rig - i; j++) {
                if (array[j] > array[j + 1]) {
                    selectionSort(array, j, j + 1);
                    trocas = false;
                }
            }
            if (trocas) {return array;}
            trocas = true;
            for (int k = rig - 1; k <= left; k--) {
                if (array[k] < array[k - 1]) {
                    selectionSort(array, k, k + 1);
                    trocas = false;
                }
            }
            if (trocas) {return array;}
        }
        return array;
    }

    public int[] selectionSortRecursive(int[] array, int left, int rig) {
        if (rig == left) {return array;}
        int menor = left;
        for (int i = left; i < rig; i++) {
            if (array[menor] > array[i + 1]) {menor = i + 1;}
        }
        swap(array, left, menor);
        left += 1;
        return selectionSortRecursive(array, left, rig);
    }

    public int[] recursiveBubbleSort(int[] array, int left, int rig) {
        if (left == rig) {return array;}
        for (int i = left; i < rig; i++) {
            if (array[i] > array[i + 1]) {swap(array, i + 1, i);}
        }
        return recursiveBubbleSort( array, left, rig - 1);
    }

    public int[] quickSort(int[] array, int left, int rig) {
        if (left < rig) {
            int pivot = partition(array, left, rig);
            quickSort(array, left, pivot - 1);
            quickSort(array, pivot + 1, rig);
        }
        return array;
    }

    private int partition(int[] array, int left, int rig) {
        int i = left;
        int pivot = array[left];
        for (int j = left + 1; j <= rig; j++) {
            if (array[j] <= pivot) {
                i += 1;
                swap(array, i, j);
            }
        }
        swap(array, left, i);
        return i;
    }

    public int[] mergeSort(int[] array, int left, int rig) {
        if (left >= rig) {return array;}
        else {
            int meio = (left + rig) / 2;
            mergeSort(array, left, meio);
            mergeSort(array, meio + 1, rig);
            array = merge(array, meio, left, rig);
        }
        return array;
    }

    private int[] merge(int[] array, int meio, int left, int rig) {
        int i = left;
        int j = meio + 1;
        int k = left;
        int[] clone = array.clone();
        while(i < meio && j < rig) {
            if (clone[i] <= clone[j]) {
                array[k] = clone[i];
                i += 1;
            } else {
                array[k] = clone[j];
                j += 1;
            }
            k += 1;
        }

        while (i <= meio) {
            array[k] = clone[i];
            i += 1;
            k += 1;
        }

        while (j <= rig) {
            array[k] = clone[j];
            j += 1;
            k += 1;
        }
        return array;
    }

    public int[] mergeSortHybrid(int[] array, int left, int rig) {
        return array;
    }

    public int[] quickSortMedianOfTree(int[] array, int left, int rig) {
        if (left < rig) {
		int pivot = particao(array, left, rig);
		quickSortMedianOfTree(array, left, pivot - 1);
		quickSortMedianOfTree(array, pivot + 1, rig);
		}
        return array;
    }

    private int particao(int[] array, int left, int rig) {
        array = comparaETroca(array, left, rig);
        int pivo = array[rig - 1];
        int i = left;
        for (int j = left + 1; j <= rig; j++) {
            if (array[j] <= pivo) {
                i += 1;
                swap(array, i, j);
            }
        }
        swap(array, left, i);
        return i;
    }

    private int[] comparaETroca(int[] array, int left, int rig) {
        int meio = (left + rig) / 2;
        if (array[meio] > array[rig]) {swap(array, rig, meio);}
        if (array[left] > array[meio]) {swap(array, left, meio);}
        if (array[left] > array[rig]) {swap(array, rig, meio);}
        swap(array, rig - 1, meio);
        return array;
    }

    public int[] coutingSort(int[] array, int left, int rig) {
        int k_maior = left;
        for (int i = left + 1; i < rig; i ++) {
            if (array[k_maior] < array[i]) {k_maior = i;}
        }
        int[] c = new int[k_maior + 1];
        //frequencia
        for (int i = left; i < rig; i++) {c[array[i]] += 1;}
        //cumulativa
        for (int i = 1; i < c.length; i++) {c[i] += c[i - 1];}
        int[] b = array.clone();
        for (int i = rig; i >= left; i--) {
            array[c[b[i]] - 1] = b[i];
            c[b[i] - 1] -= 1; 
        }
        return array;
    }

    public int[] extendeCouting(int[] array, int left, int rig) {
        int maior = left;
        int menor = left;
        for (int i = left + 1; i <= rig; i++) {
            if (array[i] > array[left]) {maior = i;}
            if (array[i] < array[left]) {menor = i;}
        }
        int[] c = new int[maior + 1];
        //frequencia
        for (int i = left; i <= rig; i++) {c[array[i] - menor] += 1;}
        //cumulativa
        for (int i = 1; i < c.length; i++) {c[i] += c[i - 1];}
        int[] b = array.clone();
        for (int i = rig; i >= left; i--) {
            array[c[b[i] - menor] - 1] = b[i];
            c[b[i] - menor] -= 1;
        }
        return array;
    }

    public int buscaBinaria(int[] array, int x, int floor, int left, int rig) {
        if (left > rig) {return floor;}
        int meio = (left + rig) / 2;
        if (x == array[meio]) {return array[meio];}
        if (x > array[meio]) {
            floor = array[meio];
            return buscaBinaria(array, x, floor, meio + 1, rig);
        }
        return buscaBinaria(array, x, floor, left, meio - 1);
    }

    public int quickSelect(int[] array, int x, int left, int rig) {
        int x_menor = x - 1;
        int pivo = partition(array, left, rig);
        if (x_menor > pivo) {return quickSelect(array, x, pivo + 1, rig);}
        if (x_menor < pivo) {return quickSelect(array, x, left, pivo - 1);}
        return x;
    }

    public int[] bubleSortt(int[] array, int left, int rig) {
        if(array.length < 1 || left < 0 || rig > array.length - 1 || left > array.length -1) {return null;}
        if(array.length - 1 == 1) {return array;}
        for (int i = left; i <= rig; i++) {
            boolean torcas = true;
            for (int j = left; j < rig - i; j++) {
                if(array[j] > array[j + 1]) {
                    swap(array, j, j+1);
                    trocas = false;
                }
                if(trocas) {return array;}
            }
        }
        return array;
    }

    public int[] mergeSortt(int[] array, int left, int rig) {
        if(array.length < 1 || left < 0 || rig > array.length - 1 || left > array.length -1) {return null;}
        if(array.length - 1 == 1) {return array;}
        if(left >= rig) {return array;}
        else {
            int meio = (left + rig) / 2;
            mergeSortt(array, left, meio);
            mergeSortt(array, meio + 1, rig);
            array = mergee(array, left, meio, rig);
        }
        return array;
    }

    private int[] mergee(int[] array, int left, int meio,int rig) {
        int i = left;
        int j = meio + 1;
        int k = left;
        int[] clone = arra.clone();

        while (i < meio && j < rig) {
            if(clone[i] <= clone[j]) {
                array[k] = clone[i];
                i += 1;
            } else {
                array[k] = clone[j];
                j += 1;
            }
            k += 1;
        }
        while (i <= meio) {
            array[k] = clone[i];
            i += 1;
            k += 1;
        }
        while (j <= rig) {
        array[k] = clone[j];
        j += 1;
        k += 1;
        }
        return array;
    }

    public int buscaBinariaa(int[] array, int floor, int left, int rig) {
        if(left > rig) {return floor;}
        int meio = (left + rig) / 2;
        if(x == array[meio]) {return array[meio];}
        if(x > array[meio]) {
            floor = array[meio];
            return buscaBinariaa(array, floor, meio + 1, rig);
        }
        return buscaBinariaa(array, floor, left, meio -1);
    }

    public int[] quickSortt(int[] array, int left, int rig) {
        if(left < rig) {
        int pivot = particaoo(array);
        quickSort(array, left, pivot -1);
        quickSort(array, pivot + 1, rig);
        }
        return array;
    }
}

