/**Binley Yang
 * CSC 172
 * Project 3: Point Location
 * Email: byang8@u.rochester.edu
 * */

import java.util.*;

/**This class reads input from console, inserts data into a binary search tree, and searches for any intersection of two points
 * */
public class PointLocator {
	
	public static void main(String[] args) {
		
		LineSegment[] lines = makeLines();
		Point[][] points = makeComparisonPoints();
		BinaryTree BST = new BinaryTree();
		TreeNode[] result = new TreeNode[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			BST.insert(lines[i]);
		}
		
		int x = BST.getNode().count();
		int y = BST.getNode().externalPathLength();
		
		//System.out.println("Here's the tree:\n");
		//BST.printInOrder();	
		
		for (int i = 0; i < points.length; i++) {
			result[i] = BST.search(BST.getNode(), points[i][0], points[i][1]);
			if (result[i] == null)
				System.out.println("No");
			else if (result[i] != null)
				System.out.println("Yes: " + result[i].line.toString());
		}
		
		for (int i = 0; i < args.length; i++) {
			if (args[0].equals("-v")) {
				System.out.println("Number of External Nodes: " + x);
				//System.out.println("Average path Length: " + (double)x/y);
			}
		}
	}
	
	/** Reads input from console and returns an array of LineSegments
	 * @return array of line segments
	 * */
	public static LineSegment[] makeLines() {
		
		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		System.out.println("Input the desired amount of lines:");
		int a = scan1.nextInt();
		int b = 0;
		String[] input = new String[4];
		Double[][] lineArray = new Double[a][4];
				
		System.out.print("\nInput Lines in the following format: x1 y1 x2 y2 where 1 is the first point of a line and 2 is the second point\n");
		for (int i = a; i > 0; i--) {	
			String cordInput = scan2.nextLine();
			input = cordInput.split(" ");
			for (int j = 0; j < input.length; j++) {
				lineArray[b][j] = Double.parseDouble(input[j]);	
			}
			b++;
		}
		LineSegment[] lineSegmentArray = new LineSegment[a];
		for (int i = 0; i < lineSegmentArray.length; i++) {
			lineSegmentArray[i] = new LineSegment(lineArray[i][0], lineArray[i][1], lineArray[i][2], lineArray[i][3]);
		}	
		return lineSegmentArray;
	}
	
	/**Reads input from console and returns a 2D array of Points to be compared
	 * @return 2D array of points
	 * */
	public static Point[][] makeComparisonPoints() {
		Scanner scan1 = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		int b = 0;
		String[] input = new String[4];
		System.out.println("\nHow many comparisons of points would you like analyzed? "); 
		int c = scan1.nextInt();
		System.out.println("Enter points with the previous format:");
		Double[][] comparePoint = new Double[c][4];
		
		for (int k = c; k > 0; k--) {
			String info = scan2.nextLine();
			input = info.split(" ");
			for(int j = 0; j < input.length; j++){
				comparePoint[b][j] = Double.parseDouble(input[j]);	
			}
			b++;
		}
		
		b = 0;
		Point[][] pointArray = new Point[c][2];
		
		for (int i = 0; i < comparePoint.length; i++) {
			Point temp1 = new Point(comparePoint[i][0], comparePoint[i][1]);
			Point temp2 = new Point(comparePoint[i][2], comparePoint[i][3]);
			pointArray[i][0] = temp1;
			pointArray[i][1] = temp2;
		}
		return pointArray;
	}

}