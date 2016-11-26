//handles requests input file, finds requested paths, and outputs to a file
//uses DFS (Depth-First-Search) helper class to perform the search and return
//list of lists of resulting possible paths

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class RequestFlights {
	DFS requestedLine = new DFS();
	//container for all paths found for a single path request input
	linkedlist<linkedlist<Vertex>> paths = new linkedlist<linkedlist<Vertex>>();
	
	String nextline;
	int numOfCommands;
	
	public void handleInfile(BufferedReader linereader, CityGraph myState) throws IOException{
		numOfCommands = Integer.parseInt(linereader.readLine());
		while ((nextline = linereader.readLine()) != null){
			String[] params = nextline.split(Pattern.quote("|"));
			System.out.println("check: " + params[0]);
			//orig dest T/C
			requestedLine.performSearch(paths, myState, params);
			//we expect a set of paths
			//sort(paths);
			//sendToOutFile(paths);

			}
		//checking data contained in paths
		Node<linkedlist<Vertex>> temp = paths.head;
		while (temp != null){
			Node<Vertex> V = temp.data.head;
			while (V != null){
				System.out.print(V.data.Name + " ");
				V = V.next;
			}
			System.out.println();
			temp = temp.next;
		}
	}
	
	
	//sorts each set by time or cost (T or C)
	public void sort(linkedlist<linkedlist<Vertex>> paths){
		Node<linkedlist<Vertex>> currPath = paths.head;
		while (currPath != null){
			
			currPath = currPath.next;
		}
	}
	
	//writes result to output file
	public void sendToOutFile(linkedlist<linkedlist<Vertex>> paths){
		
	}
}
