package assignment4;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.*;
import java.util.SortedSet;


public class Vertex<T> {
        private T name;
        private Map<T, Integer> edges; //T is a vertex, Integer is the weight

        public Vertex(T name) {
                this.name = name;
                edges = new HashMap<>();
        }

        public void addEdge(T edgeName){
                if(edges.containsKey(edgeName)){
                        edges.put(edgeName, (edges.get(edgeName)+1));
                }
                else {
                        edges.put(edgeName, 1);
                }
        }

        public boolean containsEdge(T edgeName){ return edges.containsKey(edgeName); }

        public Integer edgeSize(){
                return edges.size();
        }

        public Integer getWeight(String key){
                return edges.get(key);
        }

        public String getEdge(Integer index){
                Set<String> curEdges = new HashSet<>();
                curEdges = (Set<String>) edges.keySet();
                Iterator it = curEdges.iterator();
                String name = "null";
                for(int i = 0; i < index + 1; i++){
                        if(it.hasNext()){
                           name = it.next().toString();
                        }
                        else{
                                return name;
                        }
                }
                return name;
        }

        public T returnName(){
                return name;
        }

        public void printName(){
                System.out.print(edges + " ");
                System.out.println(name);
        }
}
