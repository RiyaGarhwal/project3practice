package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto. StockPurchaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model. StockPurchaseModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "StockPurchaseCtl", urlPatterns = { "/ctl/StockPurchaseCtl" })
public class  StockPurchaseCtl extends BaseCtl{
	@Override
	protected void preload(HttpServletRequest request) {

	
		Map<Integer, String> map = new HashMap();
		map.put(1, "High");
		map.put(2, "Medium");
		map.put(3, "Low");
		
		
		request.setAttribute("imp", map);
	    
	}
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "quantity"));
			pass = false;


		} else if (!DataValidator.isInteger(request.getParameter("quantity"))) {
			request.setAttribute("quantity", " Only numbers are allowed");
			System.out.println(pass);
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("purchasePrice"))) {
			request.setAttribute("purchasePrice", PropertyReader.getValue("error.require", "purchasePrice"));
			System.out.println(pass);
		
	} else if (!DataValidator.isFloat(request.getParameter("purchasePrice"))) {
		request.setAttribute("purchasePrice", "Only numbers are allowed");
		pass = false;
		}
//		if (!OP_UPDATE.equalsIgnoreCase(request.getParameter("operation"))) {
			
			
			if (DataValidator.isNull(request.getParameter("purchaseDate"))) {
				request.setAttribute("purchaseDate", PropertyReader.getValue("error.require", "purchaseDate"));
				pass = false;
			
			}
					
			if(DataValidator.isNull(request.getParameter("orderType"))) {
			  request.setAttribute("orderType", PropertyReader.getValue("error.require", "orderType"));
			  pass = false;
		
			return pass;
			
			
				
		
	}
			return pass;
}
//		return pass;
//		}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		 StockPurchaseDTO dto = new  StockPurchaseDTO();
		
         
         System.out.println(request.getParameter("purchaseDate"));      
   
		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		 dto.setQuantity(DataUtility.getInt(request.getParameter("quantity")));
		 dto.setPurchasePrice(DataUtility.getDouble(request.getParameter("purchasePrice")));
         dto.setPurchaseDate(DataUtility.getDate(request.getParameter("purchaseDate")));

         dto.setOrderType(DataUtility.getString(request.getParameter("orderType")));
         

        populateBean(dto,request);
		

		return dto;

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		 StockPurchaseModelInt model = ModelFactory.getInstance().getStockPurchaseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			 StockPurchaseDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (Exception e) {
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		 StockPurchaseModelInt model = ModelFactory.getInstance().getStockPurchaseModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {
			 StockPurchaseDTO dto = ( StockPurchaseDTO) populateDTO(request);
			try {
				if (id > 0) {
					model.update(dto);
					
					ServletUtility.setSuccessMessage("Data is successfully Update", request);
				} else {
					
					try {
						 model.add(dto);
					 
						 ServletUtility.setDto(dto, request);
						ServletUtility.setSuccessMessage("Data is successfully saved", request);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						return;
					} catch (DuplicateRecordException e) {
						ServletUtility.setDto(dto, request);
						ServletUtility.setErrorMessage("Login id already exists", request);
					}

				}
				ServletUtility.setDto(dto, request);
				
				
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			 StockPurchaseDTO dto = ( StockPurchaseDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView. STOCKPURCHASE_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView. STOCKPURCHASE_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView. STOCKPURCHASE_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}
	
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView. STOCKPURCHASE_VIEW;
	}

	


}
