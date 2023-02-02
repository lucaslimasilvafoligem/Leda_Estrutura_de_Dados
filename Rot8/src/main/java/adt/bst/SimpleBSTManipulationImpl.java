package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		if(tree1.size() != tree2.size()) {return false;}
		if(tree1.isEmpty() && tree2.isEmpty()) {return true;}
		else {return equalsRecursivo(tree1.getRoot(), tree2.getRoot());}
		
	}

	private boolean equalsRecursivo(BTNode<T> node1, BTNode<T> node2) {
		boolean resp = node1.equals(node2);
		if(!resp) {return false;}
		if(!node1.isEmpty() && !node2.isEmpty() && resp != false) {return resp && equalsRecursivo(node1.getLeft(), node2.getLeft()) && equalsRecursivo(node1.getRight(), node2.getRight());}
		return resp;

	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		if(tree1.size() != tree2.size()) {return false;}
		if(tree1.getRoot().isEmpty() && tree2.getRoot().isEmpty()) {return true;}
		else {return isSimiliarRecursivo(tree1.getRoot(), tree2.getRoot());}

	}


	private boolean isSimiliarRecursivo(BTNode<T> node1, BTNode<T> node2) {
		boolean resp = checaFilhos(node1, node2);
		if(checaFilhos(node1.getLeft(), node2.getLeft()) && checaFilhos(node1.getRight(), node2.getRight()) && !(resp)) {return true;}
		return resp;
	}


	private boolean checaFilhos(BTNode<T> node1, BTNode<T> node2) {
		if(node1.hasOnlyLeftChild() && node2.hasOnlyLeftChild()) {return true;}
		if(node1.hasOnlyRightChild() && node2.hasOnlyRightChild()) {return true;}
		if(node1.noHasTwoChild() && node2.noHasTwoChild()) {return true;}
		if(node1.hasTwoChild() && node2.hasTwoChild()) {return true;}
		if(node1.isLeaf() && node2.isLeaf()) {return true;}
		else {return false;}
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		if (k < 1 || tree.isEmpty() || k > tree.size()) {return null;}
		else {return orderStatisticRecursivo(tree, tree.minimum(), k - 1);}
	}

	private T orderStatisticRecursivo(BST<T> tree ,BSTNode<T> node, int k) {
		if(k > 0) {return orderStatisticRecursivo(tree, tree.sucessor(node.getData()), k - 1);}
		else {return node.getData();}
	}
}
