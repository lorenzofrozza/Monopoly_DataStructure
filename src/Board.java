public class Board <T> {
    private Node head;
    private int size;

    public Board(){
        this.head = null;
        this.size = 0;
    }

    public Node getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    //insert
    public void insert(RealEstate realEstate) {
        Node newNode = new Node(realEstate);
        if (head == null) {
            head = newNode;
            head.setNext(head); // Circular link
        } else {
            Node temp = head;
            while (temp.getNext() != head) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setNext(head); // Maintain circularity
        }
        size++;
    }

    //returns the property by name
    public Node getPropertyByName(String name) {
        if (head == null) return null;

        Node temp = head;
        do {
            if (temp.getProperties().getName().equals(name)) {
                return temp;
            }
            temp = temp.getNext();
        } while (temp != head);

        return null; // Not found
    }

    // Remove node by property name
    boolean removeByName(String name) {
        if (head == null) return false;

        Node current = head;
        Node previous = null;

        do {
            if (current.getProperties().getName().equals(name)) {
                if (previous == null) { // Removendo o head
                    if (current.getNext() == head) {
                        head = null; // Último nó
                    } else {
                        Node temp = head;
                        while (temp.getNext() != head) {
                            temp = temp.getNext();
                        }
                        head = head.getNext();
                        temp.setNext(head);
                    }
                } else {
                    previous.setNext(current.getNext());
                }
                size--;
                return true;
            }
            previous = current;
            current = current.getNext();
        } while (current != head);

        return false; // Not found
    }

    // Get property by position
    public Node getPropertyByPosition(int position) {
        if (head == null) return null;

        Node temp = head;
        int count = 0;
        do {
            if (count == position) {
                return temp;  // Retorna o nó na posição correta
            }
            temp = temp.getNext();
            count++;
        } while (temp != head);

        return null; // Se a posição não for encontrada
    }

    // list real estate
    public void list() {
        if (head == null) {
            System.out.println("No property registered.");
            return;
        }

        Node current = head;
        do {
            RealEstate rs = current.properties;
            System.out.println("- Name: " + rs.getName());
            System.out.println("  Type: " + rs.getType());
            System.out.println("  Purchase price: " + rs.getPurchasePrice());
            System.out.println("  Rent price: " + rs.getRentPrice());
            System.out.println("  Owner: " + (rs.getOwner() == null ? "No owner" : rs.getOwner().getName()));
            System.out.println();
            current = current.getNext();
        } while (current != head);
    }
}
