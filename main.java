import java.util.*;

public class main {
    
    //==The following set contains all the names of domain used in method detectPhishing()======
    static Set<String> suspiciousDomains = new HashSet<>(Arrays.asList(
    "badsite.com",
    "phishdomain.org",
    "malware-download.net",
    "fakebanklogin.com",
    "secure-update.info",
    "login-verification.xyz",
    "account-recovery-mail.net",
    "paypal-security-alert.com",
    "update-billing.info"
));
//=======Positive words for sentiment analysis=====
    static Set<String> positiveWords = new HashSet<>(Arrays.asList(
    "excellent", "awesome", "amazing", "fantastic", "great",
    "wonderful", "love", "like", "superb", "nice",
    "happy", "joy", "pleased", "delight", "brilliant",
    "positive", "good", "best", "outstanding", "favorable",
    "helpful", "supportive", "safe", "trusted", "efficient"
));
//======Negative words for sentiment analysis==========
static Set<String> negativeWords = new HashSet<>(Arrays.asList(
    "bad", "terrible", "awful", "worst", "hate",
    "angry", "upset", "sad", "poor", "negative",
    "problem", "issue", "fail", "disappointed", "frustrated",
    "danger", "threat", "fraud", "spam", "unsafe",
    "unhappy", "rude", "slow", "insecure", "error"
));
static Set<String> urgencyWords = new HashSet<>(Arrays.asList(
    "urgent",
    "immediately",
    "asap",
    "now",
    "act fast",
    "important",
    "attention",
    "verify",
    "confirm",
    "alert",
    "suspend",
    "limited time",
    "account locked",
    "security notice",
    "response needed",
    "final warning",
    "update required",
    "unauthorized access",
    "unusual activity",
    "take action"
));




    public static void main(String[] args) {
        final Map<String, String> keywordCategoryMap = new HashMap<>();
            keywordCategoryMap.put("invoice", "Finance");
            keywordCategoryMap.put("payment", "Finance");
            keywordCategoryMap.put("salary", "Finance");

            keywordCategoryMap.put("project", "Work");
            keywordCategoryMap.put("meeting", "Work");
            keywordCategoryMap.put("deadline", "Work");

            keywordCategoryMap.put("party", "Personal");
            keywordCategoryMap.put("family", "Personal");
            keywordCategoryMap.put("vacation", "Personal");
        Scanner sc = new Scanner(System.in);
        user obj1 = new user();
        Sender obj2 = new Sender(obj1);
        Functions obj3=new Functions();
        obj1.acceptEmails();
        outerLoop:
        do {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. User");
            System.out.println("2. Sender");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    int chSender;
                    do {
                        System.out.println("\n--- User Menu ---");
                        System.out.println("0. Back");
                        System.out.println("1. Display Emails");
                        System.out.println("2. Search Email");
                        System.out.print("Enter your choice: ");
                        chSender = sc.nextInt();

                        switch (chSender) {
                            case 1:
                            obj1.displayEmailSummaries(suspiciousDomains,urgencyWords);
                            System.out.println("Enter email index number to be displayed:");
                            int emailno=sc.nextInt();
                            obj1.displayEmail(obj1.emails.get(emailno));
                            System.out.println("1. Detect fishing \n2. Sentiment analysis \n3. Detect Schedule \n4. Classify Email \n5. Detect Urgency");
                            int emailInside=sc.nextInt();
                            switch(emailInside)
                            {
                                case 1:
                                List<String> listOfLinks=obj3.detectPhishingLinks(obj1.emails.get(emailno).get("Body"),suspiciousDomains);
                                if(listOfLinks.size()==0)
                                {
                                    System.out.println("No Phishing links detected");
                                }else{
                                    System.out.println("Phishing detected:");
                                    for(String link:listOfLinks)
                                    {
                                        System.out.println(link);
                                    }
                                }
                                break;
                                case 2:
                                System.out.println(obj3.sentimentAnalysis(obj1.emails.get(emailno).get("Body"),positiveWords,negativeWords));
                                break;
                                case 3:
                                obj3.detectAndPrintSchedule(obj1.emails.get(emailno).get("Body"));
                                break;
                                case 4:
                                System.out.println(obj3.classifyEmail(obj1.emails.get(emailno).get("Body"), keywordCategoryMap));
                                break;
                                case 5:
                                if(obj3.detectUrgency(obj1.emails.get(emailno).get("Body"),urgencyWords))
                                {
                                    System.out.println("Urgent");
                                }else{
                                    System.out.println("Not urgent");
                                }
                                break;
                            }
                                break;
                            case 2:
                            obj1.search();
                            break;
                            case 0:
                                continue outerLoop;
                        }
                    } while (true);

                case 2:
                    int chUser;
                    do {
                        System.out.println("\n--- Sender Menu ---");
                        System.out.println("0. Back");
                        System.out.println("1. Send Email");
                        System.out.print("Enter your choice: ");
                        chUser = sc.nextInt();

                        switch (chUser) {
                            case 1:
                                obj2.create();
                                break;
                            case 0:
                                continue outerLoop;
                        }
                    } while (true);

                case 3:
                    System.out.println("Exiting...");
                    break outerLoop;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (true);
    }
}
