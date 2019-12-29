package dataStructure;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import utils.Point3D;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;

public class DGraph implements graph{
	private HashMap  <Integer, node_data> HashMapNode  = new HashMap<Integer, node_data>();
	private HashMap  <Integer, HashMap<Integer, edge_data>> HashMapEdge  = new HashMap<Integer, HashMap<Integer, edge_data>>();
	public  int MC;

	public DGraph() 
	{
		this.HashMapNode = new HashMap<Integer, node_data>();
		this.HashMapEdge = new HashMap<Integer, HashMap<Integer,edge_data>>();
		this.MC=0;
	}

	public DGraph(DGraph g) 
	{
		this.HashMapNode.putAll(g.HashMapNode);
		this.HashMapEdge.putAll(g.HashMapEdge);
		this.MC=g.getMC();
	}

	@Override
	public node_data getNode(int key) 
	{
		return HashMapNode.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest)
	{
		Iterator<node_data> itN = this.getV().iterator(); 
		while (itN.hasNext()) 
		{
			node_data nd = itN.next();
			if (nd.getKey()==src)
			{
				Iterator<edge_data> itE = this.getE(nd.getKey()).iterator(); 
				while (itE.hasNext()) 
				{
					edge_data ed = itE.next();
					if (ed.getDest()==dest)
						return ed;
				}
			}
		}
		edge_data ed =null;
		return ed;	
	}

	@Override
	public void addNode(node_data n) 
	{
		HashMapNode.put(n.getKey(), n);
		HashMapEdge.put(n.getKey(),new HashMap<Integer, edge_data>());
		this.MC++;
	}

	@Override
	public void connect(int src, int dest, double w) 
	{
		boolean flag=false;
		edge_data originalEd= new EdgeData(src,dest,w);
		if (HashMapNode.get(src)==HashMapNode.get(dest)) 
		{
			System.out.println("the src and dest is the same");
		}

		//			if (HashMapNode.get(src)==null &&(HashMapNode.get(dest)==null)) 
		//			{
		//				node_data s= new NodeData(src);
		//				addNode(s);
		//			}

		if (HashMapEdge.get(src)==null)
		{
			HashMapEdge.put(src,new HashMap<Integer, edge_data>());
		}
		if (HashMapEdge.get(src)!=null)
		{
				Iterator<edge_data> itE = this.getE(src).iterator(); 
				while (itE.hasNext() && !flag) 
				{
					edge_data ed = itE.next();
					if (ed.getDest()==dest)
						flag = true;	
				}
		}

		if (!flag)
		HashMapEdge.get(src).put(dest,originalEd);
		//		boolean flag=false;
		//		if (HashMapNode.get(src)==HashMapNode.get(dest)) 
		//		{
		//			System.out.println("the src and dest is the same");
		//		}
		//		
		//		if (HashMapNode.get(src)==null) {
		//			node_data s= new NodeData(src);
		//			addNode(s);
		//		}
		//		if (HashMapNode.get(dest)==null) {
		//			node_data d= new NodeData(dest);
		//			addNode(d);
		//		}
		//		
		//		Iterator<edge_data> it = this.getE(src).iterator(); 
		//		while (it.hasNext()&&flag==false) 
		//		{
		//			edge_data ed = it.next();
		//			if (ed.getDest()==dest) {
		//				flag = true;
		//			}
		//
		//		}
		//		if (!flag) {
		//			edge_data ed=new EdgeData(src,dest,w);
		//			HashMapEdge.get(src).put(dest, ed);
		//			this.MC++;
		//		}
	}


	@Override
	public Collection<node_data> getV() {
		//		Collection<node_data> co= (Collection<node_data>) HashMapNode;	
		//		return co;
		return HashMapNode.values();

	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		//		Collection<edge_data> co= (Collection<edge_data>) HashMapEdge.get(node_id);
		//		return co;
		return HashMapEdge.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) 
	{
		if (HashMapNode.get(key)!=null)
		{
			if (HashMapEdge.get(key)!=null) 
			{
				HashMapEdge.remove(key);
			}
		}

		Iterator<node_data> itN = this.getV().iterator(); 
		while (itN.hasNext()) 
		{
			node_data nd = itN.next();
			Iterator<edge_data> itE = this.getE(nd.getKey()).iterator(); 
			while (itE.hasNext()) 
			{
				edge_data ed = itE.next();
				if (ed.getDest()==key)
				{	System.out.println("key "+ key);
				System.out.println("ed.getDest() "+ed.getDest());
				removeEdge(ed.getSrc(), ed.getDest());
				}
			}
		}

		node_data tempND = new NodeData (HashMapNode.get(key));

		HashMapNode.remove(key);
		this.MC++;

		return tempND;
	}

	@Override
	public edge_data removeEdge(int src, int dest) 
	{
		edge_data ed=new EdgeData();
		if (HashMapEdge.get(src)!=null) 
		{
			ed=HashMapEdge.get(src).get(dest);
			HashMapEdge.get(src).remove(dest);
			this.MC++;

		}
		else 
		{
			System.out.println("the src doesnt exsit");
		}
		return ed;
	}

	@Override
	public int nodeSize() {
		int Size = HashMapNode.size(); 
		return Size;
	}

	@Override
	public int edgeSize() 
	{
		int size =0; 
		Iterator<node_data> itN = this.getV().iterator(); 
		while (itN.hasNext()) 
		{
			node_data nd = itN.next();
			Iterator<edge_data> itE = this.getE(nd.getKey()).iterator(); 
			while (itE.hasNext()) 
			{
				edge_data ed = itE.next();
				size++;
			}
		}

		return size; 

	}


	@Override
	public int getMC() {	
		return MC;
	}

	//	public Iterator<node_data> iterator() 
	//	{
	//		return ((Collection<node_data>) HashMapNode).iterator();
	//	}
	public static void main(String[] args) 
	{
		graph Dg = new DGraph();
		Point3D p0 = new Point3D(1, 6, 0);
		Point3D p1 = new Point3D(0, 2, 3);
		Point3D p2 = new Point3D(1, 4, 0);
		Point3D p3 = new Point3D(5, 2, 0);
		NodeData node0 = new NodeData (0 ,p0, 5.8,"gh",0);
		node_data node1 = new NodeData(1 ,p1 ,6,"gh", 0);
		node_data node2 = new NodeData(2 ,p2 ,7,"gh", 0);
		node_data node3 = new NodeData(3 ,p3 ,8,"gh", 0);
		System.out.println("befor nodes"+ Dg.nodeSize());
		Dg.addNode(node0);
		Dg.addNode(node1);
		Dg.addNode(node2);
		System.out.println("after add nodes"+ Dg.nodeSize());

		System.out.println("befor connect:  "+ Dg.edgeSize());

		Dg.connect(node0.getKey(), node1.getKey(), 6);
		Dg.connect(node1.getKey(), node2.getKey(), 7);

		System.out.println("after 2 connect:  "+ Dg.edgeSize());
		if (Dg.edgeSize()!=2) 
		{ 
			fail();
		}

		Dg.connect(node2.getKey(), node3.getKey(), 8);
		System.out.println("after 3 connect:  "+ Dg.edgeSize());

		if (Dg.edgeSize()!=3) 
		{ 
			fail();
		}

		System.out.println("weight edge:   "+(   Dg.getEdge(node1.getKey(), node2.getKey()).getWeight()    )   );
		if ((Dg.getEdge(node1.getKey(), node2.getKey()).getWeight())!=7) 
		{ 
			fail();
		}	

		Dg.connect(node1.getKey(), node3.getKey(), 8);
		System.out.println("after 4 connect:  "+ Dg.edgeSize());

		if (Dg.edgeSize()!=4) 
		{ 
			fail();
		}

	}

}
