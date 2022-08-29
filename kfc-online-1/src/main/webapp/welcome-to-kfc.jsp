<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/core"%>
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
	          <a class="nav-link active" aria-current="page" href="#">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="add-product.jsp">Add New</a>
	        </li>
	      </ul>
	      <form class="d-flex" action="product">
	        <input class="form-control me-2" type="search" name="productcode" placeholder="Product Code" aria-label="Search">
	        <button class="btn btn-outline-light" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>
	<br/>
	<br/>
	<h2>Welcome to KFC!</h2>
	<br/>
	<p style="color:red;">${deleteMessage}</p>
	<p style="color:red;">${message}</p>
	<br/>
	<div class="container">
		<table class="table table-striped">
		  <thead>
		    <tr class="table-dark">
		      <th scope="col">Product Code</th>
		      <th scope="col">Product Name</th>
		      <th scope="col">Price</th>
		      <th scope="col">Delete</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<tag:forEach var="product" items="${productList}">
			    <tr>
			      <td>${product.productCode}</td>
			      <td>${product.productName}</td>
			      <td>${product.price}</td>
			      <td>
			      	<form action="product?type=delete" method="post">
			      		<button type="submit" class="btn btn-danger">Delete</button>
			      		<input type="hidden" name="productCode" value="${product.productCode}">
			      	</form>
				  </td>
			    </tr>
		    </tag:forEach>
		  </tbody>
		</table>
	</div>	
</body>
</html>