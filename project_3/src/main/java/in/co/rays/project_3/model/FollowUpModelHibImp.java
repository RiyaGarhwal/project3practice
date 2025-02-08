package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.FollowUpDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class FollowUpModelHibImp implements FollowUpModelInt {

	@Override
	public long add(FollowUpDTO dto) throws ApplicationException, DuplicateRecordException {

		FollowUpDTO existDto = null;

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
			throw new ApplicationException("Exception in FollowUp Add " + e.getMessage());
		} finally {
			session.close();
		}

		return dto.getId();
	}

	@Override
	public void delete(FollowUpDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in FollowUp Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(FollowUpDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in FollowUp update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public FollowUpDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		FollowUpDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (FollowUpDTO) session.get(FollowUpDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Product by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public FollowUpDTO findByLogin(String login) throws ApplicationException {

		Session session = null;
		FollowUpDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FollowUpDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (FollowUpDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting FollowUp by Login " + e.getMessage());

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
			Criteria criteria = session.createCriteria(FollowUpDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  FollowUp list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(FollowUpDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<FollowUpDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FollowUpDTO.class);
			if (dto != null) {
				if (dto.getId()  > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getClient() != null && dto.getClient().length() > 0) {
					criteria.add(Restrictions.like("client", dto.getClient() + "%"));
				}

				if (dto.getAppointmentDate() != null && dto.getAppointmentDate().getDate() > 0) {
					criteria.add(Restrictions.eq("appointmentDate", dto.getAppointmentDate()));
				}
				
				if (dto.getPhysician() != null && dto.getPhysician().length() > 0) {
					criteria.add(Restrictions.like("physician", dto.getPhysician() + "%"));
				}
				if (dto.getCharges()  > 0) {
					criteria.add(Restrictions.eq("charges", dto.getCharges()));
				}
			}

			System.out.println("searchcalll");
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<FollowUpDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in FollowUp search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(FollowUpDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
