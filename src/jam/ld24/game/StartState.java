package jam.ld24.game;

import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import infinitedog.frisky.gui.Button;
import jam.ld24.entities.CrossHair;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.BlobbyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartState extends ManagedGameState {
    private Image background;
    private Button button_start, button_instructions, button_credits;
    
    private boolean start_game = false;
    
    public StartState(int stateID) {
        super(stateID);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        em.setGameState(C.States.START_STATE.name);
        //Add events
        evm.addEvent(C.Events.CLICK_BUTTON.name, new InputEvent(InputEvent.MOUSE_CLICK, 
                Input.MOUSE_LEFT_BUTTON, (Integer) C.Logic.SELECT_OPTION_DELAY.data));
                //Crosshair movement
        evm.addEvent(C.Events.CROSSHAIR_MOVED.name, new InputEvent(InputEvent.MOUSE_MOVE, 
                new Rectangle(0, 0, C.SCREEN_WIDTH, C.SCREEN_HEIGHT)));
        //Load textures
        tm.addTexture(C.Textures.START_BACKGROUND.name, C.Textures.START_BACKGROUND.path);
        tm.addTexture(C.Textures.BUTTON.name, C.Textures.BUTTON.path);
        tm.addTexture(C.Textures.CROSSHAIR.name, C.Textures.CROSSHAIR.path);
        //Load entities
        button_start = new Button(C.Buttons.START_GAME.textureName,
                "button_start", C.Groups.BUTTONS.name,
                C.Buttons.START_GAME.label, C.Buttons.START_GAME.labelPosition);
        button_start.setPosition(C.Buttons.START_GAME.position);
        em.addEntity(button_start.getName(), button_start);
        
        //Add Crosshair
        em.addEntity(C.Entities.CROSSHAIR.name, new CrossHair());
        //Add background
        background = tm.getTexture(C.Textures.START_BACKGROUND.name);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        em.setGameState(C.States.START_STATE.name);
        background.draw(0, 0);
        em.render(gc, g);
        CrossHair crosshair = (CrossHair) em.getEntity(C.Entities.CROSSHAIR.name);
        crosshair.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        evm.update(gc, delta);
        em.setGameState(C.States.START_STATE.name);
        em.update(gc, delta);
        
        if(evm.isHappening(C.Events.CLICK_BUTTON.name, gc)) {
            CrossHair crosshair = (CrossHair) em.getEntity(C.Entities.CROSSHAIR.name);
                                
            if(pm.testCollisionsEntity(crosshair, button_start)) {
                //((ManagedGameState)game.getState(C.States.MAIN_STATE.value)).restart();
                game.enterState(C.States.MAIN_STATE.value, new FadeOutTransition(), new FadeInTransition());
            }
        }
    }   
}