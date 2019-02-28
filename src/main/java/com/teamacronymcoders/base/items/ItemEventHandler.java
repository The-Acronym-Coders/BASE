package com.teamacronymcoders.base.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemEventHandler {
    @SubscribeEvent
    public void entityRightClicked(PlayerInteractEvent.EntityInteractSpecific entityInteract) {
        ItemStack itemStack = entityInteract.getItemStack();
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof IRightClickEntity) {
            if(((IRightClickEntity) itemStack.getItem()).rightClickEntity(itemStack, entityInteract.getTarget(),
                    entityInteract.getEntityPlayer(), entityInteract.getEntityPlayer().getEntityWorld(), entityInteract.getHand())) {
                entityInteract.setResult(Event.Result.DENY);
            }
        }
    }
}
