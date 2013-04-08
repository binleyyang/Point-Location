/**Binley Yang
 * CSC 172
 * Project 3: Point Location
 * Email: byang8@u.rochester.edu
 * */

/**This class sets up the nodes and data for the binary search tree
 * */
public class TreeNode {

	public LineSegment line;
	public TreeNode leftchild;
	public TreeNode rightchild;
	public TreeNode parent;
	public TreeNode root;
	
	/**Node constructor with line as it's data
	 * */
	public TreeNode(LineSegment line){
		this.line = line;
		this.leftchild = null;
		this.rightchild = null;
		this.parent = null;
	}
	
	/**Prints the binary search tree in order
	 * */
	public void printInOrder() {
		if (leftchild != null)
			leftchild.printInOrder();
		
		System.out.println(line.p1.x + " " + line.p1.y + " " + line.p2.x + " " + line.p2.y);
		
		if (rightchild != null)
			rightchild.printInOrder();
	}

	/**
	 * Method to count the external nodes
	 * @return number of nodes
	 */
	public int count() {
		int c = 1;
		if (leftchild != null)
			c += leftchild.count();
		if (rightchild != null)
			c += rightchild.count();
		return c;
	}
	
	
	/**
	 * Gets the external path length
	 * @return external length
	 */
	public int externalPathLength() {
		return externalPathLengthAux(root, 0);
	}

	/**
	 * internal method to get external path length
	 * @param node
	 * @param depth
	 * @return depth
	 */
	private int externalPathLengthAux(TreeNode node, int depth) {
		if (node.rightchild == null && node.leftchild == null)  
			return depth;
		else
			return externalPathLengthAux(node.leftchild, depth+1) + externalPathLengthAux(node.rightchild, depth+1);
	}
}
