<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Hello World!世界你好！</h2>
<table>
	<c:forEach items="${user}" var="item" >
		<tr>
			<td>
				${item.realName}
			</td>
			<td>
				${item.telePhone}
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>
