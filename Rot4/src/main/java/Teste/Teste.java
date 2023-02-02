package Teste;

import problems.FloorBinarySearchImpl;
import util.Util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import orderStatistic.KLargestOrderStatisticsImpl;
import orderStatistic.QuickSelect;

public class Teste <T extends Comparable<T>> {
	Integer[] array1;
	Integer[] array2;
	Integer[] array3;
	Integer[] array4;
	Integer[] array5;
	Integer[] array6;
	Integer[] array7;
	Integer[] array8;
	Integer[] arrayDeComparacao;
	Integer[] arrayDesordenado;
	Integer[] array1K;
	Integer[] array2k;
	Integer[] array5k;
	Integer[] array6k;
	Integer[] array8k;
	
	FloorBinarySearchImpl fbs = new FloorBinarySearchImpl();
	KLargestOrderStatisticsImpl klosi = new KLargestOrderStatisticsImpl<Integer>();
	QuickSelect quick = new QuickSelect<Integer>();


	@Before
	public void setUp() throws Exception {
		this.array1 = new Integer[] {1, 2, 3, 4, 7, 9, 11};
		this.array2 = new Integer[] {2, 2, 2, 2};
		this.array3 = new Integer[] {3, 4, 5, 7, 23, 44};
		this.array4 = new Integer[] {0, 2, 4, 5, 6, 7, 9, 11, 14, 17, 23, 24, 30};
		this.array5 = new Integer[] {6, 8, 11, 11, 8, 9, 44, 2, 2, 4, 5, 5, 6, 7};
		this.array6 = new Integer[] {};
		this.array7 = new Integer[] {10, 4, 6, 7, 8, 8, 4, 3, 111, 3, 4, 9, 5, 5};
		this.array8 = new Integer[] {6, 3, 4, 5, 7};
		this.arrayDeComparacao = new Integer[] {3, 4, 6, 5, 7};
		this.arrayDesordenado = new Integer[]{6, 1, 5, 8, 9};
		this.array1K = new Integer[] {7, 9, 11};
		this.array5k = new Integer[] {9, 11, 11, 44};
		this.array2k = new Integer[] {2, 2};
		this.array8k = new Integer[] {3, 4, 5, 6, 7};
	}

	private Integer[] selectionSort(Integer[] v) {
		if (v.length < 1) {return null;}
		for (int i = 0; i < v.length; i++) {
			int i_menor = i;
			for (int j = i + 1; j < v.length; j++) {
				if (v[j] < v[i_menor]) {
					i_menor = j;
				}
			}
		Util.swap(v, i, i_menor);
		}
		return v;
	}

	public void genericTest(Integer[] array, int k, Integer[] comparacao) {
		Assert.assertArrayEquals(comparacao, klosi.getKLargest(array, k));
	}
	
	public void genericKLargestTest(Integer[] array, int k, Integer[] comparacao) {
		Assert.assertArrayEquals((T[]) new Comparable[] {}, klosi.getKLargest(array, k));
	}
	
	public void genericQuickTest(Integer[] array, int left, int rig,int k, int k_esperado) {
		Assert.assertEquals(k_esperado, quick.quickSelect(array, k, left, rig));
	}
	
	public void genericQuickNullTest(Integer[] array, int left, int rig,int k) {
		Assert.assertEquals(null, quick.quickSelect(array, k, left, rig));
	}
	
	public void genericArraysTest(Integer[] comparacao, Integer[] array, int left, int rig) {			
		Assert.assertArrayEquals(comparacao, klosi.mergeSort(array, left, rig));
	}

	@Test
	public void test0() {
		assertEquals( (int)fbs.buscaBinaria(array1, 10, -1, 0, array1.length - 1), 9);
	}

	@Test
	public void test1() {
		assertEquals( (int)fbs.buscaBinaria(array1, 9, 0, 2, 5), 9);
	}
	
	@Test
	public void test2() {
		assertEquals( (int)fbs.buscaBinaria(array1, 0, -1, 0, array1.length - 1), -1);
	}
	
	@Test
	public void test3() {
		assertEquals( (int)fbs.buscaBinaria(array2, 1, -1, 0, array2.length - 1), -1);
	}
	
	@Test
	public void test4() {
		assertEquals( (int)fbs.buscaBinaria(array2, 3, -1, 0, array2.length - 1), 2);
	}
	
	@Test
	public void test5() {
		assertEquals( (int)fbs.buscaBinaria(array3, 7, -1, 0, array3.length - 1), 7);
	}
	
	@Test
	public void test6() {
		assertEquals( (int)fbs.buscaBinaria(array3, 5, -1, 2, array3.length - 1), 5);
	}
	
	@Test
	public void test7() {
		assertEquals( (int)fbs.buscaBinaria(array4, 13, -1, 0, array4.length - 1), 11);
	}
	
	@Test
	public void test8() {
		assertEquals( (int)fbs.buscaBinaria(array4, 29, -1, 0, array4.length - 1), 24);
	}
	
	@Test
	public void test9() {
		assertEquals( (int)fbs.buscaBinaria(array2, 3, -1, 1, array2.length - 1), 2);
	}
	
	@Test
	public void test10() {
		assertEquals( (int)fbs.buscaBinaria(array4, 31, -1, 0, array4.length - 1), 30);
	}
	
	@Test
	public void tes11() {
		assertEquals( (int)fbs.buscaBinaria(array2, 1, -1, 1, array2.length - 1), -1);
	}
	
	@Test
	public void tes12() {
		assertEquals( (int)fbs.buscaBinaria(array4, 0, -1, 0, array4.length - 1), 0);
	}
	
	@Test
	public void tes13() {
		assertEquals( (int)fbs.buscaBinaria(array4, 1, -1, 0, array2.length - 1), 0);
	}
	
	@Test
	public void tes14() {
		Arrays.equals(fbs.quickSort(array5, 0, array5.length -1), selectionSort(array5));
	}
	
	@Test
	public void tes15() {
		Arrays.equals(fbs.quickSort(array7, 0, array7.length -1), selectionSort(array7));
	}
	
	@Test
	public void tes16() {
		assertEquals( (int)fbs.buscaBinaria(array6, 0, -1, 0, array6.length - 1), -1);
	}
	
	@Test
	public void tes17() {
		assertEquals( (int)fbs.buscaBinaria(array4, 0, -1, -1, array4.length - 1), -1);
	}
	
	@Test
	public void tes18() {
		assertEquals( (int)fbs.buscaBinaria(array4, 0, -1, 0, array4.length), -1);
	}
	
	@Test
	public void tes19() {
		Arrays.equals(fbs.quickSort(array6, 0, array6.length -1), null);
	}
	
	@Test
	public void tes20() {
		Arrays.equals(fbs.quickSort(array2, 0, array2.length -1), selectionSort(array7));
	}
	
	@Test
	public void tes21() {
		Arrays.equals(fbs.quickSort(array8, 0, array8.length -3), arrayDeComparacao);
	}
	
	@Test
	public void test22() {
		assertEquals( (int)fbs.floor(array7, 110), 10);
	}
	
	@Test
	public void test23() {
		assertEquals(fbs.floor(array6, 0), null);
	}

	@Test
	public void test24() {
		assertEquals( (int)fbs.floor(array5, 9), 9);
	}
	
	@Test
	public void test25() {
		assertNull(fbs.floor(arrayDesordenado, 0));
	}

	@Test
	public void testSort028() {
		genericTest(this.array1, 3, this.array1K);
	}
	
	@Test
	public void tes26() {
		genericArraysTest(selectionSort(array4), array4, 0, array4.length - 1);
	}
	
	@Test
	public void testSort027() {
		genericTest(this.array5, 4, this.array5k);
	}
	
	@Test
	public void testSort029() {
		genericKLargestTest(this.array6, 4, this.array6k);
	}
	
	@Test
	public void testSort030() {
		genericTest(this.array2, 2, this.array2k);
	}
	
	@Test
	public void testSort031() {
		genericTest(this.array8, array8.length, this.array8k);
	}

	@Test
	public void testSort32() {
		genericQuickTest(this.array1, 0, this.array1.length - 1, 3, 3);
	}
	
	@Test
	public void testSort33() {
		genericQuickTest(this.array1, 0, this.array1.length - 1, 4, 4);
	}
	
	@Test
	public void testSort34() {
		genericQuickTest(this.array2, 0, this.array2.length - 1, 3, 2);
	}
	
	@Test
	public void testSort35() {
		genericQuickNullTest(this.array1, 0, this.array1.length, 3);
	}
	
	@Test
	public void testSort41() {
		genericQuickNullTest(this.array1, 0, this.array1.length - 1, array1.length + 1);
	}
	
	@Test
	public void testSort42() {
		genericQuickNullTest(this.array1, this.array1.length -1, 0, 3);
	}
	
	@Test
	public void testSort43() {
		genericQuickNullTest(this.array6, 0, this.array6.length - 1, 3);
	}
	
	@Test
	public void testSort44() {
		genericQuickNullTest(this.array1, -1, this.array1.length - 1, 3);
	}
	
	@Test
	public void testSort45() {
		genericQuickNullTest(this.array1, 0, this.array1.length - 1, 10);
	}
	
	@Test
	public void testSort46() {
		genericQuickTest(this.array1, 0, this.array1.length - 1, 1, 1);
	}
	
	@Test
	public void testSort36() {
		genericQuickTest(this.array1, 0, this.array1.length - 1, 3, 3);
	}
	
	@Test
	public void testSort37() {
		genericQuickTest(this.array1, 0, this.array1.length - 1, 2, 2);
	}
	
	@Test
	public void testSort38() {
		genericQuickTest(this.array8, 0, this.array8.length - 1, 3, 5);
	}
	
	@Test
	public void testSort39() {
		genericQuickTest(this.array5, 0, this.array5.length - 1, 5, 5);
	}
	
	@Test
	public void testSort40() {
		genericQuickTest(this.array7, 0, this.array7.length - 1, 8, 6);
	}
	
	@Test
	public void testSort49() {
		genericQuickTest(this.array3, 0, this.array3.length - 1, 5, 23);
	}
	
}
