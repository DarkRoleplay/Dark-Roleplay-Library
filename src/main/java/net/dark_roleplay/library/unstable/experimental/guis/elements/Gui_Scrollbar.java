package net.dark_roleplay.library.unstable.experimental.guis.elements;

import org.lwjgl.glfw.GLFW;

import net.dark_roleplay.library.unstable.experimental.guis.IGuiElement;
import net.dark_roleplay.library.unstable.experimental.guis.modular.ModularGui_Drawer;
import net.dark_roleplay.library.unstable.experimental.variables.wrappers.IntegerWrapper;
import net.minecraft.client.util.InputMappings;

public class Gui_Scrollbar extends IGuiElement.IMPL{

	private IntegerWrapper var;
	
	private int min, max;
	private boolean direction;
	private int lastX, lastY;
	private boolean lastPressed;
	private float rangePerPixel;

	
	//dir = true = horizontal
	public Gui_Scrollbar(IntegerWrapper var, int min, int max, boolean dir){
		this.var = var;
		this.min = min;
		this.max = max;
		this.direction = dir;
		this.rangePerPixel = (max - min) / this.width;
	}
	
	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		
		if(mouseX >= 0 && mouseX < this.width && mouseY >= 0 && mouseY < this.height){
			if (InputMappings.isKeyDown(GLFW.GLFW_MOUSE_BUTTON_1) && lastPressed){
				int moveX = this.lastX - mouseX;
				int moveY = this.lastY - mouseY;
				this.lastX = mouseX;
				this.lastY = mouseY;
				this.var.add(moveX);
			}else if(InputMappings.isKeyDown(GLFW.GLFW_MOUSE_BUTTON_1)){
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
