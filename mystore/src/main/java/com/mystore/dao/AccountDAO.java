package com.mystore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.core.dao.AbstractDAO;
import com.core.exception.BusinessException;
import com.core.util.ConnectionManager;
import com.mystore.model.Account;

public class AccountDAO implements AbstractDAO<Account> {

	final static Logger logger = Logger.getLogger(AccountDAO.class);
	
	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static PreparedStatement ps = null;
	private static Statement stmt = null;

	@Override
	public void add(Account account) throws BusinessException{

		// TODO: Encrypt the password
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement(
					"insert into account(name, phone, address, email, password, role)values(?,?,?,?,?,?)");
			ps.setString(1, account.getName());
			ps.setString(2, account.getPhone());
			ps.setString(3, account.getAddress());
			ps.setString(4, account.getEmail());
			ps.setString(5, account.getPassword());
			ps.setString(7, account.getRole());
			ps.executeUpdate();

			logger.info("Your email is " + account.getEmail());

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
		}

	}

	@Override
	public Account get(int accountId) throws BusinessException{
		Account account = new Account();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from account where accountId=?");
			ps.setInt(1, accountId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				account.setAccountId(rs.getInt("accountId"));
				account.setEmail(rs.getString("email"));
				account.setName(rs.getString("name"));
				account.setAddress(rs.getString("address"));
				account.setPhone(rs.getString("phone"));
				account.setPassword(rs.getString("password"));
				account.setRole(rs.getString("role"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
		}
		return account;
	}

	@Override
	public void update(Account account) throws BusinessException{
		try {
			String name = account.getName();
			String email = account.getEmail();
			String address = account.getAddress();
			String phone = account.getPhone();			
			String role = account.getRole();
			String searchQuery = "";

			if (role != null) {
				searchQuery = "UPDATE account SET name ='" + name + "', address='" + address + "', phone='" + phone
						+ "', role='" + role + "' WHERE email= '" + email + "'";
			} else {
				searchQuery = "UPDATE account SET name ='" + name + "', address='" + address + "', phone='" + phone
						+ "' WHERE email= '" + email + "'";
			}
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			stmt.executeUpdate(searchQuery);
			logger.info(searchQuery);

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
		}

	}

	@Override
	public void delete(int accountId) throws BusinessException{
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("delete from account where accountId=?");
			ps.setInt(1, accountId);
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
		}
	}

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Account login(Account bean) throws BusinessException {

		try {
			String email = bean.getEmail();
			String password = bean.getPassword();

			/*
			 * //convert the password to MD5 MessageDigest md =
			 * MessageDigest.getInstance("MD5"); md.update(password.getBytes());
			 * 
			 * byte byteData[] = md.digest();
			 * 
			 * //convert the byte to hex format StringBuffer sb = new StringBuffer(); for
			 * (int i = 0; i < byteData.length; i++) {
			 * sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1)); }
			 * 
			 * String pass = sb.toString();
			 */
			String searchQuery = "select * from account where email='" + email + "' AND password='" + password + "'";

			logger.info("Your email is " + email);
			logger.info("Your password is " + password);
			logger.info("Query: " + searchQuery);

			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if user exists set the isValid variable to true
			if (more) {
				email = rs.getString("email");
				logger.info("Welcome " + email);
				bean.setEmail(email);
				bean.setValid(true);
			}
			// if user does not exist set the isValid variable to false
			else if (!more) {
				logger.info("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
		}
		return bean;
	}

	// get user by email
	public Account getAccountByEmail(String email) throws BusinessException{
		Account account = new Account();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from account where email=?");

			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				account.setAccountId(rs.getInt("accountId"));
				account.setEmail(rs.getString("email"));
				account.setName(rs.getString("name"));
				account.setAddress(rs.getString("address"));
				account.setPhone(rs.getString("phone"));
				account.setPassword(rs.getString("password"));
				account.setRole(rs.getString("role"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
		}

		return account;
	}

	// getallaccount
	public Account getAccount() throws BusinessException{
		Account account = new Account();
		try {
			currentCon = ConnectionManager.getConnection();
			ps = currentCon.prepareStatement("select * from account");

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				account.setAccountId(rs.getInt("accountId"));
				account.setName(rs.getString("name"));
				account.setEmail(rs.getString("email"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
				}
				ps = null;
			}
		}

		return account;
	}

	public Account getAccount(Account bean) throws BusinessException{

		String email = bean.getEmail();
		String searchQuery = "select * from account where email='" + email + "'";

		try {
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			if (more) {
				email = rs.getString("email");
				bean.setEmail(email);
				bean.setValid(true);
			} else if (!more) {
				logger.info("Sorry");
				bean.setValid(false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BusinessException(ex.getMessage(), ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}
		}

		return bean;
	}

}
