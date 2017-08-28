package fitnessapp.tracker.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Training {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private long from;

    @DatabaseField(canBeNull = false)
    private long till;

    @DatabaseField(canBeNull = false)
    private String title;

    @ForeignCollectionField(eager = true)
    private ForeignCollection<Exercise> exercises;

    @DatabaseField(canBeNull = false)
    private ExerciseType type;

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTill() {
        return till;
    }

    public void setTill(long till) {
        this.till = till;
    }



    public ForeignCollection<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ForeignCollection<Exercise> exercises) {
        this.exercises = exercises;
    }

    public ExerciseType getType() {
        return type;
    }

    public void setType(ExerciseType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
