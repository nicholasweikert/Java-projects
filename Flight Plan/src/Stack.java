//generic stack to use with edges and vertices

public class Stack<E> {
	linkedlist<E> list = new linkedlist<E>();
	
	public E pop(){
		//get the object the tail is holding to return later
		E X = list.tail.data;
		//two cases: if we are popping the head or anywhere else
		//we can't null tail for the prev node if it is head
		if (list.tail == list.head){
			list.tail = null;
			list.head = null;
		}
		else{
			list.tail = list.tail.prev;
			list.tail.next = null;
		}
		return X;
	}
	
	public void push(E X){
		list.add(X);
	}
	
	public E peek(){
		if (list.tail != null)
			return list.tail.data;
		else
			return null;
	}
}
