//handles requests input file, finds requested paths, and outputs to a file
//uses DFS (Depth-First-Search) helper class to perform the search and return
//list of lists of resulting possible paths

import java.io.*;
import java.util.regex.Pattern;

public class RequestFlights {
	DFS requestedLine = new DFS();
	//container for all paths found for a single path request input
	linkedlist<linkedlist<Vertex>> paths = new linkedlist<linkedlist<Vertex>>();
	
	String nextline;
	int numOfCommands;
	int currRequest = 0;
	
	public void handleInfile(BufferedReader linereader, CityGraph myState, PrintWriter outfile) throws IOException{
		numOfCommands = Integer.parseInt(linereader.readLine());
		while ((nextline = linereader.readLine()) != null){
			currRequest++;
			String[] params = nextline.split(Pattern.quote("|"));
			//orig dest T/C
			paths = requestedLine.performSearch(myState, params);
			sort(paths, params, myState);
			sendToOutFile(myState, paths, params, currRequest, outfile);
			
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
	}
	
	//sorts each set by time or cost (T or C)
	public void sort(linkedlist<linkedlist<Vertex>> paths, String[] params, CityGraph myState){
		Node<linkedlist<Vertex>> currPath;
		/* 
		 * loop through every list of paths, tail to head, get the total (of time or cost)
		 * between two lists and compare. bubble the max 3 from the tail to the head
		 */
		//find the min in the 3 lists
		for (int i = 0; i < 3; i++){
			currPath = paths.tail;
			while (currPath.prev != null){
				//do a comparison to swap list positions
				//if curr list total time is less than the prev, swap
				if (getTotal(getEdgeList(myState, currPath.data), params[2]) 
						< getTotal(getEdgeList(myState, currPath.prev.data), params[2])){
					paths.swap(currPath, currPath.prev);
				}
				currPath = currPath.prev;
			}
		}
	}
	
	//writes result to output file
	public void sendToOutFile(CityGraph myState, linkedlist<linkedlist<Vertex>> paths, String[] params, 
			int currRequest, PrintWriter outfile){
		String timeOrCost = params[2];
		//switching the print statement (for a fully completed solution)
		switch (params[2]){
		case "T":
			timeOrCost = "Time";
			break;
		case "C":
			timeOrCost = "Cost";
			break;
		}
		outfile.println("Flight " + currRequest + ": " + params[0] + ", " + params[1] + " ("
				+ timeOrCost + ")");

		//send to outfile the top 3 paths (already sorted)
		Node<linkedlist<Vertex>> currList = paths.head;
		if (currList == null){
			outfile.print("No paths were found between the origin " + params[0]
					+ " and the destination " + params[1]);
		}
		for (int i = 0; i < 3; i++){
			if (currList != null){
				//get the edgelist from the vertex list in paths
				linkedlist<Edge> edgeList = getEdgeList(myState, currList.data);
				outfile.print("Path " + (i+1) + ": " );
				Node<Edge> E = edgeList.head;
				outfile.print(params[0]);
				while (E != null){
					outfile.print(" -> " + E.data.dest.Name);
				E = E.next;
				}
				outfile.println(". Time: " + (int)getTotal(edgeList, params[2])
				+ " Cost: " + String.format("%.2f", getTotal(edgeList, params[2])));
			}
			//chicago-> florida-> houston ->dallas
			else
				break;
			currList = currList.next;
		}
	}
	
	public linkedlist<Edge> getEdgeList(CityGraph myState, linkedlist<Vertex> currList){
		Node<Vertex> temp = currList.head;
		linkedlist<Edge> edgeList = new linkedlist<Edge>();
		//traverse each path and add all of the connecting edges to the list we are returning
		while (temp.next != null){
			Node<Edge> e = temp.data.Adjacencies.head;
			//check each edge in the adjacencies list for the correct edge to add
			while (e != null){
				if (e.data.dest.Name.equals(temp.next.data.Name))
					edgeList.add(e.data);
				e = e.next;
			}
			temp = temp.next;
		}
		return edgeList;
	}

	//helper method to get total (time or cost) in a single edge list
	public double getTotal(linkedlist<Edge> edgeList, String timeOrCost){
		double temp = 0;
		Node<Edge> edge = edgeList.head;
		switch (timeOrCost){
		case "T":
			while (edge != null){
				temp += edge.data.Time;
				edge = edge.next;
			}
			break;
		case "C":
			while (edge != null){
				temp += edge.data.Cost;
				edge = edge.next;
			}
		}
		return temp;
	}
}
