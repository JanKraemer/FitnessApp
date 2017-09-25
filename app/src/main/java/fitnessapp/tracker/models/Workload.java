package fitnessapp.tracker.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable( tableName = "workloads" )
public class Workload {

    @DatabaseField( generatedId = true )
    private Integer id;

    @DatabaseField( canBeNull = false )
    private Integer exerciseId;

    @DatabaseField( canBeNull = false )
    private Integer reps;

    @DatabaseField( canBeNull = false )
    private Integer weigth;

    @DatabaseField( foreign = true )
    private Exercise parent;

    public Integer getId( ) {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public Integer getExerciseId( ) {
        return exerciseId;
    }

    public void setExerciseId( Integer exerciseId ) {
        this.exerciseId = exerciseId;
    }

    public Integer getReps( ) {
        return reps;
    }

    public void setReps( Integer reps ) {
        this.reps = reps;
    }

    public Integer getWeigth( ) {
        return weigth;
    }

    public void setWeigth( Integer weigth ) {
        this.weigth = weigth;
    }

    public Exercise getParent( ) {
        return parent;
    }

    public void setParent( Exercise parent ) {
        this.parent = parent;
    }
}
