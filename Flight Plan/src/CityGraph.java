//holder class for the city names and their edges, including their costs and time to travel

public class CityGraph {
	//list of cities in the graph
	public linkedlist<Vertex> nodeList = new linkedlist<Vertex>();
	
	
	CityGraph(){
		//we don't need to do anything without a vertex
	}
	
	CityGraph(Vertex V){
		//this is a constructor -- the list is empty
		//simply add the first vertex to the nodeList
		nodeList.add(V);
	}

	
	//adds a vertex and returns false if the vertex already exists
	public boolean add(Vertex V){
		//Check if the vertex is in the list, otherwise add a new vertex
		if (!this.contains(V)){
			nodeList.add(V);
			return true;
		}
		else
			return false;
	}
	
	//adds an edge between two vertices with their cost and time to travel
	public void connect(Vertex orig, Vertex dest, double cost, double time){
		getNode(orig.Name).Adjacencies.add(new Edge(orig, dest, cost, time));
		getNode(dest.Name).Adjacencies.add(new Edge(dest, orig, cost, time));
	}
	
	//returns the node for the string we need to use
	public Vertex getNode(String V){
		if (nodeList.head != null){
			Node<Vertex> temp = nodeList.head;
			while (temp != null){
				if (temp.data.Name.equals(V))
					return temp.data;
				temp = temp.next;
			}
		}
		//return null if this city does not exist in graph
		return null;
	}
	
	//returns true if the graph contains the city
	public boolean contains(Vertex V){
		//city name to find is V.Name
		//search the list for the city name, if not found then return false
		if(!(nodeList.head == null)){
			Node<Vertex> temp = nodeList.head;
			while (temp != null){
				if (temp.data.Name.equals(V.Name))
					return true;
				temp = temp.next;
			}
		}
		return false;
	}
}
