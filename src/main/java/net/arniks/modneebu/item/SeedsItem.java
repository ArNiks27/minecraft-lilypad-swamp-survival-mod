
package net.arniks.modneebu.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class SeedsItem extends Item {
	public SeedsItem() {
		super(new Item.Properties().stacksTo(64).rarity(Rarity.COMMON));
	}
}
