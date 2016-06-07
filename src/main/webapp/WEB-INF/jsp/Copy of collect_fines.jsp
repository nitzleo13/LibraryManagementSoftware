<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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

<title>Compute Fines</title>

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
	<%@include file="header.jsp"%>

	<div class="container theme-showcase" role="main">
	<c:choose>
			<c:when test="${payinfullResults == 'checkedin' }">
				<div class="alert alert-success" role="alert">
        			<strong>Well done!</strong> Your fine has been paid in full.  successfully
     			 </div>
				<br></br>
			</c:when>
			<c:when test="${payinfullResults == 'error' }">
				 <div class="alert alert-danger" role="alert">
        <strong>Oh snap!</strong> Payment failed. Please look up record and try again
      		</div>
				
				<br></br>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>
	<form class="form-signin" action="/bodhi/admin/fines/collectfines"
			role="form">
			<h2 class="form-signin-heading">Look up Borrower</h2>
			 <input
				type="number" name='borrowerId' class="form-control"
				placeholder="Enter Card Number"> 
				<h2 class="form-signin-heading">OR</h2>
			<input type="search" name='borrowerName' class="form-control"
				placeholder="Enter borrower name" autofocus> <br></br>
				
				
			<button class="btn btn-lg btn-primary" type="submit">Search</button>
			<button class="btn btn-lg btn-primary" type="reset">Reset</button>
			
		</form>

	<c:choose>
			<c:when test="${lookUpFineResults=='nomatchingrecords'}">
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
			<c:when test="${lookUpFineResults=='error'}">
				<div class="page-header">
					<h1>Search results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Error occurred in retrieving fine details. Please try again later</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${not empty lookUpFineResults}">
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
									<th>Total Due</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:forEach var="borrower" items="${lookUpFineResults}"
										varStatus="borrowerIndex">
										<tr>
											<td>${borrowerIndex.index}</td>
											<td>${borrower.card_no}</td>
											<td>${borrower.fname}</td>
											<td>${borrower.lname}</td>
											<td><fmt:setLocale value="en_US"/>
											<fmt:formatNumber value="${borrower.fine_amt}" type="currency"/></p></td>
											<td><button type="button" class="btn btn-xs btn-warning" onclick="window.location.href='/bodhi/admin/fines/payfull?card_no=${borrower.card_no}'">Pay</button></td>
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
