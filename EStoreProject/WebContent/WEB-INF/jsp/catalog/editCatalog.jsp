<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Edit Catalog</title>
</head>
<body>
    Edit Catalog Details:
<br>
<sf:form method="POST" modelAttribute="catalog">
<table border="0">
	<tr> 
		<th align="left">Catalog Id:</th>
    	<td align="left">${catalog.catalogId}</td> 
	</tr> 
	<tr> 
		<th align="left"><label for="catalog_name">Catalog Name:</label></th>
    	<td align="left">
    	    <sf:input path="catalogName" id="catalog_name" /><br/>
        	<sf:errors path="catalogName" cssClass="error" /> 
    	</td> 
	</tr> 
	<tr>
		<th></th>
		<td><input type="submit" value="Save" /></td>
	</tr>
</table>
</sf:form>

<br>
<a href="../list.html">Cancel</a>
<br><br>

</body>
</html>