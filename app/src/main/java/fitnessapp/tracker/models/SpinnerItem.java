package fitnessapp.tracker.models;

public class SpinnerItem {

    private Integer imageId;

    private String text;


    public SpinnerItem(String text, Integer imageId) {
        this.text = text;
        this.imageId = imageId;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
