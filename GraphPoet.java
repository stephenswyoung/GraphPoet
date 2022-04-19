package assignment4;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;


public class GraphPoet {
    private ArrayList<Vertex> graph;
    private ArrayList<String> base;
    private ArrayList<String> inputList;
    private ArrayList<String> possibleBridges;

    public Integer vertexByName(String name){
        String check;
        for(int i = 0; i < graph.size(); i++){
            check = graph.get(i).returnName().toString();
            if (check.equalsIgnoreCase(name)){
                return i;
            }
        }
        return -1;
    }

    public void addEdges(){
        Iterator it = base.iterator();
        String temp = it.next().toString();
        String cur;
        while(it.hasNext()){
            cur = it.next().toString();
            graph.get(vertexByName(temp)).addEdge(cur.toLowerCase());
            temp = cur;
        }
    }

    public String bridgeApplicable(String cur, String next){
        String bridge = "null!";
        String heaviestBridge = "null!";
        Integer bridgeWeight = 0;
        Integer bridgeIndex;
        Vertex bridgeVertex;
        Vertex curVertex = graph.get(vertexByName(cur));
        for(int i = 0; i < curVertex.edgeSize(); i++){
            bridge = curVertex.getEdge(i);               //currently has potential bridge name as value
            bridgeIndex = vertexByName(bridge);    //potential bridge index in graph
            if(bridgeIndex == -1){
                break;
            }
            bridgeVertex = graph.get(bridgeIndex); //has full vertex of potential bridge
                                                   // Now we need to check if that full vertex has the "next" parameter
            if(bridgeVertex.containsEdge(next) && bridgeWeight < curVertex.getWeight(bridge)){
                heaviestBridge = bridge;
                bridgeWeight = curVertex.getWeight(bridge);
            }
        }
        if(bridgeWeight == 0){
            return "null!";
        }
        return heaviestBridge;
    }


    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */

    public GraphPoet(File corpus) throws IOException {

        /* Read in the File and place into graph here */
        Scanner scnr = new Scanner(corpus);
        String str = scnr.next();
        graph = new ArrayList<Vertex>();
        base = new ArrayList<String>();
        inputList = new ArrayList<String>();
        possibleBridges = new ArrayList<String>();

        while(!str.isEmpty()) {
            base.add(str);
            if (vertexByName(str) == -1) {
                graph.add(new Vertex(str));
            }

            if (scnr.hasNext()) {
                str = scnr.next();
                continue;
            }
            break;
        }
        addEdges();
/*
            for (int i = 0; i < graph.size(); i++) {
                graph.get(i).printName();
            }

 */
        scnr.close();
    }

    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {

        /* Read in input and use graph to complete poem */
        Scanner scnr = new Scanner(input);
        String str= scnr.next();
        String poem = "";

        while(!str.isEmpty()){
            inputList.add(str);
            if (scnr.hasNext()) {
                str = scnr.next();
                continue;
            }
            break;
        }
        String cur;
        String next;

        for(int i = 0; i < inputList.size() - 1; i++){
            cur = inputList.get(i);
            next = inputList.get(i+1);
            if((!vertexByName(cur).equals(-1)) && (!bridgeApplicable(cur, next).equals("null!"))){
                poem = poem + cur + " " + bridgeApplicable(cur, next) + " ";
            }
            else {
                poem = poem + cur + " ";
            }
        }
        poem = poem + inputList.get(inputList.size()-1);
        scnr.close();
        return poem;
    }

}
