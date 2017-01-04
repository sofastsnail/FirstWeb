package com.snail.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.snail.ssm.exception.CustomException;
import com.snail.ssm.mapper.ItemMapper;
import com.snail.ssm.mapper.ItemMapperCustom;
import com.snail.ssm.po.Item;
import com.snail.ssm.po.ItemCustom;
import com.snail.ssm.po.ItemQueryVo;
import com.snail.ssm.service.ItemService;

public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapperCustom itemMapperCustom;
	@Autowired
	private ItemMapper itemMapper;

	public List<ItemCustom> findItemList(ItemQueryVo itemQueryVo)
			throws Exception {
		return itemMapperCustom.findItemList(itemQueryVo);
	}

	@Override
	public ItemCustom findItemById(Integer id) throws Exception {
		Item item = itemMapper.selectByPrimaryKey(id);
		ItemCustom itemCustom = null;
		if (item == null) {
			throw new CustomException("商品信息不存在！");
		} else {
			itemCustom = new ItemCustom();
			BeanUtils.copyProperties(item, itemCustom);
		}
		return itemCustom;
	}

	@Override
	public void updateItem(Integer id, ItemCustom itemCustom) throws Exception {
		// 添加业务校验

		// itemMapper.updateByPrimaryKeySelective(itemCustom);
		itemMapper.updateByPrimaryKey(itemCustom);

	}

}
