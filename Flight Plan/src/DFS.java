//create a temporary list of current traversed path (incorporates iterative backtracking)
//if a route to destination is available, copy the route to the paths list
//needs to be sorted - can either sort by time(T) or cost(C) after paths are found

/*					Dallas
 * 				/			\
 * 			Austin	-----	Houston
 * 				|	   X	|
 * 			Chicago	-----	Florida
 * 
 * 	Dallas---->Houston	DH	DAH	DACH	DACFH	DAFH	DAFCH
 * 	Chicago--->Dallas
 * austin 
 */

public class DFS {
	//container for temporary path to destination
	Stack<Vertex> tempPath = new Stack<Vertex>();
	//stack for traversing graph
	Stack<Vertex> traversal = new Stack<Vertex>();
	
	public void performSearch(linkedlist<linkedlist<Vertex>> paths, CityGraph myState, String[] params){
		//removing any excess data stored in the DFS object from past searches
		//can replace this by creating new stacks to use, java's garbage collection
		//would handle the junk
		while (tempPath.peek() != null)
			tempPath.pop();
		
		while (traversal.peek() != null)
			traversal.pop();
		
		for (Node<Vertex> tmp = myState.getNode(params[0]); tmp != null; tmp = tmp.next){
			tmp.data.canVisit = 0;
			tmp.data.visited = 0;
		}
		
		
		/* perform depth-first search on our graph
		 * backtrace tempPath during our search to find each individual path to the dest
		 * when a path is successfully found, copy the path list to the list of paths
		 */
		traversal.push(myState.getNode(params[0]).data);
		Node<Vertex> V = null;
		while (traversal.peek() != null){
			boolean visitable = false; //flag to see if we added any nodes to traversal stack
			
			//I ran into a really strange error (which might have resulted from no masking)
			//any time i was updating the vertex, i had to use my graph's getnode method
			//otherwise, traversal.pop() would sometimes not return the correct node
			V = myState.getNode(traversal.pop().Name);
			tempPath.push(V.data);
			//System.out.println("\n" + V.data.Name + "\n");
			Node<Edge> e = V.data.Adjacencies.head;
			while (e != null){
				if (e.data.dest.Name.equals(params[1])){
					//copy tempPath as a successful path to paths
					//System.out.println("\nADDING PATH");
					tempPath.push(e.data.dest);
					linkedlist<Vertex> tempList = new linkedlist<Vertex>();
					paths.add(tempList);
					//System.out.print("PATH: ");
					for (Node<Vertex> tmp = tempPath.list.head; tmp != null; tmp = tmp.next){
						tempList.add(tmp.data);
						//System.out.print(tmp.data.Name + " ");
					}
					//System.out.println("\n");
					tempPath.pop();
				}
				else if (!tempPath.list.contains(myState.getNode(e.data.dest.Name).data)){
					traversal.push(myState.getNode(e.data.dest.Name).data);
					visitable = true;
					V.data.canVisit++;
				}
				//System.out.println("  origin " + e.data.orig.Name + " dest " + e.data.dest.Name);
				e = e.next;
			}
			System.out.println(V.data.canVisit + " " + V.data.visited);
			//System.out.println("visitable: " + visitable + " " + traversal.list.size());
			//if we were unable to traverse further, backtrack until we can continue
			//while we are backtracing, set visited to true on each node to avoid 
			//tracing over the same path twice
			if (!visitable){
				System.out.println("BACKTRACKING");
				boolean backtracked = false;
				while (!backtracked){
					System.out.println("start: tempPath size: " + tempPath.list.size()
					+ " traversal size: " + traversal.list.size());
					//base case - when we have finished looking at all routes
					if (traversal.peek() == null)
						return;
					//cyclical route - we know that there is more routes to check
					//and want to avoid infinite repetition between adjacent nodes
					if (traversal.peek().equals(tempPath.peek())){
						Vertex tmp = tempPath.pop();
						tmp.canVisit = 0;
						tmp.visited = 0;
						tmp = tempPath.pop();
						tmp.canVisit = 0;
						tmp.visited = 0;
					}
					V = myState.getNode(tempPath.pop().Name);
					//we finished visiting this path
					V.data.visited++;
					Node<Edge> rev = V.data.Adjacencies.head;
					while (rev != null){
						//if our next destination is visitable, we stop
						if (rev.data.dest.Name.equals(traversal.peek().Name)){
							backtracked = true;
							if (V.data.visited < V.data.canVisit){
								tempPath.push(V.data);
							}
							else{
								V.data.visited = 0;
								V.data.canVisit = 0;
							}
						}
						//otherwise, we continue backtracing
						rev = rev.next;
					}//end inner while
				}//end outer while
			}//end if (!visitable)
		}//end search for paths loop
	}//end performSearch method
}//end of file
