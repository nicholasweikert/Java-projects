//the object that represents each city
//has data fields for name of the city, a flag for use in the DFS to plan flights,
//and an adjacency list of edges representing what cities the object connects to

public class Vertex {
	public String Name;
	public int visited; //how many edges in adjacencies have been traversed
	public int canVisit; //how many edges have not been traversed when visited
	public linkedlist<Edge> Adjacencies = new linkedlist<Edge>();
	
	//all vertices should be created with a name
	Vertex(String V){
		this.Name = V;
	}
}
