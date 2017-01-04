package com.snail.ssm.mapper;

import java.util.List;

import com.snail.ssm.po.ItemCustom;
import com.snail.ssm.po.ItemQueryVo;

public interface ItemMapperCustom {
	public List<ItemCustom> findItemList(ItemQueryVo itemQueryVo) throws Exception;
}
