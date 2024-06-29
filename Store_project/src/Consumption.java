import java.sql.SQLException;
import java.util.Date;

public class Consumption {
    public int id;
    public Date date;
    public ProductTable productTable;

    public Consumption() {
        this.date = new Date();
    }
    public Consumption(int id, Date date) {
        this.id = id;
        this.date = date;
    }
    public Consumption(int id, Date date, ProductTable productTable) {
        this.id = id;
        this.date = date;
        this.productTable = productTable;
    }


    public Consumption(int id) throws SQLException {
        DB dbConnection = new DB();
        Object[] documentData = dbConnection.getDocumentData("consumption",id);
        this.date = (Date)documentData[1];
        this.productTable = (ProductTable)documentData[2];
    }

    public void writeConsumption() throws SQLException {
        DB dbConnection = new DB();
        dbConnection.writeDocument("consumption",this.productTable,this.date,this.id);
    }

}
