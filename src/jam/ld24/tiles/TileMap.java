package jam.ld24.tiles;

/**
 *
 * @author InfiniteDog
 */
public class TileMap extends Map {
    private TileSet tileSet;

    public TileMap(int[][] map, TileSet tileSet) {
        super(map);
        this.tileSet = tileSet;
    }   
    
    public void render() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                tileSet.render(map[i][j], j, i);
            }
        }
    }
}
