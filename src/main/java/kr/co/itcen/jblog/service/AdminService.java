package kr.co.itcen.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.repository.AdminDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;
import kr.co.itcen.jblog.vo.UserVo;

@Service
public class AdminService {
	@Autowired
	AdminDao adminDao;
	
	public BlogVo getBlog(UserVo userVo) {
		BlogVo blogVo = new BlogVo();
		
		blogVo = adminDao.getBlog(userVo);
		
		return blogVo;
	}
	
	private static final String SAVE_PATH = "/uploads/";
	private static final String URL_PREFIX = "/assets/LogoImg/";
	
	public void updateBasic(BlogVo vo, MultipartFile multipartFile) {
		if(multipartFile == null) {
			adminDao.updateBasic(vo);
		} else {
			vo.setLogo(getlogoUrl(multipartFile));
			
			adminDao.updateBasic_logo(vo);
		}
	}
	
	public String getlogoUrl(MultipartFile multipartFile) {
		String url = null;
		try {
			if (multipartFile == null) {
				return url;
			}
			
			String originalFilename = multipartFile.getOriginalFilename();
			String saveFileName = generateSaveFilename(originalFilename.substring(originalFilename.lastIndexOf('.') + 1));
			long fileSize = multipartFile.getSize();

			System.out.println("#################################" + originalFilename);
			System.out.println("#################################" + saveFileName);
			System.out.println("#################################" + fileSize);

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + saveFileName);
			os.write(fileData);
			os.close();
			
			url =  URL_PREFIX + saveFileName;
			System.out.println("url : " + url);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return url;
	}
	
	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}
	
	public List<CategoryVo> getCategory(UserVo userVo) {
		List<CategoryVo> list = adminDao.getCategory(userVo);
		
		return list;
	}

	public void insertPost(PostVo postVo) {
		adminDao.insertPost(postVo);
	}

	public List<CategoryVo> getCateList(String id) {
		List<CategoryVo> list = adminDao.getCateList(id);
		
		return list;
	}

	public void deleteCate(String id, String cate_no) {
		adminDao.deleteCateList(id, cate_no);
	}

	public void insertCate(String id, String cate_name, String cate_desc) {
		adminDao.insertCateList(id, cate_name, cate_desc);
	}
}
