package assets.modjam3.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCoinPress extends BlockContainer {
	
	public BlockCoinPress(int id){
		super(id, Material.rock);
		setUnlocalizedName("blockcoinpress");
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg){
		this.blockIcon = reg.registerIcon(Reference.MOD_TEXTUREPATH + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}
	@Override
	public boolean onBlockActivated(World world, int par1, int par2, int par3, EntityPlayer player, int par4, float par5, float par6, float par9){
		if(player.isSneaking()){
			return false;
	}
		if(world.isRemote){
			return true;
		}else{
			TileCoinPress tilecoinpress = (TileCoinPress)world.getBlockTileEntity(par1, par2, par3);
			player.openGui(FinancialExpansion.instance, 1, world, par2, par3, par4);
			return true;
		}

}
	public void onBlockAdded(World world, int par1, int par2, int par3){
		super.onBlockAdded(world, par1, par2, par3);
		createNewTileEntity(world);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world){
		return new TileCoinPress();
	}

	public static void updateFurnaceBlockState(boolean b, World worldObj,
			int xCoord, int yCoord, int zCoord) {
		// TODO Auto-generated method stub
		
	}
}


