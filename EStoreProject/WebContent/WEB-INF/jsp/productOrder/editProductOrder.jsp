<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Edit Product Order</title>
</head>
<body>
    Edit Product Order Details:
<br>
<sf:form method="POST" modelAttribute="productOrder">
<table border="0">
	<tr> 
		<th align="left">Product Order Id:</th>
    	<td align="left">${productOrder.productOrderId}</td> 
	</tr>
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
		<th align="left"><label for="order_amount">Order Amount:</label></th>
    	<td align="left">
    	    <sf:input path="orderAmount" id="order_amount" /><br/>
        	<sf:errors path="orderAmount" cssClass="error" /> 
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