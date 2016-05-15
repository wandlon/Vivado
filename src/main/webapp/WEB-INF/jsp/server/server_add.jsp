<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>申请虚拟机</title>
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
    
    <!-- include left jsp -->
    <jsp:include page="../common/left.jsp" />
        
     <div class="centercontent tables">
    
        <div class="pageheader notab">
            <h1 class="pagetitle">申请虚拟机</h1>
            <span class="pagedesc">用于用户申请虚拟机</span>
            
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper">
                        
            <div id="contentwrapper" class="contentwrapper">
            	
            	<div id="validation" class="subcontent">
            	
                    <form id="applyServer" class="stdform">
                    	<input type="hidden" name="userId" id="userId" value="12345" />
                    	<p>
                        	<label>虚拟机名称</label>
                            <span class="field"><input type="text" name="serverName" id="serverName" class="longinput" /></span>
                        </p>
                        
                        
                        <p>
                        	<label>虚拟机模板</label>
                            <span class="field">
                            	<select data-placeholder="选择模板" class="chzn-select imageId" style="width:350px;" tabindex="2" name="imageId">
                            		<option value="">请选择</option>
                                  	<c:forEach var="image" items="${imList }">
                                      	<option value="${image.imageId }">${image.imageName }</option>
                                  	</c:forEach>
                                </select>
                            </span>
                        </p>
                        
                        <p>
                        	<label>虚拟机规格</label>
                            <span class="field">
                            	<select data-placeholder="选择规格" class="chzn-select flavorId" style="width:350px;" tabindex="2" name="flavorId">
                            		<option value="">请选择</option>
                                  	<c:forEach var="flavor" items="${fmList }">
                                      	<option value="${flavor.flavorId }">${flavor.flavorName }</option>
                                  	</c:forEach>
                                </select>
                            </span>
                        </p>
                        
                        <br />
                        
                        <p class="stdformbutton">
                        	<button class="submit radius2">申请</button>
                        	&nbsp;&nbsp;
                        	<a href="../server/list" id="return" class="stdbtn" style="padding:10px">返回</a>
                        </p>
                    </form>

            </div><!--subcontent-->
        
        </div><!--contentwrapper-->
        
        </div><!--contentwrapper-->
        
	</div><!-- centercontent -->
    
</div><!--bodywrapper-->

<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/public/jquery.validate.extends.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/charCount.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/ui.spinner.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plugins/chosen.jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/custom/general.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/custom/forms.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/server/server_add.js"></script>

</body>
</html>
