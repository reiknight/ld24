package jam.ld24.game.editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
    public static EditorManager getInstance() {
        if(em == null) {
            em = new EditorManager();
        }
        return em;
    }
    
    /**
     * Method for write a map.
     * @param name 
     */
    public void writeMap(String name, int[][] map) throws EditorException {
        File dir = new File("resources/maps");
        dir.mkdir();
        System.out.println(dir.getAbsolutePath());
        File f = new File(dir, name+".map");
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
                    pw.print(map[i][j] + " ");
                }
                pw.println();
            }
            pw.flush();
        } catch(IOException ioe) {
            throw new MapEditorException("Error in saving the map");
        } finally {
            try {
                fw.close();
                pw.close();
            } catch(IOException ioe) {
                throw new EditorException("Error closing archives");
            }
        }
    }
    
    public int[][] readMap(String name) throws EditorException {
        File dir = new File("resources/maps");
        File f = new File(dir,name+".map");
 
        Scanner sc = null;
        try {
            sc = new Scanner(f);
            ArrayList<ArrayList<Integer>> list = new ArrayList();
            while(sc.hasNextInt()) {
                ArrayList<Integer> row = new ArrayList<Integer>();
                row.add(sc.nextInt());
                String s = sc.findInLine("[0-9]+");
                while(s != null) {
                    row.add(Integer.parseInt(s));
                    s = sc.findInLine("[0-9]+");
                }
                list.add(row);
            }
            
            int[][] map = new int[list.size()][];
            for(int i = 0; i < map.length; i++) {
                ArrayList ar = list.get(i);
                map[i] = new int[ar.size()];
                for(int j = 0; j < map[i].length; j++) {
                    map[i][j] = (Integer) ar.get(j);
                }
            }
            return map;
        } catch(IOException ioe) {
            throw new MapEditorException("Error in getting the map");
        }
    }
}