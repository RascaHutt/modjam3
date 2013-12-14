package assets.modjam3.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class StockViewerContainer extends Container{
protected StockViewerTile tile_entity;
	
	public StockViewerContainer(StockViewerTile tile_entity, InventoryPlayer player_inventory){
		this.tile_entity = tile_entity;
		int o = 0;
		
         for(int q = 0; q <3; q++){
         for(int p = 0; p <9; p++){
        
        
         addSlotToContainer(new Slot(tile_entity, o, 8+p*18, 18+q*18));
        
             o++;
            
             }}
		
		 bindPlayerInventory(player_inventory);
	}
	private void bindPlayerInventory(InventoryPlayer player_inventory) {
		// TODO Auto-generated method stub
		 for(int i = 0; i < 3; i++){
             for(int j = 0; j < 9; j++){
                     addSlotToContainer(new Slot(player_inventory, j+i * 9 + 9, 8 + j * 18, 84 + i * 18));
                    
             }
     }

     for(int i = 0; i < 9; i++){
             addSlotToContainer(new Slot(player_inventory, i, 8 + i * 18, 142));
             
     }
	}
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		 return tile_entity.isUseableByPlayer(entityplayer);
		
	}
	  

}