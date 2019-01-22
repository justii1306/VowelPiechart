package sample;

public class Data {
    String napis;
    int aCount;
    int eCount;
    int iCount;
    int oCount;
    int uCount;

    public Data(String napis) {
        napis = napis.toLowerCase();
        this.napis = napis;
        String tempNapis = napis;
        this.aCount = tempNapis.length() - tempNapis.replace("a", "").length();
        tempNapis = napis;
        this.eCount = tempNapis.length() - tempNapis.replace("e", "").length();
        tempNapis = napis;
        this.iCount = tempNapis.length() - tempNapis.replace("i", "").length();
        tempNapis = napis;
        this.oCount = tempNapis.length() - tempNapis.replace("o", "").length();
        tempNapis = napis;
        this.uCount = tempNapis.length() - tempNapis.replace("u", "").length();
    }

    public String getNapis() {
        return napis;
    }

    public void setNapis(String napis) {
        this.napis = napis;
    }

    public int getaCount() {
        return aCount;
    }

    public int geteCount() {
        return eCount;
    }

    public int getiCount() {
        return iCount;
    }

    public int getoCount() {
        return oCount;
    }

    public int getuCount() {
        return uCount;
    }
}
