package aStar;

import java.awt.Point;
import java.util.ArrayList;


public class Board {
	public ArrayList<ArrayList<Car>> board;
	private ArrayList<Car> checked;
	
	public Board(ArrayList<ArrayList<Car>> board){
		this.board = board;
		this.checked = new ArrayList<Car>();
	}
	
	public ArrayList<Board> allBoards(){
		checked = new ArrayList<Car>();
		int x, y;
		//Alle mulig brett lagres i allBoards
		ArrayList<Board> allBoards = new ArrayList<Board>();
		
		//Går igjennom hver bil, og ser på alle mulige flytt for denne bilen. Når et mulig flytt er oppdaget kopieres brettet,
		//og det nye brettet legges inn i allBoards
		
		y = 0;
		for (ArrayList<Car> row : board ){
			x = 0;
			for (Car car : row){
				if (car != null && !checked.contains(car)){
					checked.add(car);
					if (car.iSHorisontal()){
						//Bilen er horisontal
						if(x>0){
							if(board.get(y).get(x-1)==null){
								//Cellen til venstre for bilen er ledig
								//Genrer dyp kopi
								ArrayList<ArrayList<Car>> newBoard = generateDeepCopy();
								allBoards.add(car.left(new Board(newBoard),new Point(x,y)));
							}
						}
						if (x+car.getSize()-1<row.size()-1){
							if(board.get(y).get(x+car.getSize())==null){
								ArrayList<ArrayList<Car>> newBoard = generateDeepCopy();
								allBoards.add(car.right(new Board(newBoard), new Point(x,y)));
							}
						}
					else{
						//Bilen er ikke horisontal
						if (y>0){
							if (board.get(y-1).get(x)==null){
								ArrayList<ArrayList<Car>> newBoard = generateDeepCopy();
								allBoards.add(car.up(new Board(newBoard), new Point(x,y)));
							}
						}
						if (y+car.getSize()-1<row.size()-1){
							if(board.get(y+car.getSize())==null){
								ArrayList<ArrayList<Car>> newBoard = generateDeepCopy();
								allBoards.add(car.down(new Board(newBoard), new Point(x,y)));
							}
						}
					}
				}
			}
			x++;
		}
		y++;
	}
	return allBoards;
}
	
	private ArrayList<ArrayList<Car>> generateDeepCopy(){
		//For å sikre at det ikke kopieres over objekter når det er meningen å skape nye
		//lager denne funksjonen en fullverdig kopi av alle objekter
		ArrayList<ArrayList<Car>> deepCopy = new ArrayList<ArrayList<Car>>();
		for (ArrayList<Car> row : board){
			ArrayList<Car> copiedRow = new ArrayList<Car>();
			for (Car car : row){
				copiedRow.add(car);
			}
			deepCopy.add(copiedRow);
		}
		return deepCopy;
	}
}
//Ved behov for utskrift av brett
//	public String toString(){
//		String out = "";
//		for (ArrayList<Car> row : board){
//			for (Car car : row){
//				if(car==null){
//					out+="n";
//				}
//				else{
//					out+=Integer.toString(car.getID());
//				}
//			}
//			out+="\n";
//		}
//		return out;
//	}
//	
//}
