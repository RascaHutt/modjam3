package assets.modjam3.common;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class CoinPressRecipies {
	private Map coinPressList = new HashMap();
	private Map coinPressExperience = new HashMap();
	private static final CoinPressRecipies coinPressBase = new CoinPressRecipies();
	
	public static final CoinPressRecipies press()
	{
	return coinPressBase;
	}
	public ItemStack getPressResult(int id)
	{
	return (ItemStack)coinPressList.get(Integer.valueOf(id));
	}
	
	public void addSmelting(int id, ItemStack itemStack, float experience)
	{
		coinPressList.put(Integer.valueOf(id), itemStack);
	this.coinPressExperience.put(Integer.valueOf(itemStack.itemID), Float.valueOf(experience));
	}
	public float getExperience(int par1)
	{
	return this.coinPressExperience.containsKey(Integer.valueOf(par1)) ? ((Float)this.coinPressExperience.get(Integer.valueOf(par1))).floatValue() : 0.0F;
	}
	
	private CoinPressRecipies()
	{
	addSmelting(FinancialExpansion.itemnickelIngot.itemID, new ItemStack(FinancialExpansion.itemnickelCoin, 1, 0), 0.7F);
	}
}
