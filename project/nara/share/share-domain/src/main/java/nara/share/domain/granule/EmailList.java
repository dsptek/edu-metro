package nara.share.domain.granule;

import nara.share.util.json.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class EmailList {
    //
    private List<Email> emails;

    public EmailList() {
        //
        this.emails = new ArrayList<>();
    }

    public EmailList(Email email) {
        //
        this();
        this.emails.add(email);
    }

    public  EmailList(Email.Category category, String email) {
        //
        this();
        this.emails.add(new Email(category, email));
    }

    @Override
    public String toString() {
        //
        return emails.toString();
    }

    public static EmailList getSample() {
        //
        EmailList sample = new EmailList();
        sample.add(Email.getSample());

        return sample;
    }

    public String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    public static EmailList fromJson(String json) {
        //
        return JsonUtil.fromJson(json, EmailList.class);
    }

    public void add(Email email) {
        // 
        this.emails.add(email);
    }

    public void add(Email.Category category, String email) {
        // 
        this.emails.add(new Email(category, email));
    }

    public void addAll(List<Email> emails) {
        // 
        this.emails.addAll(emails);
    }

    public List<Email> getList() {
        // 
        return emails;
    }

    public Email getFirst() {
        //
        if(emails == null|| emails.isEmpty())
            return null;

        return emails.get(0);
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public Email get(String targetEmail) {
        //
        Email foundEmail = null;
        for(Email email : this.emails) {
            if (email.getEmail().equals(targetEmail)) {
                foundEmail = email;
                break;
            }
        }

        return foundEmail;
    }

    public boolean contains(String targetEmail) {
        //
        for(Email email : this.emails) {
            if (email.getEmail().equals(targetEmail)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return emails.size();
    }

    public static void main(String[] args) {
        //
        System.out.println(getSample());
        System.out.println(getSample().toJson());
    }
}