<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/mystore/login.jsp"); %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/png" href="/mystore/logintemplate/images/icons/favicon.ico"/>
	    <link href="/mystore/css/all.min.css" rel="stylesheet">
	   	<link href="/mystore/js/all.min.js" rel="stylesheet">
	    
	    <script src="/mystore/js/all.min.js"></script>
		 
		 <link rel="stylesheet" href="/mystore/css/bootstrap.min.css">
		 <link href="/mystore/css/sb-admin-2.min.css" rel="stylesheet">
		<link rel="stylesheet" href="/mystore/css/dataTables.min.css">

		<title>List Products</title>
	
</head>
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
            <h1 class="h3 mb-0 text-gray-800">List Products</h1>
          </div>
          <div class="row">
			<div class="table mx-auto">
			<table  id="myTable" class="display" border="1" cellpadding="5">
 	<thead>
            <tr>
                <th>Sku</th>
                <th>Product Name</th>
                <th>Product Price</th>                
                <th>Product Quantity</th>
                
                <th>Update</th>
                <th>Delete</th>
                
            </tr>
            </thead>
            <tbody>
        <c:forEach var="product" items="${products}" >
                <tr>
                    <td><c:out value="${product.sku}" /></td>
                    <td><c:out value="${product.name}" /></td>
                    <td><c:out value="${product.unitPrice}" /></td>
                    <td><c:out value="${product.quantity}" /></td>
                   
                    <td><a href="ProductController?action=updateProduct&sku=<c:out value="${product.sku}" />">Update</a></td>
                   	<td><a href="ProductController?action=deleteProduct&sku=<c:out value="${product.sku}" />">Delete</a></td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
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
<script src="/mystore/js/dataTables.min.js"></script>
	<script>
	$(document).ready( function () {
		   $('#myTable').DataTable();
		} );
	</script>
</html>
