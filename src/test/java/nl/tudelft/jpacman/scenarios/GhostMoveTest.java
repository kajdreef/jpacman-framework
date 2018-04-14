package nl.tudelft.jpacman.scenarios;


import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Pellet;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.npc.ghost.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostColor;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * As a ghost;
 * I get automatically moved around;
 * So that I can try to kill the player.
 */
public class GhostMoveTest {

    private Launcher launcher;
    private Game game;

    /**
     * Scenario S3.1: A ghost moves.
     */
    @Test
    public void ghostMovesTest() throws InterruptedException{
        // Given: the game has started,
        (launcher = new Launcher()).launch();
        game = launcher.getGame();


        // and  a ghost is next to an empty cell;
        Board board = game.getLevel().getBoard();
        Square futureLocGhost = board.squareAt(11,7);

        // place ghost east of the player
        Ghost ghost = (Ghost) Navigation.findNearest(Ghost.class, futureLocGhost);
        Square currentLoc = ghost.getSquare();
        Direction d = ghost.nextMove();

        game.start();

        // When  a tick event occurs;
        Thread.sleep(ghost.getInterval());

        // Then  the ghost can move to that cell.
        assertEquals(currentLoc.getSquareAt(d), ghost.getSquare());
    }


    /**
     * Scenario S3.2: The ghost moves over a square with a pellet.
     */
    @Test
    public void ghostMovesOverSquareWithPelletTest() throws InterruptedException {
        // Given: the game has started,
        (launcher = new Launcher()).launch();
        game = launcher.getGame();


        // and  a ghost is next to a cell containing a pellet;
        Board board = game.getLevel().getBoard();
        Square ghostLoc = board.squareAt(12,15);
        Ghost ghost = (Ghost) Navigation.findNearest(Ghost.class, ghostLoc);

        // place ghost east of the player
        ghost.occupy(ghostLoc);

        // set direction of ghost to the East;
        Direction d = Direction.EAST;
        ghost.setDirection(d);
        Square nextLoc = ghostLoc.getSquareAt(d);

        // Get the sprite
        PacManSprites pacManSprites = new PacManSprites();

        game.start();

        // the ghost can move to the cell with the pellet,
        assertTrue(nextLoc.isAccessibleTo(ghost));

        // When  a tick event occurs;
        Thread.sleep(ghost.getInterval());

        // and  the pellet on that cell is not visible anymore.
        assertFalse(ghost.getSquare().getSprite() == pacManSprites.getPelletSprite());
        assertFalse(ghost.getSquare().getSprite() == pacManSprites.getGhostSprite(GhostColor.PINK));
    }


    /**
     * Scenario S3.3: The ghost leaves a cell with a pellet.
     */
    @Test
    public void ghostLeavesCellWithPelletTest() throws InterruptedException{
//    Given a ghost is on a cell with a pellet (see S3.2);

        // Given: the game has started,
        (launcher = new Launcher()).launch();
        game = launcher.getGame();


        // and  a ghost is next to a cell containing a pellet;
        Board board = game.getLevel().getBoard();
        Square ghostLoc = board.squareAt(12,15);
        Ghost ghost = (Ghost) Navigation.findNearest(Ghost.class, ghostLoc);

        // place ghost east of the player
        ghost.occupy(ghostLoc);

        // set direction of ghost to the East;
        Direction d = Direction.EAST;
        ghost.setDirection(d);

        // Get the sprite
        PacManSprites pacManSprites = new PacManSprites();

        game.start();

        // the ghost can move to the cell with the pellet,

        assertTrue(ghostLoc.getSprite() != pacManSprites.getPelletSprite());

        // When  a tick event occurs;
        Thread.sleep(ghost.getInterval());

        // Then  the ghost can move away from the cell with the pellet,
        assertEquals(ghostLoc.getOccupants().size(), 1);
        assertEquals(ghostLoc.getOccupants().get(0).getClass(), Pellet.class);
    }

    /**
     * Scenario S3.4: The player dies.
     */
    @Test
    public void playerDiesTest() throws InterruptedException {
//        Given the game has started,
//            and a ghost is next to a cell containing the player;
        //    Given a ghost is on a cell with a pellet (see S3.2);

        // Given: the game has started,
        (launcher = new Launcher()).launch();
        game = launcher.getGame();
        Player pacman = game.getPlayers().get(0);


        // and  a ghost is next to a cell containing a pellet;
        Board board = game.getLevel().getBoard();
        Square ghostLoc = board.squareAt(9,15);
        Ghost ghost = (Ghost) Navigation.findNearest(Ghost.class, ghostLoc);

        // place ghost east of the player
        ghost.occupy(ghostLoc);

        // Get the sprite
        PacManSprites pacManSprites = new PacManSprites();

        game.start();

//        When a tick event occurs;
        Thread.sleep(2*ghost.getInterval());

//        Then the ghost can move to the player,
        assertTrue( ghost.getSquare() == pacman.getSquare());
//        and the game is over.
        assertFalse(game.isInProgress());
        assertFalse(pacman.isAlive());
    }
}
