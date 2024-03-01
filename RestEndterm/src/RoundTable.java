public class RoundTable extends ShapedTable {
    public RoundTable(int capacity) {
        super(capacity, "round");
    }
        @Override
    public void assigningTables() {
        if (!isAssigned()) {
            System.out.println("Assigning " + getShapeSpecificMessage() + " table with capacity " + getCapacity() + "...");
            setAssigned(true);
        } else {
            System.out.println(getShapeSpecificMessage() + " table with capacity " + getCapacity() + " is already assigned.");
        }
    }
    @Override
    public String getShapeSpecificMessage() {
        return "Comfortable";
    }
}
