<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Data Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="user-list.jsp" class="navbar-brand">Data Management</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Data</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post" enctype="mutlipart/form-data">
				</c:if>

				<caption>
					<h3>
						<c:if test="${user != null}">
            			Edit Data
            		</c:if>
						<c:if test="${user == null}">
            			Add New Data
            		</c:if>
					</h3>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>
				
				<div class="row">
				<div class="col">
				<fieldset class="form-group">
					<label>Voltage</label> <input type="text"
						value="<c:out value='${user.voltage}' />" class="form-control"
						name="Voltage" required="required">
				</fieldset>
					</div>
					<div class="col">
					<fieldset class="form-group">
					<label>Current</label> <input type="text"
						value="<c:out value='${user.current}' />" class="form-control"
						name="Current">
				</fieldset>
					</div>
				</div>
				<fieldset class="form-group">
					<label>Distance</label> <input type="text"
						value="<c:out value='${user.distance}' />" class="form-control"
						name="Distance">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Duration</label> <input type="text"
						value="<c:out value='${user.duration}' />" class="form-control"
						name="Duration">
				</fieldset>
					
				<fieldset class="form-group">
					<label>Power</label>
					<input type="text" name="Power" value="<c:out value='${user.power}' />" 
       				class="form-control">						
					 
				</fieldset>
				
				
				
				<div class="container text-center" style="padding: 10px;">
				<button type="submit" class="btn btn-outline-success">Save</button>
				<input class="btn btn-outline-danger" type="reset" value="Reset">
				</div>
			
			</div>
		</div>
	</div>


</body>
</html>