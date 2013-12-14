package assets.modjam3.common;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCoinPress extends BlockContainer{

	public BlockCoinPress(int id){
		super(id, Material.rock);
		setUnlocalizedName("blockcoinpress");
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new CoinPressTile();
	}
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	CoinPressTile tEntity = (CoinPressTile)par1World.getBlockTileEntity(x,y,z);
         if(tEntity != null){
                 //player.displayGUIFurnace(tEntity);
                 player.openGui(FinancialExpansion.instance, 0, par1World, x, y, z);
 //        }
         return true;
 }
         return false;
    }
}
