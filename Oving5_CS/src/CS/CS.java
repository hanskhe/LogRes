package CS;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: HK
 * Date: 04.11.13
 * Time: 08:59
 * To change this template use File | Settings | File Templates.
 */
public class CS {

	private Node node;
	static long startTime;

	public static void main (String[] args){
		//For timing purposes
		//Timing code was found online at http://stackoverflow.com/questions/2572868/how-to-time-java-program-execution-speed

		startTime = System.currentTimeMillis();

		CS cs = new CS();
		//Intialize a board with randomly placed queens
		cs.node = new Node(30,30,1);
		//cs.CS_alg();
		cs.node.printBoard();
		int[] x = (cs.node.diagConflicts(2));
		for (int i = 0; i<x.length; i++){
			System.out.println(x[i]);
		}
		cs.CS_alg();
	}

	public void CS_alg(){
		Random random = new Random();
		while(true){
			int row = random.nextInt(this.node.getBoard().size());
			ArrayList<Boolean> CurrentRow = this.node.getBoard().get(row);
			int queenPos = 0;
			for (int i = 0; i<this.node.getBoard().size();i++){
				if (this.node.getBoard().get(row).get(i)){
					queenPos = i;
				}
			}
			int bestCell;
			bestCell = this.node.bestCell(row,queenPos);
			System.out.println("We are in row " + row + "and should move from " + queenPos + "to " + bestCell);


			//Move queen to designated cell

			CurrentRow.set(queenPos,false);
			CurrentRow.set(bestCell,true);

			this.node.getBoard().set(row,CurrentRow);

			//this.node.printBoard();

			//System.out.println(this.node.colConflicts(2));
			if (Node.numberOfErrors(this.node,1)==0){
				System.out.println("OMFG, You made it!");
				break;
			}
		}
		this.node.printBoard();

		//For timing purposes
		long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime) );
	}
}
