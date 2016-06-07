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

<title>Add Borrower</title>

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
			<c:when test="${update=='error'}">
			<div class="page-header">
					<h1>Error occurred. Please try again later</h1>
				</div>
			</c:when>
			<c:when test="${not empty update}">
			<c:forEach var="borrower" items="${update}">
		<form class="form-signin" action="/bodhi/admin/borrowers/updateBorrower"
			role="form">
			<h2 class="form-signin-heading">Borrower Card # : ${borrower.card_no} </h2>
			 <input
				type="text" name='fname' class="form-control"
				placeholder="First Name" required autofocus value="${borrower.fname}"><br></br> 
			<input type="text" name='lname' class="form-control"
				placeholder="Last Name" required autofocus value="${borrower.lname}"> <br></br>
			<input type="text" name='address' class="form-control"
				placeholder="Address" required autofocus value="${borrower.address}"> <br></br>
			<input type="text" name='city' class="form-control"
				placeholder="City" required autofocus value="${borrower.city}"> <br></br>
			<input type="text" name='state' class="form-control"
				placeholder="state" required autofocus value="${borrower.state}"> <br></br>				
			<input type="text" name='phone' class="form-control"
				placeholder="Phone Number" required autofocus value="${borrower.phone}"> <br></br>
			<input type="hidden" name='card_no' value="${borrower.card_no}">	
				
												
			<button class="btn btn-lg btn-primary" type="submit">Update</button>
			<button class="btn btn-lg btn-primary" type="reset">Reset</button>
			
		</form>
		</c:forEach>
		</c:when>
			<c:otherwise>
				<br />
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${updateresults=='error'}">
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>An error occurred while processing your request. Please try again.</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<br />
			</c:when>
			<c:when test="${not empty updateresults}">
			<div class="page-header">
					<h1>Borrower updated successfully.</h1>
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
