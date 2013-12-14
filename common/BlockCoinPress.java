package assets.modjam3.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockCoinPress extends BlockContainer{

	public BlockCoinPress(int id){
		super(id, Material.rock);
		setUnlocalizedName("blockcoinpress");
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3);
	}
	@SideOnly(Side.CLIENT)
	private Icon side, bottom, top, front, frontoff;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg){
		this.side = reg.registerIcon(Reference.MOD_TEXTUREPATH + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_side");
		this.bottom = reg.registerIcon(Reference.MOD_TEXTUREPATH + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_top");
		this.top = reg.registerIcon(Reference.MOD_TEXTUREPATH + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_top");
		this.front = reg.registerIcon(Reference.MOD_TEXTUREPATH + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1) + "_front");
	}
	
	@SideOnly(Side.CLIENT)
    @Override
    public Icon getIcon(int side, int metadata){
		if (side == 1) return this.top;
		else if (side == 0) return this.top;
		else if (metadata == 2 && side == 2) return this.front;
	      else if (metadata == 3 && side == 5) return this.front;
	      else if (metadata == 0 && side == 3) return this.front;	
	      else return this.side;
	
		
	}
	
	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int whichDirectionFacing = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        par1World.setBlockMetadataWithNotify(x, y, z, whichDirectionFacing, 2);
    }
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new CoinPressTile();
	}
    public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
    	CoinPressTile tEntity = (CoinPressTile)par1World.getBlockTileEntity(x,y,z);
         if(tEntity != null){
                 player.openGui(FinancialExpansion.instance, 0, par1World, x, y, z);
 //        }
         return true;
 }
         return false;
    }
    
}
