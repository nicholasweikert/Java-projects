//very generic node
//data field will hold the vertex or edge object

public class Node<E> {
	public E data;
	public Node<E> prev;
	public Node<E> next;
	
	Node(E X){
		this.data = X;
	}
}
