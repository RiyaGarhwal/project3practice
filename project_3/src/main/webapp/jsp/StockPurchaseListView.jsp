<%@page import="java.util.Map"%>
<%@page import="in.co.rays.project_3.dto.StockPurchaseDTO"%>
<%@page import="in.co.rays.project_3.controller.StockPurchaseListCtl"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.model.ModelFactory"%>
<%@page import="in.co.rays.project_3.model.RoleModelInt"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>

<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>StockPurchase List</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/utilities.js"></script>

<script>
	function validateKey(event) {
		// Check if the pressed key is a digit (0-9)
		if (!/[0-9]/.test(event.key)) {
			// Show validation message
			var validationMsg = document.getElementById('validation-message');
			validationMsg.style.display = 'inline';
			event.preventDefault(); // Prevent typing the invalid character
		} else {
			// Hide validation message if input is valid
			var validationMsg = document.getElementById('validation-message');
			validationMsg.style.display = 'none';
		}
	}
</script>

<style>
.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/1234.jpeg');
	background-size: cover;
	background-repeat: no-repeate;
	padding-top: 6%;
}

.p1 {
	padding: 4px;
	width: 200px;
	font-size: bold;
}

.text {
	text-align: center;
}
</style>
</head>

<body class="hm">
	<%@include file="Header.jsp"%>
	<%@include file="calendar.jsp"%>
	<div></div>
	<div>
		<form class="pb-5" action="<%=ORSView.STOCKPURCHASE_LIST_CTL%>"
			method="post">
			<jsp:useBean id="dto"
				class="in.co.rays.project_3.dto.StockPurchaseDTO" scope="request"></jsp:useBean>
			<%
				Map map = (Map) request.getAttribute("imp");
			%>



			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
				RoleDTO rbean1 = new RoleDTO();
				RoleModelInt rmodel = ModelFactory.getInstance().getRoleModel();

				List list = ServletUtility.getList(request);

				Iterator<StockPurchaseDTO> it = list.iterator();
				if (list.size() != 0) {
			%>
			<center>
				<h1 class="text-secondary font-weight-bold pt-3">StockPurchase
					List</h1>
			</center>
			<div class="row">
				<div class="col-md-4"></div>
				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>

			<div class="row">


				<!-- <div class="col-sm-2"></div> -->
				<div class="col-sm-2">
					<input type="text" name="quantity" placeholder="Enter quantity"
						class="form-control"
						oninput="handleIntegerInput(this, 'quantityError', 5)"
						onblur="validateIntegerInput(this, 'quantityError', 5)"
	
						value="<%=ServletUtility.getParameter("quantity", request)%>">
						
						<font color="red" class="pl-sm-5" id="quantityError"></font>
						
				</div>


				<div class="col-sm-2">
					<input type="text" name="purchasePrice" placeholder="Enter PurchasePrice"
						class="form-control" 
						
						oninput="handleDoubleInput(this, 'purchasePriceError', 4)"
											onblur="handleDoubleInput(this, 'purchasePriceError',4)"
						value="<%=ServletUtility.getParameter("purchasePrice", request)%>" >
						<font color="red"  class="pl-sm-5" id="purchasePriceError" ></font>
						
						
						
						
				</div>
				
				
				<div class="col-sm-2"><%=HTMLUtility.getList1("orderType", String.valueOf(dto.getOrderType()), map)%></div>



				<div class="col-sm-2">
					<input type="text" name="purchaseDate"
						placeholder="Enter purchaseDate" id="datepicker2" readonly="readonly"
						class="form-control"
						value="<%=ServletUtility.getParameter("purchaseDate", request)%>">
				</div>



				<input type="submit" class="btn btn-primary btn-md"
					style="font-size: 15px" name="operation"
					value="<%=StockPurchaseListCtl.OP_SEARCH%>"> &emsp; <input
					type="submit" class="btn btn-dark btn-md" style="font-size: 15px"
					name="operation" value="<%=StockPurchaseListCtl.OP_RESET%>">
			</div>


			<div class="col-sm-2"></div>
	</div>

	</br>
	<div style="margin-bottom: 20px;" class="table-responsive">
		<table class="table table-bStockPurchaseed table-dark table-hover">
			<thead>
				<tr style="background-color: purple;">

					<th width="10%"><input type="checkbox" id="select_all"
						name="Select" class="text"> Select All</th>
					<th width="5%" class="text">S.NO</th>
					<th width="15%" class="text">Quantity</th>
					<th width="15%" class="text">PurchasePrice</th>
					<th width="15%" class="text">OrderType</th>
					<th width="15%" class="text">PurchaseDate</th>

					<!-- <th width="15%" class="text">ROLE</th> -->
					<th width="10%" class="text">Edit</th>
				</tr>
			</thead>
			<%
				while (it.hasNext()) {
						dto = (StockPurchaseDTO) it.next();
			%>
			<tbody>
				<tr>
					<td align="center"><input type="checkbox" class="checkbox"
						name="ids" value="<%=dto.getId()%>"></td>
					<td class="text"><%=index++%></td>
					<td class="text"><%=dto.getQuantity()%></td>
			

					<td class="text"><%=dto.getPurchasePrice()%></td>
					<td class="text"><%=map.get(Integer.parseInt(dto.getOrderType()))%></td>

					<td class="text"><%=DataUtility.getDateString(dto.getPurchaseDate())%></td>


					<td class="text"><a
						href="StockPurchaseCtl?id=<%=dto.getId()%>">Edit</a></td>
				</tr>
			</tbody>
			<%
				}
			%>
		</table>
	</div>
	<table width="100%">
		<tr>
			<td><input type="submit" name="operation"
				class="btn btn-warning btn-md" style="font-size: 17px"
				value="<%=StockPurchaseListCtl.OP_PREVIOUS%>"
				<%=pageNo > 1 ? "" : "disabled"%>></td>

			<td><input type="submit" name="operation"
				class="btn btn-primary btn-md" style="font-size: 17px"
				value="<%=StockPurchaseListCtl.OP_NEW%>"></td>

			<td><input type="submit" name="operation"
				class="btn btn-danger btn-md" style="font-size: 17px"
				value="<%=StockPurchaseListCtl.OP_DELETE%>"></td>

			<td align="right"><input type="submit" name="operation"
				class="btn btn-warning btn-md" style="font-size: 17px"
				style="padding: 5px;" value="<%=StockPurchaseListCtl.OP_NEXT%>"
				<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
		</tr>
		<tr></tr>
	</table>

	<%
		}
		if (list.size() == 0) {
	%>
	<center>
		<h1 style="font-size: 40px; color: #162390;">StockPurchase List</h1>
	</center>
	</br>
	<div class="row">
		<div class="col-md-4"></div>

		<%
			if (!ServletUtility.getErrorMessage(request).equals("")) {
		%>
		<div class=" col-md-4 alert alert-danger alert-dismissible">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
			</h4>
		</div>
		<%
			}
		%>




		<%
			if (!ServletUtility.getSuccessMessage(request).equals("")) {
		%>

		<div class="col-md-4 alert alert-success alert-dismissible"
			style="background-color: #80ff80">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<h4>
				<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h4>
		</div>
		<%
			}
		%>
		<div style="padding-left: 48%;">
			<input type="submit" name="operation" class="btn btn-primary btn-md"
				style="font-size: 17px" value="<%=StockPurchaseListCtl.OP_BACK%>">
		</div>

		<div class="col-md-4"></div>
	</div>

	<%
		}
	%>

	<input type="hidden" name="pageNo" value="<%=pageNo%>">
	<input type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>


	</div>


</body>
<%@include file="FooterView.jsp"%>
</html>
