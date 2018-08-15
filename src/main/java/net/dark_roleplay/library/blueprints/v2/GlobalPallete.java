package net.dark_roleplay.library.blueprints.v2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import com.google.common.collect.UnmodifiableIterator;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.nbt.NBTUtil;
import net.minecraftforge.common.util.Constants.NBT;

public class GlobalPallete {

	private IBlockState[] blockstates;
	
	public GlobalPallete(IBlockState... blockstates) {
		this.blockstates = blockstates;
	}
	
	public NBTTagCompound writeToNBT() {
		NBTTagCompound compound = new NBTTagCompound();
		
		//Do the Blockstates and the true pallete
		NBTTagList blockstatesTag = new NBTTagList();
		for(int i = 0; i < blockstates.length; i++) {
			NBTTagCompound stateTag = NBTUtil.writeBlockState(new NBTTagCompound(), blockstates[i]);
			blockstatesTag.appendTag(stateTag);
		}
		
		compound.setTag("BlockStates", blockstatesTag);
		
		return compound;
	}
	
	public static GlobalPallete readFromNBT(NBTTagCompound compound) {
		if(!compound.hasKey("BlockStates")) return null;
		
		NBTTagList statesTag = compound.getTagList("BlockStates", NBT.TAG_COMPOUND);
		IBlockState[] states = new IBlockState[statesTag.tagCount()];
		
		for(int i = 0; i < statesTag.tagCount(); i++){
			states[i] = NBTUtil.readBlockState(statesTag.getCompoundTagAt(i));
		}
		
		return new GlobalPallete(states);
	}
}
