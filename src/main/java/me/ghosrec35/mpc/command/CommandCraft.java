package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CommandCraft extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "craft";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "craft.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && params.length > 0)
        {
            EntityPlayer player = (EntityPlayer)getCommandSenderAsPlayer(sender);
            
        }
    }
}