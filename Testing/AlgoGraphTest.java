package Testing;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

class AlgoGraphTest 
{
	@Test
	public void initGTest() {
		Graph_Algo Ag = new Graph_Algo();
		DGraph Dg = new DGraph();

		Point3D p1 = new Point3D(2,5,7);
		Point3D p2 = new Point3D(3,6,8);
		NodeData n1 = new NodeData(1,p1,0);
		NodeData n2 = new NodeData(2,p1,0);
		NodeData n3 = new NodeData(3,p1,0);
		NodeData n4 = new NodeData(4,p2,0);
		NodeData n5 = new NodeData(5,p2,0);
		NodeData n6 = new NodeData(6,p2,0);

		Dg.addNode(n1);
		Dg.addNode(n2);
		Dg.addNode(n3);
		Dg.addNode(n4);
		Dg.addNode(n5);
		Dg.addNode(n6);

		Dg.connect(n1.getKey(), n2.getKey(),6);
		Dg.connect(n1.getKey(), n3.getKey(),7);
		Dg.connect(n1.getKey(), n4.getKey(),8);
		Dg.connect(n6.getKey(), n2.getKey(),9);
		Dg.connect(n6.getKey(), n5.getKey(),10);
		Dg.connect(n6.getKey(), n4.getKey(),11);

		Ag.init(Dg);

		if (Ag.getMyGraph().getNode(n1.getKey()).getKey() != n1.getKey())
		{fail();};


	}

	//	@Test
	//	public void initFileTest() {
	//		Graph_Algo Ag = new Graph_Algo();
	//		DGraph Dg = new DGraph();
	//
	//		Point3D p1 = new Point3D(2,5,7);
	//		Point3D p2 = new Point3D(3,6,8);
	//		NodeData n1 = new NodeData(1,p1,0);
	//		NodeData n2 = new NodeData(2,p1,0);
	//		NodeData n3 = new NodeData(3,p1,0);
	//		NodeData n4 = new NodeData(4,p2,0);
	//		NodeData n5 = new NodeData(5,p2,0);
	//		NodeData n6 = new NodeData(6,p2,0);
	//
	//		Dg.addNode(n1);
	//		Dg.addNode(n2);
	//		Dg.addNode(n3);
	//		Dg.addNode(n4);
	//		Dg.addNode(n5);
	//		Dg.addNode(n6);
	//
	//		Dg.connect(1,2,6);
	//		Dg.connect(1,3,7);
	//		Dg.connect(1,4,8);
	//		Dg.connect(6,2,9);
	//		Dg.connect(6,5,10);
	//		Dg.connect(6,4,11);
	//		String nameF="fileToSave";
	//		String nameF1="fileToSave1";
	//		Ag.init(Dg);
	//		Ag.save(nameF);
	//
	//		Graph_Algo Ag1 = new Graph_Algo();
	//		Ag1.init(nameF1);
	//		if( Ag.isConnected() != Ag1.isConnected())
	//		{ 
	//			fail(); 
	//		}
	//
	//		if( Ag.shortestPath(1, 3)!= Ag1.shortestPath(1, 3))
	//		{ 
	//			fail(); 
	//		}
	//
	//		if( Ag.getMyGraph().getEdge(6,4).getWeight() != Ag1.getMyGraph().getEdge(6,4).getWeight())
	//		{ 
	//			fail(); 
	//		}
	//		Boolean flag = Ag.getMyGraph().getEdge(1,2).getWeight() == Ag1.getMyGraph().getEdge(1,2).getWeight();
	//		Boolean flag2 = Ag.isConnected() == Ag1.isConnected();
	//		assertEquals(true,flag);
	//		assertEquals(true,flag2);
	//
	//	}
	//
	//	@Test
	//	public void save() {
	//		Graph_Algo g = new Graph_Algo();
	//		DGraph Dg = new DGraph();
	//		Node n1 = new Node(new Point3D(100,200,3) );
	//		Node n2 = new Node(new Point3D (150,200,3));
	//		Node n3 = new Node(new Point3D(300,450,3));
	//		Node n4 = new Node(new Point3D (450,500,3));
	//		Node n5 = new Node(new Point3D (320,600,3));
	//		Node n6 = new Node(new Point3D (226,260,3));
	//		Dg.addNode(n1);
	//		Dg.addNode(n2);
	//		Dg.addNode(n3);
	//		Dg.addNode(n4); 
	//		Dg.addNode(n5);
	//		Dg.addNode(n6);
	//		Dg.connect(1,2,5);
	//		Dg.connect(1,3,5);
	//		Dg.connect(1,4,5);
	//		Dg.connect(6,2,5);
	//		Dg.connect(6,5,5);
	//		Dg.connect(6,4,5);
	//		g.init(DG);
	//		g.save("TestSave");
	//		Graph_Algo g2 = new Graph_Algo();
	//		g2.init("TestSave");
	//		Boolean flag = g.getGraph().getEdge(1,2).getWeight() == g2.getGraph().getEdge(1,2).getWeight();
	//		Boolean flag2 = g.isConnected() == g2.isConnected();
	//		assertEquals(true,flag);
	//		assertEquals(true,flag2);
	//	}
	@Test
	void isConnectTest() 
	{
		graph Dg = new DGraph();
		graph DgC = new DGraph();
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

		Dg.connect(node1.getKey(), node2.getKey(), 11);
		Dg.connect(node2.getKey(), node3.getKey(), 11);
		Dg.connect(node3.getKey(), node2.getKey(), 11);
		Dg.connect(node3.getKey(), node4.getKey(), 11);
		Dg.connect(node4.getKey(), node3.getKey(), 11);

		DgC.connect(node0.getKey(), node1.getKey(), 9);
		DgC.connect(node1.getKey(), node0.getKey(), 9);
		DgC.connect(node1.getKey(), node4.getKey(), 10);
		DgC.connect(node4.getKey(), node3.getKey(), 11);
		DgC.connect(node3.getKey(), node2.getKey(), 11);
		DgC.connect(node2.getKey(), node1.getKey(), 11);

		Ag.init(Dg);
		boolean ansNotCon= Ag.isConnected();
		if (ansNotCon)
		{ fail();}
		Ag.init(DgC);
		boolean ansCon= Ag.isConnected();
		if (!ansCon)
		{ fail();}
	}
	@Test
	public void shortestPath() 
	{
		Graph_Algo Ag = new Graph_Algo();
		DGraph Dg = new DGraph();
		NodeData n1 = new NodeData(1,new Point3D (100,200,3),0);
		NodeData n2 = new NodeData(2,new Point3D (150,200,3),0);
		NodeData n3 = new NodeData( 3,new Point3D (300,450,3),0);
		NodeData n4 = new NodeData(4,new Point3D (450,500,3),0);
		NodeData n5 = new NodeData(5,new Point3D (320,600,3),0);
		NodeData n6 = new NodeData(6,new Point3D (226,260,3),0);
		Dg.addNode(n1);
		Dg.addNode(n2);
		Dg.addNode(n3);
		Dg.addNode(n4);
		Dg.addNode(n5);
		Dg.addNode(n6);
		Dg.connect(1,2,2);
		Dg.connect(2,3,2);
		Dg.connect(3,4,2);
		Dg.connect(4,5,2);
		Dg.connect(5,6,2);
		Dg.connect(6,1,2);
		Ag.init(Dg);

		if (Ag.shortestPath(1,6).isEmpty())
		{fail();}
		if (Ag.shortestPathDist(1,6)!=10)
		{fail();}
		Dg.connect(1,2,1);
		Dg.connect(2,3,1);
		Dg.connect(1,6,12);

		if (Ag.shortestPath(1,6).isEmpty())
		{fail();}

		if (Ag.shortestPathDist(1,6)!=10)
		{fail();}

		DGraph Dg1 = new DGraph();
		Graph_Algo Ag1 = new Graph_Algo();
		Dg1.addNode(n1);
		Dg1.addNode(n2);
		Dg1.addNode(n3);
		Dg1.addNode(n4);
		Dg1.addNode(n5);
		Dg1.addNode(n6);
		Dg1.connect(1,2,2);
		Dg1.connect(2,3,2);
		Dg1.connect(3,4,2);
		Dg1.connect(4,5,2);
		Dg1.connect(5,6,2);
		Dg1.connect(1,6,11);
		Ag1.init(Dg1);
		LinkedList<node_data> ansList= new LinkedList<node_data>();
		ansList=(LinkedList<node_data>) Ag.shortestPath(1,6);
		if (ansList.isEmpty())
		{fail();}
		if (ansList.size()!=6)
		{fail();}
		
		if (Ag.shortestPathDist(1,6)!=10)
		{fail();}
		
		String sopos="1 2 3 4 5 6 ";
		String ans="";
		Iterator<node_data> itList = ansList.iterator(); 
		while (itList.hasNext()) 
		{
			node_data c = (node_data)itList.next();
			ans+=(c.getKey()+" ");
		}
		
		if (!ans.equals(sopos))
		{fail();}
	}
//		@Test
//		public void shortestPath()
//		{
//			Graph_Algo Ag = new Graph_Algo();
//			DGraph Dg = new DGraph();
//			NodeData n1 = new NodeData(1,new Point3D (100,200,3),0);
//			NodeData n2 = new NodeData(2,new Point3D (150,200,3),0);
//			NodeData n3 = new NodeData( 3,new Point3D (300,450,3),0);
//			NodeData n4 = new NodeData(4,new Point3D (450,500,3),0);
//			NodeData n5 = new NodeData(5,new Point3D (320,600,3),0);
//			NodeData n6 = new NodeData(6,new Point3D (226,260,3),0);
//			NodeData n7 = new NodeData(7,new Point3D (245,345,3),0);
//			NodeData n8 = new NodeData(8,new Point3D (200,330,3),0);
//			Dg.addNode(n1);
//			Dg.addNode(n2);
//			Dg.addNode(n3);
//			Dg.addNode(n4);
//			Dg.addNode(n5);
//			Dg.addNode(n6);
//			Dg.addNode(n7);
//			Dg.addNode(n8);
//			Dg.connect(1,2,5);
//			Dg.connect(1,5,2);
//			Dg.connect(1,3,6);
//			Dg.connect(7,6,3);
//			Dg.connect(6,7,3);
//			Dg.connect(5,6,2);
//			Dg.connect(2,7,3);
//			Dg.connect(2,8,8);
//			Dg.connect(3,4,7);
//			
//			Ag.init(Dg);
//			
//			List<node_data> ans = new LinkedList<node_data>();
//			ans = Ag.shortestPath(1,7);
//			
//			if (ans.isEmpty())
//			{fail();}
//			List<NodeData> testList = new LinkedList<NodeData>();
//			testList.add(n1);
//			testList.add(n5);
//			testList.add(n6);
//			testList.add(n7);
//			
//			if (ans.size()!= testList.size())
//			{fail();}
//			
//	//		Iterator<node_data> itList1 = ans.iterator(); 
//	//		Iterator<NodeData> itList2 = testList.iterator(); 
//	//		while (itList1.hasNext() && itList2.hasNext())
//	//		{
//	//			node_data n11 = (node_data)itList1.next();
//	//			node_data n22 = (node_data)itList1.next();
//	//			if (n11.getKey()!=n22.getKey())
//	//			{fail();}
//	//		}
//			int i=0;
//			while (i<testList.size())
//			{
//				if (testList.get(i).getKey()!=ans.get(i).getKey())
//				{fail();}
//			}
//	
//		}
//	
		@Test
		void shortestPathDis()
		{
			graph Dg = new DGraph();
			Graph_Algo Ag = new Graph_Algo();
	
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
	
			Dg.addNode(node00);
			Dg.addNode(node11);
			Dg.addNode(node22);
			Dg.addNode(node33);
			Dg.addNode(node44);
			Dg.addNode(node55);
			Dg.addNode(node66);
	
			Dg.connect(node44.getKey(), node55.getKey(), 5);
			Dg.connect(node00.getKey(), node33.getKey(), 4);
			Dg.connect(node00.getKey(), node11.getKey(), 3);
			Dg.connect(node11.getKey(), node22.getKey(), 2);
			Dg.connect(node22.getKey(), node44.getKey(), 1);
			Dg.connect(node33.getKey(), node44.getKey(), 3);
			Dg.connect(node00.getKey(), node22.getKey(), 6);
			Dg.connect(node44.getKey(), node66.getKey(), 7);
			Dg.connect(node33.getKey(), node22.getKey(), 1);
			Ag.init(Dg);
	
			if (!Ag.shortestPath(5,3).isEmpty())
			{fail();}
			if (Ag.shortestPathDist(5,3)!=-1)
			{fail();}
			if (Ag.shortestPath(3,5).isEmpty())
			{fail();}
			double ans = Ag.shortestPathDist(3,5);
			if (ans!=7)
			{fail();}
			if (Ag.shortestPath(0,6).isEmpty())
			{fail();}
			double ans1 = Ag.shortestPathDist(0,6);
			if (ans1!=13)
			{fail();}
		}
		
	
	//	@Test
	//	void TSPTest() 
	//	{
	//		graph Dg = new DGraph();
	//		Graph_Algo Ag = new Graph_Algo();
	//
	//		Point3D p0 = new Point3D(1, 6, 0);
	//		Point3D p1 = new Point3D(0, 2, 3);
	//		Point3D p2 = new Point3D(1, 4, 0);
	//		Point3D p3 = new Point3D(5, 2, 0);
	//		Point3D p4 = new Point3D(6,5, 0);
	//
	//		node_data node0 = new NodeData (0 ,p0 ,5);
	//		node_data node1 = new NodeData(1 ,p1 ,6);
	//		node_data node2 = new NodeData(2 ,p2 ,7);
	//		node_data node3 = new NodeData(3 ,p3 ,8);
	//		node_data node4 = new NodeData(4 ,p4 ,8);
	//
	//
	//		Dg.addNode(node0);
	//		Dg.addNode(node1);
	//		Dg.addNode(node2);
	//		Dg.addNode(node3);
	//		Dg.addNode(node4);
	//
	//		Dg.connect(node0.getKey(), node1.getKey(), 9);
	//		Dg.connect(node1.getKey(), node2.getKey(),3);
	//		Dg.connect(node2.getKey(), node3.getKey(), 5);
	//		Dg.connect(node3.getKey(), node0.getKey(), 4);
	//		Dg.connect(node1.getKey(), node0.getKey(), 2);
	//		Dg.connect(node1.getKey(), node4.getKey(), 1);
	//		Dg.connect(node4.getKey(), node3.getKey(), 2);
	//		Dg.connect(node3.getKey(), node2.getKey(), 6);
	//		Dg.connect(node2.getKey(), node1.getKey(), 5);
	//
	//		Ag.init(Dg);
	//		
	//		LinkedList<Integer> l = new LinkedList<Integer>();
	//		l.add(1);
	//		l.add(3);
	//		l.add(4);		
	//
	//		if (Ag.TSP(l).isEmpty())
	//		{ 
	//			fail(); 
	//		}
	//
	//		l = new LinkedList<>();
	//		l.add(1);
	//		l.add(3);
	//		l.add(0);
	//		if (Ag.TSP(l).isEmpty())
	//		{ 
	//			fail(); 
	//		}
	//	}
	//
	//	@Test
	//	void copyTest() 
	//	{
	//		graph Dg = new DGraph();
	//		Graph_Algo Ag = new Graph_Algo();
	//
	//		Point3D p00 = new Point3D(1, 6, 0);
	//		Point3D p11 = new Point3D(0, 2, 3);
	//		Point3D p22 = new Point3D(1, 4, 0);
	//		Point3D p33 = new Point3D(5, 2, 0);
	//		Point3D p44 = new Point3D(6,5, 0);
	//		Point3D p55 = new Point3D(4,6, 0);
	//		Point3D p66 = new Point3D(3,5, 0);
	//
	//		node_data node00 = new NodeData(0 ,p00 ,5);
	//		node_data node11 = new NodeData(1 ,p11 ,6);
	//		node_data node22 = new NodeData(2 ,p22,7);
	//		node_data node33 = new NodeData(3 ,p33,8);
	//		node_data node44 = new NodeData(4 ,p44,8);
	//		node_data node55 = new NodeData(5 ,p55,8);
	//		node_data node66 = new NodeData(6 ,p66,8);
	//
	//		Dg.addNode(node00);
	//		Dg.addNode(node11);
	//		Dg.addNode(node22);
	//		Dg.addNode(node33);
	//		Dg.addNode(node44);
	//		Dg.addNode(node55);
	//		Dg.addNode(node66);
	//
	//		Dg.connect(node00.getKey(), node11.getKey(), 5);
	//		Dg.connect(node11.getKey(), node22.getKey(), 5);
	//		Dg.connect(node22.getKey(), node33.getKey(), 5);
	//		Dg.connect(node33.getKey(), node44.getKey(), 5);
	//		Dg.connect(node44.getKey(), node55.getKey(), 5);
	//		Dg.connect(node55.getKey(), node66.getKey(), 5);
	//		Dg.connect(node66.getKey(), node00.getKey(), 5);
	//
	//		Ag.init(Dg);
	//
	//		graph copyGraph= new DGraph();
	//		copyGraph=Ag.copy();		
	//		if (Dg.getNode(node00.getKey()).getKey() != copyGraph.getNode(node00.getKey()).getKey())
	//		{fail();}
	//		if ( copyGraph.getNode(node55.getKey()).getKey()!=node55.getKey())
	//		{fail();}
	//		if ( copyGraph.getNode(node55.getKey()).getTag()!=node55.getTag())
	//		{fail();}
	//	}

}
