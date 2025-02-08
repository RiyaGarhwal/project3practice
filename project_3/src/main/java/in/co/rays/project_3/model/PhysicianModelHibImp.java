package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.PhysicianDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class PhysicianModelHibImp implements PhysicianModelInt {

	@Override
	public long add(PhysicianDTO dto) throws ApplicationException, DuplicateRecordException {

		PhysicianDTO existDto = null;

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
			throw new ApplicationException("Exception in Physician Add " + e.getMessage());
		} finally {
			session.close();
		}

		return dto.getId();
	}

	@Override
	public void delete(PhysicianDTO dto) throws ApplicationException {

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
			throw new ApplicationException("Exception in Physician Delete" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public void update(PhysicianDTO dto) throws ApplicationException, DuplicateRecordException {

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
			throw new ApplicationException("Exception in Physician update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	@Override
	public PhysicianDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		PhysicianDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (PhysicianDTO) session.get(PhysicianDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Product by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	@Override
	public PhysicianDTO findByLogin(String login) throws ApplicationException {

		Session session = null;
		PhysicianDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PhysicianDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (PhysicianDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting Physician by Login " + e.getMessage());

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
			Criteria criteria = session.createCriteria(PhysicianDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Physician list");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(PhysicianDTO dto, int pageNo, int pageSize) throws ApplicationException {

		Session session = null;
		ArrayList<PhysicianDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PhysicianDTO.class);
			if (dto != null) {
				if (dto.getId()  > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getFullName() != null && dto.getFullName().length() > 0) {
					criteria.add(Restrictions.like("fullName", dto.getFullName() + "%"));
				}

				if (dto.getBirthDate() != null && dto.getBirthDate().getDate() > 0) {
					criteria.add(Restrictions.eq("birthDate", dto.getBirthDate()));
				}
				
				if (dto.getPhone() != null && dto.getPhone().length() > 0) {
					criteria.add(Restrictions.like("phone", dto.getPhone() + "%"));
				}
				if (dto.getSpecialization() != null && dto.getSpecialization().length() > 0) {
					criteria.add(Restrictions.like("specialization", dto.getSpecialization() + "%"));
				}
			}

			System.out.println("searchcalll");
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<PhysicianDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Physician search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List search(PhysicianDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
