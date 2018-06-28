package net.dark_roleplay.library.experimental.blueprintsv2;

import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

/**
 * Created: 17.06.2018
 * Last edit: 17.06.2018
 * Last edit by: JTK222
 * Version added: 0.1.0
 * State: heavy unstable, don't use
 */
@Deprecated
public class BlueprintUtil2 {


	/**
	 * Transforms a BlockPos corresponding to a rotation and mirror value
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @param mirror
	 *            The value of mirroring
	 * @param rotation
	 *            The value to rotate the blockstate
	 * @return a Tranformed BlockPos
	 */
	public static BlockPos transformPos(int x, int y, int z, Mirror mirror, Rotation rotation) {
		boolean flag = true;

		switch (mirror) {
		case LEFT_RIGHT:
			z = -z;
			break;
		case FRONT_BACK:
			x = -x;
			break;
		default:
			flag = false;
		}

		switch (rotation) {
		case COUNTERCLOCKWISE_90:
			return new BlockPos(z, y, -x);
		case CLOCKWISE_90:
			return new BlockPos(-z, y, x);
		case CLOCKWISE_180:
			return new BlockPos(-x, y, -z);
		default:
			return flag ? new BlockPos(x, y, z) : new BlockPos(x, y, z);
		}
	}
}
