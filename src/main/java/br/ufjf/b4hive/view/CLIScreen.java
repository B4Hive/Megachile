package br.ufjf.b4hive.view;
import br.ufjf.b4hive.model.field.*;

public class CLIScreen {
    private int[][] visibleMap;
	private final int size;

	public CLIScreen(int size){
		this.size = size;
		this.visibleMap = new int[size][size];
	}

	public void updateVisibleMap(FieldMap field){
		for(int i = -this.size; i < this.size; i++){
			for(int j = -this.size; j < this.size; j++){
				Coordinate coord = field.getPlayerPos();
				int tile = field.getTile(coord.x() + i, coord.y() + j).getTopID();
				visibleMap[i + this.size][j + this.size] = tile;
				//looks a bit convoluted doesn't it?
			}
		}
	}
	public int[][] getVisibleMap(){
		// TODO: update before getting
		return this.visibleMap;
	}
}
