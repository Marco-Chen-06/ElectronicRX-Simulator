package com.example.electronicrx_program;

public class Drug {
    private String name;
    private String namePlaceholder;
    private String brandName;
    private String ndc;
    private String ndcPlaceholder;
    private String instructions; //also known as SIG in pharmacy terms
    private double pricePer30;
    private double copay;
    private double copayPlaceholder;
    private String generalUsage;
    public Drug(String name, String brandName, String ndc, String instructions, double pricePer30, String generalUsage)
    {
        this.name = name;
        this.namePlaceholder = name;
        this.brandName = brandName;
        this.ndc = ndc;
        this.ndcPlaceholder = ndc;
        this.instructions = instructions;
        this.pricePer30 = pricePer30;
        copay = rand(0, (int)pricePer30 - 1);
        this.generalUsage = generalUsage;
    }
    public String getName()
    {
        return name;
    }
    public String getNamePlaceholder()
    {
        return namePlaceholder;
    }
    public void setNamePlaceholder(String x)
    {
        namePlaceholder = x;
    }
    public String getBrandName()
    {
        return brandName;
    }
    public String getNdc()
    {
        return ndc;
    }
    public String getInstructions()
    {
        return instructions;
    }
    public double getPricePer30()
    {
        return pricePer30;
    }
    public double getCopay()
    {
        return copay;
    }
    public void setDrugName(String x)
    {
        name = x;
    }
    public void setCopay(double x)
    {
        copay = x;
    }
    public void setPricePer30(double x)
    {
        pricePer30 = x;
    }
    public void setNdc(String x)
    {
        ndc = x;
    }
    public void setNdcPlaceholder(String x)
    {
        ndcPlaceholder = x;
    }
    public String getNdcPlaceholder()
    {
        return ndcPlaceholder;
    }
    public void setInstructions(String x)
    {
        instructions = x;
    }
    public static double rand(int min, int max)
    {
        return (Math.random()*(max-min+1)+min);
    }
    public String getGeneralUsage()
    {
        return generalUsage;
    }
    public int compareToDrugName(Drug other) {
        return name.compareTo(other.name);
    }
    public int compareToNdc(Drug other) {
        return ndc.compareTo(other.ndc);
    }

    public String toString() {
        return name;
    }
}