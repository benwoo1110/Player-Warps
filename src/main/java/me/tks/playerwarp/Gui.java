package me.tks.playerwarp;

import me.tks.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class Gui {

    private final Inventory guiInv;
    private final int page;

    /**
     * Constructor for a GUI inventory
     *
     * @param page  Page of the GUI list
     * @param warps ArrayList containing all 36 warps in a sorted way
     */
    public Gui(int page, ArrayList<Warp> warps) {
        // Create new inventory
        guiInv = Bukkit.createInventory(null, 54, ChatColor.YELLOW + Messages.GUI_INVENTORY_NAME.getMessage());

        // Create pane item
        ItemStack pane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1);
        ItemMeta paneMeta = Bukkit.getItemFactory().getItemMeta(Material.GRAY_STAINED_GLASS_PANE);
        if (paneMeta != null) { paneMeta.setDisplayName(" "); }
        pane.setItemMeta(paneMeta);

        // Create back/next button
        ItemStack backButton = new ItemStack(Material.STONE_BUTTON, 1);
        ItemStack nextButton = new ItemStack(Material.STONE_BUTTON, 1);

        ItemMeta bbim = backButton.getItemMeta();
        ItemMeta nbim = nextButton.getItemMeta();
        if (bbim != null) { bbim.setDisplayName(ChatColor.GRAY + Messages.BACK_BUTTON.getMessage()); }
        if (nbim != null) { nbim.setDisplayName(ChatColor.GRAY + Messages.NEXT_BUTTON.getMessage()); }
        backButton.setItemMeta(bbim);
        nextButton.setItemMeta(nbim);

        // Create top gui item
        ItemStack guiItem = new ItemStack(Material.SUNFLOWER);
        ItemMeta guiItemMeta = Bukkit.getItemFactory().getItemMeta(Material.SUNFLOWER);

        if (guiItemMeta != null) { guiItemMeta.setDisplayName(ChatColor.YELLOW + "Created by The_King_Senne!"); }
        guiItem.setItemMeta(guiItemMeta);

        // Create page item
        ItemStack pageItem = new ItemStack(Material.PAPER, 1);
        ItemMeta pageMeta = Bukkit.getItemFactory().getItemMeta(Material.PAPER);

        if (pageMeta != null) { pageMeta.setDisplayName(ChatColor.AQUA + Messages.GUI_PAGE.getMessage() + page); }

        pageItem.setItemMeta(pageMeta);

        // Place items in gui
        for (int i = 0; i < 9; ++i) {
            guiInv.setItem(i, pane);
            guiInv.setItem(i + 45, pane);
        }

        guiInv.setItem(4, guiItem);
        guiInv.setItem(48, backButton);
        guiInv.setItem(50, nextButton);
        guiInv.setItem(49, pageItem);

        for (Warp warp : warps) {
            guiInv.addItem(warp.getItemStack());
        }

        this.page = page;
    }

    /**
     * Gets the page
     *
     * @return the page
     */
    public int getPage() {
        return this.page;
    }

    /**
     * Open gui.
     *
     * @param player the player
     */
    public void openGui(Player player) {
        player.openInventory(this.guiInv);
    }

    /**
     * Update item boolean.
     *
     * @param warp the warp
     * @return the boolean
     */
    public boolean updateItem(Warp warp) {
        for (int i = 0; i < guiInv.getSize(); i++) {
            if (guiInv.getItem(i).getItemMeta().getDisplayName().toLowerCase().equals(warp.getName())) {

                guiInv.setItem(i, warp.getItemStack());
                return true;
            }
        }
        return false;
    }

    /**
     * Has free slot boolean.
     *
     * @return the boolean
     */
    public boolean hasFreeSlot() {

        for (int i = 0; i < guiInv.getSize(); i++) {
            if (this.guiInv.getItem(i) == null) {
                return true;
            }
        }
        return false;

    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(ItemStack item) {
        this.guiInv.addItem(item);
    }

    /**
     * Remove item.
     *
     * @param item the item
     */
    public void removeItem(ItemStack item) {
        this.guiInv.removeItem(item);
    }

    /**
     * Gets inventory.
     *
     * @return the inventory
     */
    public Inventory getInventory() {
        return this.guiInv;
    }

}
