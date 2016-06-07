<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/bootstrap/favicon/favicon.ico">

    <title>Bodhi Admin Home Page</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/landing/theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="${pageContext.request.contextPath}/bootstrap/js/ie-emulation-modes-warning.js"></script>

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

      <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h2>Welcome to Bodhi</h2>
        <p><h4>Please contact helpdesk @ <strong>1800-111-2222</strong> for your queries</h4></p>
      </div>

	

      <div class="page-header">
		 <div class="row marketing">
        <div class="col-lg-6">
          <h2>Search</h2>
          <p>Allows you to search using a combination of book id, title and author name. </p>

          <h2>Borrowers</h2>
          <p>This feature supports managing the borrowers at the library. You have the ability to add new borrower, update borrower details and delete borrowers. 
          The feature provides an easy way to look up existing borrowers for any changes</p>

         </div>

        <div class="col-lg-6">
          <h2>Fines</h2>
          <p>This module allows you manage fines at the library. It provides 2 main features, one is the ability to compute fines on a daily basis and the other is to recieve payments. </p>
			 
		  <h2>Loans</h2>
          <p>This module allows you to manage check in and check out of books. Provides easy to use search feature to allow check in, check outs and renewals. </p>
        </div>
        </div>
      </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="${pageContext.request.contextPath}/bootstrap/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
