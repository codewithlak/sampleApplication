<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
<meta charset="ISO-8859-1">
<title>KFC Online</title>
</head>
<body>	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <div class="container-fluid">
	    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	      	<li class="nav-item">
	          <a class="nav-link active" href="product">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="#">Manage Product</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="add-product.jsp">Add New</a>
	        </li>
	      </ul>
	      <form class="d-flex" action="product">
	        <input class="form-control me-2" type="search" placeholder="Product Code" name="productcode" aria-label="Search">
	        <button class="btn btn-outline-light" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>
	<br/>
	<br/>
	<div class="container">		
		
		<p style="color:red;">${message}</p>		
		<br/>
		<form action="product?type=update" method="post">
			<label for="productCode">Product Code:</label>
			<input class="form-control" readonly type="text" name="productcode" id="productCode" value="${product.productCode}"/>
			<br/>
			<label for="productName">Product Name:</label>
			<input class="form-control" type="text" name="productname" id="productName" value="${product.productName}"/>
			<br/>
			<label for="price">Price [LKR]:</label>
			<input class="form-control" type="number" name="price" id="price" value="${product.price}"/>			
			<br/>
			<button  class="btn btn-warning">Update</button>
		</form>
	</div>
</body>
</html>