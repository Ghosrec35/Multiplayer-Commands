package net.epoxide.mpc.command;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandAttribute extends CommandMPCBase
{
    private static Map<String, IAttribute> attributeMap = new HashMap<String, IAttribute>();
    
    static
    {
        attributeMap.put("damage", SharedMonsterAttributes.attackDamage);
        attributeMap.put("range", SharedMonsterAttributes.followRange);
        attributeMap.put("knockback", SharedMonsterAttributes.knockbackResistance);
        attributeMap.put("health", SharedMonsterAttributes.maxHealth);
        attributeMap.put("speed", SharedMonsterAttributes.movementSpeed);
    }
    
    @Override
    public String getCommandName()
    {
        return "attribute";
    }

    @Override
    public String getCommandUsage(ICommandSender var1)
    {
        return "commands.attribute.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] params)
    {
        if(canCommandSenderUseCommand(sender) && params.length == 3)
        {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            IAttribute attribute = attributeMap.get(params[0]);
            if(attribute != null)
            {
                player.getAttributeMap().registerAttribute(attribute).setBaseValue(Double.parseDouble(params[1]));
                sender.addChatMessage(new ChatComponentTranslation("commands.attribute.success", translate(attribute.getAttributeUnlocalizedName()), params[1], player.getDisplayName()));
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
