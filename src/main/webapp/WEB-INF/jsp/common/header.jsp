<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="topheader">
    <div class="left">
        <h1 class="logo">Vivado.<span>Manager</span></h1>
        <span class="slogan">Vivado虚拟机</span>
        
        <div class="search">
        	<form action="" method="post">
            	<input type="text" name="keyword" id="keyword" value="Enter keyword(s)" />
                <button class="submitbutton"></button>
            </form>
        </div><!--search-->
        
        <br clear="all" />
        
    </div><!--left-->
    
    <div class="right">
    	<!--<div class="notification">
             <a class="count" href="notifications.html"><span>9</span></a>
     	</div>
-->
        <div class="userinfo">
        	<img src="<%=request.getContextPath()%>/images/thumbs/avatar.png" alt="" />
            <span>${sessionScope.user.realName }</span>
        </div><!--userinfo-->
        
        <div class="userinfodrop">            	
        	<div class="avatar">
            	<a href=""><img src="<%=request.getContextPath()%>/images/thumbs/avatarbig.png" alt="" /></a>
                <div class="changetheme">
                	更改主题: <br />
                	<a class="default"></a>
                    <a class="blueline"></a>
                    <a class="greenline"></a>
                    <a class="contrast"></a>
                    <a class="custombg"></a>
                </div>
            </div><!--avatar-->
			<div class="userdata">
            	<h4>${sessionScope.user.realName }</h4>
                <span class="email">${sessionScope.user.mail }</span>
                <ul>
                    <li><a href="<%=request.getContextPath()%>/user/toResetPasswd">修改密码</a></li>
                    <li><a href="accountsettings.html">账号设置</a></li>
                    <li><a href="help.html">帮助</a></li>
                    <li><a href="<%=request.getContextPath()%>/logout">退出</a></li>
                </ul>
            </div><!--userdata-->
        </div><!--userinfodrop-->
    </div><!--right-->
    
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/public/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery-1.7.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery.jgrowl.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery.alerts.js"></script>
    
</div><!--topheader-->


<!--header-->