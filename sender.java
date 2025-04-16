 import java.util.*;
 
 class Sender {
    user obj;
    Sender(user obj)
    {
        this.obj=obj;
    }
    Scanner sc=new Scanner(System.in);
    void create()
    {
        LinkedHashMap<String, String> email = new LinkedHashMap<>();

        System.out.print("From: ");
        email.put("From", sc.nextLine());

        System.out.print("To: ");
        email.put("To", sc.nextLine());

        System.out.print("Cc: ");
        email.put("Cc", sc.nextLine());

        System.out.print("Bcc: ");
        email.put("Bcc", sc.nextLine());

        System.out.print("Subject: ");
        email.put("Subject", sc.nextLine());

        System.out.print("Date: ");
        email.put("Date", sc.nextLine());

        email.put("Content-Type", "text/plain; charset=\"UTF-8\"");
        StringBuilder body = new StringBuilder();

        Functions obj2=new Functions();
        while(true){
        System.out.println("Enter Body (type 'End.' to finish):");
        
        
        while (true) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("End.")) break;
            body.append(line).append("\n");
        }
        List<String> cleanUpList=obj2.cleanupSuggestions(body.toString());
        if(cleanUpList.size()!=0)
        {
            System.out.println("Clean Up Suggestions:");
            for(String suggest:cleanUpList)
            {
                System.out.println(suggest);
            }
            System.out.println("Enter Body again:");
        }else break;
    }
        email.put("Body", body.toString());

        obj.emails.add(email);
        System.out.println("✅ Email saved successfully!\n");
    }
}
