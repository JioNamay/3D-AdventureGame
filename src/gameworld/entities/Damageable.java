package gameworld.entities;

public interface Damageable {
	public boolean canHealthRegenerate();
	public void die();
	public void recover(int amount);
	public void hurt(int amount);
}
