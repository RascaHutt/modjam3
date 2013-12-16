package assets.modjam3.common;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatMessageComponent;

public class CardCommand implements ICommand {

	  private List aliases;
	  public CardCommand()
	  {
	    this.aliases = new ArrayList();
	    this.aliases.add("setcard");
	    //this.aliases.add("sam");
	  }

	  @Override
	  public String getCommandName()
	  {
	    return "card";
	  }

	  @Override
	  public String getCommandUsage(ICommandSender icommandsender)
	  {
	    return "card <amount>";
	  }

	  @Override
	  public List getCommandAliases()
	  {
	    return this.aliases;
	  }

	  @Override
	  public void processCommand(ICommandSender icommandsender, String[] astring)
	  {
	    if(astring.length == 0)
	    {
	    	ChatMessageComponent chat = new ChatMessageComponent();
	    	chat.addText("Invalid arguments");
	      icommandsender.sendChatToPlayer(chat);
	      return;
	    }
		ChatMessageComponent chat = new ChatMessageComponent();
		if(icommandsender.getEntityWorld().getPlayerEntityByName(icommandsender.getCommandSenderName()).getCurrentEquippedItem()!=null){
		ItemStack stack = icommandsender.getEntityWorld().getPlayerEntityByName(icommandsender.getCommandSenderName()).getCurrentEquippedItem();
		if (stack.getItem() instanceof ItemBankCard){
			if (Integer.valueOf(astring[0])>=0){
				if (stack.stackTagCompound==null){
					stack.stackTagCompound =(new NBTTagCompound( ) );
				}
		stack.stackTagCompound.setInteger("balance", Integer.valueOf(astring[0]));
		icommandsender.getEntityWorld().getPlayerEntityByName(icommandsender.getCommandSenderName()).getCurrentEquippedItem();
    	chat.addText("Balance set to" + astring[0]);
	    icommandsender.sendChatToPlayer(chat);
			}
	  }}
	  }

	  @Override
	  public boolean canCommandSenderUseCommand(ICommandSender icommandsender)
	  {
	    return true;
	  }

	  @Override
	  public List addTabCompletionOptions(ICommandSender icommandsender,
	      String[] astring)
	  {
	    return null;
	  }

	  @Override
	  public boolean isUsernameIndex(String[] astring, int i)
	  {
	    return false;
	  }

	  @Override
	  public int compareTo(Object o)
	  {
	    return 0;
	  }
	}