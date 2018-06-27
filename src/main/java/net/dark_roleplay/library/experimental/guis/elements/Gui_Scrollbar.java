package net.dark_roleplay.library.experimental.guis.elements;

import org.lwjgl.input.Mouse;

import net.dark_roleplay.library.experimental.guis.IGuiElement;
import net.dark_roleplay.library.experimental.guis.modular.ModularGui_Drawer;
import net.dark_roleplay.library.experimental.variables.wrappers.Variable_Int;

public class Gui_Scrollbar extends IGuiElement.IMPL{

	private Variable_Int var;
	
	private int min, max;
	private boolean direction;
	private int lastX, lastY;
	private boolean lastPressed;
	private float rangePerPixel;

	
	//dir = true = horizontal
	public Gui_Scrollbar(Variable_Int var, int min, int max, boolean dir){
		this.var = var;
		this.min = min;
		this.max = max;
		this.direction = dir;
		this.rangePerPixel = (max - min) / this.width;
	}
	
	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		
		if(mouseX >= 0 && mouseX < this.width && mouseY >= 0 && mouseY < this.height){
			if (Mouse.isButtonDown(0) && lastPressed){
				int moveX = this.lastX - mouseX;
				int moveY = this.lastY - mouseY;
				this.lastX = mouseX;
				this.lastY = mouseY;
				this.var.add(moveX);
			}else if(Mouse.isButtonDown(0)){
				lastPressed = true;
			}else{
				lastPressed = false;
			}
		}
		this.lastX = mouseX;
		this.lastY = mouseY;
		
		ModularGui_Drawer.drawButtons(this.posX, this.posY, this.width, this.height, ModularGui_Drawer.State.DISABLED);
		ModularGui_Drawer.drawButtons((int)((this.var.get() - min) / rangePerPixel), this.posY, this.width, this.height, ModularGui_Drawer.State.ENABLED);
	}


}
