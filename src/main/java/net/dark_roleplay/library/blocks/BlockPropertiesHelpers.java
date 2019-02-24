package net.dark_roleplay.library.blocks;

import com.google.gson.JsonObject;

import net.minecraft.block.Block.Properties;
import net.minecraft.block.material.Material;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class BlockPropertiesHelpers {

	public Properties loadPropertiesFromJson(JsonObject object){
		Material mat = getMaterial(new ResourceLocation(JsonUtils.getString(object, "material", "minecraft:rock")));
		int lightValue = JsonUtils.getInt(object, "light_value", 0);
		float resistance = JsonUtils.getFloat(object, "resistance", 0F);
		float hardness = JsonUtils.getFloat(object, "hardness", 0F);
		float slipperiness = JsonUtils.getFloat(object, "hardness", 0.6F);
		boolean blocksMovement = JsonUtils.getBoolean(object, "blocks_movement", true);
		boolean needsRandomTick = JsonUtils.getBoolean(object, "does_tick", false);
		boolean variableOpacity = JsonUtils.getBoolean(object, "has_variable_opacity", false);
		//	      private Material material;
//	      private MaterialColor mapColor;
//	      private boolean blocksMovement = true;
//	      private SoundType soundType = SoundType.STONE;
//	      private int lightValue;
//	      private float resistance;
//	      private float hardness;
//	      private boolean needsRandomTick;
//	      private float slipperiness = 0.6F;
//	      private boolean variableOpacity;
		
		Properties prop = Properties.create(mat)
				.lightValue(lightValue)
				.hardnessAndResistance(hardness, resistance)
				.slipperiness(slipperiness);
		
		if(!blocksMovement)
			prop.doesNotBlockMovement();
		if(needsRandomTick)
			prop.doesNotBlockMovement();
		if(variableOpacity)
			prop.variableOpacity();
				
		return prop;
	}
	
	private static Material getMaterial(ResourceLocation location) {
		
		String namespace = location.getNamespace();
		String path = location.getPath();
		
		if("minecraft".equals(namespace)) {
			if("air".equals(path)) return Material.AIR;
			else if("anvil".equals(path)) return Material.ANVIL;
			else if("barrier".equals(path)) return Material.BARRIER;
			else if("bubble_column".equals(path)) return Material.BUBBLE_COLUMN;
			else if("cactus".equals(path)) return Material.CACTUS;
			else if("cake".equals(path)) return Material.CAKE;
			else if("carpet".equals(path)) return Material.CARPET;
			else if("circuits".equals(path)) return Material.CIRCUITS;
			else if("clay".equals(path)) return Material.CLAY;
			else if("cloth".equals(path)) return Material.CLOTH;
			else if("coral".equals(path)) return Material.CORAL;
			else if("crafted_snow".equals(path)) return Material.CRAFTED_SNOW;
			else if("dragon_egg".equals(path)) return Material.DRAGON_EGG;
			else if("fire".equals(path)) return Material.FIRE;
			else if("glass".equals(path)) return Material.GLASS;
			else if("gourd".equals(path)) return Material.GOURD;
			else if("grass".equals(path)) return Material.GRASS;
			else if("ground".equals(path)) return Material.GROUND;
			else if("ice".equals(path)) return Material.ICE;
			else if("iron".equals(path)) return Material.IRON;
			else if("lava".equals(path)) return Material.LAVA;
			else if("leaves".equals(path)) return Material.LEAVES;
			else if("ocean_plant".equals(path)) return Material.OCEAN_PLANT;
			else if("paclet_ice".equals(path)) return Material.PACKED_ICE;
			else if("piston".equals(path)) return Material.PISTON;
			else if("plants".equals(path)) return Material.PLANTS;
			else if("portal".equals(path)) return Material.PORTAL;
			else if("redstone_light".equals(path)) return Material.REDSTONE_LIGHT;
			else if("rock".equals(path)) return Material.ROCK;
			else if("sand".equals(path)) return Material.SAND;
			else if("sea_grass".equals(path)) return Material.SEA_GRASS;
			else if("snow".equals(path)) return Material.SNOW;
			else if("sponge".equals(path)) return Material.SPONGE;
			else if("structure_void".equals(path)) return Material.STRUCTURE_VOID;
			else if("tnt".equals(path)) return Material.TNT;
			else if("vine".equals(path)) return Material.VINE;
			else if("water".equals(path)) return Material.WATER;
			else if("web".equals(path)) return Material.WEB;
			else if("wood".equals(path)) return Material.WOOD;
		}
		
		return null;
	}
}
