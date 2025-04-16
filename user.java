import java.util.*;

class user{
    public List<LinkedHashMap<String, String>> emails = new ArrayList<>();
    


    // Accept two hardcoded emails
    public void acceptEmails() {
        LinkedHashMap<String, String> email1 = new LinkedHashMap<>();
        email1.put("From", "\"Lottery Department\" <lottery@winbig.com>");
        email1.put("To", "jane.doe@example.com");
        email1.put("Cc", "support@winbig.com");
        email1.put("Bcc", "hiddenuser123@example.com");
        email1.put("Subject", "🎉 Congratulations! You’ve won $1,000,000!");
        email1.put("Date", "Mon, 15 Apr 2025 10:24:37 +0530");
        email1.put("Content-Type", "text/plain; charset=\"UTF-8\"");
        email1.put("Body", "Dear Jane Doe,\n\n"
                + "We are pleased to inform you that you have won our grand prize of $1,000,000 in our International Lottery 2025 Draw!\n\n"
                + "To claim your prize, simply click the link below and provide your personal information and bank account details:\n\n"
                + "👉 https://badsite.com/claim-now\n\n"
                + "This offer is valid only for 24 hours. Don’t miss your chance to become a millionaire overnight!\n\n"
                + "Sincerely,\n"
                + "The WinBig Team\n"
                + "lottery@winbig.com\n"
                + "www.winbig.com\n");

        LinkedHashMap<String, String> email2 = new LinkedHashMap<>();
        email2.put("From", "\"Tech Support\" <support@yourbank.com>");
        email2.put("To", "john.smith@example.com");
        email2.put("Cc", "itdesk@yourbank.com");
        email2.put("Bcc", "admin@yourbank.com");
        email2.put("Subject", "🚨 Urgent: Account Verification Required");
        email2.put("Date", "Tue, 16 Apr 2025 09:10:12 +0530");
        email2.put("Content-Type", "text/plain; charset=\"UTF-8\"");
        email2.put("Body", "Dear John Smith,\n\n"
                + "We have detected unusual activity on your account. Please verify your account immediately to avoid suspension.\n\n"
                + "🔐 Click here to verify: https://yourbank.com/verify\n\n"
                + "If you do not act within 12 hours, your account will be locked for your protection.\n\n"
                + "Thank you,\n"
                + "YourBank Support Team\n"
                + "support@yourbank.com");

        // Add both emails to the main 'email' list
        emails.add(email1);
        emails.add(email2);
    }
    public void search() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter sender (From) to search: ");
        String query = sc.nextLine().toLowerCase(); // case-insensitive
    
        boolean found = false;
        int count = 1;
        for (LinkedHashMap<String, String> email : emails) {
            String fromField = email.get("From").toLowerCase();
    
            if (fromField.contains(query)) {
                System.out.println("========== MATCHED EMAIL " + count + " ==========");
                for (Map.Entry<String, String> entry : email.entrySet()) {
                    if (entry.getKey().equals("Body")) {
                        System.out.println("\n" + entry.getValue());
                    } else {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                }
                System.out.println("\n");
                found = true;
            }
            count++;
        }
    
        if (!found) {
            System.out.println("❌ No emails found from sender: " + query);
        }
    }
    


    public void displayEmail(LinkedHashMap<String,String> email) {
        
            for (Map.Entry<String, String> entry : email.entrySet()) {
                if (entry.getKey().equals("Body")) {
                    System.out.println("\n" + entry.getValue());
                } else {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
}


public void displayEmailSummaries(Set<String> suspiciousDomains, Set<String> urgencyWords) {
    Functions obj = new Functions();

    System.out.println("📧 Inbox:");
    int index = 0;
    for (LinkedHashMap<String, String> email : emails) {
        String from = email.get("From");
        String subject = email.get("Subject");
        String body = email.get("Body");

        // Show only the first 10 words of the body as preview
        String[] bodyWords = body.split("\\s+");
        StringBuilder preview = new StringBuilder();
        for (int i = 0; i < Math.min(10, bodyWords.length); i++) {
            preview.append(bodyWords[i]).append(" ");
        }

        // Check if it's a spam email
        boolean isSpam = obj.isSpamEmail(body, suspiciousDomains, urgencyWords);

        // Print email summary
        System.out.println("-------------------------------------------------");
        System.out.println("Email #" + (index++));
        System.out.println("Subject: " + subject);
        System.out.println("From: " + from);
        System.out.println("Preview: " + preview.toString().trim() + "...");
        System.out.println("Spam: " + (isSpam ? "✅ Yes" : "❌ No"));
        System.out.println("-------------------------------------------------");
        System.out.println();
    }
}
}