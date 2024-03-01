public class SquareTable extends ShapedTable {
    public SquareTable(int capacity) {
        super(capacity, "square");
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
        return "Space-efficient ";
    }
}