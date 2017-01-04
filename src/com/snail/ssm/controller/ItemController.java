package com.snail.ssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.snail.ssm.controller.validation.ValidGroup1;
import com.snail.ssm.po.ItemCustom;
import com.snail.ssm.po.ItemQueryVo;
import com.snail.ssm.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@ModelAttribute("itemTypes")
	public Map<String, String> getItemTypes() {
		Map<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		return itemTypes;
	}

	@RequestMapping("/queryItem")
	public ModelAndView queryItem(HttpServletRequest request,
			ItemQueryVo itemQueryVo) throws Exception {
		// System.out.println("helloword");
		// System.out.println(request.getParameter("id"));
		List<ItemCustom> list = itemService.findItemList(itemQueryVo);
		ModelAndView mv = new ModelAndView();
		mv.addObject("itemList", list);
		mv.setViewName("item/itemList");
		return mv;
	}

	// 商品信息修改页面显示
	@RequestMapping(value = "/editItem", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView editItem(
			@RequestParam(value = "id", required = true) Integer itemId)
			throws Exception {
		ItemCustom itemCustom = itemService.findItemById(itemId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("itemCustom", itemCustom);
		mv.setViewName("item/editItem");
		return mv;
	}

	/*
	 * //返回String类型
	 * 
	 * @RequestMapping("/editItem") public String editItem(Model
	 * model,@RequestParam(value="id",required=true) Integer itemId) throws
	 * Exception{ ItemCustom itemCustom = itemService.findItemById(itemId);
	 * model.addAttribute("itemCustom", itemCustom); return "item/editItem"; }
	 */

	// 商品信息修改提交
	@RequestMapping("/editItemSubmit")
	public String editItemSubmit(Model model, Integer id,
			@Validated(value = { ValidGroup1.class }) ItemCustom itemCustom,
			BindingResult bindingResult, MultipartFile item_pic)
			throws Exception {
		if (bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError error : allErrors) {
				String str = new String(error.getDefaultMessage().getBytes(
						"ISO8859-1"), "utf-8");

				errorList.add(str);
			}
			model.addAttribute("allErrors", errorList);
			return "item/editItem";
		}
		// 原始名称
		String originalFilename = item_pic.getOriginalFilename();
		if (item_pic != null && originalFilename != null
				&& originalFilename.length() > 0) {
			// 存储图片路径
			String pic_path = "E:\\picture\\";
			// 新的图片名称
			String newFilename = UUID.randomUUID()
					+ originalFilename.substring(originalFilename
							.lastIndexOf('.'));
			//新图片
			File newFile = new File(pic_path+newFilename);
			//将内存中的数据写入到磁盘
			item_pic.transferTo(newFile);
			//将图片名称写到ItemCustom中
			itemCustom.setPic(newFilename);
		}
		itemService.updateItem(id, itemCustom);

		return "success";
	}

	// 批量删除商品信息
	@RequestMapping("deleteItem")
	public String deleteItem(Integer[] item_id) throws Exception {
		// 调用itemService中的批量删除方法
		return "success";
	}

	// 批量修改商品信息显示
	@RequestMapping("/editAllItem")
	public ModelAndView editAllItem(ItemQueryVo itemQueryVo) throws Exception {
		List<ItemCustom> list = itemService.findItemList(itemQueryVo);
		ModelAndView mv = new ModelAndView();
		mv.addObject("itemList", list);
		return mv;
	}

	// 批量修改页面提交
	@RequestMapping("/editAllItemSubmit")
	public String editAllItemSubmit(ItemQueryVo itemQueryVo) throws Exception {
		return "success";
	}
}
