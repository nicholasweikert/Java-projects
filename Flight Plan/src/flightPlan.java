//Nicholas Weikert
//naw140030
//CE 3345
//describe the project

import java.io.*;
//origin+dest file used to create graph
//requested flight used in dfs to find if a path exists + min cost/time
//paths must be non-cyclical
//graph is undirected
//output to file ordered by time or cost indicated by T or C


public class flightPlan {
	//create the graph with first file
	//while reading in the names of cities, create a list to reference
	//for requested part, have each node contain a visited flag
	//set every city to visited = false while traversing list to start
	//add start city to stack
	//BFS through graph to find ALL paths to destination
	//store a list of lists of successful paths to destination
	//may be able to use either edges or nodes
	public static void main(String[] args) {
		CityGraph myState = new CityGraph();			//graph containing vertices and edges
		
		GraphCreator newGraph = new GraphCreator();		//creator object that reads in city data from file
														//and constructs graph's vertices & edge connections
		
		RequestFlights planner = new RequestFlights();	//finds all the paths in a graph from origin to destination
		
		BufferedReader linereader = null;				//file reader
		
		PrintWriter outfile = null;						//output file
		
		/*error handling for:
		 *opening orig/dest file
		 *creating a graph from the infile
		 *closing the file after graph is made
		 */
		try{
		linereader = new BufferedReader(new FileReader("./bin/cityinfo.txt"));
		newGraph.createGraph(linereader, myState);
		linereader.close();
		}
		catch (IOException e){
			System.out.println("One of two errors occurred:\n"
					+ "\t1. The origin/destination file was not found.\n "
					+ "\t2. There was an error in creating the graph.\n"
					+ "Check if the file name is correct and is in the right place.\n"
					+ "Exiting w/error code.");
			e.printStackTrace();
			return;
		}
		
		/* error handling for:
		 * opening flight requests file
		 * performing a search in the file to find all paths
		 * Closing the file after all paths have been found
		 */
		try{
		linereader = new BufferedReader(new FileReader("./bin/flightrequests.txt"));
		outfile = new PrintWriter("./bin/EfficientPaths.txt", "UTF-8");
		planner.handleInfile(linereader, myState, outfile);
		linereader.close();
		outfile.close();
		}
		catch (IOException e){
			System.out.println("One of two errors occurred:\n"
					+ "\t1. The flight requests file was not found.\n "
					+ "\t2. There was an error in finding flight paths.\n"
					+ "Check if the file name is correct and is in the right place.\n"
					+ "Exiting w/error code.");
			e.printStackTrace();
			return;
		}
		/*
		Node<Vertex> V = myState.nodeList.head;
		while (V != null){
			Node<Edge> adj = V.data.Adjacencies.head;
			Vertex vert = V.data;
			System.out.println(vert.Name + "'s adjacent vertices:");
			while (adj != null){
				Edge edge = adj.data;
				System.out.println("origin: " + edge.orig.Name + " destination: " + edge.dest.Name
						+ " cost: " + edge.cost + " time: " + edge.time);
				adj = adj.next;
			}
			V = V.next;
		}
		*/
	}
}