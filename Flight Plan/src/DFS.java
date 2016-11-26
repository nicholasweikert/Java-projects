//create a temporary list of current traversed path (needs to incorporate iterative backtracking)
//if a route to destination is available, copy the route to the paths list
//needs to be sorted - can either sort by time(T) or cost(C) during creation or after

public class DFS {
	//container for all paths found for a single path request input
	//linkedlist<linkedlist<Vertex>> paths = new linkedlist<linkedlist<Vertex>>();
	//container for temporary path to destination
	Stack<Vertex> tempPath = new Stack<Vertex>();
	Stack<Vertex> traversal = new Stack<Vertex>();
	
	public void performSearch(linkedlist<linkedlist<Vertex>> paths, CityGraph myState, String[] params){
		//reset visited param for each node in the list
		while (tempPath.peek() != null){
			tempPath.pop().visited = false;
		}
		/* perform depth-first search on our graph
		 * backtrace tempPath during our search to find each individual path to the dest
		 * when a path is successfully found, copy the path list to the list of paths
		 */
		traversal.push(myState.getNode(params[0]));
		while (traversal.peek() != null){
			boolean visitable = false; //flag to see if we added any nodes to traversal stack
			Vertex V = traversal.pop();
			V.visited = true;
			tempPath.push(V);
			System.out.println(V.Name + "\n");
			Node<Edge> e = V.Adjacencies.head;
			while (e != null){
				if (e.data.dest.Name.equals(params[1])){
					//copy tempPath as a successful path to paths
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
				System.out.println("origin " + e.data.orig.Name + " dest " + e.data.dest.Name);
				e = e.next;
			}
			if (!visitable){
				System.out.println("error message");
				/*
				boolean backtracked = false;
				while (!backtracked){
					//cyclical route - we know that there is more routes to check
					//and want to avoid infinite repetition between adjacent nodes
					if (V.equals(traversal.peek())){
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
						}
						rev = rev.next;
					}
				}
				*/
			}
		}
	}
}
