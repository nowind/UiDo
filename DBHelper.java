package io.github.nowind.uido;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class DBHelper {
	static private String jdbcConn="xxxxxxxxxxx";
	static private Connection conn = null;
	static private Statement stat=null;
	static public DBHelper me=new DBHelper();
	static synchronized  Connection getDB()
	{
		return conn;
	}
	static
	{
		
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcConn);
			conn.setAutoCommit(true);
			stat=conn.createStatement();
		 } catch (Exception e) {
			 e.printStackTrace();
		}
	}
	DBHelper _exec(String sql)
	{
		try {
			System.out.println("exec sql:"+sql);
			stat.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return me;
	}
	public BrowserEmulator then()
	{
		return EduUiContext.getBrowserEmulator();
	}
	public BrowserEmulator exec(String sql)
	{
		return exec(new String[]{sql});
	}
	public BrowserEmulator exec(String[] sqls)
	{
		for(String sql:sqls)
			_exec(sql);
		return then();
	}
	public Object[] qrys(String q)
	{
		synchronized(this)
		{
			try {
				ArrayList<Object> ret=new ArrayList<Object>();
				ResultSet rs=stat.executeQuery(q);
				while(rs!=null&&rs.next())
					ret.add(rs.getObject(1));
				return ret.toArray();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	public Object qry(String q)
	{
		
		Object[] ret=qrys(q);
		if(ret!=null&&ret.length>0)return ret[0];
		return null;
	}
	
	 // demo
	 public BrowserEmulator clearLoginRecord(Long userid)
	 {

		return exec("delete from login_record where memberId"+userid.toString());
	 }
	 
	
}
