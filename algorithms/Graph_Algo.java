package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.xml.soap.Node;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms{
	graph myGraph = new DGraph();
	 
	@Override
	public void init(graph g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(String file_name) {
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) {
		// TODO Auto-generated method stub

	}

	public Iterator<NodeData> iteretor() 
	{
		return myGraph.iterator();
	}

	@Override
	public boolean isConnected() {
		int n=0;
		Iterator <NodeData> it = this.iteretor(); 
		while (it.hasNext()) 
		{
			n=NumberOfN(it.next());
			if (n!=myGraph.size()) {//מספר השכנים צריך להיות שווה למספר הקודקודים כי הוא מחובר לכולם 
				return false;
			}
		}
		return true;
	}

	private int NumberOfN(NodeData edgeData) {

		initColor(0);
		int ans = 1;
		//int t=0;
		Stack<NodeData> G = new Stack<NodeData>();
		Stack<NodeData> C = new Stack<NodeData>();
		C.push(edgeData);
		Iterator<NodeData> it = C.iterator(); 
		while (it.hasNext()) 
		{
			node_data a =  it.next();
			if (a.getTag() ==0) {
				ans++;
				a.setTag(1);
			}
			G = getWhiteN( a);
			Iterator<NodeData> gIterator = G.iterator(); 
			while (gIterator.hasNext()) 
			{
				C.push(gIterator.next());
			}
		}
		return ans;
	}


	private Stack<NodeData> getWhiteN( node_data a) { // get all the white neib
		Stack<NodeData> neib = new Stack<NodeData>();
		
		if (a.get()==null) {			
		}
		neib.push(a.getSrc());
			return null;
	}

	private void initColor(int t) {
		Iterator<NodeData> it = this.iteretor(); 
		while (it.hasNext()) 
		{
			it.next().setTag(t);
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() { 
		graph newGraph = new DGraph();
		Iterator<node_data> itNode = this.myGraph.getV().iterator(); 
		while (itNode.hasNext()) {
			node_data nd = (node_data)itNode.next();
			newGraph.addNode(nd);
			Iterator<edge_data> itEdge = this.myGraph.getE(nd.getKey()).iterator(); 
			while (itEdge.hasNext()) {
				edge_data ed = (edge_data) itEdge.next();
				newGraph.connect(nd.getKey(), ed.getDest(), ed.getWeight());
			}
			
		}
		
		return  newGraph;
	}

}
