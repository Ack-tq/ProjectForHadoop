package com.hadoop.resources;

import org.junit.Test;

import java.sql.*;

public class LoadingToSQL {
	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;

	@Test
	public void loadingToSQL() throws SQLException {
		try {
			connection = JDBCUtils.getConnection();
			preparedStatement = connection.prepareStatement("REPLACE INTO `student` (`id`, `name`, `password`) VALUES (?, ?, COALESCE(?, DEFAULT(`password`)))");
			String[] everyCellsInRow = null;

			for (int row = 1; row <= ReadExcelUtils.getRows(); row++) {
				everyCellsInRow = ReadExcelUtils.getEveryCellsInRow(row);
				for (int i = 0; i < ReadExcelUtils.getRowSize(); i++) {
					if (i < everyCellsInRow.length)
						preparedStatement.setString(i + 1, everyCellsInRow[i]);
					else preparedStatement.setString(i + 1, null);
				}
				preparedStatement.execute();
			}
/*			for (;;){
				pS.setString();
			}*/

			Statement statement = connection.createStatement();
			ResultSet resultSet1 = statement.executeQuery("SELECT * FROM `student`");
			while (resultSet1.next()) {
				System.out.println((String) resultSet1.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
//			return false;
		} finally {
			JDBCUtils.release(connection, preparedStatement, resultSet);
		}
//		return true;
	}
}
