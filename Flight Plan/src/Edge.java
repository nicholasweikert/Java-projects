//the object that connects two vertices
//holds data for cost of flight and time of travel

public class Edge {
	public Vertex orig;
	public Vertex dest;
	public double Cost;
	public double Time;
	
	//constructor we should use since we will always be creating an edge with
	//its data available
	Edge(Vertex orig, Vertex dest, double cost, double time){
		this.orig = orig;
		this.dest = dest;
		this.Cost = cost;
		this.Time = time;
	}
}
