package net.dark_roleplay.library.experimental.guis.elements.buttons;

import net.dark_roleplay.library.experimental.guis.elements.Gui_Button;
import net.dark_roleplay.library.experimental.variables.wrappers.IntegerWrapper;

public class Button_ChangeInt extends Gui_Button{

	private IntegerWrapper var;
	private int amount;
	
	public Button_ChangeInt(IntegerWrapper var, int amount, int posX, int posY, int width, int height) {
		super(posX, posY, width, height);
		this.var = var;
		this.amount = amount;
	}
	
	@Override
	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton){
		this.var.add(amount);
		return true;
	}
}
