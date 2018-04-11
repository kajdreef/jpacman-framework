package nl.tudelft.jpacman.scenarios;


import nl.tudelft.jpacman.Launcher;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerMoveTest {

    private Launcher launcher;

    @Before
    public void initialize(){
        //Given: the game has started,
        launcher = new Launcher();
        launcher.launch();
    }

    /**
     * Scenario S2.1: The player consumes
     */
    @Test
    public void playerConsumesTest(){
        // And: my Pacman is next to a square containing a pellet;

        // When: I press an arrow key towards that square;

        // Then:
        // assertThat my Pacman can move to that square

        // assertThat: I earn the points for the pellet

        // assertThat: the pellet disappears from that square
    }

    /**
     * Scenario S2.2: The player moves on empty square
     */
    @Test
    public void playerMovesOnEmptySquareTest(){
        // and  my Pacman is next to an empty square;


        // When  I press an arrow key towards that square;


        // Then
        // assertThat: my Pacman can move to that square

        // assertThat: and  my points remain the same.

    }

    /**
     * Scenario S2.3: The move fails
     */
    @Test
    public void playerMoveFailsTest(){
        // and my Pacman is next to a cell containing a wall;


        // When:  I press an arrow key towards that cell;


        // Then:  the move is not conducted.


    }


    /**
     * Scenario S2.4: The player dies
     */
    @Test
    public void playerDiesTest(){
        // and  my Pacman is next to a cell containing a ghost;


        // When  I press an arrow key towards that square;


        // Then
        // assertThat: my Pacman dies,

        // assertThat: and  the game is over.
    }

    /**
     * Scenario S2.5: Player wins, extends S2.1
     */
    @Test
    public void playerWinsTest(){
        // When: I have eaten the last pellet;


        // Then:
        // assertThat: I win the game.
    }

}
