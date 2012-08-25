package jam.ld24.game.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Level Editor Manager.
 * @author InfiniteDog
 */
public class EditorManager {
    
    private static EditorManager em;
    
    /**
     * Private constructor, if one instance is already instantiated.
     * it doesn't do nothing
     */
    private EditorManager() {
        
    }
    
    /**
     * Method for get the Instance of the Manager.
     * @return The unique instance of EditorManager
     */
    public EditorManager getInstance() {
        if(em == null) {
            em = new EditorManager();
        }
        return em;
    }
    
    /**
     * Method for write a map.
     * @param name 
     */
    public void writeMap(String name, int[][] map) throws MapEditorException {
        File dir = new File("maps");
        dir.mkdir();
        System.out.println(dir.getAbsolutePath());
        File f = new File(dir, name);
        FileWriter fw = null;
        PrintWriter pw = null;
        if(f.exists()) {
            throw new MapEditorException("The name of the map is already taken");
        }
        try {
            f.createNewFile();
            fw = new FileWriter(f, true);
            pw = new PrintWriter(fw);
            
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    pw.print(map[i][j]);
                }
                pw.println();
            }
            pw.flush();
        } catch(IOException ioe) {
            ioe.printStackTrace();
            throw new MapEditorException("Error in saving the map");
        } finally {
            try {
                fw.close();
                pw.close();
            } catch(IOException ioe) {
                throw new MapEditorException("Error closing archives");
            }
        }
    }
    
    
    public static void main(String[] args) throws Exception {
        System.out.println("PRUEBAS DEL EDITOR:");
        System.out.println("---------------------");
        int[][] map = {{1,2,3},{4,5,6},{7,8,9}};
        
        EditorManager editor = new EditorManager();
        editor.writeMap("Catacrocker.map", map);
    }
}
