import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T>{

    private List<Edge<T>> allEdges;
    private Map<Long,VertexNew<T>> allVertexNew;
    boolean isDirected = false;
    
    public Graph(boolean isDirected){
        allEdges = new ArrayList<Edge<T>>();
        allVertexNew = new HashMap<Long,VertexNew<T>>();
        this.isDirected = isDirected;
    }
    
    public void addEdge(long id1, long id2){
        addEdge(id1,id2,0);
    }
    
    //This works only for directed graph because for undirected graph we can end up
    //adding edges two times to allEdges
    public void addVertexNew(VertexNew<T> VertexNew){
        if(allVertexNew.containsKey(VertexNew.getId())){
            return;
        }
        allVertexNew.put(VertexNew.getId(), VertexNew);
        for(Edge<T> edge : VertexNew.getEdges()){
            allEdges.add(edge);
        }
    }
    
    public VertexNew<T> addSingleVertexNew(long id){
        if(allVertexNew.containsKey(id)){
            return allVertexNew.get(id);
        }
        VertexNew<T> v = new VertexNew<T>(id);
        allVertexNew.put(id, v);
        return v;
    }
    
    public VertexNew<T> getVertexNew(long id){
        return allVertexNew.get(id);
    }
    
    public void addEdge(long id1,long id2, int weight){
        VertexNew<T> VertexNew1 = null;
        if(allVertexNew.containsKey(id1)){
            VertexNew1 = allVertexNew.get(id1);
        }else{
            VertexNew1 = new VertexNew<T>(id1);
            allVertexNew.put(id1, VertexNew1);
        }
        VertexNew<T> VertexNew2 = null;
        if(allVertexNew.containsKey(id2)){
            VertexNew2 = allVertexNew.get(id2);
        }else{
            VertexNew2 = new VertexNew<T>(id2);
            allVertexNew.put(id2, VertexNew2);
        }

        Edge<T> edge = new Edge<T>(VertexNew1,VertexNew2,isDirected,weight);
        allEdges.add(edge);
        VertexNew1.addAdjacentVertexNew(edge, VertexNew2);
        if(!isDirected){
            VertexNew2.addAdjacentVertexNew(edge, VertexNew1);
        }

    }
    
    public List<Edge<T>> getAllEdges(){
        return allEdges;
    }
    
    public Collection<VertexNew<T>> getAllVertexNew(){
        return allVertexNew.values();
    }
    public void setDataForVertexNew(long id, T data){
        if(allVertexNew.containsKey(id)){
            VertexNew<T> VertexNew = allVertexNew.get(id);
            VertexNew.setData(data);
        }
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        for(Edge<T> edge : getAllEdges()){
            buffer.append(edge.getVertexNew1() + " " + edge.getVertexNew2() + " " + edge.getWeight());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}


class VertexNew<T> {
    long id;
    private T data;
    private List<Edge<T>> edges = new ArrayList<>();
    private List<VertexNew<T>> adjacentVertexNew = new ArrayList<>();
    
    VertexNew(long id){
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
    
    public void setData(T data){
        this.data = data;
    }
    
    public T getData(){
        return data;
    }
    
    public void addAdjacentVertexNew(Edge<T> e, VertexNew<T> v){
        edges.add(e);
        adjacentVertexNew.add(v);
    }
    
    public String toString(){
        return String.valueOf(id);
    }
    
    public List<VertexNew<T>> getAdjacentVertexNewes(){
        return adjacentVertexNew;
    }
    
    public List<Edge<T>> getEdges(){
        return edges;
    }
    
    public int getDegree(){
        return edges.size();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VertexNew other = (VertexNew) obj;
        if (id != other.id)
            return false;
        return true;
    }
}

class Edge<T>{
    private boolean isDirected = false;
    private VertexNew<T> VertexNew1;
    private VertexNew<T> VertexNew2;
    private int weight;
    
    Edge(VertexNew<T> VertexNew1, VertexNew<T> VertexNew2){
        this.VertexNew1 = VertexNew1;
        this.VertexNew2 = VertexNew2;
    }

    Edge(VertexNew<T> VertexNew1, VertexNew<T> VertexNew2,boolean isDirected,int weight){
        this.VertexNew1 = VertexNew1;
        this.VertexNew2 = VertexNew2;
        this.weight = weight;
        this.isDirected = isDirected;
    }
    
    Edge(VertexNew<T> VertexNew1, VertexNew<T> VertexNew2,boolean isDirected){
        this.VertexNew1 = VertexNew1;
        this.VertexNew2 = VertexNew2;
        this.isDirected = isDirected;
    }
    
    VertexNew<T> getVertexNew1(){
        return VertexNew1;
    }
    
    VertexNew<T> getVertexNew2(){
        return VertexNew2;
    }
    
    int getWeight(){
        return weight;
    }
    
    public boolean isDirected(){
        return isDirected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((VertexNew1 == null) ? 0 : VertexNew1.hashCode());
        result = prime * result + ((VertexNew2 == null) ? 0 : VertexNew2.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (VertexNew1 == null) {
            if (other.VertexNew1 != null)
                return false;
        } else if (!VertexNew1.equals(other.VertexNew1))
            return false;
        if (VertexNew2 == null) {
            if (other.VertexNew2 != null)
                return false;
        } else if (!VertexNew2.equals(other.VertexNew2))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Edge [isDirected=" + isDirected + ", VertexNew1=" + VertexNew1
                + ", VertexNew2=" + VertexNew2 + ", weight=" + weight + "]";
    }
}

 
