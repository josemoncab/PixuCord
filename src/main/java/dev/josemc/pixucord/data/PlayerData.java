package dev.josemc.pixucord.data;

public class PlayerData {
    private boolean isOp;

    public PlayerData(boolean isOp) {
        this.isOp = isOp;
    }

    public boolean isOp() {
        return isOp;
    }

    public void setOp(boolean op) {
        isOp = op;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "isOp=" + isOp +
                '}';
    }

}
