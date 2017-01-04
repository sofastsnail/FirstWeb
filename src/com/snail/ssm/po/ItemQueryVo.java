package com.snail.ssm.po;

import java.util.List;

public class ItemQueryVo {
	
	private Item item;
	private ItemCustom itemCustom;
	private List<ItemCustom> itemList;
	
	public List<ItemCustom> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemCustom> itemList) {
		this.itemList = itemList;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public ItemCustom getItemCustom() {
		return itemCustom;
	}
	public void setItemCustom(ItemCustom itemCustom) {
		this.itemCustom = itemCustom;
	}
	
}
