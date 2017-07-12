<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Order List</title>
</head>
<body>
	Order:
	<sf:form method="POST" commandName="selectedOrders" action="list.html">
		<div>
			<c:forEach var="order" items="${orderList}">
				<sf:checkbox path="itemList" value="${order.orderId}" />
				<a href="view.html?orderId=${order.orderId}">
					<c:out value="${order.orderCreated}" />
				</a>
				, User: <c:out value="${order.user}" />
				<br>
			</c:forEach>
		</div>
 		<input type="submit" value="Delete" />
	</sf:form>
	<br>
	<a href="add.html">Add New</a>
</body>
</html>