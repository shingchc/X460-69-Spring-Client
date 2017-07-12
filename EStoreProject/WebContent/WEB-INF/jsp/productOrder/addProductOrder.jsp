<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Add Product Order</title>
</head>
<body>
    Add New Product Order:
<br>
<sf:form method="POST" modelAttribute="productOrder">
<table border="0">
	<tr> 
		<th><label for="order_id">Order Id:</label></th>
    	<td><sf:input path="orderId" size="20" id="order_id" /><br/>
        	<sf:errors path="orderId" cssClass="error" /> 
    	</td>
    </tr>	
    <tr> 
		<th><label for="product_id">Product Id:</label></th>
    	<td><sf:input path="productId" size="20" id="product_id" /><br/>
        	<sf:errors path="productId" cssClass="error" /> 
    	</td>
    </tr>
    	<tr> 
		<th><label for="order_amount">Order Amount:</label></th>
    	<td><sf:input path="orderAmount" size="20" id="order_amount" /><br/>
        	<sf:errors path="orderAmount" cssClass="error" /> 
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