package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.


	@Override
	public void insert(T element) {
		if (element != null) {
			if(this.root.isEmpty()) {
				this.root = (BSTNode<T>) new BSTNode.Builder<T>()
					.data(element)
					.right((BSTNode<T>) new BSTNode<T>())
					.parent((BSTNode<T>) new BSTNode<T>())
					.left((BSTNode<T>) new BSTNode<T>())
					.build();
				return;
			}
			insertRecursive(this.root, element);
		}
	}

	private void insertRecursive(BSTNode<T> node, T element) {
		if (element.compareTo(node.getData()) < 0){
			if (node.getLeft().isEmpty()){
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.right((BSTNode<T>) new BSTNode<T>())
						.left((BSTNode<T>) new BSTNode<T>())
						.parent(node)
						.build();
				node.setLeft(newNode);
				rebalance(newNode);
				return;
			}
			insertRecursive((BSTNode<T>) node.getLeft(), element);
		}else {
			if (node.getRight().isEmpty()){
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.right((BSTNode<T>) new BSTNode<T>())
						.left((BSTNode<T>) new BSTNode<T>())
						.parent(node)
						.build();
				node.setRight(newNode);
				rebalance(newNode);
				return;
			}
			insertRecursive((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public void remove(T element) {
		if (this.root.isEmpty() || element == null) {return;}
		recursiveRemove(search(element));
	}

	private void recursiveRemove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				if (node.getData().equals(root.getData())) {this.root = new BSTNode<>();}
				else {
					if (node.getData().compareTo(node.getParent().getData()) < 0) {node.getParent().setLeft(new BSTNode<>());}
					else {node.getParent().setRight(new BSTNode<>());}
				}
				rebalanceUp(node);
			} else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
				if (node.getData().equals(root.getData())) {
					this.root = (BSTNode<T>) node.getRight();
					this.root.setParent(new BSTNode<>());
				} else {
					node.getRight().setParent(node.getParent());
					if (node.getData().compareTo(node.getParent().getData()) < 0) {node.getParent().setLeft(node.getRight());}
					else {node.getParent().setRight(node.getRight());}
				}
			} else if (node.getRight().isEmpty() && !node.getLeft().isEmpty()) {
				if (node.getData().equals(root.getData())) {
					this.root = (BSTNode<T>) node.getLeft();
					this.root.setParent(new BSTNode<>());
				} else {
					node.getLeft().setParent(node.getParent());
					if (node.getData().compareTo(node.getParent().getData()) < 0) {node.getParent().setLeft(node.getLeft());}
					else {node.getParent().setRight(node.getLeft());}
				}
				rebalanceUp(node);
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				recursiveRemove(sucessor);
				node.setData(sucessor.getData());
			}
		}
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (!node.isEmpty()) {return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());}
		return 0;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if(node.isEmpty()) {return;}
		int balance = calculateBalance(node);
		if(balance > -2 && balance < 2) {rebalance((BSTNode<T>) node.getParent());}
		if(Math.abs(balance) > 1) {
			BSTNode<T> newRoot;
			if(balance < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {rightRotation((BSTNode<T>) node.getRight());}
				newRoot = leftRotation(node);
			}
			else {
				if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {leftRotation((BSTNode<T>) node.getLeft());}
				newRoot = rightRotation(node);
			}
			if (this.getRoot().equals(node)) {this.root = newRoot;}
		}
	}
	
	protected static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
		node.setLeft(pivot.getRight());
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		pivot.setRight(node);
		node.getLeft().setParent(node);
		if (!pivot.getParent().isEmpty()) {
			if (pivot.getParent().getRight().equals(node)) {pivot.getParent().setRight(pivot);}
			else {pivot.getParent().setLeft(pivot);}
		}
		return pivot;
	}

	protected static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> pivot = (BSTNode<T>) node.getRight();
		node.setRight(pivot.getLeft());
		pivot.setParent(node.getParent());
		node.setParent(pivot);
		pivot.setLeft(node);
		node.getRight().setParent(node);
		if (!pivot.getParent().isEmpty()) {
			if (pivot.getParent().getRight().equals(node)) {pivot.getParent().setRight(pivot);}
			else {pivot.getParent().setLeft(pivot);}
		}
		return pivot;
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if(node.isEmpty()) {return;}
		this.rebalance(node);
	}
	
	public boolean isValid(BSTNode<T> node) {
		int result = calculateBalance(node);
		return result > -2 && 2 > result;
	}
	
	public boolean isAvlTree(BSTNode<T> node) {
		if(node.isEmpty()) {return true;}
		return isValid(node) && isAvlTree((BSTNode<T>) node.getLeft()) && isAvlTree((BSTNode<T>) node.getRight());
	}
}
