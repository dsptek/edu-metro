package nara.share.domain;

public enum DramaScope {
    //
    Pavilion("P"),
    Cineroom("C");

    private String initial;

    private DramaScope(String initial) {
        //
        this.initial = initial;
    }

    public String initial() {
        //
        return initial;
    }

    public boolean isPavilionScope() {
        //
        if (this == Pavilion) {
            return true;
        }

        return false;
    }

    public boolean isCineroomScope() {
        //
        if (this == Cineroom) {
            return true;
        }

        return false;
    }
}