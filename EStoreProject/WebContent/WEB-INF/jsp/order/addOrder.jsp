<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Add Order</title>
</head>
<body>
    Add New Order:
<br>
<sf:form method="POST" modelAttribute="order">
<table border="0">
	<tr> 
		<th><label for="user">User:</label></th>
    	<td><sf:input path="user" size="20" id="user" /><br/>
        	<sf:errors path="user" cssClass="error" /> 
    	</td>
    </tr>  
	<tr>
		<th></th>
		<td><input type="submit" value="Save" /></td>
	</tr>
</table>
</sf:form>
    
</body>
</html>