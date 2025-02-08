package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto. SupplierDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class SupplierModelHibImp implements  SupplierModelInt {
	
	@Override
	public long add( SupplierDTO dto) throws ApplicationException, DuplicateRecordException {
		
		  SupplierDTO existDto = null;
			
			Session session = HibDataSource.getSession();
			Transaction tx = null;
			try {

				tx = session.beginTransaction();

				session.save(dto);

				dto.getId();
				tx.commit();
			} catch (HibernateException e) {
				e.printStackTrace();
				if (tx != null) {
					tx.rollback();

				}
				throw new ApplicationException("Exception in  Supplier Add " + e.getMessage());
			} finally {
				session.close();
			}


		return dto.getId();
	}

	@Override
	public void delete( SupplierDTO dto) throws ApplicationException {

		
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in  Supplier Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update( SupplierDTO dto) throws ApplicationException, DuplicateRecordException {
		
		
		Session session = null;
		
		/*
		 * Transaction tx = null;  SupplierDTO exesistDto = findByLogin(dto.getLogin());
		 * 
		 * if (exesistDto != null && exesistDto.getId() != dto.getId()) { throw new
		 * DuplicateRecordException("Login id already exist"); }
		 * 
		 */		  Transaction tx = null;
		 

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in  Supplier update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public  SupplierDTO findByPK(long pk) throws ApplicationException {
		
		
		Session session = null;
		 SupplierDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = ( SupplierDTO) session.get( SupplierDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public  SupplierDTO findByLogin(String login) throws ApplicationException {
		
		
		
		Session session = null;
		 SupplierDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria( SupplierDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = ( SupplierDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting  Supplier by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {
		
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria( SupplierDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Banks list");
		} finally {
			session.close();
		}

		return list;
	}

	/*
	 * @Override public List list(int pageNo, int pageSize) throws
	 * ApplicationException { // TODO Auto-generated method stub return null; }
	 */
	@Override
	public List search( SupplierDTO dto, int pageNo, int pageSize) throws ApplicationException {
		
		Session session = null;
		ArrayList< SupplierDTO> list = null;
		try {
			session = HibDataSource.getSession();
			System.out.println("---------------------------------");
			Criteria criteria = session.createCriteria( SupplierDTO.class);
			if (dto != null) {
				
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				
				  
				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("Name", dto.getName() + "%"));
				}

				
				  
				if (dto.getCategory() != null && dto.getCategory().length() > 0) {
					criteria.add(Restrictions.like("category", dto.getCategory() + "%"));
				}
				
				  if (dto.getRegistrationdate() != null && dto.getRegistrationdate().getDate() > 0) {
						criteria.add(Restrictions.eq("registrationdate", dto.getRegistrationdate()));
					}
				  if ( dto.getPaymentterm() > 0) {
						criteria.add(Restrictions.eq("paymentterm", dto.getPaymentterm()));
					}
			  
					
					
			}
					
					if (pageSize > 0) {
						pageNo = (pageNo - 1) * pageSize;
						criteria.setFirstResult(pageNo);
						criteria.setMaxResults(pageSize);
					}
					list = (ArrayList< SupplierDTO>) criteria.list();
				} catch (HibernateException e) {
					throw new ApplicationException("Exception in  Supplier search");
				} finally {
					session.close();
				}

		
		return list;
	}

	@Override
	public List search( SupplierDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}

	

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}


}
