package net.dark_roleplay.library.experimental.guis.elements.buttons;

import net.dark_roleplay.library.experimental.guis.elements.Gui_Button;
import net.dark_roleplay.library.experimental.variables.wrappers.BooleanWrapper;

public class Button_ChangeBool extends Gui_Button{

	private BooleanWrapper var;
	private int amount;
	
	public Button_ChangeBool(BooleanWrapper var, int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.var = var;
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton){
		this.var.set(!this.var.get());
		this.setText(String.valueOf(this.var.get()).toUpperCase());
		return true;
	}
}
