/**
* The node class for a DoublyLinkedList class
* A DNode stores a GameScore element
*/

public class DNode {
	private GameScore element;		//Reference to the element stored at this node
	private DNode next;				//Reference to the successor node in the list
	private DNode prev;				//Reference to the predecessor node in the list

	//The Constructor method
	public DNode(GameScore s, DNode p, DNode n) {
		element = s;
		next = n;
		prev = p;
	}

	//ACCESS METHODS

	//Returns the GameScore element stored at the node
	public GameScore getElement() {
		return element;
	}

	//Returns the next node
	public DNode getNext() {
		return next;
	}

	//Returns the previous node
	public DNode getPrev() {
		return prev;
	}

	//UPDATE METHODS

	//Updates/sets the element field
	public void setElement(GameScore s) {
		element = s;
	}

	//Sets the next node field
	public void setNext(DNode n) {
		next = n;
	}

	//Sets the next node field
	public void setPrev(DNode p) {
		prev = p;
	}

	//Returns a string representation of the node
	public String toString() {
		return element.toString();		//Using the toString() method of GameScore class
	}

	//Testing the class in the main method
	public static void main(String[] args) {
		GameScore gs1 = new GameScore("Hasham", 20);
		GameScore gs2 = new GameScore("Nadia", 100);
		DNode n1 = new DNode(gs1,null,null);
		DNode n2 = new DNode(gs2,n1,null);

		n1.setNext(n2);
		System.out.println(n1); //Automatically calls the toString() method
		System.out.println(n2);

	}

}