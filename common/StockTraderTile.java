package assets.modjam3.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class StockTraderTile extends TileEntity implements IInventory{
	private ItemStack[] inventory;
	 public final Listing[] listings = new Listing[200];
	public int pressTime = 0;
	int abc = 0;
	public StockTraderTile(){
		 this.inventory = new ItemStack[1];
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
		public void listTrade(ItemStack offer,int price,Boolean buy,EntityPlayer player){
			int b = counter();
			listings[b] = new Listing();
			
			listings[b].items = offer;
			listings[b].price =price;
			listings[b].buy = buy;
			listings[b].username =player.username;
			FinancialExpansion.instance.market.listTrade(offer, price, buy, player.username);
			
		}
		public void completeTrade(int id){
			listings[id] =null;
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
			return "Stock Trader";
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
	            NBTTagList tagLister = tagCompound.getTagList("ListingsOffers");
	            NBTTagList tagListed = tagCompound.getTagList("ListingsDetails");
	            for(int i = 0; i < tagLister.tagCount(); i++){
                    NBTTagCompound tag = (NBTTagCompound) tagLister.tagAt(i);
                    NBTTagCompound tager = (NBTTagCompound) tagListed.tagAt(i);
                   
                    byte slot = tag.getByte("Slot");
                   
                    if(slot >= 0 && slot < inventory.length){
                           listings[i].items = ItemStack.loadItemStackFromNBT(tag);
                    }
                    listings[i].price = tager.getInteger("price");
                    listings[i].buy = tager.getBoolean("buy");
                    listings[i].username = tager.getString("username");
                    FinancialExpansion.instance.market.listTrade(listings[i].items, listings[i].price, listings[i].buy, listings[i].username);
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
	            
	            NBTTagList tagLister = new NBTTagList();
	            NBTTagList tagListed = new NBTTagList();
	            for(int i = 0; i < listings.length-1; i++){
                    NBTTagCompound tag = new NBTTagCompound();
                    NBTTagCompound tager = new NBTTagCompound();
                    ItemStack stack = listings[i].items;
                    tag.setByte("Slot", (byte) i);
                   
                    if(stack != null){
                    	
                         
                         tag.setByte("Slot", (byte) i);
                         stack.writeToNBT(tag);
                         tagLister.appendTag(tag);
                    }
                     tager.setInteger("price",listings[i].price);
                    tager.setBoolean("buy", listings[i].buy);
                    tager.setString("username", listings[i].username);
                    
	            }
	            tagCompound.setTag("ListingsOffers", tagLister);
	            tagCompound.setTag("ListingsDetails", tagListed);
	    }
	   
	
		  public void updateEntity()
	      {
			
	      }
		  public void sellItems(){
			  inventory[0] = null;
		  }

		  

		  
	}
