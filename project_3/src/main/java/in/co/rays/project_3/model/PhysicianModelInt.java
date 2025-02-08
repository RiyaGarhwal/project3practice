package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.PhysicianDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface PhysicianModelInt {
	
	public long add(PhysicianDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(PhysicianDTO dto)throws ApplicationException;
	public void update(PhysicianDTO dto)throws ApplicationException,DuplicateRecordException;
	public PhysicianDTO findByPK(long pk)throws ApplicationException;
	public PhysicianDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(PhysicianDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(PhysicianDTO dto)throws ApplicationException;

}
