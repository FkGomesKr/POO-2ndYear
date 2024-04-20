public class UserStats {

    private int calories_burnt;
    private int kms_runned;
    private int altimetry_meters;

    public UserStats(int calories_burnt, int kms_runned, int altimetry_meters) {
        this.calories_burnt = calories_burnt;
        this.kms_runned = kms_runned;
        this.altimetry_meters = altimetry_meters;
    }

    public int getCalories_burnt() {
        return calories_burnt;
    }

    public void setCalories_burnt(int calories_burnt) {
        this.calories_burnt = calories_burnt;
    }

    public int getKms_runned() {
        return kms_runned;
    }

    public void setKms_runned(int kms_runned) {
        this.kms_runned = kms_runned;
    }

    public int getAltimetry_meters() {
        return altimetry_meters;
    }

    public void setAltimetry_meters(int altimetry_meters) {
        this.altimetry_meters = altimetry_meters;
    }
}
