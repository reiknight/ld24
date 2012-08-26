package jam.ld24.entities;

import infinitedog.frisky.entities.Sprite;
import jam.ld24.game.C;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Zombie extends Sprite {
    private static int id = 0;
        
    public Zombie() {
        super(C.Textures.ZOMBIE.name);
        name =  C.Entities.ZOMBIE.name + id++;
        group = C.Groups.ZOMBIES.name;
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
