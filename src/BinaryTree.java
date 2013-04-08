/**Binley Yang
 * CSC 172
 * Project 3: Point Location
 * Email: byang8@u.rochester.edu
 * */

/**This class builds the binary tree through the insert methods
 * */
public class BinaryTree {
	
	private TreeNode root;
	private final static int COUNTERCLOCKWISE = 1;
	private final static int CLOCKWISE = -1;
	private final static int COLLINEAR = 0;
	
	public BinaryTree() {}
	
	/**Prints the BST in order
	 * */
	public void printInOrder(){
		root.printInOrder();
	}
	
	public TreeNode getNode() {
		return root;
	}

	/**inserts line objects into the binary search tree
	 * @param line
	 * */
	public void insert(LineSegment line) {
		if (root == null) {		
			root = new TreeNode(line);
			//System.out.println(line.p1.x + " " + line.p1.y + " " +line.p2.x + " " + line.p2.y);
		} else {
			root = insert(line, root);
			//System.out.println(line.p1.x + " " + line.p1.y + " " +line.p2.x + " " + line.p2.y);
		}
	}
	
	/**private helper method to insert line objects into the binary search tree
	 * @param line
	 * @param node
	 * @return TreeNode
	 * */
	private TreeNode insert(LineSegment line, TreeNode n) {
		if (LineSegment.intersectbool(line, n.line)) {
			//System.out.println("split: "+line);
			if (n.leftchild != null) {
				//System.out.println("go left: "+line);
				n.leftchild = insert(line, n.leftchild);
			}
			else
				n.leftchild = new TreeNode(line);
			
			if (n.rightchild != null) {
				//System.out.println("go right: "+line);
				n.rightchild = insert(line, n.rightchild);
			}
			else
				n.rightchild = new TreeNode(line);
		}
		
		else if (ccw(line.p1, n.line.p1, n.line.p2) < 0) {
			//System.out.println("go right: "+line);
			if (n.rightchild != null){
				//System.out.println("go left: "+line);
				n.rightchild = insert(line, n.rightchild);
			}
			else
				n.rightchild = new TreeNode(line);
		}

		else if (ccw(line.p1, n.line.p1, n.line.p2) > 0) {
			//System.out.println("go left "+line);
			if (n.leftchild != null){
				//System.out.println("go left: "+line);
				n.leftchild = insert(line, n.leftchild);
			}
			else
				n.leftchild = new TreeNode(line);
		}
		return n;
	}
	
	/**Searches the tree for any two points that are separated by the line
	 * @param two points, node
	 * @return TreeNode
	 * */
	public TreeNode search(TreeNode node, Point p1, Point p2) {
		int a1 = ccw(p1, node.line.p1, node.line.p2); 
		int a2 = ccw(p2, node.line.p1, node.line.p2);
		
		if (a1 != a2) 
			return node;
		if (a1 == -1) {
			if (node.rightchild == null)
				return null;
			else
				search(node.rightchild, p1, p2);
		}
		if (a1 == 1) {
			if (node.leftchild==null)
				return null;
			else
				search(node.leftchild, p1, p2);
			}
		return null;
	}
	
	/**Determines which side of a line a point is on
	 * @param a point and the endpoints of a line
	 * @return direction of point
	 * */
	public static int ccw (Point p0, Point p1, Point p2) {
		double dx1 = Math.abs(p1.x - p0.x);
		double dy1 = Math.abs(p1.y - p0.y);
		double dx2 = Math.abs(p2.x - p0.x);
		double dy2 = Math.abs(p2.y - p0.y);
		if (dx1*dy2 > dy1*dx2) return COUNTERCLOCKWISE; 
		if (dx1*dy2 < dy1*dx2) return CLOCKWISE; 
		if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) return CLOCKWISE; 
		if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) return COUNTERCLOCKWISE;
		return COLLINEAR;
	}
}
