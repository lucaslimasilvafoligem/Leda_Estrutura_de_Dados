package adt.avltree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	protected void rebalance(BSTNode<T> node) {
		if (node.isEmpty()) {return;}
		int balance = calculateBalance(node);
		if (balance > -2 && balance < 2) {rebalance((BSTNode<T>) node.getParent());}
		if (Math.abs(balance) > 1) {
			BSTNode<T> newRoot;
			if (balance < -1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) > 0) {
					rightRotation((BSTNode<T>) node.getRight());
					newRoot = leftRotation(node);
					if (this.getRoot().equals(node)) {this.root = newRoot;}
					this.RLcounter += 1;
					return;
				}
				newRoot = leftRotation(node);
				this.RRcounter += 1;
			} else {
				if (calculateBalance((BSTNode<T>) node.getLeft()) < 0) {
					leftRotation((BSTNode<T>) node.getLeft());
					newRoot = rightRotation(node);
					if (this.getRoot().equals(node)) {this.root = newRoot;}
					this.LRcounter += 1;
					return;
				}
				newRoot = rightRotation(node);
				this.LLcounter += 1;
			}
			if (this.getRoot().equals(node)) {this.root = newRoot;}
		}
	}
	
	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		if(array == null || array.length < 1) {return;}
		Set<T> resp = new HashSet<T>();
		for (int i = 0; i < array.length; i++) {resp.add(array[i]);}
		T[] order = this.order();
		for(int i = 0; i < order.length; i++) {resp.add(array[i]);}
		array = (T[]) resp.toArray(new Comparable[resp.size()]);
		Arrays.sort(array);
		this.root = (BSTNode<T>) new BSTNode<T>();
		int altura = 0;
		while(fillRebalance(array, 0, array.length, altura)) {altura += 1;}
	}
	
	private boolean fillRebalance (T[] array, int left, int right, int altura) {
		boolean resp = false;
		if (left < right) {
			int meio = ((right - left) / 2) + left;
			if (altura != 0) {
				boolean result1 = fillRebalance(array, meio + 1, right, altura - 1);
				boolean result2 = fillRebalance(array, left, meio, altura - 1);
				resp = result1 || result2;
			} else {
				resp = true;
				this.insert(array[meio]);
			}
		}
		return resp;
	}

}
