<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Edit Order</title>
</head>
<body>
    Edit Order Details:
<br>
<sf:form method="POST" modelAttribute="order">
<table border="0">
	<tr> 
		<th align="left">Order Id:</th>
    	<td align="left">${order.orderId}</td> 
	</tr>
	<tr> 
		<th align="left"><label for="user">User:</label></th>
    	<td align="left">
    	    <sf:input path="user" id="user" /><br/>
        	<sf:errors path="user" cssClass="error" /> 
    	</td> 
	</tr> 
	<tr> 
		<th align="left">Total Amount:</label></th>
		<td align="left">${order.totalAmount}</td>	
	</tr>
	<tr> 
		<th align="left">Confirm Number:</th>
    	<td align="left">${order.confirmNumber}</td> 
	</tr>   
	<tr> 
		<th align="left">Order Created:</th>
    	<td align="left">${order.orderCreated}</td> 
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