package assets.modjam3.common;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler{

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		 if (packet.channel.equals("stocktrader")) {
             stockTrade(packet,player);
     }
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
        FinancialExpansion.instance.market.listTrade(tile.getStackInSlot(0),Integer.valueOf(price), buy, player2);
        
	}

}
