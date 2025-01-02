import java.util.Random;

public class Enemy extends Character{
    private static Random random = new Random();
    private static String[] names = {"Goblin", "Skeleton", "Orc", "Troll"};
    private int xpValue;

    public Enemy(){
        this.name = names[random.nextInt(names.length)];
        this.maxHp = random.nextInt(50) + 50;
        this.hp = maxHp;
        this.attack = random.nextInt(10) + 10;
        this.alive = true;
        this.xpValue = maxHp / 2;
    }

    public void attack(Player player){
        int damage = attack + random.nextInt(10);
            System.out.println(name + " dealt " + damage + " damage");
            player.takeDamage(damage);
    }

    public int getXp(){
        return xpValue;
    }
}