package br.ufjf.b4hive;
import br.ufjf.b4hive.view.CLIScreen;

public class Megachile {
    public static void main(String[] args) {
        if (args.length == 1){
            CLIScreen.game(Integer.parseInt(args[0]));
        }
        else
            CLIScreen.game(0);
    }
}