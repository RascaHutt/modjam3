package assets.modjam3.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockStockViewer extends BlockContainer{

	protected BlockStockViewer(int par1) {
		super(par1, Material.rock);
		setUnlocalizedName("blockstockviewer");
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3);
	}
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new StockViewerTile();
	}
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	StockViewerTile tEntity = (StockViewerTile)par1World.getBlockTileEntity(x,y,z);
         if(tEntity != null){
                 //player.displayGUIFurnace(tEntity);
                 player.openGui(FinancialExpansion.instance, 1, par1World, x, y, z);
                 FinancialExpansion.instance.market.listTrade(new ItemStack(Block.cobblestone), 100, 1, player);
 //        }
         return true;
 }
         return false;
    }
}
