<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/mystore/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <!--  <i class="fas fa-laugh-wink"></i>-->
        </div>
        <div class="sidebar-brand-text mx-3">MyStore<sup><!--<i class="fas fa-heart"></i> --></sup></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="/mystore/index.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Home</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Menu
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-user-circle"></i>
          <span>Accounts</span>
        </a>
		<%	String email = (String)session.getAttribute("currentSessionUser");%>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Account:</h6>
            <a class="collapse-item" href="/mystore/UserController?action=viewAccount&email=<c:out value="<%=email %>"/>">View Account</a>
			<a class="collapse-item" href="/mystore/UserController?action=updateAccount&email=<c:out value="<%=email%>" />">Update Account</a>			
          </div>
        </div>
      </li>
   
      <!-- Nav Item - Utilities Collapse Menu -->
     	  
	  <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProduct" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-pencil-ruler"></i>
          <span>Product Inventory</span>
        </a>
        <div id="collapseProduct" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Product:</h6>
            <a class="collapse-item" href="/mystore/ProductController?action=addProduct">Add Product</a>
            <a class="collapse-item" href="/mystore/ProductController?action=viewProduct">View Product</a>	
          </div>
        </div>
      </li>
      	  	
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOrderTo" aria-expanded="true" aria-controls="collapseUtilities">
         <i class="fas fa-edit"></i>
          <span>Order Management</span>
        </a>
        <div id="collapseOrderTo" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Order:</h6>
            <a class="collapse-item" href="/mystore/OrderController?action=createOrder">Add Order</a>
            <!-- <a class="collapse-item" href="/mystore/OrderController?action=viewOrder&email=<c:out value="<%=email %>"/>">View Your Order</a>-->            
            <a class="collapse-item" href="/mystore/OrderController?action=listOrder">List All Orders</a>
            <a class="collapse-item" href="/mystore/OrderController?action=uploadExcel">Upload Excel</a>
            
          </div>
        </div>
      </li>
	  
	  <li class="nav-item">
        <a class="nav-link" href="/mystore/LoginController?action=Logout" onclick="return confirm('Are you sure want to Logout?')">
          <i class="fas fa-sign-out-alt"></i>
          <span>Logout</span></a>
      </li>

    </ul>
    <!-- End of Sidebar -->
        
        