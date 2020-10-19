import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Graph {
    private ArrayList<ArrayList<Integer>> graph;

    public Graph(int size) {
        this.graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < size + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }
    }

    public ArrayList<ArrayList<Integer>> getGraph() {
        return this.graph;
    }

    public ArrayList<Integer> getNode(int i) {
        return this.graph.get(i);
    }


    //양방향 그래프
    public void put(int x, int y) {
        graph.get(x).add(y);
        graph.get(y).add(x);
    }

    //단방향 그래프
    public void putSingle(int x, int y) {
        graph.get(x).add(y);
    }

    public void printGraphToAdjList() {
        for (int i = 1; i < graph.size(); i++) {
            System.out.print("정점 " + i + "의 인접리스트");

            for (int j = 0; j < graph.get(i).size(); j++) {
                System.out.print(" -> " + graph.get(i).get(j));
            }
            System.out.println();
        }
    }

    public void bfs(int start) {
        boolean visited[] = new boolean[5];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int x : graph.get(node)) {
                if (!visited[x]) {
                    visited[x] = true;
                    queue.add(x);
                }
            }
        }
    }
    public void dfsUtil(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node+" ");
        for(int x : graph.get(node)){
            if(!visited[x]){
                dfsUtil(x,visited);
            }
        }
    }
    public void dfs(int start){
        boolean visited[] = new boolean[5];
        dfsUtil(start,visited);
    }
}

public class BFS {
    public static void main(String[] args) {
        int size = 5;
        Graph list = new Graph(size);

        list.put(0,1);
        list.put(0,2);
        list.put(0,3);
        list.put(0,4);

        list.put(1,2);
        list.put(1,3);
        list.put(1,4);


        list.put(2,3);
        list.put(2,4);

        list.put(3,4);

        list.put(4,3);
        list.printGraphToAdjList();
        list.bfs(0);
        System.out.println();
        list.dfs(0);
    }

}
