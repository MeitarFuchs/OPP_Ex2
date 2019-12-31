package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.*;

import com.sun.javafx.collections.IntegerArraySyncer;
import com.sun.jmx.remote.internal.ArrayQueue;

import dataStructure.DGraph;
import dataStructure.EdgeData;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms
{
	private DGraph myGraph = new DGraph();
	 public graph getMyGraph(){
	        return this.myGraph;
	    }
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
			//nd.setTag(0);
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
				if (!itE2.hasNext())//if he does'nt connect to any node
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
		{
			Iterator <node_data> itr = ( this.myGraph.getV()).iterator(); 
			if (it.hasNext()) 
			{
				System.out.println("hdbcbjvfbuscgdxkjbhvjsgaNAHSGDCJ");
				node_data nd =  itr.next();
				System.out.println("tag node "+nd.getKey()+"is:   "+nd.getTag());
			}

			return true;

		}
		return false;
	}

	private boolean checkAllTag1()
	{
		Iterator <node_data> it = ( this.myGraph.getV()).iterator(); 
		while (it.hasNext()) 
		{
			node_data nd =  it.next();
			System.out.println("tag node "+nd.getKey()+"is:   "+nd.getTag());
			if (nd.getTag()!=1)
			{

				return false;
			}
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
	private void infinityNodeW()
	{
		Iterator<node_data> it = this.myGraph.getV().iterator(); 
		while (it.hasNext()) 
		{
			node_data nd = it.next();
			nd.setWeight(Integer.MAX_VALUE);
		}
	}

	@Override
	public double shortestPathDist(int src, int dest) 
	{
		List<node_data> lNd=new LinkedList<node_data>() ;
		lNd= shortestPath(src, dest);
		double dis=this.myGraph.getNode(dest).getWeight();
		return dis;
	//	 return shortestPathDistList(shortestPath(src, dest),  src,  dest) ;
	}
	
//	public double shortestPathDistList(List<node_data> l, int src, int dest) 
//	{
//		
//		double dis=l.get(l.size()-1).getWeight();
//		return dis;
//	}

	@Override
	public List<node_data> shortestPath(int src, int dest) 
	{
		infinityNodeW();
		Queue<node_data> qn = new LinkedList<node_data>();

		List<node_data> lNd=new LinkedList<node_data>();
		node_data srcN = this.myGraph.HashMapNode.get(src);
		node_data destN = this.myGraph.HashMapNode.get(dest);
		srcN.setWeight(0);
		srcN.setInfo("");
		qn.add(srcN);
		node_data nd=this.myGraph.getNode(src);

		while (!qn.isEmpty()) 
		{
			Iterator<edge_data> itEdge = this.myGraph.getE(nd.getKey()).iterator(); 
			while (itEdge.hasNext()) 
			{
				edge_data ed = (edge_data) itEdge.next();

				if (nd.getWeight()+ed.getWeight() < this.myGraph.getNode(ed.getDest()).getWeight() )
				{
					this.myGraph.getNode(ed.getDest()).setWeight(nd.getWeight()+ed.getWeight());
					if (nd.getKey()==src)
						this.myGraph.getNode(ed.getDest()).setInfo(""+nd.getKey());
					else
						this.myGraph.getNode(ed.getDest()).setInfo(nd.getInfo()+","+  Integer.toString(nd.getKey()));

					System.out.println(this.myGraph.getNode(ed.getDest()).getInfo() );
					System.out.println(nd.getInfo());
					qn.add( this.myGraph.getNode(ed.getDest()));
				}
			}	

			while ( !qn.isEmpty() && (qn.peek().getKey() == dest ))
			{
				qn.remove();
				if (!qn.isEmpty())
				{	
				nd=qn.peek();
				}
				
			}
			
			if (!qn.isEmpty())
			{	qn.remove();
			if (!qn.isEmpty())
			{	
			nd=qn.peek();
			}
			}
		}

		String infoDest =this.myGraph.HashMapNode.get(dest).getInfo()+","+destN.getKey();
		System.out.println("infoDestttttttttttttttttttttttttt:  "+infoDest);
		int len=infoDest.length()/2; 
		String[] arr=new String [len];

		arr=infoDest.split(",");
		for (int i=0; i<arr.length; i++)
		{
			//System.out.println("arr[i]"+ arr[2]);
			lNd.add(this.myGraph.HashMapNode.get(Integer.parseInt(arr[i])));
		}

		return lNd;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		LinkedList <node_data> listAns = new LinkedList<node_data>();
		
		if (!isConnected()) 
		{
			System.out.println("im not connect -end TSP");
			return null;
		}
		if (targets.size()==1)
		{
			listAns.add(this.myGraph.HashMapNode.get(targets.get(0)));
			return listAns;
		}
		else {
			int numList=0;
			while(numList<targets.size()-1)
			{
				System.out.println("targets.get(numList):  "+targets.get(numList));
				System.out.println("targets.get(numList+1):  "+targets.get(numList+1));
				listAns.addAll(shortestPath(targets.get(numList), targets.get(numList+1)));
				
				if (numList!=0) 
				{
					listAns.remove(numList);
				}
				numList++;
				System.out.println("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
			}
			
			numList=0;
			while(numList<targets.size()-1)// remove douplicat
			{
				System.out.println("bye TSPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
				if (listAns.get(numList).getKey() == listAns.get(numList+1).getKey() )
				{
					listAns.remove(numList);
				}
				numList++;
			}	
		}
		if (!listAns.isEmpty())
			System.out.println("bye TSP");
		return listAns;
	}
	//	{
	//		List <node_data> listN = new LinkedList<node_data>();
	//		if (!isConnected())
	//		{
	//			return null;
	//		}
	//
	//		if (targets.size()==1) // had just one node
	//		{
	//			int x=targets.get(0);
	//			listN.add(this.myGraph.HashMapNode.get(x));
	//			return listN;
	//		}
	//		else //more then one node
	//		{
	//			int y = 0;
	//			while( y<targets.size()-1)
	//			{
	//				listN.addAll( shortestPath( targets.get(y) , targets.get(y+1) ) );
	//				//					if (y!=0)
	//				//					{
	//				//						listN.remove(y);
	//				//					}
	//				y++;
	//			}
	//
	//			 y=0;
	//			while(y<listN.size()-1)// remove duplicats
	//			{
	//				if (listN.get(y).getKey() == listN.get(y+1).getKey() )
	//				{	listN.remove(y);}
	//
	//				y++;
	//			}
	//		}
	//		for (int i = 0; i <listN.size() ; i++) {
	//			System.out.println(listN.get(i).getKey());
	//		}
	//		return listN;
	//	}




	//	@Override
	//	public List<node_data> TSP(List<Integer> targets) 
	//	{
	//		initColor(0);
	//		infinityNodeW();
	//		Queue<Integer> qn = new LinkedList<Integer>();
	//		List<node_data> lNd=new LinkedList<node_data>();
	//		List<node_data> listTemp=new LinkedList<node_data>();
	//
	//		Iterator<Integer> it = targets.iterator(); 
	//		if (it.hasNext())
	//		{
	//			qn.add(it.next());
	//		}
	//		node_data nd= this.myGraph.HashMapNode.get(qn.peek()) ; 	
	//		while(!qn.isEmpty())
	//		{
	//			Iterator<edge_data> itEdge = this.myGraph.getE(nd.getKey()).iterator(); 
	//			while (itEdge.hasNext()) 
	//			{
	//				edge_data ed = (edge_data) itEdge.next();
	//
	//				if (isInList(ed.getDest(),targets))
	//				{
	//					listTemp=shortestPath(nd.getKey(), ed.getDest());
	//					Collections.copy(listTemp, lNd);
	//					nd=this.myGraph.getNode(ed.getDest());
	//
	//				}
	//				else
	//				{	
	//					if (this.myGraph.getNode(ed.getDest()).getTag()!=1)
	//					{
	//						this.myGraph.getNode(ed.getDest()).setTag(1);
	//						qn.add(ed.getDest());
	//					}	
	//				}	
	//			}
	//
	//			if (!qn.isEmpty())
	//				nd=this.myGraph.getNode(qn.remove());
	//		}
	//
	//		return lNd;
	//	}

	//	private boolean isInList(int dest, List<Integer> targets) 
	//	{
	//		Iterator<Integer> it = targets.iterator(); 
	//		while (it.hasNext())
	//		{
	//			if (it.next()==dest)
	//				return true;
	//		}
	//		return false;
	//	}

	@Override
	public graph copy() { 
		graph newGraph = new DGraph();
		Iterator<node_data> itNode = this.myGraph.getV().iterator(); 
		while (itNode.hasNext()) 
		{
			node_data nd = (node_data)itNode.next();
			newGraph.addNode(nd);
			Iterator<edge_data> itEdge = this.myGraph.getE(nd.getKey()).iterator(); 
			while (itEdge.hasNext()) 
			{
				edge_data ed = (edge_data) itEdge.next();
				newGraph.connect(nd.getKey(), ed.getDest(), ed.getWeight());
			}
		}
		return  newGraph;
	}

	public static void main(String[] args) 
	{



	}


}
