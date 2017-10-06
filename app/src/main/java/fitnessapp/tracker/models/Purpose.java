package fitnessapp.tracker.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Purpose {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(canBeNull = false)
    private boolean increase;

    @DatabaseField()
    private long lastUpdated;

    @DatabaseField(foreign = true)
    private Goal parent;

    private double reachedValue;

    @DatabaseField(canBeNull = false)
    private double targetValue;

    @DatabaseField(canBeNull = false)
    private long till;

    @DatabaseField(canBeNull = false)
    private PurposeType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Goal getParent() {
        return parent;
    }

    public void setParent(Goal parent) {
        this.parent = parent;
    }

    public double getReachedValue() {
        return reachedValue;
    }

    public void setReachedValue(double reachedValue) {
        this.reachedValue = reachedValue;
    }

    public double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(double targetValue) {
        this.targetValue = targetValue;
    }

    public long getTill() {
        return till;
    }

    public void setTill(long till) {
        this.till = till;
    }

    public PurposeType getType() {
        return type;
    }

    public void setType(PurposeType type) {
        this.type = type;
    }

    //TODO add logic to method
    public boolean isAchieved() {
        return false;
    }

    public boolean isIncrease() {
        return increase;
    }

    public void setIncrease(boolean increase) {
        this.increase = increase;
    }

}
