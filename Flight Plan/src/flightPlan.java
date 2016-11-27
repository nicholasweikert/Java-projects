//Nicholas Weikert
//naw140030
//CE 3345
/* This project uses a depth-first search to find all routes in a graph from origin
 * to destination. Steps of the project are as follows:
 * 
 * origin+dest file used to create graph
 * requested flight file used in dfs to find any paths that exist + min cost/time
 * 		paths are non-cyclical
 * 		graph is undirected
 * sort the top 3 paths by time or cost indicated by T or C
 * output to file
 */

import java.io.*;


public class flightPlan {
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
		linereader = new BufferedReader(new FileReader(args[0]));
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
		 * opening output file
		 * performing a search in the file to find all paths
		 * 		sorting paths that have been found
		 * 		writing to output file
		 * Closing the files after all paths have been found
		 */
		try{
		linereader = new BufferedReader(new FileReader(args[1]));
		outfile = new PrintWriter(args[2], "UTF-8");
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
	}
}