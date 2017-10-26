
/**
 * @author Eric Lief
 *
 */

import java.util.Stack;

public class SplayIterative {

    private Node root;
    private double totalCumPathLength;
    private int totalFindOps;

    public SplayIterative() {
	root = null;
	totalCumPathLength = 0.0;
    }

    private class Node {
	private int key;          // The data in the node
	Node left;         // Left child
	Node right;        // Right child

	Node(int key) {
	    this.key = key;
	}
    }

    public Stack<Node> getPath(int key) {

	// System.out.println("Getting path for " + key);

	Stack<Node> path = new Stack<Node>();

	// Empty tree, no need to splay
	if (root == null) {
	    System.out.println("tree empty and no " + key);
	    return null;
	}

	/*
	 * Iterate from root down to insertion point, adding nodes to path
	 * stack, until null node, and then add new node with key
	 */
	Node cur = root;
	while (cur != null) {
	    path.push(cur); 	// add current node to stack
	    // System.out.println("Searching, pushing " + cur.key);
	    // System.out.println(path);

	    // Go left
	    if (key < cur.key) {
		cur = cur.left;
	    }

	    // Go right
	    else if (key > cur.key) {
		cur = cur.right;
	    }

	    // Key already present
	    else {
		// path.push(cur);
		break;
	    }
	}

	return path;
    }

    public int find(int key) {

	// System.out.println("Searching for " + key);

	Stack<Node> path = getPath(key);
	totalCumPathLength += path.size() - 1; 	// increment path
	totalFindOps += 1;
	//
	// System.out.println("before splay");
	// inorder();
	// preorder();
	// postorder();

	splay(path);

	// System.out.println("after splay");
	// inorder();
	// preorder();
	// postorder();

	if (key == root.key)
	    return key;
	else
	    return -1;

    }
    // public void insert(int key) {
    //
    // Stack<Node> path = find(key);
    // Node u = new Node(key);
    //
    // // Add new node at insertion point
    // Node p = path.peek(); // check parent
    // if (key < p.key) { // insert as left child of parent
    // p.left = u;
    // System.out.println("Inserting " + key + " left of " + p.key);
    // } else if (key > p.key) {
    // p.right = u; // insert as right child of parent
    // System.out.println("Inserting " + key + " right of " + p.key);
    // } else
    // return; // key already present, but should we still splay?
    //
    // path.push(u);
    // System.out.println("Pushing " + u.key);
    // System.out.println("Inserted " + key);
    // System.out.println(path);
    //
    // }

    public void insert(int key) {

	// Stack<Node> path = new Stack<Node>();
	Node u = new Node(key);		// new node to be inserted

	// Empty tree, no need to splay, just insert at root
	if (root == null) {
	    root = u;
	    // System.out.println("new root " + u.key);
	    return;
	}

	/*
	 * Iterate from root down to insertion point, adding nodes to path
	 * stack, until null node, and then add new node with key.
	 */

	Stack<Node> path = getPath(key);
	Node p = path.peek();	// check parent
	if (key < p.key) {	// insert as left child of parent
	    p.left = u;
	    // System.out.println("Inserting " + key + " left of " + p.key);
	} else if (key > p.key) {
	    p.right = u;	// insert as right child of parent
	    // System.out.println("Inserting " + key + " right of " + p.key);
	} else
	    return; 	// duplicate, do nothing

	path.push(u);
	// System.out.println("Pushing " + u.key);
	// System.out.println("Inserted " + key);
	// System.out.println(path);

	splay(path);

    }

    // // Splay node, either single or double rotation, determined
    // // by inequalities with parent and grandparent nodes
    //
    // Node g, top; // grandparent, top nodes
    // while (!path.empty()) {
    // g = top = null;
    // u = path.pop();
    // // p = path.pop();
    // if (path.empty()) {
    // root = u;
    // return;
    // }
    //
    // p = path.pop();
    //
    // if (!path.empty())
    // g = path.pop();
    //
    // // u < p
    // if (u.key < p.key) {
    //
    // // p < g (zig-zig)
    // if (g != null && p.key < g.key) {
    // top = rotateRight(g);
    // top = rotateRight(top);
    // if (!path.empty()) {
    // Node nextG = path.peek();
    // if (top.key < nextG.key)
    // nextG.left = top;
    // else
    // nextG.right = top;
    // }
    // System.out.println("zig-zig");
    // System.out.println(u.key + "<" + p.key + "<" + g.key);
    // // path.push(top);
    // }
    // // p > g (zig-zag)
    // else if (g != null && p.key > g.key) {
    // g.right = rotateRight(p);
    // top = rotateLeft(g);
    // System.out.println("zig-zag");
    // System.out.println(u.key + "<" + p.key + ">" + g.key);
    // // path.push(top);
    //
    // }
    // // no grandparent
    // else {
    // top = rotateRight(p);
    // System.out.println("zig, no g");
    // System.out.println(u.key + "<" + p.key);
    // // path.push(top);
    // }
    // }
    // // u > p
    // else if (u.key > p.key) {
    //
    // // p > g (zig-zig)
    // if (g != null && p.key > g.key) {
    // top = rotateLeft(g);
    // top = rotateLeft(top);
    // if (!path.empty()) {
    // Node nextG = path.peek();
    // if (top.key < nextG.key)
    // nextG.left = top;
    // else
    // nextG.right = top;
    // }
    // System.out.println("zig-zig");
    // System.out.println(u.key + ">" + p.key + ">" + g.key);
    // // path.push(top);
    // }
    // // p < g (zig-zag)
    // else if (g != null && p.key < g.key) {
    // g.left = rotateLeft(p);
    // top = rotateRight(g);
    // System.out.println("zig-zig");
    // System.out.println(u.key + ">" + p.key + "<" + g.key);
    // // path.push(top);
    //
    // }
    // // no grandparent (zig only)
    // else {
    // top = rotateLeft(p);
    // System.out.println("zig, no g");
    // System.out.println(u.key + ">" + p.key);
    // // path.push(top);
    // }
    // }
    // // Handler
    // else {
    // System.out.println("Error, keys in tree should not be identical");
    // return;
    // }
    //
    // path.push(top);
    // System.out.println(path);
    //
    // }
    //
    // }

    public void splay(Stack<Node> path) {
	// Splay node, either single or double rotation, determined
	// by inequalities with parent and grandparent nodes

	// Stack<Node> path = getPath(key);
	// Node u = new Node(key); // new node to be inserted

	Node u, p, g, top;
	while (!path.empty()) {
	    // Node u, p, g, top;
	    // u = p = g = top = null; // current, parent,
	    // grandparent, top, nodes
	    u = path.pop();

	    // Base case (root reached), set root to splayed element
	    if (path.empty()) {
		root = u;
		return;
	    }

	    p = path.pop();

	    g = null;
	    if (!path.empty())
		g = path.pop();

	    // u < p
	    if (u.key < p.key) {

		// p < g (zig-zig)
		if (g != null && p.key < g.key) {
		    top = rotateRight(g);
		    top = rotateRight(top);
		    if (!path.empty()) {
			Node nextG = path.peek();
			if (top.key < nextG.key)
			    nextG.left = top;
			else
			    nextG.right = top;
		    }
		    // System.out.println("zig-zig");
		    // System.out.println(u.key + "<" + p.key + "<" + g.key);
		    // path.push(top);
		}
		// p > g (zig-zag)
		else if (g != null && p.key > g.key) {
		    g.right = rotateRight(p);
		    top = rotateLeft(g);
		    if (!path.empty()) {
			Node nextG = path.peek();
			if (top.key < nextG.key)
			    nextG.left = top;
			else
			    nextG.right = top;
		    }
		    // System.out.println("zig-zag");
		    // System.out.println(u.key + "<" + p.key + ">" + g.key);
		    // path.push(top);

		}
		// no grandparent
		else {
		    top = rotateRight(p);
		    // System.out.println("zig, no g");
		    // System.out.println(u.key + "<" + p.key);
		    // path.push(top);
		}
	    }
	    // u > p
	    else if (u.key > p.key) {

		// p > g (zig-zig)
		if (g != null && p.key > g.key) {
		    top = rotateLeft(g);
		    top = rotateLeft(top);
		    if (!path.empty()) {
			Node nextG = path.peek();
			if (top.key < nextG.key)
			    nextG.left = top;
			else
			    nextG.right = top;
		    }
		    // System.out.println("zig-zig");
		    // System.out.println(u.key + ">" + p.key + ">" + g.key);
		    // path.push(top);
		}
		// p < g (zig-zag)
		else if (g != null && p.key < g.key) {
		    g.left = rotateLeft(p);
		    top = rotateRight(g);
		    if (!path.empty()) {
			Node nextG = path.peek();
			if (top.key < nextG.key)
			    nextG.left = top;
			else
			    nextG.right = top;
		    }
		    // System.out.println("zig-zig");
		    // System.out.println(u.key + ">" + p.key + "<" + g.key);
		    // // path.push(top);

		}
		// no grandparent (zig only)
		else {
		    top = rotateLeft(p);
		    // System.out.println("zig, no g");
		    // System.out.println(u.key + ">" + p.key);
		    // path.push(top);
		}
	    }
	    // Handler
	    else {
		System.out.println("Error, keys in tree should not be identical");
		return;
	    }

	    path.push(top);
	    // System.out.println(path);

	}
    }

    // else

    // public void insert(int key) {
    //
    // Stack<Node> path = new Stack<Node>();
    // Node u = new Node(key); // new node to be inserted
    //
    // // Empty tree, no need to splay
    // if (root == null) {
    // root = u;
    // return;
    // }
    //
    // /*
    // * Iterate from root down to insertion point, adding nodes to path
    // * stack, until null node, and then add new node with key
    // */
    // Node cur = root;
    // // path.push(cur);
    // while (true) {
    //
    // // Go left
    // if (key < cur.key) {
    // // cur = cur.left;
    //
    // if (cur.left == null)
    // break;
    //
    // cur = cur.left;
    // path.push(cur);
    //
    // }
    //
    // // Go right
    // else if (key > cur.key) {
    // cur = cur.right;
    // if (cur != null)
    // path.push(cur);
    //
    // }
    //
    // // Key already present
    // else
    // break;
    //
    // }
    //
    // // add new node at insertion point
    // cur = u;
    // // if (stack.)
    // }

    /**
     * Helper method for single right rotations
     * 
     * @param p
     *            parent
     * @return top new subtree
     */
    private Node rotateRight(Node p) {
	Node top = p.left;
	// System.out.println("parent lft" + p.left.key);

	p.left = top.right;
	// System.out.println("new parent lft" + p.left.key);

	top.right = p;
	// System.out.println("new top rt" + top.right.key);
	//
	// System.out.println("parent" + p.key);
	// // System.out.println("parent lft" + p.left.key);
	//
	// System.out.println("rot rt new top key " + top.key);
	return top;
    }

    /**
     * Helper method for single left rotations
     * 
     * @param p
     *            parent
     * @return top new subtree
     */
    private Node rotateLeft(Node p) {
	// System.out.println("parent rt" + p.right.key);
	// System.out.println("parent" + p.key);

	Node top = p.right;
	p.right = top.left;
	top.left = p;
	// System.out.println("new top lft" + top.left.key);
	//
	// System.out.println("rot lft new top key " + top.key);

	return top;
    }

    public void inorder() {
	System.out.println("\nKeys inorder:");
	inorder(root);
	System.out.println();
    }

    private void inorder(Node nd) {
	if (nd == null)
	    return;
	inorder(nd.left);
	System.out.print(nd.key + " ");
	inorder(nd.right);

    }

    public void preorder() {
	System.out.println("\nKeys preorder: ");
	preorder(root);
	System.out.println();
    }

    private void preorder(Node nd) {
	if (nd == null)
	    return;
	System.out.print(nd.key + " ");
	preorder(nd.left);
	preorder(nd.right);
    }

    public void postorder() {
	System.out.println("\nKeys postorder:");
	postorder(root);
	System.out.println();
    }

    private void postorder(Node nd) {
	if (nd == null)
	    return;
	postorder(nd.left);
	postorder(nd.right);
	System.out.print(nd.key + " ");
    }

    public double getAveCumPathLength() {
	return totalCumPathLength / totalFindOps;
    }

}
