package aStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Best_first_search {
	private static ArrayList<Node> closedList = new ArrayList<Node>();
	private static ArrayList<Node> openList = new ArrayList<Node>();
	private int numberOfNodes;
	private Node startNode;
	private static HashSet<Integer> states = new HashSet<Integer>();
	private static ArrayList<Node> children;
	//Brukes for å kunne svare på antallet noder som lages
	public static int creatednodes;

	public Best_first_search(){
		
		
	}
	//Kalles fra Static metode, må være static.
	public static int find_path(Node startNode){
		//Disse beregninger gjøres nå i constructoren til Node
		//startNode = new Node(0, null, board);
		//startNode.setNodeCost(0);
		//startNode.setEstimateCost(0); //Huristic calc needed
		openList.add(startNode);
		Node X;
		while (openList.size() != 0){
			creatednodes++;
			X = openList.remove(0);
			
			//Flytter X til Closed
			closedList.add(X);
			
			//Sjekk om vi er i mål
			if (X.getIsGoal()){
				//Skriver ut path
				System.out.println(X.getBoard());
				boolean done = false;
				Node par = X.getParrent();
				while (!done){
					System.out.println(par.getBoard());
					if (par.getParrent() != null){
						par = par.getParrent();						
					}
					else{
						done = true;
					}
				}
				//Henter ut total kostnad til path
				return X.getTotalCost();
			}
			
			//Hvis vi ikke er i mål, generer barn for node X
			children = X.PopulateChildren();
			
			//Gå igjennom barn
			for (Node child : children){
				creatednodes++;
				if(!states.contains(child.getStatus())){ 
					states.add(child.getStatus());
					openList.add(child);
			}
				Collections.sort(openList);
				//Omgått med HashSet
//				if(!openList.contains(child) && !closedList.contains(child)){
//					
//				}
			}
		}
		//Indikerer feil
		return -1;
	}
}
	
	//
//	private class SortedNodeList {
//
//        private ArrayList<Node> list = new ArrayList<Node>();
//
//        public Node getFirst() {
//                return list.get(0);
//        }
//
//        public void clear() {
//                list.clear();
//        }
//
//        public void add(Node node) {
//                list.add(node);
//                Collections.sort(list);
//        }
//
//        public void remove(Node n) {
//                list.remove(n);
//        }
//
//        public int size() {
//                return list.size();
//        }
//
//        public boolean contains(Node n) {
//                return list.contains(n);
//        }
//}
