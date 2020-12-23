package fr.alphabox.utils;


import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemCreate {

  private ItemStack item;
  private List<String> lore = new ArrayList<String>();
  private ItemMeta meta;

  public ItemCreate(Material mat, short subid, int amount) {
    item = new ItemStack(mat, amount, subid);
    meta = item.getItemMeta();
  }

  public ItemCreate(ItemStack item) {
    this.item = item;
    this.meta = item.getItemMeta();
  }

  public ItemCreate(Material mat, short subid) {
    item = new ItemStack(mat, 1, subid);
    meta = item.getItemMeta();
  }

  public ItemCreate(Material mat, int amount) {
    item = new ItemStack(mat, amount, (short)0);
    meta = item.getItemMeta();
  }

  public ItemCreate(Material mat) {
    item = new ItemStack(mat, 1, (short)0);
    meta = item.getItemMeta();
  }

  public ItemCreate setAmount(int value) {
    item.setAmount(value);
    return this;
  }

  public ItemCreate setNoName() {
    meta.setDisplayName(" ");
    return this;
  }

  public ItemCreate setGlow() {
    meta.addEnchant( Enchantment.DURABILITY, 1, true);
    meta.addItemFlags( ItemFlag.HIDE_ENCHANTS);
    return this;
  }

  public ItemCreate setData(short data) {
    item.setDurability(data);
    return this;
  }

  public ItemCreate addLoreLine(String line) {
    lore.add(line);
    return this;
  }

  public ItemCreate addLoreArray(String[] lines) {
    for(int x = 0; x < lines.length; x++) {
      lore.add(lines[x]);
    }
    return this;
  }

  public ItemCreate addLoreAll(List<String> lines) {
    lore.addAll(lines);
    return this;
  }

  public ItemCreate setDisplayName(String name) {
    meta.setDisplayName(name);
    return this;
  }

  public ItemCreate setSkullOwner(String owner) {
    ((SkullMeta )meta).setOwner(owner);
    return this;
  }

  public ItemCreate setColor(Color c) {
    ((LeatherArmorMeta )meta).setColor(c);
    return this;
  }

  public ItemCreate setBannerColor(DyeColor c) {
    ((BannerMeta )meta).setBaseColor(c);
    return this;
  }

  public ItemCreate setUnbreakable(boolean value) {
    meta.spigot().setUnbreakable(value);
    return this;
  }

  public ItemCreate addEnchantment(Enchantment ench, int lvl) {
    meta.addEnchant(ench, lvl, true);
    return this;
  }

  public ItemCreate addItemFlag(ItemFlag flag) {
    meta.addItemFlags(flag);
    return this;
  }

  public ItemCreate addLeatherColor(Color color) {
    ((LeatherArmorMeta ) meta).setColor( color );
    return this;
  }

  public ItemStack build() {
    if(lore.isEmpty() == false) {
      meta.setLore(lore);
    }
    item.setItemMeta(meta);
    return item;
  }

}
