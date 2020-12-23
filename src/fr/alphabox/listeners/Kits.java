package fr.alphabox.listeners;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.alphabox.inventory.ItemBuilder;
import fr.alphabox.main.Main;
import fr.alphabox.utils.InventoryCreate;
import me.extasio.alphaapi.mysql.PlayerInfo;


public class Kits implements Listener{
	
	ItemStack builder = new ItemBuilder(Material.BOOK).setDisplayName("§a§lRejoindre §7- §e§lAlphaBOX").build();

	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		
		ItemStack it = event.getItem();
		Player player = event.getPlayer();
    	new PlayerInfo(player);
		PlayerInfo playerInfo = new PlayerInfo(player);
		int niveau = Main.infos.get(player).get(1);
		
		//Head
		ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta im = (SkullMeta) item.getItemMeta();
		im.setOwner("MHF_ArrowRight");
		im.setLore(Main.getInstance().getConfig().getStringList("Message.kitgratuit"));
		im.setDisplayName("§9§lKit Gratuit");
		item.setItemMeta(im);
		
		//Head VIP
		ItemStack item2 = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
		SkullMeta im2 = (SkullMeta) item.getItemMeta();
		im2.setOwner("MHF_ArrowRight");
		im2.setLore(Main.getInstance().getConfig().getStringList("Message.kitvip"));
		im2.setDisplayName("§9§lKit VIP");
		item2.setItemMeta(im2);
		
		if(it != null && it.getType() == builder.getType()) {
				 InventoryCreate inv = new InventoryCreate(player, "§e§lAlphaGames §8§ §6Kits", 3);
				 inv.setClickable(false);
				
			     inv.fill(new ItemStack(Material.STAINED_GLASS_PANE));
			     inv.set(0, item);
			     inv.set(18, item2);
			     inv.set(2, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§9Kit §7- §6Soldat").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Soldat.Description"))
							.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(2, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(niveau >= 1) {
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName("§9Kit §7- §6Soldat")
										.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET).setDisplayName("§9Kit §7- §6Soldat")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setDisplayName("§9Kit §7- §6Soldat")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setDisplayName("§9Kit §7- §6Soldat")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).setDisplayName("§9Kit §7- §6Soldat")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le niveau nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.set(3, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setDisplayName("§9Kit §7- §aTank").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Tank.Description"))
							.addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(3, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(niveau >= 4) {
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.STONE_AXE).setDisplayName("§9Kit §7- §aTank")
										.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setHelmet(new ItemBuilder(Material.DIAMOND_HELMET).setDisplayName("§9Kit §7- §aTank")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).setDisplayName("§9Kit §7- §aTank")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).setDisplayName("§9Kit §7- §aTank")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).setDisplayName("§9Kit §7- §aTank")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le niveau nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.set(4, new ItemBuilder(Material.SUGAR).setDisplayName("§9Kit §7- §eEclaireur").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Eclaireur.Description"))
							.addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(4, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(niveau >= 8) {
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§9Kit §7- §eEclaireur")
										.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET).setDisplayName("§9Kit §7- §eEclaireur")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setDisplayName("§9Kit §7- §eEclaireur")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setDisplayName("§9Kit §7- §eEclaireur")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS).setDisplayName("§9Kit §7- §eEclaireur")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le niveau nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.set(5, new ItemBuilder(Material.BOW).setDisplayName("§9Kit §7- §bArcher").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Archer.Description"))
							.addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(5, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(niveau >= 12) {
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§9Kit §7- §bArcher")
										.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setHelmet(new ItemBuilder(Material.GOLD_HELMET).setDisplayName("§9Kit §7- §bArcher")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setDisplayName("§9Kit §7- §bArcher")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setDisplayName("§9Kit §7- §bArcher")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.GOLD_BOOTS).setDisplayName("§9Kit §7- §bArcher")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.getInventory().setItem(1, new ItemBuilder(Material.BOW).setDisplayName("§9Kit §7- §bArcher")
										.addEnchant(Enchantment.ARROW_DAMAGE, 2).addEnchant(Enchantment.ARROW_INFINITE, 1).addEnchant(Enchantment.ARROW_KNOCKBACK, 1).setUnbreakable(true)
										.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.getInventory().setItem(20, new ItemStack(Material.ARROW));
								player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le niveau nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.set(6, new ItemBuilder(Material.GOLDEN_APPLE).setDisplayName("§9Kit §7- §dPaladin").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Paladin.Description"))
							.addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(6, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(niveau >= 16) {
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.GOLD_SWORD).setDisplayName("§9Kit §7- §dPaladin")
										.addEnchant(Enchantment.DAMAGE_ALL, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setHelmet(new ItemBuilder(Material.GOLD_HELMET).setDisplayName("§9Kit §7- §dPaladin")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE).setDisplayName("§9Kit §7- §dPaladin")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS).setDisplayName("§9Kit §7- §dPaladin")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.GOLD_BOOTS).setDisplayName("§9Kit §7- §dPaladin")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le niveau nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.set(20, new ItemBuilder(Material.IRON_AXE).setDisplayName("§9Kit §7- §cBarbare").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Barbare.Description"))
							.addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(20, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(playerInfo.getRank() >= 1) {
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.GOLD_AXE).setDisplayName("§9Kit §7- §cBarbare")
										.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).setDisplayName("§9Kit §7- §cBarbare")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).setDisplayName("§9Kit §7- §cBarbare")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setDisplayName("§9Kit §7- §cBarbare")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addEnchant(Enchantment.PROTECTION_FALL, 10).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 0));
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le grade nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.set(21, new ItemBuilder(Material.POTION).setDisplayName("§9Kit §7- §5Sorcier").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Sorcier.Description"))
							.addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(21, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(playerInfo.getRank() >= 1) {
					    		ArrayList<String> lore = new ArrayList<String>();
					    		lore.add("§6§lCompétence 1: §cExplosion §7(35sec)");
					    		lore.add("§6§lCompétence 2: §bExpelliarmus §7(20sec)");
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§9Kit §7- §5Sorcier")
										.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.GRAY).setDisplayName("§9Kit §7- §5Sorcier")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).setDisplayName("§9Kit §7- §5Sorcier")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.GRAY).setDisplayName("§9Kit §7- §5Sorcier")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.GRAY).setDisplayName("§9Kit §7- §5Sorcier")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.getInventory().setItem(1, new ItemBuilder(Material.STICK).setDisplayName("§9Kit §7- §5Sorcier").setLore(lore)
										.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le grade nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.set(22, new ItemBuilder(Material.BLAZE_POWDER).setDisplayName("§9Kit §7- §6Pyromane").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Pyromane.Description"))
							.addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(22, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(playerInfo.getRank() >= 2) {
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.STONE_SWORD).setDisplayName("§9Kit §7- §6Pyromane")
										.addEnchant(Enchantment.FIRE_ASPECT, 2).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.RED).setDisplayName("§9Kit §7- §6Pyromane")
										.addEnchant(Enchantment.PROTECTION_FIRE, 3).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.GOLD_CHESTPLATE).setDisplayName("§9Kit §7- §6Pyromane")
										.addEnchant(Enchantment.PROTECTION_FIRE, 3).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.GOLD_LEGGINGS).setDisplayName("§9Kit §7- §6Pyromane")
										.addEnchant(Enchantment.PROTECTION_FIRE, 3).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).setDisplayName("§9Kit §7- §6Pyromane")
										.addEnchant(Enchantment.PROTECTION_FIRE, 3).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.getInventory().setItem(1, new ItemBuilder(Material.BOW).setDisplayName("§9Kit §7- §6Pyromane")
										.addEnchant(Enchantment.ARROW_FIRE, 1).addEnchant(Enchantment.ARROW_INFINITE, 1).setUnbreakable(true)
										.addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.getInventory().setItem(20, new ItemStack(Material.ARROW));
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le grade nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.set(23, new ItemBuilder(Material.FEATHER).setDisplayName("§9Kit §7- §fAnge").setLore((ArrayList<String>) Main.getInstance().getConfig().getStringList("Kit.Ange.Description"))
							.addItemFlag(ItemFlag.HIDE_ENCHANTS).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
			     inv.addRunnable(23, new Runnable() {
			    		 
			            @Override
			            public void run() {
					    	if(playerInfo.getRank() >= 2) {
								player.getInventory().clear();
								player.getEquipment().clear();
								player.getInventory().setItem(0, new ItemBuilder(Material.STONE_AXE).setDisplayName("§9Kit §7- §fAnge")
										.addEnchant(Enchantment.DAMAGE_ALL, 1).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setHelmet(new ItemBuilder(Material.LEATHER_HELMET).setColor(Color.WHITE).setDisplayName("§9Kit §7- §fAnge")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5).addEnchant(Enchantment.PROTECTION_FALL, 10).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.getInventory().setChestplate(new ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(Color.WHITE).setDisplayName("§9Kit §7- §fAnge")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setLeggings(new ItemBuilder(Material.LEATHER_LEGGINGS).setColor(Color.WHITE).setDisplayName("§9Kit §7- §fAnge")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).setUnbreakable(true).build());
								player.getInventory().setBoots(new ItemBuilder(Material.LEATHER_BOOTS).setColor(Color.WHITE).setDisplayName("§9Kit §7- §fAnge")
										.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5).setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								player.getInventory().setItem(1, new ItemBuilder(Material.FEATHER).setDisplayName("§9Kit §7- §fAnge")
										.setUnbreakable(true).addItemFlag(ItemFlag.HIDE_ATTRIBUTES).addItemFlag(ItemFlag.HIDE_UNBREAKABLE).build());
								inv.close();
					    	} else {
					    		player.sendMessage("§cVous n'avez pas le grade nécessaire.");
					    		player.getWorld().playSound(player.getLocation(), Sound.SKELETON_IDLE, 1, 1);
					    		inv.close();
					    	}
			            }
			     });
			     inv.open();
			}
		}
}
