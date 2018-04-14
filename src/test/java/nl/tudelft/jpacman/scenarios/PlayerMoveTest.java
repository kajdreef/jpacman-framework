package nl.tudelft.jpacman.scenarios;


import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.game.*;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.npc.ghost.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerMoveTest {

    private Launcher launcher;
	private Game game;

    /**
     * Scenario S2.1: The player consumes
     */
    @Test
    public void playerConsumesTest(){
        //Given: the game has started,
        (launcher = new Launcher()).launch();
        (game = launcher.getGame()).start();

        // And: my Pacman is next to a square containing a pellet;
		Player pacman = game.getPlayers().get(0);

        // When: I press an arrow key towards that square;
        Direction d = Direction.EAST;
        pacman.setDirection(d);

        // Then:
        // assertThat my Pacman can move to that square
        Square location = pacman.getSquare();
        Square destination = location.getSquareAt(d);

        assertTrue(destination.isAccessibleTo(pacman));

        // assertThat: I earn the points for the pellet
        int initialScore = pacman.getScore();
        game.move(pacman, d);
        int afterMoveScore = pacman.getScore();

        assertTrue(afterMoveScore == initialScore + 10);

        // assertThat: the pellet disappears from that square
        assertTrue(destination.getOccupants().size() == 1);

        launcher.dispose();
    }

    /**
     * Scenario S2.2: The player moves on empty square
     */
    @Test
    public void playerMovesOnEmptySquareTest(){
        //Given: the game has started,
        (launcher = new Launcher()).launch();
        (game = launcher.getGame()).start();

        // and  my Pacman is next to an empty square;
        Player pacman = game.getPlayers().get(0);
        game.move(pacman, Direction.EAST);


        // When  I press an arrow key towards that square;
        game.move(pacman, Direction.WEST);

        // Then
        // assertThat: my Pacman can move to that square
        Square location = pacman.getSquare();
        Square destination = location.getSquareAt(Direction.WEST);

        assertTrue(destination.isAccessibleTo(pacman));

        // assertThat: and my points remain the same.
        int scoreBefore = pacman.getScore();
        game.move(pacman, Direction.EAST);
        int scoreAfter = pacman.getScore();

        assertEquals(scoreBefore, scoreAfter);

        launcher.dispose();

    }

    /**
     * Scenario S2.3: The move fails
     */
    @Test
    public void playerMoveFailsTest(){
        //Given: the game has started,
        (launcher = new Launcher()).launch();
        (game = launcher.getGame()).start();


        // and my Pacman is next to a cell containing a wall;
        Player pacman = game.getPlayers().get(0);
        Square location1 = pacman.getSquare();

        // When:  I press an arrow key towards that cell;
        game.move(pacman, Direction.SOUTH);
        Square location2 = pacman.getSquare();


        // Then:  the move is not conducted.
        assertTrue(location1.equals(location2));

        launcher.dispose();
    }


    /**
     * Scenario S2.4: The player dies
     */
    @Test
    public void playerDiesTest(){
        //Given: the game has started,
        (launcher = new Launcher()).launch();
        (game = launcher.getGame()).start();

        // and  my Pacman is next to a cell containing a ghost;
        Player pacman = game.getPlayers().get(0);

        // Get the square east of the player in default map
        Board board = game.getLevel().getBoard();
        Square futureLocGhost = board.squareAt(12, 15);

        // place ghost east of the player
        Ghost ghost = (Ghost) Navigation.findNearest(Ghost.class, futureLocGhost);
        ghost.occupy(futureLocGhost);


        // When  I press an arrow key towards that square;
        game.move(pacman, Direction.EAST);

        // Then
        // assertThat: my Pacman dies,
        assertFalse(pacman.isAlive());
        // assertThat: and  the game is over.
        assertFalse(game.isInProgress());

        launcher.dispose();
    }

    /**
     * Scenario S2.5: Player wins, extends S2.1
     */
    @Test
    public void playerWinsTest(){
        // Given the game has started,
        // and  my Pacman is next to a square containing a pellet;

        // create custom game with only 2 objects player and pallet
        char[][] map = {{'P', '.'}};

        PacManSprites pacmanSprites = new PacManSprites();
        GameFactory gameFactory = new GameFactory(new PlayerFactory(pacmanSprites));

        MapParser parser = new MapParser(new LevelFactory(pacmanSprites, new GhostFactory(pacmanSprites)),
            new BoardFactory(pacmanSprites));


        // create the new game
        game = gameFactory.createSinglePlayerGame(parser.parseMap(map));
        game.start();

        // get player
        Player pacman = game.getPlayers().get(0);

        // When  I press an arrow key towards that square;
        // and I have eaten the last pellet;
        game.move(pacman, Direction.SOUTH);

        // assertThat: I win the game.
        assertEquals(0, game.getLevel().remainingPellets());

        assertTrue(pacman.isAlive());
        assertFalse(game.isInProgress());
    }
}
