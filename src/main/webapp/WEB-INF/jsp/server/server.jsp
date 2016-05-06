<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>用户虚拟机</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.default.css" type="text/css" />
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="js/plugins/css3-mediaqueries.js"></script>
<![endif]-->
</head>

<body class="withvernav">
<div class="bodywrapper">
    <!-- include header jsp -->
    <jsp:include page="../common/header.jsp" />
    
        
     <div class="centercontent tables">
    
        <div class="pageheader notab">
            <h1 class="pagetitle">Vivado用户虚拟机</h1>
            <span class="pagedesc">用户申请虚拟机</span>
            
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper">
                        
            <div id="server_list">
            	<div class="contenttitle2">
	            	<h3>用户虚拟机列表</h3>
	            </div><!--contenttitle-->
	            <div class="tableoptions">
                	<a href="apply" class="btn btn_flag" title="申请虚拟机"><span>申请虚拟机</span></a> &nbsp;
                </div><!--tableoptions-->
	            <table cellpadding="0" cellspacing="0" border="0" class="stdtable stdtablecb" id="dyntable2">
	                <colgroup>
	                    <col class="con0" style="width: 4%" />
	                    <col class="con1" />
	                    <col class="con0" />
	                    <col class="con1" />
	                    <col class="con0" />
	                    <col class="con1" />
	                    <col class="con0" />
	                    <col class="con1" />
	                    <col class="con0" />
	                    <col class="con1" />
	                </colgroup>
	                <thead>
	                    <tr>
	                      	<th class="head0 nosort aligncenter"><input type="checkbox" class="checkall" /></th>
	                      	<th class="head1">实例ID</th>
	                        <th class="head0">名称</th>
	                        <th class="head1">固定IP</th>
	                        <th class="head0">浮动IP</th>
	                        <th class="head1">宿主机</th>
	                        <th class="head0">task-state</th>
	                        <th class="head1">状态</th>
	                        <th class="head0 nosort">操作</th>
	                    </tr>
	                </thead>
	                <tfoot>
	                    <tr>
	                      	<th class="head0 aligncenter"><span class="center">
	                        	<input type="checkbox" class="checkall" />
	                      	</span></th>
	                        <th class="head1">实例ID</th>
	                        <th class="head0">名称</th>
	                        <th class="head1">固定IP</th>
	                        <th class="head0">浮动IP</th>
	                        <th class="head1">宿主机</th>
	                        <th class="head0">task-state</th>
	                        <th class="head1">状态</th>
	                        <th class="head0 nosort">操作</th>
	                    </tr>
	                </tfoot>
	                <tbody>
	                	<c:forEach var="sm" items="${smList}">
	                		<tr>
		                		<td align="center">
		                			<span class="center">
		                				<input type="checkbox" value="${sm.instanceId}" />
		                			</span>
		                		</td>
						  		<td class="imeiId"><c:out value="${sm.instanceId}"/></td>
						  		<td><c:out value="${sm.serverName}"/></td>
						  		<td><c:out value="${sm.fixedIp}"/></td>
						  		<td><c:out value="${sm.floatingIp}"/></td>
						  		<td><c:out value="${sm.hypervisorName}"/></td>
						  		<td><c:out value="${sm.taskState}"/></td>
						  		<td><c:out value="${sm.serverState}"/></td>
						  		<td><a href="#" onclick="deleteServer(${sm.instanceId})">删除</a> | 
						  		<a href="#" onclick="display?instanceId=${sm.instanceId}">登录</a></td>
					  		</tr>
					  	</c:forEach>
	                </tbody>
	            </table>
            </div>    
        
        </div><!--contentwrapper-->
        
	</div><!-- centercontent -->
    
</div><!--bodywrapper-->

<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/custom/general.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/custom/tables.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/server/server.js"></script>

</body>
</html>
