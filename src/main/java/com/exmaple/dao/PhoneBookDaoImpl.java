package com.exmaple.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exmaple.vo.PhoneBookVo;


public class PhoneBookDaoImpl implements PhoneBookDao {
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Connection 얻음
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "C##BITUSER", "bituser");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 실패");
		}
		return conn;
	}
	
	// 1.전체 주소록
	@Override
	public List<PhoneBookVo> getlist() {
		List<PhoneBookVo> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = " SELECT id, name, hp, tel"
						+ " FROM Address_book ORDER BY id";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				// 1.데이터 받음
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				// 2.VO 객체 생성
				PhoneBookVo vo = new PhoneBookVo();
				vo.setId(id);
				vo.setName(name);
				vo.setHp(hp);
				vo.setTel(tel);
				
				// 3.리스트 추가
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	// 2.검색 개선하기
	@Override
	public List<PhoneBookVo> search(String keyword) {
		List<PhoneBookVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "SELECT * FROM Address_book WHERE "
						+ keyword +" LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+keyword+"%");
		
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				// 1. 데이터 받음
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("Tel");
				
				// 2. VO 객체 생성
				PhoneBookVo vo = new PhoneBookVo();
				vo.setId(id);
				vo.setName(name);
				vo.setHp(hp);
				vo.setTel(tel);
				
				// 3. 리스트 추가함
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 3.주소록 추가
	@Override
	public int insertList(PhoneBookVo vo) {
		int insertCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO Address_book "
					+ " VALUES(seq_Address_book.NEXTVAL,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());

			insertCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return insertCount;
	}
	
	// 4.주소록 수정
	@Override
	public int updateList(PhoneBookVo vo) {
		int updateCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "UPDATE Address_book SET name =?, hp=?, tel=? WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			// 파라미터 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			pstmt.setLong(4, vo.getId());
			
			updateCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return updateCount;
	}

	// 5.주소록 삭제
	@Override
	public int deleteList(Long id) {
		int deleteCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM Address_book WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			deleteCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return deleteCount;
	}
}