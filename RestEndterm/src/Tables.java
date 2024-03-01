public abstract class Tables {
    public int capacity;
    private boolean assigned;
    public Tables(int capacity) {
        this.capacity = capacity;
        this.assigned = false;
    }
    public int getCapacity() {
        return capacity;
    }
    public boolean isAssigned() {
        return assigned;
    }
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
    public abstract void assigningTables();
}

