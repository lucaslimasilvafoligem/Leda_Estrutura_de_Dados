package adt.bst.extended;

import adt.bst.BSTImpl;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {
	@Override
	public Integer floor(Integer[] array, double numero) {
		if(array.length < 1) {return null;}
		BSTImpl<Integer> tree = new BSTImpl<Integer>();
		tree = addNaArvore(tree, array, 0);
		if(tree.minimum().getData() > numero) {return null;}
		return floorRecursive(tree, tree.minimum().getData(), numero);
	}

	private Integer floorRecursive(BSTImpl<Integer> tree, Integer min, double numero) {
		if(numero < tree.sucessor(min).getData()) {return min;}
		else {return floorRecursive(tree, tree.sucessor(min).getData(), numero);}
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		if(array.length < 1) {return null;}
		BSTImpl<Integer> tree = new BSTImpl<Integer>();
		tree = addNaArvore(tree, array, 0);
		if(tree.maximum().getData() < numero) {return null;}
		return ceilRecursive(tree, tree.maximum().getData(), numero);
	}

	private Integer ceilRecursive(BSTImpl<Integer> tree, Integer max, double numero) {
		if (numero > tree.predecessor(max).getData()) {return max;}
		else {return ceilRecursive(tree, tree.predecessor(max).getData(), numero);}
	}

	private BSTImpl<Integer> addNaArvore(BSTImpl<Integer> tree, Integer[] array, int index) {
		if(index < array.length) {
			tree.insert(array[index]);
			return addNaArvore(tree, array, index + 1);
		}
		else {return tree;}
	}
}
