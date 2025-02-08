package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.PhysicianDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.PhysicianModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "PhysicianCtl", urlPatterns = { "/ctl/PhysicianCtl" })

public class PhysicianCtl extends BaseCtl{
	
	
	@Override
	protected void preload(HttpServletRequest request) {
		
		Map<Integer, String> map = new HashMap();
		map.put(1, "dermatologist");
		map.put(2, "cardiologist");
		map.put(3, "neurologist");
		
		request.setAttribute("physi", map);	
		
		/*ap<Integer, String> map1 = new HashMap();
		map1.put(1, "Cash");
		map1.put(2, "online");
		map1.put(3, "netbanking");
		map1.put(4, "cheque");
		
		request.setAttribute("transaction_typee", map1);
*/
		
		 	}
	

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("fullName"))) {
			request.setAttribute("fullName", PropertyReader.getValue("error.require", "fullName"));
			pass = false;
		}

		
		
		if (DataValidator.isNull(request.getParameter("birthDate"))) {
			request.setAttribute("birthDate", PropertyReader.getValue("error.require", "birthDate"));
			pass = false;
		}

		

		if (DataValidator.isNull(request.getParameter("phone"))) {
			request.setAttribute("phone", PropertyReader.getValue("error.require", "phone"));

			pass = false;
		

		}
		if (DataValidator.isNull(request.getParameter("specialization"))) {
			request.setAttribute("specialization", PropertyReader.getValue("error.require", "specialization"));
			pass = false;
		}
		/*
		 * else if (!DataValidator.isFloat(request.getParameter("totalCost"))) {
		 * request.setAttribute("totalCost", "Only numbers are allowed"); pass = false;
		 * }
		 */
			  
		 

		return pass;

	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		PhysicianDTO dto = new PhysicianDTO();

		System.out.println(request.getParameter("dob"));

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setFullName(DataUtility.getString(request.getParameter("fullName")));
		dto.setBirthDate(DataUtility.getDate(request.getParameter("birthDate")));
		dto.setPhone(DataUtility.getString(request.getParameter("phone")));
        dto.setSpecialization(DataUtility.getString(request.getParameter("specialization")));



		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		PhysicianModelInt model = ModelFactory.getInstance().getPhysicianModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			PhysicianDTO dto;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		PhysicianModelInt model = ModelFactory.getInstance().getPhysicianModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			PhysicianDTO dto = (PhysicianDTO) populateDTO(request);
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

			PhysicianDTO dto = (PhysicianDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.PHYSICIAN_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PHYSICIAN_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PHYSICIAN_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PHYSICIAN_VIEW;
	}



}
