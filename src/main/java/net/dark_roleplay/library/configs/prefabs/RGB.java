package net.dark_roleplay.library.configs.prefabs;

import net.minecraftforge.common.config.Config;

public class RGB {

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

	public RGB(float red, float green, float blue) {
		this.RED = red;
		this.GREEN = green;
		this.BLUE = blue;
	}
}
