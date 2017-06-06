package bigbox.db;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.*;
import java.sql.*;
//leaving these here to see what i used 
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;


import bigbox.business.Store;






public class StoreDB implements StoreDAO {

	@Override
	public Store getStoreByDivionAndStoreNumber(String inDiv, String inStoreNbr) {
		String sql = "SELECT * FROM Stores WHERE DivisionNumber = ?, StoreNumber = ? ";

		Store s = null;
		Connection connection = DBUtil.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, inDiv);
			ps.setString(2, inStoreNbr);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				s = getStoreFromRow(rs);
				rs.close();
			}

		} catch (SQLException e) {

			e.getStackTrace();

		}

		return s;
	}

	@Override
	public ArrayList<Store> getAllStores() {
		String sql = "SELECT * FROM Stores ";
		ArrayList<Store> Stores = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {

				Store p = getStoreFromRow(rs);

				Stores.add(p);

	}
			
		} catch (SQLException e) {
			e.printStackTrace();
				
		}
		return Stores;
		
		}
		
			
			/*
				if (store == null) {
					Validator.display("Error! Unable to get products.\n");
				} else {
					Product p;
					StringBuilder sb = new StringBuilder();
					for (Product product : products) {
						p = product;
						sb.append(StringUtil.padWithSpaces(p.getDivisionNumber(), 3));
						sb.append(StringUtil.padWithSpaces(p.getDescription(), 34));
						sb.append(p.getPriceFormatted());
						sb.append("\n");
					}
					Console.display(sb.toString());
				}
			}
				*/
				
	private Store getStoreFromRow(ResultSet rs) {
		  {
        	 Store p = new Store();
        	try
        	{
        	String DivisionNbr = rs.getString("DivisionNumber");
             String StoreNbr = rs.getString("StoreNumber");
             double Sales = rs.getDouble("Sales");
             String Name = rs.getString("Name");
             String Address = rs.getString("Address");
             String City = rs.getString("City");
             String State = rs.getString("State");
              String ZipCode = rs.getString("ZipCode");        	
        
        
             p.setDivisionNbr(DivisionNbr);
             p.setStoreNbr(StoreNbr);
             p.setSales(Sales);
             p.setName(Name);
             p.setAddress(Address);
             p.setCity(City);
             p.setState(State);
             p.setZip(ZipCode);
        	
        	}
        	catch (InputMismatchException e)
        	{
        		e.printStackTrace();
        	}
        	catch (SQLException e)
        	{
				e.printStackTrace();
				
			}
			return  p;
		}
    
	}

	@Override
	public ArrayList<Store> getAllStoresByDivision(String inDiv) {
		String sql = "SELECT * FROM Stores WHERE DivisionNumber = '"+inDiv+"' ORDER BY DivisionNumber";
		ArrayList<Store> stores = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {

			Store p = getStoreFromRow(rs);

				stores.add(p);

			}
			
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			
		}
		return stores;
	}

	
	
	
	@Override
	public void addStore(Store inStore){
		 String sql
         = "INSERT INTO Stores ( DivisionNumber,  StoreNumber, Sales, Name,  Address, City, State, ZipCode ) "
         + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
		 Connection connection = DBUtil.getConnection();
		 try (PreparedStatement ps = connection.prepareStatement(sql)) 
		 {
			 ps.setString(1, inStore.getDivisionNbr());
		     ps.setString(2, inStore.getStoreNbr());
		     ps.setDouble(3, inStore.getSales());
		     ps.setString(4, inStore.getName());
		     ps.setString(5, inStore.getAddress());
		     ps.setString(6, inStore.getCity());
		     ps.setString(7, inStore.getState());
		     ps.setString(8, inStore.getZip());
		     ps.executeUpdate();
		 } 
		 catch (SQLException e) 
		 {
			 e.printStackTrace();
		 }
	}

	@Override
	public void updateStore(Store inStore){
		 String sql = "UPDATE Stores SET "
	                + "DivisionNumber = ?, "
	                + "StoreNumber = ?, "
	                + "Sales = ? "
	                + "Name = ? "
	                + "Address = ? "
	                + "City = ? "
	                + "State = ? "
	                + "ZipCode = ?";
	                
	        Connection connection = DBUtil.getConnection();
	        try (PreparedStatement ps = connection.prepareStatement(sql)) {
	        	 ps.setString(1, inStore.getDivisionNbr());
			     ps.setString(2, inStore.getStoreNbr());
			     ps.setDouble(3, inStore.getSales());
			     ps.setString(4, inStore.getName());
			     ps.setString(5, inStore.getAddress());
			     ps.setString(6, inStore.getCity());
			     ps.setString(7, inStore.getState());
			     ps.setString(8, inStore.getZip());
			     ps.executeUpdate();
	        } catch (SQLException e) {
	            e.getSuppressed();
	        }        
	    }
	    
	
	@Override
	public void deleteStore(Store inStore)  {
		
		        String sql = "DELETE FROM stores "
		                   + "WHERE DivisionNumber = ?"
		                   + "  AND StoreNumber = ?";
		        Connection connection = DBUtil.getConnection();
		        try (PreparedStatement ps = connection.prepareStatement(sql)) {
		            ps.setString(1, inStore.getDivisionNbr());
		            ps.setString(2, inStore.getStoreNbr());
		            ps.executeUpdate();
				 } 
				 catch (SQLException e) 
				 {
					 e.printStackTrace();
				 }
			}
	}
	
	

	
	



		




	