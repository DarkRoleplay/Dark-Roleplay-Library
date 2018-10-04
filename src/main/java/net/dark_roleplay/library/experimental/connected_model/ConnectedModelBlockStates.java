package net.dark_roleplay.library.experimental.connected_model;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;

public class ConnectedModelBlockStates {

	public static final PropertyBool NORTH_LEFT = PropertyBool.create("north_left");
	public static final PropertyBool NORTH_CENTER = PropertyBool.create("north_center");
	public static final PropertyBool NORTH_RIGHT = PropertyBool.create("north_right");

	public static final PropertyBool SOUTH_LEFT = PropertyBool.create("south_left");
	public static final PropertyBool SOUTH_CENTER = PropertyBool.create("south_center");
	public static final PropertyBool SOUTH_RIGHT = PropertyBool.create("south_right");

	public static final PropertyBool CENTER_LEFT = PropertyBool.create("center_left");
	public static final PropertyBool CENTER_RIGHT = PropertyBool.create("center_right");

	public static final PropertyBool UP_LEFT = PropertyBool.create("up_left");
	public static final PropertyBool UP_CENTER = PropertyBool.create("up_center");
	public static final PropertyBool UP_RIGHT = PropertyBool.create("up_right");

	public static final PropertyBool DOWN_LEFT = PropertyBool.create("down_left");
	public static final PropertyBool DOWN_CENTER = PropertyBool.create("down_center");
	public static final PropertyBool DOWN_RIGHT = PropertyBool.create("down_right");

	public static final PropertyEnum FACING_HORIZONTAL = PropertyEnum.create("facing_horizontal", EnumFacing.class, EnumFacing.EAST, EnumFacing.WEST, EnumFacing.NORTH, EnumFacing.SOUTH);
	public static final PropertyEnum FACING = PropertyEnum.create("facing", EnumFacing.class);
	public static final PropertyEnum AXIS_HORIZONTAL = PropertyEnum.create("axis_horizontal", EnumFacing.Axis.class, EnumFacing.Axis.X, EnumFacing.Axis.Z);
	public static final PropertyEnum AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class);

	public static final IProperty[] HORIZONTAL = new IProperty[] {NORTH_LEFT, NORTH_CENTER, NORTH_RIGHT, SOUTH_LEFT, SOUTH_CENTER, SOUTH_RIGHT, CENTER_LEFT, CENTER_RIGHT};
	public static final IProperty[] VERTICAL = new IProperty[] {UP_LEFT, UP_CENTER, UP_RIGHT, DOWN_LEFT, DOWN_CENTER, DOWN_RIGHT, CENTER_LEFT, CENTER_RIGHT};
	public static final IProperty[] Z_AXIS = new IProperty[] {NORTH_CENTER, SOUTH_CENTER};
	public static final IProperty[] X_AXIS = new IProperty[] {CENTER_LEFT, CENTER_RIGHT};
	public static final IProperty[] Y_AXIS = new IProperty[] {UP_CENTER, DOWN_CENTER};

}
