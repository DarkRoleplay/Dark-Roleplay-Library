package net.dark_roleplay.library.experimental.blueprints.v2;

import net.dark_roleplay.library.experimental.blueprints.v2.exception.DataCorruptedException;
import net.dark_roleplay.library.experimental.blueprints.v2.exception.VersionNotSupportedException;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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
		compound.setInteger("Version", 1);
		
		return compound;
	}
	
	public static GlobalPallete readFromNBT(NBTTagCompound compound) throws VersionNotSupportedException, DataCorruptedException{
		if(!compound.hasKey("Version")) throw new VersionNotSupportedException("No version data found");
		
		int version = compound.getInteger("Version");
		switch(version) {
			case 1:{
				if(!compound.hasKey("BlockStates")) throw new DataCorruptedException("No 'BlockStates' key found, data seems to be corrupted");
				
				NBTTagList statesTag = compound.getTagList("BlockStates", NBT.TAG_COMPOUND);
				IBlockState[] states = new IBlockState[statesTag.tagCount()];
				
				for(int i = 0; i < statesTag.tagCount(); i++){
					states[i] = NBTUtil.readBlockState(statesTag.getCompoundTagAt(i));
				}
				
				return new GlobalPallete(states);
			}
			default:
				 throw new VersionNotSupportedException(String.format("Version '%d' is not supported", version));
		}
	}
}
