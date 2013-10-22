package SA;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created with IntelliJ IDEA.
 * User: HK
 * Date: 21.10.13
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class Test_SA {

	public static void main(String[] args){

		//[[false, true, false, false, true, false], [true, false, false, false, false, true], [true, false, true, false, false, true],


		Board board = new Board(6,6,2);

		ArrayList<Boolean> row0 = new ArrayList<Boolean>();
		row0.add(false);
		row0.add(true);
		row0.add(false);
		row0.add(false);
		row0.add(true);
		row0.add(false);
		board.getBoard().set(0, row0);

		ArrayList<Boolean> row = new ArrayList<Boolean>();
		row.add(true);
		row.add(false);
		row.add(false);
		row.add(false);
		row.add(false);
		row.add(true);
		board.getBoard().set(1, row);

		ArrayList<Boolean> row1 = new ArrayList<Boolean>();
		row1.add(true);
		row1.add(false);
		row1.add(true);
		row1.add(false);
		row1.add(false);
		row1.add(true);
		board.getBoard().set(2, row1);
		//[true, false, true, false, true, false], [false, false, true, true, false, true], [true, false, true, true, true, true]]
		ArrayList<Boolean> row2 = new ArrayList<Boolean>();
		row2.add(true);
		row2.add(false);
		row2.add(true);
		row2.add(false);
		row2.add(true);
		row2.add(false);
		board.getBoard().set(3, row2);

		ArrayList<Boolean> row3 = new ArrayList<Boolean>();
		row3.add(false);
		row3.add(false);
		row3.add(true);
		row3.add(true);
		row3.add(false);
		row3.add(true);
		board.getBoard().set(4, row3);

		ArrayList<Boolean> row4 = new ArrayList<Boolean>();
		row4.add(true);
		row4.add(false);
		row4.add(true);
		row4.add(true);
		row4.add(true);
		row4.add(true);
		board.getBoard().set(5, row4);



		System.out.println("There are # number of eggs: " + board.numberOfEggsInBoard());
		board.setNumberOfEggsCurrentlyOnBoard(board.numberOfEggsInBoard());


		System.out.println("Score: " + Board.objectiveFunction(board,2));


		System.out.println(board.getBoard());

	}


}
