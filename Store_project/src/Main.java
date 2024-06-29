import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main (String args[]) throws SQLException {
        showMainMenu();

        Scanner in = new Scanner(System.in);

        String choice = in.nextLine();
        int id;
        switch(choice){
            case "0":
                System.exit(0);
                break;
            case "1":
                showMenuAddProduct();
                break;
            case "2":
                System.out.println("Укажите id товара:");
                in = new Scanner(System.in);
                id = Integer.parseInt(in.nextLine());
                showProduct(id);
                break;
            case "3":
                showAddReceipt();
                break;
            case "4":
                System.out.println("Укажите id документа поступления:");
                in = new Scanner(System.in);
                id = Integer.parseInt(in.nextLine());
                showReceipt(id);
                break;
            case "5":
                showAddConsumption();
                break;
            case "6":
                System.out.println("Укажите id документа списсания:");
                in = new Scanner(System.in);
                id = Integer.parseInt(in.nextLine());
                showConsumption(id);
                break;
            default:
                showMainMenu();
                break;
        }
    }
    public static void showMainMenu(){
        System.out.println("Выберите желаемое действие:");
        System.out.println("1 - Создание номенклатуры");
        System.out.println("2 - Просмотр и редактирование номенклатуры");
        System.out.println("3 - Ввод документа поступления товара");
        System.out.println("4 - Просмотр документа поступления");
        System.out.println("5 - Ввод документа списания товара");
        System.out.println("6 - Просмотр документа списания");
        System.out.println("0 - Выход из программы");
    }
    public static void showMenuAddProduct() throws SQLException {
        System.out.println("Укажите наименование товара:");
        Scanner in = new Scanner(System.in);
        String productName = in.nextLine();

        Product product = new Product(productName);
        product.writeProduct();
        System.out.println("Номенклатура: "+product.toString()+" успешно создана.");
        showMainMenu();
    }
    public static void showProduct(int id) throws SQLException {
        Product product = new Product(id);
        product.getProductByID();
        System.out.println("Номенклатура: "+product.toString());
        System.out.println("Выберите желаемое действие:");
        System.out.println("1 - Изменить название");
        System.out.println("0 - Вернуться в главное меню");
        Scanner in = new Scanner(System.in);

        String choice = in.nextLine();
        switch(choice) {
            case "0":
                showMainMenu();
                break;
            case "1":
                System.out.println("Укажите новое название: ");
                in = new Scanner(System.in);

                product.name = in.nextLine();
                product.writeProduct();
                showProduct(product.id);
                break;
        }
    }
    public static void showAddReceipt() throws SQLException {
        Receipt receipt = new Receipt();
        ProductTable productTable = new ProductTable();

        while (true) {
            System.out.println("Укажите id товара: ");
            Scanner in = new Scanner(System.in);
            int id = Integer.parseInt(in.nextLine());

            System.out.println("Укажите количество товара: ");
            in = new Scanner(System.in);
            int count = Integer.parseInt(in.nextLine());

            Object[] productLine = new Object[2];
            Product product = new Product(id);
            productLine[0] = product.id;
            productLine[1] = -count;
            productTable.productList.add(productLine);

            System.out.println("Чтобы продолжить нажмите '1'");
            System.out.println("Для записи докумеента введите 'w'.");
            System.out.println("Для выхода без сохранения введите 'e'.");

            if (in.nextLine().equals("w")) {
                receipt.writeReceipt();
            }
            if (in.nextLine().equals("e")) {
                showMainMenu();
            }
        }
    }
    public static void showReceipt(int id) throws SQLException {
        Receipt receipt = new Receipt(id);
        System.out.println("Документ номер +"+ receipt.id + " от " + receipt.date);
        for (Object[] productList : receipt.productTable.productList) {
            Product product = new Product((int) productList[0]);
            System.out.println("Товар: "+ product.toString() + ", количество: "+ productList[1]);
        }
    }
    public static void showAddConsumption() throws SQLException {
        Consumption consumption = new Consumption();
        ProductTable productTable = new ProductTable();

        while (true) {
            System.out.println("Укажите id товара: ");
            Scanner in = new Scanner(System.in);
            int id = Integer.parseInt(in.nextLine());

            System.out.println("Укажите количество товара: ");
            in = new Scanner(System.in);
            int count = Integer.parseInt(in.nextLine());

            Object[] productLine = new Object[2];
            Product product = new Product(id);
            productLine[0] = product.id;
            productLine[1] = -count;
            productTable.productList.add(productLine);

            System.out.println("Чтобы продолжить нажмите '1'");
            System.out.println("Для записи докумеента введите 'w'.");
            System.out.println("Для выхода без сохранения введите 'e'.");

            if (in.nextLine().equals("w")) {
                consumption.writeConsumption();
            }
            if (in.nextLine().equals("e")) {
                showMainMenu();
            }
        }
    }
    public static void showConsumption(int id) throws SQLException {
        Consumption consumption = new Consumption(id);
        System.out.println("Документ номер +"+ consumption.id + " от " + consumption.date);
        for (Object[] productList : consumption.productTable.productList) {
            Product product = new Product((int) productList[0]);
            System.out.println("Товар: "+ product.toString() + ", количество: "+ productList[1]);
        }
    }

}
