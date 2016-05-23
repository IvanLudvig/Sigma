package by.coffeecat.er.dialog;

import java.io.IOException;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Conversation {
	
	Texture speakui;
	String[] choices = new String[1];
	
	boolean inConversation = false;


	
	Speakui speech;
	Element choice_element;
	Iterator choice_iterator;
	
	public Conversation(String conversation, String person, Element dalshe, int index, InputMultiplexer inputMultiplexer) {	
		XmlReader xml = new XmlReader();
		try {
			int exit = 0;
			Element root = xml.parse(Gdx.files.internal(conversation));

			Element mess = root.getChildByName("message");
			Iterator iterator_mess = root.getChildrenByName("message").iterator();
		
			int choiceCount = root.getChildCount();

			if(dalshe==null){
				choice_element = root.getChildByName("choices");
			}else{
				choice_element = dalshe.getChildrenByName("choice").get(index);
				mess  = choice_element.getChildByName("message");
				System.out.println(mess.getChildCount());
				if(mess.getChildCount()==1){
					exit = 1;
				}
				choice_iterator = choice_element.getChildrenByName("choice").iterator();
				choiceCount = choice_element.getChildrenByName("choice").size;
				
			}
			choices = new String[choiceCount];
			for(int i=0; i<choiceCount; i++){
				choices[i] = choice_element.getChildrenByName("choice").get(i).getText();
			}
			
		
			Element element = (XmlReader.Element)iterator_mess.next();
		
			speech = new Speakui(mess.getText(), person, choices, choice_element, inputMultiplexer, exit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public void render(){
		speech.render(Gdx.graphics.getDeltaTime());
	}
		
		
	public void addChoice(){
		
	}
	
}