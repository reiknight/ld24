package jam.ld24.game;

import org.newdawn.slick.geom.Vector2f;

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
        MOVE_DOWN("move_down"),
        CLICK_BUTTON("click_button"),
        CROSSHAIR_MOVED("crosshair_moved");

        public String name;

        private Events(String name) {
            this.name = name;
        }
    }

    public static enum Textures {
        START_BACKGROUND("start_background", "resources/textures/start_background.png"),
        ZOMBIE("zombie", "resources/textures/zombie.png"),
        AVATAR("avatar", "resources/textures/zombie_0.png"),
        BUTTON("button", "resources/textures/button.png"),
        CROSSHAIR("crosshair", "resources/textures/crosshair.png");

        public String name;
        public String path;

        private Textures(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    public static enum Entities {
        PLAYER("player"),
        ZOMBIE("zombie"),
        CROSSHAIR("crosshair"),
        ENEMY("enemy");

        public String name;

        private Entities(String name) {
            this.name = name;
        }
    }

    public static enum Groups {
        BULLETS("bullets"),
        ZOMBIES("zombies"),
        BUTTONS("buttons"),
        ENEMIES("enemies");

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
        START_STATE("start", 0),
        MAIN_STATE("main", 1);

        public String name;
        public int value;

        private States(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }

     public static enum Logic {
        ZOMBIE_SPEED((float)0.25),
        SELECT_OPTION_DELAY(500),
        TILE_SIZE(32);

        public Object data;

        private Logic(Object data) {
            this.data = data;
        }
    }

     public static enum Buttons {
        START_GAME(Textures.BUTTON.name, "Start", new Vector2f(600, 175), new Vector2f(45, 25));

        public String textureName;
        public String label;
        public Vector2f position;
        public Vector2f labelPosition;

        Buttons(String textureName, String label, Vector2f position, Vector2f labelPosition) {
            this.textureName = textureName;
            this.label = label;
            this.position = position;
            this.labelPosition = labelPosition;
        }
    }
}