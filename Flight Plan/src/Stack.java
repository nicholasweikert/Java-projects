//generic stack to use with edges and vertices

public class Stack<E> {
	linkedlist<E> list = new linkedlist<E>();
	
	public E pop(){
		//get the object the tail is holding to return later
		E X = list.tail.data;
		//if we are popping the head, we can't null tail for the prev node
		if (list.tail != list.head)
			list.tail.prev.next = null;
		list.tail = null;
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
