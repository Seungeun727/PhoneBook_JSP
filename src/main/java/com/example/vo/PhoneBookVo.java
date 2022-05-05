package com.example.vo;

public class PhoneBookVo {
	// 필드
	private Long id;
	private String name;
	private String hp;
	private String tel;
	
	// Getter, Setter 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "PhoneBookVo [id=" + id + ", name=" + name + ", hp=" + hp + ", tel=" + tel + "]";
	}
}