package jam.ld24.entities;

import infinitedog.frisky.events.EventManager;
import infinitedog.frisky.textures.TextureManager;
import jam.ld24.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

public class Avatar extends Zombie {
    
    public Avatar() {
        super();
        this.setTexture(C.Textures.AVATAR.name);
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) {
        super.render(gc, g);
    }
    
    @Override
    public void update(GameContainer gc, int delta) {
        super.update(gc, delta);
        
        EventManager evm = EventManager.getInstance();
        float x = getX();
        float y = getY();
    
        //Player movement
        if(evm.isHappening(C.Events.MOVE_LEFT.name, gc)) {
            x -= speed * delta;
        }
        
        if(evm.isHappening(C.Events.MOVE_RIGHT.name, gc)) {
            x += speed * delta;
        }
        
        if(evm.isHappening(C.Events.MOVE_UP.name, gc)) {
            y -= speed * delta;
        }
        
        if(evm.isHappening(C.Events.MOVE_DOWN.name, gc)) {
            y += speed * delta;
        }
        
        setPosition(new Vector2f(x, y));
    }
}
