package net.epoxide.mpc.util;

import java.util.UUID;

import com.google.common.collect.Multimap;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class StackUtils {

	/**
	 * General UUID used for all items in the game. This is used for all the stack uuids. 
	 */
	private static final UUID toolUUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
	
	/**
	 * Prepares an ItemStack with a tag compound if one is not already
	 * on the tag. 
	 * @param stack: The ItemStack being used.
	 */
    public static ItemStack prepareStackTag(ItemStack stack) {

        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        
        return stack;
    }
    
	/**
	 * Alters the max health of the ItemStacks holder.
	 * @param stack: ItemStack being used. 
	 * @param amount: The amount of health being set.
	 * @param type: The operation type for the modifier.
	 */
	public static ItemStack setHealthAttribute(ItemStack stack, double amount, int type) {
		
		setAttributeForStack(stack, SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), "Tool modifier", amount, type);
		return stack;
	}
	
	/**
	 * Retrieves the max health attribute value for the ItemStack.
	 * @param stack: ItemStack being used. 
	 */
	public static double getHealthAttribute(ItemStack stack) {
		
		return getAttributeForStack(stack, SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName());
	}
	
	/**
	 * Alters the knock back resistance of the ItemStacks holder.
	 * @param stack: ItemStack being used. 
	 * @param amount: The amount of speed being set.
	 * @param type: The operation type for the modifier.
	 */
	public static ItemStack setKnockbackResistanceAttribute(ItemStack stack, double amount, int type) {
		
		setAttributeForStack(stack, SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), "Tool modifier", amount, type);
		return stack;
	}
	
	/**
	 * Retrieves the knock back attribute value for the ItemStack.
	 * @param stack: ItemStack being used. 
	 */
	public static double getKnockbackResistanceAttribute(ItemStack stack) {
		
		return getAttributeForStack(stack, SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName());
	}
	
	/**
	 * Alters the follow range of the ItemStacks holder.
	 * @param stack: ItemStack being used. 
	 * @param amount: The amount of speed being set.
	 * @param type: The operation type for the modifier.
	 */
	public static ItemStack setFollowAttribute(ItemStack stack, double amount, int type) {
		
		setAttributeForStack(stack, SharedMonsterAttributes.followRange.getAttributeUnlocalizedName(), "Tool modifier", amount, type);
		return stack;
	}
	
	/**
	 * Retrieves the follow range attribute value for the ItemStack.
	 * @param stack: ItemStack being used. 
	 */
	public static double getFollowAttribute(ItemStack stack) {
		
		return getAttributeForStack(stack, SharedMonsterAttributes.followRange.getAttributeUnlocalizedName());
	}
	
	/**
	 * Alters the speed of the ItemStacks holder.
	 * @param stack: ItemStack being used. 
	 * @param amount: The amount of speed being set.
	 * @param type: The operation type for the modifier.
	 */
	public static ItemStack setSpeedAttribute(ItemStack stack, double amount, int type) {
		
		setAttributeForStack(stack, SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), "Tool modifier", amount, type);
		return stack;
	}
	
	/**
	 * Retrieves the speed attribute value for the ItemStack.
	 * @param stack: ItemStack being used. 
	 */
	public static double getSpeedAttribute(ItemStack stack) {
		
		return getAttributeForStack(stack, SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName());
	}
	
	/**
	 * Alters the attack damage of the ItemStacks holder.
	 * @param stack: ItemStack being used. 
	 * @param amount: The amount of damage being set.
	 * @param type: The operation type for the modifier.
	 */
	public static ItemStack setDamageAttribute(ItemStack stack, double amount, int type) {
		
		setAttributeForStack(stack, SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), "Weapon modifier", amount, type);
		return stack;
	}
	
	/**
	 * Retrieves the damage attribute value for the ItemStack.
	 * @param stack: ItemStack being used. 
	 */
	public static double getDamageAttribute(ItemStack stack) {
		
		return getAttributeForStack(stack, SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName());
	}
	
	/**
	 * Sets an attribute on an ItemStack, this method is used by the other helper methods
	 * that provide certain elements such as the atb name. This method is sensitive to 
	 * existing lists however it does not check to make sure that a stack has the ability
	 * to work with nbt. This method will drop the damamge value of an item down to 1 if 
	 * it the damage modifier was not applied beforehand. 
	 * @param stack: ItemStack to set values to.
	 * @param atbName: The attribute name, this should be unlocalized.
	 * @param name: The name of the attribute. 
	 * @param amount: The amount that this is being set to. 
	 * @param operation: 0-base 1-additive 2-multiplicative http://minecraft.gamepedia.com/Attribute
	 */
	public static ItemStack setAttributeForStack(ItemStack stack, String atbName, String name, double amount, int operation) {
		
		NBTTagList list;
		
		if (stack.stackTagCompound.hasKey("AttributeModifiers")) 
			list = stack.stackTagCompound.getTagList("AttributeModifiers", 10);

		else 
			list = new NBTTagList();
		
		NBTTagCompound tag = new NBTTagCompound();	
		tag.setString("AttributeName", atbName);
		tag.setString("Name", name);
		tag.setDouble("Amount", amount);
		tag.setInteger("Operation", operation);
		tag.setLong("UUIDMost", toolUUID.getMostSignificantBits());
		tag.setLong("UUIDLeast", toolUUID.getLeastSignificantBits());	
		list.appendTag(tag);
		stack.stackTagCompound.setTag("AttributeModifiers", list);
		return stack;
	}
	
	/**
	 * Retrieves an attribute value from an ItemStack. This method does not check
	 * to make sure the ItemStack has valid tags, this should be done before hand.
	 * This method is used by the other helper methods to retrieve certain values.
	 * @param stack: The ItemStack being used to retrieve info from.
	 * @param atbName: The name of the attribute. 
	 */
	public static double getAttributeForStack(ItemStack stack, String atbName) {
		
		NBTTagList list = stack.stackTagCompound.getTagList("AttributeModifiers", 10);
		
		for (int i = 0; i < list.tagCount(); i++) {
			
			NBTTagCompound tag = list.getCompoundTagAt(i);
			
			if (tag.hasKey("AttributeName") && tag.getString("AttributeName").equals(atbName))
				return tag.getDouble("Amount");
		}
		
		System.out.println("There was an error retrieving the " + atbName + " Attribute from the " + stack.getDisplayName() + " 0.00 was returned");
		return 0.00;
	}

	 public static double getItemWeaponDamage(ItemStack stack) {
		 
		 Multimap multimap = stack.getAttributeModifiers();

		 if (multimap.containsKey(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName())) {
			 
			 if (multimap.get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()).toArray().length>0 ) {
				 
				 if (multimap.get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()).toArray()[0] instanceof AttributeModifier) {
					 
					 AttributeModifier weaponModifier=(AttributeModifier) multimap.get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()).toArray()[0];
					 
					 return weaponModifier.getAmount();
					 }
				 }
			 }
		 
		 return 0;
	 }
}