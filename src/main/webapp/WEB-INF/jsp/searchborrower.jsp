<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon"
	href="${pageContext.request.contextPath}/bootstrap/favicon/favicon.ico">

<title>Borrower Search Page</title>

<!-- Bootstrap core CSS -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap theme -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="${pageContext.request.contextPath}/bootstrap/css/landing/theme.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script
	src="${pageContext.request.contextPath}/bootstrap/js/ie-emulation-modes-warning.js"></script>


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body role="document">

	    <!-- Fixed navbar -->
    <%@include file="header.jsp" %>
    
	<div class="container theme-showcase" role="main">
		<c:choose>
			<c:when test="${deleteStatus == 'deleted' }">
			<div class="alert alert-success" role="alert">
        			<strong>Borrower is successfully deleted.
     			 </div>
				<br></br>
			</c:when>
			<c:when test="${deleteStatus == 'checkedOutBooks' }">
			<div class="alert alert-danger" role="alert">
        			<strong>Borrower still has books checked out. Please check in all books before deleting the borrower.
     			 </div>
				<br></br>
			</c:when>
				<c:when test="${deleteStatus == 'error' }">
			<div class="alert alert-success" role="alert">
        			<strong>Error occurred while deleting the borrower. Please try again later.
     			 </div>
				<br></br>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>
		<form class="form-signin" action="/bodhi/admin/borrowers/searchResults"
			role="form">
			<h2 class="form-signin-heading">Search Borrower</h2>
			 <input
				type="text" name='borrowerId' class="form-control"
				placeholder="Enter Card Number"> 
				<h2 class="form-signin-heading">OR</h2>
			<input type="search" name='borrowerName' class="form-control"
				placeholder="Enter borrower name" autofocus> <br></br>
				
				
			<button class="btn btn-lg btn-primary" type="submit">Search</button>
			<button class="btn btn-lg btn-primary" type="reset">Reset</button>
			
		</form>


		<c:choose>
			<c:when test="${borrowerresults=='nomatchingrecords'}">
				<div class="page-header">
					<h1>Search results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>No Borrowers found. Please change your search
										criteria and try again.</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${not empty borrowerresults}">
				<div class="page-header">
					<h1>Search results</h1>
				</div>
				<br />
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Borrower ID</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Address</th>
									<th>Phone</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:forEach var="borrower" items="${borrowerresults}"
										varStatus="borrowerIndex">
										<tr>
											<td>${borrowerIndex.index}</td>
											<td>${borrower.card_no}</td>
											<td>${borrower.fname}</td>
											<td>${borrower.lname}</td>
											<td>${borrower.address}, ${borrower.city}, ${borrower.state} </td>
											<td>${borrower.phone}</td>
											<td><button type="button" class="btn btn-xs btn-success" onclick="window.location.href='/bodhi/admin/borrowers/update?card_no=${borrower.card_no}'">Edit</button></td>
											<td><button type="button" class="btn btn-xs btn-danger" onclick="window.location.href='/bodhi/admin/borrowers/delete?card_no=${borrower.card_no}'">X</button></td>
										</tr>
									</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<br />
			</c:otherwise>
		</c:choose>









	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/docs.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script
		src="${pageContext.request.contextPath}/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
