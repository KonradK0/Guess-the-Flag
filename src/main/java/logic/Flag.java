package logic;

import com.opencsv.bean.CsvBindByName;

public class Flag {
    @CsvBindByName
    public String name;
    @CsvBindByName
    private boolean hasGreen;
    @CsvBindByName
    private boolean hasRed;
    @CsvBindByName
    private boolean hasBlue;
    @CsvBindByName
    private boolean hasBlack;
    @CsvBindByName
    private boolean hasWhite;
    @CsvBindByName
    private boolean hasYellow;
    @CsvBindByName
    private boolean hasCrest;
    @CsvBindByName
    private boolean hasStar;
    @CsvBindByName
    private boolean hasHalfMoon;
    @CsvBindByName
    private boolean hasCircle;
    @CsvBindByName
    private boolean hasCross;
    @CsvBindByName
    private boolean hasTriangle;
    @CsvBindByName
    private boolean hasVerticalStripes;
    @CsvBindByName
    private boolean hasHorizontalStripes;
    @CsvBindByName
    private int colorsCount;
    @CsvBindByName
    private boolean hasOrange;
    @CsvBindByName
    private int stripesCount;
    @CsvBindByName
    private boolean hasDiagonalStripes;
    @CsvBindByName
    private boolean hasBrown;
    @CsvBindByName
    private boolean hasCenterOfSymmetry;
    @CsvBindByName
    private String topMostColor;
    @CsvBindByName
    private boolean hasSpecialFeatures;
    @CsvBindByName
    private String colorOnRight;
    @CsvBindByName
    private int starsCount;
    @CsvBindByName
    private String widthHeightRatio;
    @CsvBindByName
    private String blueHue;
    @CsvBindByName
    private String dominatingColor;

    public Flag() {
    }


    public boolean hasGreen() {
        return hasGreen;
    }

    public boolean hasRed() {
        return hasRed;
    }

    public boolean hasBlue() {
        return hasBlue;
    }

    public boolean hasBlack() {
        return hasBlack;
    }

    public boolean hasWhite() {
        return hasWhite;
    }

    public boolean hasYellow() {
        return hasYellow;
    }

    public boolean hasOrange() {
        return hasOrange;
    }

    public boolean hasCrest() {
        return hasCrest;
    }

    public boolean hasStar() {
        return hasStar;
    }

    public boolean hasHalfMoon() {
        return hasHalfMoon;
    }

    public boolean hasCircle() {
        return hasCircle;
    }

    public boolean hasCross() {
        return hasCross;
    }

    public boolean hasTriangle() {
        return hasTriangle;
    }

    public boolean hasHorizontalStripes() {
        return hasHorizontalStripes;
    }

    public boolean hasVerticalStripes() {
        return hasVerticalStripes;
    }

    public boolean hasDiagonalStrupes() {
        return hasDiagonalStripes;
    }

    public int getColorsCount() {
        return colorsCount;
    }

    public int getStripesCount() {
        return stripesCount;
    }

    public boolean hasBrown() {
        return hasBrown;
    }

    public boolean hasCenterOfSymmetry() {
        return hasCenterOfSymmetry;
    }

    public String getTopMostColor() {
        return topMostColor;
    }

    public boolean hasSpecialFeatures() {
        return hasSpecialFeatures;
    }

    public String getColorOnRight() {
        return colorOnRight;
    }

    public int getStarsCount() {
        return starsCount;
    }

    public String getWidthHeightRatio() {
        return widthHeightRatio;
    }

    public String getBlueHue() {
        return blueHue;
    }

    public String getDominatingColor() {
        return dominatingColor;
    }
}
