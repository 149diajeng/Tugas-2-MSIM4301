import java.util.ArrayList;
import java.util.Scanner;

class MenuItem {
    String name;
    double price;
    String category;

    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

public class RestaurantApp {
    static ArrayList<MenuItem> menuItems = new ArrayList<>();
    static ArrayList<MenuItem> orderedItems = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeMenu();

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayMenu();
                    orderMenuItems();
                    break;
                case 2:
                    printReceipt();
                    break;
                case 3:
                    manageMenu();
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan aplikasi kami!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

    private static void initializeMenu() {
        menuItems.add(new MenuItem("Nasi Goreng", 25000, "Makanan"));
        menuItems.add(new MenuItem("Ayam Bakar", 35000, "Makanan"));
        menuItems.add(new MenuItem("Es Teh", 5000, "Minuman"));
        menuItems.add(new MenuItem("Es Jeruk", 7000, "Minuman"));
    }

    private static void displayMainMenu() {
        System.out.println("\nSelamat datang di Restoran XYZ");
        System.out.println("1. Pemesanan");
        System.out.println("2. Cetak Struk");
        System.out.println("3. Manajemen Menu");
        System.out.println("4. Keluar");
        System.out.print("Pilih menu: ");
    }

    private static void displayMenu() {
        System.out.println("\n== Menu Restoran ==");
        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            System.out.println((i + 1) + ". " + item.name + " - Rp " + item.price + " (" + item.category + ")");
        }
    }

    private static void orderMenuItems() {
        while (true) {
            System.out.print("\nPilih nomor menu yang ingin dipesan (selesai untuk mengakhiri): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("selesai")) {
                break;
            }

            try {
                int itemIndex = Integer.parseInt(input) - 1;
                if (itemIndex >= 0 && itemIndex < menuItems.size()) {
                    MenuItem selectedMenu = menuItems.get(itemIndex);
                    orderedItems.add(selectedMenu);
                    System.out.println(selectedMenu.name + " telah ditambahkan ke pesanan.");
                } else {
                    System.out.println("Nomor menu tidak valid, silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Masukan tidak valid, silakan coba lagi.");
            }
        }
    }

    private static void printReceipt() {
        double total = 0;
        System.out.println("\n== Struk Pesanan ==");
        for (MenuItem item : orderedItems) {
            System.out.println(item.name + " - Rp " + item.price);
            total += item.price;
        }

        double tax = total * 0.1;
        double serviceCharge = 20000;
        double grandTotal = total + tax + serviceCharge;

        System.out.println("\nTotal Biaya: Rp " + total);
        System.out.println("Pajak (10%): Rp " + tax);
        System.out.println("Biaya Pelayanan: Rp " + serviceCharge);
        System.out.println("Grand Total: Rp " + grandTotal);

        // Apply discounts or special offers
        if (total > 100000) {
            double discount = total * 0.1;
            grandTotal -= discount;
            System.out.println("Diskon 10%: -Rp " + discount);
        }
        if (total > 50000) {
            System.out.println("Penawaran Beli Satu Gratis Satu untuk Minuman!");
        }
        
        orderedItems.clear(); // clear the ordered items for the next transaction
    }

    private static void manageMenu() {
        while (true) {
            System.out.println("\n== Manajemen Menu ==");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih menu: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addMenuItem();
                    break;
                case 2:
                    editMenu();
                    break;
                case 3:
                    deleteMenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
    }

    private static void addMenuItem() {
        System.out.print("\nMasukkan nama menu baru: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan harga menu baru: Rp ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Masukkan kategori menu baru (Makanan/Minuman): ");
        String category = scanner.nextLine();

        menuItems.add(new MenuItem(name, price, category));
        System.out.println("Menu baru berhasil ditambahkan!");
    }

    private static void editMenu() {
        displayMenu();
        System.out.print("\nMasukkan nomor menu yang ingin diubah: ");
        int itemIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (itemIndex >= 1 && itemIndex <= menuItems.size()) {
            MenuItem selectedMenu = menuItems.get(itemIndex - 1);

            System.out.println("Menu yang akan diubah:");
            System.out.println("Nama: " + selectedMenu.name);
            System.out.println("Harga: Rp " + selectedMenu.price);
            System.out.println("Kategori: " + selectedMenu.category);

            System.out.print("\nMasukkan nama baru (kosongkan jika tidak ingin diubah): ");
            String newName = scanner.nextLine();
            if (!newName.isEmpty()) {
                selectedMenu.name = newName;
            }

            System.out.print("Masukkan harga baru (kosongkan jika tidak ingin diubah): Rp ");
            String newPriceStr = scanner.nextLine();
            if (!newPriceStr.isEmpty()) {
                double newPrice = Double.parseDouble(newPriceStr);
                selectedMenu.price = newPrice;
            }

            System.out.print("Masukkan kategori baru (kosongkan jika tidak ingin diubah): ");
            String newCategory = scanner.nextLine();
            if (!newCategory.isEmpty()) {
                selectedMenu.category = newCategory;
            }

            System.out.println("Menu berhasil diubah!");
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }

    private static void deleteMenu() {
        displayMenu();
        System.out.print("\nMasukkan nomor menu yang ingin dihapus: ");
        int itemIndex = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (itemIndex >= 1 && itemIndex <= menuItems.size()) {
            MenuItem deletedMenu = menuItems.remove(itemIndex - 1);
            System.out.println(deletedMenu.name + " telah dihapus dari menu.");
        } else {
            System.out.println("Nomor menu tidak valid.");
        }
    }
}
