package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		if(this.bst.isEmpty()) {return true;}
		if(!this.bst.root.getParent().isEmpty()) {return false;}
		return isBst(this.bst.getRoot());
		}

	private boolean isBst(BSTNode<T> node) {
		if(node.isEmpty()) {return true;}
		else {
			boolean esquerda = true;
			boolean direita = true;
			boolean paiEsquerda = true;
			boolean paiDireita = true;
			if(node.getRight().isEmpty() && !node.getLeft().isEmpty() || !node.getRight().isEmpty() && !node.getLeft().isEmpty()) {
				esquerda = node.getLeft().getData().compareTo(node.getData()) < 0;
				paiEsquerda = node.getLeft().getParent().getData().equals(node.getData());
			}
			if(!node.getRight().isEmpty() && node.getLeft().isEmpty() || !node.getRight().isEmpty() && !node.getLeft().isEmpty()) {
				direita = node.getRight().getData().compareTo(node.getData()) > 0;
				paiDireita = node.getRight().getParent().getData().equals(node.getData());
			}
			boolean result = esquerda && direita && paiDireita && paiEsquerda;
			return result && isBst((BSTNode<T>) node.getLeft()) && isBst((BSTNode<T>) node.getRight());
		}
	}
	
}
