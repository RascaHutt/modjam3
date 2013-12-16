package assets.modjam3.common;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		 if (packet.channel.equals("stocktrader")) 
			 stockTrade(packet,player);
             
         if (packet.channel.equals("stockviewer")) 
        	 stockViewer(packet,(EntityPlayer) player);
         
         if (packet.channel.equals("balanceupdate")) 
        	 cardBalance(packet,(EntityPlayer) player);
         
         if (packet.channel.equals("stockclient")) 
        	 stockViewerClient(packet,(EntityPlayer) player);
     
         if (packet.channel.equals("traderbalance")) 
        	 traderBalance(packet,(EntityPlayer) player);
	}

	private void stockTrade(Packet250CustomPayload packet,Player player) {
		// TODO Auto-generated method stub
DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
String price;
Boolean buy;
        int x;
        int y;
        int z;
        EntityPlayer player2 = (EntityPlayer) player;
        try {
                price = inputStream.readUTF();
                buy = inputStream.readBoolean();
                x = inputStream.readInt();
                y = inputStream.readInt();
                z = inputStream.readInt();
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }
        StockTraderTile tile = (StockTraderTile) player2.worldObj.getBlockTileEntity(x,y,z);
        tile.listTrade(tile.getStackInSlot(0),Integer.valueOf(price), buy, player2);
        tile.sellItems();
	}
	private void stockViewer(Packet250CustomPayload packet,
            EntityPlayer player) {
    // TODO Auto-generated method stub
    
DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
World world = player.worldObj;
if (world.isRemote == false)
 System.out.println("yes");
int xx;
int yy;
int zz;
int id;

String name;
StockTraderTile tile;
try {
 xx = inputStream.readInt();
 yy = inputStream.readInt();
 zz = inputStream.readInt();
 id = inputStream.readInt();
 

 

 tile = (StockTraderTile) world.getBlockTileEntity(xx,yy, zz);
 tile.completeTrade(id);

} catch (IOException e) {
 e.printStackTrace();
 return;
}

System.out.println(xx);
}
	private void stockViewerClient(Packet250CustomPayload packet,
            EntityPlayer player) {
    // TODO Auto-generated method stub
    
DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
World world = player.worldObj;
if (world.isRemote == false)
 System.out.println("yes");
int xx;
int yy;
int zz;
int id;
int itemid;
int price;
int amount;
Boolean buy;
String player2;

String name;
StockTraderTile tile;
try {
 xx = inputStream.readInt();
 yy = inputStream.readInt();
 zz = inputStream.readInt();
 id = inputStream.readInt();
 itemid = inputStream.readInt();
amount = inputStream.readInt();
price = inputStream.readInt();
buy =  inputStream.readBoolean();
player2 = inputStream.readUTF();
 

 tile = (StockTraderTile) world.getBlockTileEntity(xx,yy, zz);
 tile.listTrade(new ItemStack(Item.itemsList[itemid],amount), price, buy, player.worldObj.getPlayerEntityByName(player2));;

} catch (IOException e) {
 e.printStackTrace();
 return;
}

System.out.println(xx);
}
	private void cardBalance(Packet250CustomPayload packet,
            EntityPlayer player) {
    // TODO Auto-generated method stub
    
DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
World world = player.worldObj;

 System.out.println("yes");
int xx;
int yy;
int zz;
int id;
int balance;


StockViewerTile tile;
try {
 xx = inputStream.readInt();
 yy = inputStream.readInt();
 zz = inputStream.readInt();

 balance = inputStream.readInt();


 tile = (StockViewerTile) world.getBlockTileEntity(xx,yy, zz);
 //tile.listTrade(new ItemStack(Item.itemsList[itemid],amount), price, buy, player.worldObj.getPlayerEntityByName(player2));;
tile.getStackInSlot(0).stackTagCompound.setInteger("balance",balance);
} catch (IOException e) {
 e.printStackTrace();
 return;
}

System.out.println(xx);
}
	private void traderBalance(Packet250CustomPayload packet,
            EntityPlayer player) {
    // TODO Auto-generated method stub
    
DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
World world = player.worldObj;

 System.out.println("yes");
int xx;
int yy;
int zz;
int balance;

String name;
StockTraderTile tile;
try {
 xx = inputStream.readInt();
 yy = inputStream.readInt();
 zz = inputStream.readInt();
 balance = inputStream.readInt();
 

 

 tile = (StockTraderTile) world.getBlockTileEntity(xx,yy, zz);
 tile.balance=balance;

} catch (IOException e) {
 e.printStackTrace();
 return;
}

System.out.println(xx);
}
}
