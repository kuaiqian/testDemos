<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>mvc</title>
</head>
<body>
Welcome,${user.userId}<br/>
<a href="<%=request.getContextPath()%>/message/list/${user.userId}" >我的消息</a>
<form action="<%=request.getContextPath()%>/upload" enctype="multipart/form-data" method="post">
	上传帅照<input type="file" name="pic" accept="image/png"/>
	<input type="hidden" name="picName" value="wodetouxiang"/>
	<input type="submit" name="提交"/>
</form>
</body>
</html>