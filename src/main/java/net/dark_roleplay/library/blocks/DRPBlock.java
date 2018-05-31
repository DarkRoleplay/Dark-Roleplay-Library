package net.dark_roleplay.library.blocks;

import java.util.Random;

import net.dark_roleplay.library.blocks.behaviors.IActivatedBehavior;
import net.dark_roleplay.library.blocks.behaviors.IBreakingBehavior;
import net.dark_roleplay.library.blocks.behaviors.IExplodedBehavior;
import net.dark_roleplay.library.blocks.behaviors.IAddedBehavior;
import net.dark_roleplay.library.blocks.behaviors.IClickedBehavior;
import net.dark_roleplay.library.blocks.behaviors.ICollidedWithBehavior;
import net.dark_roleplay.library.blocks.behaviors.IDestroyedBehavior;
import net.dark_roleplay.library.blocks.behaviors.IFallenUponBehavior;
import net.dark_roleplay.library.blocks.behaviors.IHarvestedBehavior;
import net.dark_roleplay.library.blocks.behaviors.IPlacedBehavior;
import net.dark_roleplay.library.blocks.behaviors.IWalkedUpon;
import net.dark_roleplay.library.blocks.behaviors.IPlacementBehavior;
import net.dark_roleplay.library.blocks.behaviors.IRainTickBehavior;
import net.dark_roleplay.library.blocks.behaviors.IRandomDisplayTickBehavior;
import net.dark_roleplay.library.blocks.behaviors.IRandomTickBehavior;
import net.dark_roleplay.library.blocks.behaviors.IUpdateTickBehavior;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created: 31.05.2018
 * Last edit: 31.05.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: Experimental + W.i.P.
 */
public class DRPBlock extends Block {

	/** Behaviours **/
	private IActivatedBehavior onActivatedBehavior = null;	
	private IAddedBehavior addedBehavior = null;
	private IBreakingBehavior breakingBehavior = null;
	private IClickedBehavior clickedBehavior = null;
	private ICollidedWithBehavior collidedWithBehavior = null;
	private IDestroyedBehavior destroyedBehavior = null;
	private IExplodedBehavior explodedBehavior = null;
	private IFallenUponBehavior fallenUponBehavior = null;
	private IHarvestedBehavior harvestedBehavior = null;
	private IPlacedBehavior placedBehavior = null;
	private IPlacementBehavior placementBehavior = null;
	private IRainTickBehavior rainTickBehavior = null;
	private IRandomDisplayTickBehavior randomDisplayTickBehavior = null;
	private IRandomTickBehavior randomTickBehavior = null;
	private IUpdateTickBehavior updateTickBehavior = null;
	private IWalkedUpon walkedUponBehavior = null;
	

	public DRPBlock(String name, BlockSettings settings) {
		super(settings.getMaterial(), settings.getMapColor());
		this.setSoundType(settings.getSoundType());
		this.setHardness(settings.getHardness());
		this.setResistance(settings.getBlastResistance());
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (onActivatedBehavior != null)
			return this.onActivatedBehavior.execute(world, pos, state, player, hand, facing, hitX, hitY, hitZ);
		return false;
	}
	
	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
		if(this.placementBehavior != null)
			return this.placementBehavior.execute(world, pos, side);
		return world.getBlockState(pos).getBlock().isReplaceable(world, pos);
	}
	

	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if(this.destroyedBehavior != null)
			this.destroyedBehavior.execute(world, pos, state);
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if(this.placedBehavior != null)
			this.placedBehavior.execute(world, pos, state, placer, stack);
	}
	
	@Override
	public void randomTick(World world, BlockPos pos, IBlockState state, Random random) {
		if(this.randomTickBehavior != null)
			this.randomTickBehavior.execute(world, pos, state, random);
		else
			this.updateTick(world, pos, state, random);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if(this.updateTickBehavior != null)
			this.updateTickBehavior.execute(world, pos, state, rand);
	}
	
	@Override
	public void fillWithRain(World world, BlockPos pos) {
		if(this.rainTickBehavior != null)
			this.rainTickBehavior.execute(world, pos);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand) {
		if(this.randomDisplayTickBehavior != null)
			this.randomDisplayTickBehavior.execute(state, world, pos, rand);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if(this.addedBehavior != null)
			this.addedBehavior.execute(world, pos, state);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		if(this.breakingBehavior != null)
			this.breakingBehavior.execute(world, pos, state);
		else if (hasTileEntity(state)) {
			world.removeTileEntity(pos);
		}
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		if(this.explodedBehavior != null)
			this.explodedBehavior.execute(world, pos, explosion);
	}

	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if(this.walkedUponBehavior != null)
			this.walkedUponBehavior.execute(world, pos, entity);
	}

	@Override
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if(this.clickedBehavior != null)
			this.clickedBehavior.execute(world, pos, player);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if(this.collidedWithBehavior != null)
			this.collidedWithBehavior.execute(world, pos, state, entity);
	}

	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
		if(this.fallenUponBehavior != null)
			this.fallenUponBehavior.execute(world, pos, entity, fallDistance);
		else entity.fall(fallDistance, 1.0F);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if(this.harvestedBehavior != null)
			this.harvestedBehavior.execute(world, pos, state, player);
	}

	/**
	 * Sets the Behavior that's being called when the Block is Activated
	 * @param behaviour
	 * @return itself
	 */
	public DRPBlock setActivationBehavior(IActivatedBehavior behavior) {
		this.onActivatedBehavior = behavior;
		return this;
	}
	
	/**
	 * Sets the Behavior that's being called when a player tries to place this block
	 * @param behaviour
	 * @return itself
	 */
	public DRPBlock setPlacementBehavior(IPlacementBehavior behavior) {
		this.placementBehavior = behavior;
		return this;
	}
}
