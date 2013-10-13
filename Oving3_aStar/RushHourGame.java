package aStar;

import java.util.ArrayList;

public class RushHourGame {
	private static ArrayList<ArrayList<Car>> map = new ArrayList<ArrayList<Car>>();

	public static void main(String args[]){
		initializeMap(0);
		Node firstNode = new Node(0,null,new Board(map));
		int movesToGoal = Best_first_search.find_path(firstNode);
		System.out.println("Number of moves to goal: " + movesToGoal);
		System.out.println("Number of nodes that where created: " + Best_first_search.creatednodes);
		//movesToGoal = BFS.solve(firstNode);
		//System.out.println(movesToGoal);
		//System.out.println(BFS.numberOfCreatedNodes);
//		movesToGoal = DFS.solve(firstNode);
//		System.out.println(movesToGoal);
//		System.out.println(DFS.numberOfCreatedNodes);
	}

	
	public static void initializeMap(int mapNumber){
		int[][] table = Boards.getMap(mapNumber);
		//Create an ArrayList<ArrayList<Car>> filled with null
		for (int y = 0; y<6; y++){
			ArrayList<Car> temp = new ArrayList<Car>();
			for (int x = 0; x<6; x++){
				temp.add(null);
			}
			map.add(temp);
		}
		//Add the cars to the ArrayList
		for (int y = 0; y<table.length; y++){
			Car car = new Car(y,table[y][3], table[y][0]==0);
			if (car.iSHorisontal()){
				for (int i = 0; i<car.getSize(); i++){
					map.get(table[y][2]).set(table[y][1]+i, car);
				}
			}
			else{
				for (int i = 0; i<car.getSize(); i++){
					map.get(table[y][2]+i).set(table[y][1], car);
				}
			}
		}
		System.out.println(map);
	}

}

