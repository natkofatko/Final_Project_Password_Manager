package NewEntry;

/**
 * this was supposet to be a drop box of a different sites, but ebentually it was not used in the project dur to some error
 */
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

