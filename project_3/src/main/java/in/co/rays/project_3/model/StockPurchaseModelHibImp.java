package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto. StockPurchaseDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class StockPurchaseModelHibImp implements  StockPurchaseModelInt {
	
	@Override
	public long add( StockPurchaseDTO dto) throws ApplicationException, DuplicateRecordException {
		
		  StockPurchaseDTO existDto = null;
			
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
				throw new ApplicationException("Exception in  StockPurchase Add " + e.getMessage());
			} finally {
				session.close();
			}


		return dto.getId();
	}

	@Override
	public void delete( StockPurchaseDTO dto) throws ApplicationException {

		
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
			throw new ApplicationException("Exception in  StockPurchase Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update( StockPurchaseDTO dto) throws ApplicationException, DuplicateRecordException {
		
		
		Session session = null;
		
		/*
		 * Transaction tx = null;  StockPurchaseDTO exesistDto = findByLogin(dto.getLogin());
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
			throw new ApplicationException("Exception in  StockPurchase update" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public  StockPurchaseDTO findByPK(long pk) throws ApplicationException {
		
		
		Session session = null;
		 StockPurchaseDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = ( StockPurchaseDTO) session.get( StockPurchaseDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public  StockPurchaseDTO findByLogin(String login) throws ApplicationException {
		
		
		
		Session session = null;
		 StockPurchaseDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria( StockPurchaseDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = ( StockPurchaseDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting  StockPurchase by Login " + e.getMessage());

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
			Criteria criteria = session.createCriteria( StockPurchaseDTO.class);
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
	public List search( StockPurchaseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		
		Session session = null;
		ArrayList< StockPurchaseDTO> list = null;
		try {
			session = HibDataSource.getSession();
			System.out.println("---------------------------------");
			Criteria criteria = session.createCriteria( StockPurchaseDTO.class);
			if (dto != null) {
				
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				
				  
				if ( dto.getQuantity() > 0) {
					criteria.add(Restrictions.eq("quantity", dto.getQuantity()));
				}
				
				  
				 if ( dto.getPurchasePrice()> 0) {
					  criteria.add(Restrictions.eq("purchasePrice", dto.getPurchasePrice() ));
					  }
				
				  if (dto.getPurchaseDate() != null && dto.getPurchaseDate().getDate() > 0) {
						criteria.add(Restrictions.eq("purchaseDate", dto.getPurchaseDate()));
					}
				  if (dto.getOrderType() != null && dto.getOrderType().length() > 0) {
					  criteria.add(Restrictions.like("orderType", dto.getOrderType() + "%"));
					  }
			  
					
					
			}
					
					if (pageSize > 0) {
						pageNo = (pageNo - 1) * pageSize;
						criteria.setFirstResult(pageNo);
						criteria.setMaxResults(pageSize);
					}
					list = (ArrayList< StockPurchaseDTO>) criteria.list();
				} catch (HibernateException e) {
					throw new ApplicationException("Exception in  StockPurchase search");
				} finally {
					session.close();
				}

		
		return list;
	}

	@Override
	public List search( StockPurchaseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto,0,0);
	}

	@Override
	public List getRoles( StockPurchaseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0,0);
	}


}
