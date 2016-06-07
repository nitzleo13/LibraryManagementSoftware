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

<title>Check Out Books Page</title>

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
		<form class="form-signin" action="/bodhi/admin/loan/checkOutResults"
			role="form">
			<h2 class="form-signin-heading">Begin Check out</h2>
			<input type="text" name='bookId' class="form-control"
				placeholder="Enter Book ID" required autofocus> <br></br>
			<input type="text" name='borrowerId' class="form-control"
				placeholder="Enter Card Number" required autofocus> <br></br>
			<c:choose>
				<c:when test="${not empty branchList}">
					<h4>Select Branch : </h4>
					<select id="branch" name="branchId">
					<c:forEach var="branch" items="${branchList}">
							<option value="${branch.branch_id}">${branch.branch_name}</option>
					</c:forEach>
						</select>
											<br></br>
				</c:when>
				<c:otherwise>
					<input type="text" name='branchId' class="form-control"
						placeholder="Enter Branch ID">
					<br></br>
				</c:otherwise>
			</c:choose>


				<button class="btn btn-lg btn-primary" type="submit">Check
					Out</button>
				<button class="btn btn-lg btn-primary" type="reset">Reset</button>
		</form>


		<c:choose>
		<c:when test="${checkOutResults=='fines'}">
				<div class="page-header">
					<h1>Check Out results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>You have fines on your account. Please pay your dues before checkin out any additional books.</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${checkOutResults=='checkOutLimit'}">
				<div class="page-header">
					<h1>Check Out results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>You have reached your max limit on the number of books
										you can borrow. Please check in 1 or more books and try again.</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${not empty checkOutResultsNoErr}">
				<div class="page-header">
				<h1>Check Out results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th><c:forEach var="loan" items="${checkOutResultsNoErr}">
										<h4>Your book has been checked out successfully.Due date for book # ${loan.book_id} is ${loan.due_date}</h4>
									</c:forEach>
									</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${checkOutResults=='error'}">
				<div class="page-header">
					<h1>Check Out results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>An error occurred in completing the check out transaction. Please try again later</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${checkOutResults=='noCopyAvl'}">
				<div class="page-header">
					<h1>Check Out results</h1>
				</div>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>There is no copy of this book currently available in the selected branch. Please try to check out at a different 
									branch or use search feature to narrow down availability. </th>
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
