package in.co.rays.project_3.controller;

public interface ORSView {
	public String APP_CONTEXT = "/project_3";

	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView404.jsp";
	public String Employee_VIEW = PAGE_FOLDER + "/EmployeeView.jsp";
	public String Employee_LIST_VIEW = PAGE_FOLDER + "/EmployeeListView.jsp";
	
	public String ASSET_VIEW = PAGE_FOLDER + "/AssetView.jsp";
	public String ASSET_LIST_VIEW = PAGE_FOLDER + "/AssetListView.jsp";
	// -------------------------------------------------------------------------------Supplier
	public String SUPPLIER_VIEW = PAGE_FOLDER + "/SupplierView.jsp";
	public String SUPPLIER_CTL = APP_CONTEXT + "/ctl/SupplierCtl";
	public String SUPPLIER_LIST_CTL = APP_CONTEXT + "/ctl/SupplierListCtl";
	public String SUPPLIER_LIST_VIEW = PAGE_FOLDER + "/SupplierListView.jsp";
	
	public String STAFFMEMBER_VIEW = PAGE_FOLDER + "/StaffMemberView.jsp";
	public String STAFFMEMBER_CTL = APP_CONTEXT + "/ctl/StaffMemberCtl";
	public String STAFFMEMBER_LIST_CTL = APP_CONTEXT + "/ctl/StaffMemberListCtl";
	public String STAFFMEMBER_LIST_VIEW = PAGE_FOLDER + "/StaffMemberListView.jsp";
	
	
	public String ORDER_VIEW = PAGE_FOLDER + "/OrderView.jsp";
	public String ORDER_CTL = APP_CONTEXT + "/ctl/OrderCtl";
	public String ORDER_LIST_CTL = APP_CONTEXT + "/ctl/OrderListCtl";
	public String ORDER_LIST_VIEW = PAGE_FOLDER + "/OrderListView.jsp";
	
	public String INVENTORY_VIEW = PAGE_FOLDER + "/InventoryView.jsp";
	public String INVENTORY_CTL = APP_CONTEXT + "/ctl/InventoryCtl";
	public String INVENTORY_LIST_CTL = APP_CONTEXT + "/ctl/InventoryListCtl";
	public String INVENTORY_LIST_VIEW = PAGE_FOLDER + "/InventoryListView.jsp";
	
	public String TRANSPORTATION_VIEW = PAGE_FOLDER + "/TransportationView.jsp";
	public String TRANSPORTATION_CTL = APP_CONTEXT + "/ctl/TransportationCtl";
	public String TRANSPORTATION_LIST_CTL = APP_CONTEXT + "/ctl/TransportationListCtl";
	public String TRANSPORTATION_LIST_VIEW = PAGE_FOLDER + "/TransportationListView.jsp";
	
	public String FOLLOWUP_VIEW = PAGE_FOLDER + "/FollowUpView.jsp";
	public String FOLLOWUP_CTL = APP_CONTEXT + "/ctl/FollowUpCtl";
	public String FOLLOWUP_LIST_CTL = APP_CONTEXT + "/ctl/FollowUpListCtl";
	public String FOLLOWUP_LIST_VIEW = PAGE_FOLDER + "/FollowUpListView.jsp";
	
	public String PHYSICIAN_VIEW = PAGE_FOLDER + "/PhysicianView.jsp";
	public String PHYSICIAN_CTL = APP_CONTEXT + "/ctl/PhysicianCtl";
	public String PHYSICIAN_LIST_CTL = APP_CONTEXT + "/ctl/PhysicianListCtl";
	public String PHYSICIAN_LIST_VIEW = PAGE_FOLDER + "/PhysicianListView.jsp";
	
	

	
	
	
	public String CARTOVERVIEW_VIEW = PAGE_FOLDER + "/CartOverViewView.jsp";
	public String CARTOVERVIEW_CTL = APP_CONTEXT + "/ctl/CartOverViewCtl";
	public String CARTOVERVIEW_LIST_CTL = APP_CONTEXT + "/ctl/CartOverViewListCtl";
	public String CARTOVERVIEW_LIST_VIEW = PAGE_FOLDER + "/CartOverViewListView.jsp";
	// ------------------------------------------------------------------------------StockAnalysisView

		public String STOCKPURCHASE_VIEW = PAGE_FOLDER + "/StockPurchaseView.jsp";
		public String STOCKPURCHASE_CTL = APP_CONTEXT + "/ctl/StockPurchaseCtl";
		public String STOCKPURCHASE_LIST_CTL = APP_CONTEXT + "/ctl/StockPurchaseListCtl";
		public String STOCKPURCHASE_LIST_VIEW = PAGE_FOLDER + "/StockPurchaseListView.jsp";
		

		// ------------------------------------------------------------------------------Transaction
	
	public String TRANSACTION_VIEW = PAGE_FOLDER + "/TransactionView.jsp";
	public String TRANSACTION_LIST_VIEW = PAGE_FOLDER + "/TransactionListView.jsp";
	public String TRANSACTION_CTL = APP_CONTEXT + "/ctl/TransactionCtl";
	public String TRANSACTION_LIST_CTL = APP_CONTEXT + "/ctl/TransactionListCtl";
	
	// ------------------------------------------------------------------------------Purchaseorder
	public String PURCHASEORDER_VIEW = PAGE_FOLDER + "/PurchaseOrderView.jsp";
	public String PURCHASEORDER_LIST_VIEW = PAGE_FOLDER + "/PurchaseOrderListView.jsp";
	public String PURCHASEORDER_CTL = APP_CONTEXT + "/ctl/PurchaseOrderCtl";
	public String PURCHASEORDER_LIST_CTL = APP_CONTEXT + "/ctl/PurchaseOrderListCtl";


	public String BANK_VIEW = PAGE_FOLDER + "/BankView.jsp";
	public String BANK_LIST_VIEW = PAGE_FOLDER + "/BankListView.jsp";
	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimeTableView.jsp";
	public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimeTableListView.jsp";
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
	
	
	public String ERROR_CTL = APP_CONTEXT +"/ErrorCtl";
	public String Employee_CTL = APP_CONTEXT + "/ctl/EmployeeCtl";
	public String Employee_LIST_CTL = APP_CONTEXT + "/ctl/EmployeeListCtl";
	
	
	public String ASSET_CTL = APP_CONTEXT + "/ctl/AssetCtl";
	public String ASSET_LIST_CTL = APP_CONTEXT + "/ctl/AssetListCtl";
	

	
	public String BANK_CTL = APP_CONTEXT + "/ctl/BankCtl";
	public String BANK_LIST_CTL = APP_CONTEXT + "/ctl/BankListCtl";
	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";
	
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";
	public String TIMETABLE_CTL = APP_CONTEXT + "/ctl/TimeTableCtl";
	public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/ctl/TimeTableListCtl";
	
	
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";
}
