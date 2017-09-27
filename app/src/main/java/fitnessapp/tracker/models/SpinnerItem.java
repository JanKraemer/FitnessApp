package fitnessapp.tracker.models;

public class SpinnerItem {

    private String text;
    private Integer imageId;


    public SpinnerItem(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }

    public void setText( String text ) {
        this.text = text;
    }

    public void setImageId( Integer imageId ) {
        this.imageId = imageId;
    }
}
