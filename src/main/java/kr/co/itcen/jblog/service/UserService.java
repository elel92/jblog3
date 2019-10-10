package kr.co.itcen.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.UserDao;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public void join(UserVo userVo) {
		userDao.insert(userVo);
	}

	public UserVo login(UserVo userVo) {
		UserVo authUser = userDao.get(userVo);
		
		return authUser;
	}
}
