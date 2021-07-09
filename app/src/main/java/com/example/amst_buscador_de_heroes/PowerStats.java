package com.example.amst_buscador_de_heroes;

public class PowerStats {
    String stat;
    int value;

    public PowerStats(String stat, int value) {
        this.stat = stat;
        this.value = value;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
