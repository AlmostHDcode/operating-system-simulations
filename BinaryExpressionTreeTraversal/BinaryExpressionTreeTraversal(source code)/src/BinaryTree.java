import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

/**Modified Binary Tree with nodes that handles expressions
 * @author Matt Hunt 
 * @version V1-2/6/2019
 */

/**
 * Binary Tree Class with Nodes
 * @param <E>
 */
public class BinaryTree<E extends Comparable<E>> implements Serializable{

	//Node inner class
	protected static class Node<E> implements Serializable {
		// Data Fields
		public E data;
		public Node<E> left;
		public Node<E> right;

		/**
		 * Construct a node with E data
		 * @param data stored in this node
		 */
		public Node(E data) {
			this.data = data;
		}

		/**
		 * Returns a string of the node.
		 * @return A string of the data fields
		 */
		@Override
		public String toString() {
			return data.toString();
		}
	}
	/** The root node of the binary tree */
	protected Node<E> root;

	/** Construct an empty BinaryTree */
	public BinaryTree() {
		root = null;
	}

	/**
	 * @param root node of the tree.
	 */
	protected BinaryTree(Node<E> root) {
		this.root = root;
	}

	/**
	 * Constructs a new binary tree with data in its root,leftTree
	 * as its left subtree and rightTree as its right subtree.
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree,
			BinaryTree<E> rightTree) {

		root = new Node<E>(data);

		root.left = leftTree.root;

		root.right = rightTree.root;

	}

	/**
	 * Return the left subtree
	 * @return The left subtree or null if either the root or
	 * the left subtree is null
	 */
	public BinaryTree<E> getLeftSubtree() {

		if (root != null && root.right != null) {
			return new BinaryTree<E>(root.right);

		} else {
			return new BinaryTree<E>(null);
		}

	}

	/**
	 * @return the right sub-tree or
	 * null if either the root or the
	 * right subtree is null.
	 */
	public BinaryTree<E> getRightSubtree() {

		if (root != null && root.right != null) {
			return new BinaryTree<E>(root.left);

		} else {
			return new BinaryTree<E>(null);
		}

	}

	/**
	 * Return the data field of the root
	 * @return the data field of the root
	 * or null if the root is null
	 */
	public E getData() {
		if (root != null) {
			return root.data;
		} else {
			return null;
		}
	}

	/**Checks if tree is empty
	 * 
	 * @return true if root is null, and false otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**Checks if tree is a leaf.
	 * 
	 * @return true if this tree is a leaf, and false otherwise.
	 */
	public boolean isLeaf() {
		return root != null && root.left == null && root.right == null;
	}

	/**
	 * toString that returns the preorder traversal of the tree
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}

	/**
	 * Method to read a binary tree.
	 * @pre The input consists of a preorder traversal of the binary tree. The line "null" indicates a null tree.
	 * @param bR - input file
	 * @return binary tree
	 * @throws IOException If there is an input error
	 */
	public static BinaryTree<String> readBinaryTree(BufferedReader bR)
			throws IOException {
		String data = bR.readLine().trim();
		if (data.equals("null")) {
			return null;
		} else {
			BinaryTree<String> leftTree = readBinaryTree(bR);
			BinaryTree<String> rightTree = readBinaryTree(bR);
			return new BinaryTree<String>(data, leftTree, rightTree);
		}
	}
	
	/**
	 * traverses the tree using inorder
	 * @return sb the created inorder traversal string
	 */
	public String inOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		inOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	/**
	 * helper method of inOrderTraverse()
	 * @param node The local root of tree
	 * @param depth The depth of tree
	 * @param sb The string builder to save the output
	 */
	private void inOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		if(node != null) {
			inOrderTraverse(node.left, depth + 1, sb);
			sb.append(node.toString());
			inOrderTraverse(node.right, depth + 1, sb);
		}
	}
	
	/**
	 * traverses the tree using preorder
	 * @return sb the created preorder traversal string
	 */
	public String preOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	/**
	 * helper method of preOrderTraverse()
	 * @param node The local root of tree
	 * @param depth The depth of tree
	 * @param sb The string builder to save the output
	 */
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		if (node != null) {
			sb.append(node.toString());
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);
		}
	}
	
	/**
	 * traverses the tree using postorder
	 * @return sb the created postorder traversal string
	 */
	public String postOrderTraverse() {
		StringBuilder sb = new StringBuilder();
		postOrderTraverse(root, 1, sb);
		return sb.toString();
	}
	
	/**
	 * helper method of postOrderTraverse()
	 * @param node The local root of tree
	 * @param depth The depth of tree
	 * @param sb The string builder to save the output
	 */
	private void postOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		if(node != null) {
			postOrderTraverse(node.left, depth + 1, sb);
			postOrderTraverse(node.right, depth + 1, sb);
			sb.append(node.toString());
		}
	}
	
	/**checks to see if a character is an operand or not
	 * @param x
	 * @return true is character is an operand, false otherwise
	 */
	private boolean isOperand(char x) {
		return (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z') || (x >= '0' && x <= '9');
	}

	/**checks to see if a character is an operator or not
	 * @param x
	 * @return true if characer is an operator, false otherwise
	 */
	private boolean isOperator(char x) {
		if(x == '*' || x == '/' || x == '+' || x == '-' || x == '^' || x == '(' || x == ')') {
			return true;
		} else {
			return false;
		}
	}
	
	/**used to compare two operators, if one has a higher value than the other, it has higher precedent
	 * @param op the inputted character
	 * @return 1,2,3, or -1
	 */
	private int compareOperators(char op) {
		switch(op) {
		case '+':
			return 1;
		case '-':
			return 1;
		case '*':
			return 2;
		case '/':
			return 2;
		case '^':
			return 3;
		}
		return -1;
	}
	
	// operator stack needed to convert from infix to postfix
	private Stack<Character> operators = new Stack<Character>();
	//string that holds the result after the conversion
	private StringBuilder postfix = new StringBuilder();

	/** 
	 * Takes infix expression and returns postfix
	 * helper method to makeExpressionTree
	 * @param expression an infix string
	 * @return postfix expression
	 */
	private String infixToPostfix(String expression) {

		for(int i = 0; i < expression.length(); i++) {
			char x = expression.charAt(i);
			if(isOperand(x)) {
				postfix.append(x);
			} else if(x == '(') {
				operators.push(x);
			} else if(x == ')') {
				while(!operators.isEmpty() && operators.peek() != '(') {
					postfix.append(operators.pop());
				} if(!operators.isEmpty() && operators.peek() != '(') {
					return null;
				} else if(!operators.isEmpty()) {
					operators.pop();
				}
			} else if(isOperator(x)) {
				if(!operators.isEmpty() && compareOperators(x) <= compareOperators(operators.peek())) {
					postfix.append(operators.pop());
				}
				operators.push(x);
			}
		}
		while(!operators.isEmpty()) {
			postfix.append(operators.pop());
		}
		return postfix.toString();
	
	}

	/** 
	 * @param expression an infix string
	 * @return postfix expression
	 */
	public void makeExpressionTree(String expression) {
		//calls infixToPostfix
		//takes parameter and turns into postfix
		//postfix used to create new Binary Tree
		String postfix = infixToPostfix(expression);

		Stack<Node> nodes = new Stack<Node>();

		for(int i = 0; i < postfix.length(); i++) {
			char x = postfix.charAt(i);
			if(isOperator(x)) {
				//if character is an operator
				//the right and left nodes are the top two on the stack
				//creates new node
				//new node left and right set to rNode and lNode
				//new node pushed to stack
				Node<String> rNode = nodes.pop();
				Node<String> lNode = nodes.pop();
				Node<String> newNode = new Node<String>(x+"");
				newNode.left = lNode;
				newNode.right = rNode;
				nodes.push(newNode);
			} else {
				//if character is not an operator
				//new node created and pushed to stack
				//new node does not have any left or right nodes
				Node<String> newNode = new Node<String>(x+"");
				nodes.push(newNode);
			}
		}
		//root is the top of the stack
		root = nodes.pop();
	}


}