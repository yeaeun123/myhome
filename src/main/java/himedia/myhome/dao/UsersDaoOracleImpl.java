package himedia.myhome.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import himedia.myhome.vo.UserVo;

public class UsersDaoOracleImpl implements UsersDao {
	private String dbuser;
	private String dbpass;
	
	public UsersDaoOracleImpl(String dbuser, String dbpass) {
		this.dbuser = dbuser;
		this.dbpass = dbpass;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1522:xe";
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public List<UserVo> getList(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<UserVo> list = new ArrayList();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM users ORDER BY created_at DESC";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Long no = rs.getLong("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String gender = rs.getString("gender");
				Date createdAt = rs.getDate("created_at");
				
				UserVo vo = new UserVo(no, name, password, email, gender, createdAt);
				
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO users (no, name, password, email, gender) values (seq_users_pk.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getGender());
			
			insertedCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 1 == insertedCount;
	}

	@Override
	public boolean update(UserVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long no) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserVo getUserByIdAndPassword(String email, String password) {
		  	UserVo user = null;
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        try {
				String dburl = "jdbc:oracle:thin:@localhost:1522:xe";
	            conn = DriverManager.getConnection(dburl, dbuser, dbpass);
	            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, email);
	            pstmt.setString(2, password);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                user = new UserVo();
	                user.setNo(rs.getLong("no"));
	                user.setEmail(rs.getString("email"));
	                user.setPassword(rs.getString("password"));
	                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return user;

	
	}
}
