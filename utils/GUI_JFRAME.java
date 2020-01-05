package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.NodeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;


public class GUI_JFRAME extends JFrame implements ActionListener, MouseListener
{
	private graph Dg= new DGraph();
	 private Graph_Algo Ag = new Graph_Algo();
   
    private int mc;

	public GUI_JFRAME(graph graph)
	{
		if (graph==null)
			throw new RuntimeException("the graph is null");
		this.Dg= new DGraph((DGraph) graph);
		 this.mc=this.Dg.getMC();
		 this.Ag.init(this.Dg);
		initGUI(graph);
	}

	private void initGUI(graph graph) 
	{
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);

		this.Dg=graph;
		
		MenuBar menuBar = new MenuBar();
		
		Menu file = new Menu("file");
		menuBar.add(file);
		this.setMenuBar(menuBar);
		Menu graph_algo = new Menu("graph algo");
		menuBar.add(graph_algo);

		MenuItem item1 = new MenuItem("save to file");
		item1.addActionListener(this);
		MenuItem item2 = new MenuItem("load from file");
		item2.addActionListener(this);
		
		MenuItem item3 = new MenuItem("Paint my Graph Algo");
		item3.addActionListener(this);
		MenuItem item4 = new MenuItem("Is the graph Conncected?");
		item4.addActionListener(this);
		MenuItem item5 = new MenuItem("shortest path");
		item5.addActionListener(this);
		MenuItem item6 = new MenuItem("tsp");
		item6.addActionListener(this);

		file.add(item1);
		file.add(item2);
		graph_algo.add(item3);
		graph_algo.add(item4);
		graph_algo.add(item5);
		graph_algo.add(item6);
		
		this.addMouseListener(this);
	}
	
	public node_data getNodeDest(int keyDest) ///maybe to delete the function
	{
		boolean flag=false;
		node_data nd=null;
		Iterator<node_data> it = this.Dg.getV().iterator(); 
		while (it.hasNext() && flag==false) 
		{
			nd = it.next();
			if (nd.getKey()==keyDest) {
				flag = true;
			}

		}
		return nd;
	}
	
	public void paint(Graphics dg)
	{
		super.paint(dg);

		if (dg!=null)
		{// paint nodes
			Iterator<node_data> itNode = Dg.getV().iterator(); 
			while (itNode.hasNext())
			{
				node_data nd = itNode.next();
				//draw nodes
				dg.setColor(Color.blue);
				((Graphics) dg).fillOval((int)nd.getLocation().x(), (int)nd.getLocation().y(), 10, 10);
				//write the key node
				dg.setColor(Color.BLUE);
				dg.drawString(""+  nd.getKey() , (int)nd.getLocation().x()+1 , (int)nd.getLocation().y()+1 );

				if (Dg.edgeSize()!=0)
				{// if there are edge - paint edge
					Iterator<edge_data> itEdge = Dg.getE(nd.getKey()).iterator(); 
						while (itEdge.hasNext()) 
						{
							edge_data ed = (edge_data) itEdge.next();
							//draw the edges
							dg.setColor(Color.PINK);
							Point3D pSrc=new Point3D((int)nd.getLocation().x(), (int)nd.getLocation().y());
							node_data destNode = Dg.getNode(ed.getDest());
					//		node_data destNode = getNodeDest(ed.getDest());
							Point3D pDest= new Point3D((int)destNode.getLocation().x(), (int)destNode.getLocation().y());
							((Graphics2D) dg).setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
							(dg).drawLine(pSrc.ix(), pSrc.iy(),pDest.ix(), pDest.iy());
							//write the w edge
							String wString = "";
							wString+=String.valueOf(ed.getWeight());
							dg.drawString(wString, ((int)pSrc.ix()+pDest.ix())/2 , ((int)pSrc.iy()+pDest.iy())/2 );
							
							dg.setColor(Color.YELLOW);
							dg.fillOval((int)(0.9*pDest.ix()+0.1*pSrc.ix()) , (int)(0.9*pDest.iy()+0.1*pSrc.iy()) , 10 , 10 );
						}

					}
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Graph_Algo AgNew=new Graph_Algo();
			String str = e.getActionCommand();
			JFileChooser choose;
			
			if(str.equals("Paint my Graph Algo"))
			{
				initGUI(this.Dg);				
			}
			
			if(str.equals("save to file"))
			{
				AgNew=new Graph_Algo();	
				AgNew.init(this.Dg);
				
				choose= new JFileChooser(FileSystemView.getFileSystemView());
				choose.setDialogTitle("Save graph to a file now..");
				int userChoose = choose.showSaveDialog(null);
				
				if (userChoose == JFileChooser.APPROVE_OPTION) 
				{
					System.out.println("Saved as a file:   " + choose.getSelectedFile().getAbsolutePath());
					AgNew.save(choose.getSelectedFile().getAbsolutePath());
				}
			}
			if(str.equals("load from file"))
			{
				AgNew=new Graph_Algo();

				choose= new JFileChooser(FileSystemView.getFileSystemView());
				choose.setDialogTitle("Init a graph out FROM file now..."); 
				int userChoose = choose.showOpenDialog(null);
				
				if(userChoose == JFileChooser.APPROVE_OPTION)
				{
					System.out.println("the file that choosen to open is:   " + choose.getSelectedFile().getName());
					AgNew.init(choose.getSelectedFile().getAbsolutePath());
					graph theNewGraph=AgNew.copy();
					initGUI(theNewGraph);	
				}
			}
		
	   	       repaint();

		}
	
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");

		}

		//@Override
		//		public void mousePressed(MouseEvent e) {
		//			int x = e.getX();
		//			int y = e.getY();
		//			Point3D p = new Point3D(x,y);
		//			points.add(p);
		//			repaint();
		//			System.out.println("mousePressed");
		//			
		//		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("mouseReleased");

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("mouseEntered");

		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("mouseExited");
		}


		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
		
		public static void main(String[] args) 
		{
			graph Dgraph = new DGraph();
			
			Point3D p1 = new Point3D(200, 200, 0);
			Point3D p2 = new Point3D(250, 189, 0);
			Point3D p3 = new Point3D(167, 470, 0);
			Point3D p4 = new Point3D(390, 178, 0);
			Point3D p5 = new Point3D(350, 279, 0);
			Point3D p6 = new Point3D(278, 590, 0);
			Point3D p7 = new Point3D(240, 690, 0);

			node_data n1 = new NodeData(1, p1, 9);
			node_data n2 = new NodeData(2, p2, 10);
			node_data n3 = new NodeData(3, p3, 11);
			node_data n4 = new NodeData(4, p4, 12);
			node_data n5 = new NodeData(5, p5, 13);
			node_data n6 = new NodeData(6, p6, 14);
			node_data n7 = new NodeData(7, p7, 15);
			
			Dgraph.addNode(n1);
			Dgraph.addNode(n2);
			Dgraph.addNode(n3);
			Dgraph.addNode(n4);
			Dgraph.addNode(n5);
			Dgraph.addNode(n6);
		
			Dgraph.connect(n1.getKey(), n2.getKey(), 3.4);
			Dgraph.connect(n1.getKey(), n5.getKey(), 5);
			Dgraph.connect(n2.getKey(), n1.getKey(), 5.8);
			Dgraph.connect(n2.getKey(), n3.getKey(), 4);
			Dgraph.connect(n3.getKey(), n1.getKey(), 4);
			Dgraph.connect(n5.getKey(), n3.getKey(), 2.09);
			Dgraph.connect(n5.getKey(), n1.getKey(), 2.87);
			Dgraph.connect(n6.getKey(), n4.getKey(), 3.89);
			Dgraph.connect(n6.getKey(), n2.getKey(), 12);
			
			GUI_JFRAME GuiG= new GUI_JFRAME(Dgraph);
			GuiG.setVisible(true);
			
			Dgraph.removeNode(n6.getKey());
			GuiG.repaint();
			Dgraph.removeEdge(n5.getKey(), n3.getKey());
			GuiG.repaint();
			Dgraph.addNode(n7);
			GuiG.repaint();
			Dgraph.connect(n7.getKey(),n2.getKey(),20);
			GuiG.repaint();

		}

	}



