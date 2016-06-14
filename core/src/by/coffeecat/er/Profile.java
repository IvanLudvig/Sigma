package by.coffeecat.er;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;

import by.coffeecat.er.body.HumanProperties;
import by.coffeecat.er.objects.Item;

public class Profile {

	public static String name;
	Sigma game;
	String mag;
	String it;
	String map;
	public String gender;
	
	Json json;
	
	FileHandle magf;
	FileHandle itf;
	FileHandle mapf;
	FileHandle namef;
	FileHandle gn;
	
	int number;
	
	ArrayList<Array<Item>> maj;
	
	public Profile(Sigma game, String name, String gender) {
		this.game = game;
		//save();
		number = game.prohand.getCount();
		this.name = name;
		this.gender = gender;
		json = new Json();
		
		magf = Gdx.files.local(game.prohand.getCount()+"magf.sav");
		itf = Gdx.files.local(game.prohand.getCount()+"itf.sav");
		mapf = Gdx.files.local(game.prohand.getCount()+"mapf.sav");
		gn = Gdx.files.local(game.prohand.getCount()+"gn.sav");
		namef = Gdx.files.local(game.prohand.getCount()+".sav");
		
		name = json.toJson(name);
		name = Base64Coder.encodeString(name);
		namef.writeString(name, false);
		
		//save();
	}
	
	public void newGame(String name){
		this.name = name;
	}
	
	public void save(){
		json = new Json();
		json.setOutputType(OutputType.json);
		for(int i = 0;i<game.maps.length;i++){
			if(game.gama.current == game.maps[i]){
				mapf.writeString(Base64Coder.encodeString(json.toJson(i)), false);
			}
		}
		gn.writeString(Base64Coder.encodeString(json.toJson(gender)), false);
		itf.writeString(Base64Coder.encodeString(json.toJson(game.ui.inventory.props)), false);
		magf.writeString(Base64Coder.encodeString(json.toJson(game.gama.mage.props)), false);
		System.out.println(json.toJson(game.gama.mage.props));

	}
	
	public void load(){
		mag = magf.readString();
		it = itf.readString();
		map = mapf.readString();
		name = namef.readString();
		
		mag = Base64Coder.decodeString(mag);
		it = Base64Coder.decodeString(it);
		map = Base64Coder.decodeString(map);
		
		name = json.fromJson(String.class, name);
		gender = json.fromJson(String.class, Base64Coder.decodeString(gn.readString()));
		game.gama.current = game.maps[json.fromJson(int.class, map)];
		game.gama.mage.applyProps(json.fromJson(HumanProperties.class, mag));
		game.ui.inventory.maj = game.nor.nor(json.fromJson(ArrayList.class, it));
		
	}
	
	public void loadFrom(FileHandle magf, FileHandle itf, FileHandle mapf, FileHandle namef, FileHandle gn){
		
	}

}
