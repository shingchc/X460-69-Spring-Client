<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>View Catalog</title>
</head>
<body>
    Catalog Details:
<br>
<table border="0">
	<tr> 
		<th align="left">Catalog Id:</th>
    	<td align="left">${catalog.catalogId}</td> 
	</tr> 
	<tr> 
		<th align="left">Catalog Name:</th>
    	<td align="left">${catalog.catalogName}</td> 
	</tr> 
	<tr> 
		<th></th>
    	<td><a href="edit/${catalog.catalogId}.html">Edit</a></td> 
	</tr> 
</table>
<br>
<a href="list.html">Back to Catalog List</a>
<br><br>

</body>
</html>