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

<title>Check In Books Page</title>

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
			<c:when test="${checkInResults == 'checkedin' }">
				<div class="alert alert-success" role="alert">
        			<strong>Well done!</strong> Check in completed successfully
     			 </div>
				<br></br>
			</c:when>
			<c:when test="${checkInResults == 'error' }">
				 <div class="alert alert-danger" role="alert">
        <strong>Oh snap!</strong> Check in failed. Please look up record and try again
      		</div>
				
				<br></br>
			</c:when>
			<c:when test="${renewResults == 'renewed' }">
					<div class="alert alert-success" role="alert">
        			<strong>Well done!</strong> Renewal request completed successfully
     			 </div>
				<br></br>
			</c:when>
			<c:when test="${renewResults == 'error' }">
			 <div class="alert alert-danger" role="alert">
        <strong>Oh snap!</strong> Renewal request failed. Please look up record and try again
      		</div>
				<br></br>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>
		<form class="form-signin" action="/bodhi/admin/loan/findLoans"
			role="form">
			<h2 class="form-signin-heading">Begin Check In</h2>
			<input type="text" name='bookId' class="form-control"
				placeholder="Enter Book ID" autofocus> <br></br>
			<input type="number" name='borrowerId' class="form-control"
				placeholder="Enter Card Number" autofocus> <br></br>
			<input type="text" name='borrowerName' class="form-control"
				placeholder="Enter Name" autofocus> <br></br>

				<button class="btn btn-lg btn-primary" type="submit">Look up</button>
				<button class="btn btn-lg btn-primary" type="reset">Reset</button>
		</form>

		<c:choose>
		<c:when test="${findLoansResults=='nomatchingrecords'}">
				<div class="page-header">
					<h1>Search results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>No matching records found for check in. Please change your search
										criteria and try again.</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${not empty findLoansResults}">
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
									<th>Card #</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Book ID</th>
									<th>Due Date</th>
									<th>Branch ID</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<c:forEach var="loaner" items="${findLoansResults}"
										varStatus="loanerIndex">
										<tr>
											<td>${loanerIndex.index}</td>
											<td>${loaner.card_no}</td>
											<td>${loaner.fname}</td>									
											<td>${loaner.lname}</td>
											<td>${loaner.book_id}</td>
											<td><fmt:formatDate type="date" value="${loaner.due_date}" /></td>
											<td>${loaner.branch_id}</td>
											<td><button type="button" class="btn btn-xs btn-success" onclick="window.location.href='/bodhi/admin/loan/loancheckin?loan_id=${loaner.loan_id}'">Check-in</button></td>
											<td><button type="button" class="btn btn-xs btn-success" onclick="window.location.href='/bodhi/admin/loan/renew?loan_id=${loaner.loan_id}'">Renew</button></td>
										</tr>
									</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:when>
			<c:when test="${findLoansResults=='error'}">
				<div class="page-header">
					<h1>Check In results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>An error occurred while attempting to retrieve the list of books. Please try your search again later</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
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
