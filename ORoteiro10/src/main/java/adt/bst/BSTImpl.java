package adt.bst;

import java.util.LinkedList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	protected int height(BSTNode<T> node) {
		if (node.isEmpty()) {return -1;}
		else {return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));}
	}

	@Override
	public BSTNode<T> search(T element) {
		if(element == null) {return null;}
		return search(this.root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if(node.isEmpty()) {return (new BSTNode<>());}
		if(node.getData().equals(element)) {return node;}
		if(element.compareTo(node.getData()) < 0) {return search((BSTNode<T>) node.getLeft(), element);}
		else {return search((BSTNode<T>) node.getRight(), element);}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()){
				this.root = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.right((BSTNode<T>) new BSTNode<T>())
						.parent((BSTNode<T>) new BSTNode<T>())
						.left((BSTNode<T>) new BSTNode<T>())
						.build();
			}
			else {insert(this.root, element);}
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (element.compareTo(node.getData()) < 0){
			if (node.getLeft().isEmpty()){
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.right((BSTNode<T>) new BSTNode<T>())
						.left((BSTNode<T>) new BSTNode<T>())
						.parent(node)
						.build();
				node.setLeft(newNode);
				return;
			}
			insert((BSTNode<T>) node.getLeft(), element);
		}else {
			if (node.getRight().isEmpty()){
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.right((BSTNode<T>) new BSTNode<T>())
						.left((BSTNode<T>) new BSTNode<T>())
						.parent(node)
						.build();
				node.setRight(newNode);
				return;
			}
			insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if(isEmpty()) {return null;}
		else {return maximum(this.root);}
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if(node.getRight().getData() == null) {return node;}
		else {return maximum((BSTNode<T>) node.getRight());}
	}

	@Override
	public BSTNode<T> minimum() {
		if(isEmpty()) {return null;}
		else {return minimum(this.root);}
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if(node.getLeft().getData() == null) {return node;}
		else {return minimum((BSTNode<T>) node.getLeft());}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if(element == null) {return null;}
		else {
			BSTNode<T> sucessor = search(element);
			if(sucessor.getData() == null) {return null;}
			if(sucessor.getRight().getData() != null) {return minimum((BSTNode<T>) sucessor.getRight());}
			else {
				BSTNode<T> aux = (BSTNode<T>) sucessor.getParent();
				while (aux.getData() != null) {
					if(aux.getData().compareTo(sucessor.getData()) > 0) {return  aux;}
					else {aux = (BSTNode<T>) aux.getParent();}
				}
				if(aux.getData() == null) {return null;}
				return aux;
			}
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if(element == null) {return null;}
		else {
			BSTNode<T> predecessor = search(element);
			if(predecessor.getData() == null) {return null;}
			if(predecessor.getLeft().getData() != null) {return maximum((BSTNode<T>) predecessor.getLeft());}
			else {
				BSTNode<T> aux = (BSTNode<T>) predecessor.getParent();
				while (aux.getData() != null) {
					if(aux.getData().compareTo(predecessor.getData()) < 0) {return  aux;}
					else {aux = (BSTNode<T>) aux.getParent();}
				}
				if(aux.getData() == null) {return null;}
				return aux;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (this.isEmpty() || element == null) {return;}
		recursiveRemove(search(element));
	}

	private void recursiveRemove(BSTNode<T> node) {
		if (!node.isEmpty()) {
			if (node.isLeaf()) {
				if (node.getData().equals(root.getData())) {
					this.root = new BSTNode<>();
				} else {
					if (node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(new BSTNode<>());
					} else {
						node.getParent().setRight(new BSTNode<>());
					}
				}
			} else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
				if (node.getData().equals(root.getData())) {
					this.root = (BSTNode<T>) node.getRight();
					this.root.setParent(new BSTNode<>());
				} else {
					node.getRight().setParent(node.getParent());
					if (node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(node.getRight());
					} else {
						node.getParent().setRight(node.getRight());
					}
				}
			} else if (node.getRight().isEmpty() && !node.getLeft().isEmpty()) {
				if (node.getData().equals(root.getData())) {
					this.root = (BSTNode<T>) node.getLeft();
					this.root.setParent(new BSTNode<>());
				} else {
					node.getLeft().setParent(node.getParent());
					if (node.getData().compareTo(node.getParent().getData()) < 0) {
						node.getParent().setLeft(node.getLeft());
					} else {
						node.getParent().setRight(node.getLeft());
					}
				}
			} else {
				BSTNode<T> sucessor = sucessor(node.getData());
				recursiveRemove(sucessor);
				node.setData(sucessor.getData());
			}
		}
	}

	@Override
	public T[] preOrder() {
		if(isEmpty()) {return ((T[]) new Comparable[0]);}
		List<T> array = new LinkedList<T>();
		this.preOrder(this.root, (LinkedList<T>) array);
		return (T[]) array.toArray(new Comparable[0]);
	}

	private LinkedList<T> preOrder(BSTNode<T> root, LinkedList<T> array) {
		if (!root.isEmpty()) {
			array.add(root.getData());
			preOrder((BSTNode<T>) root.getLeft(), array);
			preOrder((BSTNode<T>) root.getRight(), array);
		}
		return array;
	}

	@Override
	public T[] order() {
		if(isEmpty()) {return ((T[]) new Comparable[0]);}
		List<T> array = new LinkedList<T>();
		this.order(this.root, (LinkedList<T>) array);
		return (T[]) array.toArray(new Comparable[0]);
	}

	private LinkedList<T> order(BSTNode<T> root, LinkedList<T> array) {
		if(!root.isEmpty()) {
			order((BSTNode<T>) root.getLeft(), array);
			array.add(root.getData());
			order((BSTNode<T>) root.getRight(), array);
		}
		return array;
	}

	@Override
	public T[] postOrder() {
		if(isEmpty()) {return ((T[]) new Comparable[0]);}
		List<T> array = new LinkedList<T>();
		this.postOrder(this.root, (LinkedList<T>) array);
		return (T[]) array.toArray(new Comparable[0]);
	}

	private LinkedList<T> postOrder(BSTNode<T> root, LinkedList<T> array) {
		if (!root.isEmpty()) {
			postOrder((BSTNode<T>) root.getLeft(), array);
			postOrder((BSTNode<T>) root.getRight(), array);
			array.add(root.getData());
		}
		return array;
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
