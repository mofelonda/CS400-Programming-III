import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Filename:   GraphImpl.java
 * Project:    p4
 * Course:     cs400 
 * Authors:    Daewon Lee
 * Due Date:   November 19, 2018
 * 
 * T is the label of a vertex, and List<T> is a list of
 * adjacent vertices for that vertex.
 *
 * Additional credits: 
 *
 * Bugs or other notes: 
 *
 * @param <T> type of a vertex
 */
public class GraphImpl<T> implements GraphADT<T> {

    // YOU MAY ADD ADDITIONAL private members
    // YOU MAY NOT ADD ADDITIONAL public members

    /**
     * Store the vertices and the vertices' adjacent vertices
     */
    private Map<T, List<T>> verticesMap; 
    private int numEdges = 0;
    
    /**
     * Construct and initialize and empty Graph
     */ 
    public GraphImpl() {
        verticesMap = new HashMap<T, List<T>>();
        // you may initialize additional data members here
    }

    public void addVertex(T vertex) {
    	//Checks if vertex is null
    	if(vertex.equals(null)) 
    		return;
    	//Checks if vertex doesn't already exist
    	if(verticesMap.containsKey(vertex))
    		return;
        
    	verticesMap.put(vertex, new ArrayList<T>());
    }

    public void removeVertex(T vertex) {
        //Checks if vertex is null
    	if(vertex.equals(null))
        	return;
    	//Checks if vertex exists in graph
        if(verticesMap.containsKey(vertex)) {
        	verticesMap.remove(vertex);
        } else {
        	//return;
        }
        for(T remVertex : verticesMap.keySet()) {
       		for(T edges : verticesMap.get(remVertex)) {
       			if(edges.equals(vertex)) {
       				verticesMap.get(remVertex).remove(vertex);
       				break;
       			}
       		}
        	
       	}


    }

    public void addEdge(T vertex1, T vertex2) {
        //Checks if either vertex is null
    	if(vertex1.equals(null) || vertex2.equals(null)) 
        	return;
    	//Checks if vertices exists in graph
        if(verticesMap.containsKey(vertex1) && verticesMap.containsKey(vertex2)) {
        	//Checks if edge exists
        	if(verticesMap.get(vertex1).contains(vertex2)) {
        		return; 
        	} else {
        		verticesMap.get(vertex1).add(vertex2);
        		numEdges++;
        	}
        } else {
        	return; //If vertices doesn't exist
        }
        
    }
    
    public void removeEdge(T vertex1, T vertex2) {
        //Checks if either vertex is null
    	if(vertex1.equals(null) && vertex2.equals(null))
        	return;
    	//Checks if vertices exists in graph
        if(verticesMap.containsKey(vertex1) && verticesMap.containsKey(vertex2)) {
        	//Checks if edge exists
        	if(verticesMap.get(vertex1).contains(vertex2)) {
        		verticesMap.get(vertex1).remove(vertex2);
        		numEdges--;
        	} else {
        		return; //If edge doesn't exist
        	}
        } else {
        	return; // If vertices are doesn't exist
        }
    }    
    
    public Set<T> getAllVertices() {
        return verticesMap.keySet();
    }

    public List<T> getAdjacentVerticesOf(T vertex) {
    	return verticesMap.get(vertex);
    }
    
    public boolean hasVertex(T vertex) {
        return verticesMap.containsKey(vertex);
    }

    public int order() {
        return verticesMap.size();
    }

    public int size() {
        return numEdges;
    }
    
    
    /**
     * Prints the graph for the reference
     * DO NOT EDIT THIS FUNCTION
     * DO ENSURE THAT YOUR verticesMap is being used 
     * to represent the vertices and edges of this graph.
     */
    public void printGraph() {

        for ( T vertex : verticesMap.keySet() ) {
            if ( verticesMap.get(vertex).size() != 0) {
                for (T edges : verticesMap.get(vertex)) {
                    System.out.println(vertex + " -> " + edges + " ");
                }
            } else {
                System.out.println(vertex + " -> " + " " );
            }
        }
    }
    public static void main(String[] args) throws Exception {
    	GraphImpl<Integer> test = new GraphImpl<Integer>();
    	
    	test.addVertex(0);
    	test.addVertex(1);
    	test.addVertex(2);
    	test.addVertex(3);
    	
    	test.addEdge(0, 1);
    	test.addEdge(2, 3);
    	test.addEdge(0, 2);
    	
    	System.out.println("Num of Vertices: " + test.order());
    	System.out.println("Num of Edges: " + test.size());
    	test.printGraph();
    	
    	System.out.println("REMOVING 2");
    	
    	test.removeVertex(2);
    	System.out.println("Num of Verticwes: " + test.order());
    	System.out.println("Num of Edges: " + test.size());
    	test.printGraph();
    	
    }
}
