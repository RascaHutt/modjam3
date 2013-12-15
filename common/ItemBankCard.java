package assets.modjam3.common;



import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBankCard extends Item {

	public ItemBankCard(int id){
		super(id);
		setUnlocalizedName("itembankcard");
		setCreativeTab(CreativeTabs.tabBlock);
		this.setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg){
		this.itemIcon = reg.registerIcon(Reference.MOD_TEXTUREPATH + ":" + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
		
	}
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
	list.add(String.valueOf(stack.getItemDamage()));
	
	}
}
