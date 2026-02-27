import java.util.Scanner;

public class RestaurantMain {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Restaurant menu = new Restaurant();

        menu.tambahMenuMakanan("Pizza", 250000, 20);
        menu.tambahMenuMakanan("Spaghetti", 80000, 20);
        menu.tambahMenuMakanan("Tenderloin Steak", 60000, 30);
        menu.tambahMenuMakanan("Chicken Steak", 45000, -30);
        
        System.out.println("\n== MENU MAKANAN ==");
        menu.tampilMenuMakanan();

        char lanjut = 'y';

        while (lanjut == 'y' || lanjut == 'Y') {

            System.out.println("\n== DAFTAR MENU ==");
            System.out.println("Daftar index menu:");
            menu.tampilIndexMenu();

            System.out.print("Masukkan index menu: ");
            int index = input.nextInt();

            System.out.print("Masukkan jumlah pesanan: ");
            int jumlah = input.nextInt();

            menu.pesanMenu(index, jumlah);

            
            System.out.print("\nPesan lagi? (y/n): ");
            lanjut = input.next().charAt(0);
        }
        System.out.println("\nMenu setelah pemesanan:");
        menu.tampilMenuMakanan();

        input.close();
    }
}