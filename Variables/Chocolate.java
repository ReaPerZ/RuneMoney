package Variables;

public class Chocolate {

    public static enum x {
        CHOCOLATE(1973),
        DUST(1975);

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
