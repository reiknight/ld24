package jam.ld24.game;
 
import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import jam.ld24.entities.Avatar;
import jam.ld24.entities.Zombie;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class MainState extends ManagedGameState {
    private boolean paused = false;
    
    public MainState(int stateID)
    {
        super(stateID);
        em.setGameState(C.States.MAIN_STATE.name);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        
        tm.getInstance().addTexture(C.Textures.ZOMBIE.name, C.Textures.ZOMBIE.path);
        tm.getInstance().addTexture(C.Textures.AVATAR.name, C.Textures.AVATAR.path);
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        Avatar ivan = new Avatar();
        Zombie david = new Zombie();
        
        ivan.setPosition(new Vector2f(50, 50));
        david.setPosition(new Vector2f(100, 100));
        
        ivan.render(gc, g);
        david.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
    }
}
