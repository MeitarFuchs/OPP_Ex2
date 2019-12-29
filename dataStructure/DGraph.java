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
		node_data tempNd=null;
		if (HashMapNode.get(key)!=null)
		{
			//if (HashMapEdge.get(key)!=null) 
			//{
			tempNd=HashMapNode.get(key);
				HashMapNode.remove(key);
			//}
		}
		

		Iterator<node_data> itN = this.getV().iterator(); 
		while (itN.hasNext()) 
		{
			System.out.println("key  "+ key);
			node_data nd = itN.next();
			Iterator<edge_data> itE = this.getE(nd.getKey()).iterator(); 
			while (itE.hasNext()) 
			{
				edge_data ed = itE.next();
				System.out.println("ed.getdest() "+ed.getDest());
				System.out.println("key "+ key);
				if (ed.getDest()==key)
				{	
				removeEdge(ed.getSrc(), ed.getDest());
				}
			}
		}

		//node_data tempND = new NodeData (HashMapNode.get(key));

		HashMapNode.remove(key);
		this.MC++;

		return tempNd;
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

	public static void main(String[] args) 
	{
		
	}

}
