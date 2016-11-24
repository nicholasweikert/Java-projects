//takes in a graph and a file reader and constructs the graph from the input file

import java.io.*;
import java.util.regex.Pattern;

public class GraphCreator{
	String nextline;
	int numOfEdges;
	
	public void createGraph(BufferedReader linereader, CityGraph myState) throws IOException {
		//first line will always be the number of edges to make
		numOfEdges = Integer.parseInt(linereader.readLine());
		//read the rest of the lines in the file
		while ((nextline = linereader.readLine()) != null){
			String[] params = nextline.split(Pattern.quote("|"));
			addset(params, myState);
		}
	}
	
	//helper method to construct the graph
	public static void addset(String[] params, CityGraph myState){
		Vertex origin = new Vertex(params[0]);
		Vertex destination = new Vertex(params[1]);
		myState.add(origin);
		myState.add(destination);
		myState.connect(origin, destination, Integer.parseInt(params[2]),
				Integer.parseInt(params[3]));
	}
}
