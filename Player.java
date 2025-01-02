import java.util.Random;

public class Player extends Character{
    private static Random random = new Random();
    private int level;
    private int xp;
    private int xpToNextLevel;

    public Player(String name){
        this.name = name;
        this.level = 1;
        this.xp = 0;
        this.xpToNextLevel = 100;
        this.maxHp = 100;
        this.hp = maxHp;
        this.attack = 20;
        this.alive = true;
    }

    public void attack(Enemy enemy){
        int damage = attack + random.nextInt(10);
            System.out.println("You dealt " + damage + "damage");
            enemy.takeDamage(damage);
    }

    public void gainXp(int amount){
        xp += amount;
        while (xp >= xpToNextLevel){
            levelUp();
        }
    }

    private void levelUp() {
        level ++;
        xp -= xpToNextLevel;
        xpToNextLevel = level * 100;
        maxHp += 20;
        hp = maxHp;
        attack += 5;
        System.out.println("\nLevel up! You are now level " + level);
    }

    public void rest(){
        hp = maxHp;
        System.out.println("You rested for a night. You feel refreshed! HP restored to " + hp);
    }

    public void showStats(){
        System.out.println("\nLevel: " + level);
        System.out.println("HP: " + hp + "/" + maxHp);
        System.out.println("Attack: " + attack);
        System.out.println("XP: " + xp + "/" + xpToNextLevel);
    }

}