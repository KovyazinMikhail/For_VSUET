import java.util.ArrayList;

public class ProductTable {
    //0 - id товара
    //1 - количество товара
    public ArrayList<Object[]> productList;
    public int docID;

    public ProductTable() {

    }
    public ProductTable(ArrayList<Object[]> productList) {
        this.productList = productList;
    }

}
