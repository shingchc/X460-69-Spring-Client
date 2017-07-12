<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Product Order List</title>
</head>
<body>
	Order:
	<sf:form method="POST" commandName="selectedProductOrders" action="list.html">
		<div>
			<c:forEach var="productOrder" items="${productOrderList}">
				<sf:checkbox path="itemList" value="${productOrder.productOrderId}" />
				<a href="view.html?productOrderId=${productOrder.productOrderId}">
					<c:out value="${productOrder.productOrderId}" />
				</a>
				
				<br>
			</c:forEach>
		</div>
 		<input type="submit" value="Delete" />
	</sf:form>
	<br>
	<a href="add.html">Add New</a>
</body>
</html>