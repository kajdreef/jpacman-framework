package nl.tudelft.jpacman.scenarios;


import nl.tudelft.jpacman.Launcher;
import org.junit.Before;
import org.junit.Test;

/**
 * As a ghost;
 * I get automatically moved around;
 * So that I can try to kill the player.
 */
public class GhostMoveTest {

    private Launcher launcher;

    @Before
    public void startGame(){
        launcher = new Launcher();
        launcher.launch();
    }

    /**
     * Scenario S3.1: A ghost moves.
     */
    @Test
    public void ghostMovesTest(){
//    and  a ghost is next to an empty cell;

//    When  a tick event occurs;


//    Then  the ghost can move to that cell.

    }


    /**
     * Scenario S3.2: The ghost moves over a square with a pellet.
     */
    @Test
    public void ghostMovesOverSquareWithPelletTest(){
//    and  a ghost is next to a cell containing a pellet;
//    When  a tick event occurs;
//    Then  the ghost can move to the cell with the pellet,
//    and  the pellet on that cell is not visible anymore.
    }


    /**
     * Scenario S3.3: The ghost leaves a cell with a pellet.
     */
    @Test
    public void ghostLeavesCellWithPelletTest(){
//    Given a ghost is on a cell with a pellet (see S3.2);
//    When  a tick even occurs;
//    Then  the ghost can move away from the cell with the pellet,
//    and  the pellet on that cell is is visible again.
    }

    /**
     * Scenario S3.4: The player dies.
     */
    @Test
    public void playerDiesTest() {
//        Given the game has started,
//            and a ghost is next to a cell containing the player;
//        When a tick event occurs;
//        Then the ghost can move to the player,
//        and the game is over.
    }
}
