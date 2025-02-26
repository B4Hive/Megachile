package br.ufjf.b4hive;
import br.ufjf.b4hive.view.CLIScreen;

public class Megachile {
    public static void main(String[] args) {
        if (args.length == 1)
            CLIScreen.show(Integer.parseInt(args[0]));
        else
            CLIScreen.show(0);
    }
}