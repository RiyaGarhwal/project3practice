package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.TransportationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class TransportationModelHibImp implements TransportationModelInt {

	@Override
	public long add(TransportationDTO dto) throws ApplicationException, DuplicateRecordException {

		TransportationDTO existDto = null;

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
			throw new ApplicationException("Exception in Transportation Add " + e.getMessage());
		} finally {
			session.close();
		}

		return dto.getId();
	}

	@Override
	public void delete(TransportationDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in Transportation Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(TransportationDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Transportation update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public TransportationDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		TransportationDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (TransportationDTO) session.get(TransportationDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Product by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public TransportationDTO findByLogin(String login) throws ApplicationException {

		Session session = null;
		TransportationDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TransportationDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (TransportationDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting Transportation by Login " + e.getMessage());

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
			Criteria criteria = session.createCriteria(TransportationDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Transportation list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(TransportationDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<TransportationDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(TransportationDTO.class);
			if (dto != null) {
				if (dto.getId()  > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}

				if (dto.getDate() != null && dto.getDate().getDate() > 0) {
					criteria.add(Restrictions.eq("date", dto.getDate()));
				}
				
				if (dto.getMode() != null && dto.getMode().length() > 0) {
					criteria.add(Restrictions.like("mode", dto.getMode() + "%"));
				}
				if (dto.getDescription() != null && dto.getDescription().length() > 0) {
					criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
				}
				
				if (dto.getCost()  > 0) {
					criteria.add(Restrictions.eq("cost", dto.getCost()));
				}
			}

			System.out.println("searchcalll");
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<TransportationDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in transportation search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(TransportationDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
