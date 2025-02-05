package br.ufjf.b4hive.view;
import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.controller.CLI;
import br.ufjf.b4hive.controller.Engine;

public class CLIScreen {
    private static int[][] visibleMap;
	private static int size;

	public static void game(int s){
		char option;
		init(s);
		List<String> log = new ArrayList<>();
		
		do { //menu principal
			printMainMenu();
			System.out.println("Input your command: ");
			option = CLI.getChar();
			switch(option){
				case 's' -> Engine.newGame();
				case 'q' -> {
					CLI.clear();
					System.out.println("Game Closed.");
					System.exit(0);
				}
				default -> {}
			}
		} while (option != 's' && option != 'q');

		do { //jogo
			log.add("Input your command: ");
			updateVisibleMap();
			printMap();
			//printBar();
			printLog(log);
			option = CLI.getChar();
			//adicionar movimentação
			switch (option) {
				case 'q' -> {
					Engine.endGame();
					game(s);
				}
				case 'w', 'a', 's', 'd' -> {
					String temp = Engine.movePlayer(option);
					if(temp != null) log.add(temp);
				}
				default -> {}
			}
		} while (option != 'q');


	}

	private static void init(int s){
		if(s < 7) s = 7;
		else if(s % 2 == 0) s++;
		size = s;
		visibleMap = new int[size][size];
		Dictionary.init();
	}

	private static void updateVisibleMap(){
		visibleMap = Engine.getVisibleMap(size);
	}

	private static void printMap(){
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

	//private static void printBar(){}

	private static void printLog(List<String> log){
		for(String s : log){
			System.out.println(s);
		}
		log.clear();
	}

	private static void printMainMenu(){
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
