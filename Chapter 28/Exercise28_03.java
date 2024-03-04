import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Exercise28_03 {
  public static void main(String[] args) {
    String[] vertices = {"Seattle", "San Francisco", "Los Angeles",
      "Denver", "Kansas City", "Chicago", "Boston", "New York",
      "Atlanta", "Miami", "Dallas", "Houston"};

    int[][] edges = {
      {0, 1}, {0, 3}, {0, 5},
      {1, 0}, {1, 2}, {1, 3},
      {2, 1}, {2, 3}, {2, 4}, {2, 10},
      {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
      {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
      {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
      {6, 5}, {6, 7},
      {7, 4}, {7, 5}, {7, 6}, {7, 8},
      {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
      {9, 8}, {9, 11},
      {10, 2}, {10, 4}, {10, 8}, {10, 11},
      {11, 8}, {11, 9}, {11, 10}
    };

    UnweightedGraph<String> graph = new UnweightedGraph<>(vertices, edges);
    UnweightedGraph<String>.SearchTree dfs = graph.dfsNonRecurs(graph.getIndex("Chicago")); 

    java.util.List<Integer> searchOrders = dfs.getSearchOrder();
    
    System.out.println(dfs.getNumberOfVerticesFound() + " vertices are searched in this DFS order:");
    for (int i = 0; i < searchOrders.size(); i++) {
      System.out.print(graph.getVertex(searchOrders.get(i)) + " ");
    }
    System.out.println('\n');

    for (int i = 0; i < searchOrders.size(); i++)
      if (dfs.getParent(i) != -1)
        System.out.println("parent of " + graph.getVertex(i) + " is " + graph.getVertex(dfs.getParent(i)));
  }
  
  public SearchTree dfsNonRecurs(int v) {
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1;
    boolean[] isVisited = new boolean[vertices.size()];
    List<Integer> searchOrder = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    
    stack.push(vertecies[i]);
    searchOrder(i) = vertecies[i];
    isVisited[i] = true;
    
    while (stack.empty() != true) {
      stack.peek();
      
      if (size.neighbors.get(x) == 0) {
        stack.pop();
      }
      else {
        for (int i = size of vertices in x's neighbor list and count towards 0) {
          grab Edge at index i, call e
          remove ending vertex from x's neighbor list
          
          if (ending vertex of e is not visited) {
            mark parent of ending vertex as x
            push ending vertex onto stack
            mark ending vertex as visited
            add ending vertex to search order
            break;
          }
        }
      }
    return new SearchTree(v, parent, searchOrder);
  }
          
}