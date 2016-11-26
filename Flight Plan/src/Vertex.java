//the object that represents each city
//has data fields for name of the city, a flag for use in the DFS to plan flights,
//and an adjacency list of edges representing what cities the object connects to

public class Vertex {
	public String Name;
	public boolean visited = false;
	public linkedlist<Edge> Adjacencies = new linkedlist<Edge>();
	
	//all vertices should be created with a name
	Vertex(String V){
		this.Name = V;
	}
}
