package com.snail.ssm.service;

import java.util.List;

import com.snail.ssm.po.ItemCustom;
import com.snail.ssm.po.ItemQueryVo;

public interface ItemService {
	public List<ItemCustom> findItemList(ItemQueryVo itemQueryVo) throws Exception;
	public ItemCustom findItemById(Integer id) throws Exception;
	public void updateItem(Integer id,ItemCustom itemCustom) throws Exception;
}
