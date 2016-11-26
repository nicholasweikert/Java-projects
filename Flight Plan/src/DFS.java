//create a temporary list of current traversed path (needs to incorporate iterative backtracking)
//if a route to destination is available, copy the route to the paths list
//needs to be sorted - can either sort by time(T) or cost(C) during creation or after

/*					Dallas
 * 				/			\
 * 			Austin	-----	Houston
 * 				|	   X	|
 * 			Chicago	-----	Florida
 * 
 * 	Dallas---->Houston
 * 	Chicago--->Dallas
 */

public class DFS {
	//container for temporary path to destination
	Stack<Vertex> tempPath = new Stack<Vertex>();
	//stack for traversing graph
	Stack<Vertex> traversal = new Stack<Vertex>();
	
	public void performSearch(linkedlist<linkedlist<Vertex>> paths, CityGraph myState, String[] params){
		//reset visited param for each node in the list
		Node<Vertex> temp = myState.nodeList.head;
		while (temp != null){
			temp.data.visited = false;
			temp = temp.next;
		}
		//removing any excess data stored in the DFS object from past searches
		while (tempPath.peek() != null)
			tempPath.pop();
		
		while (traversal.peek() != null)
			traversal.pop();
		
		
		/* perform depth-first search on our graph
		 * backtrace tempPath during our search to find each individual path to the dest
		 * when a path is successfully found, copy the path list to the list of paths
		 */
		traversal.push(myState.getNode(params[0]).data);
		Node<Vertex> V = null;
		while (traversal.peek() != null){
			boolean visitable = false; //flag to see if we added any nodes to traversal stack
			V = myState.getNode(traversal.pop().Name);
			V.data.visited = true;
			tempPath.push(V.data);
			System.out.println(V.data.Name + "\n");
			Node<Edge> e = V.data.Adjacencies.head;
			while (e != null){
				if (e.data.dest.Name.equals(params[1])){
					//copy tempPath as a successful path to paths
					System.out.println("ADDING PATH");
					tempPath.push(e.data.dest);
					linkedlist<Vertex> tempList = new linkedlist<Vertex>();
					paths.add(tempList);
					for (Node<Vertex> tmp = tempPath.list.head; tmp != null; tmp = tmp.next){
						tempList.add(tmp.data);
					}
					tempPath.pop();
				}
				else if (!(e.data.dest.visited)){
					//add dest vertex to the stack and continue
					traversal.push(e.data.dest);
					visitable = true;
				}
				System.out.println(e.data.dest.visited + " origin " + e.data.orig.Name + " dest " + e.data.dest.Name);
				e = e.next;
			}
			System.out.println(traversal.list.size());
			//if we were unable to traverse further, backtrack until we can continue
			if (!visitable){
				System.out.println("BACKTRACKING");
				/*
				boolean backtracked = false;
				while (!backtracked){
					//base case - when we have finished looking at all routes
					if (traversal.peek() == null){
						while (tempPath.peek() != null)
							tempPath.pop().visited = false;
						return;
					}
					//cyclical route - we know that there is more routes to check
					//and want to avoid infinite repetition between adjacent nodes
					if (tempPath.peek().equals(traversal.peek())){
						tempPath.pop().visited = false;
						tempPath.pop().visited = false;
					}
					V = tempPath.pop();
					V.visited = false;
					Node<Edge> rev = V.Adjacencies.head;
					while (rev != null){
						if (rev.data.dest.equals(traversal.peek())){
							backtracked = true;
							V.visited = true;
							tempPath.push(V);
						}
						rev = rev.next;
					}
				}
				*/
			}
		}
	}
}
