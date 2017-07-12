<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<title>Catalog List</title>
</head>
<body>
	Departments:
	<sf:form method="POST" commandName="selectedCatalogs" action="list.html">
		<sf:checkboxes path="itemList" items="${catalogList}" 
    		itemValue="catalogId" itemLabel="catalogName" />
 		<input type="submit" value="Delete" />
	</sf:form>
	<br>
	<a href="add.html">Add New</a>
</body>
</html>