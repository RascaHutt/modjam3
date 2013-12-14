package assets.modjam3.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public class BlockNickelBlock extends Block{
	
	public BlockNickelBlock(int id){
		super(id, Material.rock);
		setUnlocalizedName("blocknickelblock");
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg){
		this.blockIcon = reg.registerIcon(Reference.MOD_TEXTUREPATH + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
	}

}