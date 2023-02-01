public class SparseVector {

	private Node head;
	private int length;

	public SparseVector(int len){
		head = null;
		length = len;
	}

	// Prints out a sparse vector (including zeros)
	public String toString(){

		String result = "";
		Node currNode = head;
		int currIndex = 0;
		while( currNode != null ){
			int idx = currNode.getIndex();

			// Pad the space between nodes with zero
			while( currIndex < idx ){
				result += "0, ";
				currIndex++;
			}
			result += currNode;
			currNode = currNode.getNext();
			currIndex++;

			// Only add a comma if this isn't the last element
			if( currNode != null ){ result += ", "; }
		}
		return result;
	}

	public void addElement(int index, double value){
		Node current = head;
		if (index >= length){
			System.out.println("Index is outside of the vector length");
		}
		else if (head == null){
			head = new Node(index, value);
		}
		else {
			while (current.getNext() != null){
				current = current.getNext();
			}
			Node n = new Node(index, value);
			current.setNext(n);
		}
	}


	public static double dot( SparseVector x, SparseVector y ){
		double result = 0;
		Node currX = x.head;
		Node currY = y.head;
		if (x.length == y.length){
			if (currX.getIndex() == currY.getIndex()) {
				while (currX != null && currY != null) {
						result += (currY.getValue() * currX.getValue());
						currX = currX.getNext();
						currY = currY.getNext();
				}
			}
		}
		return result;
	}



	// TODO: Test out your code here!
	public static void main(String[] args) {
		SparseVector x = new SparseVector(2);
		x.addElement(0, 2.0);
		x.addElement(1, 3.0);
		//x.addElement(10000001, -2.0);
		SparseVector y = new SparseVector(2);
		y.addElement(0, 12.0);
		y.addElement(1, -7.0);
		double result = dot(x, y);
		System.out.println(result);
	}
}


