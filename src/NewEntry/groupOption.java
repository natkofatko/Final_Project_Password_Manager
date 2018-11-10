package NewEntry;

public enum groupOption {

    SocialMedia, Blogs, eMail, Games, Banking, Home, Software, Network;

    private groupOption() {

    }

    public String Value() {
        return name();
    }

    public static groupOption fromValue(String v) {
        return valueOf(v);
    }
}

