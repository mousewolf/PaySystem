<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/page/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/page/include/headScript.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>绑定邮箱成功</title>
</head>
<body>
<c:if test="${not empty currentUser}">
	<jsp:include page="/page/include/TopMenuMember.jsp"></jsp:include>
	<div class="container">
	</c:if>
	<div class="ht"></div>
	 <div class="headline">
	<div class="title">绑定邮箱</div>
</div>

	<div class="tipsBox">
	<div class="tipsTitle">
			<ul>
				<li class="TipsImg SuccTipsImg"></li>
				<li class="tipTxt markGreen"> 恭喜您，邮箱绑定成功！</li>
			</ul>
		</div>
	<div class="clear"></div>
	</div>
	<div class="ht"></div>
	</div>
	<jsp:include page="../../foot.jsp" />
</body>
</html>

<script type="text/javascript">
/*页面分类*/
 $(document).ready(function() { setPageType('.men-security', '.men-security-info '); })
 </script>