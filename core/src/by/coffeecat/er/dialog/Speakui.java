package by.coffeecat.er.dialog;

import java.util.ArrayList;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.XmlReader.Element;

import by.coffeecat.er.Sigma;

public class Speakui extends Window{
	
	SpriteBatch batch = new SpriteBatch();
	BitmapFont font;

	int choicenum = 4;
	Stage stage;
	Label dialogText;
	
	List<String> choiceList;
	ScrollPane scroller;
	TextButton closeButton;
	
	static Skin skin = Sigma.skin;
	Label words_label;
	ArrayList <Label> choices;

	String[] choices_string;
	Conversation dalshe_con;
	TextButton exitbtn;
	int render = 0;
	
	public Speakui(String words, final String person, String[] choices, final Element element, final InputMultiplexer inputMultiplexer, int exit) {
		super(person, skin);
		words_label = new Label(words, skin);
		words_label.setSize(10, 100);
		this.setPosition(0, 0);
		this.setSize(800, 150);
		
		exitbtn = new TextButton("X", skin);
		stage = new Stage(Sigma.viewport);
		
		choiceList = new List<String>(skin);
		scroller = new ScrollPane(choiceList, skin);

	    choices_string = choices;
	    System.out.println();
		choiceList.setItems(choices_string);
		
		choiceList.setTouchable(Touchable.enabled);
		this.setTouchable(Touchable.enabled);
		
		choiceList.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				String choiceSelected = (String) choiceList.getSelected();
				if(choice == null) {
					return;
				}else{
					System.out.println(choiceList.getSelectedIndex() );
					dalshe_con = new Conversation("dialogues/1.xml", person, element, choiceList.getSelectedIndex(), inputMultiplexer );
					stage.dispose();
				}
			}
		});
		
		exitbtn.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				render =1;
			}
		});

		
		super.add(words_label);
		super.row();
		if(exit == 1){
			super.add(exitbtn);
		}
		if(choiceList.getItems().size>0){
			super.add(choiceList).bottom();
		}
		
		font = Sigma.font;
		
		super.pack();
		stage.addActor(this);
		
		inputMultiplexer.addProcessor(stage);
		System.out.println(exit);
	}
	
	String choice = "ol";
	
	public void render(float delta){
		if(render==0){
			this.act(delta);
			stage.act(delta);
			stage.draw();
			if(dalshe_con!=null){
				dalshe_con.render();
			}
		}
	}
	
}

