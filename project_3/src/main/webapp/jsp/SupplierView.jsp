<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.controller.SupplierCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@page import="in.co.rays.project_3.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Supplier View</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/utilities.js"></script>
<script type="text/javascript">
	function numberLength(input) {
		if (input.value.length > 10) {
			input.value = input.value.slice(0, 10);
		}
	}
</script>
<style type="text/css">
i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	padding-bottom: 11px;
	background-color: #ebebe0;
}

.input-group-addon {
	box-shadow: 9px 8px 7px #001a33;
	background-image: linear-gradient(to bottom right, white, pink);
	background-repeat: no-repeat;
	background-size: 100%;
	padding-bottom: 11px;
}

.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/download (3).jpeg');
	background-size: cover;
	padding-top: 6%;
}
</style>
</head>
<body class="hm">
	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<main>
	<form action="<%=ORSView.SUPPLIER_CTL%>" method="post">
		<jsp:useBean id="dto"
			class="in.co.rays.project_3.dto.SupplierDTO" scope="request"></jsp:useBean>
		<div class="row pt-3">
			<div class="col-md-4 mb-4"></div>
			<div class="col-md-4 mb-4">
				<div class="card input-group-addon">
					<div class="card-body">
						<%
							long id = DataUtility.getLong(request.getParameter("id"));
						%>
						<%
							if (dto.getId() != null && id > 0) {
						%>
						<h3 class="text-center default-text text-primary">Update
							 Supplier</h3>
						<%
							} else {
						%>
						<h3 class="text-center default-text text-primary">Add Supplier
							</h3>
						<%
							}
						%>

						<div>
							<%
								Map map = (Map) request.getAttribute("imp");
							%>
							<h4 align="center">
								<%
									if (!ServletUtility.getSuccessMessage(request).isEmpty()) {
								%>
								<div class="alert alert-success alert-dismissible">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<%=ServletUtility.getSuccessMessage(request)%>
								</div>
								<%
									}
								%>
							</h4>

							<h4 align="center">
								<%
									if (!ServletUtility.getErrorMessage(request).isEmpty()) {
								%>
								<div class="alert alert-danger alert-dismissible">
									<button type="button" class="close" data-dismiss="alert">&times;</button>
									<%=ServletUtility.getErrorMessage(request)%>
								</div>
								<%
									}
								%>
							</h4>
							<input type="hidden" name="id" value="<%=dto.getId()%>">
						</div>

						<!-- Input Fields -->
									<!-- Quantity ----------------------------------------------------------------------------------------------------------------------------->
						<span class="pl-sm-5"><b> Name</b>
		<span style="color: red;">*</span></span> </br>
		<div class="col-sm-12">
      <div class="input-group">
        <div class="input-group-prepend">
          <div class="input-group-text"><i class="fa fa-Supplier-alt grey-text" style="font-size: 1rem;"></i> </div>
        </div>
        <input type="text" class="form-control" name="name" placeholder=" Name"
        oninput="handleLetterInput(this, 'nameError', 9)"
		onblur="validateLetterInput(this, 'nameError', 9)"
         
        value="<%=DataUtility.getStringData(dto.getName())%>">
      </div>
    </div>
	<font color="red" class="pl-sm-5" id = "nameError"> <%=ServletUtility.getErrorMessage("name", request)%></font></br>


	<!-- Purchase Price ------------------------------------------------------------------------------------------------------------------------------>
						<div class="md-form">
							<label class="pl-sm-5"><b>paymentterm</b> <span
								style="color: red;">*</span></label><br>
							<div class="col-sm-12">
								<div class="input-group">
								<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-venus-mars grey-text"
													style="font-size: 1rem;"></i>
											</div>
										</div>
									<input type="text" class="form-control" name="paymentterm"
										placeholder="Enter paymentterm"
										oninput="handleIntegerInput(this, 'paymenttermError', 5)"
											onblur="validateIntegerInput(this, 'paymenttermError',5)"

												value="<%=(DataUtility.getStringData(dto.getPaymentterm()).equals("0") ? "" : dto.getPaymentterm())%>">
								</div>
							</div>
							<font color="red" id="paymenttermError" class="pl-sm-5">
								<%=ServletUtility.getErrorMessage("paymentterm", request)%>
							</font><br>
						</div>
<!-- Purchase Date ---------------------------------------------------------------------------------------------------------------------------------->
						<span class="pl-sm-5"><b>Registrationdate</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-calendar grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" id="datepicker2" name="registrationdate"
											class="form-control" placeholder=" Enter Date "
											readonly="readonly"
											value="<%=DataUtility.getDateString(dto.getRegistrationdate())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("registrationdate", request)%></font></br>
								

						<!-- Order Type ------------------------------------------------------------------------------------------------------------------------------>
						
						
						<span class="pl-sm-5"><b>category</b><span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-paper-plane grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
									<%=HTMLUtility.getList1("category", String.valueOf(dto.getCategory()), map)%>
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("category", request)%></font></br>

						

						<!-- Buttons ------------------------------------------------------------------------------------------------------------------------------------->
						<%
							if (dto.getId() != null && id > 0) {
						%>
						<div class="text-center">
							<input type="submit" name="operation"
								class="btn btn-success btn-md"
								value="<%=SupplierCtl.OP_UPDATE%>"> <input
								type="submit" name="operation" class="btn btn-warning btn-md"
								value="<%=SupplierCtl.OP_CANCEL%>">
						</div>
						<%
							} else {
						%>
						<div class="text-center">
							<input type="submit" name="operation"
								class="btn btn-success btn-md"
								value="<%=SupplierCtl.OP_SAVE%>"> <input
								type="submit" name="operation" class="btn btn-warning btn-md"
								value="<%=SupplierCtl.OP_RESET%>">
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4"></div>
		</div>
	</form>
	</main>
	<%@include file="FooterView.jsp"%>
</body>
</html>
