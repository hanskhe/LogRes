package SA;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: HK
 * Date: 20.10.13
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */
public class Board {
	private ArrayList<ArrayList<Boolean>> board;
	private int numberOfEggsCurrentlyOnBoard;



	public Board(int x, int y){
		board = new ArrayList<ArrayList<Boolean>>();
		for (int i = 0; i<x; i++){
			System.out.println("i:" + i);
			board.add(new ArrayList<Boolean>());
			for (int j = 0; j<y; j++){
				System.out.println("j:" + i);
				board.get(i).add(false);
			}
		}
		this.numberOfEggsCurrentlyOnBoard = numberOfEggsInBoard();

	}

	public Board(ArrayList<ArrayList<Boolean>> board){
		 this.board = (ArrayList<ArrayList<Boolean>>) board.clone();
		this.numberOfEggsCurrentlyOnBoard = numberOfEggsInBoard();
	}


	public Board generateNeighbors(){
		//Chose between adding, removing or moving an egg
		Random random = new Random();
		int Choice = random.nextInt(3);
		switch (Choice){
			case 0 :
				System.out.println("AddEgg");
				return new Board(addEgg());
			case 1 :
				System.out.println("RemoveEgg");
				return new Board(removeEgg());
			case 2 :
				System.out.println("MoveEgg");
				return new Board(moveEgg());
			default:
				return null;
		}
	}


	private ArrayList<ArrayList<Boolean>> addEgg(){

		while (true){
			int newX = (int) (Math.random() * board.size());
			int newY = (int) (Math.random() * board.get(newX).size());
			//Insert egg at random empty space
			if (!(board.get(newX).get(newY))){
				ArrayList<Boolean> temp = board.get(newX);
				temp.set(newY,true);
				board.set(newX,temp);
				this.numberOfEggsCurrentlyOnBoard++;
				break;
			}
		}

		return board;

	}

	private ArrayList<ArrayList<Boolean>> removeEgg(){
		if (this.numberOfEggsCurrentlyOnBoard == 0){
			return board;
		}
		while (true){
			int newX = (int) (Math.random() * board.size());
			int newY = (int) (Math.random() * board.get(newX).size());
			//Insert egg at random empty space
			if ((board.get(newX).get(newY))){
				ArrayList<Boolean> temp = board.get(newX);
				temp.set(newY,false);
				board.set(newX,temp);
				this.numberOfEggsCurrentlyOnBoard--;
				break;
			}
		}
		return board;
	}

	private ArrayList<ArrayList<Boolean>> moveEgg(){
		if (this.numberOfEggsCurrentlyOnBoard == 0){
			return board;
		}
		ArrayList<ArrayList<Boolean>> newBoard = new ArrayList<ArrayList<Boolean>>();
		while (true){
			int newX = (int) (Math.random() * board.size());
			int newY = (int) (Math.random() * board.get(newX).size());
			Random random = new Random();
			//Insert egg at random empty space
			if ((board.get(newX).get(newY))){
				System.out.println("Found an egg to move at" + newX + newY);
				int deltaX = random.nextInt(3)-1;
				int deltaY = random.nextInt(3)-1;
				if (checkLeagality(newX, newY, deltaX, deltaY)){

					ArrayList<Boolean> temp1 = board.get(newX+deltaX);
					temp1.set(newY+deltaY, true);

					ArrayList<Boolean> temp2 = board.get(newX);
					temp2.set(newY, false);

					board.set(newX+deltaX, temp1);
					board.set(newX, temp2);
					break;
				}
			}
		}
		return newBoard;
	}

	private boolean checkLeagality(int x, int y, int dx, int dy){
		System.out.println("checkLegality");
		System.out.println("Attempting to move egg" + dx + " " + dy);
		if (x+dx < 0 || x+dx > board.size()-1){
			return false;
		}
		else if (y+dy < 0 || y+dy > board.size()-1){
			return false;
		}
		else if ((board.get(x+dx).get(y+dy))){
		   return false;
		}
		else{
			return true;
		}

	}

	public ArrayList<ArrayList<Boolean>> getBoard(){
		return this.board;
	}

	public static float objectiveFunction(Board node, int numberOfEggs){
		numberOfErrors(node, numberOfEggs);
		return 0;
	}

	private static int numberOfErrors(Board node, int numberOfEggs){
		int errors = 0;
		errors += diagonalErrors(node, numberOfEggs) + horizontalErrors(node, numberOfEggs) + verticalErrors(node, numberOfEggs);
		return errors;
	}

	private static int diagonalErrors(Board node, int numberOfEggs){
		return 0;
	}

	private static int horizontalErrors(Board node, int numberOfEggs){
		 int errorSum = 0;
		 for (int i = 0; i<node.getBoard().size(); i++){
			 ArrayList<Boolean> row = node.getBoard().get(i);
			 int rowSum = 0;
			 for (int j = 0; j<row.size(); j++){
				 if (row.get(j)){
					 rowSum++;
				 }
			 }
			 errorSum += rowSum;
		 }
		 return errorSum;

	}

	private static int verticalErrors(Board node, int numberOfEggs){
		int errorSum = 0;
		for (int i = 0; i<node.getBoard().size(); i++){
			int columnError = 0;
			for (int j = 0; j<node.getBoard().size(); j++){
				if (node.getBoard().get(j).get(i)){
				   columnError++;
				}
			}
			errorSum += columnError;
		}
		return errorSum;
	}

	public int numberOfEggsInBoard(){
	   int numberOfEggs = 0;
		for (int i = 0; i<board.size(); i++){
			for (int j = 0; j<board.size(); j++){
				if (board.get(i).get(j)){
					numberOfEggs++;
				}
			}
		}
		return numberOfEggs;
	}

}

