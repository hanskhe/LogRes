package CS;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: HK
 * Date: 20.10.13
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */
public class Node {
	private ArrayList<ArrayList<Boolean>> board;
	private int numberOfEggsCurrentlyOnBoard;
	public static int k;
	public static int optimalNumberofEggs;


	public Node(int x, int y, int k){
		board = new ArrayList<ArrayList<Boolean>>();
		for (int i = 0; i<x; i++){
			System.out.println("i:" + i);
			board.add(new ArrayList<Boolean>());
			for (int j = 0; j<y; j++){
				System.out.println("j:" + i);
				board.get(i).add(false);
			}
		}
		PlaceQueens();
		//this.optimalNumberofEggs = board.size()*k;
		//this.k = k;

	}

	public Node(ArrayList<ArrayList<Boolean>> board, int k){
		this.board = new ArrayList<ArrayList<Boolean>>();
		for (int i = 0; i<board.size();i++){
			ArrayList<Boolean> row = new ArrayList<Boolean>();
			for (int j = 0; j<board.size();j++){
				row.add(board.get(i).get(j));
			}
			this.board.add(row);
		}
		//this.board = (ArrayList<ArrayList<Boolean>>) board.clone();

		//this.k = k;
		//this.optimalNumberofEggs = this.board.size()*k;   //This will only work for quadratick boards.
	}


	private void PlaceQueens(){
		Random random = new Random();
		for (int i = 0; i< this.board.size(); i++){
			ArrayList<Boolean> row = this.board.get(i);
			int pos = random.nextInt(this.board.size());
			row.set(pos,true);
			this.board.set(i,row);
		}
	}


	private boolean checkLeagality(int x, int y, int dx, int dy){
		//System.out.println("checkLegality");
		//System.out.println("Attempting to move egg" + dx + " " + dy);
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


	public static int numberOfErrors(Node node, int numberOfEggs){
		int errors = 0;
		errors += diagonalErrors(node, numberOfEggs) + horizontalErrors(node, numberOfEggs) + verticalErrors(node, numberOfEggs);
		return errors;
	}

	private static int diagonalErrors(Node node, int numberOfEggs){
		//System.out.println("Checking for diagonal errors");
		int errorSum = 0;
		int diag1Eggs;
		int diag2Eggs;
		for (int i = -(node.getBoard().size()+1); i<node.getBoard().size(); i++){
			diag1Eggs = 0;
			diag2Eggs = 0;
			for (int j = 0; j<node.getBoard().size()-Math.abs(i); j++){

				if (i<0 && node.getBoard().get(Math.abs(i)+j).get(j)){
					diag1Eggs ++;
				}
				else if (i>=0 && node.getBoard().get(j).get(i+j)){
					diag1Eggs++;
				}

				if (i<0 && node.getBoard().get(Math.abs(i)+j).get(node.getBoard().size()-1-j)){
					diag2Eggs ++;
				}
				else if (i>=0 && node.getBoard().get(j).get(node.getBoard().size()-1 - (i + j))){
					diag2Eggs++;
				}



			}
			if (diag1Eggs > numberOfEggs){
				errorSum++;
			}
			if (diag2Eggs > numberOfEggs){
				errorSum++;
			}
		}
		System.out.println("Diagonal errors found: " + errorSum);
		return errorSum;
	}

	private static int horizontalErrors(Node node, int numberOfEggs){
		//System.out.println("Checking for horizontal errors");
		 int errorSum = 0;
		 for (int i = 0; i<node.getBoard().size(); i++){
			 ArrayList<Boolean> row = node.getBoard().get(i);
			 int rowSum = 0;
			 for (int j = 0; j<row.size(); j++){
				 if (row.get(j)){
					 rowSum++;
				 }
			 }
			 if (rowSum > numberOfEggs){
				errorSum++;
			 }
		 }
		System.out.println("Horizontal errors found: " + errorSum);
		return errorSum;

	}

	private static int verticalErrors(Node node, int numberOfEggs){
		//System.out.println("Checking for vertical errors");
		int errorSum = 0;
		for (int i = 0; i<node.getBoard().size(); i++){
			int columnError = 0;
			for (int j = 0; j<node.getBoard().size(); j++){
				if (node.getBoard().get(j).get(i)){
				   columnError++;
				}
			}
			if (columnError > numberOfEggs){
				errorSum++;
			}
		}
		System.out.println("Vertical errors found: " + errorSum);
		return errorSum;
	}



	public int getK(){
		return k;
	}

	public void printBoard(){
		for (int i = 0; i<this.board.size(); i++){
			System.out.println(this.board.get(i));
		}
	}

	public int[] colConflicts(int row){
		//Finds the number of conflicts for each position in the given row
		//We know that there is just one queen pr row, so we can focus on coloumns and diagonals
		int[] colConflicts = new int[this.board.size()];
		for (int i = 0; i<this.board.size();i++){
			if (i == row){
				continue;
			}
			for (int j = 0; j<this.board.size(); j++){
				if(this.board.get(j).get(i)){
					colConflicts[i]+=1;
				}
			}
		}
		return colConflicts;
	}

	public int[] diagConflicts(int row){
		int[] diagConflicts = new int[this.board.size()];
		for (int j = 0; j<this.board.size();j++){
			int x;
			int y;
			//Search down-right
			//Used to find the start position for where we will check
			if(j<row){
				x=0;
				y=row-j;
			}
			else{
				x=j-row;
				y=0;
			}
			int eggsInDiagonal = 0;
			for(int i = 0; i+x<board.size() && i+y<board.size();i++){
				if(board.get(y+i).get(x+i)==true && y+i!=row){
					diagConflicts[j]+=1;
				}
			}
			//Search down-left
			//Used to find the start position for where we will check
			if(board.size()-1-j<row){
				x=board.size()-1;
				y=row-x+j;
			}
			else{
				x=j+row;
				y=0;
			}
			for(int i = 0; x-i>=0 && i+y<board.size();i++){
				if(board.get(y+i).get(x-i)==true && y+i!=row){
					diagConflicts[j]+=1;
				}
			}

		}
		return diagConflicts;
	}

	public int bestCell(int row, int pos){
		//Find the number of conflicts
		int[] colConflicts = colConflicts(row);
		int[] diagConflicts = diagConflicts(row);
		int[] totalConflicts = new int[colConflicts.length];
		for (int i = 0; i<colConflicts.length;i++){
			totalConflicts[i] = colConflicts[i] + diagConflicts[i];
		}
		//Decide which cell to move to
		int currentConflicts = totalConflicts[pos];
		int better = currentConflicts;
		ArrayList<Integer> betterIndex = new ArrayList<Integer>();
		for (int j = 0; j<totalConflicts.length;j++){
			if (totalConflicts[j] < better){
				better = totalConflicts[j];
				betterIndex.clear();
				betterIndex.add(j);
			}
			else if (totalConflicts[j] == better){
				betterIndex.add(j);
			}
		}
		//If we have the same number of conflicts
		Random random = new Random();
		int tryPos;
		if (currentConflicts == better){
			if ((betterIndex.size() == 1)) {
				return betterIndex.get(0);
			}
			while (true){
				tryPos = betterIndex.get(random.nextInt(betterIndex.size()));
				if (tryPos != pos){
					return tryPos;
				}
			}
		}
		else if (betterIndex.size() > 1){
			return betterIndex.get(random.nextInt(betterIndex.size()));
		}
		else{
			return betterIndex.get(0);
		}
		//return -1;
	}

}

