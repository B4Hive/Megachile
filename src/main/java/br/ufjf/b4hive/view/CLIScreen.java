package br.ufjf.b4hive.view;
import br.ufjf.b4hive.controller.CLI;
import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.field.FieldMap;

public class CLIScreen {
    private static int[][] visibleMap;
	private static int size;

	public static void init(int s){
		if(s % 2 == 0) s++;
		size = s;
		visibleMap = new int[size][size];
	}

	public static void updateVisibleMap(FieldMap field){
		for(int i = 1+(size/2); i > -size/2; i--){
			for(int j = -size/2; j < 1+(size/2); j++){
				Coordinate center = field.getPlayerPos();
				int tile = field.getTile(center.x() + i, center.y() + j).getTopID();
				visibleMap[i + size/2][j + size/2] = tile;
				//looks a bit convoluted doesn't it?
			}
		}
	}

	public static char[][] getVisibleMap(){
		char [][] map = new char[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                map[i][j] = (char)visibleMap[i][j]; //will be replaced with a dictionary from ID to icon
            }
        }
		return map;
	}

	public static void printMap(){
		CLI.clear();
		char[][] map = getVisibleMap();

		System.out.print(" +-");
        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.println("-+ ");
		
		for(int i = 0; i < size; i++){
			System.out.print(" | ");
			for(int j = 0; j < size; j++){
				System.out.print(" ");
				System.out.print(map[i][j]);
				System.out.print(" ");
			}
			System.out.println(" | ");
		}

		System.out.print(" +-");
        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.println("-+ ");
	}

	public static void printBar(){
		
	}
}
