package net.epoxide.mpc.command;

import net.epoxide.mpc.creativetab.CreativeTabCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;

public class CommandDelItem extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "delitem";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.delitem.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender))
        {
            if(params.length == 2)
            {
                CreativeTabCommand tab = CreativeTabCommand.tabMap.get(params[0]);
                ItemStack stack;
                try
                {
                    stack = new ItemStack(Item.getItemById(Integer.parseInt(params[1])));
                }
                catch(Exception e)
                {
                    stack = new ItemStack((Item)Item.itemRegistry.getObject(params[1].toLowerCase()));
                }
                tab.removeItem(stack);
                sender.addChatMessage(new ChatComponentTranslation("commands.delitem.success", stack.getDisplayName(), params[0]));
            }
        }
    }

    @Override
	public boolean hasProperParams(String[] params) 
    {
		// TODO Do basic param checks
		return true;
	}
}
