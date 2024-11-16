import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		HashTableLinearProbe<String, String> hashTable = new HashTableLinearProbe<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Insert");
            System.out.println("2. Find");
            System.out.println("3. Delete");
            System.out.println("4. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key: ");
                    String key = scanner.next();
                    System.out.print("Enter value: ");
                    String value = scanner.next();
                    boolean inserted = hashTable.insert(key, value);
                    System.out.println(inserted ? "Inserted successfully" : "Duplicate key or error");
                    break;
                case 2:
                    System.out.print("Enter key to find: ");
                    key = scanner.next();
                    String result = hashTable.find(key);
                    System.out.println(result != null ? "Found: " + result : "Not found");
                    break;
                case 3:
                    System.out.print("Enter key to delete: ");
                    key = scanner.next();
                    boolean deleted = hashTable.delete(key);
                    System.out.println(deleted ? "Deleted successfully" : "Key not found");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
		
	}

}
