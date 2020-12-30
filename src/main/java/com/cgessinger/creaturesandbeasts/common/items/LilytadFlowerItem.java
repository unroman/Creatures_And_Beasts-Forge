package com.cgessinger.creaturesandbeasts.common.items;

import com.cgessinger.creaturesandbeasts.CreaturesAndBeasts;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LilytadFlowerItem extends Item
{
	public LilytadFlowerItem ()
	{
		super(new Item.Properties()
				.group(CreaturesAndBeasts.TAB)
				.food(new Food.Builder()
						.hunger(4)
						.saturation(0.5F)
						.setAlwaysEdible()
						.build())
		);
	}

	@Override
	public ItemStack onItemUseFinish (ItemStack stack, World worldIn, LivingEntity entityLiving)
	{
		entityLiving.heal(4);
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
}