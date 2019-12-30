package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import javax.xml.soap.Node;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

import utils.Point3D;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms
{
	graph myGraph = new DGraph();

	@Override
	public void init(graph g) //???
	{
		this.myGraph=(DGraph) g;
	}

	@Override
	public void init(String file_name) //lode
	{	
		try
		{
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			myGraph= (DGraph)in.readObject();

			in.close();
			file.close();

			System.out.println("The Graph has been deserialized");
		}

		catch(ClassNotFoundException ex)
		{
			System.out.println("ClassNotFoundException is caught");
		}
		catch(IOException ex)
		{
			System.out.println(ex);
		}
	}

	@Override
	public void save(String file_name) 
	{
		String filename = "myAlgoGraph.txt"; 

		try
		{    
			FileOutputStream file = new FileOutputStream(filename); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			out.writeObject(myGraph); 

			out.close(); 
			file.close(); 

			System.out.println("The Graph has been serialized"); 
		}   

		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 
	} 
//	@Override
//	public Iterator <NodeData> iteretor() 
//	{
//		return  ((Collection<node_data>) myGraph).iterator();
//	}

	@Override
	public boolean isConnected() 
	{
		initColor(0);
		//System.out.println("after clear");
		if (this.myGraph.nodeSize() == 1) {
            return true;
        }
        if (this.myGraph.edgeSize() == 0 && this.myGraph.nodeSize() > 1) {
            return false;
        }
        if(this.myGraph.nodeSize()>this.myGraph.edgeSize()){
            return false;
        }
        
        Stack<NodeData> sn = new Stack<NodeData>();
		Iterator <node_data> it = ( this.myGraph.getV()).iterator(); 
		if (it.hasNext()) 
		{
			node_data nd =  it.next();
			nd.setTag(0);
			//System.out.println("eveynode -node: "+nd.getKey());
			Iterator<edge_data> itE = this.myGraph.getE(nd.getKey()).iterator(); 
			while (itE.hasNext()) 
			{
				edge_data ed = itE.next();
				this.myGraph.getNode(ed.getDest()).setTag(1);
				sn.push((NodeData) this.myGraph.getNode(ed.getDest()));
			}
			while(!sn.isEmpty())
			{
				Iterator<edge_data> itE2 = this.myGraph.getE(sn.pop().getKey()).iterator(); 
				if (itE2.hasNext()==false)
					return false;
				while (itE2.hasNext()) 
				{
					edge_data ed2 = itE2.next();
					if (this.myGraph.getNode(ed2.getDest()).getTag()!=1)
					{
						this.myGraph.getNode(ed2.getDest()).setTag(1);
						sn.push((NodeData) this.myGraph.getNode(ed2.getDest()));
					}
				}
			}
		}
		
		if (checkAllTag1())		
			{return true;}
		return false;
	}

	private boolean checkAllTag1()
	{
		Iterator <node_data> it = ( this.myGraph.getV()).iterator(); 
		while (it.hasNext()) 
		{
			node_data nd =  it.next();
			if (nd.getTag()!=1)
				return false;
		}
		return true;	
	}
//	private int NumberOfN(node_data nd) {
//
//		initColor(0);
//		int ans = 1;
//		//int t=0;
//		Stack<NodeData> G = new Stack<NodeData>();
//		Stack<NodeData> C = new Stack<NodeData>();
//		C.push(edgeData);
//		Iterator<NodeData> it = C.iterator(); 
//		while (it.hasNext()) 
//		{
//			node_data a =  it.next();
//			if (a.getTag() ==0) {
//				ans++;
//				a.setTag(1);
//			}
//			G = getWhiteN( a);
//			Iterator<NodeData> gIterator = G.iterator(); 
//			while (gIterator.hasNext()) 
//			{
//				C.push(gIterator.next());
//			}
//		}
//		return ans;
//	}


//	private Stack<NodeData> getWhiteN( node_data a) { // get all the white neib
//		Stack<NodeData> neib = new Stack<NodeData>();
//
//		if (a.get()==null) {			
//		}
//		neib.push(a.getSrc());
//		return null;
//	}

	private void initColor(int t)
	{
		Iterator<node_data> it = this.myGraph.getV().iterator(); 
		while (it.hasNext()) 
		{
			node_data nd = it.next();
			nd.setTag(t);
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
	
	public static void main(String[] args) 
	{
		graph Dg = new DGraph();
		Graph_Algo Ag = new Graph_Algo();

		Point3D p0 = new Point3D(1, 6, 0);
		Point3D p1 = new Point3D(0, 2, 3);
		Point3D p2 = new Point3D(1, 4, 0);
		Point3D p3 = new Point3D(5, 2, 0);
		Point3D p4 = new Point3D(6,5, 0);

		node_data node0 = new NodeData (0 ,p0 ,5,"gh", 0);
		node_data node1 = new NodeData(1 ,p1 ,6,"gh", 0);
		node_data node2 = new NodeData(2 ,p2 ,7,"gh", 0);
		node_data node3 = new NodeData(3 ,p3 ,8,"gh", 0);
		node_data node4 = new NodeData(4 ,p4 ,8,"gh", 0);

		Dg.addNode(node0);
		Dg.addNode(node1);
		Dg.addNode(node2);
		Dg.addNode(node3);
		Dg.addNode(node4);

		Dg.connect(node0.getKey(), node1.getKey(), 9);
		Dg.connect(node1.getKey(), node0.getKey(), 9);
		Dg.connect(node1.getKey(), node4.getKey(), 10);
		Dg.connect(node4.getKey(), node3.getKey(), 11);
		Dg.connect(node3.getKey(), node2.getKey(), 11);
		//Dg.connect(node2.getKey(), node1.getKey(), 11);
		Ag.init(Dg);
		boolean t = Ag.isConnected();
		System.out.println("is connect:  "+ t);
		
	}
	

}
