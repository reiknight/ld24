package jam.ld24.entities;

import infinitedog.frisky.textures.TextureManager;
import jam.ld24.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

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
    }
}
