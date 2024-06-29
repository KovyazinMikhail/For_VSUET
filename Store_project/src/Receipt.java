import java.sql.SQLException;
import java.util.Date;

public class Receipt {

    public int id;
    public Date date;
    public ProductTable productTable;

    public Receipt() {
        this.date = new Date();
        this.productTable = new ProductTable();
    }
    public Receipt(int id, Date date) throws SQLException {
        this.id = id;
        this.date = date;
        DB dbConnection = new DB();
        Object[] documentData = dbConnection.getDocumentData("receipt",id);
        this.productTable = (ProductTable) documentData[2];
    }
    public Receipt(int id, Date date, ProductTable productTable) {
        this.id = id;
        this.date = date;
        this.productTable = productTable;
    }


    public Receipt(int id) throws SQLException {
        DB dbConnection = new DB();
        Object[] documentData = dbConnection.getDocumentData("receipt",id);
        this.date = (Date)documentData[1];
        this.productTable = (ProductTable)documentData[2];
    }

    public void writeReceipt() throws SQLException {
        DB dbConnection = new DB();
        dbConnection.writeDocument("receipt",this.productTable,this.date,this.id);
    }


}
