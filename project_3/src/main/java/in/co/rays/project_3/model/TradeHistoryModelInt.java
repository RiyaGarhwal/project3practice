package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.TradeHistoryDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface TradeHistoryModelInt {

	
	public long add(TradeHistoryDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(TradeHistoryDTO dto)throws ApplicationException;
	public void update(TradeHistoryDTO dto)throws ApplicationException,DuplicateRecordException;
	public TradeHistoryDTO findByPK(long pk)throws ApplicationException;
	public TradeHistoryDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(TradeHistoryDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(TradeHistoryDTO dto)throws ApplicationException;
	
}
