package assets.modjam3.common;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class StockViewerTile extends TileEntity implements IInventory{
	private ItemStack[] inventory;
	public int pressTime = 0;
	int abc = 0;
	public Boolean bcd=false;
	public StockViewerTile(){
		 this.inventory = new ItemStack[29];
	}
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		   return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		// TODO Auto-generated method stub
		  return this.inventory[i];
	}

	/*@Override
	public ItemStack decrStackSize(int slotIndex, int amount) {
		// TODO Auto-generated method stub
		  ItemStack stack = getStackInSlot(slotIndex);
          
          
          if(stack != null){
         
                  if(stack.stackSize <= amount){
                          setInventorySlotContents(slotIndex, null);
                  }
                  else{
                          stack = stack.splitStack(amount);
                          if(stack.stackSize == 0){
                                  setInventorySlotContents(slotIndex, null);
                          }
                  }
          }
          return stack;
	}*/

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		// TODO Auto-generated method stub
        ItemStack stack = getStackInSlot(slotIndex);
        
        
        if(stack != null){
                setInventorySlotContents(slotIndex, null);
        }
       
       
        return stack;
	}

	/*@Override
	public void setInventorySlotContents(int slot, ItemStack stack){ 
		   this.inventory[slot] = stack;
        
        if(stack != null && stack.stackSize > getInventoryStackLimit()){
                stack.stackSize = getInventoryStackLimit();
        }
		
	}*/

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "Stock Viewer";
	}

	@Override
	public boolean isInvNameLocalized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		 return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this && entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return false;
	}
	   @Override
	    public void readFromNBT(NBTTagCompound tagCompound){
	            super.readFromNBT(tagCompound);
	           
	            NBTTagList tagList = tagCompound.getTagList("Inventory");
	           
	            for(int i = 0; i < tagList.tagCount(); i++){
	                    NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
	                   
	                    byte slot = tag.getByte("Slot");
	                   
	                    if(slot >= 0 && slot < inventory.length){
	                            inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
	                    }
	            }
	    }
	    @Override
	    public void writeToNBT(NBTTagCompound tagCompound){
	            super.writeToNBT(tagCompound);
	           
	            NBTTagList itemList = new NBTTagList();
	           
	            for(int i = 0; i < inventory.length; i++){
	                    ItemStack stack = inventory[i];
	                   
	                    if(stack != null){
	                            NBTTagCompound tag = new NBTTagCompound();
	                           
	                            tag.setByte("Slot", (byte) i);
	                            stack.writeToNBT(tag);
	                            itemList.appendTag(tag);
	                    }
	            }
	           
	            tagCompound.setTag("Inventory", itemList);
	    }
	  public void updateEntity()
      {
	int i=0;
		if (FinancialExpansion.instance.market!=null){
		for (Listing list:FinancialExpansion.instance.market.listings){
			
		if (FinancialExpansion.instance.market.listings[i]!=null){
			inventory[i+2]=FinancialExpansion.instance.market.listings[i].items;
		inventory[i+2].setItemDamage(FinancialExpansion.instance.market.listings[i].price);
		inventory[i+2].stackTagCompound =(new NBTTagCompound( ) );
		inventory[i+2].stackTagCompound.setString("username", FinancialExpansion.instance.market.listings[i].username);
		//inventory[1]=FinancialExpansion.instance.market.displaySatck(1);
		}
		i++;
		}}
      }
	  public void unList(int slot){
		  FinancialExpansion.instance.market.completeTrade(slot-2);
	  }
@Override
public ItemStack decrStackSize(int slotIndex, int amount) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stu
	if (slotIndex ==1||slotIndex ==0||inventory[0].getItemDamage()>=inventory[slotIndex].getItemDamage()){
	  ItemStack stack = getStackInSlot(slotIndex);
    
    
    if(stack != null){
   
            if(stack.stackSize <= amount){
                    setInventorySlotContents(slotIndex, null);
            }
            else{
                    stack = stack.splitStack(amount);
                    if(stack.stackSize == 0){
                            setInventorySlotContents(slotIndex, null);
                    }
            }
    }

    return stack;
	}
	return null;
}
@Override
public void setInventorySlotContents(int slot, ItemStack stack){ 
	  
		
	if (slot==0||bcd==true){
		bcd=false;
	this.inventory[slot] = stack;
    
    if(stack != null && stack.stackSize > getInventoryStackLimit()){
            stack.stackSize = getInventoryStackLimit();
    }
	   }
}
}
