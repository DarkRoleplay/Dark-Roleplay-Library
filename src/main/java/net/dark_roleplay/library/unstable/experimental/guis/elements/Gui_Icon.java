package net.dark_roleplay.library.unstable.experimental.guis.elements;

import net.dark_roleplay.library.unstable.experimental.guis.IGuiElement;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Gui_Icon extends IGuiElement.IMPL{

	private ResourceLocation icon;
	
	public Gui_Icon(ResourceLocation icon){
		this.icon = icon;
	}
	
	@Override
	public void draw(int mouseX, int mouseY, float partialTicks) {
		if(icon != null){
			Minecraft.getInstance().textureManager.bindTexture(this.icon);
			this.drawPerctentedRect(this.posX, this.posY, this.width, this.height, 0F, 0F, 1F, 1F);
		}
	}

	public Gui_Icon getClone(){
		return new Gui_Icon(this.icon);
	}
}
