public abstract class ShapedTable extends Tables {
    private String shape;
    public ShapedTable(int capacity, String shape){
        super(capacity);
        this.shape = shape;
    }
    public String getShape(){
        return shape;
    }
    public abstract String getShapeSpecificMessage();
}
