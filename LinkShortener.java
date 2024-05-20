import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LinkShortener {
    private Map<String, String> urlMap;

    public LinkShortener() {
        this.urlMap = new HashMap<>();
    }

    public String shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl(longUrl);
        urlMap.put(shortUrl, longUrl);
        return shortUrl;
    }

    public String expandUrl(String shortUrl) {
        if (!urlMap.containsKey(shortUrl)) {
            throw new IllegalArgumentException("Invalid short URL");
        }
        return urlMap.get(shortUrl);
    }

    private String generateShortUrl(String longUrl) {
        int hash = longUrl.hashCode();
        return Integer.toHexString(hash);
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL: ");
                    String longUrl = scanner.next();
                    String shortUrl = linkShortener.shortenUrl(longUrl);
                    System.out.println("Short URL: " + shortUrl);
                    break;
                case 2:
                    System.out.print("Enter the short URL: ");
                    String inputShortUrl = scanner.next();
                    try {
                        String expandedUrl = linkShortener.expandUrl(inputShortUrl);
                        System.out.println("Expanded URL: " + expandedUrl);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
