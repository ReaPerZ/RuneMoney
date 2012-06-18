package Variables;

public class Scales {

    public static enum x {
        DRAGONSCALE(1),
        CRUSHEDSCALE(1),
        PASTLEANDMORTAR(1);

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
