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
		double dis=myGraph.getNode(dest).getWeight();
		return dis;
	}

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
			}
			if (!qn.isEmpty())
			{	qn.remove();
			nd=qn.peek();
			}
		}

		String infoDest =this.myGraph.HashMapNode.get(dest).getInfo()+","+destN.getKey();
		System.out.println("infoDest:  "+infoDest);
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
		List <node_data> listAns = new LinkedList<node_data>();
		if (!isConnected()) 
		{
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
				listAns.addAll(shortestPath(targets.get(numList),targets.get(numList+1)));
				if (numList!=0) 
				{
					listAns.remove(numList);
				}
				numList++;
			}
			numList=0;
			while(numList<targets.size()-1)// remove douplicat
			{
				if (listAns.get(numList).getKey() == listAns.get(numList+1).getKey() )
				{
					listAns.remove(numList);
				}
				numList++;
			}	
		}
		
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
		//		graph Dg = new DGraph();
		//		Graph_Algo Ag = new Graph_Algo();
		//
		//		Point3D p0 = new Point3D(1, 6, 0);
		//		Point3D p1 = new Point3D(0, 2, 3);
		//		Point3D p2 = new Point3D(1, 4, 0);
		//		Point3D p3 = new Point3D(5, 2, 0);
		//		Point3D p4 = new Point3D(6,5, 0);
		//
		//		node_data node0 = new NodeData (0 ,p0 ,5,"gh", 0);
		//		node_data node1 = new NodeData(1 ,p1 ,6,"gh", 0);
		//		node_data node2 = new NodeData(2 ,p2 ,7,"gh", 0);
		//		node_data node3 = new NodeData(3 ,p3 ,8,"gh", 0);
		//		node_data node4 = new NodeData(4 ,p4 ,8,"gh", 0);
		//
		//		//Dg.addNode(node0);
		//		Dg.addNode(node1);
		//		Dg.addNode(node2);
		//		Dg.addNode(node3);
		//		Dg.addNode(node4);
		//
		//		//		Dg.connect(node0.getKey(), node1.getKey(), 9);
		//		//		Dg.connect(node1.getKey(), node0.getKey(), 9);
		//		//		Dg.connect(node1.getKey(), node4.getKey(), 10);
		//		//		Dg.connect(node4.getKey(), node3.getKey(), 11);
		//		//		Dg.connect(node3.getKey(), node2.getKey(), 11);
		//		//		Dg.connect(node2.getKey(), node1.getKey(), 11);
		//
		//		Dg.connect(node1.getKey(), node2.getKey(), 11);
		//		Dg.connect(node2.getKey(), node3.getKey(), 11);
		//		Dg.connect(node3.getKey(), node2.getKey(), 11);
		//		Dg.connect(node3.getKey(), node4.getKey(), 11);
		//		Dg.connect(node4.getKey(), node3.getKey(), 11);
		//
		//		Ag.init(Dg);
		//		boolean t = Ag.isConnected();
		//		System.out.println("is connect:  "+ t);

		graph Dg2 = new DGraph();
		Graph_Algo Ag2 = new Graph_Algo();

		Point3D p00 = new Point3D(1, 6, 0);
		Point3D p11 = new Point3D(0, 2, 3);
		Point3D p22 = new Point3D(1, 4, 0);
		Point3D p33 = new Point3D(5, 2, 0);
		Point3D p44 = new Point3D(6,5, 0);
		Point3D p55 = new Point3D(4,6, 0);
		Point3D p66 = new Point3D(3,5, 0);

		node_data node00 = new NodeData(0 ,p00 ,5);
		node_data node11 = new NodeData(1 ,p11 ,6);
		node_data node22 = new NodeData(2 ,p22,7);
		node_data node33 = new NodeData(3 ,p33,8);
		node_data node44 = new NodeData(4 ,p44,8);
		node_data node55 = new NodeData(5 ,p55,8);
		node_data node66 = new NodeData(6 ,p66,8);

		Dg2.addNode(node00);
		Dg2.addNode(node11);
		Dg2.addNode(node22);
		Dg2.addNode(node33);
		Dg2.addNode(node44);
		Dg2.addNode(node55);
		Dg2.addNode(node66);

		//		Dg2.connect(node00.getKey(), node33.getKey(), 4);
		//		Dg2.connect(node00.getKey(), node22.getKey(), 6);
		//		Dg2.connect(node00.getKey(), node11.getKey(), 3);
		//		Dg2.connect(node11.getKey(), node22.getKey(), 2);
		//		Dg2.connect(node33.getKey(), node22.getKey(), 1);
		//		Dg2.connect(node33.getKey(), node44.getKey(), 3);
		//		Dg2.connect(node22.getKey(), node44.getKey(), 2);
		//		Dg2.connect(node44.getKey(), node66.getKey(), 7);
		//		Dg2.connect(node44.getKey(), node55.getKey(), 5);
		Dg2.connect(node00.getKey(), node11.getKey(), 5);
		Dg2.connect(node11.getKey(), node22.getKey(), 5);
		Dg2.connect(node22.getKey(), node33.getKey(), 5);
		Dg2.connect(node33.getKey(), node44.getKey(), 5);
		Dg2.connect(node44.getKey(), node55.getKey(), 5);
		Dg2.connect(node55.getKey(), node66.getKey(), 5);
		Dg2.connect(node66.getKey(), node00.getKey(), 5);

		Ag2.init(Dg2);
		
		System.out.println("Ag2:  "+Ag2.myGraph.HashMapNode.get(node66.getKey()).getKey());
		graph g= new DGraph();
		g=Ag2.copy();		
		System.out.println("g copy:  "+g.getNode(node66.getKey()).getKey());
		//	System.out.println("kkkkk"+Ag2.shortestPathDist(0, 4));
		double x = Ag2.shortestPathDist(0, 4);
		List<node_data> lNd=Ag2.shortestPath(0, 4);
		Iterator<node_data> itList = lNd.iterator(); 
		while (itList.hasNext()) {
			node_data c = (node_data)itList.next();
			System.out.print(c.getKey()+" ");
		}
		System.out.println();
		System.out.println("x "+ x);
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(3);
		List<node_data> ans = new LinkedList<node_data>();
		ans=Ag2.TSP(l);
		
		for (int i = 0; i <ans.size() ; i++) 
		{System.out.println("ans");
			if (i==0)
				System.out.print (ans.get(i).getKey());
			else
				System.out.print (" , "+ans.get(i).getKey());
		}
		System.out.println();
		if (null==Ag2.TSP(l))
			System.out.println("not good");
		else
			System.out.println(" good");
		//
		//		System.out.println("shortest path:  "+ x);
		//		LinkedList<Integer> l = new LinkedList<Integer>();
		//		l.add(1);
		//		l.add(3);
		//		l.add(5);
		//		List<node_data> ll=  Ag2.TSP(l);
		//		System.out.println(ll);
		//		Iterator<node_data> itLi = ll.iterator(); 
		//		while (itLi.hasNext()) 
		//		{
		//			node_data c = (node_data)itLi.next();
		//			System.out.print(c.getKey()+" ");
		//		}
		//		System.out.println();



		///TSP test
		//		graph Dg = new DGraph();
		//		Graph_Algo Ag3 = new Graph_Algo();
		//
		//		Point3D p0 = new Point3D(1, 6, 0);
		//		Point3D p1 = new Point3D(0, 2, 3);
		//		Point3D p2 = new Point3D(1, 4, 0);
		//		Point3D p3 = new Point3D(5, 2, 0);
		//	//	Point3D p4 = new Point3D(6,5, 0);
		//
		//		node_data node0 = new NodeData (0 ,p0 ,5);
		//		node_data node1 = new NodeData(1 ,p1 ,6);
		//		node_data node2 = new NodeData(2 ,p2 ,7);
		//		node_data node3 = new NodeData(3 ,p3 ,8);
		//	//	node_data node4 = new NodeData(4 ,p4 ,8);
		//
		//		Dg.connect(node0.getKey(), node1.getKey(), 9);
		//		Dg.connect(node1.getKey(), node2.getKey(),3);
		//		Dg.connect(node2.getKey(), node3.getKey(), 5);
		//		Dg.connect(node3.getKey(), node0.getKey(), 4);
		//		Dg.connect(node0.getKey(), node1.getKey(), 9);
		//		Dg.connect(node1.getKey(), node0.getKey(), 9);
		//		Dg.connect(node1.getKey(), node4.getKey(), 10);
		//		Dg.connect(node4.getKey(), node3.getKey(), 11);
		//		Dg.connect(node3.getKey(), node2.getKey(), 11);
		//		Dg.connect(node2.getKey(), node1.getKey(), 11);

		//Ag3.init(Dg);

		//	LinkedList<Integer> l = new LinkedList<Integer>();
		//		l.add(1);
		//		l.add(3);
		//		l.add(7);
		//		if (null==Ag3.TSP(l))
		//			System.out.println("not good");
		//		else
		//			System.out.println(" good");

		//		l = new LinkedList<>();
		//		l.add(5);
		//		l.add(3);
		//		l.add(0);
		//		if (null== Ag3.TSP(l))
		//			System.out.println("not good");
		//		else
		//			System.out.println(" good");


		//		List<Integer> l=new LinkedList<Integer>();
		//		l.add(0,0);
		//		l.add(0,3);
		//		l.add(0,5);
		//		System.out.println("lNd2.size()"+l.size());
		//		
		//		List <node_data> lNd2 = new LinkedList<node_data>();
		//		lNd2=Ag2.TSP(l);
		//		
		//		for (int i = 0; i <lNd2.size() ; i++) 
		//		{
		//			System.out.print(" "+lNd2.get(i).getKey());
		//		}
		//		System.out.println();
		//		System.out.println("end Tsp");
		//				Iterator<node_data> itList2 = lNd2.iterator(); 
		//				while (itList2.hasNext())
		//				{
		//					node_data c2 = (node_data)itList2.next();
		//					System.out.print(c2.getKey()+" ");
		//				}


	}


}
