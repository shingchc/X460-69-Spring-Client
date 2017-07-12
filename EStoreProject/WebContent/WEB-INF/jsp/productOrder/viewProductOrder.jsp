<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>View Order</title>
</head>
<body>
    Order Details:
<br>
<table border="0">
	<tr> 
		<th align="left">Product Order Id:</th>
    	<td align="left">${productOrder.productOrderId}</td> 
	</tr> 
	<tr> 
		<th align="left">Order Id:</th>
    	<td align="left">${productOrder.orderId}</td> 
	</tr> 
	<tr> 
		<th align="left">Product Id:</th>
    	<td align="left">${productOrder.productId}</td> 
	</tr>
	<tr> 
		<th align="left">Order Amount:</th>
    	<td align="left">${productOrder.orderAmount}</td> 
	</tr>  
	<tr> 
		<th></th>
    	<td><a href="edit/${productOrder.productOrderId}.html">Edit</a></td> 
	</tr> 
</table>
<br>
<a href="list.html">Back to Product Order List</a>
<br><br>

</body>
</html>