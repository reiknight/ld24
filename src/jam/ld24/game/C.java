package jam.ld24.game;

public class C {
    public static final boolean DEBUG_MODE = true;
    public static final boolean GOD_MODE = false;
    public static final int SCREEN_HEIGHT = 600;
    public static final int SCREEN_WIDTH = 800;
    
    public static enum Events {
        CLOSE_WINDOW("close_window"),
        MOVE_LEFT("move_left"),
        MOVE_RIGHT("move_right"),
        MOVE_UP("move_up"),
        MOVE_DOWN("move_down");
        
        public String name;

        private Events(String name) {
            this.name = name;
        }
    }
    
    public static enum Textures {
        START_BACKGROUND("start_background", "resources/inicio.jpg"),
        ZOMBIE("zombie", "resources/textures/zombie.png"),
        AVATAR("avatar", "resources/textures/zombie_0.png");

        public String name;
        public String path;

        private Textures(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
    
    public static enum Entities {
        PLAYER("player"),
        ZOMBIE("zombie");

        public String name;

        private Entities(String name) {
            this.name = name;
        }
    }
    
    public static enum Groups {
        BULLETS("bullets"),
        ZOMBIES("zombies");
        
        public String name;

        private Groups(String name) {
            this.name = name;
        }
    }
    
    public static enum Sounds {
        MUSIC("music", "resources/music.ogg");
        
        public String name;
        public String path;

        private Sounds(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
    
    public static enum States {
        MAIN_STATE("Main", 0);
        
        public String name;
        public int value;
        
        private States(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}