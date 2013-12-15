package assets.modjam3.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;

public class BlockBankATM extends BlockContainer {
	public BlockBankATM(int id){
		super(id, Material.rock);
		setUnlocalizedName("blockbankatm");
		setCreativeTab(CreativeTabs.tabBlock);
		setHardness(3);
	}
	 public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	    {
		 if (player.getCurrentEquippedItem()==null){
			 player.addChatMessage("You need a bank card to acsess the ATM. Talk to a banker to obtain one.");
			 return false;
		 }
		 Item equipped = player.getCurrentEquippedItem() != null ? player.getCurrentEquippedItem().getItem() : null;
		 System.out.println(player.getHeldItem().itemID);
		 BankATMTile tEntity = (BankATMTile)par1World.getBlockTileEntity(x,y,z);
	         if(tEntity != null&&equipped instanceof ItemBankCard) {
	                 player.openGui(FinancialExpansion.instance, 3, par1World, x, y, z);
	          
	 //        }
	         return true;
	 }
	         return false;
	    }
	@Override
	public TileEntity createNewTileEntity(World world) {
		// TODO Auto-generated method stub
		return new BankATMTile();
	}
}
