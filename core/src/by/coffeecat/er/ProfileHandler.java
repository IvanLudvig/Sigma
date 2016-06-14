package by.coffeecat.er;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class ProfileHandler {
	
	public int num=0;
	Json json;

	Sigma game;
	public ProfileHandler(Sigma game) {
		json = new Json();
		this.game = game;
	}
	
	public Array<Profile> getProfiles(){
		Array<Profile> profiles = new Array<Profile>(11);
		for(int i = 0;i<11;i++){
			profiles.add(null);
			FileHandle some = Gdx.files.local(i+".sav");
			FileHandle name = Gdx.files.local(i+"gn.sav");
			if(some.readString()!=null){
				profiles.add(new Profile(game, json.fromJson(String.class, some.readString()), 
						json.fromJson(String.class, Base64Coder.decodeString(name.readString()))));
				profiles.get(i).load();
			}
		}
		return profiles;
	}
	
	public int getCount(){
		for(int i = 0;i<11;i++){
			FileHandle some = Gdx.files.local(i+".sav");
			try{
				some.readString();
				num++;
			}catch(Exception e){
				break;
			}
				
		}
		
		return num;
	}
	
	
	public void saveProfile(Profile profile){
		getCount();
	}

}
