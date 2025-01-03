import java.util.Random;
import java.util.Scanner;

public class RPG {
    private static Scanner scanner = new Scanner(System.in);
    private static Player player;
    private static Random random = new Random();

    public static void main(String[] args) {
        initializeGame();
        gameLoop();
    }

    private static void initializeGame() {
        System.out.println("Welcome to the Adventure!");

        while (true) {
            try {
                System.out.print("Enter your character's name: ");
                String name = scanner.nextLine();

                if (name.matches("\\d+")) {
                    throw new IllegalArgumentException("Name cannot be a number. Please enter a valid name.");
                }

                player = new Player(name);
                System.out.println("\nWelcome " + name + "! Your adventure begins...");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void gameLoop() {
        while (player.isAlive()) {
            showMainMenu();
            int choice = getPlayerChoice(1, 4);
            
            switch (choice) {
                case 1 -> exploreArea();
                case 2 -> player.showStats();
                case 3 -> player.rest();
                case 4 -> {
                    System.out.println("Thanks for playing!");
                    return;
                }
            }
        }
        System.out.println("Game Over!");
    }

    private static void showMainMenu() {
        System.out.println("\n1. Explore");
        System.out.println("2. Show Stats");
        System.out.println("3. Rest");
        System.out.println("4. Quit");
    }

    private static void exploreArea() {
        if (random.nextInt(100) < 60) {
            Enemy enemy = new Enemy();
            battle(enemy);
        } else {
            System.out.println("You found nothing interesting...");
        }
    }

    private static void battle(Enemy enemy) {
        System.out.println("\nA " + enemy.getName() + " appears!");
        
        while (enemy.isAlive() && player.isAlive()) {
            System.out.println("\nYour HP: " + player.getHp());
            System.out.println("Enemy HP: " + enemy.getHp());
            System.out.println("\n1. Attack");
            System.out.println("2. Run");
            
            int choice = getPlayerChoice(1, 2);
            if (choice == 1) {
                player.attack(enemy);
                if (enemy.isAlive()) {
                    enemy.attack(player);
                }
            } else {
                if (random.nextInt(100) < 50) {
                    System.out.println("You escaped successfully!");
                    return;
                } else {
                    System.out.println("Couldn't escape!");
                    enemy.attack(player);
                }
            }
        }
        
        if (player.isAlive()) {
            System.out.println("You won! Gained " + enemy.getXp() + " XP!");
            player.gainXp(enemy.getXp());
        }
    }

    private static int getPlayerChoice(int min, int max) {
        while (true) {
            try {
                System.out.print("Choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                }
            } catch (NumberFormatException e) {}
            System.out.println("Please enter a valid choice (" + min + "-" + max + ")");
        }
    }
}