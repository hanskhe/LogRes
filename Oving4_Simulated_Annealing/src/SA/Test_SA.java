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
		Board board = new Board(5,5);
		ArrayList<Boolean> row = new ArrayList<Boolean>();
		row.add(false);
		row.add(false);
		row.add(true);
		row.add(true);
		row.add(false);
		board.getBoard().set(1, row);

		ArrayList<Boolean> row1 = new ArrayList<Boolean>();
		row1.add(false);
		row1.add(false);
		row1.add(true);
		row1.add(false);
		row1.add(true);
		board.getBoard().set(2, row1);

		ArrayList<Boolean> row2 = new ArrayList<Boolean>();
		row2.add(false);
		row2.add(false);
		row2.add(false);
		row2.add(false);
		row2.add(false);
		board.getBoard().set(3, row2);

		board.setNumberOfEggsCurrentlyOnBoard(board.numberOfEggsInBoard());

		System.out.println(Board.objectiveFunction(board,2));


		System.out.println(board.getBoard());

	}


}
