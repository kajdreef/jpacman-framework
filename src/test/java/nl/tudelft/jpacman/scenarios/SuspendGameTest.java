package nl.tudelft.jpacman.scenarios;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SuspendGameTest {

    /**
     * Scenario S4.1: Suspend the game.
     */
    @Test
    public void suspendGameTest(){
        // Given the game has started;
        Launcher launcher = new Launcher();
        launcher.launch();

        Game game = launcher.getGame();
        game.start();

        // When  the player clicks the "Stop" button;
        game.stop();

        // Then  all moves from ghosts and the player are suspended.
        assertThat(game.isInProgress() == false);
    }


    /**
     * Scenario S4.2: Restart the game.
     */
    @Test
    public void restartGame(){
        //    Given the game is suspended;
        Launcher launcher = new Launcher();
        launcher.launch();

        Game game = launcher.getGame();
        game.start();
        game.stop();

        //    When  the player hits the "Start" button;
        game.start();

        //    Then  the game is resumed.
        assertThat(game.isInProgress() == true);

    }


}
