package assets.modjam3.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class CoinPressTile extends TileEntity implements IInventory{
	private ItemStack[] inventory;
	public int pressTime = 0;
	int abc = 0;
	public CoinPressTile(){
		 this.inventory = new ItemStack[2];
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

	@Override
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
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex) {
		// TODO Auto-generated method stub
        ItemStack stack = getStackInSlot(slotIndex);
        
        
        if(stack != null){
                setInventorySlotContents(slotIndex, null);
        }
       
       
        return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack){ 
		   this.inventory[slot] = stack;
        
        if(stack != null && stack.stackSize > getInventoryStackLimit()){
                stack.stackSize = getInventoryStackLimit();
        }
		
	}

	@Override
	public String getInvName() {
		// TODO Auto-generated method stub
		return "Coin Press";
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
    public void pressItem()
    {
             if (this.canPress())
             {
                     ItemStack var1 = CoinPressRecipies.press().getPressResult(this.inventory[0].getItem().itemID);
                     if (this.inventory[1] == null)
                     {
                             this.inventory[1] = var1.copy();
                           
                     }
                     else if (this.inventory[1].itemID == var1.itemID)
                     {
                    	 System.out.println("2");
                             ++this.inventory[1].stackSize;
                     }
                     --this.inventory[0].stackSize;
                     if (this.inventory[0].stackSize == 0)
                     {
                             Item var2 = this.inventory[0].getItem().getContainerItem();
                             this.inventory[0] = var2 == null ? null : new ItemStack(var2);
                     }
             }
    }
	private boolean canPress() {
		// TODO Auto-generated method stub
		  if (inventory[0] == null)
          {
			  
                  return false;
                  
          }

          ItemStack itemstack = CoinPressRecipies.press().getPressResult(inventory[0].getItem().itemID);

          if (itemstack == null)
          {
        	 
                  return false;
          }

          if (inventory[1] == null)
          {
        	 
                  return true;
          }

          if (!inventory[1].isItemEqual(itemstack))
          {
        	 
                  return false;
          }

          if (inventory[1].stackSize < getInventoryStackLimit() && inventory[1].stackSize < inventory[1].getMaxStackSize())
          {
        	  
                  return true;
          }

          return inventory[1].stackSize < itemstack.getMaxStackSize();
	}
	  public void updateEntity()
      {
		if (abc ==100){
			
			if (canPress()){
				pressItem();
				
			}
			abc=0;
		}else{
			
			abc++;
		}
      }
	  
}
