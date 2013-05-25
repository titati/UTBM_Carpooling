package fr.utbm.carpooling.model;


import java.io.Serializable;

public class Color implements Serializable, Comparable {

    private int id;
    private String name;
    private String hex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {

        if (!hex.startsWith("#")) {
            hex = "#".concat(hex);
        }

        this.hex = hex;
    }

    @Override
    public int compareTo(Object another) {

        Color color = (Color) another;

        int C1 = android.graphics.Color.parseColor(getHex());
        int C2 = android.graphics.Color.parseColor(color.getHex());

        float[] hsv1 = new float[3];
        float[] hsv2 = new float[3];

        android.graphics.Color.colorToHSV(C1, hsv1);
        android.graphics.Color.colorToHSV(C2, hsv2);

        if (hsv1[0] < hsv2[0])
            return -1;
        if (hsv1[0] > hsv2[0])
            return 1;
        if (hsv1[1] < hsv2[1])
            return -1;
        if (hsv1[1] > hsv2[1])
            return 1;
        if (hsv1[2] < hsv2[2])
            return -1;
        if (hsv1[2] > hsv2[2])
            return 1;
        return 0;
    }

}
