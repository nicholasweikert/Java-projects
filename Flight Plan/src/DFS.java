//create a temporary list of current traversed path (needs to incorporate iterative backtracking)
//if a route to destination is available, copy the route to the paths list
//needs to be sorted - can either sort by time(T) or cost(C) during creation or after

public class DFS {
	//container for all paths found for a single path request input
	linkedlist<linkedlist<Vertex>> paths = new linkedlist<linkedlist<Vertex>>();
	//container for temporary path to destination
	Stack<Vertex> tempPath = new Stack<Vertex>();
	Stack<Vertex> traversal = new Stack<Vertex>();
	
	public linkedlist<linkedlist<Vertex>> performSearch(CityGraph myState, String[] params){
		traversal.push(myState.getNode(params[0]));
		//reset visited param for each node in the list
		Node<Vertex> temp = myState.nodeList.head;
		while (temp != null){
			temp.data.visited = false;
			temp = temp.next;
		}
		/* perform depth-first search on our graph
		 * backtrace tempPath during our search to find each individual path to the dest
		 * when a path is successfully found, copy the path list to the list of paths
		 */
		while (traversal.peek() != null){
			boolean visitable = false;
			Vertex V = traversal.pop();
			V.visited = true;
			tempPath.push(V);
			Node<Edge> e = V.Adjacencies.head;
			while (e != null){
				if (e.data.dest.Name == params[1]){
					//copy tempPath as a successful path to paths
					paths.add(tempPath.list);
				}
				else if (!(e.data.dest.visited)){
					//add dest vertex to the stack and continue
					traversal.push(e.data.dest);
					visitable = true;
				}
				e = e.next;
			}
			if (!visitable){
				//backtrack to the top of the stack
				tempPath.pop();
			}
		}
		return paths;
	}
}
