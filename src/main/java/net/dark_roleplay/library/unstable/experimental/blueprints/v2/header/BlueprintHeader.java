package net.dark_roleplay.library.unstable.experimental.blueprints.v2.header;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

/**
 * The BlueprintHeader stores basic Information about a Blueprint
 * @author JTK222
 * @version 1.0
 */
public class BlueprintHeader {

	private int version = 0;
	private String name = "";
	private Set<String> credits = new HashSet<String>();
	private BlockPos size = new BlockPos(5, 5, 5);
	private Set<String> requiredMods = new HashSet<String>();
	private Set<String> layerFiles = new HashSet<String>();
	private String description = "";
	private NBTTagCompound customData = new NBTTagCompound();


	/**
	 * @return The version of this Blueprints data structure.
	 */
	public int getVersion() {
		return this.version;
	}

	/**
	 * Sets the version of this Blueprints data structure
	 * @param version The new version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * @return The name of this Blueprint
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of this Blueprint
	 * @param name The new name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return The description of this Blueprint
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description of this Blueprint
	 * @param description The new name
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return The credits of this Blueprint
	 */
	public Set<String> getCredits() {
		return this.credits;
	}

	/**
	 * Adds the credits String to this Blueprint
	 * @param credits The credits String to add
	 */
	public void addCredits(String credits) {
		this.credits.add(credits);
	}

	/**
	 * @return The size of this Blueprint
	 */
	public BlockPos getSize() {
		return this.size;
	}

	/**
	 * Sets the size of this Blueprint
	 * @param size The new size
	 */
	public void setSize(BlockPos size) {
		this.size = size;
	}

	/**
	 * @return The required mods for this Blueprint
	 */
	public Set<String> getRequiredMods() {
		return this.requiredMods;
	}

	/**
	 * Adds a required mod
	 * @param requiredMod The modid to add
	 */
	public void addRequiredMod(String requiredMod) {
		this.requiredMods.add(requiredMod);
	}

	/**
	 * @return The set of names for the LayerFiles
	 */
	public Set<String> getLayerFiles() {
		return this.layerFiles;
	}

	/**
	 * Adds a LayerFile to the list of LayerFiles
	 * @param layerFile The name of the LayerFile to add without the file ending.
	 */
	public void addLayerFile(String layerFile) {
		this.layerFiles.add(layerFile);
	}

	/**
	 * @param key The key for the NBTTagCompound that you want to obtain.
	 * @return The NBTTagCompound stored under the 'key', or a new NBTTagCompound if the 'key' didn't exist.
	 */
	public NBTTagCompound getCustomData(String key) {
		return this.customData.getCompound(key);
	}

	/**
	 * Adds a custom NBTTagCompound to the header
	 * @param key The key under which to store the NBTTagCompound.<br>
	 * 	The recommended key should contain your modid.
	 * @param comp The NBTTagCompound you want to store.
	 */
	public void addCustomData(String key, NBTTagCompound comp) {
		this.customData.setTag(key, comp);
	}

	public NBTTagCompound getAllCustomData() {
		return this.customData;
	}
}
