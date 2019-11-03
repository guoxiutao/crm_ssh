<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crm客户关系管理系统</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	function addTab(title,url){
		//判断要打开的tab是否已经打开
		if($('#tt').tabs('exists',title)){
		//已经打开=>不再打开
			$('#tt').tabs('select',title);
			return ;
		}
		//未打开=>继续
		$('#tt').tabs('add',{
			title:title,
			content:"<iframe src='"+url+"' style='width:100%;height:100%;' />",
			closable:true
		});
	}
	</script>
</head>
<body class="easyui-layout">
	<div region="north" border="false" style="height:60px;background:#B3DFDA;padding:10px">
			<h1>Crm客户关系管理系统</h1>
	</div>
	<div region="west"  split="true" title="菜单" style="width:150px;">
					<div id="aa" class="easyui-accordion" fit="true" >
						<div title="用户管理"  style="overflow:auto;padding:10px;">
							<a href="JavaScript:void(0)" onclick="addTab('用户列表','${pageContext.request.contextPath}/list.jsp')" >用户列表</a>
						</div>
						<div title="客户管理"  >
						</div>
						<div title="联系人管理"  style="padding:10px;">
						</div>
					</div>
	</div>
	<div region="center" >
				<div id="tt" class="easyui-tabs" tools="#tab-tools" fit="true" >
					<div title="欢迎" tools="#p-tools" style="padding:20px;">
						<h3>欢迎您登陆!</h3>
					</div>
				</div>
	</div>
</body>
</html>