package me.ghosrec35.mpc.command;

import net.minecraft.command.ICommandSender;

public class CommandWarp extends CommandMPCBase
{
    @Override
    public String getCommandName()
    {
        return "tp";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "tp.commands.usage";
    }

    @Override
    public void processCommand(ICommandSender var1, String[] var2)
    {        
    }
}
