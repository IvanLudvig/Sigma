package by.coffeecat.er.ui;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;

import by.coffeecat.er.objects.Item;

public class InProperties {
	
	ArrayList<Array<String>> items = new ArrayList<Array<String>>(125);

	public InProperties(ArrayList<Array<Item>> maj) {
		for (int i = 0; i < 5; i++) {
			  items.add(new Array<String>(25));
		}
		for(Array<String> array: items){
			for (int i = 0; i < 25; i++) {
				array.add(null);
			}
		}
		for(int g=0;g<4;g++){   
			for(int i=0;i<maj.get(g).size;i++){
				if(maj.get(g).get(i)!=null){
					System.out.println(maj.get(g).get(i).getClass().getName());
					items.get(g).add(maj.get(g).get(i).getClass().getSimpleName());
				}
			}
		}
	}

}
