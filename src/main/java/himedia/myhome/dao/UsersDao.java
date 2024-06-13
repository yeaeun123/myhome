package himedia.myhome.dao;

import java.util.List;

import himedia.myhome.vo.UserVo;

public interface UsersDao { // DAO Interface
	
	public List<UserVo> getList();  	//목록
	public boolean insert(UserVo userVo);	// INSERT
	public boolean update(UserVo userVo);	// UPDATE
	public boolean delete(Long no); 	// DELETE 삭제하기위해서는 pk만 있어도 됨
	
	public UserVo getUserByIdAndPassword(String email, String password);
	// -> 로그인을 위한 메서드!!! 
}
