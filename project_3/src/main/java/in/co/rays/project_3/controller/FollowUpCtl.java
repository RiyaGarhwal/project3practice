package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.FollowUpDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.FollowUpModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(name = "FollowUpCtl", urlPatterns = { "/ctl/FollowUpCtl" })

public class FollowUpCtl extends BaseCtl{
	
	
	@Override
	protected void preload(HttpServletRequest request) {
		
		Map<Integer, String> map = new HashMap();
		map.put(1, "Shubham");
		map.put(2, "Paras");
		map.put(3, "Vaibhav");
		
		request.setAttribute("clien", map);	
		
		Map<Integer, String> map1 = new HashMap();
		map1.put(1, "Dentist");
		map1.put(2, "Dermatologist");
		map1.put(3, "Neurologist");
		map1.put(4, "Cardiologist");
		
		request.setAttribute("physi", map1);

		
		 	}
	

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("client"))) {
			request.setAttribute("client", PropertyReader.getValue("error.require", "client"));
			pass = false;
		}

		
		
		if (DataValidator.isNull(request.getParameter("appointmentDate"))) {
			request.setAttribute("appointmentDate", PropertyReader.getValue("error.require", "appointmentDate"));
			pass = false;
		}

		

		if (DataValidator.isNull(request.getParameter("physician"))) {
			request.setAttribute("physician", PropertyReader.getValue("error.require", "physician"));

			pass = false;
		

		}
		if (DataValidator.isNull(request.getParameter("charges"))) {
			request.setAttribute("charges", PropertyReader.getValue("error.require", "charges"));
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
		FollowUpDTO dto = new FollowUpDTO();

		System.out.println(request.getParameter("dob"));

		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setClient(DataUtility.getString(request.getParameter("client")));
		dto.setAppointmentDate(DataUtility.getDate(request.getParameter("appointmentDate")));
		dto.setPhysician(DataUtility.getString(request.getParameter("physician")));
        dto.setCharges(DataUtility.getDouble(request.getParameter("charges")));



		populateBean(dto, request);

		return dto;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String op = DataUtility.getString(request.getParameter("operation"));
		FollowUpModelInt model = ModelFactory.getInstance().getFollowUpModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			FollowUpDTO dto;
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
		FollowUpModelInt model = ModelFactory.getInstance().getFollowUpModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			FollowUpDTO dto = (FollowUpDTO) populateDTO(request);
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

			FollowUpDTO dto = (FollowUpDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.FOLLOWUP_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FOLLOWUP_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FOLLOWUP_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FOLLOWUP_VIEW;
	}



}
