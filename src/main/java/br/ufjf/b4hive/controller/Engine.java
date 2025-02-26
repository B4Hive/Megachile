package br.ufjf.b4hive.controller;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.b4hive.model.DataBank;
import br.ufjf.b4hive.model.entity.Bee;
import br.ufjf.b4hive.model.entity.Entity;
import br.ufjf.b4hive.model.field.Coordinate;
import br.ufjf.b4hive.model.field.FieldMap;
import br.ufjf.b4hive.model.field.Tile;
import br.ufjf.b4hive.model.inventory.Effect;
import br.ufjf.b4hive.model.inventory.Item;
import br.ufjf.b4hive.model.inventory.Weapon;

public class Engine {

    private static boolean running = false;
    private static FieldMap field;
    private static Bee player;
    private static List<Entity> entities;

    public static void newGame() {
        running = true;
        DataBank.initData();
        field = new FieldMap();
        player = new Bee(99, "@Player");
        player.getInventory().addItem(DataBank.getRandItem());
        player.setPosition(new Coordinate(0, 0));
        field.getTile(player.getPosition()).setEntity(player);
        entities = new ArrayList<>();
        entities.add(player);
    }

    public static void endGame() {
        running = false;
        // save the game here
    }

    public static int[][] getVisibleMap(int size) {
        if (!running)
            System.exit(0);

        int[][] visibleMap = new int[size][size];

        Coordinate center = player.getPosition();
        int x = center.x() - (size / 2);
        int i = 0;
        int y = center.y() - (size / 2);
        int j = size - 1;
        while (y <= center.y() + (size / 2)) {
            while (x < center.x() + (size / 2) + 1) {
                if (field.getTile(x, y).getEntity() != null) {
                    Entity e = field.getTile(x, y).getEntity();
                    if (!e.alive()) {
                        Item it = e.drop((int) (Math.random() * 100));
                        field.getTile(x, y).setItem(it);
                        field.getTile(x, y).setEntity(null);
                        entities.remove(e);
                    }
                }
                visibleMap[i][j] = field.getTile(x, y).view();
                x++;
                i++;
            }
            x = center.x() - (size / 2);
            i = 0;
            y++;
            j--;
        }
        return visibleMap;
    }

    public static String lookInto(int x, int y) {
        if (!running)
            System.exit(0);

        x = player.getPosition().x() + x;
        y = player.getPosition().y() - y;
        Tile tile = field.getTile(x, y);
        if (tile.getEntity() != null) {
            return tile.getEntity().getInfo();
        }
        if (tile.getItem() != null) {
            return tile.getItem().getInfo();
        }
        // incluir tipo de tile depois se possível
        return null;
    }

    public static Tile generateTile() {
        if (!running)
            System.exit(0);

        int t = (int) (Math.random() * 3);
        int e = (int) (Math.random() * 10);
        Entity en = null;
        if (e < 1) {
            en = DataBank.getRandEnemy();
            entities.add(en);
        } else if (e < 3) {
            en = DataBank.getRandEntity();
        }
        Tile tile = new Tile(t);
        tile.setEntity(en);
        return tile;
    }

    public static String movePlayer(char dir) {
        return movement(dir, player);
    }

    private static String movement(char dir, Entity e) {
        if (!running)
            System.exit(0);

        Coordinate pos = e.getPosition();
        Coordinate newPos = pos.dislocate(dir);
        Tile tile = field.getTile(newPos);
        if (tile.getEntity() == null) {
            tile.setEntity(e);
            field.getTile(pos).setEntity(null);
            e.setPosition(newPos);
            return e.getName() + " moved to (" + newPos.x() + ", " + newPos.y() + ")";
        }
        if (!tile.getEntity().equals(e)) return basicAttack(e, newPos);
        return null;
    }

    public static String basicAttack(int x, int y) {
        if (!running)
            System.exit(0);

        x = player.getPosition().x() + x;
        y = player.getPosition().y() - y;
        Entity e = field.getTile(x, y).getEntity();
        if (e != null) {
            return basicAttack(player, new Coordinate(x, y));
        }
        return null;
    }

    public static String basicAttack(Entity e, Coordinate newPos) {
        return e.getName() + " attacks, " + field.getTile(newPos).getEntity().takeDamage(e.atk());
    }

    public static String useAbility(int x, int y) {
        if (!running)
            System.exit(0);

        x = player.getPosition().x() + x;
        y = player.getPosition().y() - y;
        Tile tile = field.getTile(x, y);
        Entity e = tile.getEntity();
        if (e != null) {
            if (player.getInventory().getHand() instanceof Weapon weapon) {
                Effect temp = weapon.useAbility(player.getInventory());
                if (temp != null)
                    return e.addEffect(temp);
                return "Ability on cooldown.";
            } else
                return "No weapon equipped.";
        }
        return null;
    }

    public static String takeItem() {
        if (!running)
            System.exit(0);

        if (player.getInventory().isFull())
            return "Inventory is full.";

        Coordinate pos = player.getPosition();
        Tile tile = field.getTile(pos);
        if (tile.getItem() != null) {
            Item item = tile.getItem();
            tile.setItem(null);
            return player.takeItem(item);
        }
        return null;
    }

    public static List<String> listPlayerInventory() {
        if (!running)
            System.exit(0);

        List<String> inv = new ArrayList<>();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            Item item = player.getInventory().getItem(i);
            String temp;
            if (item == null)
                temp = "Empty";
            else
                temp = item.getInfo();
            inv.add(temp);
        }
        return inv;
    }

    public static String playerItemInfo(int n) {
        if (!running)
            System.exit(0);

        Item item = player.getInventory().getItem(n);
        if (item == null)
            return "Empty";
        return item.getInfo();
    }

    public static String useItem(int n) {
        if (!running)
            System.exit(0);

        if (player.getInventory().getItem(n) == null)
            return null;
        return player.useItem(n);
    }

    public static String dropItem(int n) {
        if (!running)
            System.exit(0);

        if (player.getInventory().getItem(n) == null)
            return null;
        Coordinate pos = player.getPosition();
        if (field.getTile(pos).getItem() != null)
            return "Can't drop item, there is already an item in the tile.";
        Item item = player.drop(n);
        String temp = player.getName() + " dropped " + item.getName() + ".";
        field.getTile(pos).setItem(item);
        return temp;
    }

    public static List<String> tick() {
        if (!running)
            System.exit(0);
        // IA entra aqui
        List<String> effects = new ArrayList<>();
        for (Entity e : entities) {
            List<String> temp;
            temp = e.tickEffects(e);
            effects.addAll(temp);
        }
        return effects;
    }

    public static String terminal(String command) {
        if (!running)
            System.exit(0);

        String[] cmd = command.split(" ");
        if (cmd[0].equals("additem")) {
            if (cmd.length < 2)
                return "Invalid command.";
            int id = Integer.parseInt(cmd[1]);
            Item item = DataBank.getItem(id);
            if (item == null)
                return "Item not found.";
            player.getInventory().addItem(item);
            return "Item added to inventory.";
        }
        return "Invalid command.";
    }

}
