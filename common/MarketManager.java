package assets.modjam3.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Console;
import java.io.DataOutputStream;
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
import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class MarketManager{
	 private static final String financialexp= "financialexpansion";
	 
	  public final Listing[] listings = new Listing[200];
	public MarketManager() {
		
		// TODO Auto-generated constructor stub
	}
public void listTrade(ItemStack offer,int price,Boolean buy,String player,int x,int y,int z,World world,int ID){
		int b = counter();
		listings[b] = new Listing();
		
		listings[b].items = offer;
		listings[b].price =price;
		listings[b].buy = buy;
		listings[b].username =player;
		listings[b].x=x;
		listings[b].y=y;
		listings[b].z=z;
		listings[b].world = world;
		listings[b].ID = ID;
		if (world!=null){
		if (world.isRemote==false){
	
			PacketDispatcher.sendPacketToAllPlayers(packet2(x,y,z,ID,"stockclient", price, offer.stackSize, player, buy, offer.itemID));
		}}
	}
	public void completeTrade(int id,int balance){
		if (listings[id]!=null){
		//PacketDispatcher.sendPacketToServer(packet3(listings[id].x,listings[id].y,listings[id].z,balance));
		updateCard(listings[id].x,listings[id].y,listings[id].z,balance);
		//tile.completeTrade(listings[id].ID);
		}
		listings[id] =null;
		
	}
	public void updateCard(int x,int y,int z ,int balance){
		PacketDispatcher.sendPacketToAllPlayers(packet3(x,y,z,balance));
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
	
	public Packet packet(int x,int y,int z,int id,String channel){

		

		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
		   
		       outputStream.writeInt(x);
		       outputStream.writeInt(y);
		       outputStream.writeInt(z);
		       outputStream.writeInt(id);
		       
		} catch (Exception ex) {
		        ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = channel;
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		return packet;
	}
	public Packet packet2(int x,int y,int z,int id,String channel,int price,int amount,String player2,Boolean buy,int itemid){

		

		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
		   
		       outputStream.writeInt(x);
		       outputStream.writeInt(y);
		       outputStream.writeInt(z);
		       outputStream.writeInt(id);
		       outputStream.writeInt(itemid );
		       outputStream.writeInt(amount );
		       outputStream.writeInt(price );
		       outputStream.writeBoolean(buy);
		       outputStream.writeUTF(player2);
		
		       
		} catch (Exception ex) {
		        ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = channel;
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		return packet;
	}
	  public Packet packet3(int x,int y,int z, int balance){
			Random random = new Random();
			

			ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
			DataOutputStream outputStream = new DataOutputStream(bos);
			try {
			   
			      
			       outputStream.writeInt(x);
			       outputStream.writeInt(y);
			       outputStream.writeInt(z);
			       outputStream.writeInt(balance);
			} catch (Exception ex) {
			        ex.printStackTrace();
			}

			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = "traderbalance";
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			return packet;
		}
	  
}
