package assets.modjam3.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class MarketManager extends WorldSavedData{
	 private static final String financialexp= "financialexpansion";
	 
	  public final Listing[] listings = new Listing[200];
	public MarketManager() {
		super(financialexp);
		// TODO Auto-generated constructor stub
	}
	public void listTrade(ItemStack offer,int price,Boolean buy,EntityPlayer player){
		int b = counter();
		listings[b] = new Listing();
		
		listings[b].items = offer;
		listings[b].price =price;
		listings[b].buy = buy;
		listings[b].username =player.username;
		
	}
	public int counter(){
		int i=0;
		for (Listing list:listings){
			if (list==null)
					return i;
			i++;
		
		}
		return 200;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		int i=0;
		for (Listing list : listings){
			list.readFromNBT(nbttagcompound.getCompoundTag(String.valueOf(i)));
			i++;
		}
	}
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		// TODO Auto-generated method stub
		int i=0;
		for (Listing list : listings){
			  nbttagcompound.setCompoundTag(String.valueOf(i), list.writeToNBT(new NBTTagCompound()));
			i++;
		}
	}
}
