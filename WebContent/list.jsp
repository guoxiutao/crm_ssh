<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
					$(function(){
						$('#test').datagrid({
							singleSelect:true,//单选
							title:'用户列表',//标题
							iconCls:'icon-save',//表格图标
							nowrap: false,//是否不换行显示列中的数据
							striped: true,//是否隔行变色
							collapsible:false,//是否可以折叠
							url:'${pageContext.request.contextPath}/UserAction_list',//加载表格数据的后台资源路径
							sortName: 'id',//指定排序列
							sortOrder: 'asc',//排序规则
							remoteSort: false,//是否进行远程排序
							idField:'id',//指定那一列是id列(没啥用)
							frozenColumns:[[//配置冻结列
				                {field:'ck',checkbox:true},//准备多选框列
				                {title:'id',field:'user_id',width:80,sortable:true}//主键列
							]],
							columns:[[
								{field:'user_code',title:'登录名',width:120},
								{field:'user_name',title:'昵称',width:120}
							]],
							pagination:true,//是否支持分页
							rownumbers:true,//知否显示行号
							toolbar:[{ //列表工具栏配置
								id:'btnadd',
								text:'添加用户',//按钮名称
								iconCls:'icon-add',
								handler:function(){
										 //清空表单
							        	$('#ff').form('clear');
										//打开添加窗口
										$('#w').window('open');
								}
							},{
								id:'btncut',
								text:'修改用户',
								iconCls:'icon-cut',
								handler:function(){
									//获得选中行并回显 表单
									getSelected();
								}
							},{
								id:'btnsave',
								text:'删除用户',
								iconCls:'icon-cancel',
								handler:function(){
									//1 获得被选中的行
									var selected = $('#test').datagrid('getSelected');
											if (!selected){
														//未获得到=>提示请选择
													alert("请选择一行,在操作");
													return;
											}
									//2 发送ajax请求访问后台删除用户
									$.post("${pageContext.request.contextPath}/UserAction_delete", { user_id: selected.user_id},
									  function(data){//执行成功后返回
									    alert(data);
									  	//刷新表单
										$('#test').datagrid('reload'); 
									  	
									  });
								}
							}]
						});
						
						//指定表单未ajax提交
						$('#ff').form({    
						    url:'${pageContext.request.contextPath}/UserAction_regist',    
						    onSubmit: function(){return true}, 
						    success:function(data){    
						        alert(data);
						        //清空表单
						        $('#ff').form('clear');
						        //关闭窗口
						        $('#w').window('close'); 
						        //刷新列表
						        $('#test').datagrid('reload'); 
						    }    
						});    
					});
					//提交表单
					function submitForm(){
						$('#ff').submit(); 
					}
					//获得被选中的行
					function getSelected(){
						//获得被选中的行
						var selected = $('#test').datagrid('getSelected');
						if (selected){//如果获得到被选中的行
							//打印行中的指定列
							//alert(selected.user_id+":"+selected.user_name+":"+selected.user_code);
							//将获得到的数据回显到表单中
							$('#ff').form('load',{
								user_id:selected.user_id,
								user_code:selected.user_code,
								user_name:selected.user_name
								});
							//显示窗口
							$('#w').window('open');
						}else{
							alert("请选中一行");
						}
					}
	</script>
</head>
<body>
	<table id="test"></table>
	<!-- 添加窗口 -->
	<div id="w" class="easyui-window" title="客户管理" closed="true" iconCls="icon-save" style="width:500px;height:200px;padding:5px;">
		<form id="ff" method="post" >
			<input type="hidden" name="user_id" />
	        <div>
	            <label for="name">登陆名:</label>
	            <input class="easyui-validatebox" type="text" name="user_code" required="true"></input>
	        </div>
	         <div>
	            <label for="name">昵&nbsp;称:</label>
	            <input class="easyui-validatebox" type="text" name="user_name" required="true"></input>
	        </div>
	         <div>
	            <label for="name">密&nbsp;码:</label>
	            <input class="easyui-validatebox" type="text" name="user_password" required="true"></input>
	        </div>
	        <div>
	            <input type="button" value="提交" onclick="submitForm();" >
	        </div>
	    </form>
	</div>
</body>
</html>