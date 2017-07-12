<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Catalog List</title>
</head>
<body>
	Catalog:
	<sf:form method="POST" commandName="selectedCatalogs" action="list.html">
		<div>
			<c:forEach var="catalog" items="${catalogList}">
				<sf:checkbox path="itemList" value="${catalog.catalogId}" />
				<a href="view.html?catalogId=${catalog.catalogId}">
					<c:out value="${catalog.catalogName}" />
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