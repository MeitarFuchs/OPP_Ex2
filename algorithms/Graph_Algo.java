package algorithms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;

import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

import static org.junit.Assert.fail;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DGraph myGraph = new DGraph();
	
	
	public graph getMyGraph()
	{
		return this.myGraph;
	}
	@Override
	public void init(graph g) 
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
			System.out.println("ClassNotFoundException is caught: we can't lode your graph");
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
			System.out.println("IOException is caught- we can't save your graph"); 
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
//			Iterator <node_data> itr = ( this.myGraph.getV()).iterator(); 
//			if (it.hasNext()) 
//			{
//				//System.out.println("hdbcbjvfbuscgdxkjbhvjsgaNAHSGDCJ");
//				node_data nd =  itr.next();
//				//System.out.println("tag node "+nd.getKey()+"is:   "+nd.getTag());
//			}

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
			//	System.out.println("tag node "+nd.getKey()+"is:   "+nd.getTag());
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
		lNd=shortestPath(src, dest);
		System.out.println("dis list :    "+lNd.size());
		if (lNd.isEmpty())
			return -1;
		double dis=this.myGraph.getNode(dest).getWeight();
		System.out.println("////////////////////////////////////////////////////// The dis fron shortPathDis:   "+dis);
		return dis;
	}
	@Override
	public List<node_data> shortestPath(int src, int dest) 
	{


		infinityNodeW();
		initColor(0);
		int count=0;

		Queue<node_data> queueNode = new LinkedList<node_data>();
		List<node_data> shortestPathList=new LinkedList<node_data>();
		node_data srcN = new NodeData(this.myGraph.HashMapNode.get(src));
		node_data destN = this.myGraph.HashMapNode.get(dest);
		System.out.println("src:    "+src+"   dest:    "+dest);
		System.out.println("srcN"+ srcN.getKey());
		System.out.println("destN"+ this.myGraph.HashMapNode.get(dest).getKey());
		if (this.myGraph.HashMapNode.get(src)==null ||  this.myGraph.HashMapNode.get(dest)==null)
		{
			System.out.println("null-1");
			return null;
		}

		//		Iterator<edge_data> itEdgeS = this.myGraph.getE(srcN.getKey()).iterator();
		//		Iterator<edge_data> itEdgeD = this.myGraph.getE(destN.getKey()).iterator();
		//		if( itEdgeS==null || itEdgeD==null )
		//			return null;

		System.out.println("the src "+srcN);
		srcN.setWeight(0);
		System.out.println("the src weight "+ srcN.getWeight());
		srcN.setInfo("");
		queueNode.add(srcN);
		System.out.println("size queue:   "+queueNode.size());
		System.out.println("srcKey:  "+srcN.getKey());
		node_data currNode=srcN;
		System.out.println("currntNode srcKey:  "+currNode.getKey());
		double min=Double.MAX_VALUE;

		//while(!checkAllTag1())
		while(count<1000)	
		{
			System.out.print("I am node:  "+currNode.getKey()+"     ");
			Iterator<edge_data> itEdge = this.myGraph.getE(currNode.getKey()).iterator();


			if (itEdge.hasNext())
			{	
				while (itEdge.hasNext()) 
				{
					edge_data currEdge = (edge_data) itEdge.next();
					System.out.print("i am in dest:  "+this.myGraph.getNode(currEdge.getDest()).getKey());
					if (currNode.getWeight()+currEdge.getWeight() < this.myGraph.getNode(currEdge.getDest()).getWeight() )
					{
						this.myGraph.getNode(currEdge.getDest()).setWeight(currNode.getWeight()+currEdge.getWeight());//update w
						System.out.println("the weight of the ed dest "+ this.myGraph.getNode(currEdge.getDest()).getWeight());
						this.myGraph.getNode(currEdge.getDest()).setInfo(""+currNode.getKey());
					}
					if ((this.myGraph.getNode(currEdge.getDest())).getTag()==0)
						queueNode.add(this.myGraph.getNode(currEdge.getDest()));
				}
			}
			node_data minNode= new NodeData();
			minNode=null;
			node_data temp=new NodeData();
			min=Double.MAX_VALUE;
			//			currNode.setTag(1);
			//			queueNode.remove();
			System.out.println("Queue is empty?  "+!queueNode.isEmpty());

			while (!queueNode.isEmpty()) 
			{
				temp=queueNode.remove();;
				System.out.println("temp:   "+temp.getKey());
				System.out.print("i am minimum:  "+min+"   ");
				//System.out.print("now the first in queue is:   "+queueNode.peek().getKey()+ "  and my w is:  ");
				double t= temp.getWeight();
				System.out.println("my weight" +t);
				if (temp.getKey()!=currNode.getKey())
				{
					if (t<min)
					{
						min=t;
						minNode=this.myGraph.getNode(temp.getKey());
						System.out.println("min node key is:   "+minNode.getKey()+"  and w=:  "+minNode.getWeight());
					}
				}

			}

			//System.out.println("The Min Key:   "+minNode.getKey()+"  and w=:  "+minNode.getWeight());
			System.out.println("crunNode is:   "+currNode.getKey());
			currNode.setTag(1);
			System.out.println("crunNode Tag is:   "+currNode.getTag());
			if (minNode!=null)
			{
				currNode=minNode;
				queueNode.add(currNode);
			}

			System.out.println("now crunNode is:   "+currNode.getKey());
			count++;
		}
		System.out.println("finish all the nodes");
		if (!destN.getInfo().equals(""))
		{
			currNode=this.myGraph.getNode(dest);
			shortestPathList.add(currNode);
		}
		System.out.println("The currnt node thet i want to add to the list:    "+currNode.getKey());
		while(currNode.getKey()!=src)
		{
				shortestPathList.add(this.myGraph.getNode(Integer.parseInt(currNode.getInfo())));
				currNode=this.myGraph.getNode(Integer.parseInt(currNode.getInfo()));
			
		}

		if (shortestPathList.size()==1 )
		{
			shortestPathList=null;
			return null;
		}

		return swapListNode(shortestPathList);

	}
	private List<node_data> swapListNode(List<node_data> l)
	{
		List<node_data> newList=new LinkedList<node_data>();

		for (int i=l.size()-1; i>=0; i--)
		{
			newList.add(l.get(i));
		}
		System.out.println("print from swapListNode:  ");
		Iterator<node_data> itList = newList.iterator(); 
		while (itList.hasNext()) 
		{
			node_data c = (node_data)itList.next();
			System.out.print(c.getKey() + " " );
		}
		System.out.println("byeeee");

		return newList;
	}
	//	public List<node_data> shortestPath11(int src, int dest) 
	//	{
	//		infinityNodeW();
	//		Queue<node_data> qn = new LinkedList<node_data>();
	//
	//		List<node_data> shortestPath=new LinkedList<node_data>();
	//		node_data srcN = this.myGraph.HashMapNode.get(src);
	//		node_data destN = this.myGraph.HashMapNode.get(dest);
	//		srcN.setWeight(0);
	//		srcN.setInfo("");
	//		qn.add(srcN);
	//		node_data currNode=this.myGraph.getNode(src);
	//		while (!qn.isEmpty()) 
	//		{
	//			Iterator<edge_data> itEdge = this.myGraph.getE(currNode.getKey()).iterator(); 
	//			while (itEdge.hasNext()) 
	//			{
	//				edge_data currEdge = (edge_data) itEdge.next();
	//				if (currNode.getWeight()+currEdge.getWeight() < this.myGraph.getNode(currEdge.getDest()).getWeight() )
	//				{
	//					this.myGraph.getNode(currEdge.getDest()).setWeight(currNode.getWeight()+currEdge.getWeight());
	//					System.out.println("Update weight: "+this.myGraph.getNode(currEdge.getDest()).getWeight());
	//					//					if (currNode.getKey()==src)
	//					this.myGraph.getNode(currEdge.getDest()).setInfo(""+currNode.getKey());
	//					//					else
	//					//						this.myGraph.getNode(currEdge.getDest()).setInfo(currNode.getInfo()+","+  Integer.toString(currNode.getKey()));
	//					System.out.println("I am node:  "+currNode.getKey() );
	//					//System.out.println("info dest of"+this.myGraph.getNode(currEdge.getDest()).getKey()+"is: " +this.myGraph.getNode(currEdge.getDest()).getInfo() );
	//					qn.add( this.myGraph.getNode(currEdge.getDest()));
	//				}
	//			}	
	//
	//			while ( !qn.isEmpty() && (qn.peek().getKey() == dest ))
	//			{
	//				qn.remove();
	//				if (!qn.isEmpty())
	//				{	
	//					currNode=qn.peek();
	//				}
	//
	//			}
	//
	//			if (!qn.isEmpty())
	//			{	
	//				qn.remove();
	//
	//				if (!qn.isEmpty())
	//				{	
	//					currNode=qn.peek();
	//				}
	//			}
	//		}
	//		System.out.println("destN weight:     "+destN.getWeight());
	//		String infoDest =this.myGraph.HashMapNode.get(dest).getInfo()+","+destN.getKey();
	//		int len=infoDest.length()/2; 
	//		String[] arr=new String [len];
	//
	//		arr=infoDest.split(",");
	//		for (int i=0; i<arr.length; i++)
	//		{
	//			shortestPath.add(this.myGraph.HashMapNode.get(Integer.parseInt(arr[i])));
	//		}
	//
	//		return shortestPath;
	//	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		LinkedList <node_data> listAns = new LinkedList<node_data>();
		LinkedList <node_data> tempList = new LinkedList<node_data>();
				if (!isConnected()) 
				{
					return TSPnotConnect(targets);
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
				tempList=null;
				System.out.println("targets.get(numList):  "+targets.get(numList));
				System.out.println("targets.get(numList+1):  "+targets.get(numList+1));
				tempList= (LinkedList<node_data>) shortestPath(targets.get(numList), targets.get(numList+1));
				if (tempList.isEmpty())
					return null;
				else
					listAns.addAll(tempList);

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
	
	
	private List<node_data> TSPnotConnect(List<Integer> targets) 
	{
		

     
		return null;
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
		graph Dg = new DGraph();
		Graph_Algo Ag = new Graph_Algo();

		Point3D p0 = new Point3D(1, 6, 0);
		Point3D p1 = new Point3D(0, 2, 3);
		Point3D p2 = new Point3D(1, 4, 0);
		Point3D p3 = new Point3D(5, 2, 0);
		Point3D p4 = new Point3D(6,5, 0);

		node_data node0 = new NodeData (0 ,p0 ,5);
		node_data node1 = new NodeData(1 ,p1 ,6);
		node_data node2 = new NodeData(2 ,p2 ,7);
		node_data node3 = new NodeData(3 ,p3 ,8);
		node_data node4 = new NodeData(4 ,p4 ,8);

		Dg.addNode(node0);
		Dg.addNode(node1);
		Dg.addNode(node2);
		Dg.addNode(node3);
		Dg.addNode(node4);

			Dg.connect(node0.getKey(), node1.getKey(), 9);
		Dg.connect(node1.getKey(), node2.getKey(),3);
		Dg.connect(node2.getKey(), node3.getKey(), 5);
		Dg.connect(node3.getKey(), node0.getKey(), 4);
		Dg.connect(node1.getKey(), node0.getKey(), 2);
		Dg.connect(node1.getKey(), node4.getKey(), 1);
		Dg.connect(node4.getKey(), node3.getKey(), 2);
		Dg.connect(node3.getKey(), node2.getKey(), 6);
		Dg.connect(node2.getKey(), node1.getKey(), 5);

		Ag.init(Dg);

		//		boolean t = Ag.isConnected();
		//		if (!t)
		//		{ 
		//			fail(); 
		//		}

		System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
		List<node_data> lNd = Ag.shortestPath(0, 3);
		Iterator<node_data> itList = lNd.iterator(); 
		while (itList.hasNext()) 
		{
			node_data c = (node_data)itList.next();
			System.out.print(c.getKey() + " " );
		}

		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(1);
		l.add(3);
		l.add(4);		

		if (Ag.TSP(l).isEmpty())
		{ 
			fail(); 
		}

		l = new LinkedList<>();
		l.add(1);
		l.add(3);
		l.add(0);
		if (Ag.TSP(l).isEmpty())
		{ 
			fail(); 
		}
		System.out.println("ooooooooooooooooooooooooo");
		
//// working on shortPath
		//		DGraph DG1 = new DGraph();
		//		Graph_Algo g1 = new Graph_Algo();
		//		NodeData b1 = new NodeData(1,new Point3D (100,200,3),0);
		//		NodeData b2 = new NodeData(2,new Point3D (150,200,3),0);
		//		NodeData b3 = new NodeData( 3,new Point3D (300,450,3),0);
		//		NodeData b4 = new NodeData(4,new Point3D (450,500,3),0);
		//		NodeData b5 = new NodeData(5,new Point3D (320,600,3),0);
		//		NodeData b6 = new NodeData(6,new Point3D (226,260,3),0);
		//		DG1.addNode(b1);
		//		DG1.addNode(b2);
		//		DG1.addNode(b3);
		//		DG1.addNode(b4);
		//		DG1.addNode(b5);
		//		DG1.addNode(b6);
		//		DG1.connect(1,2,1);
		//		DG1.connect(2,3,2);
		//		DG1.connect(3,4,3);
		//		DG1.connect(4,5,4);
		//		DG1.connect(5,6,5);
		//		DG1.connect(1,6,16);
		//		g1.init(DG1);
		//		boolean flag = 15 == g1.shortestPathDist(1,6);
		//		List<node_data> lNd=g1.shortestPath(1, 6);
		//		if (lNd.isEmpty())
		//			System.out.println("the list from shourt path is EMPTY");
		//		System.out.println("print from the main:  ");
		//		Iterator<node_data> itList = lNd.iterator(); 
		//		while (itList.hasNext()) 
		//		{
		//			node_data c = (node_data)itList.next();
		//			System.out.print(c.getKey() + " " );
		//		}
		//		if (!flag)
		//			System.out.println("dis not working");
		//	
		//////////////////////////////////////////////////////////////

	}
}
