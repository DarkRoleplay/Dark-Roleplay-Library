package net.dark_roleplay.library.configs.prefabs;

import net.minecraftforge.common.config.Config;

/**
 * Created: 27.06.2018
 * Last edit: 27.06.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: completed
 */
public class RGBA {
	
	@Config.Name("Red Value")
	@Config.Comment("1.0 = full red, 0.0 = not red at all")
	@Config.RangeDouble(min = 0.0, max = 1.0)
	public float RED = 1.0F;
	
	@Config.Name("Green Value")
	@Config.Comment("1.0 = full green, 0.0 = not green at all")
	@Config.RangeDouble(min = 0.0, max = 1.0)
	public float GREEN = 1.0F;
	
	@Config.Name("Blue Value")
	@Config.Comment("1.0 = full blue, 0.0 = not blue at all")
	@Config.RangeDouble(min = 0.0, max = 1.0)
	public float BLUE = 1.0F;
	
	@Config.Name("Alpha Value")
	@Config.Comment("1.0 = full visible, 0.0 = not visible at all")
	@Config.RangeDouble(min = 0.0, max = 1.0)
	public float ALPHA = 1.0F;
	
	public RGBA(float red, float green, float blue, float alpha) {
		this.RED = red;
		this.GREEN = green;
		this.BLUE = blue;
		this.ALPHA = alpha;
	}
}