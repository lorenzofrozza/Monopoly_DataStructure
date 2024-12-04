public class Node {
    public RealEstate properties;
    private Node next;

    public Node(RealEstate properties){
        this.properties = properties;
        this.next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public RealEstate getProperties() {
        return properties;
    }

    public void setProperties(RealEstate properties) {
        this.properties = properties;
    }
}
