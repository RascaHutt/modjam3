package assets.modjam3.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		if(!world.blockExists(x, y, z))
			return null;
		
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		switch(ID){
		case 1:
			if (!(tile instanceof TileCoinPress)){
				return null;
			}
			return new ContainerCoinPress(player.inventory, (TileCoinPress)tile);
			default:
				return null;
		}
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		if(!world.blockExists(x, y, z))
			return null;
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		switch(ID){
			case 1:
				if (!(tile instanceof TileCoinPress))
					return null;
				return new GuiCoinPress(player.inventory, (TileCoinPress)tile);
				default:
					return null;
	}
	}
}
