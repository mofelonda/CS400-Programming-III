
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * Filename:   CourseSchedulerUtil.java
 * Project:    p4
 * Authors:    Debra Deppeler, Daewon Lee
 * 
 * Use this class for implementing Course Planner
 * @param <T> represents type
 */

public class CourseSchedulerUtil<T> {
    
    // can add private but not public members
    
    /**
     * Graph object
     */
    private GraphImpl<T> graphImpl;
    
    /**
     * constructor to initialize a graph object
     */
    public CourseSchedulerUtil() {
        this.graphImpl = new GraphImpl<T>();
    }
    
    /**
    * createEntity method is for parsing the input json file 
    * @return array of Entity object which stores information 
    * about a single course including its name and its prerequisites
    * @throws Exception like FileNotFound, JsonParseException
    */
    @SuppressWarnings("rawtypes")
    public Entity[] createEntity(String fileName) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(fileName));
        JSONObject jsonObj = (JSONObject) obj; 
        JSONArray courseArray = (JSONArray) jsonObj.get("courses");
        Iterator itr1 = courseArray.iterator();

        Entity[] courses = new Entity[courseArray.size()];
        
        
        for(int i = 0; i < courseArray.size(); i++) {
        	JSONObject jsonCourse = (JSONObject) itr1.next();
        	String name = (String) jsonCourse.get("name");
        	
        	JSONArray preq = (JSONArray) jsonCourse.get("prerequisites");
        	Iterator itr2 = preq.iterator();
            String[] prereq = new String[preq.size()];
        	for(int j = 0; j < preq.size(); j++) {
        		prereq[j] = (String) itr2.next();
        	}
        	Entity temp = new Entity();
        	temp.setName(name);
        	temp.setPrerequisites(prereq);
        	courses[i] = temp;
        }
        return courses;
        
    }
    
    
    /**
     * Construct a directed graph from the created entity object 
     * @param entities which has information about a single course 
     * including its name and its prerequisites
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void constructGraph(Entity[] entities) {
    	
    	//Creates a successor graph 
    	for(int i = 0; i < entities.length; i++) {
    		graphImpl.addVertex((T) entities[i].getName());
    		for(int j = 0; j < entities[i].getPrerequisites().length; j++) {
    			graphImpl.addVertex((T) entities[i].getPrerequisites()[j]);
    		}
        }
    	for(int i = 0; i < entities.length; i++) {
    		for(int j = 0; j < entities[i].getPrerequisites().length; j++) {
    			graphImpl.addEdge((T) entities[i].getName(), (T) entities[i].getPrerequisites()[j]);
    		}
    	}
    }
    
    
    /**
     * Returns all the unique available courses
     * @return the set of all available courses
     */
    public Set<T> getAllCourses() {
        return graphImpl.getAllVertices();
    }
    
    
    /**
     * To check whether all given courses can be completed or not
     * @return boolean true if all given courses can be completed,
     * otherwise false
     * @throws Exception
     */
    public boolean canCoursesBeCompleted() throws Exception {
        Set<T> courses = graphImpl.getAllVertices();
        T[] array = (T[]) courses.toArray();
        
        Set<T> visited = new HashSet<>();
        Set<T> recStack = new HashSet<>();
        for(int i = 0; i < array.length; i++) {
        	if(hasCycle(array[i], visited, recStack)) {
        		return false;
        	}
        }
        return true;
    }
    
    private boolean hasCycle(T vertex, Set<T> visited, Set<T> recStack) {
    	if(!visited.contains(vertex)) {
    		visited.add(vertex);
    		recStack.add(vertex);
    		
    		for(T node : graphImpl.getAdjacentVerticesOf(vertex)) {
    			if(!visited.contains(node)) {
    				if(hasCycle(node, visited, recStack)) {
    					return true;
    				}
    			} else if(recStack.contains(node)) {
    				return true;
    			}
    		}
    	}
    	recStack.remove(vertex);
    	return false;
    }
    /**
     * The order of courses in which the courses has to be taken
     * @return the list of courses in the order it has to be taken
     * @throws Exception when courses can't be completed in any order
     */
    public List<T> getSubjectOrder() throws Exception {
        Stack<T> stack = new Stack<T>();
        List<T> subjectOrder = new ArrayList<T>() ;
        Set<T> visited = new HashSet<>();
        Set<T> courses = graphImpl.getAllVertices();
        T[] array = (T[]) courses.toArray();
        if(canCoursesBeCompleted() == false) {
        	throw new Exception();
        }
        for(int i = 0; i < array.length; i++) {
        	if(!visited.contains(array[i])) {

        		topologicalSort(array[i], visited, stack);
        	}
        }
        
        while(!stack.isEmpty()) {
        	subjectOrder.add((T) stack.pop());
        }
        Collections.reverse(subjectOrder);
        return subjectOrder;

    }
    
    private void topologicalSort(T vertex, Set<T> visited, Stack<T> stack) {
    	visited.add(vertex);
    	
    	for(T pred : graphImpl.getAdjacentVerticesOf(vertex)) {
    		if(!visited.contains(pred)) {
    			topologicalSort(pred, visited, stack);
    		}
    	}
    	stack.push(vertex);
    }
    
    /**
     * The minimum course required to be taken for a given course
     * @param courseName 
     * @return the number of minimum courses needed for a given course
     */
	int result = 0;
    public int getMinimalCourseCompletion(T courseName) throws Exception {
    	
    	result = graphImpl.getAdjacentVerticesOf(courseName).size();
    	
    	if(result == 0) return 0;
    	
    	for(T preq : graphImpl.getAdjacentVerticesOf(courseName)) {
    		result = result + getMinimalCourseCompletion(preq);
    	}
    	
    	return result;
    }
    private void print() {
    	graphImpl.printGraph();
    }
    public static void main(String[] args) throws Exception {
        CourseSchedulerUtil<String> courses = new CourseSchedulerUtil<String>();
        

        courses.constructGraph(courses.createEntity("valid.json"));
        //courses.getMinimalCourseCompletion("CS400");
        System.out.println(courses.getSubjectOrder().toString());
        //courses.canCoursesBeCompleted();
        //courses.print();
    }
	
    
}
