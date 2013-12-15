package assets.modjam3.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class BankerTradeHandler implements IVillageTradeHandler {

	@Override
	public void manipulateTradesForVillager(EntityVillager villager,
			MerchantRecipeList recipeList, Random random) {
	ItemStack stack= new ItemStack(FinancialExpansion.itembankCard,1,0);
	stack.setItemDamage(100);
	//stack.stackTagCompound =(new NBTTagCompound( ) );
		recipeList.add(new MerchantRecipe(new ItemStack(FinancialExpansion.itemnickelCoin,8,0),stack));
	}

}
