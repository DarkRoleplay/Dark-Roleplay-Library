package net.dark_roleplay.library.experimental.blocks;

import java.util.Random;
import java.util.concurrent.Callable;

import javax.annotation.Nullable;

import net.dark_roleplay.library.experimental.blocks.behaviors.IActivatedBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IAddedBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IBlockBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IBoundingBoxBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IBreakingBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IClickedBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.ICollidedWithBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IDestroyedBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IExplodedBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IFallenUponBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IHarvestedBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.INeighborChangedBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IPlacedBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IPlacementBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IRainTickBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IRandomDisplayTickBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IRandomTickBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IUpdateTickBehavior;
import net.dark_roleplay.library.experimental.blocks.behaviors.IWalkedUponBehavior;
import net.minecraft.block.Block;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
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
	private IBoundingBoxBehavior boundingBoxBehavior = null;
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
	private IWalkedUponBehavior walkedUponBehavior = null;
	private INeighborChangedBehavior neighborChangedBehavior = null;

	//TODO add Collission Box Behavior (plants like)

	private BlockSettings settings;
	private Callable<TileEntity> tileEntityFactory;

	public DRPBlock(String name, BlockSettings settings) {
		super(settings.getMaterial(), settings.getMapColor());
		this.settings = settings;
		this.setRegistryName(name);
		this.setTranslationKey(this.getRegistryName().getNamespace() + "." + name);
		this.setSoundType(settings.getSoundType());
		this.setHardness(settings.getHardness());
		this.setResistance(settings.getBlastResistance());
		this.setLightLevel(settings.getLightLevel());
		this.setLightOpacity(settings.getLightOpacity());
		this.fullBlock = this.getDefaultState().isOpaqueCube();
		this.setDefaultSlipperiness(settings.getSlipperiness());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getRenderLayer(){
		return this.settings != null ? this.settings.getBlockRenderLayer() : BlockRenderLayer.SOLID;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess world, IBlockState state, BlockPos pos, EnumFacing facing){
		return this.settings != null ? this.settings.getFaceShape() : BlockFaceShape.SOLID;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state){
		return this.settings != null ? this.settings.getRenderType() : EnumBlockRenderType.MODEL;
    }

	@Override
	public boolean isFullCube(IBlockState state){
		return this.settings != null ? this.settings.isFullCube() : true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state){
		return this.settings != null ? this.settings.isOpaqueCube() : true;
	}

	/** ---------- Behaviours ---------- **/

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if(this.boundingBoxBehavior != null)
			return this.boundingBoxBehavior.getBoundingBox(state, source, pos);
		return FULL_BLOCK_AABB;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (this.onActivatedBehavior != null)
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
	public void onPlayerDestroy(World world, BlockPos pos, IBlockState state) {
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
		else if (this.hasTileEntity(state)) {
			world.removeTileEntity(pos);
		}
	}

	@Override
	public void onExplosionDestroy(World world, BlockPos pos, Explosion explosion) {
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
	public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entity) {
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

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
		if(this.neighborChangedBehavior != null)
			this.neighborChangedBehavior.execute(state, world, pos, block, fromPos);
	}

	@Override
	public boolean hasTileEntity(IBlockState state){
		return this.tileEntityFactory != null;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(World world, IBlockState state){
		if(this.tileEntityFactory != null) {
			try {
				return this.tileEntityFactory.call();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public DRPBlock addBehaviors(IBlockBehavior... behaviors) {

		for(IBlockBehavior behavior : behaviors) {
			if(behavior instanceof IActivatedBehavior) {
				this.onActivatedBehavior = (IActivatedBehavior) behavior;
			}
			if(behavior instanceof IAddedBehavior) {
				this.addedBehavior = (IAddedBehavior) behavior;
			}
			if(behavior instanceof IBoundingBoxBehavior) {
				this.boundingBoxBehavior = (IBoundingBoxBehavior) behavior;
			}
			if(behavior instanceof IBreakingBehavior) {
				this.breakingBehavior = (IBreakingBehavior) behavior;
			}
			if(behavior instanceof IClickedBehavior) {
				this.clickedBehavior = (IClickedBehavior) behavior;
			}
			if(behavior instanceof ICollidedWithBehavior) {
				this.collidedWithBehavior = (ICollidedWithBehavior) behavior;
			}
			if(behavior instanceof IDestroyedBehavior) {
				this.destroyedBehavior = (IDestroyedBehavior) behavior;
			}
			if(behavior instanceof IExplodedBehavior) {
				this.explodedBehavior = (IExplodedBehavior) behavior;
			}
			if(behavior instanceof IFallenUponBehavior) {
				this.fallenUponBehavior = (IFallenUponBehavior) behavior;
			}
			if(behavior instanceof IHarvestedBehavior) {
				this.harvestedBehavior = (IHarvestedBehavior) behavior;
			}
			if(behavior instanceof IPlacedBehavior) {
				this.placedBehavior = (IPlacedBehavior) behavior;
			}
			if(behavior instanceof IPlacementBehavior) {
				this.placementBehavior = (IPlacementBehavior) behavior;
			}
			if(behavior instanceof IRainTickBehavior) {
				this.rainTickBehavior = (IRainTickBehavior) behavior;
			}
			if(behavior instanceof IRandomDisplayTickBehavior) {
				this.randomDisplayTickBehavior = (IRandomDisplayTickBehavior) behavior;
			}
			if(behavior instanceof IRandomTickBehavior) {
				this.randomTickBehavior = (IRandomTickBehavior) behavior;
			}
			if(behavior instanceof IUpdateTickBehavior) {
				this.updateTickBehavior = (IUpdateTickBehavior) behavior;
			}
			if(behavior instanceof IWalkedUponBehavior) {
				this.walkedUponBehavior = (IWalkedUponBehavior) behavior;
			}
			if(behavior instanceof INeighborChangedBehavior) {
				this.neighborChangedBehavior = (INeighborChangedBehavior) behavior;
			}
		}

		return this;
	}

	/**
	 * Sets a Factory, that is used to create a TileEntity for the Block
	 * @param tileEntityFactory A Factory to create the TileEntity instance
	 * @return itself
	 */
	public DRPBlock setTileEntityFactory(Callable<TileEntity> tileEntityFactory) {
		this.tileEntityFactory = tileEntityFactory;
		return this;
	}
}
