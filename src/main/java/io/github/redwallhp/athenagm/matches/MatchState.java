package io.github.redwallhp.athenagm.matches;


public enum MatchState {

    WAITING(),
    COUNTDOWN(),
    PLAYING(),
    ENDED();

    @Override
    public String toString() {
        final String lowerCase = this.name().toLowerCase();
        return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
    }

}
