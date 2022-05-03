package com.exmaple.dao;

import java.util.List;

import com.exmaple.vo.PhoneBookVo;

public interface PhoneBookDao {
	
	public List<PhoneBookVo> getlist();
	public List<PhoneBookVo>search(String keyword);
	public int insertList(PhoneBookVo vo);
	public int updateList(PhoneBookVo vo);
	public int deleteList(Long id);
	
	
}
