package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto. SupplierDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.SupplierModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "SupplierCtl", urlPatterns = { "/ctl/SupplierCtl" })
public class  SupplierCtl extends BaseCtl{
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

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "name"));
			pass = false;


		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", " Only numbers are allowed");
			System.out.println(pass);
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("paymentterm"))) {
			request.setAttribute("paymentterm", PropertyReader.getValue("error.require", "paymentterm"));
			System.out.println(pass);
		
	} else if (!DataValidator.isFloat(request.getParameter("paymentterm"))) {
		request.setAttribute("paymentterm", "Only numbers are allowed");
		pass = false;
		}
//		if (!OP_UPDATE.equalsIgnoreCase(request.getParameter("operation"))) {
			
			
			if (DataValidator.isNull(request.getParameter("registrationdate"))) {
				request.setAttribute("registrationdate", PropertyReader.getValue("error.require", "registrationdate"));
				pass = false;
			
			}
					
			if(DataValidator.isNull(request.getParameter("category"))) {
			  request.setAttribute("category", PropertyReader.getValue("error.require", "category"));
			  pass = false;
		
			return pass;
			
			
				
		
	}
			return pass;
}
//		return pass;
//		}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		 SupplierDTO dto = new  SupplierDTO();
		
         
         System.out.println(request.getParameter("purchaseDate"));      
   
		 dto.setId(DataUtility.getLong(request.getParameter("id")));
		 dto.setName(DataUtility.getString(request.getParameter("name")));
		 dto.setCategory(DataUtility.getString(request.getParameter("category")));
         dto.setRegistrationdate(DataUtility.getDate(request.getParameter("registrationdate")));

         dto.setPaymentterm(DataUtility.getInt(request.getParameter("paymentterm")));
         

        populateBean(dto,request);
		

		return dto;

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		SupplierModelInt model = ModelFactory.getInstance().getSupplierModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			 SupplierDTO dto;
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
		SupplierModelInt model = ModelFactory.getInstance().getSupplierModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {
			 SupplierDTO dto = ( SupplierDTO) populateDTO(request);
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

			 SupplierDTO dto = ( SupplierDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView. SUPPLIER_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView. SUPPLIER_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView. SUPPLIER_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}
	
	
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView. SUPPLIER_VIEW;
	}

	


}
