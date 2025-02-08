package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.TransportationDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface TransportationModelInt {
	
	public long add(TransportationDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(TransportationDTO dto)throws ApplicationException;
	public void update(TransportationDTO dto)throws ApplicationException,DuplicateRecordException;
	public TransportationDTO findByPK(long pk)throws ApplicationException;
	public TransportationDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(TransportationDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(TransportationDTO dto)throws ApplicationException;

}
