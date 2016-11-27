//very generic linkedlist that can be used with vertices or edges

public class linkedlist<E> {
	public Node<E> head;
	public Node<E> tail;
	
	//unnecessary, but assert that head and tail are null
	linkedlist(){
		this.head = null;
		this.tail = null;
	}
	
	//set head and tail of the list
	linkedlist(E V){
		Node<E> newNode = new Node<E>(V);
		this.head = newNode;
		this.head.next = null;
		this.head.prev = null;
		this.tail = newNode;
		this.tail.next = null;
		this.tail.prev = null;
	}
	
	public void add(E V){
		Node<E> newNode = new Node<E>(V);
		//if list is empty, add the node to be head and tail
		if (this.head == null){
			this.head = newNode;
			this.head.prev = null;
			this.head.next = null;
			this.tail = newNode;
			this.tail.next = null;
			this.tail.prev = null;
		}
		//otherwise add the vertex to the tail of the list
		else{
			this.tail.next = newNode;
			this.tail.next.prev = this.tail;
			this.tail.next.next = null;
			this.tail = this.tail.next;
		}
	}
	
	public boolean contains (E V){
		Node<E> Y = this.head;
		//if the node requested is in the list, return true
		while (Y != null){
			if (Y.data == V)
				return true;
			Y = Y.next;
		}
		//if the node is not found, return false
		return false;
	}
	
	public int size(){
		Node<E> temp = head;
		int ret=0;
		while (temp != null){
			ret++;
			temp = temp.next;
		}
		return ret;
	}
	
	public void swap(Node<E> L1, Node<E> L2){
		Node<E> tmp = L1;
		L1 = L2;
		L2 = tmp;
	}
}
