package orderStatistic;

import java.util.Arrays;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1] -> [1,4,6,8,9,12]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
    	if(k > array.length || array.length < 1 || k < 1) {return (T[]) new Comparable[]{};}
		array = mergeSort(array, 0, array.length - 1);
    	T[] k_maiores = (T[]) new Comparable[k];
    	for (int i = 0; i < k_maiores.length; i++) {
    		k_maiores[i] = orderStatistics(array, array.length - k + i);
    	}
    	return k_maiores;
    }
   
    public T[] mergeSort(T[] array, int left, int rig) {
		if (rig > array.length - 1 || left < 0 || array.length < 1) {return null;}
		if (left >= rig) {return array;}
		else {
			int meio = (left + rig) / 2;
			mergeSort(array, left, meio);
			mergeSort(array, meio + 1, rig);
			array = merge(array, left, meio, rig);
		}
		return array;
	}

	private T[] merge(T[] array, int left, int meio, int rig) {
		T[] clone = array.clone();
		int i = left;
		int j = meio + 1;
		int k = left;
		
		while (i <= meio && j <= rig) {
			if (clone[j].compareTo(clone[i]) > 0) {
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

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	private T orderStatistics(T[] array, int k){
		if (array.length < 1 || k > array.length - 1) {return null;}
		return array[k];
	}
}
