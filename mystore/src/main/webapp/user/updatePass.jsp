<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/mystore/login.jsp"); %>
<!DOCTYPE html>

<html lang="en">
<%	String accountEmail = (String)session.getAttribute("currentSessionUser");%>

	<head>
		<meta charset="UTF-8">
	    <meta name="description" content="">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <link rel="icon" type="image/png" href="/mystore/logintemplate/images/icons/favicon.ico"/>
	    <link href="/mystore/css/all.min.css" rel="stylesheet">
	   	<link href="/mystore/js/all.min.js" rel="stylesheet">
	    
	    <script src="/mystore/js/all.min.js"></script>
		 
		 <link rel="stylesheet" href="/mystore/css/bootstrap.min.css">
		 <link href="/mystore/css/sb-admin-2.min.css" rel="stylesheet">


		<title>Update Password</title>
</head>
<link href="/mystore/css/select2.min.css" rel="stylesheet">

<body id="page-top">
	<!-- Page Wrapper -->
  <div id="wrapper">

   <jsp:include page="header.jsp" />

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

       <!-- Page Top Navigation  -->
		<jsp:include page="navigation.jsp" />

        <!-- Begin Page Content -->
        <div class="container-fluid">
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Update Account</h1>
          </div>
          <div class="row">
			<div class="mx-auto" style="width: 400px;">
			<form method="post"  action="/mystore/PasswordController">
       		
			<br>Current Password:<input type="password" class="form-control" name="currentPassword" />
			<br>New Password:<input type="password" class="form-control" name="newPassword"/>		
			
			<input type="email" hidden class="form-control" name="email" value="<c:out value="<%=accountEmail%>" />" />
			<br><br><input type = "submit" name="action" class="btn btn-primary" value = "Submit">

		</form>
	<br>
	
	<br>	
			</div>
          </div>         
        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
</body>
 <script src="/mystore/js/jquery-3.4.1.min.js"></script>
   <script src="/mystore/js/bootstrap.min.js"></script>
  
    <script src="/mystore/js/sb-admin-2.min.js"></script>
<script src="/mystore/js/select2.full.min.js"></script>
	<script>
$(document).ready(function(){
	$('.autocomplete').select2();
});
</script>
</html>
