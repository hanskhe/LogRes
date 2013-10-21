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
		row.add(false);
		row.add(false);
		row.add(false);
		board.getBoard().set(1, row);
		System.out.println(board.getBoard());
		board.generateNeighbors();
		System.out.println(board.numberOfEggsInBoard());

		System.out.println(board.getBoard());
	}


}
