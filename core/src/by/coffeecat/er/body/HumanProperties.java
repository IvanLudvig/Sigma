package by.coffeecat.er.body;

import by.coffeecat.er.magic.Magic;
import by.coffeecat.er.objects.clothes.Clothes;

public class HumanProperties {

	public String chesta;
	public String feeta;
	public String hata;
	
	public int allhp = 100;
	public int hp = allhp;
	
	public int armour = allhp;
	
	public int allmana = 500;
	public int mana = allmana;
	//public float reRate = 1f;
	
	public int allstrength = 300;
	public int strength = allstrength;

	public String magico[] = new String[10];
	
	public HumanProperties(Clothes chesta, Clothes feeta, Clothes hata, int allhp, int hp, int armour, int allmana, int mana, int reRate,
			int allstrength, int strength, Magic[] magico) {
		this.chesta = chesta.getClass().getSimpleName();
		this.feeta = feeta.getClass().getSimpleName();
		this.hata = hata.getClass().getSimpleName();
		
		this.allhp = allhp;
		this.hp = hp;
		this.armour = armour;
		this.allmana = allmana;
		this.mana = mana;
		//this.reRate = reRate;
		this.allstrength = allstrength;
		this.strength = strength;
		
		for(int y = 0; y<magico.length; y++){
			if(magico[y]!=null){
				this.magico[y] = magico[y].getClass().getSimpleName();
				System.out.println(magico[y].getClass().getSimpleName());
			}
		}
	}
	
}
