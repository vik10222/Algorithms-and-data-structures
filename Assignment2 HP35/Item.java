public class Item {
private ItemType type; //private DataType variableName; bascaiily means type can be of the set of values ItemType
private int value = 0; 

public Item(ItemType type, int value) {
    this.type = type;
    this.value = value;
}

public ItemType type() {
    return type;
}

public static Item Add() {
    return new Item(ItemType.ADD, 0);
}

public static Item Sub() {
    return new Item(ItemType.SUB, 0);
}

public static Item Mul() {
    return new Item(ItemType.MUL, 0);
}

public static Item Div() {
    return new Item(ItemType.DIV, 0);
}

public static Item value(int val) {
    return new Item(ItemType.VALUE, val);
}

public int value() {
    return value;
}

}
