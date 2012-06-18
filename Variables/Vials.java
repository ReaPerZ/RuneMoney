package Variables;

public class Vials {

    public static enum x {
        EMPTYVIAL(229),
        VIALOFWATER(227),
        FOUNTAIN(47150),
        BANK(3416);

        private final int id;

        x(
                int id
        ) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

}
