package net.dark_roleplay.library.unstable.experimental.guis.elements;

import java.util.List;

import net.dark_roleplay.library.unstable.experimental.guis.IGuiElement;
import net.dark_roleplay.library.unstable.experimental.guis.modular.ModularGui_Drawer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public abstract class Gui_Panel extends IGuiElement.IMPL{
	
	protected boolean isHollow;
	
	public Gui_Panel(int posX, int posY, int width, int height){
		this(posX, posY, width, height, false);
	}
	
	public Gui_Panel(int posX, int posY, int width, int height, boolean isHollow){
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		this.isHollow = isHollow;
	}
	
	@Override
	public int addChild(IGuiElement child) {
		try{
			children.add(child);
			return children.size() - 1;
		}catch(Exception e){
			return -1;
		}
	}

	@Override
	public IGuiElement getChild(int id) {
		if(children.size() >= id && id > 0){
			return children.get(id);
		}else{
			return null;
		}
	}
	
	@Override
	public void setChild(int id, IGuiElement newChild) {
		this.children.set(id, newChild);
	}

	@Override
	public List<IGuiElement> getChildren() {
		return this.children;
	}
	
	@Override
	public void draw(int mouseX, int mouseY, float partialTick) {
		GlStateManager.pushMatrix();
        GlStateManager.translatef((float)(this.posX), (float)(this.posY), -400.0F);
        GlStateManager.enableDepthTest();

		GlStateManager.depthFunc(518);
	    drawRect(0, 0, width, height, -16777216);
	    GlStateManager.depthFunc(515);
		
	    GlStateManager.color3f(1F, 1F, 1F);
	    
	    ModularGui_Drawer.drawBackgroundCenter( 0, 0, this.width, this.height, isHollow);
	    
	    this.drawBackground(mouseX - this.posX, mouseY - this.posY, partialTick);
	    this.drawMiddleground(mouseX - this.posX, mouseY - this.posY, partialTick);
	    this.drawForeground(mouseX - this.posX, mouseY - this.posY, partialTick);
	    
		GlStateManager.popMatrix();
	    GlStateManager.depthFunc(515);
	    GlStateManager.disableDepthTest();
	}
	
	public abstract void drawBackground(int mouseX, int mouseY, float partialTick);
	
	public void drawMiddleground(int mouseX, int mouseY, float partialTick){
		for(IGuiElement child : this.children){
			if(child.isVisible())
				child.draw(mouseX, mouseY, partialTick);
		}
	}
	
	public abstract void drawForeground(int mouseX, int mouseY, float partialTick);
	
	protected static void drawLine(int x1, int y1, int x2, int y2, int color) {

		float f3 = (float) (color >> 24 & 255) / 255.0F;
		float f = (float) (color >> 16 & 255) / 255.0F;
		float f1 = (float) (color >> 8 & 255) / 255.0F;
		float f2 = (float) (color & 255) / 255.0F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder vertexbuffer = tessellator.getBuffer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.color4f(f, f1, f2, f3);

		float angle = (float) ((Math.atan((y1 - y2) / (x1 - x2)) * 180F) / Math.PI);
		System.out.println(angle);
		
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
		vertexbuffer.pos((double) x1 + (y1 < y2 ? -0.5D : 0.5D), (double) y1 + (x1 < x2 ? +0.5D : -0.5D), 0.0D).endVertex();
		vertexbuffer.pos((double) x2 + (y1 < y2 ? -0.5D : 0.5D), (double) y2 + (x1 < x2 ? +0.5D : -0.5D), 0.0D).endVertex();
		vertexbuffer.pos((double) x2 + (y1 > y2 ? -0.5D : 0.5D), (double) y2 + (x1 > x2 ? +0.5D : -0.5D), 0.0D).endVertex();
		vertexbuffer.pos((double) x1 + (y1 > y2 ? -0.5D : 0.5D), (double) y1 + (x1 > x2 ? +0.5D : -0.5D), 0.0D).endVertex();

		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}
	
	public static class IMPL extends Gui_Panel{

		public IMPL(int posX, int posY, int width, int height) {
			super(posX, posY, width, height, true);
		}
		
		public IMPL(int posX, int posY, int width, int height, boolean isHollow) {
			super(posX, posY, width, height, isHollow);
		}

		@Override
		public void drawBackground(int mouseX, int mouseY, float partialTick) {
			
		}

		@Override
		public void drawForeground(int mouseX, int mouseY, float partialTick) {
			
		}
		
	}
}
