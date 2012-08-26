package jam.ld24.game;
 
import infinitedog.frisky.events.InputEvent;
import infinitedog.frisky.game.ManagedGameState;
import jam.ld24.entities.Wall;
import jam.ld24.entities.Zombie;
import jam.ld24.game.editor.EditorException;
import jam.ld24.game.editor.EditorManager;
import jam.ld24.tiles.CollisionMap;
import jam.ld24.tiles.TileMap;
import jam.ld24.tiles.TileSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class MainState extends ManagedGameState {
    private boolean paused = false;
    private EditorManager edm = EditorManager.getInstance();
    Zombie ivan;
    Zombie david;
    Wall wall;
    TileMap map;
    CollisionMap collisionMap;
    
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
                
        tm.getInstance().addTexture(C.Textures.ZOMBIE.name, C.Textures.ZOMBIE.path);
        tm.getInstance().addTexture(C.Textures.AVATAR.name, C.Textures.AVATAR.path);
        tm.getInstance().addTexture(C.Textures.WALL.name, C.Textures.WALL.path);
        
        // Init zombies
        ivan = new Zombie();
        david = new Zombie();
        
        ivan.setPosition(new Vector2f(50, 50));
        david.setPosition(new Vector2f(100, 100));
        
        em.addEntity(ivan.getName(), ivan);
        em.addEntity(david.getName(), david);
        
        TileSet test = new TileSet("test", "resources/textures/tileset_test.png", 32);
        
        int[][] mapArray = {};
        
        try {
            mapArray = edm.readMap("test");
        } catch (EditorException ex) {
            Logger.getLogger(MainState.class.getName()).log(Level.SEVERE, null, ex);
        }
        map = new TileMap(mapArray, test);
        
        try {
            mapArray = edm.readMap("test");
        } catch (EditorException ex) {
            Logger.getLogger(MainState.class.getName()).log(Level.SEVERE, null, ex);
        }
        collisionMap = new CollisionMap(mapArray);
        
        // Init walls
        for(int i = 0; i < mapArray.length; i++) {
            for(int j = 0; j < mapArray[i].length; j++) {
                if(mapArray[i][j] == 1) {
                    wall = new Wall();
                    wall.setPosition(new Vector2f(j * (Integer) C.Logic.TILE_SIZE.data, 
                            i * (Integer) C.Logic.TILE_SIZE.data));
                    em.addEntity(wall.getName(), wall);
                }
            }
        }
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
        map.render();
        em.render(gc, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
        em.setGameState(C.States.MAIN_STATE.name);
        em.update(gc, delta);
        
        if(evm.isHappening(C.Events.CLOSE_WINDOW.name, gc)) {
            gc.exit();
        }
    }
}
