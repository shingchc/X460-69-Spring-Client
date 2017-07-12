<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Add Product</title>
</head>
<body>
    Add New Product:
<br>
<sf:form method="POST" modelAttribute="product">
<table border="0">
	<tr> 
		<th><label for="product_catalogId">Catalog ID:</label></th>
    	<td><sf:input path="catalogId" size="20" id="product_catalogId" /><br/>
        	<sf:errors path="catalogId" cssClass="error" /> 
    	</td> 
	</tr> 
	<tr> 
		<th><label for="product_name">Product Name:</label></th>
    	<td><sf:input path="name" size="20" id="product_name" /><br/>
        	<sf:errors path="name" cssClass="error" /> 
    	</td> 
	</tr> 
	<tr> 
		<th><label for="product_sku">Product SKU:</label></th>
    	<td><sf:input path="sku" size="20" id="product_sku" /><br/>
        	<sf:errors path="sku" cssClass="error" /> 
    	</td> 
	</tr>
		<tr> 
		<th><label for="product_uom">Product UOM:</label></th>
    	<td><sf:input path="unitOfMeasure" size="20" id="product_uom" /><br/>
        	<sf:errors path="unitOfMeasure" cssClass="error" /> 
    	</td> 
	</tr>
		<tr> 
		<th><label for="product_availQuant">Product Available Quantity:</label></th>
    	<td><sf:input path="availableQuantity" size="20" id="product_availQuant" /><br/>
        	<sf:errors path="availableQuantity" cssClass="error" /> 
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