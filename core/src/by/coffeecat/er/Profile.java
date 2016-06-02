package by.coffeecat.er;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class Profile {

	static String name;
	Sigma game;
	
	String mag;
	String it;
	String map;
	String whole;
	
	public String gender;
	
	Json json;
	
	FileHandle wholef = Gdx.files.local("wholef.sav");
	FileHandle magf = Gdx.files.local("magf.sav");
	FileHandle itf = Gdx.files.local("itf.sav");
	FileHandle mapf = Gdx.files.local("mapf.sav");
	FileHandle namef = Gdx.files.local("namef.sav");
	
	
	public Profile() {
		//save();
		
		//whole = json.toJson(whole);
		//whole = Base64Coder.encodeString(whole);
		//wholef.writeString(whole, false);
	}
	
	public void newGame(String name){
		this.name = name;
	}
	
	public void save(){
		json = new Json();

		name = json.toJson(name);
		mag = json.toJson(game.gama.mage);
		it = json.toJson(game.ui.inventory.maj);
		map = json.toJson(game.gama.current);

		mag = Base64Coder.encodeString( mag );
		it = Base64Coder.encodeString( it );
		map = Base64Coder.encodeString( it );

		magf.writeString(mag, false);
		itf.writeString(it, false);
		mapf.writeString(map, false);
		namef.writeString(name, false);
	}
	
	public void load(){
		mag = magf.readString();
		it = itf.readString();
		map = mapf.readString();
		name = namef.readString();
		
		mag = Base64Coder.decodeString(mag);
		it = Base64Coder.decodeString(it);
		map = Base64Coder.decodeString(map);
	}

}
