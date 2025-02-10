package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.TradeHistoryDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class TradeHistoryModelHibImp implements TradeHistoryModelInt {

	@Override
	public long add(TradeHistoryDTO dto) throws ApplicationException, DuplicateRecordException {

		TradeHistoryDTO existDto = null;

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
			throw new ApplicationException("Exception in Order Add " + e.getMessage());
		} finally {
			session.close();
		}

		return dto.getId();
	}

	@Override
	public void delete(TradeHistoryDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in Order Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(TradeHistoryDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = null;

		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Order update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public TradeHistoryDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		TradeHistoryDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (TradeHistoryDTO) session.get(TradeHistoryDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Product by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public TradeHistoryDTO findByLogin(String login) throws ApplicationException {

		Session session = null;
		TradeHistoryDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TradeHistoryDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (TradeHistoryDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting Order by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TradeHistoryDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Order list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(TradeHistoryDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<TradeHistoryDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TradeHistoryDTO.class);
			if (dto != null) {
				if (dto.getId()  > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getUserId() != null && dto.getUserId().length() > 0) {
					criteria.add(Restrictions.like("userId", dto.getUserId() + "%"));
				}

				if (dto.getStartDate() != null && dto.getStartDate().getDate() > 0) {
					criteria.add(Restrictions.eq("startDate", dto.getStartDate()));
				}
				
				if (dto.getEndDate() != null && dto.getEndDate().getDate() > 0) {
					criteria.add(Restrictions.eq("endDate", dto.getEndDate()));
				}
				
				if (dto.getTransactionType() != null && dto.getTransactionType().length() > 0) {
					criteria.add(Restrictions.like("transactionType", dto.getTransactionType() + "%"));
				}
				
			}

			System.out.println("searchcalll");
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<TradeHistoryDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Order search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(TradeHistoryDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
