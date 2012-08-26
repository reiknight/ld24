package jam.ld24.game;
 
import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import jam.ld24.entities.Wall;
import jam.ld24.entities.Zombie;
import jam.ld24.game.editor.EditorException;
import jam.ld24.game.editor.EditorManager;
import jam.ld24.level.Level;
import jam.ld24.tiles.CollisionMap;
import jam.ld24.tiles.TileMap;
import jam.ld24.tiles.TileSet;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainState extends ManagedGameState {
    private boolean paused = false;
    private EditorManager edm = EditorManager.getInstance();
    Level level;
    
    public MainState(int stateID)
    {
        super(stateID);
        em.setGameState(C.States.MAIN_STATE.name);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        
        evm.addEvent(C.Events.CLOSE_WINDOW.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_ESCAPE));
        
        //Player movement
        evm.addEvent(C.Events.MOVE_LEFT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_A));
        evm.addEvent(C.Events.MOVE_RIGHT.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_D));
        evm.addEvent(C.Events.MOVE_UP.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_W));
        evm.addEvent(C.Events.MOVE_DOWN.name, new InputEvent(InputEvent.KEYBOARD, Input.KEY_S));
                
        tm.addTexture(C.Textures.ZOMBIE.name, C.Textures.ZOMBIE.path);
        tm.addTexture(C.Textures.ENEMY.name, C.Textures.ENEMY.path);
        tm.addTexture(C.Textures.WALL.name, C.Textures.WALL.path);
        
        level = new Level("test");
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        level.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        level.update(gc, delta);
        
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
    }
}
