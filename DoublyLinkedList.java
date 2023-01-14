/**
* A DoublyLinkedList class, holding a list of DNodes
* A DNode stores a GameScore element
*/

public class DoublyLinkedList {
	//Instance Variables
	private DNode header;		//The header sentinel DNode
	private DNode trailer;		//The trailer sentinel DNode
	private int size;			//The number of nodes in the list

	//Constructs an initially empty list
	//Creates and links the sentinel nodes to each other
	public DoublyLinkedList() {
		header = new DNode(null, null, null);		//create header
		trailer = new DNode(null, header, null);	//trailer is preceded by header
		header.setNext(trailer);					//header is followed by trailer
		size = 0;
	}

	//ACCESS METHODS

	//Returns the size, number of nodes in the list
	public int size() {
		return size;
	}

	//Returns true if the list is empty, false otherwise
	public boolean isEmpty() {
		return size == 0;
	}

	//Returns (but doesn't remove) the first element
	//That's the element stored at the node after the header
	public GameScore first() {
		return header.getNext().getElement();
	}

	//Returns (but doesn't remove) the last element
	//That's the element stored at the node before the trailer
	public GameScore last() {
		return trailer.getPrev().getElement();
	}

	//UPDATE METHODS

	//Adds the newScore element to the front of the list
	public void addFirst(GameScore newScore) {
		addBetween(newScore, header, header.getNext());
	}

	//Adds the newScore element to the end of the list
	public void addLast(GameScore newScore) {
		addBetween(newScore, trailer.getPrev(), trailer);
	}

	//Removes and returns the first element of the list
	public GameScore removeFirst() {
		if (isEmpty()) return null;

		return remove(header.getNext());
	}

	//Removes and returns the last element of the list
	public GameScore removeLast() {
		if (isEmpty()) return null;

		return remove(trailer.getPrev());
	}


	/**
	 * Displays the list contents
	 */
	public void display() {
		DNode current = header.getNext();		//Start with the node after header, potential first node
		//Walk down the list until you reach the trailer node
		while (current.getElement() != null) {      			// for each node,
			System.out.print(current + " ");  					// display it using the DNode.toString() method
			current = current.getNext();						//Fetch the next node in the list
		}
		System.out.println("");
	}




	//---------------------  OUR PRIVATE HELPER METHODS  --------------------

	//Adds the newScore in between the given nodes
	private void addBetween(GameScore newScore, DNode prevNode, DNode nextNode) {
		DNode newNode = new DNode(newScore, prevNode, nextNode);	//Create and link the new node
		//Update the given nodes to link to the new node
		prevNode.setNext(newNode);
		nextNode.setPrev(newNode);
		size++;
	}

	//Removes the given node from the list and returns its element
	private GameScore remove(DNode xNode) {
		//Get the nodes on either side of xNode
		DNode prevNode = xNode.getPrev();
		DNode nextNode = xNode.getNext();
		//Link prevNode and nextNode to each other, essentially removing xNode from the list
		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);
		size--;
		return xNode.getElement();
	}

	//--------------------------- HW5 METHODS ----------------------------------------------

	/**
	 * Checks to see if a given GameScore is contained in the linked list
	 * @param target - A GameScore object to be searched for
	 * @return - the DNode containing the target GameScore if found, null otherwise
	 * @author Annika Hoag
	 */
	public DNode find(GameScore target) {
		DNode current = header.getNext();

		while (current.getElement() != null){				//loop until you've reached trailer (null for element)
			
			if (current.getElement().equals(target)){		//return node if its element equals target
				return current;
			}

			current = current.getNext();					//else reset current to its next
		}//closes while loop

		return null;										//if not found return null
	}


	/**
	 * Adds a new GameScore object into the linked list
	 * The list is sorted in decreasing order by score, this method creates a DNode with the given GameScore
	 * 		and then inserts it in the appropriate spot in the already-sorted list
	 * @param newScore - A GameScore object to be added to the list
	 * @author Annika Hoag
	 */
	public void add(GameScore newScore){
		
		if (isEmpty()){						//if list is empyty...
			this.addFirst(newScore);		//add as first node
		
		}else{

			DNode current = header.getNext();
			DNode ahead = current.getNext();

			//test for list of size 1
			if (size ==1){

				//test if newScore should go after the only node
				if (current.getElement().getScore() >= newScore.getScore() ){
					this.addBetween(newScore, current, ahead);

				//else it goes in the first spot	
				}else{
					this.addFirst(newScore);
				}
	

			//test if newScore belongs at the beginning of the list
			}else if (newScore.getScore() >= current.getElement().getScore()){
				this.addFirst(newScore);
			

			}else{

				while (ahead.getNext() != trailer){						//loop until ahead is last node in list	
					

					//test if newScore goes in btwn current and head
					if (current.getElement().getScore() >= newScore.getScore()
						 && ahead.getElement().getScore() < newScore.getScore() ){ 

						this.addBetween(newScore, current, ahead);
						break;											//stop looping if added 

					}else{
						current=ahead;									//reset current to be ahead				
						ahead=ahead.getNext();							//reset ahead to be its next
					}

				}//closes while

				//if finished loop...
				if (ahead.getNext() == trailer){
					
					//test if newScore goes btwn last 2 nodes
					if (current.getElement().getScore() >= newScore.getScore()
						&& ahead.getElement().getScore() < newScore.getScore()){

						this.addBetween(newScore, current, ahead);

					//test if newScore should be the last node by being less than current last
					}else if (newScore.getScore() < ahead.getElement().getScore()){
						this.addLast(newScore);
					}
				} 

			}//closes inner else	

		}//closes outer else

	}


	/**
	 * Deletes a given GameScore entry from the linked list
	 * @param gameScore - A GameScore object to be searched for and deleted
	 * @return the DNode containing the target GameScore if search and deletion was   successful, null otherwise.
	 * @author Annika Hoag
	 */
	public DNode delete(GameScore gameScore) {
		DNode foundNode = this.find(gameScore);		//create a variable equal to the node containing gameScore
		this.remove(foundNode);						//remove that node
		return foundNode;							//return variable to node
	}



	//testing in main methid
	public static void main(String[] args){
		//creates an instance of doubly linked list class
		DoublyLinkedList scoreBoard = new DoublyLinkedList();

		//adding GameScore objects
		GameScore player1 = new GameScore("Erin", 50);
		GameScore player2 = new GameScore("David", 67);
		GameScore player3 = new GameScore("Annika", 98);
		GameScore player4 = new GameScore("Diane", 77);
		GameScore player5 = new GameScore("Carter", 1000);

		scoreBoard.add(player3);
		scoreBoard.add(new GameScore("Erica", 81));
		scoreBoard.add(player2);
		scoreBoard.add(player5);
		scoreBoard.add(player1);
		scoreBoard.add(new GameScore("Carol", 126));
		scoreBoard.add(new GameScore("Bob", 90));
		scoreBoard.add(player4);
		scoreBoard.add(new GameScore("Bill", 90));
		scoreBoard.add(new GameScore("Lincoln", 72));


		//display
		System.out.println("\nThis is what the doubly linked list looks like: ");
		scoreBoard.display();

		//testing find
		System.out.println("\nFound " + scoreBoard.find(player1));
		System.out.println("Found " + scoreBoard.find(player2));

		//testing remove
		System.out.println("\nRemoved " + scoreBoard.delete(player3));
		System.out.println("Removed " + scoreBoard.delete(player4));
		System.out.println("Removed " + scoreBoard.delete(player1));
		System.out.println("Removed " + scoreBoard.delete(player5));

		//display
		System.out.println("\nThis is what the singly linked list looks like now: ");
		scoreBoard.display();

	}//closes main method



}//closes DoublyLinkedList