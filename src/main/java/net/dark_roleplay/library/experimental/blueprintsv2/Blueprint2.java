package net.dark_roleplay.library.experimental.blueprintsv2;

import java.util.List;

import net.dark_roleplay.library.blueprints.Settings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStructure;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created: 17.06.2018
 * Last edit: 17.06.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: heavy unstable, don't use
 */
@Deprecated
public class Blueprint2 {

	private List<String> requiredMods;
	private short sizeX, sizeY, sizeZ;
	private short palleteSize;
	private IBlockState[] pallete;
	private String name;
	private String[] architects;
	private String[] missingMods;

	private byte[][][] structureSmall;
	private short[][][] structureMedium;
	private int[][][] structureLarge;
	private NBTTagCompound[] tileEntities;
	private NBTTagCompound[] entities;
	private NBTTagCompound customTags;
	
	private IBlockState[] palleteCached;
	private Settings cacheKey;
	
	public boolean doesFit(World wrold, Settings settings, boolean cachePallete) {
		IBlockState[] palleteCopy = new IBlockState[this.pallete.length];
		
		for(int i = 0; i < pallete.length; i++) {
			palleteCopy[i] = this.pallete[i].withRotation(settings.getRotation()).withMirror(settings.getMirror());
		}
		
		if(cachePallete) {
			palleteCached = palleteCopy;
			cacheKey = settings;
		}
		
		for (short y = 0; y < sizeY; y++) {
			for (short z = 0; z < sizeZ; z++) {
				for (short x = 0; x < sizeX; x++) {
					IBlockState state;
					if(pallete.length <= Byte.MAX_VALUE) {
						state = pallete[structureSmall[y][z][x]];
					}else if(pallete.length <= Short.MAX_VALUE) {
						state = pallete[structureMedium[y][z][x]];
					}else{
						state = pallete[structureLarge[y][z][x]];
					}
					Block b;
					if(!settings.isEditingMode() && (state.getBlock() == Blocks.STRUCTURE_VOID || (state.getBlock() == Blocks.STRUCTURE_BLOCK && state.getValue(BlockStructure.MODE) == TileEntityStructure.Mode.DATA)))
						continue;
					
					
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Generates a Blueprint in passed world, with the passed settings
	 * @param world
	 * @param settings
	 */
	public void generate(World world, Settings settings) {
		IBlockState[] palleteCopy;
		
		if(cacheKey != null && cacheKey == settings) {
			palleteCopy = palleteCached;
		}else {
			palleteCopy = new IBlockState[this.pallete.length];
			
			for(int i = 0; i < pallete.length; i++) {
				palleteCopy[i] = this.pallete[i].withRotation(settings.getRotation()).withMirror(settings.getMirror());
			}
		}
		
		for (short y = 0; y < sizeY; y++) {
			for (short z = 0; z < sizeZ; z++) {
				for (short x = 0; x < sizeX; x++) {
					IBlockState state;
					if(pallete.length <= Byte.MAX_VALUE) {
						state = pallete[structureSmall[y][z][x]];
					}else if(pallete.length <= Short.MAX_VALUE) {
						state = pallete[structureMedium[y][z][x]];
					}else{
						state = pallete[structureLarge[y][z][x]];
					}
					Block b;
					if(!settings.isEditingMode() && (state.getBlock() == Blocks.STRUCTURE_VOID || (state.getBlock() == Blocks.STRUCTURE_BLOCK && state.getValue(BlockStructure.MODE) == TileEntityStructure.Mode.DATA)))
						continue;
					
					BlockPos rotated = BlueprintUtil2.transformPos(x, y, z, settings.getMirror(), settings.getRotation());
					world.setBlockState(settings.getPos().add(rotated.getX(), rotated.getY(), rotated.getZ()), state, 2);
				}
			}
		}
	}
	
}
