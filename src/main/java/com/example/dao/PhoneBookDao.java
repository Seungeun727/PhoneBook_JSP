package com.example.dao;

import java.util.List;

import com.example.vo.PhoneBookVo;

public interface PhoneBookDao {
	
	public List<PhoneBookVo> getList();
	public List<PhoneBookVo>search(String keyword);
	public int insertList(PhoneBookVo vo);
	public int updateList(PhoneBookVo vo);
	public int deleteList(Long id);
	
	
}
