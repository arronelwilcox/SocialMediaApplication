package edu.institution.asn11;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;


public class BST<E extends Comparable<E>> {

	protected TreeNode<E> root;
	protected int size = 0;
	
	public BST() { }
	
	public BST(E[] objects) {
		for (int i=0; i<objects.length; i++) {
			insert(objects[i]);
		}
	}
	
	public boolean search(E e) {
		TreeNode<E> current = root;
		
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean insert(E e) {
		if (root == null) {
			root = createNewNode(e);
		} else {
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else {
					return false;
				}
			}
				
			if (e.compareTo(parent.element) < 0) {
				parent.left = createNewNode(e);
			} else {
				parent.right = createNewNode(e);
			}
		}
		
		size++;
		return true;
	}
	
	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<E>(e);
	}
	
	public void inorder() {
		inorder(root);
	}
	
	protected void inorder(TreeNode<E> root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}
	
	public void postorder() {
		postorder(root);
	}
	
	protected void postorder(TreeNode<E> root) {
		if (root == null) return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}
	
	public void preorder() {
		preorder(root);
	}
	
	protected void preorder(TreeNode<E> root) {
		if (root == null) return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}
	
	public int getSize() {
		return size;
	}
	
	public TreeNode<E> getRoot() {
		return root;
	}
	
	public ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		TreeNode<E> current = root;
		
		while (current != null) {
			list.add(current);
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				break;
			}
		}
		
		return list;
	}
	
	public boolean delete(E e) {
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element)> 0) {
				parent = current;
				current = current.right;
			} else {
				break;
			}
		}
		
		if (current == null) {
			return false;
		}
		
		if (current.left == null) {
			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
			}
		} else {
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;
			
			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}
			
			current.element = rightMost.element;
			
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			} else {
				parentOfRightMost.left = rightMost.left;
			}
		}
		
		size--;
		return true;
	}
	
	public Iterator<E> iterator() {
		return new InorderIterator();
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	
	private class InorderIterator implements Iterator<E> {
		
		private ArrayList<E> list = new ArrayList<>();
		private int current = 0;
		
		public InorderIterator() {
			inorder();
		}
		
		private void inorder() {
			inorder(root);
		}
		
		private void inorder(TreeNode<E> root) {
			if (root == null) return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}
		
		public boolean hasNext() {
			return current < list.size();
		}
		
		public E next() {
			return list.get(current++);
		}
		
		public void remove() {
			delete(list.get(current));
			list.clear();
			inorder();
		}
		
		
	}
	/*** Traverses the nodes using the breadth-first traversal algorithm and returns a list of elements in the correct order.
	 * @return the elements in the order that reflects a breadth-first traversal.*/
	@SuppressWarnings({ "unchecked", "unused" })
	public List<E> breadthFirstTraversal() {
		// Create a New tree node list.
		List<TreeNode<E>> treeList = new ArrayList<TreeNode<E>>();
		if(root != null) {
			treeList.add(root);
		}
		TreeNode<E> current;
		// While the list is not empty
		while(!treeList.isEmpty()) {
			current = treeList.remove(0);
			// Displays the current element of the tree
			System.out.println(current.element + " ");
			// if the left node is not empty
			if(current.left != null) {
				// add the current node to the list
				treeList.add(current.left);
			}
			// if the right node is not empty
			else if (current.right != null) {
				// add the current node to the list
				treeList.add(current.right);
			}
		}
		return (List<E>) treeList;
	}
	
	/*** Returns the number of edges between the tree's root and its furthest leaf.*
	 * @return the height.*/
	@SuppressWarnings("unused")
	public int getHeight() {
		if(root == null)
			return 0;	
	    else{
	        TreeNode<E> node = root;
	        return getHeight(node);
	    }			
	}
	int getHeight(TreeNode<E> node) {
		int heightLeft = 0;
	    int heightRight = 0;
	    if(node.left!=null)
	        heightLeft = getHeight(node.left);
	    if(node.right!=null)
	        heightRight = getHeight(node.right);
	    if(heightLeft > heightRight){
	        return heightLeft+1;
	    }
	    else{
	        return heightRight+1;
	    }
		
	}
	/*** Returns the results of an in-order traversal without the use of recursion.* 
	 * *@returnthe elements in the order that reflects an in-order traversal.*/
	@SuppressWarnings({ "unchecked", "unused" })
	public List<E> nonRecursiveInorder() {
		List<TreeNode<E>> treeList = new ArrayList<TreeNode<E>>();
		Stack <TreeNode<E>> treeStack = new Stack  <TreeNode<E>>();
		if(root == null) {
			return (List<E>) treeList;
		}
		treeStack.push(root);
		// while the stack is not empty
		while(!treeStack.isEmpty()) {
			// stores the top value of the stack into node variable
			TreeNode<E> node = treeStack.peek();
			// if the left node is not null and not in the list
			if(node.left != null && !treeList.contains(node.left)) {
				// add the left node to the stack
				treeStack.push(node.left);
			}
			// else remove the node from the stack
			else {
				// removes the node from the stack
				treeStack.pop();
				// add the node to the list
				treeList.add(node);
				
				if(node.right != null) {
					// add the right node to the stack
					treeStack.push(node.right);
				}
			}
		}
		for(int i = 0 ; i < treeList.size(); i++) {
			System.out.println(treeList.get(i));
		}
		return (List<E>) treeList;
	}
}
