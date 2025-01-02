public abstract class Character{
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected boolean alive;

    public boolean isAlive(){
        return alive;
    }

    public int getHp(){
        return hp;
    }

    public String getName(){
        return name;
    }

    public void takeDamage(int damage){
        hp -= damage;
        if(hp <= 0){
            hp = 0;
            alive = false;
        }
    }
}