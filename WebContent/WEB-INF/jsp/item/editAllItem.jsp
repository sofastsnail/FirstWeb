<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量修改商品信息</title>
<script type="text/javascript">
	function editAllItem(){
		document.itemForm.action="${pageContext.request.contextPath}/item/editAllItemSubmit.action";
		document.itemForm.submit();
	}
	function queryItem(){
		document.itemForm.action="${pageContext.request.contextPath}/item/queryItem.action";
		document.itemForm.submit();
	}
</script>
</head>
<body>
	<form name="itemForm" action="${pageContext.request.contextPath}/item/queryItem.action" method="post">
		查询条件：
		<table width="60%" border="1">
			<tr>
				<td><input type="text" name="itemCustom.name"/></td>
				<td>
					<input type="button" value="查询" onclick="queryItem()"/>
					<input type="button" value="批量修改" onclick="editAllItem()"/>
				</td>
			</tr>
		</table>
		商品列表：
		<table width="60%" border="1">
			<tr>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
			</tr>
			<c:forEach var="item" items="${itemList }" varStatus="status">
				<tr>
					<td><input type="text" name="itemList[${status.index }].name" value="${item.name }"/></td>
					<td><input type="text" name="itemList[${status.index }].price" value="${item.price }"/></td>
					<td><input type="text" name="itemList[${status.index }].createtime" value="<fmt:formatDate value="${item.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>"/> </td>
					<td><input type="text" name="itemList[${status.index }].detail" value="${item.detail }"/></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>