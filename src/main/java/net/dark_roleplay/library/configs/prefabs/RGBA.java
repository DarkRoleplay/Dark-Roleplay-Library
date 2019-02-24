package net.dark_roleplay.library.configs.prefabs;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Created: 27.06.2018
 * Last edit: 27.06.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: completed
 */
public class RGBA{
	
	public final ForgeConfigSpec.DoubleValue red;
	public final ForgeConfigSpec.DoubleValue green;
	public final ForgeConfigSpec.DoubleValue blue;
	public final ForgeConfigSpec.DoubleValue alpha;
	
	public RGBA(String key, ForgeConfigSpec.Builder builder) {
		this(key, builder, 1.0, 1.0, 1.0, 1.0);
	}

	
	public RGBA(String key, ForgeConfigSpec.Builder builder, double defaultRed, double defaultGreen, double defaultBlue, double defaultAlpha) {
		builder.comment("1.0 = maximum color intensity, 0.0 = minimum color intensity", "The alpha value defines the transparency of the color.").push(key);
		
		this.red = builder
			.translation("drplibrary.config.red")
			.defineInRange("red", defaultRed, 0.0, 1.0);
		this.green = builder
				.translation("drplibrary.config.green")
				.defineInRange("green", defaultGreen, 0.0, 1.0);
		this.blue = builder
				.translation("drplibrary.config.blue")
				.defineInRange("blue", defaultBlue, 0.0, 1.0);
		this.alpha = builder
				.translation("drplibrary.config.alpha")
				.defineInRange("alpha", defaultAlpha, 0.0, 1.0);
		builder.pop();
	}
}