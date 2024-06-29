import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class DB {
    private static final String CON_STR = "jdbc:sqlite:store.db";
    private Connection connection = null;
    private Statement statement = null;

    public DB() throws SQLException {
        try {
            DriverManager.registerDriver(new JDBC());
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

//Общие функции
    public void deleteRecord(String tableName, int id) throws SQLException {
        String sqlText = "DELETE FROM "+ tableName +" WHERE id = " + id;

        try {
            this.connection = DriverManager.getConnection(CON_STR);
            this.statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlText);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.connection != null) {
                this.connection.close();
            }
        }
    }
    public boolean recordExist(String tableName, int[] id) throws SQLException {
        String sqlText = "SELECT 1 FROM " + tableName + " WHERE id = " + id;
        try {
            this.connection = DriverManager.getConnection(CON_STR);
            this.statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlText);

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.connection != null) {
                this.connection.close();
            }
        }
        return false;
    }



//Функции для работы с справочниками

    public ArrayList<Object[]> getReferenceData(String tableName, int... id) throws SQLException {
        ArrayList<Object[]> listReference = new ArrayList<Object[]>();

        String sqlText = "SELECT * FROM " + tableName;

        if (id!=null) {
            sqlText += " WHERE id = " + Arrays.toString(id);
        }

        try {
            this.connection = DriverManager.getConnection(CON_STR);
            this.statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlText);

            while (resultSet.next()){
                int productId = resultSet.getInt(1);
                String productName = resultSet.getString(2);

                Object[] productData = new Object[2];
                productData[0] = productId;
                productData[1] = productName;
                listReference.add(productData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.connection != null) {
                this.connection.close();
            }
        }
        return listReference;
    }

    public void writeReference(String tableName, String nameElement, int... id) throws SQLException {
        String methodSQL = "";
        String selectionSQL = "";

        if (id!=null) {
            if (recordExist(tableName, id)) {
                methodSQL = "UPDATE ";
                selectionSQL = " WHERE id = " + Arrays.toString(id);
            }else{
                methodSQL = "INSERT ";
            }
        }
        String sqlText = methodSQL+"INTO "+ tableName +" VALUES("+ nameElement +")"+selectionSQL;

        try {
            this.connection = DriverManager.getConnection(CON_STR);
            this.statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlText);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.connection != null) {
                this.connection.close();
            }
        }
    }


//Функции для работы с документами
    public Object[] getDocumentData(String tableName, int id) throws SQLException {
        Object[] documentData = new Object[3];

        String sqlText = "SELECT * FROM " + tableName + " WHERE id = " + id;

        try {
            this.connection = DriverManager.getConnection(CON_STR);
            this.statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlText);

            resultSet.next();
            int docId = resultSet.getInt(1);
            Date docDate = resultSet.getDate(2);

            documentData[0] = docId;
            documentData[1] = docDate;

            sqlText = "SELECT * FROM productTable WHERE docID = " + id;

            ProductTable productTable = new ProductTable();

            while (resultSet.next()){
                int productId = resultSet.getInt(3);
                int productCount = resultSet.getInt(4);

                Object[] productData = new Object[2];
                productData[0] = productId;
                productData[1] = productCount;
                productTable.productList.add(productData);
            }
            documentData[2] = productTable;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.connection != null) {
                this.connection.close();
            }
        }
        return documentData;
    }

    public void writeDocument(String tableName, ProductTable products, Date documentDate, int... id) throws SQLException {
        String methodSQL = "";
        String selectionSQL = "";

        if (id!=null) {
            if (recordExist(tableName, id)) {
                methodSQL = "UPDATE ";
                selectionSQL = " WHERE id = " + Arrays.toString(id);
            }else{
                methodSQL = "INSERT ";
            }
        }
        String sqlText = methodSQL+"INTO "+ tableName +" VALUES("+ documentDate +")"+selectionSQL;

        try {
            this.connection = DriverManager.getConnection(CON_STR);
            this.statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlText);

            ArrayList<Object[]> productsData = products.productList;
            for (Object[] product : productsData) {
                if (recordExist("productTable", (int[]) product[0])) {
                    methodSQL = "UPDATE ";
                    selectionSQL = " WHERE id = " + Arrays.toString(id);
                } else {
                    methodSQL = "INSERT ";
                }
                sqlText = methodSQL + "INTO productTable VALUES(" + id + ", " + product[0] + ", " + product[1]+")" + selectionSQL;
                resultSet = statement.executeQuery(sqlText);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (this.statement != null) {
                this.statement.close();
            }
            if (this.connection != null) {
                this.connection.close();
            }
        }
    }
}

