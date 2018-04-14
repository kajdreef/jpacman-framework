package nl.tudelft.jpacman.scenarios;

import nl.tudelft.jpacman.Launcher;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StartupTest {

    private Launcher launcher;
	
	@Test
    public void doesTheGameStartupTest() {
        launcher = new Launcher();
        launcher.launch();

    	//When The user presses the start button
        launcher.getGame().start();

		//Then the Game should start
        assertThat(launcher.getGame().isInProgress());
    }
}
