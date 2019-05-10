package st0ro.jojo.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class JOJO extends StateBasedGame{
	public JOJO() {
		//super constructor to set window name
		super("Just an Ordinary Journey Opportunity");
	}

	public static void main(String[] arguments) {
		//create a game window
		try {
			AppGameContainer app = new AppGameContainer(new JOJO()); //create game in container
			app.setDisplayMode(16*64, 9*64, false); //set window size and if fullscreen
			app.start();
		} catch (SlickException e) {
			e.printStackTrace(); //in case of failure to start game, will print error
		}
	}
	
	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		//add game states
		addState(new MapState());
	}

}
