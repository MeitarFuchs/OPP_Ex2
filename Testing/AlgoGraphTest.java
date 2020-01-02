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
//	  @Test
//  public void initGTest() {
//      Graph_Algo Ag = new Graph_Algo();
//      DGraph Dg = new DGraph();
//      
//      Point3D p1 = new Point3D(2,5,7);
//      Point3D p2 = new Point3D(3,6,8);
//      NodeData n1 = new NodeData(1,p1,0);
//      NodeData n2 = new NodeData(2,p1,0);
//      NodeData n3 = new NodeData(3,p1,0);
//      NodeData n4 = new NodeData(4,p2,0);
//      NodeData n5 = new NodeData(5,p2,0);
//      NodeData n6 = new NodeData(6,p2,0);
//      
//      Dg.addNode(n1);
//      Dg.addNode(n2);
//      Dg.addNode(n3);
//      Dg.addNode(n4);
//      Dg.addNode(n5);
//      Dg.addNode(n6);
//      
//      Dg.connect(1,2,6);
//      Dg.connect(1,3,7);
//      Dg.connect(1,4,8);
//      Dg.connect(6,2,9);
//      Dg.connect(6,5,10);
//      Dg.connect(6,4,11);
//      
//      Ag.init(Dg);
//      assertEquals(Dg,Ag.getMyGraph());
//    
//  }

//  @Test
//  public void initFileTest() {
//	  Graph_Algo Ag = new Graph_Algo();
//      DGraph Dg = new DGraph();
//      
//      Point3D p1 = new Point3D(2,5,7);
//      Point3D p2 = new Point3D(3,6,8);
//      NodeData n1 = new NodeData(1,p1,0);
//      NodeData n2 = new NodeData(2,p1,0);
//      NodeData n3 = new NodeData(3,p1,0);
//      NodeData n4 = new NodeData(4,p2,0);
//      NodeData n5 = new NodeData(5,p2,0);
//      NodeData n6 = new NodeData(6,p2,0);
//      
//      Dg.addNode(n1);
//      Dg.addNode(n2);
//      Dg.addNode(n3);
//      Dg.addNode(n4);
//      Dg.addNode(n5);
//      Dg.addNode(n6);
//      
//      Dg.connect(1,2,6);
//      Dg.connect(1,3,7);
//      Dg.connect(1,4,8);
//      Dg.connect(6,2,9);
//      Dg.connect(6,5,10);
//      Dg.connect(6,4,11);
//      String nameF="fileToSave";
//      String nameF1="fileToSave1";
//      Ag.init(Dg);
//      Ag.save(nameF);
//      
//      Graph_Algo Ag1 = new Graph_Algo();
//      Ag1.init(nameF1);
//      if( Ag.isConnected() != Ag1.isConnected())
//      { 
// 			fail(); 
// 		}
//      
//      if( Ag.shortestPath(1, 3)!= Ag1.shortestPath(1, 3))
//      { 
// 			fail(); 
// 		}
//      
//     if( Ag.getMyGraph().getEdge(6,4).getWeight() != Ag1.getMyGraph().getEdge(6,4).getWeight())
//     { 
//			fail(); 
//		}
//      Boolean flag = Ag.getMyGraph().getEdge(1,2).getWeight() == Ag1.getMyGraph().getEdge(1,2).getWeight();
//    Boolean flag2 = Ag.isConnected() == Ag1.isConnected();
//    assertEquals(true,flag);
//    assertEquals(true,flag2);

 // }
//
//  @Test
//  public void save() {
//      Graph_Algo g = new Graph_Algo();
//      DGraph DG = new DGraph();
//      Node n1 = new Node(new Point3D(100,200,3) );
//      Node n2 = new Node(new Point3D (150,200,3));
//      Node n3 = new Node(new Point3D(300,450,3));
//      Node n4 = new Node(new Point3D (450,500,3));
//      Node n5 = new Node(new Point3D (320,600,3));
//      Node n6 = new Node(new Point3D (226,260,3));
//      DG.addNode(n1);
//      DG.addNode(n2);
//      DG.addNode(n3);
//      DG.addNode(n4); 
//      DG.addNode(n5);
//      DG.addNode(n6);
//      DG.connect(1,2,5);
//      DG.connect(1,3,5);
//      DG.connect(1,4,5);
//      DG.connect(6,2,5);
//      DG.connect(6,5,5);
//      DG.connect(6,4,5);
//      g.init(DG);
//      g.save("TestSave");
//      Graph_Algo g2 = new Graph_Algo();
//      g2.init("TestSave");
//      Boolean flag = g.getGraph().getEdge(1,2).getWeight() == g2.getGraph().getEdge(1,2).getWeight();
//      Boolean flag2 = g.isConnected() == g2.isConnected();
//      assertEquals(true,flag);
//      assertEquals(true,flag2);
//  }
//
//  @Test
//  public void shortestPathDist() {
//      Graph_Algo g = new Graph_Algo();
//      DGraph DG = new DGraph();
//      NodeData n1 = new NodeData(1,new Point3D (100,200,3),0);
//      NodeData n2 = new NodeData(2,new Point3D (150,200,3),0);
//      NodeData n3 = new NodeData( 3,new Point3D (300,450,3),0);
//      NodeData n4 = new NodeData(4,new Point3D (450,500,3),0);
//      NodeData n5 = new NodeData(5,new Point3D (320,600,3),0);
//      NodeData n6 = new NodeData(6,new Point3D (226,260,3),0);
//      DG.addNode(n1);
//      DG.addNode(n2);
//      DG.addNode(n3);
//      DG.addNode(n4);
//      DG.addNode(n5);
//      DG.addNode(n6);
//      DG.connect(1,2,5);
//      DG.connect(2,3,5);
//      DG.connect(3,4,5);
//      DG.connect(4,5,5);
//      DG.connect(5,6,5);
//      DG.connect(6,1,5);
//      g.init(DG);
//      boolean flag = 25 == g.shortestPathDist(1,6);
//      assertEquals(true,flag);
//      DG.connect(1,2,1);
//      DG.connect(2,3,2);
//      DG.connect(3,4,3);
//      DG.connect(4,5,4);
//      DG.connect(5,6,5);
//      DG.connect(1,6,10);
//      g.init(DG);
//      flag = 10 == g.shortestPathDist(1,6);
//      assertEquals(true,flag);
      
//      DGraph DG1 = new DGraph();
//      Graph_Algo g1 = new Graph_Algo();
//      NodeData b1 = new NodeData(1,new Point3D (100,200,3),0);
//      NodeData b2 = new NodeData(2,new Point3D (150,200,3),0);
//      NodeData b3 = new NodeData( 3,new Point3D (300,450,3),0);
//      NodeData b4 = new NodeData(4,new Point3D (450,500,3),0);
//      NodeData b5 = new NodeData(5,new Point3D (320,600,3),0);
//      NodeData b6 = new NodeData(6,new Point3D (226,260,3),0);
//      DG1.addNode(b1);
//      DG1.addNode(b2);
//      DG1.addNode(b3);
//      DG1.addNode(b4);
//      DG1.addNode(b5);
//      DG1.addNode(b6);
//      DG1.connect(1,2,1);
//      DG1.connect(2,3,2);
//      DG1.connect(3,4,3);
//      DG1.connect(4,5,4);
//      DG1.connect(5,6,5);
//      DG1.connect(1,6,16);
//      g1.init(DG1);
//   //   System.out.println("kkhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhkk"+g1.shortestPathDist(6,1));
//     // boolean flag = 15 == g1.shortestPathDist(1,6);
//      List<node_data> lNd=g1.shortestPath(1, 6);
//      String ans="";
//      Iterator<node_data> itList = lNd.iterator(); 
//		while (itList.hasNext()) 
//		{
//			node_data c = (node_data)itList.next();
//			ans+=(c.getKey()+" ");
//			System.out.print(c.getKey() + " " );
//		}
   //   System.out.println("flag"+flag);
   //   assertEquals(true,flag);
//  }
	 @Test
  public void shortestPath() //O&N test
	 {
      Point3D x = new Point3D(14,4,0);
      Point3D x2 = new Point3D(-75,14,0);
      Point3D x3 = new Point3D(80,5,0);
      Point3D x4 = new Point3D(1,4,0);
      Point3D x5 = new Point3D(-5,1,0);
      Point3D x6 = new Point3D(8,3,0);
      Point3D x7 = new Point3D(4,1,0);
      Point3D x8 = new Point3D(75,14,0);
      
      NodeData a1 = new NodeData(1,x,0);
      NodeData a2 = new NodeData(2,x2,0);
      NodeData a3 = new NodeData(3,x3,0);
      NodeData a4 = new NodeData(4,x4,0);
      NodeData a5 = new NodeData(5,x5,0);
      NodeData a6 = new NodeData(6,x6,0);
      NodeData a7 = new NodeData(7,x7,0);
      NodeData a8 = new NodeData(8,x8,0);
      DGraph d = new DGraph();
      d.addNode(a1);
      d.addNode(a2);
      d.addNode(a3);
      d.addNode(a4);
      d.addNode(a5);
      d.addNode(a6);
      d.addNode(a7);
      d.addNode(a8);
      d.connect(a1.getKey(),a2.getKey(),5);
      d.connect(a1.getKey(),a5.getKey(),2);
      d.connect(a1.getKey(),a3.getKey(),6);
      d.connect(a3.getKey(),a4.getKey(),7);
      d.connect(a2.getKey(),a8.getKey(),8);
      d.connect(a2.getKey(),a7.getKey(),3);
      d.connect(a5.getKey(),a6.getKey(),2);
      d.connect(a6.getKey(),a7.getKey(),3);
      d.connect(a7.getKey(),a6.getKey(),3);
      Graph_Algo p = new Graph_Algo();
      p.init(d);
      List<node_data> ans = new LinkedList<node_data>();
      ans = p.shortestPath(1,7);
      List<NodeData> test = new LinkedList<NodeData>();
      test.add((NodeData) a1);
      test.add(a5);
      test.add(a6);
      test.add(a7);
      Iterator<NodeData> itList = test.iterator(); 
      System.out.println("welomeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		while (itList.hasNext())
		{
			node_data c = (node_data)itList.next();
			System.out.print(c.getKey()+" ");
			if (itList.hasNext()==false)
				if (7!=c.getWeight())
				{ 
					fail(); 
				}
		}
		
      assertEquals(test.get(0).getKey(),ans.get(0).getKey());
      assertEquals(test.get(1).getKey(),ans.get(1).getKey());
      assertEquals(test.get(2).getKey(),ans.get(2).getKey());
      assertEquals(test.get(3).getKey(),ans.get(3).getKey());
  }
	 
	 @Test
		void shortestPathDis4()
		{ /// algo graph do zdady
		 graph Dg = new DGraph();
			Graph_Algo Ag = new Graph_Algo();

	 Point3D p00 = new Point3D(1, 6, 0);
		Point3D p11 = new Point3D(0, 2, 3);
		Point3D p22 = new Point3D(1, 4, 0);
	 
	 node_data node00 = new NodeData(0 ,p00 ,5);
		node_data node11 = new NodeData(1 ,p11 ,6);
		node_data node22 = new NodeData(2 ,p22,7);

		Dg.addNode(node00);
		Dg.addNode(node11);
		Dg.addNode(node22);

		Dg.connect(node00.getKey(), node11.getKey(), 4);
		Dg.connect(node11.getKey(), node00.getKey(), 6);
		Dg.connect(node11.getKey(), node22.getKey(), 3);
		Dg.connect(node22.getKey(), node11.getKey(), 2);
		Dg.connect(node00.getKey(), node22.getKey(), 2);
		Dg.connect(node22.getKey(), node00.getKey(), 2);
		Ag.init(Dg);
		
		if (Ag.shortestPathDist(0, 2)!=2)
		{ 
			fail(); 
		}
		if (Ag.shortestPath(0, 2).isEmpty())
		{ 
			fail(); 
		}
		
		
		
		
		}
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

		Dg.connect(node00.getKey(), node33.getKey(), 4);
		Dg.connect(node00.getKey(), node22.getKey(), 6);
		Dg.connect(node00.getKey(), node11.getKey(), 3);
		Dg.connect(node11.getKey(), node22.getKey(), 2);
		Dg.connect(node33.getKey(), node22.getKey(), 1);
		Dg.connect(node33.getKey(), node44.getKey(), 3);
		Dg.connect(node22.getKey(), node44.getKey(), 2);
		Dg.connect(node44.getKey(), node66.getKey(), 7);
		Dg.connect(node44.getKey(), node55.getKey(), 5);
		//		Dg2.connect(node00.getKey(), node11.getKey(), 5);
		//		Dg2.connect(node11.getKey(), node22.getKey(), 5);
		//		Dg2.connect(node22.getKey(), node33.getKey(), 5);
		//		Dg2.connect(node33.getKey(), node44.getKey(), 5);
		//		Dg2.connect(node44.getKey(), node55.getKey(), 5);
		//		Dg2.connect(node55.getKey(), node66.getKey(), 5);
		//		Dg2.connect(node66.getKey(), node00.getKey(), 5);

		Ag.init(Dg);

	//	double x = Ag.shortestPathDist(0, 4);
		if (!Ag.shortestPath(5, 3).isEmpty())
		{ 
			fail(); 
		}
		double y = Ag.shortestPathDist(5, 3);
		System.out.println("fffffff"+Ag.shortestPath(5, 3).size());
		if (y!=-1)
		{ 
			fail(); 
		}
//		if (x!=7)
//		{ 
//			fail(); 
//		}
//		String ans="";
//		List<node_data> lNd=Ag.shortestPath(0, 4);
//		Iterator<node_data> itList = lNd.iterator(); 
//		while (itList.hasNext()) {
//			node_data c = (node_data)itList.next();
//			ans+=(c.getKey()+" ");
//		}
//		if (!ans.equals("0 1 2 4 "))
//		{ 
//			fail(); 
//		}

	}



	@Test
	void isConnectTest() 
	{
		graph Dg = new DGraph();
		graph Dg1 = new DGraph();
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

		Dg1.connect(node0.getKey(), node1.getKey(), 9);
		Dg1.connect(node1.getKey(), node0.getKey(), 9);
		Dg1.connect(node1.getKey(), node4.getKey(), 10);
		Dg1.connect(node4.getKey(), node3.getKey(), 11);
		Dg1.connect(node3.getKey(), node2.getKey(), 11);
		Dg1.connect(node2.getKey(), node1.getKey(), 11);

		Ag.init(Dg);
		boolean t = Ag.isConnected();
		if (t)
		{ 
			fail(); 
		}
		Ag.init(Dg1);
		t = Ag.isConnected();
		if (!t)
		{ 
			fail(); 
		}
	}


	@Test
	void TSPTest() 
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
		
//		double x = Ag.shortestPathDist(0,3);
//		System.out.println("ooooooooooooooooooooooooo");
//		String ans="";
//		List<node_data> lNd=Ag.shortestPath(0, 3);
//		Iterator<node_data> itList = lNd.iterator(); 
//		while (itList.hasNext()) {
//			node_data c = (node_data)itList.next();
//			ans+=(c.getKey()+" ");
//		}
//		if (!ans.equals(""))
//		{ 
//			fail(); 
//		}
//		if (x!=-1)
//		{ 
//			fail(); 
//		}
		
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
	}



	@Test
	void copyTest() 
	{
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

		Dg2.connect(node00.getKey(), node11.getKey(), 5);
		Dg2.connect(node11.getKey(), node22.getKey(), 5);
		Dg2.connect(node22.getKey(), node33.getKey(), 5);
		Dg2.connect(node33.getKey(), node44.getKey(), 5);
		Dg2.connect(node44.getKey(), node55.getKey(), 5);
		Dg2.connect(node55.getKey(), node66.getKey(), 5);
		Dg2.connect(node66.getKey(), node00.getKey(), 5);

		Ag2.init(Dg2);

		graph g= new DGraph();
		g=Ag2.copy();		
		if (Dg2.getNode(node66.getKey()).getKey() != g.getNode(node66.getKey()).getKey())
		{ 
			fail(); 
		}
		if ( g.getNode(node55.getKey()).getKey()!=5)
		{ 
			fail(); 
		}
	}

}
