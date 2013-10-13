package aStar;

public class Boards {
	public static int[][] Easy = {{0,2,2,2},{0,0,4,3}, {0,3,4,2}, {0,4,1,2}, {1,2,0,2},{1,4,2,2}};
	public static int[][] Medium = {{0,1,2,2},{0,0,5,3}, {0,1,3,2}, {0,3,0,2}, {1,0,2,3},{1,2,0,2},{1,3,1,2},{1,3,3,3},{1,4,2,2},{1,5,0,2},{1,5,2,2}};
	public static int[][] Hard = {{0,2,2,2},{0,0,4,2}, {0,0,5,2}, {0,2,5,2}, {0,4,0,2},{1,0,0,3},{1,1,1,3},{1,2,0,2},{1,3,0,2},{1,4,2,2},{1,4,4,2},{1,5,3,3}};
	public static int[][] Expert = {{0,0,2,2},{0,0,1,3}, {0,0,5,2}, {0,1,0,2}, {0,2,3,2},{0,3,4,2},{1,0,3,2},{1,2,4,2},{1,3,0,3},{1,4,0,2},{1,4,2,2},{1,5,2,2},{1,5,4,2}};
	
	
	public static int[][] getMap(int i){
		switch(i){
		case 0:
			return Easy;
			
		case 1:
			return Medium;
		
		case 2:
			return Hard;
			
		case 3:
			return Expert;
		default:
			return null;
		}
	}

}
