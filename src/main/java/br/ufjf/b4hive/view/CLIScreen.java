package br.ufjf.b4hive.view;
import br.ufjf.b4hive.controller.CLI;
import br.ufjf.b4hive.controller.Engine;

public class CLIScreen {
    private static int[][] visibleMap;
	private static int size;

	public static void game(int s){
		char option;
		init(s);
		
		do { //menu principal
			printMainMenu();
			option = CLI.getChar();
			switch(option){
				case 's' -> Engine.newGame();
				case 'q' -> System.exit(0);
				default -> {}
			}
		} while (option != 's' && option != 'q');

		do { //jogo
			updateVisibleMap();
			printMap();
			option = CLI.getChar();
			//adicionar movimentação
			switch (option) {
				case 'q' -> Engine.endGame();
				case 'w', 'a', 's', 'd' -> Engine.movePlayer(option);
				default -> {}
			}
		} while (option != 'q');


	}

	public static void init(int s){
		if(s < 7) s = 7;
		else if(s % 2 == 0) s++;
		size = s;
		visibleMap = new int[size][size];
		Dictionary.init();
		//vai chamar o menu principal daqui
	}

	public static void updateVisibleMap(){
		visibleMap = Engine.getVisibleMap(size);
	}

	public static void printMap(){
		CLI.clear();

		System.out.print(" +-");
        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.println("-+ ");
		
		for(int j = 0; j < size; j++){
			System.out.print(" | ");
			for(int i = 0; i < size; i++){
				System.out.print(" ");
				System.out.print(Dictionary.getIcon(visibleMap[i][j]));
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
		//
	}

	public static void printMainMenu(){
		CLI.clear();

		System.out.print(" +-");
        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.println("-+ ");

		for(int i = 0; i < size; i++){
			System.out.print(" | ");
			if(i == (size/2) - 1){
				for(int j = 0; j < ((size-3) * 3)/2; j++){
					System.out.print(" ");
				}
				System.out.print("S - Start");
				for(int j = 0; j < ((size-3) * 3)/2; j++){
					System.out.print(" ");
				}
			} else if (i == (size/2) + 1){
				for(int j = 0; j < ((size-3) * 3)/2; j++){
					System.out.print(" ");
				}
				System.out.print("Q - Quit ");
				for(int j = 0; j < ((size-3) * 3)/2; j++){
					System.out.print(" ");
				}
			} else {
				for(int j = 0; j < size*3; j++){
					System.out.print(" ");
				}
			}
			System.out.println(" | ");
		}

		System.out.print(" +-");
        for (int i = 0; i < size; i++) {
            System.out.print("---");
        }
        System.out.println("-+ ");
	}

}
