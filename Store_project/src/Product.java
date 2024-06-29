import java.sql.SQLException;
import java.util.ArrayList;

public class Product {

    public int id;
    public String name;

    public Product() {

    }
    public Product(int id) {
        this.id = id;
    }
    public Product(String name) {
        this.name = name;
    }
    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Код товара: %s | Товар: %s ", this.id, this.name);
    }

    public void getProductByID() throws SQLException {
        DB dbConnection = new DB();

        Object[] productData = dbConnection.getReferenceData("product",this.id).getFirst();

        this.id = (int) productData[0];
        this.name = (String) productData[1];
    }
    public void writeProduct() throws SQLException {
        DB dbConnection = new DB();
        dbConnection.writeReference("product",this.name,this.id);
    }
    public void deleteProduct() throws SQLException {
        DB dbConnection = new DB();
        dbConnection.deleteRecord("product",this.id);
    }
}
