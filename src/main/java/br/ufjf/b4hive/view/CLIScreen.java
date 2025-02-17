package br.ufjf.b4hive.view;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.controller.CLI;
import br.ufjf.b4hive.controller.Engine;

public class CLIScreen {
    private static int[][] visibleMap;
	private static int size;

	public static void show(int s){
		init(s);
		mainMenuScreen();
	}

	private static void init(int s){
		if(s < 7) s = 7;
		else if(s % 2 == 0) s++;
		size = s;
		visibleMap = new int[size][size];
		Dictionary.init();
	}

	private static void mainMenuScreen() {
		char option;
		do {
			printMainMenu();
			System.out.println();
			System.out.print("Input your command: ");
			option = CLI.getChar();
			switch(option){
				case 's' -> {
					Engine.newGame();
					gameScreen();
				}
				case 'q' -> {
					CLI.clear();
					System.out.println("Game Closed.");
					System.exit(0);
				}
				default -> {
					
				}
			}
		} while (option != 'q');
	}

	private static void printMainMenu(){
		CLI.clear();

		System.out.print(" ╔═");
        for (int i = 0; i < size; i++) {
            System.out.print("═══");
        }
        System.out.println("═╗ ");

		for(int i = 0; i < size; i++){
			System.out.print(" ║ ");
			if (i == 1){
				for(int j = 0; j < ((size-3) * 3)/2; j++){
					System.out.print(" ");
				}
				System.out.print("MEGACHILE");
				for(int j = 0; j < ((size-3) * 3)/2; j++){
					System.out.print(" ");
				}
			} else if(i == (size/2) - 1){
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
			System.out.println(" ║ ");
		}

		System.out.print(" ╚═");
        for (int i = 0; i < size; i++) {
            System.out.print("═══");
        }
        System.out.println("═╝ ");
	}

	private static void gameScreen() {
		List<String> log = new ArrayList<>();
		char option;
		do {
			updateVisibleMap();
			printMap();
			printBar();
			System.out.println("WASD - Move | C - Inventory | E - Take Item | X - Look | Q - Quit");
			log.add("Input your command: ");
			printLog(log);
			option = CLI.getChar();
			switch (option) {
				case 'q' -> {
					Engine.endGame();
					mainMenuScreen();
				}
				case 'w', 'a', 's', 'd' -> {
					String temp = Engine.movePlayer(option);
					log.add(temp);
				}
				case 'c' -> {
					String temp = inventoryScreen();
					log.add(temp);
				}
				case 'e' -> {
					String temp = Engine.takeItem();
					log.add(temp);
				}
				case 'x' -> {
					lookScreen();
				}
				default -> {
					
				}
			}
			// IA
		} while (option != 'q');
	}

	private static void updateVisibleMap(){
		visibleMap = Engine.getVisibleMap(size);
	}

	private static void printMap(){
		CLI.clear();

		System.out.print(" ╔═");
        for (int i = 0; i < size; i++) {
            System.out.print("═══");
        }
        System.out.println("═╗ ");
		
		for(int j = 0; j < size; j++){
			System.out.print(" ║ ");
			for(int i = 0; i < size; i++){
				System.out.print(" ");
				System.out.print(Dictionary.getIcon(visibleMap[i][j]));
				System.out.print(" ");
			}
			System.out.println(" ║ ");
		}

		System.out.print(" ╠═");
        for (int i = 0; i < size; i++) {
            System.out.print("═══");
        }
        System.out.println("═╣ ");
	}

	private static void printBar(){
		System.out.print(" ║ ");
		for(int j = 0; j < (size * 3); j++){
			System.out.print(" "); // 4 lines: HP, Hunger, Position, QuickSlots
		}
		System.out.println(" ║ ");

		System.out.print(" ╚═");
        for (int i = 0; i < size; i++) {
            System.out.print("═══");
        }
        System.out.println("═╝ ");
	}

	private static void printLog(List<String> log){
		for(String s : log){
			if(s == null) continue;
			System.out.println();
			System.out.print(s);
		}
		log.clear();
	}

	private static String inventoryScreen(){
		char option;
		do {
			printInventory();
			System.out.print("Input your command: ");
			option = CLI.getChar();
			if(option == 'q') gameScreen();

			int n;
			try {
				n = Integer.parseInt(String.valueOf(option));
			} catch (NumberFormatException e) {
				n = -1;
			}
			if(n >= 0 && n < Engine.listPlayerInventory().size()){
				String temp = itemScreen(n);
				if(temp != null) return temp;
			}
		} while (option != 'q');
		return null;
	}

	private static void printInventory(){
		CLI.clear();
		System.out.println("Inventory: ");
		List<String> inventory = Engine.listPlayerInventory();
		for(int i = 0; i < inventory.size(); i++){
			System.out.print(i + " - ");
			System.out.println(inventory.get(i));
		}
	}

	private static String itemScreen(int n){
		char option;
		do {
			printItem(n);
			System.out.println("Q - Back | E - Use | Z - Drop");
			System.out.print("Input your command: ");
			option = CLI.getChar();
			if(option == 'q') inventoryScreen();
			if(option == 'e'){
				String temp = Engine.useItem(n);
				return temp;
			}
			if(option == 'z'){
				String temp = Engine.dropItem(n);
				return temp;
			}
		} while (option != 'q');
		return null;
	}

	private static void printItem(int n){
		CLI.clear();
		System.out.println("Item: ");
		System.out.println(Engine.playerItemInfo(n));
	}

	private static void lookScreen(){
		List<String> log = new ArrayList<>();
		int x = size/2;
		int y = size/2;
		char option;
		do {
			updateVisibleMap();
			printLookScreen(x, y);
			printBar();
			System.out.println("WASD - Move | E - Info | Z - Skill | Q - Back");
			log.add("Input your command: ");
			printLog(log);
			option = CLI.getChar();
			switch (option) {
				case 'q' -> {
					gameScreen();
				}
				case 'w', 'a', 's', 'd' -> {
					switch (option) {
						case 'w' -> {
							if(y > 0) y--;
						}
						case 'a' -> {
							if(x > 0) x--;
						}
						case 's' -> {
							if(y < size - 1) y++;
						}
						case 'd' -> {
							if(x < size - 1) x++;
						}
					}
				}
				case 'e' -> {
					int xField = x - (size/2);
					int yField = y - (size/2);
					String temp = Engine.lookInto(xField, yField); // look at tile
					log.add(temp);
				}
				case 'z' -> {
					// if weapon in hand, use skill
				}
				default -> {
					
				}
			}
			// IA
		} while (option != 'q');
	}

	private static void printLookScreen(int x, int y){
		CLI.clear();

		System.out.print(" ╔═");
        for (int i = 0; i < size; i++) {
            System.out.print("═══");
        }
        System.out.println("═╗ ");
		
		for(int j = 0; j < size; j++){
			System.out.print(" ║ ");
			for(int i = 0; i < size; i++){
				if (x != i || y != j) System.out.print(" ");
				else System.out.print("[");
				System.out.print(Dictionary.getIcon(visibleMap[i][j]));
				if (x != i || y != j) System.out.print(" ");
				else System.out.print("]");
			}
			System.out.println(" ║ ");
		}

		System.out.print(" ╠═");
        for (int i = 0; i < size; i++) {
            System.out.print("═══");
        }
        System.out.println("═╣ ");
	}

}
