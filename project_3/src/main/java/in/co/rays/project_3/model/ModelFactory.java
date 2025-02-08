package in.co.rays.project_3.model;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * ModelFactory decides which model implementation run
 * 
 * @author Mahi Singh
 */
public final class ModelFactory {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.project_3.bundle.system");
	private static final String DATABASE = rb.getString("DATABASE");

	private static ModelFactory mFactory = null;
	private static HashMap modelCache = new HashMap();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {
		if (mFactory == null) {
			mFactory = new ModelFactory();
		}
		return mFactory;
	}

	public MarksheetModelInt getMarksheetModel() {
		MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache.get("marksheetModel");
		if (marksheetModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				marksheetModel = new MarksheetModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				marksheetModel = new MarksheetModelJDBCImpl();
			}
			modelCache.put("marksheetModel", marksheetModel);
		}
		return marksheetModel;
	}
	
	public StaffMemberModelInt getStaffMemberModel() {
		StaffMemberModelInt StaffMemberModel = (StaffMemberModelInt) modelCache.get("StaffMemberModel");
		if (StaffMemberModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				StaffMemberModel = new StaffMemberModelHibImp();
			}
			
			modelCache.put("StaffMemberModel", StaffMemberModel);
		}

		return StaffMemberModel;
	}
	
	public InventoryModelInt getInventoryModel() {
		InventoryModelInt inventoryModel = (InventoryModelInt) modelCache.get("inventoryModel");
		if (inventoryModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				inventoryModel = new InventoryModelHibImp();
			}
			
			modelCache.put("inventoryModel", inventoryModel);
		}

		return inventoryModel;
	}
	
	public OrderModelInt getOrderModel() {
		OrderModelInt OrderModel = (OrderModelInt) modelCache.get("OrderModel");
		if (OrderModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				OrderModel = new OrderModelHibImp();
			}
			
			modelCache.put("OrderModel", OrderModel);
		}

		return OrderModel;
	}
	
	public FollowUpModelInt getFollowUpModel() {
		FollowUpModelInt followUpModel = (FollowUpModelInt) modelCache.get("followUpModel");
		if (followUpModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				followUpModel = new FollowUpModelHibImp();
			}
			
			modelCache.put("followUpModel", followUpModel);
		}

		return followUpModel;
	}

	
	public PhysicianModelInt getPhysicianModel() {
		PhysicianModelInt physicianModel = (PhysicianModelInt) modelCache.get("physicianModel");
		if (physicianModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				physicianModel = new PhysicianModelHibImp();
			}
			
			modelCache.put("physicianModel", physicianModel);
		}

		return physicianModel;
	}
	
	public TransportationModelInt getTransportationModel() {
		TransportationModelInt transportationModel = (TransportationModelInt) modelCache.get("transportationModel");
		if (transportationModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				transportationModel = new TransportationModelHibImp();
			}
			
			modelCache.put("transportationModel", transportationModel);
		}

		return transportationModel;
	}

	public CollegeModelInt getCollegeModel() {
		CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");
		if (collegeModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				collegeModel = new CollegeModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				collegeModel = new CollegeModelJDBCImpl();
			}
			modelCache.put("collegeModel", collegeModel);
		}
		return collegeModel;
	}

	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
		if (roleModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				roleModel = new RoleModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				roleModel = new RoleModelJDBCImpl();
			}
			modelCache.put("roleModel", roleModel);
		}
		return roleModel;
	}

	public UserModelInt getUserModel() {

		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");
		if (userModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				userModel = new UserModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				userModel = new UserModelJDBCImpl();
			}
			modelCache.put("userModel", userModel);
		}

		return userModel;
	}

	public StudentModelInt getStudentModel() {
		StudentModelInt studentModel = (StudentModelInt) modelCache.get("studentModel");
		if (studentModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				studentModel = new StudentModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				studentModel = new StudentModelJDBCImpl();
			}
			modelCache.put("studentModel", studentModel);
		}

		return studentModel;
	}

	public CourseModelInt getCourseModel() {
		CourseModelInt courseModel = (CourseModelInt) modelCache.get("courseModel");
		if (courseModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				courseModel = new CourseModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				courseModel = new CourseModelJDBCImpl();
			}
			modelCache.put("courseModel", courseModel);
		}

		return courseModel;
	}

	public TimetableModelInt getTimetableModel() {

		TimetableModelInt timetableModel = (TimetableModelInt) modelCache.get("timetableModel");

		if (timetableModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				timetableModel = new TimetableModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				timetableModel = new TimetableModelJDBCImpl();
			}
			modelCache.put("timetableModel", timetableModel);
		}

		return timetableModel;
	}

	public SubjectModelInt getSubjectModel() {
		SubjectModelInt subjectModel = (SubjectModelInt) modelCache.get("subjectModel");
		if (subjectModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				subjectModel = new SubjectModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				subjectModel = new SubjectModelJDBCImpl();
			}
			modelCache.put("subjectModel", subjectModel);
		}

		return subjectModel;
	}

	public FacultyModelInt getFacultyModel() {
		FacultyModelInt facultyModel = (FacultyModelInt) modelCache.get("facultyModel");
		if (facultyModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				facultyModel = new FacultyModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				facultyModel = new FacultyModelJDBCImpl();
			}
			modelCache.put("facultyModel", facultyModel);
		}

		return facultyModel;
	}

	
		
	public TransactionModelInt getTransactionModel() {

		TransactionModelInt transactionModel = (TransactionModelInt) modelCache.get("transactionModel");
		if (transactionModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				transactionModel = new TransactionModelHibImp();
			}
			
			modelCache.put("transactionModel", transactionModel);
		}

		return transactionModel;
	}

	public PurchaseOrderModelInt getPurchaseOrderModel (){

	PurchaseOrderModelInt purchaseOrderModel = (PurchaseOrderModelInt) modelCache.get("purchaseOrderModel");
		if (purchaseOrderModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				purchaseOrderModel = new PurchaseOrderModelHibImp();
			}
			
			modelCache.put("transactionModel", purchaseOrderModel);
		}

		return purchaseOrderModel;
	}
	
	public StockPurchaseModelInt getStockPurchaseModel() {

		StockPurchaseModelInt StockPurchaseModel = (StockPurchaseModelInt) modelCache.get("StockPurchaseModel");
		if (StockPurchaseModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				StockPurchaseModel = new StockPurchaseModelHibImp();
			}
			modelCache.put("StockPurchaseModel", StockPurchaseModel);

		}

		return StockPurchaseModel;
	}



	
	public AssetModelInt getAssetModel() {

		AssetModelInt AssetModel = (AssetModelInt) modelCache.get("AssetModel");
		if (AssetModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				AssetModel = new AssetModelHibImpl();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("AssetModel", AssetModel);
		}

		return AssetModel;
	}
	public SupplierModelInt getSupplierModel() {

		SupplierModelInt supplierModel = (SupplierModelInt) modelCache.get("SupplierModel");
		if (supplierModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				supplierModel = new SupplierModelHibImp();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("supplierModel", supplierModel);
		}

		return supplierModel;
	}
	
	public CartOverViewModelInt getCartOverViewModel() {

		CartOverViewModelInt CartOverViewModel = (CartOverViewModelInt) modelCache.get("CartOverViewModel");
		if (CartOverViewModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				CartOverViewModel = new CartOverViewModelHibImp() {
				};
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { bankModel = new UserModelJDBCImpl(); }
			 */
			modelCache.put("CartOverViewModel", CartOverViewModel);
		}

		return CartOverViewModel;
	}
	
	
	



}
