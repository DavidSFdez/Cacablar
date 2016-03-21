package com.sdi.persistence.impl;

import java.sql.*;

import com.sdi.model.User;
import com.sdi.model.UserStatus;
import com.sdi.persistence.UsersDao;
import com.sdi.persistence.exception.*;

/**
 * Implementaci��n de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podr��a ser cualquier otra tecnologia de
 * persistencia, por ejemplo, la que veremos m��s adelante JPA (mapeador de
 * objetos a relacional)
 * 
 * @author alb
 * 
 */
public class UserJdbcDAO implements UsersDao {

    // En una implemenntación más sofisticada estas constantes habría
    // que sacarlas a un sistema de configuración:
    // xml, properties, descriptores de despliege, etc
    String SQL_DRV = "org.hsqldb.jdbcDriver";
    String SQL_URL = "jdbc\\:hsqldb\\:hsql\\://localhost";

    /*
     * public List<Alumno> getAlumnos() { PreparedStatement ps = null; ResultSet
     * rs = null; Connection con = null;
     * 
     * List<Alumno> alumnos = new ArrayList<Alumno>();
     * 
     * try {
     * 
     * // Obtenemos la conexión a la base de datos. Class.forName(SQL_DRV); con
     * = DriverManager.getConnection(SQL_URL, "sa", ""); ps =
     * con.prepareStatement("select * from alumno"); rs = ps.executeQuery();
     * 
     * while (rs.next()) { Alumno alumno = new Alumno();
     * alumno.setId(rs.getLong("ID")); alumno.setNombre(rs.getString("NOMBRE"));
     * alumno.setApellidos(rs.getString("APELLIDOS"));
     * alumno.setEmail(rs.getString("EMAIL"));
     * alumno.setIduser(rs.getString("IDUSER"));
     * 
     * alumnos.add(alumno); } } catch (ClassNotFoundException e) {
     * e.printStackTrace(); throw new PersistenceException("Driver not found",
     * e); } catch (SQLException e) { e.printStackTrace(); throw new
     * PersistenceException("Invalid SQL or database schema", e); } finally { if
     * (rs != null) {try{ rs.close(); } catch (Exception ex){}}; if (ps != null)
     * {try{ ps.close(); } catch (Exception ex){}}; if (con != null) {try{
     * con.close(); } catch (Exception ex){}}; }
     * 
     * return alumnos; }
     */
    public void delete(Long id) throws NotPersistedException {
	PreparedStatement ps = null;
	Connection con = null;
	int rows = 0;

	try {
	    // Obtenemos la conexi��n a la base de datos.
	    Class.forName(SQL_DRV);
	    con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement("delete from tusers where id = ?");

	    ps.setLong(1, id);

	    rows = ps.executeUpdate();
	    if (rows != 1) {
		throw new NotPersistedException("User " + id + " not found");
	    }

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Driver not found", e);
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (con != null) {
		try {
		    con.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	}
    }

    public User findById(Long id) {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;
	User user = null;

	try {
	    // Obtenemos la conexi��n a la base de datos.
	    Class.forName(SQL_DRV);
	    con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement("select * from tusers where id = ?");
	    ps.setLong(1, id);

	    rs = ps.executeQuery();
	    if (rs.next()) {
		user = new User();

		user.setId(rs.getLong("ID"));
		user.setEmail(rs.getString("EMAIL"));
		user.setLogin(rs.getString("LOGIN"));
		user.setName(rs.getString("NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setStatus(UserStatus.values()[rs.getInt("STATUS")]);
		user.setSurname(rs.getString("SURNAME"));

	    }

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Driver not found", e);
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (con != null) {
		try {
		    con.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	}

	return user;
    }

    public void save(User user) throws AlreadyPersistedException {
	PreparedStatement ps = null;
	Connection con = null;
	int rows = 0;

	try {
	    // Obtenemos la conexi��n a la base de datos.
	    Class.forName(SQL_DRV);
	    con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement("insert into tusers (login,name,password,"
	    	+ "surname,email,status) values (?,?,?,?,?,?)");

	    ps.setString(1, user.getLogin());
	    ps.setString(2, user.getName());
	    ps.setString(3, user.getPassword());
	    ps.setString(4, user.getSurname());
	    ps.setString(5, user.getEmail());
	    ps.setInt(6, user.getStatus().ordinal());

	    rows = ps.executeUpdate();
	    if (rows != 1) {
		throw new AlreadyPersistedException("Usuario " + user
			+ " already persisted");
	    }

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Driver not found", e);
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (con != null) {
		try {
		    con.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	}
    }

    public void update(User user) throws NotPersistedException {
	PreparedStatement ps = null;
	Connection con = null;
	int rows = 0;

	try {
	    // Obtenemos la conexi��n a la base de datos.
	    Class.forName(SQL_DRV);
	    con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement("UPDATE TUSERS SET login = ?, name = ?,  "
	    	+ "password = ?, surname = ?, email = ?, status = ? where id = ?");

	    ps.setString(1, user.getLogin());
	    ps.setString(2, user.getName());
	    ps.setString(3, user.getPassword());
	    ps.setString(4, user.getSurname());
	    ps.setString(5, user.getEmail());
	    ps.setInt(6, user.getStatus().ordinal());
	    ps.setLong(7, user.getId());

	    rows = ps.executeUpdate();
	    if (rows != 1) {
		throw new NotPersistedException("Usuario " + user + " not found");
	    }

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Driver not found", e);
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (con != null) {
		try {
		    con.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	}
    }

    @Override
    public User login(String login, String password) {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;
	User user = null;

	try {
	    // Obtenemos la conexi��n a la base de datos.
	    Class.forName(SQL_DRV);
	    con = DriverManager.getConnection(SQL_URL, "sa", "");
	    ps = con.prepareStatement("select * from tusers where login = ? and "
	    	+ "password = ?");
	    ps.setString(1, login);
	    ps.setString(2, password);

	    rs = ps.executeQuery();
	    if (rs.next()) {
		user = new User();

		user.setId(rs.getLong("ID"));
		user.setEmail(rs.getString("EMAIL"));
		user.setLogin(rs.getString("LOGIN"));
		user.setName(rs.getString("NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setStatus(UserStatus.values()[rs.getInt("STATUS")]);
		user.setSurname(rs.getString("SURNAME"));

	    }

	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Driver not found", e);
	} catch (SQLException e) {
	    e.printStackTrace();
	    throw new PersistenceException("Invalid SQL or database schema", e);
	} finally {
	    if (rs != null) {
		try {
		    rs.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (ps != null) {
		try {
		    ps.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	    if (con != null) {
		try {
		    con.close();
		} catch (Exception ex) {
		}
	    }
	    ;
	}

	return user;
    }

}
