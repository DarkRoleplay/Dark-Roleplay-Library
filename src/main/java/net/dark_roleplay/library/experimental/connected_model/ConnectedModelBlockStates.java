package net.dark_roleplay.library.experimental.connected_model;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties.PropertyAdapter;

public class ConnectedModelBlockStates {

	public static final PropertyAdapter<Boolean> NORTH_LEFT = new PropertyAdapter<Boolean>(PropertyBool.create("north_left"));
	public static final PropertyAdapter<Boolean> NORTH_CENTER = new PropertyAdapter<Boolean>(PropertyBool.create("north_center"));
	public static final PropertyAdapter<Boolean> NORTH_RIGHT = new PropertyAdapter<Boolean>(PropertyBool.create("north_right"));
	public static final PropertyAdapter<Boolean> SOUTH_LEFT = new PropertyAdapter<Boolean>(PropertyBool.create("south_left"));
	public static final PropertyAdapter<Boolean> SOUTH_CENTER = new PropertyAdapter<Boolean>(PropertyBool.create("south_center"));
	public static final PropertyAdapter<Boolean> SOUTH_RIGHT = new PropertyAdapter<Boolean>(PropertyBool.create("south_right"));
	public static final PropertyAdapter<Boolean> CENTER_LEFT = new PropertyAdapter<Boolean>(PropertyBool.create("center_left"));
	public static final PropertyAdapter<Boolean> CENTER_RIGHT = new PropertyAdapter<Boolean>(PropertyBool.create("center_right"));
	public static final PropertyAdapter<Boolean> UP_LEFT = new PropertyAdapter<Boolean>(PropertyBool.create("up_left"));
	public static final PropertyAdapter<Boolean> UP_CENTER = new PropertyAdapter<Boolean>(PropertyBool.create("up_center"));
	public static final PropertyAdapter<Boolean> UP_RIGHT = new PropertyAdapter<Boolean>(PropertyBool.create("up_right"));
	public static final PropertyAdapter<Boolean> DOWN_LEFT = new PropertyAdapter<Boolean>(PropertyBool.create("down_left"));
	public static final PropertyAdapter<Boolean> DOWN_CENTER = new PropertyAdapter<Boolean>(PropertyBool.create("down_center"));
	public static final PropertyAdapter<Boolean> DOWN_RIGHT = new PropertyAdapter<Boolean>(PropertyBool.create("down_right"));

	public static final PropertyEnum FACING_HORIZONTAL = PropertyEnum.create("facing_horizontal", EnumFacing.class, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH);
	public static final PropertyEnum FACING = PropertyEnum.create("facing", EnumFacing.class);
	public static final PropertyEnum AXIS_HORIZONTAL = PropertyEnum.create("axis_horizontal", EnumFacing.Axis.class, EnumFacing.Axis.X, EnumFacing.Axis.Z);
	public static final PropertyEnum AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);

	public static final IUnlistedProperty[] HORIZONTAL = new IUnlistedProperty[] {NORTH_LEFT, NORTH_CENTER, NORTH_RIGHT, SOUTH_LEFT, SOUTH_CENTER, SOUTH_RIGHT, CENTER_LEFT, CENTER_RIGHT};
	public static final IUnlistedProperty[] VERTICAL = new IUnlistedProperty[] {UP_LEFT, UP_CENTER, UP_RIGHT, DOWN_LEFT, DOWN_CENTER, DOWN_RIGHT, CENTER_LEFT, CENTER_RIGHT};
	public static final IUnlistedProperty[] Z_AXIS = new IUnlistedProperty[] {NORTH_CENTER, SOUTH_CENTER};
	public static final IUnlistedProperty[] X_AXIS = new IUnlistedProperty[] {CENTER_LEFT, CENTER_RIGHT};
	public static final IUnlistedProperty[] Y_AXIS = new IUnlistedProperty[] {UP_CENTER, DOWN_CENTER};

}
