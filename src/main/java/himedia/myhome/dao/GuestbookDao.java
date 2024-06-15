package himedia.myhome.dao;


import java.util.List;

import himedia.myhome.vo.GuestVo;


public interface GuestbookDao {
	List<GuestVo> getList();
	GuestVo get(Long no);
	boolean insert(	GuestVo vo);
//	public List<GuestVo> getList(); 	// emaillist table SELECT
//	public boolean insert(GuestVo vo);	// emaillist table INSERT
//	public boolean delete(int no, String password);
	boolean delete(GuestVo vo);

}